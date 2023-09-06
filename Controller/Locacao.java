import javax.swing.*;
import java.time.LocalDateTime;

public class Locacao {
    private static long idGlobal = 0;
    private long idLocacao;
    private Pessoa locatario;
    private Veiculo veiculo;
    private LocalDateTime retirada;
    private LocalDateTime devolucao;
    private double valorTotal;
    private boolean locacaoFinalizada;

    public Locacao(Pessoa locatario, Veiculo veiculo, LocalDateTime retirada) {
        this.idLocacao = ++idGlobal;
        this.locatario = locatario;
        this.veiculo = veiculo;
        this.retirada = retirada;
        this.devolucao = null;
        this.valorTotal = -1;
        this.locacaoFinalizada = false;
    }
    public boolean finalizarLocacao(LocalDateTime devolucao) {
        int quantidadeDiarias = calcularDiarias(devolucao);
        // calcularValorDiaria
        // calcularValorTotal

        return false;
    }

    private int calcularDiarias(LocalDateTime devolucao) {
        if (locacaoFinalizada) {
            //implementar calculo da quantidade de diárias.
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        String dadosLocacao = "--------------" +
                "\nIdentificador: " + this.idLocacao +
                "\nLocatário....: " + this.locatario.toString() +
                "\nVeículo......: " + this.veiculo.toString() +
                "\nRetirada.....: " + this.retirada.toString();
        if (this.isLocacaoFinalizada()) {
            dadosLocacao += "\nDevolução....: " + this.devolucao.toString() +
                    "\nValor Total..: R$ " + this.valorTotal +
                    "\n--------------";
        } else {
            dadosLocacao += "\nDevolução....: Locação em Andamento" +
                    "\nValor Total..: A processar" +
                    "\n--------------";
        }
        return dadosLocacao;
    }

    private boolean isLocacaoFinalizada() {
        return this.locacaoFinalizada;
    }
}
