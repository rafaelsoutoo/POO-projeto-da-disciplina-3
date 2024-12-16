package model;

public class ContaNormal extends Conta {

    @Override
    public void creditar(double valor) {
        saldo = saldo + valor;
    }

    @Override
    public void debitar(double valor) {
        if (saldo - valor >= 0) {
            saldo = saldo - valor;
        }
    }

    @Override
    public int compareTo(Conta o) {
        return numero.compareTo(o.getNumero());
    }
}