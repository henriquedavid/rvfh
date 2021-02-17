package rvfhljtf;

import java.util.Comparator;
import java.util.Date;

public class Consumidor implements Comparator<Consumidor>{
	
	private int code;
	private double latitude;
	private double longitude;
	private double distancia_ao_estab;
	private double volume;
	private Date disponivel;
	private Date disponivel_ate;
	
	public Consumidor(int code, double latitude, double longitude, double volume, Date disponivel, Date disponivel_ate, Estabelecimento estab) {
		super();
		this.code = code;
		this.latitude = latitude;
		this.longitude = longitude;
		this.volume = volume;
		this.disponivel = disponivel;
		this.disponivel_ate = disponivel_ate;
		calcularDistanciaEstab(estab);
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void calcularDistanciaEstab(Estabelecimento e) {
		this.distancia_ao_estab = Math.sqrt(Math.pow((latitude-e.latitude), 2) + Math.pow(longitude-e.longitude, 2));
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public Date getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(Date disponivel) {
		this.disponivel = disponivel;
	}
	public Date getDisponivel_ate() {
		return disponivel_ate;
	}
	public void setDisponivel_ate(Date disponivel_ate) {
		this.disponivel_ate = disponivel_ate;
	}
	
	public double getDistancia_ao_estab() {
		return distancia_ao_estab;
	}
	public void setDistancia_ao_estab(double distancia_ao_estab) {
		this.distancia_ao_estab = distancia_ao_estab;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public int compare(Consumidor c1, Consumidor c2) {
		if(c1.distancia_ao_estab < c2.distancia_ao_estab)
			return -1;
		else if(c1.distancia_ao_estab > c2.distancia_ao_estab)
			return 1;
		else
			return 0;
	}
	
	@Override
	public String toString() {
		return "Consumidor [code=" + code + ", latitude=" + latitude + ", longitude=" + longitude + ", volume=" + volume + ", disponivel="
				+ disponivel + ", disponivel_ate=" + disponivel_ate + "]";
	}
	

}
