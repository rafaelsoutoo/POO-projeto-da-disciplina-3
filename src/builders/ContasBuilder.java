package builders;

import java.util.Iterator;
import model.Conta;

public interface ContasBuilder {
    String gerarCabecalho();
    String gerarListagemContas(Iterator<Conta> iterator);
    String gerarSumario();
}