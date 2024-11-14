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
        List<Integer> clientesCpf = new ArrayList<>();
        List<String> clientesSenha = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        List<Conta> contas = new ArrayList<>();

        while (n != 0) {
            menu.menuImprime();
            n = entrada.nextInt();

            if ((n != 1) && (n != 2) && (n != 3) && (n!= 4) && (n != 0)){
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

                    System.out.println("Sucesso ao cadastrar usuário!");
                    cliente.clienteImrpime();
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
                    System.out.println(3);
                }

                //Autenticação de usuário
                if (n == 4){
                    System.out.println("Por favor digite o CPF proprietário da conta: ");
                    int cpf = entrada.nextInt();

                }
            }
        }
        System.out.println("Saindo do programa.");
    }
}