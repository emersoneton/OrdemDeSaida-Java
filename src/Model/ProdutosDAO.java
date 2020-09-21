
package Model;

import Controller.CadastroDeProdutos;
import Controller.ProdutoTableModel;
import View.FormClientes;
import View.FormProdutos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;


public class ProdutosDAO {
    
    ProdutoTableModel tableModel = new ProdutoTableModel();
    
    DefaultListModel MODELO;
    
    // CHAMA A CLASSE DE CONEXÃO
    private Connection con;
    
    // CONEXÃO
    private void Conexao(){
        this.con = Database.getConnection();
    }
    
    // INSERT
    public void Salvar(CadastroDeProdutos pro){
        Conexao();
        
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
    
    // SELECT
    public void Buscar(CadastroDeProdutos pro){
        boolean validador = false;

        Conexao(); // chama a classe de conexão com o Banco de Dados
        
        try {
            PreparedStatement busca = con.prepareStatement("SELECT * FROM produtos");
            
            ResultSet rs = busca.executeQuery();

            while(rs.next()){
                String descricao = rs.getString("descricao");
                
                if(descricao.trim().equals(pro.getDescricao())){
                    pro.setCodigo(rs.getString("codigo"));
                    pro.setCodigoBarras(rs.getString("codigo_barras"));
                    pro.setDescricao(rs.getString("descricao"));
                    pro.setValor(rs.getString("valor"));
                    pro.setQuantidade(rs.getString("quantidade"));
                    validador = true;
                    break;
                }
                
            }
            
            if (validador == false) {
                JOptionPane.showMessageDialog(null, "Não foi encontrado registro desse Produto no Banco de Dados!", "Mensagem",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(FormProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // UPDATE
    public void Alterar(CadastroDeProdutos pro){
        
        Conexao();
        
        try {
            PreparedStatement alterar = con.prepareStatement("UPDATE produtos SET codigo_barras = ?, descricao = ?, valor = ?, quantidade = ?"
                    + "WHERE codigo = ?");
            
            alterar.setString(1, pro.getCodigoBarras());
            alterar.setString(2, pro.getDescricao());
            alterar.setString(3, pro.getValor());
            alterar.setString(4, pro.getQuantidade());
            
            alterar.setString(5, pro.getCodigo());
            
            alterar.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!", "Mensagem",
                    JOptionPane.INFORMATION_MESSAGE);
             
             
             con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(FormProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void CodigoDoProduto(CadastroDeProdutos pro){
         Conexao();
        
        try {
            PreparedStatement busca = con.prepareStatement("select MAX(codigo + 1) AS codigo from produtos");
            
            ResultSet rs = busca.executeQuery();
            while(rs.next()){
                pro.setCodigo(rs.getString("codigo"));
            }
            con.close();
                    } catch (SQLException ex) {
            Logger.getLogger(FormProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ListaDePesquisa(CadastroDeProdutos pro){
        MODELO.removeAllElements();
        
        Conexao();
        
        try {
            PreparedStatement busca = con.prepareStatement("select * from produtos where descricao like '%"+pro.getDescricao()+"%' Order by descricao");
            
            ResultSet rs = busca.executeQuery();
            int v = 0;
            while(rs.next()){
                MODELO.addElement(rs.getString("descricao"));
                v++;
            }
            if(v >= 1){
          //      Lista.setVisible(true);
            }else{
          //      Lista.setVisible(false);
            }
            
           con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
