package team1;

public interface Seat {

	int getNumber();
	double getPrice();
	boolean isAvailable();
	void setAvailable(boolean availability);
	SeatClass getClazz();
	
	enum SeatClass {
		ECONOMICAL, BUSINESS, FIRST;
	}
}
