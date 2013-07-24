public class Seat {
    private String flight;
    private String seatSymbol;
    private double price;
    private boolean book;

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getSeatSymbol() {
        return seatSymbol;
    }

    public void setSeatSymbol(String seatSymbol) {
        this.seatSymbol = seatSymbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBook() {
        return book;
    }

    public void setBook(boolean book) {
        this.book = book;
    }

}
