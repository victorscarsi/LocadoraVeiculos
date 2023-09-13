import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuLocacao {

	public static void menuLocacao() {
		int opcao = -1;
		while (opcao != 4) {
			System.out.println("----Gerenciar Locação----");
			System.out.println("Por favor escolha uma opção:");
			System.out.println("1 - alugar");
			System.out.println("2 - buscar");
			System.out.println("3 - listar");
			System.out.println("4 - Retornar ao menu anterior");
			System.out.println("9 - sair");

			opcao = Integer.parseInt(Menu.sc.nextLine());

			switch (opcao) {
			case 1:
				novaLocacao();
				break;
			case 2:
				buscarPeloID();
				break;
			case 3:
				listarLocacoes();
				break;
			case 4:
				break;
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

	private static void novaLocacao() {
		Scanner leitura = new Scanner(System.in);
		System.out.println("NOVA LOCAÇÃO");

		System.out.print("Informe o CPF ou CNPJ do cliente: ");
		String id = leitura.nextLine();
		Pessoa cliente = Menu.clientes.buscar(id);
		if (cliente == null) {
			System.out.println("Cliente não encontrado! Tente novamente. \n");
			return;
		} else {
			System.out.println(cliente.toString());
			System.out.println();

			System.out.print("Informe a placa do veículo: ");
			String placa = leitura.nextLine();
			Veiculo carro = Menu.veiculos.buscar(placa);
			if (placa == null) {
				System.out.println("Cliente não encontrado! Tente novamente. \n");
				return;
			} else {
				System.out.println(carro.toString());
				System.out.println();

				System.out.print("Informe a data e hora da retirada (dd/mm/aaaa hh:mm): ");
				String dataHora = leitura.nextLine();
				LocalDateTime retirada = null;
				try {
					retirada = LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
				} catch (Exception e) {
					System.out.println("Data/hora em formato inválido! \n");
					return;
				}
				if (ChronoUnit.MINUTES.between(retirada, LocalDateTime.now()) < 0) {
					System.out.println("Impossível retirar um veículo com data futura!\n");
					return;
				} else {
					System.out.print("Informe a agência de retirada do veículo: ");
					String agencia = leitura.nextLine();

					try {
						Menu.locadora.alugar(cliente, carro, agencia, retirada);
					} catch (Exception e) {
						System.out.println("Ooops, algo deu errado! Tente novamente. \n");
						return;
					}
				}
			}
		}
	}
}
