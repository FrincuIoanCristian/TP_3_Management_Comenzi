package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * Clasa care creaza interfata grafica pentru a gestiona tabele clientilor
 * @Author: Frincu Ioan-Cristian
 * @Since: Apr 03, 2017
 */
public class ClientView extends JFrame{
    private JTextField idTF;
    private JTextField numeTF;
    private JTextField adresaTF;
    private JTextField emailTF;
    private JTextField varstaTF;

    private JButton inapoi;
    private JButton adauga;
    private JButton delete;
    private JButton edit;
    private JButton viewAll;

    /**
     * Constructorul care creaza interfata
     */
    public ClientView(){
        Color color = null;
        JLabel titlu = new JLabel("CLIENT");
        titlu.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));

        this.idTF = new JTextField();
        idTF.setPreferredSize(new Dimension(200, 30));
        this.numeTF = new JTextField();
        numeTF.setPreferredSize(new Dimension(200, 30));
        this.adresaTF = new JTextField();
        adresaTF.setPreferredSize(new Dimension(200, 30));
        this.emailTF = new JTextField();
        emailTF.setPreferredSize(new Dimension(200, 30));
        this.varstaTF = new JTextField();
        varstaTF.setPreferredSize(new Dimension(200, 30));

        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
        idLabel.setPreferredSize(new Dimension(200, 20));
        JLabel numeLabel = new JLabel("Nume:");
        numeLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
        numeLabel.setPreferredSize(new Dimension(200, 20));
        JLabel adresaLabel = new JLabel("Adresa:");
        adresaLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
        adresaLabel.setPreferredSize(new Dimension(200, 20));
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
        emailLabel.setPreferredSize(new Dimension(200, 20));
        JLabel varstaLabel = new JLabel("Varsta:");
        varstaLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
        varstaLabel.setPreferredSize(new Dimension(200, 20));


        JPanel p1 = new JPanel();
        p1.setBackground(color);
        p1.setLayout(new GridLayout(5,2));
        p1.add(idLabel);
        p1.add(idTF);
        p1.add(numeLabel);
        p1.add(numeTF);
        p1.add(adresaLabel);
        p1.add(adresaTF);
        p1.add(emailLabel);
        p1.add(emailTF);
        p1.add(varstaLabel);
        p1.add(varstaTF);

        inapoi = new JButton("Inapoi");
        adauga = new JButton("Add");
        delete = new JButton("Delete");
        edit = new JButton("Edit");
        viewAll = new JButton("View All");

        JPanel p2 = new JPanel();
        p2.setBackground(color);
        p2.setLayout(new FlowLayout());
        p2.add(inapoi);
        p2.add(adauga);
        p2.add(delete);
        p2.add(edit);
        p2.add(viewAll);


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

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(color);
        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        mainPanel.add(p3);
        mainPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        Dimension screenSize = getDefaultToolkit().getScreenSize();
        this.setContentPane(mainPanel);
        this.setTitle("Clienti");
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
     * metoda adauga actionListener pe butonul edit
     * @param actionListener obiectul ActionListener
     */
    public void editButton(ActionListener actionListener){
        this.edit.addActionListener(actionListener);
    }

    /**
     * metoda adauga actionListener pe butonul viewAll
     * @param actionListener obiectul ActionListener
     */
    public void viewAllButton(ActionListener actionListener){
        this.viewAll.addActionListener(actionListener);
    }

    /**
     * metoda ce extrage string-ul din idTF
     * @return string-ul din TextField
     */
    public String getIdTF() {
        return idTF.getText();
    }

    /**
     * metoda ce extrage string-ul din numeTF
     * @return string-ul din TextField
     */
    public String getNumeTF() {
        return numeTF.getText();
    }

    /**
     * metoda ce extrage string-ul din adresaTF
     * @return string-ul din TextField
     */
    public String getAdresaTF() {
        return adresaTF.getText();
    }

    /**
     * metoda ce extrage string-ul din emailTF
     * @return string-ul din TextField
     */
    public String getEmailTF() {
        return emailTF.getText();
    }

    /**
     * metoda ce extrage string-ul din varstaTF
     * @return string-ul din TextField
     */
    public String getVarstaTF() {
        return varstaTF.getText();
    }

    /**
     * afiseaza un mesaj pe ecran
     * @param message mesajul ce il afiseaza
     */
    public void showMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}
