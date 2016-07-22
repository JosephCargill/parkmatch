package pm;

public class ParkingSpace {

	private long id;
	private String location;
	private String price;
	
	public ParkingSpace(long id, String location, String price) {
		this.id = id;
		this.location = location;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return String.format(
			"ParkingSpace[id=%d, location='%s', price='%s']",
			id, location, price);
	}
	//needs getters and setters
}