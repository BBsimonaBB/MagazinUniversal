package BusinessLogic;

import Connection.ConnectionFactory;
import DataAccess.ClientDAO;
import DataAccess.ProductDAO;
import Model.Product;

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
 * @see ProductDAO
 */
public class ProductBLL {
    private ProductDAO productDAO;
    private ConnectionFactory connection;

    public ProductBLL() {
        productDAO = new ProductDAO();
    }


    public Product findProductById(int id) {
        Product st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }
    public List<Product> findAll(){
        List<Product> products;
        products = productDAO.findAll();
        if (products.size() == 0) {
            throw new NoSuchElementException("There are no products in the data base yet");
        }
        return products;
    }
    public void insert(Product p){
        productDAO.insertt(p);
    }
    public void delete(int id){
        if(findProductById(id) == null)
            throw new NoSuchElementException("No product with this ID");
        productDAO.deletee(id);
    }
    public void update(int id, String field, String newValue){
        if(findProductById(id) == null)
            throw new NoSuchElementException("No product with this ID");
        productDAO.updatee(id,field,newValue);
    }
}
