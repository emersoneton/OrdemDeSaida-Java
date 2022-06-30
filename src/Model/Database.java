package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Database {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/os";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {

        try {
            Class.forName(DRIVER);

            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            //Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "* ERRO DE CONEXÃO COM O BANCO DE DADOS *\n\n"
                    + "  CERTIFIQUE-SE QUE A CONEXÃO FOI INICIADA \n\n"
                    + "    FECHE A APLICAÇÃO E INICIE NOVAMENTE!");
            throw new RuntimeException("Erro na Conexão", ex);

        }

    }

    
 public static void closeConnection(Connection con){   
     if (con != null) {         
        try {
             con.close();
         } catch (SQLException ex) {
            // Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
             System.err.println("Erro: "+ex);
         }        
     }  
 }
    
 public static void closeConnection(Connection con, PreparedStatement stmt){   
     if (stmt != null) {    
         try {
             con.close();
         } catch (SQLException ex) {
             System.err.println("Erro: "+ex);
         }       
     } 
     closeConnection(con);
 }   
 
 public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){   
     if (stmt != null) {    
         try {
             rs.close();
         } catch (SQLException ex) {
             System.err.println("Erro: "+ex);
         }       
     } 
     closeConnection(con, stmt);
 }   


    public void executaSQL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void executaSQL(String select__from_clientes_where_) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
     }
