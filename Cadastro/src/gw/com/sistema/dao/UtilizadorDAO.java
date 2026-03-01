/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gw.com.sistema.dao;

import gw.com.sistema.jdbc.ConexaoBanco;
import gw.com.sistema.model.Utilizador;
import gw.com.sistema.view.Main;
import gw.com.sistema.view.NewJFrame1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author giova
 */
public class UtilizadorDAO {
    private Main main;
    private Connection conn;
    
    public UtilizadorDAO() {
        this.conn = new ConexaoBanco().pegarConexao();
    }
    
    public boolean Registrar(Utilizador obj) {
        try {
            String sql = "insert into usuario(nome,email,senha,sexo)" + "values(?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getEmail());
            stmt.setString(3, obj.getSenha());
            stmt.setString(4, obj.getSexo());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Registrado com sucesso");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar: " + e);
            return false;
        }
    }
    
    public boolean login(String email, String senha) {
        try {
            String sql = "select * from usuario where email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rsEmail = stmt.executeQuery();
            
            if (!rsEmail.next()) {
                JOptionPane.showMessageDialog(null, "E-mail invalido!");
                return false;
            } 
                
            String sql1 = "select * from usuario where email = ? and senha = ?";
            PreparedStatement stmtSenha = conn.prepareStatement(sql1);
            stmtSenha.setString(1, email);
            stmtSenha.setString(2, senha);
            ResultSet rsSenha = stmtSenha.executeQuery();
            
            if(!rsSenha.next()){
                JOptionPane.showMessageDialog(null, "Senha invalido!");
                return false;
            }
            
            NewJFrame1 nf = new NewJFrame1();
            nf.utilizadorLogado = rsSenha.getString("nome");
            nf.emailLogado = rsSenha.getString("email");
            JOptionPane.showMessageDialog(null, "Seja bem-vindo!");
            nf.setVisible(true);
            return true;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return false;
        }
    }
}
