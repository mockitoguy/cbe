package pl.cbe;

/**
 * 
 * @author bartosz.walacik
 */
public class Seat {
	private boolean available;
	private int no;
	private double price;
	
	public Seat(int no, double price) {
		super();
		this.available = true;
		this.no = no;
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public int getSeatNo() {
		return no;
	}

	public void book() {
		if (!available) {
			throw new RuntimeException("seat already booked");
		}
		available = false;
		
	}
}
