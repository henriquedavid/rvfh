package rvfh;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
	private int id;
	private int type;
	private int volume;
	private int fixed_cost;
	private int qnt;
	private List<Integer> consumidores;
	
	public Vehicle() {
		super();
		this.consumidores = new ArrayList<Integer>();
	}
	public Vehicle(int id, int type, int volume, int fixed_cust) {
		super();
		this.id = id;
		this.type = type;
		this.volume = volume;
		this.fixed_cost = fixed_cust;
		this.qnt = 10;
		this.consumidores = new ArrayList<Integer>();
	}
	
	
	public Vehicle(int id, int type, int volume, int fixed_cost, int qnt) {
		super();
		this.id = id;
		this.type = type;
		this.volume = volume;
		this.fixed_cost = fixed_cost;
		this.qnt = qnt;
		this.consumidores =  new ArrayList<Integer>();
	}
	
	public int getQnt() {
		return qnt;
	}
	public void setQnt(int qnt) {
		this.qnt = qnt;
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
	
	public boolean possuiQntDisponivel(int i , int j) {
		int qnt__ = this.consumidores.size();
		for(int q = 0; q < consumidores.size(); q++) {
			if(consumidores.get(q) == i) {
				qnt__--;
			}
			if(consumidores.get(q) == j) {
				qnt__--;
			}
		}
		
		return qnt__ < qnt;
	}
	
	public void removeConsumidor(Integer partida) {
		List<Integer> l_ = new ArrayList<Integer>();
		for(int i = 0; i < consumidores.size(); i++) {
			if(consumidores.get(i) != partida)
				l_.add(consumidores.get(i));
		}
		this.consumidores = l_;
	}
	
	public void addConsumerToVehicle(Integer i) {
		this.consumidores.add(i);
	}
	
	public boolean possuiConsumidor(Integer partida) {
		for(int i = 0; i < consumidores.size(); i++) {
			if(consumidores.get(i) == partida)
				return true;
		}
		
		return false;
	}
	
	public List<Integer> getPontosdepartidas() {
		return consumidores;
	}
	public void setPontosdepartidas(List<Integer> pontosdepartidas) {
		this.consumidores = pontosdepartidas;
	}
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", type=" + type + ", volume=" + volume + ", fixed_cust=" + fixed_cost + ", qnt=" + (qnt-consumidores.size()) + "]";
	}
	
	
}
