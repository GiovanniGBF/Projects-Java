/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cadastro;

import java.awt.image.BufferedImage;

/**
 *
 * @author Bobito
 */
public class Aluno {
    private String Nome;
    private String Apelido;
    private String NomeLvro;
    private int id;
    private String Sala;
    private int Numero;
    private int Turma;
    private BufferedImage Imagem;

    public void setImagem(BufferedImage Imagem) {
        this.Imagem = Imagem;
    }

    public BufferedImage getImagem() {
        return Imagem;
    }

    public String getNome() {
        return Nome;
    }

    public String getApelido() {
        return Apelido;
    }

    public String getNomeLvro() {
        return NomeLvro;
    }

    public int getId() {
        return id;
    }

    public String getSala() {
        return Sala;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public void setApelido(String Apelido) {
        this.Apelido = Apelido;
    }

    public void setNomeLvro(String NomeLvro) {
        this.NomeLvro = NomeLvro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSala(String Sala) {
        this.Sala = Sala;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public void setTurma(int Turma) {
        this.Turma = Turma;
    }

    public int getNumero() {
        return Numero;
    }

    public int getTurma() {
        return Turma;
    }
    
}
