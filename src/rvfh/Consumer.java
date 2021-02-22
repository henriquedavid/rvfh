package rvfh;

public class Consumer {
	private int id;
	private int x;
	private int y;
	private int demanda;
	
	public Consumer() {
		super();
	}
	public Consumer(int id, int x, int y, int demanda) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.demanda = demanda;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDemanda() {
		return demanda;
	}
	public void setDemanda(int demanda) {
		this.demanda = demanda;
	}
	@Override
	public String toString() {
		return "Consumer [id=" + id + ", x=" + x + ", y=" + y + ", demanda=" + demanda + "]";
	}
	
	
}
