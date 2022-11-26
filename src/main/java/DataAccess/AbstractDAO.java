package DataAccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author : Tivadar Maria Simona
 *          gr 30223
 * @since : Apr 17 2022
 * Usage: implement the basic CRUD operations for mySQL database usage
 *        write the queries that will be run in mySQL such as createSelectQuery, createUpdateQuery....
 *        strong example of Reflection for the 3 tables that we used in our program
 *        strong example of using Generics to help whit writing clean and non-repetitive code
 *        so that the CRUD operations are implemented only once and useful for all 3 object types
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    // DELETE FROM client WHERE id = 4;
    private String createDeleteQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id = ?");
        return sb.toString();
    }
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }
    public String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO ");
        sb.append(type.getSimpleName() + " (");
        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                sb.append(field.getName() + ", ");

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

        }
        sb.delete(sb.length()-2, sb.length());
        sb.append(") VALUES (");
        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            sb.append("?, ");
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append(")");
        System.out.println(sb + " ");
        return sb.toString();
    }
    //UPDATE client SET ?  where id = ?;
    private String createUpdateQuery(String field, int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        sb.append(field);
        sb.append(" = ? ");
        sb.append(" WHERE id = ?");
        System.out.println(sb.toString());
        return sb.toString();
    }

    public List<T> findAll() {
        // TODO:
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        String st = sb.toString();
        ArrayList<T> objects = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = st;
        int i=0;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            objects = (ArrayList<T>) createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findall " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return objects;
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
    public JTable createTable(ArrayList<T> arrayList){
        String[] column = new String[0];
        column = Reflection.retrieveColumn(arrayList.get(0));
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.setColumnIdentifiers(column);
        JTable table = new JTable(defaultTableModel);
        Object[][] obj = Reflection.retrieveData(arrayList);
        for(Object o : obj) {
            defaultTableModel.addRow((Object[]) o);
        }
        return table;
    }
    public T insert(T t)  {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i=1;
            for(Field field: t.getClass().getDeclaredFields()){
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(t);
                    if(value instanceof String)
                        statement.setString(i, (String) value);
                    else {
                        int n = (Integer) value;
                        statement.setInt(i, n);
                    }
                    i++;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
            int rows = statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:InsertERROR " + e.getMessage());
        } finally {
            //ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    public void update(int id, String field, String newValue) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery(field, id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            try {
                statement.setInt(1, Integer.parseInt(newValue));
            }
            catch (NumberFormatException nfex) {
                statement.setString(1, newValue);
            }
            statement.setInt(2, id);
            int row = statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            //ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
        public void delete (int id){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
