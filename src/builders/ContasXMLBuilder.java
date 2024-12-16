package builders;

import java.util.Iterator;
import model.Conta;

public class ContasXMLBuilder implements ContasBuilder {
    double saldo_total = 0;

    public String gerarCabecalho() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    }

    public String gerarListagemContas(Iterator<Conta> iterator) {
        String corpo = "<contas>\n";

        while (iterator.hasNext()) {
            Conta c = iterator.next();
            corpo += "\t<conta>\n" +
                    "\t\t<numero>" + c.getNumero() + "</numero>\n" +
                    "\t\t<saldo>" + c.getSaldo() + "</saldo>\n" +
                    "\t</conta>\n";
            saldo_total += c.getSaldo();
        }

        corpo += "</contas>\n";
        return corpo;
    }

    public String gerarSumario() {
        return "<saldo_total>" + saldo_total + "</saldo_total>";
    }

    public String listagemContas(Iterator<Conta> iterator) {
        return gerarCabecalho() + "\n" + gerarListagemContas(iterator) + "\n" + gerarSumario();
    }
}