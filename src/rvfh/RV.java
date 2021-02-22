package rvfh;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


public class RV {
	/**
	 * A Matriz de Adjacência possui valor -1 se não possuir rota e valor maior que 0 se possuir rota para
	 * outro consumidor de distância na posicao [x][y];
	 */
	private List<List<Integer>> adjacency_matrix;
	private List<Consumer> consumers;
	private List<Vehicle> vehicles;
	
	
	public RV() {
		super();
		this.adjacency_matrix = new ArrayList<List<Integer>>();
		this.consumers = new ArrayList<Consumer>();
		this.vehicles = new ArrayList<Vehicle>();
	}

	public Integer distance(Consumer c1, Consumer c2) {
		return (Math.abs(c1.getX() - c2.getX()) + Math.abs(c1.getY() - c2.getY()));
	}

	public void readFile() {
		try {
			File file_ = new File("/home/henrique/Documents/ufrn/paa/caso_20");
			Scanner reader_ = new Scanner(file_);
			
			int consumer = 0;
			int vehicles_ = 0;
			int step = 0;
			Consumer consumerObj = new Consumer();
			Vehicle vehicleObj = new Vehicle();
			while(reader_.hasNextInt()) {
				if(consumer == 0) {
					consumer = reader_.nextInt();
				} else {
					int v_ = reader_.nextInt();
					if(consumers.size() < consumer + 1) {
						switch (step) {
							case 0:
								consumerObj.setId(v_);
								step = 1;
								break;
							case 1:
								consumerObj.setX(v_);
								step = 2;
								break;
							case 2:
								consumerObj.setY(v_);
								step = 3;
								break;
							case 3:
								consumerObj.setDemanda(v_);
								consumers.add(consumerObj);
								consumerObj = new Consumer();
								step = 0;
							default:
								break;
						}
					} else {
						if(vehicles_ == 0) {
							vehicles_ = v_;
						} else {
							switch (step) {
								case 0:
									vehicleObj.setType(v_);
									step = 1;
									break;
								case 1:
									vehicleObj.setVolume(v_);
									step = 2;
									break;
								case 2:
									vehicleObj.setFixed_cust(v_);
									vehicles.add(vehicleObj);
									vehicleObj = new Vehicle();
									step = 0;
									break;
								default:
									break;
							}
						}
					}
				}	
			}
			reader_.close();
			inicializarMatrixAdjacencia();
		} catch(FileNotFoundException e) {
			System.out.println("Error in read file.");
			e.printStackTrace();
		}
	}
	
	public void inicializarMatrixAdjacencia() {
		for(int i = 0; i < consumers.size(); i++) {
			// Gerar o grafo direcional
			boolean nzero = false;
			List<Integer> l_ = new ArrayList<Integer>();
			for(int j = 0 ; j < consumers.size(); j++) {
				//Todos os nós começam do nó 0
				if(i == 0 && j != 0) {
					l_.add(1);
				} else if(i == 0 && j == 0) {
					l_.add(0);
				} else if(j == 0 && i != 0){
					boolean possivel = false;
					for(int v = 0; v < vehicles.size(); v++) {
						if(vehicles.get(v).getVolume() >= consumers.get(i).getDemanda()) {
							possivel = true;
						}
					}
					if(possivel)
						l_.add(1);
					else {
						l_.add(0);
					}
				} else {
					l_.add(0);
				}
				//				if(i != j) {
//					if(nzero)
//						l_.add(distance(consumers.get(i), consumers.get(j)));
//					else {
//						l_.add(0);
//					}
//				} else {
//					l_.add(0);
//					nzero = true;
//				}
//					}
			}
			adjacency_matrix.add(l_);
		}
//		print();
	}
	
	public int demanda1to1(Integer start, Integer end) {
			if(adjacency_matrix.get(start).get(end) == 1) {
				return consumers.get(start).getDemanda() + consumers.get(end).getDemanda();
			} else {
				return 0;
			}
	}
	
	public void changePositions(int c, int a, int v) {
		List<Integer> c_ = adjacency_matrix.get(c);
		c_.set(a, v);
		adjacency_matrix.set(c, c_);
		print();
	}
	
	public void generate() {
		boolean combineDone = true;
		int usingVehicle = 4;
		while(combineDone) {
			combineDone = false;
			for(int i = 1; i < consumers.size(); i++) {
				for(int j = 0; j < consumers.size(); j++) {
					int v_vehicle = vehicles.get(usingVehicle).getVolume();
					int v_demanda = calculateTotalDemanda(i) + calculateTotalDemanda(j);
					System.out.println(i+" - " +j);
					if(isEndPoint(i) && isEndPoint(j) 
							&& i != j && i != 0 &&
							v_vehicle > v_demanda) {
						System.out.println("ENTROU");
						changePositions(i, 0, 0);
						changePositions(i, j, 1);
						combineDone = true;
						i = 0;
						j = 0;
					}
					
					print();
				}
			}
		}
	}
	
	public boolean isUnique(int l) {
		int qnt = 0;
		for(int i = 0; i < consumers.size(); i++) {
			if(adjacency_matrix.get(i).get(l) == 1) {
				qnt++;
			}
		}
		
		return qnt < 2;
	}
	
	public void ordenarVeiculosPorCapacidade() {
		List<Vehicle> veiculos = vehicles;
		Queue<Vehicle> veiculos_= new LinkedList();
		
		while(!veiculos.isEmpty()) {
			Vehicle maiorCapacidade = veiculos.get(0);
			for(int i = 1; i < veiculos.size(); i++) {
				Vehicle c_ = veiculos.get(i);
				if(maiorCapacidade.getVolume() > c_.getVolume())
					maiorCapacidade = c_;
			}
			veiculos_.add(maiorCapacidade);
			veiculos.remove(maiorCapacidade);
		}
		
		for(Vehicle e: veiculos_) {
			veiculos.add(e);
			//System.out.println("BEGIN = " + e.getDisponivel() + " -> " + e.getDisponivel_ate() + " | T = " + (e.getDisponivel_ate().getTime() - e.getDisponivel().getTime()) + " | VOLUME = " + e.getVolume());
		}
	}
	
	public int calculateTotalDemanda(Integer partida) {

		int total = consumers.get(partida).getDemanda();
		int next = hasNext(partida);
		
		while(next != 0) {
			total += consumers.get(next).getDemanda();
			next = hasNext(next);
		}
		
		return total;
	}
	
	public int hasNext(int pos) {
		int next = 0;
		for(int i = 1; i < adjacency_matrix.size() && pos < adjacency_matrix.size(); ++i) {
			if(adjacency_matrix.get(i).get(pos) == 1) {
				return i;
			}
		}
		return next;
	}
	
	public boolean isEndPoint(int posicao) {
		return adjacency_matrix.get(posicao).get(0) == 1;
	}
	
	
	public void print() {
		System.out.println("");
		for(int i = 0; i < consumers.size(); i++) {
			System.out.print("("+i+")");
			for(int j = 0 ; j < consumers.size(); j++) {
				System.out.print(adjacency_matrix.get(i).get(j) + " ");
			}
			System.out.println();
		}
		
		for(int i = 0; i < consumers.size();i++) {
			if(adjacency_matrix.get(i).get(0) == 1)
				System.out.println(calculateTotalDemanda(i));
		}
		
		for(int i = 0; i < vehicles.size(); i++) {
			System.out.println(vehicles.get(i));
		}
		
	}
	
	
	

}
