/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufrn.bti.banco1000.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinicius
 */
public class Cliente {
    private String nome;
    private int cpf;
    private String senha;
    private Conta conta;

    public Cliente(String nome, int cpf, String senha){
       this.nome = nome;
       this.cpf = cpf;
       this.senha = senha;
    }

    //getters e setters
    public String getNome() { return nome; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

}
