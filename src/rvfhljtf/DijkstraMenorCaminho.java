package rvfhljtf;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class DijkstraMenorCaminho {
	int dist[];
	Set<Integer> settled;
	PriorityQueue<Consumidor> pq;
	private int V;
	List<List<Consumidor>> adj;
	
	public DijkstraMenorCaminho(int V) {
		this.V = V;
	}
	
	public void dijsktra(List<List<Consumidor>> adj, int src) {
		this.adj = adj;
		
		for(int i = 0; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
	}

}
