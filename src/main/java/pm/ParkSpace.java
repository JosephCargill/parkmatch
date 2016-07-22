package pm;

public class ParkSpace {

	private Integer id;
	private String loc;
	private Integer price;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setLocation(String loc) {
		this.loc = loc;
	}
	
	public String getLocation() {
		return loc;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getPrice() {
		return price;
	}
}
