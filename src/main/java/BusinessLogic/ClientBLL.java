package BusinessLogic;
import BusinessLogic.Validators.EmailValidators;
import DataAccess.ClientDAO;
import Model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import Connection.ConnectionFactory;

/**
 * @author : Tivadar Maria Simona
 *          gr 30223
 * @since : Apr 17 2022
 * Usage: not only implement the CRUD operations of a SQL table through DAO but also complete the other business logic
 * to assure the rightness of the operations
 * throws RuntimeException NoSuchElementException - unchecked
 * @see DataAccess.AbstractDAO
 * @see ClientDAO
 */

public class ClientBLL {
    private ClientDAO clientDAO;
    private ConnectionFactory connection;
    private EmailValidators ev;

    public ClientBLL() {
        clientDAO = new ClientDAO();
        ev = new EmailValidators();
    }


    public Client findClientById(int id) {
        Client st = clientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return st;
    }

    public List<Client> findAll(){
        List<Client> clients = new ArrayList<Client>();
        clients = clientDAO.findAll();
        if (clients == null) {
            throw new NoSuchElementException("There are no clients in the data base yet");
        }
        return clients;
    }
    public void insert(Client c){
        ev.validate(c);
        clientDAO.insertt(c);
    }
    public void delete(int id){
        if(findClientById(id) == null)
            throw new NoSuchElementException("No client with this ID");
        clientDAO.deletee(id);
    }
    public void update(int id, String field, String newValue){
        if(findClientById(id) == null)
            throw new NoSuchElementException("No client with this ID");
        clientDAO.updatee(id,field,newValue);
    }
}