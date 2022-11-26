package BusinessLogic;

import Connection.ConnectionFactory;
import DataAccess.ClientDAO;
import DataAccess.OrderDAO;
import DataAccess.ProductDAO;
import GUI.OrderInputView;
import Model.Client;
import Model.Orderr;
import Model.Product;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * @author : Tivadar Maria Simona
 *          gr 30223
 * @since : Apr 17 2022
 * Usage: not only implement the CRUD operations of a SQL table through DAO but also complete the other business logic
 * to assure the rightness of the operations
 * throws RuntimeException NoSuchElementException - unchecked
 * @see DataAccess.AbstractDAO
 * @see OrderDAO
 */
public class OrderBLL {
    private FileWriter myFile;
    private OrderInputView oiw;
    private OrderDAO orderDAO;
    private ProductBLL productBLL;
    private ClientBLL clientBLL;
    private ConnectionFactory connection;

    public OrderBLL() {
        orderDAO = new OrderDAO();
        productBLL = new ProductBLL();
        clientBLL = new ClientBLL();
        oiw = new OrderInputView();
    }


    public Orderr findOrderById(int id) {
        Orderr st = orderDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return st;
    }


    public List<Orderr> findAll(){
        List<Orderr> orders;
        orders = orderDAO.findAll();
        if (orders.size() == 0) {
            throw new NoSuchElementException("There are no products in the data base yet");
        }
        return orders;
    }
    public void insert(Orderr o){
        Product p = productBLL.findProductById(o.getIdProduct());
        if (p == null) {
            throw new NoSuchElementException("The product with id =" + o.getIdProduct() + " was not found!");
        }
        Client c = clientBLL.findClientById(o.getIdClient());
        if (c == null) {
            throw new NoSuchElementException("The product with id =" + o.getIdProduct() + " was not found!");
        }
        o.setTotalPrice(o.getQuantity()*p.getPret());
        if(o.getQuantity() > p.getStoc())
            JOptionPane.showMessageDialog(oiw, "Nu avem destule bucati in stoc. Introduceti o cantitate mai mica");
        else {
            int newValue = p.getStoc() - o.getQuantity();
            String field = "stoc";
            productBLL.update(p.getId(), field, String.valueOf(newValue));
            orderDAO.insertt(o);
        }
    }
    public void writeBill(Orderr o){
        Client c = clientBLL.findClientById(o.getIdClient());
        Product p = productBLL.findProductById(o.getIdProduct());
        try {
            myFile = new FileWriter("file" + o.getId() +".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Comanda cu ID-ul ");
            sb.append( o.getId());
            sb.append(":\nEfectuata de clientul cu ID-ul ");
            sb.append(c.getId());
            sb.append(" cu numele ");
            sb.append(c.getName());
            sb.append(" \nS-au achizitionat produse cu ID-ul ");
            sb.append(p.getId());
            sb.append(" adica ");
            sb.append(p.getNume() );
            sb.append("\nin cantitate de ");
            sb.append(o.getQuantity());
            sb.append(" cu o valoare totala de ");
            sb.append(o.getTotalPrice());
            myFile.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }try {
            myFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeAllBills(){
        List<Orderr> orders = findAll();
        if(orders.size() != 0)
            for(Orderr o : orders)
                writeBill(o);
            else JOptionPane.showMessageDialog(oiw, "Nu avem comenzi deci nu putem genera nicio factura");
    }
    public void update(int id, String field, String newValue){
        if(findOrderById(id) == null)
            throw new NoSuchElementException("No product with this ID");
        orderDAO.updatee(id,field,newValue);
    }
}
