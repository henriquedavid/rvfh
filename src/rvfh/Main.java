package rvfh;

public class Main {
	public static void main(String[] args) {
			RV rv = new RV();
			
			rv.readFile();
			rv.generate();
			rv.print();
			
			System.out.println("V = "+rv.calculateTotalDemanda(4));
	}
}
