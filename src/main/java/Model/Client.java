package Model;
/**
 * @author : Tivadar Maria Simona
 *          gr 30223
 * @since : Apr 17 2022
 * Usage: implement the Client object and the respective CRUD operations on it
 *        it's fields has to exact the ones mentioned in the database too
 */

public class Client {
    private  Integer id;
    private String name;
    private String address;
    private String email;

    public Client(){

    }

    public Client(Integer id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString(){
        return id + "    " + name + "    " + address + "    " + email;
    }
}
