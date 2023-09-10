import java.util.ArrayList;
import java.util.List;

public class LocadoraDeVeiculos {
    private GerenciarVeiculo controleVeiculo = new GerenciarVeiculo();;
    private GerenciarPessoa controlePessoa = new GerenciarPessoa();
    private List<Locacao> listaLocacoes = new ArrayList<>();
    
    
	public static void inicializarSistema() {
        // Carregar BD na listaLocacoes
    }
    public boolean alugar(Pessoa pessoa, Veiculo veiculo) {
        return false;
    }

    public static Locacao buscarLocacao(String id) {
        return null;
    }
    
    public List<Locacao> listar() {
    	return this.listaLocacoes;
    }
    
    
}
