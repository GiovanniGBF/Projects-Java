/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gw.com.sistema.dao;

import gw.com.sistema.jdbc.ConexaoBanco;
import gw.com.sistema.model.Aluno;
import gw.com.sistema.model.Utilizador;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author giova
 */
public class AlunoDAO {
    private final Connection conn;
    
    
    public AlunoDAO(){
        this.conn = new ConexaoBanco().pegarConexao();
    }
    
    public void Salvar(Aluno obj){
        try {
            String sql = "insert into aluno(nome, codigo, email, curso, nivel, contacto)" + "values(?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setInt(2, obj.getCodigo());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getCurso());
            stmt.setString(5, obj.getNivel());
            stmt.setString(6, obj.getContacto());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Aluno salvo com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar salvar o aluno" + erro);
        }
 
    }
    
    public void Editar(Aluno obj){
        try {
            String sql = "update aluno set nome = ?, codigo = ?, email = ?, curso = ?, nivel = ?, contacto = ?"
                    + "where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setInt(2, obj.getCodigo());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getCurso());
            stmt.setString(5, obj.getNivel());
            stmt.setString(6, obj.getContacto());
            stmt.setInt(7, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Aluno editado com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar editar o aluno" + erro);
        }
 
    }
    
    public void Excluir(Aluno obj) {
        try {
            String sql = "delete from aluno where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Aluno excluido com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir aluno!");
        }
    }
    
    public Aluno Buscar_Aluno(String nome) {
        try {
            String sql = "Select * from aluno where nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Aluno obj = new Aluno();
            if(rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCodigo(rs.getInt("codigo"));
                obj.setEmail(rs.getString("email"));
                obj.setCurso(rs.getString("curso"));
                obj.setNivel(rs.getString("nivel"));
                obj.setContacto(rs.getString("contacto"));
            } 
            return obj;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar Aluno! " + erro);
        }
        return null;
    }
    
    public List<Aluno>listar() {
        List<Aluno> lista = new ArrayList<>();
        try {
            String sql = "select * from aluno";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                Aluno obj = new Aluno();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCodigo(rs.getInt("codigo"));
                obj.setEmail(rs.getString("email"));
                obj.setCurso(rs.getString("curso"));
                obj.setNivel(rs.getString("nivel"));
                obj.setContacto(rs.getString("contacto"));
                lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao criar a lista: " + erro);
        }
        return null;
    }
    
    public List<Aluno>filtrar(String nome, int codigo) {
        List<Aluno> lista = new ArrayList<>();
        try {
            String sql = "select * from aluno where nome like ? or codigo like ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setInt(2, codigo);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                Aluno obj = new Aluno();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCodigo(rs.getInt("codigo"));
                obj.setEmail(rs.getString("email"));
                obj.setCurso(rs.getString("curso"));
                obj.setNivel(rs.getString("nivel"));
                obj.setContacto(rs.getString("contacto"));
                lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao criar a lista: " + erro);
        }
        return null;
    }
}
