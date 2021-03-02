package rvfh;

public class Main {
	public static void main(String[] args) {
			RV rv = new RV();
			
//			Consumer c = new Consumer(0, 2, 2, 3);
//			Consumer c1 = new Consumer(1, 0, 3, 100);
//			Consumer c2 = new Consumer(2, 1, 0, 100);
//			
//			System.out.println(rv.distance(c, c2));
//			
			rv.readFile();
			rv.generate();
			rv.print();
//			
//			System.out.println("V = "+rv.calculateTotalDemanda(4));
//			rv.writeFile();
	}
}
