import java.util.ArrayList;
import java.util.List;

public class GerenciarPessoa implements Gerenciador<Pessoa, String>{
	private final List<Pessoa> listaDePessoas = new ArrayList<>();

	@Override
	public void cadastrar(Pessoa objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pessoa buscar(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pessoa> listar() {
		return this.listaDePessoas;
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
