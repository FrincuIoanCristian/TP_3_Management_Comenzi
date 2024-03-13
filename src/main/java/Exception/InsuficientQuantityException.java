package Exception;

/**
 * Clasa pentru exceptia in care vrem sa adaugam o comanda dar nu avem destule produse
 * @Author: Frincu Ioan-Cristian
 * @Since: Apr 03, 2017
 */
public class InsuficientQuantityException extends Exception{
    private static final long serialVersionUID = 1L;
    public InsuficientQuantityException () {}
    public InsuficientQuantityException(String msg)
    {         super(msg);     }
}
