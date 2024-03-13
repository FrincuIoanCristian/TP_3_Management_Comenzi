package bll;

import dao.ComandaDAO;
import model.Comanda;

import java.util.List;
import java.util.NoSuchElementException;
import Exception.ExistElementException;

/**
 * Clasa unde apelam operatiile de findById, insert, delete, update, creareTabel pentru obiecte Comanda
 * @Author: Frincu Ioan-Cristian
 * @Since: Apr 03, 2017
 */
public class ComandaBLL {
    private ComandaDAO comandaDAO;

    public ComandaBLL(){
        comandaDAO = new ComandaDAO();
    }

    /**
     * apeleza findById pentru comanda
     * @param id id-ul dupa care cauta
     * @return comanda gasita
     */
    public Comanda findComandaById(int id){
        Comanda order = comandaDAO.findById(id);
        if(order == null){
            throw new NoSuchElementException("Order-ul cu id-ul =\" + id + \" nu exista!");
        }
        return order;
    }

    /**
     * apeleaza findAll pentru comanda
     * @return  lista de comenzi
     */
    public List<Comanda> findAll() {
        return comandaDAO.findAll();
    }

    /**
     * apeleza insert pentru comanda
     * @param comanda comanda ce o inseram
     * @throws ExistElementException
     */
    public void insert(Comanda comanda) throws  ExistElementException {
        comandaDAO.insert(comanda);
    }

    /**
     * apelam delete pentru Comanda
     * @param id id-ul dupa care stergem
     */
    public void delete(int id) {
        comandaDAO.delete(id);
    }

    /**
     * apelam creareTabel pentru comenzi
     * @throws IllegalAccessException
     */
    public void tabel() throws IllegalAccessException {
        comandaDAO.createTable(new Comanda());
    }
}
