import java.util.List;

public class MenuPessoa {
	public static void menuPessoa() {
		while (true) {
			System.out.println("----Gerenciar pessoa----");
			System.out.println("Por favor escolha uma opção:");
			System.out.println("1 - cadastrar");
			System.out.println("2 - buscar");
			System.out.println("3 - alterar");
			System.out.println("4 - listar");
			System.out.println("5 - Retornar ao menu anterior");
			System.out.println("9 - sair");

			int opcao = Integer.parseInt(Menu.sc.nextLine());

			switch (opcao) {
			case 1:
				cadastrarPessoa();
				break;
			case 2:
				buscarPessoaID();
				break;
			case 3:
				alterarPessoa();
				break;

			case 4:
				listarPessoas();
				break;
			case 5:
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

	private static void alterarPessoa() {
		try {
			System.out.print("Digite o CPF ou CNPJ da pessoa que deseja alterar: ");
			String novoId = Menu.sc.nextLine();

			if (novoId == null || novoId.isEmpty()) {
				throw new IllegalArgumentException("CPF ou CNPJ não pode ser vazio.");
			}

			Pessoa pessoaParaAlterar = Menu.locadora.getControlePessoa().buscar(novoId);

			if (pessoaParaAlterar != null) {
				System.out.print("Novo endereço: ");
				String novoEndereco = Menu.sc.nextLine();
				if (novoEndereco == null || novoEndereco.isEmpty()) {
					throw new IllegalArgumentException("Endereço não pode ser vazio.");
				}

				System.out.print("Novo contato: ");
				String novoContato = Menu.sc.nextLine();
				if (novoContato == null || novoContato.isEmpty()) {
					throw new IllegalArgumentException("Contato não pode ser vazio.");
				}

				Menu.locadora.getControlePessoa().alterar(novoId, novoEndereco, novoContato);
				System.out.println("Pessoa alterada com sucesso.");
			} else {
				throw new NullPointerException("Nenhuma pessoa encontrada com o CPF ou CNPJ fornecido.");
			}
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
			Menu.sc.nextLine();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
			Menu.sc.nextLine();
		}
	}

	private static void listarPessoas() {
		List<Pessoa> pessoas = Menu.locadora.getControlePessoa().listar();

		if (pessoas == null || pessoas.isEmpty()) {
			System.out.println("Nenhuma pessoa encontrada.");
			Menu.sc.nextLine();
		} else {
			System.out.println(pessoas);
		}
	}

	private static void buscarPessoaID() {
		try {
			System.out.print("Digite o cpf ou cnpj da pessoa:");
			String id = Menu.sc.nextLine();

			if (id == null || id.isEmpty()) {
				throw new IllegalArgumentException("CPF ou CNPJ não pode ser vazio.");
			}

			Pessoa pessoa = Menu.locadora.getControlePessoa().buscar(id);

			if (pessoa == null) {
				throw new NullPointerException("Nenhuma pessoa encontrada com o CPF ou CNPJ fornecido.");
			}

			System.out.println("Informações da pessoa:\n" + pessoa);
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println("Ocorreu um erro ao buscar a pessoa: " + e.getMessage());
			Menu.sc.nextLine();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
			Menu.sc.nextLine();
		}
	}

	public static void cadastrarPessoa() {
		try {
			System.out.print("CPF ou CNPJ da pessoa: ");
			String id = Menu.sc.nextLine();
			if (id == null || id.isEmpty()) {
				throw new IllegalArgumentException("CPF ou CNPJ não pode ser vazio.");
			}

			System.out.print("Nome da pessoa: ");
			String nome = Menu.sc.nextLine();
			if (nome == null || nome.isEmpty()) {
				throw new IllegalArgumentException("Nome não pode ser vazio.");
			}

			System.out.print("Contato da pessoa: ");
			String contato = Menu.sc.nextLine();
			if (contato == null || contato.isEmpty()) {
				throw new IllegalArgumentException("Contato não pode ser vazio.");
			}

			System.out.print("Endereço da pessoa: ");
			String endereco = Menu.sc.nextLine();
			if (endereco == null || endereco.isEmpty()) {
				throw new IllegalArgumentException("Endereço não pode ser vazio.");
			}

			// Verifique se a pessoa já existe
			Pessoa pessoaExistente = Menu.locadora.getControlePessoa().buscar(id);
			if (pessoaExistente != null) {
				System.out.println("Pessoa já cadastrada com este CPF ou CNPJ.");
			} else {
				Pessoa novaPessoa;
				if (id.length() == 11) { // CPF tem 11 caracteres
					novaPessoa = new PessoaFisica(id, nome, contato, endereco);
				} else if (id.length() == 14) { // CNPJ tem 14 caracteres
					novaPessoa = new PessoaJuridica(id, nome, contato, endereco);
				} else {
					throw new IllegalArgumentException("CPF ou CNPJ inválido.");
				}

				Menu.locadora.getControlePessoa().cadastrar(novaPessoa);
				System.out.println("Pessoa cadastrada com sucesso.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			Menu.sc.nextLine();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
			Menu.sc.nextLine();
		}
	}
}
