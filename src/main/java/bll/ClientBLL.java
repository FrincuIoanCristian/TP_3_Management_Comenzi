package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Exception.ExistElementException;
import dao.ClientDAO;
import model.Client;


/**
 * Clasa unde apelam operatiile de findById, insert, delete, update, creareTabel pentru obiecte de tip Client
 * @Author: Frincu Ioan-Cristian
 * @Since: Apr 03, 2017
 */
public class ClientBLL {
    private ClientDAO clientDAO;
    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    /**
     * Metoda ce apeleza metoda de cautare dupa id pentru client
     * @param id id-ul ce vrem sa il gasim
     * @return clientul gasit
     */
    public Client findClientById(int id) {
        Client client = clientDAO.findById(id);
        if (client == null) {
            throw new NoSuchElementException("The client with id = " + id + " was not found!");
        }
        return client;
    }

    /**
     * apeleaza metoda de findAll
     * @return  lista de clienti din tabela
     */
    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    /**
     * apeleza metoda de inserare
     * @param client    clientul ce vrem sa-l inseram
     * @throws ExistElementException
     */
    public void insert(Client client) throws ExistElementException {
        clientDAO.insert(client);
    }

    /**
     * apelaza metoda de delete
     * @param id id-ul dupa care vremsa stergem
     */
    public void delete(int id) {
        clientDAO.delete(id);
    }

    /**
     * apeleaza metoda de update
     * @param client clientul ce il modificam
     * @param fields campurile ce le modificam
     * @param value noile valori
     */
    public void update(Client client, ArrayList<String> fields, ArrayList<Object> value){
        clientDAO.update(client, fields, value);
    }

    /**
     * apeleza metoda de creareTabel pentru clienti
     * @throws IllegalAccessException
     */
    public void tabel() throws IllegalAccessException {
        clientDAO.createTable(new Client());
    }

}
