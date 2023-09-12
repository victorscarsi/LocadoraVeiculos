import javax.swing.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Locacao {
    private static long idGlobal = 0;
    private long idLocacao;
    private Pessoa locatario;
    private Veiculo veiculo;
    private LocalDateTime retirada;
    private LocalDateTime devolucao;
    private double valorTotal;
    private boolean locacaoFinalizada;
    private String agencia;

    public Locacao(Pessoa locatario, Veiculo veiculo, String agencia,LocalDateTime retirada) {
        this.idLocacao = ++idGlobal;
        this.locatario = locatario;
        this.veiculo = veiculo;
        this.agencia = agencia;
        this.retirada = retirada;
        this.devolucao = null;
        this.valorTotal = -1;
        this.locacaoFinalizada = false;
    }
    public void finalizarLocacao(LocalDateTime devolucao) throws Exception {
        int quantidadeDiarias = calcularDiarias(devolucao);
        if (quantidadeDiarias != -1) {
            if (calcularValorTotal(quantidadeDiarias)) {
                this.locacaoFinalizada = true;
            } else {
                throw new Exception("Erro ao calcular o valor total da locação.");
            }
        } else {
            throw new Exception("Erro no cálculo das diárias.");
        }
    }

    private int calcularDiarias(LocalDateTime devolucao) {
        if (this.locacaoFinalizada) {
            //implementar calculo da quantidade de diárias.
            long tempoLocacaoMinutos = ChronoUnit.MINUTES.between(this.retirada, devolucao);
            int diasLocado = (int) tempoLocacaoMinutos/1440;
            if (diasLocado <= 0) {
                return -1;
            } else {
                if (tempoLocacaoMinutos % 1440 > 0) {
                    diasLocado++;
                }
                return diasLocado;
            }
        } else {
            return -1;
        }
    }

    private boolean calcularValorTotal(int quantideDiarias) {
        double valorDiaria, valorTotal = 0;
        switch (this.veiculo.getTipo()) {
            case PEQUENO:
                valorDiaria = 100;
                break;
            case MEDIO:
                valorDiaria = 150;
                break;
            case SUV:
                valorDiaria = 200;
                break;
            default:
                return false;
        }
        valorTotal = quantideDiarias * valorDiaria;

        if (this.locatario.getClass().equals(PessoaFisica.class)) {
            if (quantideDiarias > 5) {
                valorTotal *= 0.95;
            }
        } else if (this.locatario.getClass().equals(PessoaJuridica.class)) {
            if (quantideDiarias > 3) {
                valorTotal *= 0.90;
            }
        } else {
            return false;
        }

        this.valorTotal = valorTotal;
        return true;
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

    public long getIdLocacao() {
        return this.idLocacao;
    }
}
