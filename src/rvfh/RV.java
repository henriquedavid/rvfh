package rvfh;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
//		print();
	}
	
	public void generate() {
		boolean combineDone = true;
		while(combineDone) {
			combineDone = false;
			for(int i = 1; i < consumers.size(); i++) {
				for(int j = 1; j < consumers.size(); j++) {
					int v_demanda = calculateTotalDemanda(i) + calculateTotalDemanda(j);
					int veh_ = findMinVehicle(v_demanda, false);
					int v_vehicle = vehicles.get(veh_).getVolume();
					if(isEndPoint(i) && isEndPoint(j) 
							&& i != j && cs_saving(i, j) >= 0 && v_demanda < v_vehicle) {
						
						combineDone = true;
						changePositions(i, 0, 0);
						changePositions(i, j, 1);
					}
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
	
	public int calculateTotalDemanda(Integer partida) {

		int total = consumers.get(partida).getDemanda();
		int next = hasNext(partida);
		
		while(next != 0) {
			total += consumers.get(next).getDemanda();
			next = hasNext(next);
		}
		
		return total;
	}
	
	public int cw_saving(int i, int j) {
		int total = calculateDistanciaTotal(i) + calculateDistanciaTotal(j) - distance(consumers.get(i), consumers.get(j));
		return total;
	}
	
	public int cs_saving(int i, int j) {
		int sij = cw_saving(i,j) 
				+ findMinVehicle(calculateTotalDemanda(i), true) 
				+ findMinVehicle(calculateTotalDemanda(j), true)
				- findMinVehicle(calculateTotalDemanda(i) + calculateTotalDemanda(j), true);
		return sij;
	}
	
	public int findMinVehicle(int totalDemanda, boolean isFixedCust) {
		int menor = vehicles.size()-1;
		for(int i = 0; i < vehicles.size(); i++) {
			if(totalDemanda <= vehicles.get(i).getVolume()) {
				if(vehicles.get(i).getFixed_cust() < vehicles.get(menor).getFixed_cust()) {
					menor = i;
				}
			}
		}
		if(isFixedCust)
			return vehicles.get(menor).getFixed_cust();
		else
			return menor;
	}
	
	public int calculateDistanciaTotal(Integer partida) {

		int total = distance(consumers.get(0), consumers.get(partida));
		int next = hasNext(partida);
		
		while(next != 0) {
			total += distance(consumers.get(next), consumers.get(partida));
			partida = next;
			next = hasNext(next);
		}
		
		total += distance(consumers.get(partida), consumers.get(partida));
		
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
	
	public void writeFile() {
		try {
			FileWriter fw = new FileWriter("/home/henrique/Documents/ufrn/paa/file.txt");
			
			for(int i = 0; i < consumers.size(); i++) {
				fw.write("("+i+")");
				for(int j = 0 ; j < consumers.size(); j++) {
					fw.write(adjacency_matrix.get(i).get(j) + " ");
				}
				fw.write("\n");
			}
			
			for(int i = 0; i < consumers.size();i++) {
				if(adjacency_matrix.get(i).get(0) == 1)
					fw.write(calculateTotalDemanda(i) + "\n");
			}
			
			fw.close();
			System.out.println("Verifique o arquivo de saída.");
		} catch(IOException e) {
			System.out.println("Ocorreu um erro ao escrever o arquivo.");
			e.printStackTrace();
		}
	}
	

}
