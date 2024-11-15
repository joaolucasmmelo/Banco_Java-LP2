package br.ufrn.bti.banco1000.model;

public class Menu {
    public void menuImprime(){
        System.out.println();
        System.out.println("BANCO:\n");
        System.out.println("1 - Cadastro de usuário");
        System.out.println("2 - Criação de conta bancária");
        System.out.println("3 - Operações bancárias");
        System.out.println("4 - Login");
        System.out.println("5 - Logout");
        System.out.println("0 - Sair");
        System.out.print("Digite um número correspondente a uma das opções: ");
    }

    public void menuOperacoesImprime(){
        System.out.println();
        System.out.println("Operações bancárias:\n");
        System.out.println("1 - Consulta de saldo");
        System.out.println("2 - Saque");
        System.out.println("3 - Depósito");
        System.out.println("4 - Transferência");
        System.out.println("0 - Sair do Menu de Operações bancárias");
        System.out.print("Digite o número correspondente a operação que deseja realizar: ");
    }
}
