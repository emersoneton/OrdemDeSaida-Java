package Model;

import Controller.CadastroDeFilial;
import View.FormFilial;
import View.FormProdutos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FilialDAO {

    private Connection con;

    private void Conexao() { // Classe de Conexão com o Banco de Dados
        this.con = Database.getConnection();
    }

    public void Salvar(CadastroDeFilial fil) {
        Conexao(); // chama a classe de conexão com o Banco de Dados

        try {
            PreparedStatement salvar = con.prepareStatement("INSERT INTO filial (bairro,cidade,cnpj,estado,razao_fantasia,"
                    + "inscricao_estadual, inscricao_municipal, numero, pais, razao_social, telefone_comercial, telefone_celular, endereco, cep"
                    + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            salvar.setString(1, fil.getBairro());
            salvar.setString(2, fil.getCidade());
            salvar.setString(3, fil.getCnpj());
            salvar.setString(4, fil.getEstado());
            salvar.setString(5, fil.getRazaoFantasia());
            salvar.setString(6, fil.getInscricaoEstadual());
            salvar.setString(7, fil.getInscricaoMunicipal());
            salvar.setString(8, fil.getNumero());
            salvar.setString(9, fil.getPais());
            salvar.setString(10, fil.getRazaoSocial());
            salvar.setString(11, fil.getTelefoneComercial());
            salvar.setString(12, fil.getTelefoneCelular());
            salvar.setString(13, fil.getEndereco());
            salvar.setString(14, fil.getCep());

            salvar.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registro inserido com sucesso!", "Mensagem",
                    JOptionPane.INFORMATION_MESSAGE);

            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar, verifique os dados e tente novamente!", "Mensagem",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void Buscar(CadastroDeFilial fil) {
        boolean validador = false;
        Conexao(); // chama a classe de conexão com o Banco de Dados

        try {
            PreparedStatement busca = con.prepareStatement("SELECT * FROM filial");

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {
                String codigo = rs.getString("codigo");

                if (codigo.equals(fil.getCodigo())) {
                    fil.setRazaoSocial(rs.getString("razao_social"));
                    fil.setRazaoFantasia(rs.getString("razao_fantasia"));
                    fil.setCnpj(rs.getString("cnpj"));
                    fil.setInscricaoEstadual(rs.getString("inscricao_estadual"));
                    fil.setInscricaoMunicipal(rs.getString("inscricao_municipal"));
                    fil.setEndereco(rs.getString("endereco"));
                    fil.setNumero(rs.getString("numero"));
                    fil.setBairro(rs.getString("bairro"));
                    fil.setCidade(rs.getString("cidade"));
                    fil.setEstado(rs.getString("estado"));
                    fil.setPais(rs.getString("pais"));
                    fil.setTelefoneComercial(rs.getString("telefone_comercial"));
                    fil.setTelefoneCelular(rs.getString("telefone_celular"));
                    fil.setCep(rs.getString("cep"));
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
            Logger.getLogger(FormFilial.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Alterar(CadastroDeFilial fil) {

        Conexao();

        try {
            PreparedStatement update = con.prepareStatement("UPDATE filial SET bairro = ?, cidade = ?, cnpj = ?, estado = ?,"
                    + "razao_fantasia = ?, inscricao_estadual = ?, inscricao_municipal = ?, numero = ?, pais = ?, razao_social = ?,"
                    + "telefone_comercial = ?, telefone_celular = ?, endereco = ?, cep = ? WHERE codigo = ?");

            update.setString(1, fil.getBairro());
            update.setString(2, fil.getCidade());
            update.setString(3, fil.getCnpj());
            update.setString(4, fil.getEstado());
            update.setString(5, fil.getRazaoFantasia());
            update.setString(6, fil.getInscricaoEstadual());
            update.setString(7, fil.getInscricaoMunicipal());
            update.setString(8, fil.getNumero());
            update.setString(9, fil.getPais());
            update.setString(10, fil.getRazaoSocial());
            update.setString(11, fil.getTelefoneComercial());
            update.setString(12, fil.getTelefoneCelular());
            update.setString(13, fil.getEndereco());
            update.setString(14, fil.getCep());

            update.setString(15, fil.getCodigo());

            update.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registro inserido com sucesso!", "Mensagem",
                    JOptionPane.INFORMATION_MESSAGE);

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(FormFilial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void BuscarCodigoDeFilial(CadastroDeFilial fil){
        Conexao();

        
        String codigo = null;
        try {
            PreparedStatement busca = con.prepareStatement("select MAX(codigo + 1) AS codigo from filial");

            ResultSet rs = busca.executeQuery();
            while (rs.next()) {
                codigo = rs.getString("codigo");
            }
            
            if(codigo != null){
                fil.setCodigo(codigo);
            }else{
                fil.setCodigo("1");
            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> BuscarEstado(FilialDAO filial) { // Cria o metodo como ArrayList
        List<String> list = new ArrayList<String>(); // crio a lista de arrei para pegar os estados do banco

        PreparedStatement stmt = null;

        Conexao(); // chama a classe de conexão com o Banco de Dados

        try (PreparedStatement busca = con.prepareStatement("select * from estado order by uf")) {

            ResultSet rs = busca.executeQuery();
            
            while (rs.next()) {
                list.add(rs.getString("uf")); // Adiciona todos os Estados a Lista de Array 
            }

            con.close();
        } catch (SQLException ex) {
            System.err.println("Erro" + ex);

        } finally {
            Database.closeConnection(con, stmt);
        }
        return list; // Retorna a Lista de Array
    }

    
    
}
