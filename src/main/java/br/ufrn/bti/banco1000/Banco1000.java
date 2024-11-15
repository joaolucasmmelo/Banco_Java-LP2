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
        List<Integer> clientesCpf = new ArrayList<>();
        List<String> clientesSenha = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        List<Conta> contas = new ArrayList<>();

        while (n != 0) {
            if (verLogin == 0){
                menu.menuImprime();
            }
            else{
                System.out.println("\nDados da conta:");
                System.out.println("Nome: " + clientesLista.get(verLogin).getNome());
                System.out.println("CPF: " + clientesLista.get(verLogin).getCpf());
                menu.menuImprime();
            }


            n = entrada.nextInt();

            if ((n != 1) && (n != 2) && (n != 3) && (n != 4) && (n != 0) && (n != 5)){
                System.out.println("Por favor digite uma opção válida!");
            }
            else{
                // Cadastro de usuários
                if (n == 1) {

                    System.out.println("Digite os dados necessários para o cadastro do usuário:");

                    System.out.print("\nDigite o nome do usuário: ");
                    entrada.nextLine();
                    String nome = entrada.nextLine();

                    System.out.print("\nDigite o CPF do usuário: ");
                    int cpf = entrada.nextInt();
                    clientesCpf.add(cpf);

                    System.out.print("\nDigite a senha do usuário: ");
                    entrada.nextLine();
                    String senha = entrada.nextLine();
                    clientesSenha.add(senha);

                    Cliente cliente = new Cliente(nome, cpf, senha);
                    clientesLista.add(cliente);

                    System.out.println("Sucesso ao cadastrar usuário!");
                }

                // Criação de conta para o usuário selecionado
                if (n == 2){
                    System.out.print("Digite o CPF do proprietário da conta: ");

                    int verif = 0;
                    while (verif != 1){
                        int cpf = entrada.nextInt();
                        int i = 0;
                        for (int cpfusuario : clientesCpf){
                            if (cpf == cpfusuario){
                                String senhaCorreta = clientesSenha.get(i);
                                System.out.print("Digite a senha: ");
                                while (true){
                                    String senha = entrada.nextLine();
                                    if (senha.equals(senhaCorreta)){
                                        //criação da conta no banco
                                        while (true){
                                            System.out.println("Digite o tipo de conta que deseja criar:");
                                            System.out.println("Corrente ou Poupança");
                                            String tipo = entrada.nextLine();

                                            if (tipo.equals("Corrente") || tipo.equals("corrente") || tipo.equals("Poupança") || tipo.equals("poupança")){
                                                while (true){
                                                    System.out.print("Digite o saldo inicial da conta: ");
                                                    double saldo = entrada.nextDouble();
                                                    if (saldo < 0){
                                                        System.out.println("O saldo inicial da conta não pode ser negativo!");
                                                    }
                                                    else{
                                                        System.out.println("Por ultimo digite o ID da sua conta:");
                                                        while (true) {
                                                            int ver = 0;
                                                            int id = entrada.nextInt();
                                                            for (int idver : ids) {
                                                                if (id == idver) {
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
                                                            ids.add(id);

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
                                }
                            }
                            i++;
                        }
                        if (verif == 1){
                            break;
                        }
                        else{
                            System.out.println("CPF inválido!");
                            System.out.print("Digite o CPF do proprietário da conta: ");
                        }
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
                if (n == 4){
                    int verWhile = 0;
                    while (verWhile == 0){
                        System.out.print("Por favor digite o CPF proprietário da conta: ");
                        int cpf = entrada.nextInt();

                        int i = 0;
                        for (int cpfver : clientesCpf){
                            if (cpf == cpfver){
                                System.out.print("Digite a senha do usuário: ");
                                entrada.nextLine();

                                do {
                                    String senha = entrada.nextLine();

                                    for (String senhaVer : clientesSenha){
                                        if (senha.equals(senhaVer)){
                                            System.out.println("\nUsuário logado.");
                                            System.out.println("\nDados do cliente:");

                                            clientesLista.get(i).clienteImrpime();
                                            cpfVerOp = clientesCpf.get(i);

                                            verWhile = 1;
                                            verLogin = i;
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
