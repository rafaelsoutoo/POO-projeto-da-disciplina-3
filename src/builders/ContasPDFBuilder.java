package builders;

import java.util.Iterator;
import model.Conta;

public class ContasPDFBuilder implements ContasBuilder {

    public String gerarCabecalho() {
        return "";
    }

    public String gerarListagemContas(Iterator<Conta> iterator) {
        return "";
    }

    public String gerarSumario() {
        return "";
    }

    public String listagemContas(Iterator<Conta> iterator) {
        String resultado = gerarCabecalho() + "\n" + gerarListagemContas(iterator) + "\n" + gerarSumario();
        return resultado;
    }
}