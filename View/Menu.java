import java.util.Scanner;

public class Menu {
	private static Scanner sc = new Scanner(System.in);
	private static LocadoraDeVeiculos locadora = new LocadoraDeVeiculos();

	public static void main(String[] args) {

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
				System.out.print("Digite o ID da locação:");
				Locacao locacao = locadora.buscarLocacao(sc.nextLine());
				System.out.println("Informações da locação:\n" + locacao);
				break;
			case 3:
				System.out.println(locadora.listar());
				break;
			case 4:
				return;
			case 9:
				System.exit(0);
			default: 
				System.out.println("Opção inválida, digite novamente:");
				break;
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
				System.out.print("Digite a placa do veículo:");
				Veiculo veiculo = locadora.getControleVeiculo().buscar(sc.nextLine());
				System.out.println("Informações do veículo:\n" + veiculo);
				break;
			case 3:
				// alterar carro
				break;
			case 4:
				System.out.println(locadora.getControleVeiculo().listar());
				break;
			case 5:
				return;
			case 9:
				System.exit(0);
			default: 
				System.out.println("Opção inválida, digite novamente:");
				break;
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
				System.out.print("CPF ou CNPJ da pessoa: ");
				String id = sc.nextLine();
				System.out.print("Nome da pessoa: ");
				String nome = sc.nextLine();
				System.out.print("Contato da pessoa: ");
				String contato = sc.nextLine();
				System.out.print("Endereço da pessoa: ");
				String endereco = sc.nextLine();

				// Verifique se a pessoa já existe
				Pessoa pessoaExistente = locadora.getControlePessoa().buscar(id);
				if (pessoaExistente != null) {
					System.out.println("Pessoa já cadastrada com este CPF ou CNPJ.");
				} else {
					Pessoa novaPessoa;
					if (id.length() == 11) { // CPF tem 11 caracteres
						novaPessoa = new PessoaFisica(id, nome, contato, endereco);
					} else if (id.length() == 14) { // CNPJ tem 14 caracteres
						novaPessoa = new PessoaJuridica(id, nome, contato, endereco);
					} else {
						System.out.println("CPF ou CNPJ inválido.");
						break;
					}

					locadora.getControlePessoa().cadastrar(novaPessoa);
					System.out.println("Pessoa cadastrada com sucesso.");
				}
				break;



			case 2:
				System.out.print("Digite o cpf ou cnpj da pessoa:");
				Pessoa pessoa = locadora.getControlePessoa().buscar(sc.nextLine());
				System.out.println("Informações da pessoa:\n" + pessoa);
				break;
			case 3:
				System.out.print("Digite o CPF ou CNPJ da pessoa que deseja alterar: ");
				String novoId = sc.nextLine();
				Pessoa pessoaParaAlterar = locadora.getControlePessoa().buscar(novoId);

				if (pessoaParaAlterar != null) {
					System.out.print("Novo endereço: ");
					String novoEndereco = sc.nextLine();
					System.out.print("Novo contato: ");
					String novoContato = sc.nextLine();

					locadora.getControlePessoa().alterar(novoId, novoEndereco, novoContato);
					System.out.println("Pessoa alterada com sucesso.");
				} else {
					System.out.println("Pessoa não encontrada.");
				}
				break;

			case 4:
				System.out.println(locadora.getControlePessoa().listar());
				break;
			case 5:
				return;
			case 9:
				System.exit(0);
			default:
				System.out.println("Opção inválida, digite novamente:");
				break;
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
