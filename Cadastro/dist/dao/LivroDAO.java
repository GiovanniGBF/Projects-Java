/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jdbc.Conexao;
import model.Livro;
/**
 *
 * @author giova
 */
public class LivroDAO {
    private final Connection conn;
    
    public LivroDAO() throws ClassNotFoundException {
        this.conn = Conexao.getConnection();
    }
    
    public void Salvar(Livro obj) {
        PreparedStatement stmt = null;
        String sql = "insert into livro(titulo, autor, ano_publicacao, genero, "
                    + "paginas, idioma, data_cadastro, quantidade, editora)" + " values(?,?,?,?,?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getTitulo());
            stmt.setString(2, obj.getAutor());
            stmt.setInt(3, obj.getAno_publicacao());
            stmt.setString(4, obj.getGenero());
            stmt.setInt(5, obj.getPaginas());
            stmt.setString(6, obj.getIdioma());
            stmt.setDate(7, obj.getData_cadastro());
            stmt.setInt(8, obj.getQuantidade());
            stmt.setString(9, obj.getEditora());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Livro salvo com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e);
                }
            }
        }
    }
    
    public void Eliminar(Livro obj) {
        PreparedStatement stmt = null;
        String sql = "delete from livro where id = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Livro eliminado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e);
                }
            }    
        }
    }
    
    public List<Livro> Listar() {
        List<Livro> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select * from livro";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Livro l = new Livro();
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setEditora(rs.getString("editora"));
                l.setGenero(rs.getString("genero"));
                l.setIdioma(rs.getString("idioma"));
                l.setAno_publicacao(rs.getInt("ano_publicacao"));
                l.setQuantidade(rs.getInt("quantidade"));
                lista.add(l);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao criar a lista: " + erro);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e);
            }
        }
        return null;
    }
    
    public Livro buscarPorId(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select * from livro where id = ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            Livro l = new Livro();
            if (rs.next()) {
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setAno_publicacao(rs.getInt("ano_publicacao"));
                l.setGenero(rs.getString("genero"));
                l.setPaginas(rs.getInt("paginas"));
                l.setIdioma(rs.getString("idioma"));
                l.setData_cadastro(rs.getDate("data_cadastro"));
                l.setQuantidade(rs.getInt("quantidade"));
                l.setEditora(rs.getString("editora"));
            }
            return l;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e);
            }
        }
        return null;
    }
    
    public void atualizar(Livro l) {
        PreparedStatement stmt = null;
        String sql = "update livro set titulo=?, autor=?, ano_publicacao=?, "
                + "genero=?, paginas=?, idioma=?, quantidade=?, editora=? where id=?";

        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, l.getTitulo());
            stmt.setString(2, l.getAutor());
            stmt.setInt(3, l.getAno_publicacao());
            stmt.setString(4, l.getGenero());
            stmt.setInt(5, l.getPaginas());
            stmt.setString(6, l.getIdioma());
            stmt.setInt(7, l.getQuantidade());
            stmt.setString(8, l.getEditora());
            stmt.setInt(9, l.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Livro atualizado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro: " + e);
                }
            }
        }
    }
}
