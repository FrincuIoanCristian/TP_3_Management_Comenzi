package Exception;

/**
 * Clasa pentru exceptia in care incercam sa inseram un obiect, dar id-ul exista deja
 * @Author: Frincu Ioan-Cristian
 * @Since: Apr 03, 2017
 */
public class ExistElementException extends Exception {

    private static final long serialVersionUID = 1L;
    public ExistElementException () {}
    public ExistElementException(String msg)
    {         super(msg);     }

}