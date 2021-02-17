package rvfhljtf;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Roteamento {

	List<Consumidor> consumidores;
	List<Veiculo> veiculos;
	
	public Roteamento() {
		this.consumidores = new ArrayList<Consumidor>();
		this.veiculos = new ArrayList<Veiculo>();
	}
	
	public void addConsumidor( Consumidor consumidor ) {
		consumidores.add(consumidor);
	}
	
	public void addVeiculo(Veiculo veiculo) {
		veiculos.add(veiculo);
	}
	
	public void ordenarPorTempo() {
		Queue<Consumidor> consumidores_= new LinkedList();
		
		while(!consumidores.isEmpty()) {
			Consumidor maisproximo = consumidores.get(0);
			for(int i = 1; i < consumidores.size(); i++) {
				Consumidor c_ = consumidores.get(i);
				if(maisproximo.getDisponivel().after(c_.getDisponivel())) {
					if(!maisproximo.getDisponivel_ate().after(c_.getDisponivel_ate())) {
						maisproximo = c_;
					}
				}
			}
			consumidores_.add(maisproximo);
			consumidores.remove(maisproximo);
		}
		
		for(Consumidor e: consumidores_) {
			consumidores.add(e);
			//System.out.println("BEGIN = " + e.getDisponivel() + " -> " + e.getDisponivel_ate() + " | T = " + (e.getDisponivel_ate().getTime() - e.getDisponivel().getTime()) + " | VOLUME = " + e.getVolume());
		}
	}
	
	public void ordenarVeiculosPorCapacidade() {
		Queue<Veiculo> veiculos_= new LinkedList();
		
		while(!veiculos.isEmpty()) {
			Veiculo maiorCapacidade = veiculos.get(0);
			for(int i = 1; i < veiculos.size(); i++) {
				Veiculo c_ = veiculos.get(i);
				if(maiorCapacidade.getVolume() > c_.getVolume())
					maiorCapacidade = c_;
			}
			veiculos_.add(maiorCapacidade);
			veiculos.remove(maiorCapacidade);
		}
		
		for(Veiculo e: veiculos_) {
			veiculos.add(e);
			//System.out.println("BEGIN = " + e.getDisponivel() + " -> " + e.getDisponivel_ate() + " | T = " + (e.getDisponivel_ate().getTime() - e.getDisponivel().getTime()) + " | VOLUME = " + e.getVolume());
		}
	}
	
	public void distribuirRotas() {
		List<Consumidor> lista = consumidores;
		List<Veiculo> veiculos_ = veiculos;
		// Percorre de consumidor em consumidor para atribuir a um veículo
		while(!lista.isEmpty()) {
			Consumidor c_ = lista.get(0);
			boolean adicionado = false;
			for(int j = 0 ; j < veiculos_.size(); j++) {
				if(!adicionado) {
					if(veiculos_.get(j).getVolume() > c_.getVolume()) {
						veiculos_.get(j).getClientes().add(c_);
						veiculos_.get(j).setVolume(veiculos_.get(j).getVolume()-c_.getVolume());
						System.out.println("CODIGO = " + c_.getCode());
						lista.remove(0);
						adicionado = true;
					}
				}
			}
			if(adicionado == false) {
				System.out.println("EM ESPERA");
			}
		}
	}	
	
	
}
