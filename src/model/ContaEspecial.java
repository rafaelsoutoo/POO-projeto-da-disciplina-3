package model;

public abstract class ContaEspecial extends Conta {
    protected double limite;

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void creditar(double valor) {
        saldo = saldo + valor;
    }
}