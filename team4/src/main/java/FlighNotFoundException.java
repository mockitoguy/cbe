/**
 * Created with IntelliJ IDEA.
 * User: rafal.myslek
 * Date: 31.01.2014
 * Time: 10:51
 * To change this template use File | Settings | File Templates.
 */
public class FlighNotFoundException extends Exception {
    public FlighNotFoundException(String message) {
        super(message);
    }

    public FlighNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlighNotFoundException(Throwable cause) {
        super(cause);
    }
}
