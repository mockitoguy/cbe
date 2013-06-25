package pl.allegro.tdd;

public class SeatAlreadyBookedException extends Throwable {
  SeatAlreadyBookedException(String message) {
    super(message);
  }
}
