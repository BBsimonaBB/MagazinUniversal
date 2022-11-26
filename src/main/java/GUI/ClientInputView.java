package GUI;

import BusinessLogic.ClientBLL;
import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author : Tivadar Maria Simona
 *          gr 30223
 * @since : Apr 17 2022
 * Usage: allows user to insert the data needed in order to complete the CRUD operations on Client object
 * @see ClientView
 */
public class ClientInputView extends JFrame {
    private JComboBox fieldMenu = new JComboBox(new String[]{"Nume", "Adresa", "Email"});
    private JTextField newValue = new JTextField(7);
    private JPanel content = new JPanel();
    private JButton btnClient = new JButton("Mai departe");
    private  JButton backBtn = new JButton("Inapoi");
    private JTextField t_id = new JTextField("Introduceti ID:");
    private JTextField id = new JTextField(7);
    private JTextField t_name = new JTextField("Introduceti numele");
    private JTextField name = new JTextField(7);
    private JTextField t_address = new JTextField("Introduceti adresa");
    private JTextField address = new JTextField(7);
    private JTextField t_email = new JTextField("Introduceti email-ul");
    private JTextField email = new JTextField(7);


    public ClientInputView(){
    }
    public ClientInputView(int type) {
        //type = 0 findById
        //type = 1 insert
        //type = 2 delete
        //type = 3 update
        this.setContentPane(content);
        this.pack();
        this.setTitle("Clienti de modificat");
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,350);
        content.setLayout(null);

        Color color_background = new Color(216, 175, 241);
        content.setBackground(color_background);
        btnClient.setBounds(200,250,200,40);
        btnClient.setVisible(true);
        btnClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (type == 0) {
                    String input = id.getText();
                    try {
                        Client c = new ClientBLL().findClientById(Integer.parseInt(input));
                        initTupla(c.toString());
                    } catch (NullPointerException | NumberFormatException nfex) {
                        showError("Ati uitat sa introduceti ID-ul");
                    } catch (IndexOutOfBoundsException nfex) {
                        showError("Nu exista niciun client cu ID-ul cautat de d-voastra");
                    }
                } else if (type == 1) {
                    String i_id = id.getText();
                    String i_name = name.getText();
                    String i_address = address.getText();
                    String i_email = email.getText();
                    try {
                        Client c = new Client(Integer.parseInt(i_id),i_name,i_address,i_email);
                        ClientBLL cbll = new ClientBLL();
                        cbll.insert(c);
                    } catch (NullPointerException | NumberFormatException nfex) {
                        showError("Ati uitat sa introduceti ID-ul");
                    } catch (IndexOutOfBoundsException nfex) {
                        showError("Nu exista niciun client cu ID-ul cautat de d-voastra");
                    }
                }
                else if (type == 2) {
                    String i_id = id.getText();
                    try {
                        ClientBLL cbll = new ClientBLL();
                        cbll.delete(Integer.parseInt(i_id));
                    } catch (NullPointerException | NumberFormatException nfex) {
                        showError("Ati uitat sa introduceti ID-ul");
                    }
                }
                else if (type == 3) {
                    String i_id = id.getText();
                    String nv = newValue.getText();
                    try {
                        ClientBLL cbll = new ClientBLL();
                         if(fieldMenu.getSelectedItem().toString().equals("Nume"))
                             cbll.update(Integer.parseInt(i_id), "name", nv);
                        if(fieldMenu.getSelectedItem().toString().equals("Adresa"))
                            cbll.update(Integer.parseInt(i_id), "address",nv);
                        if(fieldMenu.getSelectedItem().toString().equals("Email"))
                            cbll.update(Integer.parseInt(i_id), "email",nv);
                    } catch (NullPointerException | NumberFormatException nfex) {
                        showError("Ati uitat sa introduceti ID-ul");
                    } catch (IndexOutOfBoundsException nfex) {
                        showError("Nu exista niciun client cu ID-ul cautat de d-voastra");
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
        t_address.setEditable(false);
        t_name.setEditable(false);
        t_email.setEditable(false);

        t_id.setBounds(20,10,100,30);
        id.setBounds(270,10,100,30);
        t_name.setBounds(20,50,100,30);
        name.setBounds(270,50,100,30);
        t_address.setBounds(20,90,100,30);
        address.setBounds(270,90,100,30);
        t_email.setBounds(20,130,100,30);
        email.setBounds(270,130,100,30);

        t_id.setVisible(true);
        id.setVisible(true);
        content.add(t_id);
        content.add(id);
        content.add(btnClient);
        content.add(backBtn);
        if(type == 1){
            t_name.setVisible(true);
            name.setVisible(true);
            content.add(t_name);
            content.add(name);
            t_address.setVisible(true);
            address.setVisible(true);
            content.add(t_address);
            content.add(address);
            t_email.setVisible(true);
            email.setVisible(true);
            content.add(t_email);
            content.add(email);
        }
        if(type == 3){
            fieldMenu.setVisible(true);
            fieldMenu.setBounds(20,100,120,30);
            content.add(fieldMenu);
            newValue.setVisible(true);
            newValue.setBounds(270,50,100,30);
            content.add(newValue);
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
    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
}
