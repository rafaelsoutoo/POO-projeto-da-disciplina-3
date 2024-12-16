package apresentacao;

import java.util.ArrayList;
import java.util.Iterator;

import model.Conta;
import model.ContaNormal;
import database.AcessoADado;
import model.ContaDebEspecial;

public class Banco {
    private ArrayList<Conta> contas;

    Banco() {
        contas = contas();
    }

    private ArrayList<Conta> contas() {
        if (contas == null) {
            contas = new ArrayList<>();
        }
        return contas;
    }

    private void CriaConta(Conta c) {
        contas.add(c);
    }

    private void RemoveConta(String numero) {
        Iterator<Conta> iterator = contas.iterator();
        while (iterator.hasNext()) {
            Conta c = iterator.next();
            if (c.getNumero().equals(numero)) {
                iterator.remove();
            }
        }
    }

    private void CreditaConta(String numero, double valor) {
        Iterator<Conta> iterator = contas.iterator();
        while (iterator.hasNext()) {
            Conta c = iterator.next();
            if (c.getNumero().equals(numero)) {
                c.creditar(valor);
            }
        }
    }

    private void DebitaConta(String numero, double valor) {
        Iterator<Conta> iterator = contas.iterator();
        while (iterator.hasNext()) {
            Conta c = iterator.next();
            if (c.getNumero().equals(numero)) {
                c.debitar(valor);
            }
        }
    }

    private void TransfereConta(String numero_conta_origem, String numero_conta_destino, double valor) {
        DebitaConta(numero_conta_origem, valor);
        CreditaConta(numero_conta_destino, valor);
    }

    private void ListaContas() {
        Iterator<Conta> iterator = contas.iterator();
        while (iterator.hasNext()) {
            Conta c = iterator.next();
            System.out.printf("Conta %s  %s\n", c.getNumero(), c.getSaldo());
        }
    }

    public static void main(String args[]) {
        Banco banco = new Banco();
        AcessoADado acessoADado = new AcessoADado();

        String numeroConta1 = "1654-3";
        String numeroConta2 = "4067-6";
        String numeroConta3 = "7865-2";
        String numeroConta4 = "1234-7";

        String respostaConta1 = acessoADado.cadastrar_conta_normal(numeroConta1);
        String respostaConta2 = acessoADado.cadastrar_conta(numeroConta2, 2500);
        String respostaConta3 = acessoADado.cadastrar_conta_normal(numeroConta3);
        String respostaConta4 = acessoADado.cadastrar_conta(numeroConta4, 1000);
        System.out.println(respostaConta1);  
        System.out.println(respostaConta2);  
        System.out.println(respostaConta3);  
        System.out.println(respostaConta4);  

        Conta c1 = new ContaNormal();
        c1.setNumero(numeroConta1);
        c1.setSaldo(500); 
        banco.CriaConta(c1);

        Conta c2 = new ContaDebEspecial();
        c2.setNumero(numeroConta2);
        c2.setSaldo(2500);
        banco.CriaConta(c2);

        Conta c3 = new ContaNormal();
        c3.setNumero(numeroConta3);
        c3.setSaldo(1000);
        banco.CriaConta(c3);

        Conta c4 = new ContaDebEspecial();
        c4.setNumero(numeroConta4);
        c4.setSaldo(1000);
        banco.CriaConta(c4);

        banco.ListaContas();

        banco.CreditaConta(numeroConta1, 1000);
        acessoADado.atualizar_saldo(numeroConta1, 1500);  
        banco.ListaContas();

        banco.TransfereConta(numeroConta1, numeroConta2, 500);
        acessoADado.atualizar_saldo(numeroConta1, 1000); 
        acessoADado.atualizar_saldo(numeroConta2, 3000);  
        banco.ListaContas();

        banco.RemoveConta(numeroConta1);
        String respostaRemocao = acessoADado.remover_conta(numeroConta1);
        System.out.println(respostaRemocao);
        banco.ListaContas();

    }
}