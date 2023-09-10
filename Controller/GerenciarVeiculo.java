import java.util.ArrayList;
import java.util.List;

public class GerenciarVeiculo implements Gerenciador<Veiculo, String>{
	private final List<Veiculo> listaVeiculo = new ArrayList<>();

	@Override
	public void cadastrar(Veiculo objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Veiculo buscar(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Veiculo> listar() {
		return this.listaVeiculo;
	}

	@Override
	public void alterar(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(String id) {
		// TODO Auto-generated method stub
		
	}
}
