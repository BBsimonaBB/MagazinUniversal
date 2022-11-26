package GUI;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
/**
 * @author : Tivadar Maria Simona
 *          gr 30223
 * @since : Apr 17 2022
 * Usage: main controll of the application
 */
public class Controller {
    protected static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
    private StartView sw;
    private ClientView cw;
    private ClientInputView ciw;
    private OrderView ow;
    private OrderInputView oiw;
    private ProductView pw;
    private ProductInputView piw;
    private ClientBLL clientBLL;
    private OrderBLL orderBLL;
    private ProductBLL productBLL;

    Controller(StartView sw, ClientView cw, OrderView ow, ProductView pw, ClientInputView ciw, ProductInputView piw, OrderInputView oiw){

        this.sw = sw;
        this.cw = cw;
        this.ow = ow;
        this.oiw = oiw;
        this.pw = pw;
        this.ciw = ciw;
        this.piw = piw;
        this.clientBLL = clientBLL;
        this.orderBLL = orderBLL;
        this.productBLL = productBLL;

        sw.addGenerateListener(new GenerateListener());
        cw.addButtonClientListener(new ButtonClientListener());
        pw.addButtonProductListener(new ButtonProductListener());
        ow.addButtonOrderListener(new ButtonOrderListener());
     //   ciw.addAdvancedBtnClientListener(new AdvancedBtnClientListener());


    }
    class ButtonOrderListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(ow.getOrderMenu().getItemAt(ow.getOrderMenu().getSelectedIndex()).equals("Afiseaza toate comenzile"))
                ow.displayTable();
            else if(ow.getOrderMenu().getItemAt(ow.getOrderMenu().getSelectedIndex()).equals("Creeaza comanda"))
            {
                oiw = new OrderInputView(1);
                oiw.setVisible(true);
            }
        }
    }
    class ButtonProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                if(pw.getProductMenu().getItemAt(pw.getProductMenu().getSelectedIndex()).equals("Afiseaza toate produsele"))
                    pw.displayTable();
                if(pw.getProductMenu().getItemAt(pw.getProductMenu().getSelectedIndex()).equals("Cauta dupa ID")) {
                    piw = new ProductInputView(0);
                    piw.setVisible(true);
                }
                if(pw.getProductMenu().getItemAt(pw.getProductMenu().getSelectedIndex()).equals("Modifica dupa ID")) {
                    piw = new ProductInputView(3);
                    piw.setVisible(true);
                }
                if(pw.getProductMenu().getItemAt(pw.getProductMenu().getSelectedIndex()).equals("Sterge dupa ID")) {
                    piw = new ProductInputView(2);
                    piw.setVisible(true);
                }
                if(pw.getProductMenu().getItemAt(pw.getProductMenu().getSelectedIndex()).equals("Creeaza produs"))
                {
                    piw = new ProductInputView(1);
                    piw.setVisible(true);
                }
            }
    }
    class ButtonClientListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(cw.getClientMenu().getItemAt(cw.getClientMenu().getSelectedIndex()).equals("Afiseaza toti clientii"))
                cw.displayTable();
            if(cw.getClientMenu().getItemAt(cw.getClientMenu().getSelectedIndex()).equals("Cauta dupa ID")) {
                ciw = new ClientInputView(0);
                ciw.setVisible(true);
            }
            if(cw.getClientMenu().getItemAt(cw.getClientMenu().getSelectedIndex()).equals("Modifica dupa ID")) {
                ciw = new ClientInputView(3);
                ciw.setVisible(true);
            }
            if(cw.getClientMenu().getItemAt(cw.getClientMenu().getSelectedIndex()).equals("Sterge dupa ID")) {
                ciw = new ClientInputView(2);
                ciw.setVisible(true);
            }
            if(cw.getClientMenu().getItemAt(cw.getClientMenu().getSelectedIndex()).equals("Creeaza client"))
            {
                ciw = new ClientInputView(1);
                ciw.setVisible(true);
            }
        }
    }
    class GenerateListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if(sw.getMenu().getItemAt(sw.getMenu().getSelectedIndex()).equals("Detalii clienti"))
                cw.setVisible(true);
            else if(sw.getMenu().getItemAt(sw.getMenu().getSelectedIndex()).equals("Detalii produse"))
                pw.setVisible(true);
            else ow.setVisible(true);

           // sw.setVisible(false);

        }
    }
    public static void main (String[] args) {
        StartView sw = null;
        try {
            sw = new StartView();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        ClientView cw = new ClientView();
        OrderView ow = new OrderView();
        ProductView pw = new ProductView();
        ClientInputView ciw = new ClientInputView();
        ProductInputView piw = new ProductInputView();
        OrderInputView oiw = new OrderInputView();
        Controller controller = new Controller(sw,cw,ow,pw,ciw,piw,oiw);
        sw.setVisible(true);



    }
}
