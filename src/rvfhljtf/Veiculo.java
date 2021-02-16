package rvfhljtf;

import java.util.Queue;

public class Veiculo {

	private double volume;
	private double custo;
	int codigo;
	private double tipo;
	private Queue<Consumidor> clientes;
	
	public Veiculo(int codigo, double volume, double custo, double tipo, Queue<Consumidor> clientes) {
		super();
		this.codigo = codigo;
		this.volume = volume;
		this.custo = custo;
		this.tipo = tipo;
		this.clientes = clientes;
	}
	
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public double getCusto() {
		return custo;
	}
	public void setCusto(double custo) {
		this.custo = custo;
	}
	public double getTipo() {
		return tipo;
	}
	public void setTipo(double tipo) {
		this.tipo = tipo;
	}
	
	
	public Queue<Consumidor> getClientes() {
		return clientes;
	}

	public void setClientes(Queue<Consumidor> clientes) {
		this.clientes = clientes;
	}
	
	
	
	public void mostrarRelatorio() {
		Queue<Consumidor> q = clientes;
		for(Consumidor c: q) {
			System.out.println("["+c.getCode()+"] - " + c.toString());
		}
	}

	@Override
	public String toString() {
		return "Veiculo [volume=" + volume + ", custo=" + custo + ", tipo=" + tipo + "]";
	}
	
	
	
	
	
	
	
}
