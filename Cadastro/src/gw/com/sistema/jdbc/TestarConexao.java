/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gw.com.sistema.jdbc;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author giova
 */
public class TestarConexao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            new ConexaoBanco().pegarConexao();
           JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
        } catch (HeadlessException erro) {
            JOptionPane.showMessageDialog(null,"Erro: " + erro.getMessage());
        }
    }
    
}
