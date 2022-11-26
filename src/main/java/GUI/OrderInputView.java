package GUI;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;
import Model.Client;
import Model.Orderr;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author : Tivadar Maria Simona
 *          gr 30223
 * @since : Apr 17 2022
 * Usage: allows user to insert the data needed in order to complete the CRUD operations on Order object
 * @see OrderView
 */
public class OrderInputView extends JFrame {
    ClientBLL cliientBLL = new ClientBLL();
    ProductBLL productBLL = new ProductBLL();
    private JComboBox fieldClient = new JComboBox();
    private JComboBox fieldProduct = new JComboBox();
    private JTextField newValue = new JTextField(7);
    private JPanel content = new JPanel();
    private JButton btnClient = new JButton("Mai departe");
    private  JButton backBtn = new JButton("Inapoi");
    private JTextField t_id = new JTextField("Introduceti ID Comanda:");
    private JTextField id = new JTextField(7);
    private JTextField t_idc = new JTextField("Introduceti nume Client:");
    //private JTextField idc = new JTextField(7);
    private JTextField t_idp = new JTextField("Introduceti nume Produs");
    //private JTextField idp = new JTextField(7);
    private JTextField t_cantitate = new JTextField("Introduceti cantitatea");
    private JTextField cantitate = new JTextField(7);
    public OrderInputView(){
    }
    public OrderInputView(int type) {

        List<Client> clients =  cliientBLL.findAll();
        for(Client c : clients)
            fieldClient.addItem(c.getName());
        List<Product> products =  productBLL.findAll();
        for(Product p : products)
            fieldProduct.addItem(p.getNume());
        //type = 0 findById
        //type = 1 insert
        //type = 2 delete
        //type = 3 update
        this.setContentPane(content);
        this.pack();
        this.setTitle("Comenzi de facut");
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,350);
        content.setLayout(null);


        fieldClient.setBounds(400,10,100,30);
        fieldClient.setVisible(true);
        content.add(fieldClient);

        fieldProduct.setBounds(400,50,100,30);
        fieldProduct.setVisible(true);
        content.add(fieldProduct);

        Color color_background = new Color(216, 175, 241);
        content.setBackground(color_background);
        btnClient.setBounds(200,250,200,40);
        btnClient.setVisible(true);
        btnClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (type == 0) {
                    String input = id.getText();
                    try {
                        Orderr p = new OrderBLL().findOrderById(Integer.parseInt(input));
                        initTupla(p.toString());
                    } catch (NullPointerException | NumberFormatException nfex) {
                        showError("Ati uitat sa introduceti ID-ul");
                    } catch (IndexOutOfBoundsException nfex) {
                        showError("Nu exista niciun produs cu ID-ul cautat de d-voastra");
                    }
                } else if (type == 1) {
                    String i_id = id.getText();
                    //String i_idc = idc.getText();
                    //String i_idp = idp.getText();
                    String i_cantitate = cantitate.getText();

                    int id_product = 0;
                    int id_client = 0;
                    try {
                        //Orderr o = new Orderr(Integer.parseInt(i_id),Integer.parseInt(i_idc),Integer.parseInt(i_idp),Integer.parseInt(i_cantitate));
                        for(Product p : products){
                            if(fieldProduct.getItemAt(fieldProduct.getSelectedIndex()).equals(p.getNume())) {
                                id_product = p.getId();
                                break;
                            }
                        }
                        for(Client c : clients){
                            if(fieldClient.getItemAt(fieldClient.getSelectedIndex()).equals(c.getName())) {
                                id_client = c.getId();
                                break;
                            }
                        }
                        Orderr o = new Orderr(Integer.parseInt(i_id),id_client,id_product,Integer.parseInt(i_cantitate));
                        OrderBLL obll = new OrderBLL();
                        obll.insert(o);
                    } catch (NullPointerException | NumberFormatException nfex) {
                        showError("Ati uitat sa introduceti ID-ul");
                    } catch (IndexOutOfBoundsException nfex) {
                        showError("Nu exista niciun produs cu ID-ul cautat de d-voastra");
                    }
                }
            }
        });


        backBtn.setBounds(20,250,100,50);
        backBtn.setVisible(true);
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        t_id.setEditable(false);
        t_cantitate.setEditable(false);
        t_idc.setEditable(false);
        t_idp.setEditable(false);

        t_id.setBounds(20,10,150,30);
        id.setBounds(270,10,100,30);
        t_idc.setBounds(20,50,150,30);
        fieldClient.setBounds(270,50,100,30);
        t_idp.setBounds(20,90,150,30);
        fieldProduct.setBounds(270,90,100,30);
        t_cantitate.setBounds(20,130,150,30);
        cantitate.setBounds(270,130,100,30);

        t_id.setVisible(true);
        id.setVisible(true);
        content.add(t_id);
        content.add(id);
        content.add(btnClient);
        content.add(backBtn);
        if(type == 1){
            t_idp.setVisible(true);
            fieldProduct.setVisible(true);
            t_idc.setVisible(true);
            fieldClient.setVisible(true);
            content.add(t_idp);
            content.add(fieldProduct);
            content.add(t_idc);
            content.add(fieldClient);
            t_cantitate.setVisible(true);
            cantitate.setVisible(true);
            content.add(t_cantitate);
            content.add(cantitate);
        }
    }
    public void initTupla(String data){
        Font f1  = new Font(Font.DIALOG_INPUT, Font.BOLD,12);
        JTextField ta = new JTextField(data);
        ta.setFont(f1);
        ta.setBackground((new Color(216, 175, 241)));
        ta.setBounds(30,50 ,400,30);
        ta.setVisible(true);
        content.add(ta);
        content.setSize(600,350);
        content.setBackground(new Color(216, 175, 241));
        content.setVisible(true);
    }

    String getID() {
        return id.getText();
    }
    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
}
