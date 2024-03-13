package Exception;

/**
 * Clasa pentru exceptia in care introduce date gresite in view si vrem sa le folosim
 * @Author: Frincu Ioan-Cristian
 * @Since: Apr 03, 2017
 */
public class InvalidInputException extends Exception {

    private static final long serialVersionUID = 1L;
    public InvalidInputException () {}
    public InvalidInputException(String msg)
    {         super(msg);     }

}