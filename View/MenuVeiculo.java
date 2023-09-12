import java.util.List;

public class MenuVeiculo {

	public static void menuVeiculo() {
		while (true) {
			System.out.println("----Gerenciar Veiculo----");
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
				cadastrarVeiculo();
				break;
			case 2:
				buscarVeiculo();
				break;
			case 3:
				// alterar carro (falta fazer)
				break;
			case 4:
				listarVeiculos();
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

	private static void cadastrarVeiculo() {
		System.out.println("Digite a placa do veículo:");
		String placa = Menu.sc.nextLine();

		System.out.println("Digite o tipo do veículo (PEQUENO, MEDIO, SUV):");
		String tipo = Menu.sc.nextLine();

		TipoVeiculo tipoVeiculo;
		switch (tipo.toUpperCase()) {
		case "PEQUENO":
			tipoVeiculo = TipoVeiculo.PEQUENO;
			break;
		case "MEDIO":
			tipoVeiculo = TipoVeiculo.MEDIO;
			break;
		case "SUV":
			tipoVeiculo = TipoVeiculo.SUV;
			break;
		default:
			System.out.println("Tipo de veículo inválido. Usando 'PEQUENO' como padrão.");
			tipoVeiculo = TipoVeiculo.PEQUENO;
		}
		
		Veiculo novoVeiculo = new Veiculo(placa, tipoVeiculo);

		Menu.locadora.getControleVeiculo().cadastrar(novoVeiculo);
	}

	private static void listarVeiculos() {
		List<Veiculo> veiculos = Menu.locadora.getControleVeiculo().listar();

		if (veiculos == null || veiculos.isEmpty()) {
			System.out.println("Nenhum veículo encontrado.");
			Menu.sc.nextLine();
		} else {
			System.out.println(veiculos);
		}
	}

	private static void buscarVeiculo() {
		try {
			System.out.print("Digite a placa do veículo:");
			String placa = Menu.sc.nextLine();

			if (placa == null || placa.isEmpty()) {
				throw new IllegalArgumentException("A placa do veículo não pode ser vazia.");
			}

			Veiculo veiculo = Menu.locadora.getControleVeiculo().buscar(placa);

			if (veiculo == null) {
				throw new NullPointerException("Nenhum veículo encontrado com a placa fornecida.");
			}

			System.out.println("Informações do veículo:\n" + veiculo);
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println("Ocorreu um erro ao buscar o veículo: " + e.getMessage());
			Menu.sc.nextLine();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
			Menu.sc.nextLine();
		}
	}

}
