package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
/**
 * @author : Tivadar Maria Simona
 *          gr 30223
 * @since : Apr 17 2022
 * Usage: Start GUI windows - the first one that the user interacts with
 */

public class StartView extends JFrame {
    private JLabel welcome = new JLabel("Bun venit in magazinul nostru!");
    private JLabel welcome2 = new JLabel("Ce va intereseaza sa faceti azi?");
    private  JComboBox menu = new JComboBox(new String[]{"Detalii clienti", "Detalii produse", "Detalii comenzi"});
    private JButton next = new JButton("Mai departe");
    private Connection connection;

    StartView() throws FontFormatException {
        JPanel content = new JPanel();
        this.setContentPane(content);
        this.pack();
        this.setTitle("Supermercato");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450,300);
        content.setLayout(null);

        Font  f1  = new Font(Font.DIALOG_INPUT, Font.BOLD,15) ;
        Color color_background = new Color(205, 123, 238);
        content.setBackground(color_background);

        welcome.setBounds(70,30,300,20);
        welcome.setFont(f1);
        content.add(welcome);

        welcome2.setBounds(70,60,300,20);
        welcome2.setFont(f1);
        content.add(welcome2);

        menu.setBounds(120,120,150,30);
        content.add(menu);

        next.setBounds(120, 210, 150, 40);
        content.add(next);


    }
    void addGenerateListener(ActionListener mal) {
        next.addActionListener(mal);
    }

    public JComboBox getMenu() {
        return menu;
    }
}
