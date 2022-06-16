package com.example.exercicio06;

import java.io.Serializable;

public class Cliente implements Serializable {
    private Integer id;
    private String nome;
    private Integer matricula;
    private String endereco;
    private Integer numero;
    private String complemento;
    private String cidade;

    public Cliente() {

    }
    public Cliente( Integer id,String nome, Integer matricula, String endereco, Integer numero,
                    String complemento, String cidade){
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


    public String toString() {
        return "nome: " + nome +
                "\nMatricula: "+ matricula+
                "\nEndereco: "+endereco+
                "\nNúmero: "+numero+
                "\nComplemento: "+complemento+
                "\nCidade: "+ cidade+"\n";


}
}