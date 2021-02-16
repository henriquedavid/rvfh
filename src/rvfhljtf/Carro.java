package rvfhljtf;

import java.util.LinkedList;
import java.util.Queue;

public class Carro extends Veiculo{
	
	public Carro(int codigo) {
		super(codigo, 50, 0.5, 1, new LinkedList<Consumidor>());
	}

	public Carro(int codigo, double volume, double custo, double tipo, Queue<Consumidor> consumidores) {
		super(codigo, volume, custo, tipo, consumidores);
	}
	
	

}
