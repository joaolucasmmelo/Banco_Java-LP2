/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.ufrn.bti.banco1000;

import br.ufrn.bti.banco1000.model.Cliente;
import br.ufrn.bti.banco1000.model.Menu;
import br.ufrn.bti.banco1000.model.Conta;
import br.ufrn.bti.banco1000.model.Movimentacao;
import br.ufrn.bti.banco1000.model.Transferencia;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author vinicius
 */
public class Banco1000 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Menu menu = new Menu();

        int n = 1;
        int verLogin = 0;
        int cpfVerOp = 0;

        List<Cliente> clientesLista = new ArrayList<>();
        List<Conta> contas = new ArrayList<>();

        while (n != 0) {
            if (verLogin == 0){
                menu.menuImprime();
            }
            else{
                System.out.println("\nDados da conta:");
                System.out.println("Nome: " + clientesLista.get(verLogin - 1).getNome());
                System.out.println("CPF: " + clientesLista.get(verLogin - 1).getCpf());
                menu.menuImprime();
            }

            n = entrada.nextInt();

            if ((n != 1) && (n != 2) && (n != 3) && (n != 4) && (n != 0) && (n != 5)){
                System.out.println("Por favor digite uma opção válida!");
            }
            else{
                // Cadastro de usuários
                if (n == 1) {
                    int verWhile = 0;

                    while (verWhile != 1){
                        System.out.println("Digite os dados necessários para o cadastro do usuário:");

                        System.out.print("\nDigite o nome do usuário: ");
                        entrada.nextLine();
                        String nome = entrada.nextLine();

                        System.out.print("\nDigite o CPF do usuário: ");
                        int cpf = entrada.nextInt();

                        for (Cliente cliente : clientesLista){
                            if (cpf == cliente.getCpf()){
                                System.out.println("Esse CPF já está cadastrado em nosso sistema!");
                                verWhile = 1;
                            }
                        }

                        if (verWhile != 1){
                            System.out.print("\nDigite a senha do usuário: ");
                            entrada.nextLine();
                            String senha = entrada.nextLine();

                            Cliente cliente = new Cliente(nome, cpf, senha);
                            clientesLista.add(cliente);

                            System.out.println("Sucesso ao cadastrar usuário!");
                            verWhile = 1;
                        }
                    }
                }

                // Criação de conta para o usuário selecionado
                if (n == 2){
                    if (!clientesLista.isEmpty()){
                        System.out.print("Digite o CPF do proprietário da conta: ");

                        int verif = 0;
                        while (verif != 1){
                            int cpf = entrada.nextInt();
                            int i = 0;
                            for (Cliente cliente : clientesLista){
                                if (cpf == cliente.getCpf()){
                                    String senhaCorreta = cliente.getSenha();
                                    System.out.print("Digite a senha: ");

                                    while (true){
                                        String senha = entrada.next();
                                        if (senha.equals(senhaCorreta)){
                                            //criação da conta no banco
                                            while (true){
                                                System.out.println("1 - Corrente\n2 - Poupança");
                                                System.out.print("Digite o tipo de conta que deseja criar: ");

                                                String tipo = entrada.next();

                                                if (tipo.equals("1") || tipo.equals("2")){
                                                    if (tipo.equals("1")){
                                                        tipo = "Corrente";
                                                    }
                                                    if (tipo.equals("2")){
                                                        tipo = "Poupança";
                                                    }

                                                    while (true){
                                                        System.out.print("Digite o saldo inicial da conta: ");
                                                        double saldo = entrada.nextDouble();
                                                        if (saldo < 0){
                                                            System.out.println("O saldo inicial da conta não pode ser negativo!");
                                                        }
                                                        else{
                                                            System.out.print("Por ultimo digite o ID da sua conta: ");
                                                            while (true) {
                                                                int ver = 0;
                                                                int id = entrada.nextInt();
                                                                for (Conta conta : contas) {
                                                                    if (id == conta.getIdConta()) {
                                                                        System.out.println("ID já existente!");
                                                                        ver = 1;
                                                                    }
                                                                }
                                                                if (ver == 1){
                                                                    System.out.print("Por favor digite um ID exclusivo: ");
                                                                    continue;
                                                                }

                                                                System.out.println("Conta criada com sucesso!");
                                                                Conta conta = new Conta(cpf, id, tipo, senha, saldo);
                                                                contas.add(conta);

                                                                break;
                                                            }
                                                            break;
                                                        }
                                                    }
                                                    break;
                                                }

                                                else{
                                                    System.out.println("Tipo de conta inválido!");
                                                }
                                            }


                                            //final da criação da conta
                                            verif = 1;
                                            break;
                                        }
                                        else {
                                            System.out.println("\nSenha incorreta!");
                                            verif = 1;
                                            break;
                                        }
                                    }
                                }
                                i++;
                            }
                            if (verif != 1) {
                                System.out.println("CPF inválido!");
                            }
                            break;
                        }
                    }
                    else{
                        System.out.println("Não há nenhum usuário cadastrado para criação de contas!");
                    }
                }

                // Operações bancárias
                if (n == 3){
                    List<Integer> idsUsuario = new ArrayList<>();

                    // Autorização de login para os procedimentos da conta bancária
                    if (verLogin != 0){
                        int verWhile = 0;
                        int verWhileOp = 0;

                        while (verWhile != 1){
                            System.out.println("Suas contas: ");
                            for (Conta conta : contas){
                                if (cpfVerOp == conta.getCpf()){
                                    idsUsuario.add(conta.getIdConta());
                                    System.out.println(conta.getIdConta());
                                }
                            }

                            System.out.print("\nDigite o ID da conta em que deseja realizar uma operação: ");
                            int id = entrada.nextInt();

                            for (int idVerLista : idsUsuario){
                                if (id == idVerLista){
                                    verWhile = 1;
                                    int op = 1;

                                    while (verWhileOp != 1) {
                                        if (verWhileOp != 1){
                                            menu.menuOperacoesImprime();
                                        }

                                        op = entrada.nextInt();
                                        if ((op != 1) && (op != 2) && (op != 3) && (op != 4) && (op != 0)) {
                                            System.out.println("Por favor digite uma opção válida!");
                                        }
                                        else {
                                            // Consulta de Saldo
                                            if (op == 1) {
                                                System.out.println("Saldo da conta " + id + ":");
                                                for (Conta conta : contas){
                                                    if (conta.getIdConta() == id){
                                                        System.out.println("R$ " + conta.getSaldo());
                                                    }
                                                }
                                            }

                                            // Saque
                                            if (op == 2) {
                                                int verSaque = 0;

                                                for (Conta conta : contas){
                                                    if (conta.getIdConta() == id){
                                                        while (verSaque != 1){
                                                            System.out.print("Digite quanto deseja sacar: ");
                                                            double valorSaque = entrada.nextDouble();

                                                            if (valorSaque > conta.getSaldo()){
                                                                System.out.println("Saldo insuficiente!");
                                                                System.out.println("Saldo disponível: R$ " + conta.getSaldo());
                                                            }
                                                            else{
                                                                conta.setSaldo(conta.getSaldo() - valorSaque);
                                                                System.out.println("Saldo Restante: R$ " + conta.getSaldo());

                                                                verSaque = 1;
                                                            }
                                                        }
                                                        break;
                                                    }
                                                }
                                            }

                                            // Depósito
                                            if (op == 3) {
                                                int verDepo = 0;

                                                for (Conta conta : contas){
                                                    if (conta.getIdConta() == id){
                                                        while (verDepo != 1){
                                                            System.out.print("Digite quanto deseja depositar: ");
                                                            double valorDeposito = entrada.nextDouble();

                                                            conta.setSaldo(conta.getSaldo() + valorDeposito);
                                                            System.out.println("Novo Saldo: R$ " + conta.getSaldo());

                                                            verDepo = 1;
                                                        }
                                                        break;
                                                    }
                                                }
                                            }

                                            //
                                            if (op == 4) {

                                            }

                                            // Saída das operações bancárias
                                            if (op == 0) {
                                                verWhileOp = 1;
                                                System.out.println("Saindo das Operações bancárias.");
                                            }
                                        }
                                    }
                                }
                            }

                            if (verWhile == 0){
                                System.out.println("Digite um ID correspondente a uma das opções abaixo!");
                            }
                        }
                    }
                    else{
                        System.out.println("Você precisa estar logado em uma conta para poder realizar operações!");
                        System.out.println("Por favor realize login antes de voltar para as operações. (Opção 4)");
                    }
                }

                //Autenticação de usuário
                if ((n == 4)){
                    if (verLogin == 0){
                        int verWhile = 0;
                        while (verWhile == 0){
                            System.out.print("Por favor digite o CPF proprietário da conta: ");
                            int cpf = entrada.nextInt();

                            int i = 0;
                            for (Cliente cliente : clientesLista){
                                if (cpf == cliente.getCpf()){
                                    System.out.print("Digite a senha do usuário: ");
                                    entrada.nextLine();

                                    do {
                                        String senha = entrada.nextLine();

                                        for (Cliente cliente2 : clientesLista){
                                            if (senha.equals(cliente.getSenha())){
                                                cpfVerOp = cliente2.getCpf();

                                                verWhile = 1;
                                                verLogin = i+1;
                                            }
                                        }
                                        if ((verWhile == 0) && (verLogin == i)){
                                            break;
                                        }
                                    }while ((verLogin == 0) && (verWhile != 1));
                                }
                                i++;
                            }
                            if (verWhile == 0){
                                System.out.println("Senha ou CPF incorretos.");
                                System.out.println("Por favor digite os dados corretamente.");
                            }
                        }
                    }
                    else{
                        System.out.println("\nVocê já está logado em uma conta!");
                    }
                }

                if (n == 5){
                    if (verLogin != 0){
                        verLogin = 0;
                        cpfVerOp = 0;
                        System.out.println("Sucesso ao sair da conta.");
                    }
                    else{
                        System.out.println("Você já não está logado em conta nehuma.");
                    }
                }
            }
        }
        System.out.println("Saindo do programa.");
    }
}
