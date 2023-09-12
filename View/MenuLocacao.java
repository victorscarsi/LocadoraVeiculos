import java.util.InputMismatchException;
import java.util.List;

public class MenuLocacao {

	public static void menuLocacao() {
		while (true) {
			System.out.println("----Gerenciar Locação----");
			System.out.println("Por favor escolha uma opção:");
			System.out.println("1 - alugar");
			System.out.println("2 - buscar");
			System.out.println("3 - listar");
			System.out.println("4 - Retornar ao menu anterior");
			System.out.println("9 - sair");

			int opcao = Integer.parseInt(Menu.sc.nextLine());

			switch (opcao) {
			case 1:
				// alugar (falta fazer)
				break;
			case 2:
				buscarPeloID();
				break;
			case 3:
				listarLocacoes();
				break;
			case 4:
				return;
			case 9:
				Menu.sc.close();
				System.exit(0);

			default:
				System.out.println("Opção inválida, digite novamente:");
				break;
			}
		}
	}

	private static void listarLocacoes() {
		List<Locacao> locacoes = Menu.locadora.listar();

		if (locacoes == null || locacoes.isEmpty()) {
			System.out.println("Nenhuma locação encontrada.");
			Menu.sc.nextLine();
		} else {
			System.out.println(locacoes);
		}
	}

	private static void buscarPeloID() {
		try {
			System.out.print("Digite o ID da locação:");
			Long idLocacao = Menu.sc.nextLong();
			Menu.sc.nextLine(); 

			Locacao locacao = Menu.locadora.buscarLocacao(idLocacao);

			if (locacao == null) {
				throw new NullPointerException("Nenhuma locação encontrada com o ID fornecido.");
			}

			System.out.println("Informações da locação:\n" + locacao);

		} catch (InputMismatchException e) {
			System.out.println("Por favor, insira um número válido para o ID da locação.");
			Menu.sc.nextLine(); 
		} catch (NullPointerException e) {
			System.out.println("Ocorreu um erro ao buscar a locação: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
		}
	}
}
