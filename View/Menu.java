import java.util.Scanner;

public class Menu {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		LocadoraDeVeiculos locadora = new LocadoraDeVeiculos();

		while (true) {
			menuPrincipal();
			int opcao = Integer.parseInt(sc.nextLine());

			switch (opcao) {
			case 1:
				menuPessoa();
				break;

			case 2:
				menuVeiculo();
				break;

			case 3:
				menuLocacao();
				break;

			case 9:
				System.out.println("Saindo do sistema");
				System.exit(0);

			default:
				System.out.println("Opção Inválida, tente novamente");
			}

		}

	}

	private static void menuLocacao() {
		while (true) {
			System.out.println("----Gerenciar Locação----");
			System.out.println("Por favor escolha uma opção:");
			System.out.println("1 - alugar");
			System.out.println("2 - buscar");
			System.out.println("3 - listar");
			System.out.println("4 - Retornar ao menu anterior");
			System.out.println("9 - sair");

			int opcao = Integer.parseInt(sc.nextLine());

			switch (opcao) {
			case 1:
				// alugarCarro
				break;
			case 2:
				// buscar locação
				break;
			case 3:
				// listar locaçao
				break;
			case 4:
				return;
			case 9:
				System.exit(0);
			}
		}
	}

	private static void menuVeiculo() {
		while (true) {
			System.out.println("----Gerenciar Veiculo----");
			System.out.println("Por favor escolha uma opção:");
			System.out.println("1 - cadastrar");
			System.out.println("2 - buscar");
			System.out.println("3 - alterar");
			System.out.println("4 - listar");
			System.out.println("5 - Retornar ao menu anterior");
			System.out.println("9 - sair");

			int opcao = Integer.parseInt(sc.nextLine());

			switch (opcao) {
			case 1:
				// cadastrar carro
				break;
			case 2:
				// buscar carro
				break;
			case 3:
				// alterar carro
				break;
			case 4: 
				// listar carros
				break;
			case 5:
				return;
			case 9:
				System.exit(0);
			}
		}
	}

	private static void menuPessoa() {
		System.out.println("----Gerenciar pessoa----");
		System.out.println("Por favor escolha uma opção:");
		System.out.println("1 - cadastrar");
		System.out.println("2 - buscar");
		System.out.println("3 - alterar");
		System.out.println("4 - listar");
		System.out.println("5 - Retornar ao menu anterior");
		System.out.println("9 - sair");
		
		int opcao = Integer.parseInt(sc.nextLine());
		
		switch (opcao) {
		case 1:
			// cadastrar pessoa
			break;
		case 2:
			// buscar pessoa
			break;
		case 3:
			// alterar pessoa
			break;
		case 4: 
			// listar pessoas
			break;
		case 5:
			return;
		case 9:
			System.exit(0);
		}
	}

	private static void menuPrincipal() {
		System.out.println("Bem Vindo, por favor escolha uma opção");
		System.out.println("Gerenciar pessoa - 1");
		System.out.println("Gerenciar veiculo - 2");
		System.out.println("Gerenciar locações - 3");
		System.out.println("Sair - 9");
	}
}
