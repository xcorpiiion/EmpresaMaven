import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Loja;
import entities.Produtos;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nome, email;
		int tipyLogin = 0; // determina qual é o seu tipo de Login
		boolean isFinish = false;
		
		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);
		
		List<Produtos> produtos = new ArrayList<Produtos>();
		produtos.add(new Produtos("God of war", 150.00, 30));
		
		Loja loja = new Loja("Kratos games", "kratos.games@gmail.com", produtos);
		
		while(!isFinish) {
			
		}
	}

}
