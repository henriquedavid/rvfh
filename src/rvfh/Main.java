package rvfh;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
			RV rv = new RV();
			
//			Consumer c = new Consumer(0, 2, 2, 3);
//			Consumer c1 = new Consumer(1, 0, 3, 100);
//			Consumer c2 = new Consumer(2, 1, 0, 100);
//			
//			System.out.println(rv.distance(c, c2));
			Scanner s_ = new Scanner(System.in);
			System.out.println("Insira o caminho para o arquivo junto com o nome do arquivo: ");
			rv.readFile(s_.nextLine());
			rv.generate();
			rv.print();
			
			Scanner s_out = new Scanner(System.in);
			System.out.println("Insira o nome do arquivo de saida: ");
			rv.writeFile(s_out.nextLine());
	}
}
