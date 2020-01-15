import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Funcionario;
import entities.Loja;
import entities.Produtos;
import enums.Cargo;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nome = "", email = "";
		int typeLogin = 0; // determina qual é o seu tipo de Login
		boolean isFinish = false;
		SimpleDateFormat dataNascimento = new SimpleDateFormat("dd/MM/yyyy");

		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);

		List<Produtos> produtos = new ArrayList<Produtos>();
		produtos.add(new Produtos("God of war", 150.00, 30));

		// cadastra um funcionario do RH para poder contratar novos funcionarios
		try {
			dataNascimento.parse("20/01/1997");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Funcionario funcionario = new Funcionario("Jessie", "jessie@gmail.com", 2501.00, Cargo.RH, dataNascimento);

		// cria uma loja
		Loja loja = new Loja("Kratos games", "kratos.games@gmail.com", produtos);
		try {
			loja.contratarFuncionario(funcionario.getNome(), funcionario.getEmail(), funcionario.getCargo(),
					funcionario.getSalario(), funcionario.getDataNascimento());
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (!isFinish) {
			typeLogin = verificarValidade("Você é funcionario ou cliente?", "1 - para cliente / 2 - para funcionario: "
					, scanner, typeLogin);
			// Verifica o número digitado
			switch (typeLogin) {
			case 1:
				int cadastro = 0; 
				cadastro = verificarValidade("Você possui cadastro?", "1 - para sim / 2 - para não: "
						, scanner, cadastro);
				
				
				break;
			case 2:
				
				break;
			default:
				
				break;
			}

		}

		scanner.close();
	}
	
	// Vai verificar se o numero que eu digitei é valido e vai alterar as mensagens do print
	private static int verificarValidade(String msg, String msg2, Scanner scanner, int numeroVerificador) {
		while (numeroVerificador == 0) {
			try {
				System.out.println(msg);
				System.out.print(msg2);
				numeroVerificador = scanner.nextInt();
				if(numeroVerificador < 1 || numeroVerificador > 2) {
					System.out.println("Informe um número valido");
					numeroVerificador = 0;
				} 
			} catch (NumberFormatException e) {
				System.out.println("Você informou um digito invalido " + e.getMessage());
			}
		}
		return numeroVerificador;
	}

}
