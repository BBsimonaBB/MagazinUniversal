package Model;
/**
 * @author : Tivadar Maria Simona
 *          gr 30223
 * @since :  Apr 17 2022
 * Usage: implement the Product object and the respective CRUD operations on it
 *        it's fields has to exact the ones mentioned in the database too
 */
public class Product {
    private int id;
    private String nume;
    private int stoc;
    private int pret;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public Product(int id, String nume, int stoc, int pret) {
        this.id = id;
        this.nume = nume;
        this.stoc = stoc;
        this.pret = pret;
    }
    public Product(){

    }
}
