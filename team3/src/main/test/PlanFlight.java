
public class PlanFlight {
	private String flight;
	private String date;
	private String origin;
	private String destination;
	public PlanFlight(String flight, String date, String origin,
			String des) {
		this.flight = flight;
		this.date = date;
		this.origin = origin;
		this.destination = des;
	}
	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = flight;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}

}
