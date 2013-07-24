public class Seat {

    private int number;
    private int price;
    private boolean isBooked;
    // TODO Seat array
    Seat[] seats;

    public Seat(int number, int price, boolean isBooked) {
        this.number = number;
        this.price = price;
        this.isBooked = isBooked;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public int getNumber() {
        return number;
    }

    public void bookSeat(int number) {
        isBooked = true;
    }

}
