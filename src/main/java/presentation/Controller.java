package presentation;

import bll.ClientBLL;
import bll.ComandaBLL;
import bll.FacturaBLL;
import bll.ProdusBLL;
import model.Client;
import Exception.InvalidInputException;
import model.Comanda;
import model.Factura;
import model.Produs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import Exception.ExistElementException;
import Exception.InsuficientQuantityException;

/**
 * Clasa unde legam toate butoanele interfetei
 * @Author: Frincu Ioan-Cristian
 * @Since: Apr 03, 2017
 */
public class Controller {
    private ClientBLL clientBLL;
    private ProdusBLL produsBLL;
    private ComandaBLL comandaBLL;
    private FacturaBLL facturaBLL;
    private ClientView clientView;
    private ProdusView produsView;
    private ComandaView comandaView;
    private View view;

    /**
     * Constructorul in care creem obiectele necesare si apelam metodele pentru toate butoanele
     * @param view un obiect de tipul interfetei principale
     */
    public Controller(View view){
        this.view = view;
        this.clientView = new ClientView();
        this.produsView = new ProdusView();
        this.comandaView = new ComandaView();
        this.clientBLL = new ClientBLL();
        this.comandaBLL = new ComandaBLL();
        this.produsBLL = new ProdusBLL();
        this.facturaBLL = new FacturaBLL();

        view.clientiButton(new ClientListener());
        view.produseButton(new ProdusListener());
        view.comenziButton(new ComandaListener());

        clientView.inapoiButton(new ClientInapoiListener());
        clientView.viewAllButton(new ClientViewAllListener());
        clientView.addButton(new ClientAddListener());
        clientView.deleteButton(new ClientDeleteListener());
        clientView.editButton(new ClientUpdateListener());
        produsView.inapoiButton(new ProdusInapoiListener());
        produsView.viewAllButton(new ProdusViewAllListener());
        produsView.addButton(new ProsudAddListener());
        produsView.deleteButton(new ProdusDeleteListener());
        produsView.editButton(new ProdusUpdateListener());
        comandaView.inapoiButton(new ComandaInapoiListener());
        comandaView.viewAllButton(new ComandaViewAllListener());
        comandaView.addButton(new ComandaAddListener());
        comandaView.deleteButton(new ComandaDeleteListener());
    }
    ///////////////////////////////////////////CLIENT//////////////////////////////////////////////////////

