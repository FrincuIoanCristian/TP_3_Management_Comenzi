package bll;

import dao.FacturaDAO;
import dao.ProdusDAO;
import model.Factura;
import model.Produs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import Exception.ExistElementException;

/**
 * Clasa unde apelam operatiile de insert, creareTabel pentru obiecte de tip Factura
 * @Author: Frincu Ioan-Cristian
 * @Since: Apr 03, 2017
 */
public class FacturaBLL {
    private FacturaDAO facturaDAO;

    public FacturaBLL(){
        facturaDAO = new FacturaDAO();
    }

    public List<Factura> findAll() {
        return facturaDAO.findAll();
    }

    public void insert(Factura factura) throws ExistElementException {
        facturaDAO.insert(factura);
    }
    public void tabel() throws IllegalAccessException {
        facturaDAO.createTable(new Factura(1,"nume", "produs", 1,1,1));
    }

    /**
     * metoda ce creaza un fisier .txt ce reprezinta factura
     * @param factura factura pentru care se creaza fisierul
     */
    public void createFactura(Factura factura){
        File f = new File("Facturi/Factura_" + factura.id() + ".txt");
        try {
            FileWriter myWriter = new FileWriter(f);
            myWriter.write("Factura pentru " + factura.numeClient() + "\n");
            myWriter.write("----------------------------------\n");
            myWriter.write("Produs: " + factura.numeProdus() + "\n");
            myWriter.write("Pret: " + factura.pretProdus() + "\n");
            myWriter.write("Cantitate: " + factura.cantitate() + "\n");
            myWriter.write("----------------------------------\n");
            myWriter.write("Suma totala: " + factura.suma());
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
