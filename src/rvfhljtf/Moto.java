package rvfhljtf;

import java.util.LinkedList;
import java.util.Queue;

public class Moto extends Veiculo{
	
	public Moto(int codigo) {
		super(codigo, 5, 0.25, 2, new LinkedList<Consumidor>());
	}

	public Moto(int codigo, double volume, double custo, double tipo, Queue<Consumidor> consumidores) {
		super(codigo, volume, custo, tipo, consumidores);
	}
	
}
