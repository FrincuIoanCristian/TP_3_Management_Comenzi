package model;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Produs {
    private int id;
    private String nume;
    private int pret;
    private int cantitate;

    public Produs() {
    }

    /**
     * Constructorul cu care initializam obiectele
     * @param id id-ul produsului
     * @param nume numele produsului
     * @param pret pretul produsului
     * @param cantitate cantitatea produsului
     */
    public Produs(int id, String nume, int pret, int cantitate) {
        super();
        this.id = id;
        this.nume = nume;
        this.pret = pret;
        this.cantitate = cantitate;
    }

    public Produs(String nume, int pret, int cantitate) {
        super();
        this.nume = nume;
        this.pret = pret;
        this.cantitate = cantitate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
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
        return "Produs [idProdus=" + id + ", nume='" + nume + ", pret=" + pret + ", cantitate=" + cantitate + "]";
    }
}
