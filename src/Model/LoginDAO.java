
package Model;

import Controller.VerificaLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LoginDAO {
    
    private Connection con;
    
    private void Conexao(){
        this.con = Database.getConnection();
    }
    
    public void Login(VerificaLogin log){
        String login = log.getLogin();
        String senha = log.getSenha();
        
        Conexao();
        
        try {
            PreparedStatement busca = con.prepareStatement("SELECT * FROM usuarios");
            
            ResultSet rs = busca.executeQuery();
            
            while(rs.next()){
              String login_banco = rs.getString("login");
              String senha_banco = rs.getString("senha");

              if(login.trim().equals(login_banco) && senha.trim().equals(senha_banco)){
                 log.setValidador(true);
                 log.setNome(rs.getString("nome"));
                 log.setTipo(rs.getString("tipo"));
              }
              if(login.trim().equals(login_banco)){
                  log.setValidaLogin("LOGIN");
              }
              if(senha.trim().equals(senha_banco)){
                  log.setValidaSenha("SENHA");
              }
              
            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
