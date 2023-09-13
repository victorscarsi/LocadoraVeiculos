import java.util.Scanner;

public class Menu {
	public static Scanner sc = new Scanner(System.in);

	public static GerenciarVeiculo veiculos = new GerenciarVeiculo();
	public static GerenciarPessoa clientes = new GerenciarPessoa();
	public static LocadoraDeVeiculos locadora = new LocadoraDeVeiculos();

	public static void main(String[] args) {

		int opcao = -1;
		while (opcao != 9) {
			try {
				menuPrincipal();
				opcao = Integer.parseInt(sc.nextLine());

				switch (opcao) {
					case 1:
						MenuPessoa.menuPessoa();
						break;

					case 2:
						MenuVeiculo.menuVeiculo();
						break;

					case 3:
						MenuLocacao.menuLocacao();
						break;

					case 9:
						System.out.println("Saindo do sistema");
						sc.close();
						System.exit(0);

					default:
						System.out.println("Opção Inválida, tente novamente");
				}
			} catch (NumberFormatException e) {
				System.out.println("Por favor, insira um número válido.");
			}
		}
	}

	private static void menuPrincipal() {
		System.out.println("\nBem Vindo, por favor escolha uma opção");
		System.out.println("Gerenciar pessoa - 1");
		System.out.println("Gerenciar veiculo - 2");
		System.out.println("Gerenciar locações - 3");
		System.out.println("Sair - 9");
	}
}
