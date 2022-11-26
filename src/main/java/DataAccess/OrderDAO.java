package DataAccess;

import Model.Orderr;
/**
 * @author : Tivadar Maria Simona
 *          gr 30223
 * @since : Apr 17 2022
 * Usage: implements the methods from the parent class specifically for it's object type - in this case, Order
 *         strong example of using Generics
 * @see DataAccess.AbstractDAO
 */

public class OrderDAO extends AbstractDAO<Orderr>{
    public void insertt(Orderr o){
        insert(o); //????
    }
    public void deletee(int id){
        delete(id);
    }
    public void updatee(int id, String field, String newValue){
        update(id, field, newValue);
    }
}
