
package Model;

import Controller.CadastroDeClientes;
import Controller.ClienteTabelaModel;
import View.FormClientes;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ClientesDAO {
    
    ClienteTabelaModel tabelaClientes = new ClienteTabelaModel();
    
    private Connection con;
    
    private void Conexao(){
        this.con = Database.getConnection();
    }
    
    public void Salvar(CadastroDeClientes cli){
        Date data = new Date(System.currentTimeMillis());
      //  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = dateFormat.format(data);
        
         String sql = "INSERT INTO clientes (nome,endereco,numero,telefone,cnpj,cep,cidade,estado,pais,email,email2,cpf,bairro,data) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        con = Database.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cli.getNome());
            stmt.setString(2, cli.getEndereco());
            stmt.setString(3, cli.getNumero());
            stmt.setString(4, cli.getTelefone());
            stmt.setString(5, cli.getCnpj());
            stmt.setString(6, cli.getCep());
            stmt.setString(7, cli.getCidade());
            stmt.setString(8, cli.getEstado());
            stmt.setString(9, cli.getPais());
            stmt.setString(10, cli.getEmail());
            stmt.setString(11, cli.getEmail2());
            stmt.setString(12, cli.getCpf());
            stmt.setString(13, cli.getBairro());
            stmt.setString(14, dataFormatada);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registro inserido com sucesso!", "Mensagem",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            System.err.println("Erro" + ex);

        } finally {
            Database.closeConnection(con, stmt);
        }
    }
    
    public void Buscar(CadastroDeClientes cli){
         boolean validador = false;
        PreparedStatement stmt = null;

        this.con = Database.getConnection();

        try (PreparedStatement busca = con.prepareStatement("select * from clientes")) {

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");

                if (nome.trim().equals(cli.getNome())) {

                    cli.setCodigo(rs.getString("codigo"));
                    cli.setNome(rs.getString("nome"));
                    cli.setEndereco(rs.getString("endereco"));
                    cli.setNumero(rs.getString("numero"));
                    cli.setTelefone(rs.getString("telefone"));
                    cli.setCnpj(rs.getString("cnpj"));
                    cli.setCpf(rs.getString("cpf"));
                    cli.setCep(rs.getString("cep"));
                    cli.setCidade(rs.getString("cidade"));
                    cli.setEstado(rs.getString("estado"));
                    cli.setPais(rs.getString("pais"));
                    cli.setEmail(rs.getString("email"));
                    cli.setEmail2(rs.getString("email2"));
                    cli.setBairro(rs.getString("bairro"));
                  
                    validador = true;
                    break;
                }

            }
            
            if (validador == false) {
                JOptionPane.showMessageDialog(null, "Não foi encontrado registro desse Produto no Banco de Dados!", "Mensagem",
                    JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("Erro" + ex);

        } finally {
            Database.closeConnection(con, stmt);
        }
    }
    
    public List<CadastroDeClientes> InsereNaTabela(){ //Crio o metodo List do Cadastro de Clientes
        List<CadastroDeClientes> lista = new ArrayList<CadastroDeClientes>(); // Crio um ArrayList de Cadastro de Clientes para pegar os valores de dentro da classe
             
        Conexao();

        try {
            PreparedStatement busca = con.prepareStatement("SELECT * FROM clientes ORDER BY nome");

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {

               CadastroDeClientes cli = new CadastroDeClientes(); 
               
               cli.setNome(rs.getString("nome"));
               cli.setEndereco(rs.getString("endereco"));
               cli.setTelefone(rs.getString("telefone"));
               cli.setData(rs.getString("data"));
               
               lista.add(cli); // adiciono todos os itens a lista
            }

            con.close();
        } catch (SQLException ex) {
             System.err.println("Erro" + ex);
        }
     return lista; // retorno o valor da lista para a chamada na outra classe
    }

    
    public void BuscarCodigoDeCliente(CadastroDeClientes cli){
        Conexao();

        try {
            PreparedStatement busca = con.prepareStatement("SELECT MAX(codigo +1) AS codigo FROM clientes");

            ResultSet rs = busca.executeQuery();
            rs.next();

            cli.setCodigo(rs.getString("codigo"));

            con.close();
        } catch (SQLException ex) {
            System.err.println("Erro" + ex);
        }
    }
    
    public List<String> BuscarEstado() {
        
        List<String> lista = new ArrayList<>();
        
        PreparedStatement stmt = null;

        Conexao(); // chama a classe de conexão com o Banco de Dados

        try (PreparedStatement busca = con.prepareStatement("select * from estado order by uf")) {

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString("uf"));
            }

        } catch (SQLException ex) {
            System.err.println("Erro" + ex);

        } finally {
            Database.closeConnection(con, stmt);
        }
    return lista;
    }
    
    public void atualizar(CadastroDeClientes cli) {
        PreparedStatement stmt = null;

        
        Conexao(); // chama a classe de conexão com o Banco de Dados

        try (PreparedStatement edit = con.prepareStatement("update clientes set nome = ?, endereco = ?, numero = ?, telefone = ?,"
                + "cidade = ?, estado = ?, pais = ?, email = ?, email2 = ?, cnpj = ?, cpf = ?, cep = ?, bairro = ?"
                + "where codigo = ?")) {

            edit.setString(1, cli.getNome());
            edit.setString(2, cli.getEndereco());
            edit.setString(3, cli.getNumero());
            edit.setString(4, cli.getTelefone());
            edit.setString(5, cli.getCidade());
            edit.setString(6, cli.getEstado());
            edit.setString(7, cli.getPais());
            edit.setString(8, cli.getEmail());
            edit.setString(9, cli.getEmail2());
            edit.setString(10, cli.getCnpj());
            edit.setString(11, cli.getCpf());
            edit.setString(12, cli.getCep());
            edit.setString(13, cli.getBairro());

            edit.setString(14, cli.getCodigo());

            edit.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registro Atualizado com sucesso!", "Mensagem",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            System.err.println("Erro" + ex);

        } finally {
            Database.closeConnection(con, stmt);
        }
    }
    
    
    public List<CadastroDeClientes> GerarPDF(){
        Conexao();
        
        
        List<CadastroDeClientes> lista = new ArrayList<>();
        
        try {
            PreparedStatement busca = con.prepareStatement("select * from clientes");
            
            ResultSet rs = busca.executeQuery();
            
            while(rs.next()){
                CadastroDeClientes cli = new CadastroDeClientes();
                cli.setNome(rs.getString("nome"));
                cli.setEndereco(rs.getString("endereco"));
                cli.setNumero(rs.getString("numero"));
                cli.setBairro(rs.getString("bairro"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setCidade(rs.getString("cidade"));
                cli.setEstado(rs.getString("estado"));
                
                lista.add(cli);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    
    public List<CadastroDeClientes> ListaDePesquisa(CadastroDeClientes cli){
        List<CadastroDeClientes> lista = new ArrayList<>();
        
        Conexao();
         
        try {
            PreparedStatement busca = con.prepareStatement("select nome from clientes where nome like '%" + cli.getNome() + "%' Order by nome");

            ResultSet rs = busca.executeQuery();
            while (rs.next()) {
                CadastroDeClientes cli2 = new CadastroDeClientes();
                cli2.setNome(rs.getString("nome"));
                
                lista.add(cli2);
                
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return lista;
    }
    
    
}
