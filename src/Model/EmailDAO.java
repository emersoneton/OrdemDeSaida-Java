
package Model;

import Controller.CadastroEmail;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Emerson
 */
public class EmailDAO {
    
     // chama o metodo de conexão
    private Connection con;

    // cria a conexão
    private void Conexao() {
        this.con = Database.getConnection();
    }
    
    public void Salvar(CadastroEmail cad){
        Conexao();
        
        byte[] bytesDaSenha = null;
        
        try {
            
            bytesDaSenha = cad.getSenha().getBytes("UTF-8");
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            PreparedStatement salvar = con.prepareStatement("INSERT INTO email(nome,email,senha,email_saida,aceita_ssl,aceita_tls,porta,email_padrao) VALUES(?,?,?,?,?,?,?,?)");
            
            salvar.setString(1, cad.getNome());
            salvar.setString(2, cad.getEmail());
            salvar.setString(3, ""+cad.getSenha());
            salvar.setString(4, cad.getEmailSaida());
            salvar.setString(5, ""+cad.getAceitaSsl());
            salvar.setString(6, ""+cad.getAceitaTls());
            salvar.setString(7, ""+cad.getPorta());
            salvar.setString(8, ""+cad.getEmailDefinePadrao());
            
            salvar.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "E-MAIL SALVO COM SUCESSO!");
            
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FALHA AO ENVIAR O E-MAIL, CONFIRA TODOS OS DADOS!");
        }
    }
    
    
    public void BuscarEmailParaEnviarEmail(CadastroEmail cad){
        Conexao();
        

        
        try {
            PreparedStatement busca = con.prepareStatement("SELECT * FROM email WHERE email_padrao = '1'");
            
            ResultSet rs = busca.executeQuery();
            
            while(rs.next()){
                cad.setNome(rs.getString("nome"));
                cad.setEmail(rs.getString("email"));
                cad.setSenha(rs.getString("senha"));
                cad.setEmailSaida(rs.getString("email_saida"));
                cad.setAceitaSsl(Integer.parseInt(rs.getString("aceita_ssl")));
                cad.setAceitaTls(Integer.parseInt(rs.getString("aceita_tls")));
                cad.setPorta(Integer.parseInt(rs.getString("porta")));;
                cad.setEmailDefinePadrao(Integer.parseInt(rs.getString("email_padrao")));
            }
            
            try {
                byte[] bytesDaSenha = cad.getSenha().getBytes("UTF-8");
                
                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    
                    byte[] digest = md.digest(bytesDaSenha);
                    
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
}
