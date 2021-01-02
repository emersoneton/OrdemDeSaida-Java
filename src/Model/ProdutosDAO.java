package Model;

import Controller.CadastroDeProdutos;
import Tabelas.ProdutoTableModelOrdemDeServico;
import View.FormClientes;
import View.FormProdutos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    ProdutoTableModelOrdemDeServico tableModel = new ProdutoTableModelOrdemDeServico();

    DefaultListModel MODELO;

    // CHAMA A CLASSE DE CONEXÃO
    private Connection con;

    // CONEXÃO
    private void Conexao() {
        this.con = Database.getConnection();
    }

    // INSERT
    public void Salvar(CadastroDeProdutos pro) {
        Conexao();
        
        String descricao = "";
        try {

            boolean validaDescricaoProduto = false;
            PreparedStatement buscar = con.prepareStatement("SELECT descricao FROM produtos WHERE descricao = '" + pro.getDescricao() + "'");

            ResultSet rs = buscar.executeQuery();

            while(rs.next()){
                descricao = rs.getString("descricao");
                if (descricao.equals(pro.getDescricao())){
                    validaDescricaoProduto = true;
                }
            }

            if (validaDescricaoProduto) {

                JOptionPane.showMessageDialog(null, "O PRODUTO *" + pro.getDescricao() + "* JÁ EXISTE CADASTRADO NO SISTEMA! \n\n"
                        + "                             FAVOR ALTERAR O NOME DO NOVO PRODUTO", "Mensagem",
                        JOptionPane.INFORMATION_MESSAGE);

            } else {

                PreparedStatement salvar = con.prepareStatement("INSERT INTO produtos (codigo_barras,descricao,valor,quantidade,situacao) VALUES (?,?,?,?,?)");

                salvar.setString(1, pro.getCodigoBarras());
                salvar.setString(2, pro.getDescricao());
                salvar.setString(3, "" + pro.getValor());
                salvar.setString(4, "" + pro.getQuantidade());
                salvar.setString(5, "" + pro.getSituacao());

                salvar.executeUpdate();

                JOptionPane.showMessageDialog(null, "Cadastro SALVO com sucesso!", "Mensagem",
                        JOptionPane.INFORMATION_MESSAGE);

            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // SELECT
    public void Buscar(CadastroDeProdutos pro) {
        boolean validador = false;

        Conexao(); // chama a classe de conexão com o Banco de Dados

        try {
            PreparedStatement busca = con.prepareStatement("SELECT * FROM produtos");

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");

                if (descricao.trim().equals(pro.getDescricao())) {
                    pro.setCodigo(rs.getString("codigo"));
                    pro.setCodigoBarras(rs.getString("codigo_barras"));
                    pro.setDescricao(rs.getString("descricao"));
                    pro.setValor(Double.parseDouble(rs.getString("valor")));
                    pro.setQuantidade(Integer.parseInt(rs.getString("quantidade")));
                    pro.setSituacao(rs.getString("situacao"));
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
    public void Alterar(CadastroDeProdutos pro) {

        Conexao();

        try {
            PreparedStatement alterar = con.prepareStatement("UPDATE produtos SET codigo_barras = ?, descricao = ?, valor = ?, quantidade = ?, situacao = ?"
                    + "WHERE codigo = ?");

            alterar.setString(1, pro.getCodigoBarras());
            alterar.setString(2, pro.getDescricao());
            alterar.setString(3, "" + pro.getValor());
            alterar.setString(4, "" + pro.getQuantidade());
            alterar.setString(5, "" + pro.getSituacao());

            alterar.setString(6, pro.getCodigo());

            alterar.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastro ALTERADO com sucesso!", "Mensagem",
                    JOptionPane.INFORMATION_MESSAGE);

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(FormProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CodigoDoProduto(CadastroDeProdutos pro) {
        Conexao();

        String codigo = null;
        try {
            PreparedStatement busca = con.prepareStatement("select MAX(codigo + 1) AS codigo from produtos");

            ResultSet rs = busca.executeQuery();
            while (rs.next()) {
                codigo = rs.getString("codigo");

            }

            if (codigo != null) {
                pro.setCodigo(codigo);
            } else {
                pro.setCodigo("1");
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<CadastroDeProdutos> ListaDePesquisa(CadastroDeProdutos pro) { // crio o metodo de arrailist passando parametros de cadastro de produtos

        List<CadastroDeProdutos> lista = new ArrayList<>(); // Crio a lista Setando o cadastro de produtos

        Conexao();

        try {
            PreparedStatement busca = con.prepareStatement("select * from produtos where descricao like '%" + pro.getDescricao() + "%' Order by descricao");

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {
                CadastroDeProdutos pro2 = new CadastroDeProdutos();

                pro2.setDescricao(rs.getString("descricao"));

                lista.add(pro2); // adiciono itens na lista do tipo CadastroDeProdutos
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<CadastroDeProdutos> InsereDadosNaTabela() {
        Conexao();

        List<CadastroDeProdutos> lista = new ArrayList<CadastroDeProdutos>();

        try {
            PreparedStatement busca = con.prepareStatement("SELECT * FROM produtos ORDER BY descricao");

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {
                CadastroDeProdutos pro = new CadastroDeProdutos();

                pro.setCodigo(rs.getString("codigo"));
                pro.setDescricao(rs.getString("descricao"));
                pro.setValor(Double.parseDouble(rs.getString("valor")));
                pro.setQuantidade(Integer.parseInt(rs.getString("quantidade")));

                lista.add(pro);
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<CadastroDeProdutos> GerarPDF() {
        List<CadastroDeProdutos> lista = new ArrayList<>();

        Conexao();

        try {
            PreparedStatement busca = con.prepareStatement("SELECT * FROM produtos ORDER BY descricao");

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {
                CadastroDeProdutos pro = new CadastroDeProdutos();

                pro.setCodigo(rs.getString("codigo"));
                pro.setDescricao(rs.getString("descricao"));
                pro.setCodigoBarras(rs.getString("codigo_barras"));
                pro.setValor(Double.parseDouble(rs.getString("valor")));
                pro.setQuantidade(Integer.parseInt(rs.getString("quantidade")));
                pro.setSituacao(rs.getString("situacao"));

                lista.add(pro);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

}
