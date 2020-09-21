
package Model;

import Controller.CadastroDeProdutos;
import View.FormProdutos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ProdutosDAO {
    
    private Connection con;
    private void Conexao(){
        this.con = Database.getConnection();
    }
    
    public void Salvar(){
        Conexao();
        
        CadastroDeProdutos pro = new CadastroDeProdutos();
        
         try {
            PreparedStatement salvar = con.prepareStatement("INSERT INTO produtos (codigo_barras,descricao,valor,quantidade) VALUES (?,?,?,?)");
        
            salvar.setString(1, pro.getCodigoBarras());
            salvar.setString(2, pro.getDescricao());
            salvar.setString(3, pro.getValor());
            salvar.setString(4, pro.getQuantidade());
            
            salvar.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Registro inserido com sucesso!", "Mensagem",
                    JOptionPane.INFORMATION_MESSAGE);
        

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
