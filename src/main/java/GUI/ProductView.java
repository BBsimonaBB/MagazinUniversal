package GUI;

import BusinessLogic.ProductBLL;
import DataAccess.ProductDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author : Tivadar Maria Simona
 *          gr 30223
 * @since : Apr 17 2022
 * Usage: allows user to choose the operation to be completed
 * @see StartView
 */

public class ProductView extends JFrame {
    private JPanel content = new JPanel();
    private JComboBox productMenu = new JComboBox(new String[]{"Afiseaza toate produsele", "Cauta dupa ID", "Modifica dupa ID","Sterge dupa ID", "Creeaza produs" });
    private JButton next = new JButton("Cautare simpla");


    ProductView(){
        this.setContentPane(content);
        this.pack();
        this.setTitle("Produse");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,500);
        content.setLayout(null);

        Color color_background = new Color(216, 175, 241);
        content.setBackground(color_background);

        content.add(productMenu);
        productMenu.setBounds(20,50,200,40);
        productMenu.setVisible(true);

        content.add(next);
        next.setBounds(40,200,150,30);
        next.setVisible(true);

        Font f1  = new Font(Font.DIALOG_INPUT, Font.BOLD,15);


    }
    public void displayTable(){
        ArrayList product = (ArrayList) new ProductBLL().findAll();
        JScrollPane scrollPane= new JScrollPane();
        scrollPane.setBounds(230, 50, 450,200);

        JTable productTable;
        ProductDAO cdao = new ProductDAO();
        productTable = cdao.createTable(product);
        productTable.setVisible(true);
        productTable.setEnabled(true);

        scrollPane.setViewportView(productTable);
        content.add(scrollPane);
    }
    public JComboBox getProductMenu() {
        return productMenu;
    }
    public void initTupla(String data, int line){
        Font f1  = new Font(Font.DIALOG_INPUT, Font.BOLD,15);
        JTextField ta = new JTextField(data);
        ta.setFont(f1);
        ta.setBackground((new Color(216, 175, 241)));
        ta.setBounds(280,50 + line,600,20);
        ta.setVisible(true);
        content.add(ta);
        content.setSize(1000,700);
        content.setBackground(new Color(216, 175, 241));
        content.setVisible(true);
    }

    void addButtonProductListener(ActionListener mal) {
        next.addActionListener(mal);
    }
    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }

}
