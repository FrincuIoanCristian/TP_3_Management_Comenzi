package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.Toolkit.getDefaultToolkit;

/**
 * Clasa care creaza interfata grafica principala
 * @Author: Frincu Ioan-Cristian
 * @Since: Apr 03, 2017
 */
public class View extends JFrame {
    private JButton clienti;
    private JButton produse;
    private JButton comenzi;

    /**
     * Constructorul ce creaza interfata principala de unde selectam pe ce tabele vrem sa lucram
     */
    public View() {
        JLabel titlu  = new JLabel("Managamentul Comenzilor");
        titlu.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        titlu.setBounds(100, 40, 300, 50);

        this.clienti = new JButton("Client");
        clienti.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        clienti.setBounds(75, 140, 150, 70);

        this.comenzi = new JButton("Comenzi");
        comenzi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        comenzi.setBounds(175, 230, 150, 70);

        this.produse = new JButton("Produse");
        produse.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        produse.setBounds(275, 140, 150, 70);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.add(titlu);
        mainPanel.add(clienti);
        mainPanel.add(produse);
        mainPanel.add(comenzi);

        Dimension screenSize = getDefaultToolkit().getScreenSize();
        this.setContentPane(mainPanel);
        this.setTitle("Tabele");
        this.pack();
        this.setBounds(screenSize.width / 2 - 200, screenSize.height / 2 - 200, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * adauga actionListener pe butonul clienti
     * @param actionListener obiectul ActionListener
     */
    public void clientiButton(ActionListener actionListener) {
        this.clienti.addActionListener(actionListener);
    }

    /**
     * adauga actionListener pe butonul produse
     * @param actionListener obiectul ActionListener
     */
    public void produseButton(ActionListener actionListener) {
        this.produse.addActionListener(actionListener);
    }

    /**
     * adauga actionListener pe butonul comenzi
     * @param actionListener obiectul ActionListener
     */
    public void comenziButton(ActionListener actionListener) {
        this.comenzi.addActionListener(actionListener);
    }
}
