/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufrn.bti.banco1000.model;

import java.util.ArrayList;

public class Conta {
    private Cliente cliente;

    private int cpf;
    private String senha;
    private int idConta;
    private String tipo;
    private double saldo;
    private ArrayList<Movimentacao> movimentacao = new ArrayList();
    private ArrayList<Integer> idContas = new ArrayList();

    public Conta(int cpf, int idConta, String tipo, String senha, double saldo, ArrayList movimentacao) {
        this.cpf = cpf;
        this.idConta = idConta;
        this.tipo = tipo;
        this.senha = senha;
        this.saldo = saldo;
        this.movimentacao = movimentacao;
        idContas.add(idConta);
    }

    public Conta(int cpf, int idConta, String  tipo, String senha, double saldo) {
        this.cpf = cpf;
        this.idConta = idConta;
        this.tipo = tipo;
        this.senha = senha;
        this.saldo = saldo;
        idContas.add(idConta);
    }

    public int getIdConta(){
        return this.idConta;
    }

    public void depositar(double valor) {
        this.saldo = this.saldo + valor;
        this.movimentacao.add(new Movimentacao("Deposito", this.cliente, valor));
        
    }
    
    public void sacar(double valor) {
        if (this.saldo - valor >= 0) {
            this.saldo = this.saldo - valor;
            this.movimentacao.add(new Movimentacao("Saque", this.cliente, valor));
            
        }
    }
    
    public void transferir(int agencia, int idConta, double valor) {
        if (this.saldo - valor >= 0) {
           this.saldo = this.saldo - valor; 
        }
    }
    
    public void transferir(Conta conta, double valor) {
        
         if (this.saldo - valor >= 0) {
           conta.sacar(valor);
           conta.depositar(valor);
           conta.movimentacao.add(new Movimentacao("Saque", this.cliente, valor));
           this.movimentacao.add(new Movimentacao("Deposito", this.cliente, valor));
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getCpf() {
        return this.cpf;
    }
    
    public double getSaldo() {
        return this.saldo;
    }
    
    @Override
    public boolean equals(Object o){
        return false;
    }
    
    @Override
    public String toString()
    {
        return "";
    }
}