    /**
     * Clasa in care creem interfata pentru clienti
     */
    class ClientListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            clientView.setVisible(true);
        }
    }

    /**
     * Clasa care ne trimite inapoi la pagina principala
     */
    class ClientInapoiListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            clientView.dispose();
            view.setVisible(true);
        }
    }

    /**
     * Clasa pentru a adauga un client
     */
    class ClientAddListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String string = new String("");
            try{
                int id = Integer.parseInt(clientView.getIdTF());
                String nume = clientView.getNumeTF();
                if(nume.equals("")){
                    string = "Introduceti numele";
                    throw new InvalidInputException();
                }
                String adresa = clientView.getAdresaTF();
                String email = clientView.getEmailTF();
                int varsta = Integer.parseInt(clientView.getVarstaTF());
                if(varsta < 0){
                    string = "Varsta incorecta";
                    throw new InvalidInputException();
                }
                clientBLL.insert(new Client(id, nume, adresa, email, varsta));
                clientView.showMessage("Client adaugat!");
            }catch (NumberFormatException ex){
                clientView.showMessage("Eroare");
            }catch (InvalidInputException ex2){
                clientView.showMessage(string);
            } catch (ExistElementException ex) {
                clientView.showMessage("Exista clientul cu id-ul asta");
            }
        }
    }

    /**
     * Clasa pentru a sterge un client
     */
    class ClientDeleteListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int id;
            try{
                id = Integer.parseInt(clientView.getIdTF());
                clientBLL.findClientById(id);
                clientBLL.delete(id);
                clientView.showMessage("Client sters!");
            }catch (NumberFormatException ex){
                clientView.showMessage("Eroare");
            }catch (NoSuchElementException | IndexOutOfBoundsException ex){
                clientView.showMessage("Nu exista clientul cu acest id");
            }
        }
    }

    /**
     * Clasa pentru a edita un client
     */
    class ClientUpdateListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> field = new ArrayList<>();
            ArrayList<Object> value = new ArrayList<>();
            Client client;
            try{
                int id;
                String nume, adresa, email, varstaString;
                String idString = clientView.getIdTF();
                if(idString.equals("")){
                    throw new InvalidInputException();
                }else{
                    id = Integer.parseInt(idString);
                    client = clientBLL.findClientById(id);
                }
                nume = clientView.getNumeTF();
                if(!nume.equals("")){
                    field.add("nume");value.add(nume);
                }
                adresa = clientView.getAdresaTF();
                if(!adresa.equals("")){
                    field.add("adresa");value.add(adresa);
                }
                email = clientView.getEmailTF();
                if(!email.equals("")){
                    field.add("email");value.add(email);
                }
                varstaString = clientView.getVarstaTF();
                if(!varstaString.equals("")){
                    field.add("varsta");value.add(Integer.parseInt(varstaString));
                }
                value.add(client.getId());
                if(field.size() == 0){
                    clientView.showMessage("Nu s-au introdus date pentru a edita!");
                    return;
                }
                clientBLL.update(client, field, value);
                clientView.showMessage("Update reusit");
            }catch (NumberFormatException ex1){
                clientView.showMessage("Eroare");
            }catch (InvalidInputException ex2){
                clientView.showMessage("Introduceti id-ul!");
            }catch (NoSuchElementException | IndexOutOfBoundsException ex){
                clientView.showMessage("Nu exista clientul cu acest id");
            }
        }
    }

    /**
     * Clasa pentru a vizualiza toti clienti
     */
    class ClientViewAllListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                clientBLL.tabel();
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    ///////////////////////////////////////////PRODUS//////////////////////////////////////////////////////

    /**
     * Clasa in care creem interfata pentru produse
     */
    class ProdusListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            produsView.setVisible(true);
        }
    }

    /**
     * Clasa care ne trimite inapoi la pagina principala
     */
    class ProdusInapoiListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            produsView.dispose();
            view.setVisible(true);
        }
    }

    /**
     * Clasa pentru a adauga un produs
     */
    class ProsudAddListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String string = new String("");
            try{
                int id = Integer.parseInt(produsView.getIdTF());
                String nume = produsView.getNumeTF();
                if(nume.equals("")){
                    string = "Introduceti numele produsului!";
                    throw new InvalidInputException();
                }
                int pret = Integer.parseInt(produsView.getPretTF());
                int cantitate = Integer.parseInt(produsView.getCantitateTF());
                if(pret < 0){
                    string = "Pret incorect!";
                    throw new InvalidInputException();
                }
                if(cantitate < 0){
                    string = "Cantitate incorecta!";
                    throw new InvalidInputException();
                }
                produsBLL.insert(new Produs(id, nume, pret, cantitate));

                produsView.showMessage("Produs adaugat!");
            }catch (NumberFormatException ex){
                produsView.showMessage("Eroare");
            }catch (InvalidInputException ex2){
                produsView.showMessage(string);
            } catch (ExistElementException ex) {
                produsView.showMessage("Exisat produsul cu id-ul asta");
            }
        }
    }

    /**
     * Clasa pentru a sterge un produs
     */
    class ProdusDeleteListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                int id = Integer.parseInt(produsView.getIdTF());
                produsBLL.findProdusById(id);
                produsBLL.delete(id);
                produsView.showMessage("produs sters!");
            }catch (NumberFormatException ex){
                produsView.showMessage("Eroare");
            }catch (NoSuchElementException | IndexOutOfBoundsException ex){
                produsView.showMessage("Nu exista produsul cu acest id");
            }
        }
    }

    /**
     * Clasa pentru a edita un produs
     */
    class ProdusUpdateListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> field = new ArrayList<>();
            ArrayList<Object> value = new ArrayList<>();
            Produs produs;
            try{
                int id;
                String nume, pret, cantitate;
                String idString = produsView.getIdTF();
                if(idString.equals("")){
                    throw new InvalidInputException();
                }else{
                    id = Integer.parseInt(idString);
                    produs = produsBLL.findProdusById(id);
                }
                nume = produsView.getNumeTF();
                if(!nume.equals("")){
                    field.add("nume");value.add(nume);
                }
                pret = produsView.getPretTF();
                if(!pret.equals("")){
                    field.add("pret");value.add(Integer.parseInt(pret));
                }
                cantitate = produsView.getCantitateTF();
                if(!cantitate.equals("")){
                    field.add("cantitate"); value.add(Integer.parseInt(cantitate));
                }
                value.add(produs.getId());
                if(field.size() == 0){
                    produsView.showMessage("Nu s-au introdus date pentru a edita!");
                    return;
                }
                produsBLL.update(produs, field, value);
                produsView.showMessage("Update reusit");
            }catch (NumberFormatException ex1){
                produsView.showMessage("Eroare");
            }catch (InvalidInputException ex2){
                produsView.showMessage("Introduceti id-ul!");
            }catch (NoSuchElementException | IndexOutOfBoundsException ex){
                produsView.showMessage("Nu exista clientul cu acest id");
            }
        }
    }

    /**
     * Clasa pentru a vizualiza toate produsele
     */
    class ProdusViewAllListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                produsBLL.tabel();
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    ///////////////////////////////////////////COMANDA//////////////////////////////////////////////////////

    /**
     * Clasa in care creem interfata pentru comezi
     */
    class ComandaListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            comandaView.setVisible(true);
        }
    }

    /**
     * Clasa care ne trimite inapoi la pagina principala
     */
    class ComandaInapoiListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            comandaView.dispose();
            view.setVisible(true);
        }
    }

    /**
     * Clasa pentru a adauga o comanda
     */
    class ComandaAddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = new String("");
            try {
                int id = Integer.parseInt(comandaView.getIdTF());
                int idClient = Integer.parseInt(comandaView.getClientIdTF());
                s = "Nu exista clientul cu id-ul asta";
                Client client = clientBLL.findClientById(idClient);
                int idProdus = Integer.parseInt(comandaView.getProdusIdTF());
                s = "Nu exista produsul cu id-ul asta";
                Produs produs = produsBLL.findProdusById(idProdus);
                int cantitate = Integer.parseInt(comandaView.getCantitateTF());
                if(cantitate > produs.getCantitate()){
                    throw new InsuficientQuantityException();
                }
                Comanda comanda = new Comanda(id, client.getNume(), produs.getNume(), cantitate);
                comandaBLL.insert(comanda);
                ArrayList<String> field = new ArrayList<>();
                field.add("cantitate");
                ArrayList<Object> value = new ArrayList<>();
                value.add(produs.getCantitate() - cantitate);
                value.add(produs.getId());
                produsBLL.update(produs, field, value);
                int suma = cantitate * produs.getPret();
                Factura factura = new Factura(id, client.getNume(), produs.getNume(), cantitate, produs.getPret(), suma);
                facturaBLL.insert(factura);
                facturaBLL.createFactura(factura);
                comandaView.showMessage("Comanda plasata");
            }catch (InsuficientQuantityException ex1){
                comandaView.showMessage("Cantitate prea mare!");
            } catch (ExistElementException ex2) {
                comandaView.showMessage("Exista comanda cu id-ul asta");
            }catch (NoSuchElementException | IndexOutOfBoundsException ex3){
                comandaView.showMessage(s);
            }catch (NumberFormatException ex4){
                comandaView.showMessage("Eroare");
            }
        }
    }

    /**
     * Clasa pentru a sterge o comanda
     */
    class ComandaDeleteListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                int id = Integer.parseInt(comandaView.getIdTF());
                comandaBLL.findComandaById(id);
                comandaBLL.delete(id);
                comandaView.showMessage("Comanda stearsa!");
            }catch (NumberFormatException ex){
                comandaView.showMessage("Eroare");
            }catch (NoSuchElementException | IndexOutOfBoundsException ex){
                comandaView.showMessage("Nu exista comanda cu acest id");
            }
        }
    }

    /**
     * Clasa pentru a vizualiza toate datele din fiecare tabel, in functie de care este selectat
     */
    class ComandaViewAllListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                switch (comandaView.getComboBox()){
                    case "PERSOANE":
                    {
                        clientBLL.tabel();
                        break;
                    }
                    case "PRODUSE":
                    {
                        produsBLL.tabel();
                        break;
                    }
                    case "COMENZI":{
                        comandaBLL.tabel();
                        break;
                    }
                    case "FACTURI":{
                        facturaBLL.tabel();
                    }
                    default:{
                        break;
                    }
                }
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}