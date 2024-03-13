package model;

/**
 * Clasa model pentru Client ce contine aceleasi campuri ca in bd
 * @Author: Frincu Ioan-Cristian
 * @Since: Apr 03, 2017
 */
public class Client {
    private int id;
    private String nume;
    private String adresa;
    private String email;
    private int varsta;

    public Client() {
    }

    /**
     * Constructorul cu care initializam obiectele
     * @param id id-ul clientului
     * @param nume numele clientului
     * @param adresa adresa clientului
     * @param email email-ul clientului
     * @param varsta varsta clientului
     */
    public Client(int id, String nume, String adresa, String email, int varsta) {
//        super();
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
        this.email = email;
        this.varsta = varsta;
    }

    public Client(String nume, String adresa, String email, int varsta) {
//        super();
        this.nume = nume;
        this.adresa = adresa;
        this.email = email;
        this.varsta = varsta;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    /**
     * metoda pentru afisarea obiectului sub foma de string
     * @return string-ul obtinut
     */
    @Override
    public String toString() {
        return "Client [id=" + id + ", nume=" + nume + ", adresa=" + adresa + ", email=" + email + ", varsta=" + varsta + "]";
    }

}
