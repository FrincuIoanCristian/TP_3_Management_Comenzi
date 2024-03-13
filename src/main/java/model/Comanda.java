package model;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Comanda {
    private int id;
    private String numeClient;
    private String numeProdus;
    private int cantitate;

    public Comanda(){
    }

    /**
     * Constructorul cu care initializam obiectele
     * @param id id-ul comenzii
     * @param numeClient numele clientului
     * @param numeProdus numele produsului
     * @param cantitate cantitatea de produse
     */
    public Comanda(int id, String numeClient, String numeProdus, int cantitate){
        this.id= id;
        this.numeClient = numeClient;
        this.numeProdus = numeProdus;
        this.cantitate = cantitate;
    }

    public Comanda(String numeClient, String numeProdus, int cantitate){
        this.numeClient = numeClient;
        this.numeProdus = numeProdus;
        this.cantitate = cantitate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }


    public String getNumeProdus() {
        return numeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    /**
     * metoda pentru afisarea obiectului sub foma de string
     * @return string-ul obtinut
     */
    @Override
    public String toString() {
        return "Comanda [" + "id=" + id + ", numeClient='" + numeClient + ", numeProdus='" + numeProdus + ", cantitate=" + cantitate + "]";
    }
}

