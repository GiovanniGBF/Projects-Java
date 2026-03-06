/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cadastro;

import java.util.Date;

/**
 *
 * @author Bobito
 */
public class LIVROS {
        private int id;
    private String Titulo;
    private String Autor;
    private Date AnoPublico;
    private Date DataEntrada;
    private String Editora;
    private String Descrição;
    private String Idioma;
    private String Condição ;
    private String Genero ;  
    private int NumeroPagina ; 

    public int getNumeroPagina() {
        return NumeroPagina;
    }

    public void setNumeroPagina(int NumeroPagina) {
        this.NumeroPagina = NumeroPagina;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public Date getAnoPublico() {
        return AnoPublico;
    }

    public void setAnoPublico(Date AnoPublico) {
        this.AnoPublico = AnoPublico;
    }

    public Date getDataEntrada() {
        return DataEntrada;
    }

    public void setDataEntrada(Date DataEntrada) {
        this.DataEntrada = DataEntrada;
    }

    public String getEditora() {
        return Editora;
    }

    public void setEditora(String Editora) {
        this.Editora = Editora;
    }

    public String getDescrição() {
        return Descrição;
    }

    public void setDescrição(String Descrição) {
        this.Descrição = Descrição;
    }

    public String getIdioma() {
        return Idioma;
    }

    public void setIdioma(String Idioma) {
        this.Idioma = Idioma;
    }

    public String getCondição() {
        return Condição;
    }

    public void setCondição(String Condição) {
        this.Condição = Condição;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }
    
}
