package pl.codebyexample;

/**
 * Created with IntelliJ IDEA.
 * User: andrzej.wislowski
 * Date: 24.06.2013
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */
public class Seat {

    private int price;
    private boolean available = true;
    private String number;

    public Seat(String number, int price) {
        this.price = price;
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getNumber() {
        return number;
    }
}
