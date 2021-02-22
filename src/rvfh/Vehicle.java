package rvfh;

public class Vehicle {
	private int id;
	private int type;
	private int volume;
	private int fixed_cost;
	
	public Vehicle() {
		super();
	}
	public Vehicle(int id, int type, int volume, int fixed_cust) {
		super();
		this.id = id;
		this.type = type;
		this.volume = volume;
		this.fixed_cost = fixed_cust;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getFixed_cust() {
		return fixed_cost;
	}
	public void setFixed_cust(int fixed_cust) {
		this.fixed_cost = fixed_cust;
	}
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", type=" + type + ", volume=" + volume + ", fixed_cust=" + fixed_cost + "]";
	}
	
	
}
