package bll;

import dao.ProdusDAO;
import model.Produs;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import Exception.ExistElementException;

/**
 * Clasa unde apelam operatiile de findById, insert, delete, update, creareTabel pentru obiecte de tip Client
 * @Author: Frincu Ioan-Cristian
 * @Since: Apr 03, 2017
 */
public class ProdusBLL {
    private ProdusDAO produsDAO;

    public ProdusBLL(){
        produsDAO = new ProdusDAO();
    }

    /**
     * apeleaza findById pentru produs
     * @param id id--ul pe care il cautam
     * @return produsul gasit
     */
    public Produs findProdusById(int id){
        Produs produs = produsDAO.findById(id);
        if(produs == null){
            throw new NoSuchElementException("The prrodus with id = " + id + " was not found!");
        }
        return produs;
    }

    /**
     * apeleza findAll pentru produse
     * @return o lista de produse din tabele
     */
    public List<Produs> findAll() {
        return produsDAO.findAll();
    }

    /**
     * apeleza insert pentru un produs
     * @param produs produsul ce il inseram
     * @throws ExistElementException
     */
    public void insert(Produs produs) throws ExistElementException {
        produsDAO.insert(produs);
    }

    /**
     * apeleaza update pentru un produs
     * @param produs prosudul pentru care se face update
     * @param fields field-urile ce le modificam
     * @param value noile valori
     */
    public void update(Produs produs, ArrayList<String> fields, ArrayList<Object> value){
        produsDAO.update(produs, fields, value);
    }

    /**
     * apeleaza delete pentru un produs
     * @param id id-ul produsului ce il stergem
     */
    public void delete(int id) {
        produsDAO.delete(id);
    }

    /**
     * apeleaza creareTabel pentru produse
     * @throws IllegalAccessException
     */
    public void tabel() throws IllegalAccessException {
        produsDAO.createTable(new Produs());
    }
}
