package model;

public class ContaDebEspecial extends ContaEspecial {

    public ContaDebEspecial() {
        super();
    }

    public ContaDebEspecial(String n, double s, double l) {
        numero = n;
        saldo = s;
        limite = l;
    }

    @Override
    public void debitar(double valor) {
        if ((limite + saldo - valor) >= 0) {
            saldo = saldo - valor;
        }
    }

    @Override
    public int compareTo(Conta o) {
        return numero.compareTo(o.getNumero());
    }
}
