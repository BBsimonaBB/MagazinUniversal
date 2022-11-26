package GUI;

import BusinessLogic.ClientBLL;
import DataAccess.ClientDAO;
import DataAccess.AbstractDAO;
import DataAccess.Reflection;
import Model.Client;

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
public class ClientView extends JFrame {
    private JPanel content = new JPanel();
    private JComboBox clientMenu = new JComboBox(new String[]{"Afiseaza toti clientii", "Cauta dupa ID", "Modifica dupa ID","Sterge dupa ID", "Creeaza client" });
    private JButton next = new JButton("Cautare simpla");


    ClientView(){
        this.setContentPane(content);
        this.pack();
        this.setTitle("Clienti");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,500);
        content.setLayout(null);

        Color color_background = new Color(216, 175, 241);
        content.setBackground(color_background);

        content.add(clientMenu);
        clientMenu.setBounds(20,50,200,40);
        clientMenu.setVisible(true);

        content.add(next);
        next.setBounds(40,200,150,30);
        next.setVisible(true);

        Font f1  = new Font(Font.DIALOG_INPUT, Font.BOLD,15);


    }
    public void displayTable(){
        ArrayList<Client> client = (ArrayList) new ClientBLL().findAll();
        JScrollPane scrollPane= new JScrollPane();
        scrollPane.setBounds(230, 50, 450,200);

        JTable clientTable;
        ClientDAO cdao = new ClientDAO();
        clientTable = cdao.createTable(client);
        clientTable.setVisible(true);
        clientTable.setEnabled(true);

        scrollPane.setViewportView(clientTable);
        content.add(scrollPane);
    }
    public JComboBox getClientMenu() {
        return clientMenu;
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

    void addButtonClientListener(ActionListener mal) {
        next.addActionListener(mal);
    }
    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }

}
