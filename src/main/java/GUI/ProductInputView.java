package GUI;

import BusinessLogic.ProductBLL;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author : Tivadar Maria Simona
 *          gr 30223
 * @since : Apr 17 2022
 * Usage: allows user to insert the data needed in order to complete the CRUD operations on Product object
 * @see ProductView
 */

public class ProductInputView extends JFrame {
    private JComboBox fieldMenu = new JComboBox(new String[]{"Nume", "Pret", "Stoc"});
    private JTextField newValue = new JTextField(7);
    private JPanel content = new JPanel();
    private JButton btnClient = new JButton("Mai departe");
    private  JButton backBtn = new JButton("Inapoi");
    private JTextField t_id = new JTextField("Introduceti ID:");
    private JTextField id = new JTextField(7);
    private JTextField t_name = new JTextField("Introduceti numele");
    private JTextField name = new JTextField(7);
    private JTextField t_pret = new JTextField("Introduceti pretul");
    private JTextField pret = new JTextField(7);
    private JTextField t_stoc = new JTextField("Introduceti stocul");
    private JTextField stoc = new JTextField(7);

    public ProductInputView(){
    }
    public ProductInputView(int type) {
        //type = 0 findById
        //type = 1 insert
        //type = 2 delete
        //type = 3 update
        this.setContentPane(content);
        this.pack();
        this.setTitle("Produse de modificat");
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
                        Product p = new ProductBLL().findProductById(Integer.parseInt(input));
                        initTupla(p.toString());
                    } catch (NullPointerException | NumberFormatException nfex) {
                        showError("Ati uitat sa introduceti ID-ul");
                    } catch (IndexOutOfBoundsException nfex) {
                        showError("Nu exista niciun produs cu ID-ul cautat de d-voastra");
                    }
                } else if (type == 1) {
                    String i_id = id.getText();
                    String i_name = name.getText();
                    String i_pret = pret.getText();
                    String i_stoc = stoc.getText();
                    try {
                        Product p = new Product(Integer.parseInt(i_id), i_name, Integer.parseInt(i_pret), Integer.parseInt(i_stoc));
                        ProductBLL pbll = new ProductBLL();
                        pbll.insert(p);
                    } catch (NullPointerException | NumberFormatException nfex) {
                        showError("Ati uitat sa introduceti ID-ul");
                    } catch (IndexOutOfBoundsException nfex) {
                        showError("Nu exista niciun produs cu ID-ul cautat de d-voastra");
                    }
                }
                else if (type == 2) {
                    String i_id = id.getText();
                    try {
                        ProductBLL pbll = new ProductBLL();
                        pbll.delete(Integer.parseInt(i_id));
                    } catch (NullPointerException | NumberFormatException nfex) {
                        showError("Ati uitat sa introduceti ID-ul");
                    }
                }
                else if (type == 3) {
                    String i_id = id.getText();
                    String nv = newValue.getText();
                    try {
                        ProductBLL pbll = new ProductBLL();
                        if(fieldMenu.getSelectedItem().toString().equals("Nume"))
                            pbll.update(Integer.parseInt(i_id), "name", nv);
                        if(fieldMenu.getSelectedItem().toString().equals("Pret"))
                            pbll.update(Integer.parseInt(i_id), "pret",nv);
                        if(fieldMenu.getSelectedItem().toString().equals("Stoc"))
                            pbll.update(Integer.parseInt(i_id), "stoc",nv);
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
        t_pret.setEditable(false);
        t_name.setEditable(false);
        t_stoc.setEditable(false);

        t_id.setBounds(20,10,100,30);
        id.setBounds(270,10,100,30);
        t_name.setBounds(20,50,100,30);
        name.setBounds(270,50,100,30);
        t_pret.setBounds(20,90,100,30);
        pret.setBounds(270,90,100,30);
        t_stoc.setBounds(20,130,100,30);
        stoc.setBounds(270,130,100,30);

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
            t_pret.setVisible(true);
            pret.setVisible(true);
            content.add(t_pret);
            content.add(pret);
            t_stoc.setVisible(true);
            stoc.setVisible(true);
            content.add(t_stoc);
            content.add(stoc);
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
    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
}
