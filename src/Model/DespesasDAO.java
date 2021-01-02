
package Model;

import Controller.DespesasFinanceiras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class DespesasDAO {
    
    private Connection con;
    
    private void Conexao(){
        this.con = Database.getConnection();
    }
    
    
    public void Salvar(DespesasFinanceiras des){
        Conexao();
        
        try {
            
            PreparedStatement salvar = con.prepareStatement("INSERT INTO despesas(referente,data,valor) VALUES (?,?,?)");
            
            salvar.setString(1, des.getReferente());
            salvar.setString(2, des.getData());
            salvar.setString(3, ""+des.getValor());
            
            salvar.executeUpdate();
            
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NÃO FOI POSSÍVEL SALVAR AS DESPESAS!\n"+ex);
            Logger.getLogger(DespesasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
