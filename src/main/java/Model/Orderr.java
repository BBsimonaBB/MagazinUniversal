package Model;
/**
 * @author : Tivadar Maria Simona
 *          gr 30223
 * @since : Apr 17 2022
 * Usage: implement the Order object and the respective CRUD operations on it
 *        it's fields has to exact the ones mentioned in the database too
 */
public class Orderr {
    private int id;
    private int quantity;
    private int totalPrice;
    private int idClient;
    private int idProduct;

    public Orderr(int id, int idClient, int idProduct, int quantity) {
        this.id = id;
        this.quantity = quantity;
        this.totalPrice = 0;
        this.idClient = idClient;
        this.idProduct = idProduct;
    }
    public Orderr(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}

