package GUI;

import BusinessLogic.OrderBLL;
import DataAccess.OrderDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author : Tivadar Maria Simona
 *          gr 30223
 * @since : Apr 17 2022
 * Usage: allows user to choose the operation to be completed
 * @see StartView
 */

public class OrderView extends JFrame {
    private OrderBLL orderBLL;
    private JPanel content = new JPanel();
    private JComboBox orderMenu = new JComboBox(new String[]{"Afiseaza toate comenzile", "Creeaza comanda" });
    private JButton next = new JButton("Cautare simpla");
    private JButton btnfactura = new JButton("Genereaza facturi");


    OrderView(){
        orderBLL = new OrderBLL();
        this.setContentPane(content);
        this.pack();
        this.setTitle("Comenzi");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,500);
        content.setLayout(null);

        Color color_background = new Color(216, 175, 241);
        content.setBackground(color_background);

        content.add(orderMenu);
        orderMenu.setBounds(20,50,200,40);
        orderMenu.setVisible(true);

        content.add(next);
        next.setBounds(40,200,150,30);
        next.setVisible(true);

        Font f1  = new Font(Font.DIALOG_INPUT, Font.BOLD,15);
        btnfactura.setBounds(300,250,300,40);
        btnfactura.setVisible(true);
        content.add(btnfactura);
        btnfactura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            orderBLL.writeAllBills();
            }
        } );

    }
    public void displayTable(){
        ArrayList order = (ArrayList) new OrderBLL().findAll();
        JScrollPane scrollPane= new JScrollPane();
        scrollPane.setBounds(230, 50, 450,200);

        JTable orderTable;
        OrderDAO cdao = new OrderDAO();
        orderTable = cdao.createTable(order);
        orderTable.setVisible(true);
        orderTable.setEnabled(true);

        scrollPane.setViewportView(orderTable);
        content.add(scrollPane);
    }
    public JComboBox getOrderMenu() {
        return orderMenu;
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

    void addButtonOrderListener(ActionListener mal) {
        next.addActionListener(mal);
    }
    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
}
