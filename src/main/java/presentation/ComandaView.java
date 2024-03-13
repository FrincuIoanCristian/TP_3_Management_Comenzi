package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * Clasa care creaza interfata grafica pentru a gestiona tabele comenzilor
 * @Author: Frincu Ioan-Cristian
 * @Since: Apr 03, 2017
 */
public class ComandaView extends JFrame{

    private JTextField idTF;
    private JTextField clientIdTF;
    private JTextField produsIdTF;
    private JTextField cantitateTF;

    private JButton inapoi;
    private JButton adauga;
    private JButton delete;
    private JButton viewAll;

    private JComboBox comboBox;

    /**
     * Constructorul care creaza interfata
     */
    public ComandaView(){
        Color color = null;
        JLabel titlu = new JLabel("COMANDA");
        titlu.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));

        this.idTF = new JTextField();
        idTF.setPreferredSize(new Dimension(200, 30));
        this.clientIdTF = new JTextField();
        clientIdTF.setPreferredSize(new Dimension(200, 30));
        this.produsIdTF = new JTextField();
        produsIdTF.setPreferredSize(new Dimension(200, 30));
        this.cantitateTF = new JTextField();
        cantitateTF.setPreferredSize(new Dimension(200, 30));

        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
        idLabel.setPreferredSize(new Dimension(200, 20));
        JLabel clientIDLabel = new JLabel("Client ID:");
        clientIDLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
        clientIDLabel.setPreferredSize(new Dimension(200, 20));
        JLabel produsIDLabel = new JLabel("Produs ID:");
        produsIDLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
        produsIDLabel.setPreferredSize(new Dimension(200, 20));
        JLabel cantitateLabel = new JLabel("Cantitate:");
        cantitateLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
        cantitateLabel.setPreferredSize(new Dimension(200, 20));


        JPanel p1 = new JPanel();
        p1.setBackground(color);
        p1.setLayout(new GridLayout(6,2));
        p1.add(idLabel);
        p1.add(idTF);
        p1.add(clientIDLabel);
        p1.add(clientIdTF);
        p1.add(produsIDLabel);
        p1.add(produsIdTF);
        p1.add(cantitateLabel);
        p1.add(cantitateTF);

        inapoi = new JButton("Inapoi");
        adauga = new JButton("Add");
        delete = new JButton("Delete");
        viewAll = new JButton("View All");

        JPanel p2 = new JPanel();
        p2.setBackground(color);
        p2.setLayout(new FlowLayout());
        p2.add(inapoi);
        p2.add(adauga);
        p2.add(delete);

        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        p4.add(viewAll);
        String[] tabele = new String[]{"PERSOANE", "PRODUSE", "COMENZI", "FACTURI"};
        comboBox = new JComboBox<String>(tabele);
        p4.add(comboBox);

        JPanel p3 = new JPanel();
        p3.setBackground(color);
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
        p3.add(Box.createRigidArea(new Dimension(0, 15)));
        p3.add(titlu);
        titlu.setAlignmentX(CENTER_ALIGNMENT);
        p3.add(Box.createRigidArea(new Dimension(0, 30)));
        p3.add(p1);
        p3.add(Box.createRigidArea(new Dimension(0, 30)));
        p3.add(p2);
        p3.add(p4);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(color);
        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        mainPanel.add(p3);
        mainPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        Dimension screenSize = getDefaultToolkit().getScreenSize();
        this.setContentPane(mainPanel);
        this.setTitle("Comanda");
        this.pack();
        this.setBounds(screenSize.width / 2 - 200, screenSize.height / 2 - 200, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * metoda adauga actionListener pe butonul inapoi
     * @param actionListener obiectul ActionListener
     */
    public void inapoiButton(ActionListener actionListener){
        this.inapoi.addActionListener(actionListener);
    }

    /**
     * metoda adauga actionListener pe butonul adauga
     * @param actionListener obiectul ActionListener
     */
    public void addButton(ActionListener actionListener){
        this.adauga.addActionListener(actionListener);
    }

    /**
     * metoda adauga actionListener pe butonul delete
     * @param actionListener obiectul ActionListener
     */
    public void deleteButton(ActionListener actionListener){
        this.delete.addActionListener(actionListener);
    }

    /**
     * metoda adauga actionListener pe butonul viewAll
     * @param actionListener obiectul ActionListener
     */
    public void viewAllButton(ActionListener actionListener){
        this.viewAll.addActionListener(actionListener);
    }

    /**
     * extrage din comboBox string-ul ce e selectat
     * @return returneaza string-ul
     */
    public String getComboBox() {
        return (String) this.comboBox.getSelectedItem();
    }

    /**
     * metoda ce extrage string-ul din idTF
     * @return string-ul din TextField
     */
    public String getIdTF() {
        return idTF.getText();
    }

    /**
     * metoda ce extrage string-ul din clientIdTF
     * @return string-ul din TextField
     */
    public String getClientIdTF() {
        return clientIdTF.getText();
    }

    /**
     * metoda ce extrage string-ul din produsIdTF
     * @return string-ul din TextField
     */
    public String getProdusIdTF() {
        return produsIdTF.getText();
    }

    /**
     * metoda ce extrage string-ul din cantitateTF
     * @return string-ul din TextField
     */
    public String getCantitateTF() {
        return cantitateTF.getText();
    }

    /**
     * afiseaza un mesaj pe ecran
     * @param message mesajul ce il afiseaza
     */
    public void showMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}
