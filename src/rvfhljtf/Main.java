package rvfhljtf;

import java.util.Calendar;
import java.util.Date;

public class Main {
	public static void main(String[] args) {
		Roteamento roteamento = new Roteamento();
		
		Estabelecimento origem = new Estabelecimento(29,  7);

		// Veiculos
		Carro c1 = new Carro(1);
		Carro c2 = new Carro(2);
		Moto m3 = new Moto(3);
		Moto m4 = new Moto(4);
		
		// Consumidores
		@SuppressWarnings("deprecation")
		Consumidor cc1 = new Consumidor(1,0, 50, 13, new Date(2021, 02, 15, 07, 00) , new Date(2021, 02, 15, 22, 00), origem);
		Consumidor cc2 = new Consumidor(2,8, 60, 18, new Date(2021, 02, 15, 07, 00) , new Date(2021, 02, 15, 10, 00), origem);
		Consumidor cc3 = new Consumidor(3,17, 72, 10, new Date(2021, 02, 15, 12, 30) , new Date(2021, 02, 15, 15, 00), origem);
		Consumidor cc4 = new Consumidor(4,39, 33, 1, new Date(2021, 02, 15, 10, 20) , new Date(2021, 02, 15, 10, 50), origem);
		Consumidor cc5 = new Consumidor(5,45, 21, 2, new Date(2021, 02, 15, 19, 00) , new Date(2021, 02, 15, 20, 00), origem);
		Consumidor cc6 = new Consumidor(6,30, 0, 3, new Date(2021, 02, 15, 13, 00) , new Date(2021, 02, 15, 15, 00), origem);
		Consumidor cc7 = new Consumidor(7,10, 10, 1, new Date(2021, 02, 15, 07, 00) , new Date(2021, 02, 15, 07, 30), origem);
		Consumidor cc8 = new Consumidor(8,0, 5, 9, new Date(2021, 02, 15, 11, 00) , new Date(2021, 02, 15, 14, 00), origem);
		
		roteamento.addVeiculo(c1);
		roteamento.addVeiculo(c2);
		roteamento.addVeiculo(m3);
		roteamento.addVeiculo(m4);
		
		roteamento.addConsumidor(cc1);
		roteamento.addConsumidor(cc2);
		roteamento.addConsumidor(cc3);
		roteamento.addConsumidor(cc4);
		roteamento.addConsumidor(cc5);
		roteamento.addConsumidor(cc6);
		roteamento.addConsumidor(cc7);
		roteamento.addConsumidor(cc8);
		
		// Quantos carros
		// Caracteristicas(Capacidade)
		// Peso das arestas
		
		roteamento.ordenarPorTempo();
		roteamento.ordenarVeiculosPorCapacidade();
		roteamento.distribuirRotas();
		
		System.out.println("---C1---");
		c1.mostrarRelatorio();
		System.out.println("---C2---");
		c2.mostrarRelatorio();
		System.out.println("---M3---");
		m3.mostrarRelatorio();
		System.out.println("---M4---");
		m4.mostrarRelatorio();
		
		
	}
}
