/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cadastro;

import ConexaoDAO.Conexao;
import java.awt.image.BufferedImage;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sistemagestaobiblioteca.ATENDIMENTO;
import sistemagestaobiblioteca.Principal;

/**
 *
 * @author Bobito
 */
public class Enviar {

    Connection conn = null;
    PreparedStatement stmt = null;

    public void inserir(LIVROS l) throws ClassNotFoundException {

        try {
            // Abrir conexão com o banco
            conn = Conexao.getConnection();

            // SQL de inserção (apenas a coluna titulo)
            String sql = "INSERT INTO livros (Titulo, Autor, AnoPublico, Editora, Idioma,Descrição, Condição, DataEntrada, Genero, NumeroPagina) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)";
            stmt = conn.prepareStatement(sql);

            // Preencher parâmetro
            stmt.setString(1, l.getTitulo());
            stmt.setString(2, l.getAutor());
            stmt.setDate(3, (Date) l.getAnoPublico());
            stmt.setString(4, l.getEditora());
            stmt.setString(5, l.getIdioma());
            stmt.setString(6, l.getDescrição());
            stmt.setString(7, l.getCondição());
            stmt.setDate(8, (Date) l.getDataEntrada());
            stmt.setString(9, l.getGenero());
            stmt.setInt(10, l.getNumeroPagina());
            // Executar
            stmt.executeUpdate();
            ATENDIMENTO A = new ATENDIMENTO(null, true);
            A.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
    
    

    
    
    
    
    

    
    
    
    
    
    
    public boolean Atualizar(LIVROS l) throws ClassNotFoundException {

        try {
            conn = Conexao.getConnection();
            // SQL de atuliza 
           
            String sql = " UPDATE livros"
                    + "    SET Titulo = ?,"
                    + "    Autor = ?,"
                    + "    AnoPublico = ?,"
                    + "    Editora = ?,"
                    + "    Idioma = ?,"
                    + "    Descrição = ?,"
                    + "    Condição = ?,"
                    + "    DataEntrada = ?,"
                    + "    Genero = ?,"
                    + "    NumeroPagina= ? WHERE id = ?;";
            stmt = conn.prepareStatement(sql);

            // Preencher parâmetro
            stmt.setString(1, l.getTitulo());
            stmt.setString(2, l.getAutor());
            stmt.setDate(3, (Date) l.getAnoPublico());
            stmt.setString(4, l.getEditora());
            stmt.setString(5, l.getIdioma());
            stmt.setString(6, l.getDescrição());
            stmt.setString(7, l.getCondição());
            stmt.setDate(8, (Date) l.getDataEntrada());
            stmt.setString(9, l.getGenero());
            stmt.setInt(10, l.getNumeroPagina());
            stmt.setInt(11, l.getId());

            // Executar
            return stmt.executeUpdate() > 0;
             
            
            
        } catch (SQLException e) {
            System.out.println("Falha ao atualizar " + e.getMessage());
            return false; 
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    /**
     *
     * @return
     */
    public List<LIVROS> Lista() throws ClassNotFoundException {
        List<LIVROS> li = new ArrayList();
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = conn.prepareStatement("SELECT * FROM livros");
            rs = stmt.executeQuery();

            while (rs.next()) {
                LIVROS l = new LIVROS();
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("Titulo"));
                l.setAutor(rs.getString("Autor"));
                l.setAnoPublico(rs.getDate("AnoPublico"));
                l.setEditora(rs.getString("Editora"));
                l.setIdioma(rs.getString("Idioma"));
                l.setDescrição(rs.getString("Descrição"));
                l.setCondição(rs.getString("Condição"));
                l.setDataEntrada(rs.getDate("DataEntrada"));
                l.setGenero(rs.getString("Genero"));
                l.setNumeroPagina(rs.getInt("NumeroPagina"));
                li.add(l);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Enviar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();

                } catch (Exception e) {
                    JOptionPane.showConfirmDialog(null, e);
                }
            }

        }

        return li;

    }

    /**
     *
     * @return
     */
    public List<Aluno> Lista1() throws ClassNotFoundException {
        List<Aluno> li = new ArrayList();
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = conn.prepareStatement("SELECT * FROM livros");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno l = new Aluno();
                l.setId(rs.getInt("id"));
                l.setNome(rs.getString("Nome"));
                l.setApelido(rs.getString("Apelido"));
                l.setNomeLvro (rs.getString("Nomelvro"));//(BufferedImage) rs.getBlob("Imagem"))
                l.setSala(rs.getString("Sala"));
                l.setNumero(rs.getInt("Numero"));
                l.setTurma(rs.getInt("Turma"));
                l.setImagem((BufferedImage) rs.getBlob("Imagem"));
                li.add(l);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Enviar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();

                } catch (Exception e) {
                    JOptionPane.showConfirmDialog(null, e);
                }
            }

        }

        return li;

    }
  

}
