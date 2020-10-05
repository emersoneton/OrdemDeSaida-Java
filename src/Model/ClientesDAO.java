package Model;

import Controller.CadastroDeClientes;
import Controller.ClienteTabelaModel;
import View.FormClientes;
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

    private void Conexao() {
        this.con = Database.getConnection();
    }

    public void Salvar(CadastroDeClientes cli) {
        Date data = new Date(System.currentTimeMillis());
        //  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = dateFormat.format(data);

        String sql = "INSERT INTO clientes (nome,endereco,numero,telefone, telefone_celular ,cnpj,cep,cidade,estado,pais,email,email2,cpf,bairro,data, situacao) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        con = Database.getConnection();

        PreparedStatement stmt = null;

        try {

            PreparedStatement busca = con.prepareStatement("select nome from clientes");

            ResultSet rs = busca.executeQuery();

            String nome = "";
            while (rs.next()) {
                nome = rs.getString("nome");
            }

            if (nome.trim().equals(cli.getNome())) {
                
               JOptionPane.showMessageDialog(null, "O CLIENTE *" + cli.getNome() + "* JÁ EXISTE CADASTRADO NO SISTEMA! \n\n"
                        + "                             FAVOR ALTERAR O NOME DO NOVO CLIENTE", "Mensagem",
                        JOptionPane.INFORMATION_MESSAGE);
                
            } else {
                
                stmt = con.prepareStatement(sql);
                stmt.setString(1, cli.getNome());
                stmt.setString(2, cli.getEndereco());
                stmt.setString(3, cli.getNumero());
                stmt.setString(4, cli.getTelefone());
                stmt.setString(5, cli.getTelefoneCelular());
                stmt.setString(6, cli.getCnpj());
                stmt.setString(7, cli.getCep());
                stmt.setString(8, cli.getCidade());
                stmt.setString(9, cli.getEstado());
                stmt.setString(10, cli.getPais());
                stmt.setString(11, cli.getEmail());
                stmt.setString(12, cli.getEmail2());
                stmt.setString(13, cli.getCpf());
                stmt.setString(14, cli.getBairro());
                stmt.setString(15, dataFormatada);
                stmt.setString(16, cli.getSituacao());

                stmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Cadastro SALVO com sucesso!", "Mensagem",
                        JOptionPane.INFORMATION_MESSAGE);
                
            }

        } catch (SQLException ex) {
            System.err.println("Erro" + ex);

        } finally {
            Database.closeConnection(con, stmt);
        }
    }

    public void Buscar(CadastroDeClientes cli) {
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
                    cli.setTelefoneCelular(rs.getString("telefone_celular"));
                    cli.setCnpj(rs.getString("cnpj"));
                    cli.setCpf(rs.getString("cpf"));
                    cli.setCep(rs.getString("cep"));
                    cli.setCidade(rs.getString("cidade"));
                    cli.setEstado(rs.getString("estado"));
                    cli.setPais(rs.getString("pais"));
                    cli.setEmail(rs.getString("email"));
                    cli.setEmail2(rs.getString("email2"));
                    cli.setBairro(rs.getString("bairro"));
                    cli.setSituacao(rs.getString("situacao"));

                    validador = true;
                    break;
                }

            }

            if (validador == false) {
                JOptionPane.showMessageDialog(null, "Não foi encontrado registro do cliente "+cli.getNome()+" informado!", "Mensagem",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            System.err.println("Erro" + ex);

        } finally {
            Database.closeConnection(con, stmt);
        }
    }

    public List<CadastroDeClientes> InsereNaTabela() { //Crio o metodo List do Cadastro de Clientes
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

    public void BuscarCodigoDeCliente(CadastroDeClientes cli) {
        Conexao();

        String codigo = null;
        try {
            PreparedStatement busca = con.prepareStatement("SELECT MAX(codigo +1) AS codigo FROM clientes");

            ResultSet rs = busca.executeQuery();
            rs.next();

            codigo = rs.getString("codigo");

            if (codigo != null) {
                cli.setCodigo(codigo);
            } else {
                cli.setCodigo("1");
            }

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

        try (PreparedStatement edit = con.prepareStatement("update clientes set nome = ?, endereco = ?, numero = ?, telefone = ?, telefone_celular = ?,"
                + "cidade = ?, estado = ?, pais = ?, email = ?, email2 = ?, cnpj = ?, cpf = ?, cep = ?, bairro = ?, situacao = ?"
                + "where codigo = ?")) {

            edit.setString(1, cli.getNome());
            edit.setString(2, cli.getEndereco());
            edit.setString(3, cli.getNumero());
            edit.setString(4, cli.getTelefone());
            edit.setString(5, cli.getTelefoneCelular());
            edit.setString(6, cli.getCidade());
            edit.setString(7, cli.getEstado());
            edit.setString(8, cli.getPais());
            edit.setString(9, cli.getEmail());
            edit.setString(10, cli.getEmail2());
            edit.setString(11, cli.getCnpj());
            edit.setString(12, cli.getCpf());
            edit.setString(13, cli.getCep());
            edit.setString(14, cli.getBairro());
            edit.setString(15, cli.getSituacao());

            edit.setString(16, cli.getCodigo());

            edit.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastro Atualizado com sucesso!", "Mensagem",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            System.err.println("Erro" + ex);

        } finally {
            Database.closeConnection(con, stmt);
        }
    }

    public List<CadastroDeClientes> GerarPDF() {
        Conexao();

        List<CadastroDeClientes> lista = new ArrayList<>();

        try {
            PreparedStatement busca = con.prepareStatement("select * from clientes");

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {
                CadastroDeClientes cli = new CadastroDeClientes();
                cli.setNome(rs.getString("nome"));
                cli.setEndereco(rs.getString("endereco"));
                cli.setNumero(rs.getString("numero"));
                cli.setBairro(rs.getString("bairro"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setTelefoneCelular(rs.getString("telefone_celular"));
                cli.setTelefoneCelular(rs.getString("telefone_celular"));
                cli.setCidade(rs.getString("cidade"));
                cli.setEstado(rs.getString("estado"));
                cli.setSituacao(rs.getString("situacao"));

                lista.add(cli);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<CadastroDeClientes> ListaDePesquisa(CadastroDeClientes cli) { //Cria o Metodo com o ArrayList e recebe os valores por parametro do Cadastro de cliente
        List<CadastroDeClientes> lista = new ArrayList<>();

        Conexao();

        try {
            PreparedStatement busca = con.prepareStatement("select nome from clientes where nome like '%" + cli.getNome() + "%' Order by nome");

            ResultSet rs = busca.executeQuery();
            while (rs.next()) {
                CadastroDeClientes cli2 = new CadastroDeClientes();

                cli2.setNome(rs.getString("nome"));

                lista.add(cli2); // Adiciona a lista as informações do Cadastro de Clientes

            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormClientes.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

}
