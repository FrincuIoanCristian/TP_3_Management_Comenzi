package start;

import bll.FacturaBLL;
import model.Factura;
import presentation.Controller;
import presentation.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa principala unde creem interfata si controller-ul
 * @Author: Frincu Ioan-Cristian
 * @Since: Apr 03, 2017
 */
public class Main {
    public static void main(String[] args) {
        View view = new View();
        Controller c = new Controller(view);
    }

}