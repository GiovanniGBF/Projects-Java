/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import javax.swing.JOptionPane;
import jdbc.Conexao;
import model.Utilizador;
import view.AreaTrabalho;
import view.Main;
/**
 *
 * @author giova
 */
public class UtilizadorDAO {
    private Main main;
    private final Connection conn;
    
    public UtilizadorDAO() throws ClassNotFoundException {
        this.conn = Conexao.getConnection();
    }
    
    public boolean Registrar(Utilizador obj) {
        try {
            String sql = "insert into utilizador(nome,email,senha,sexo)" + "values(?,?,?,?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getEmail());
                stmt.setString(3, obj.getSenha());
                stmt.setString(4, obj.getSexo());
                stmt.execute();
            }
            JOptionPane.showMessageDialog(null, "Registrado com sucesso");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar: " + e);
            return false;
        }
    }
    
    public boolean login(String email, String senha) {
        try {
            String sql = "select * from utilizador where email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rsEmail = stmt.executeQuery();
            
            if (!rsEmail.next()) {
                JOptionPane.showMessageDialog(null, "E-mail invalido!");
                return false;
            } 
                
            String sql1 = "select * from utilizador where email = ? and senha = ?";
            PreparedStatement stmtSenha = conn.prepareStatement(sql1);
            stmtSenha.setString(1, email);
            stmtSenha.setString(2, senha);
            ResultSet rsSenha = stmtSenha.executeQuery();
            
            if(!rsSenha.next()){
                JOptionPane.showMessageDialog(null, "Senha invalido!");
                return false;
            }
            
            AreaTrabalho at = new AreaTrabalho();
            at.utilizadorLogado = rsSenha.getString("nome");
            at.emailLogado = rsSenha.getString("email");
            JOptionPane.showMessageDialog(null, "Seja bem-vindo!");
            at.setVisible(true);
            return true;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return false;
        }
    }
}
