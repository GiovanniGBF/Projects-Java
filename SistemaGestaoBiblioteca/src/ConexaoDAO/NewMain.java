/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ConexaoDAO;
import java.sql.*;
/**
 *
 * @author giova
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // TODO code application logic here
        try(Connection con = Conexao.getConnection()) {
            System.out.println("Conectado sucesso");
        } catch (SQLException e) {
            System.out.println("erro " + e);
        }
    }
    
}
