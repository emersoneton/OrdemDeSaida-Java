package Model;

import Controller.CadastroDeServico;
import View.FormOrdemDeServico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class OrdemDeServicoDAO {

    private Connection con;

    private void Conexao() {
        this.con = Database.getConnection();
    }

    public void Salvar(CadastroDeServico ser) {

        Conexao();

        try {
            PreparedStatement busca = con.prepareStatement("INSERT INTO servicos(cliente,valor,desconto,data_agendamento,complemento) "
                    + "VALUES (?,?,?,?,?)");

            busca.setString(1, ser.getCliente());
            busca.setString(2, "" + ser.getValorTotal());
            busca.setString(3, "" + ser.getDesconto());
            busca.setString(4, ser.getData());
            busca.setString(5, ser.getComplemento());

            busca.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registro inserido com sucesso!", "Mensagem",
                    JOptionPane.INFORMATION_MESSAGE);

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(OrdemDeServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SalvarItens(CadastroDeServico ser) {

        Conexao();

        try {
            PreparedStatement busca = con.prepareStatement("INSERT INTO itens_servico(cod_servico,descricao,valor,quantidade) "
                    + "VALUES (?,?,?,?)");

            for (int x = 0; x <= ser.getContador(); x++) {
                
                busca.setString(1, ""+ser.getOs());
                busca.setString(2, ser.getDescricao());
                busca.setString(3, "" + ser.getValorTotal());
                busca.setString(4, "" + ser.getQuantidade());

                busca.executeUpdate();
            }
 
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(OrdemDeServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public void BuscarOS(CadastroDeServico ser){
        Conexao();
        
        try {
            PreparedStatement busca = con.prepareStatement("SELECT MAX(codigo+1) AS codigo FROM servicos");
            
            ResultSet rs = busca.executeQuery();
            
            while(rs.next()){
                ser.setOs(Integer.parseInt(rs.getString("codigo")));
            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrdemDeServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
