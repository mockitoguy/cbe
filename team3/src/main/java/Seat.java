public class Seat {

    int number;
    int price;

    boolean isBooked = false;

    public Seat(int number, int price) {

        this.number = number;
        this.price = price;
    }

    public Seat book() {
        isBooked = true;
        return this;
    }

}
