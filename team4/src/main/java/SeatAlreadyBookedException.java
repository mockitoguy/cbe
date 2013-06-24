/**
* Created with IntelliJ IDEA.
* User: tomasz.klaudel
* Date: 6/24/13
* Time: 12:13 PM
* To change this template use File | Settings | File Templates.
*/
public class SeatAlreadyBookedException extends RuntimeException {
  public SeatAlreadyBookedException(String s) {
    super(s);
  }
}
