package Model;

import Controller.CadastroDeClientes;
import Controller.CadastroDeFilial;
import Controller.CadastroDeServico;
import View.FormOrdemDeServico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            PreparedStatement busca = con.prepareStatement("INSERT INTO servicos(cliente,valor,desconto,data_agendamento,"
                    + "complemento,horario_agendamento,data_os,status_os) "
                    + "VALUES (?,?,?,?,?,?,?,?)");

            busca.setString(1, ser.getCliente());
            busca.setString(2, "" + ser.getValorTotal());
            busca.setString(3, "" + ser.getDesconto());
            busca.setString(4, ser.getDataAgendamento());
            busca.setString(5, ser.getComplemento());
            busca.setString(6, ser.getHorarioAgendamento());
            busca.setString(7, ser.getData());
            busca.setString(8, "" + ser.getStatus());

            busca.executeUpdate();

            JOptionPane.showMessageDialog(null, "Nota SALVA com sucesso!", "Mensagem",
                    JOptionPane.INFORMATION_MESSAGE);

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(OrdemDeServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Buscar(CadastroDeServico ser) {
        Conexao();

        try {
            PreparedStatement busca = con.prepareStatement("SELECT * FROM servicos WHERE codigo = '" + ser.getOs() + "'");

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {

                ser.setCliente(rs.getString("cliente"));
                ser.setValorTotal(Double.parseDouble(rs.getString("valor")));
                ser.setDesconto(Double.parseDouble(rs.getString("desconto")));
                ser.setDataAgendamento(rs.getString("data_agendamento"));
                ser.setHorarioAgendamento(rs.getString("horario_agendamento"));
                ser.setComplemento(rs.getString("complemento"));

            }

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

                busca.setString(1, "" + ser.getOs());
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

    public void AlterarEstoque(CadastroDeServico ser) {
        Conexao();

        try {
            PreparedStatement busca = con.prepareStatement("SELECT * FROM produtos WHERE descricao = '" + ser.getDescricao() + "'");

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {
                double quantidade = Double.parseDouble(rs.getString("quantidade"));

                PreparedStatement alterar = con.prepareStatement("UPDATE produtos SET quantidade = ? WHERE descricao = ?");

                for (int x = 0; x <= ser.getContador(); x++) {

                    quantidade = quantidade - ser.getQuantidade();

                    alterar.setString(1, "" + quantidade);

                    alterar.setString(2, ser.getDescricao());

                    alterar.executeUpdate();
                }
            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(OrdemDeServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AlterarNota(CadastroDeServico ser) {

        Conexao();

        try {
            PreparedStatement alterar = con.prepareStatement("UPDATE servicos SET cliente = ?, complemento = ?, data_agendamento = ?, horario_agendamento = ?, valor = ?, desconto = ?"
                    + "WHERE codigo = ?");

            alterar.setString(1, ser.getCliente());
            alterar.setString(2, ser.getComplemento());
            alterar.setString(3, ser.getDataAgendamento());
            alterar.setString(4, ser.getHorarioAgendamento());
            alterar.setString(5, "" + ser.getValorTotal());
            alterar.setString(6, "" + ser.getDesconto());

            alterar.setString(7, "" + ser.getOs());

            alterar.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrdemDeServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void DeletaItens(CadastroDeServico ser) {

        Conexao();

        try {
            // DELETA AS INFORMAÇÕES DA TABELA
            PreparedStatement delete = con.prepareStatement("DELETE FROM itens_servico WHERE cod_servico = ?");

            delete.setString(1, "" + ser.getOs());

            delete.executeUpdate();

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(OrdemDeServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public void BuscarOS(CadastroDeServico ser) {

        Conexao();

        String codigo = null;
        try {
            PreparedStatement busca = con.prepareStatement("SELECT MAX(codigo+1) AS codigo FROM servicos");

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {

                codigo = rs.getString("codigo");

            }

            if (codigo != null) {
                ser.setOs(Integer.parseInt(codigo));
            } else {
                ser.setOs(Integer.parseInt("1"));
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrdemDeServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InsereValorNaTabela(CadastroDeServico ser) {
        Conexao();

        try {
            PreparedStatement buscar = con.prepareStatement("SELECT descricao,valor FROM produtos");

            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                if (descricao.trim().equals(ser.getDescricao())) {
                    //  double valor = (rs.getDouble("valor"));
                    ser.setValorTotal(rs.getDouble("valor"));
                }

            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(FormOrdemDeServico.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<String> BuscarProdutos(CadastroDeServico ser) {
        List<String> lista = new ArrayList<>();

        Conexao();

        try {
            PreparedStatement buscar = con.prepareStatement("SELECT descricao,valor FROM produtos ORDER BY descricao");

            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {

                lista.add(rs.getString("descricao"));

            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(FormOrdemDeServico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<CadastroDeServico> ListaDePesquisa(CadastroDeServico ser) {
        List<CadastroDeServico> lista = new ArrayList<>();

        Conexao();

        try {
            PreparedStatement buscar = con.prepareStatement("SELECT * FROM clientes where nome like '%" + ser.getCliente() + "%' ORDER BY nome");

            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {
                CadastroDeServico ser2 = new CadastroDeServico();

                ser2.setCliente(rs.getString("nome"));

                lista.add(ser2);

            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(FormOrdemDeServico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void MostrarPesquisa() {
        /* int Linha = Lista.getSelectedIndex();
        if(Linha >=0){
            Conexao();
            try {
                PreparedStatement buscar = con.prepareStatement("SELECT * FROM clientes where nome like '%"
                        +txtPesquisaCliente.getText()+"%' ORDER BY nome LIMI"+ Linha +" , 1");
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(FormOrdemDeServico.class.getName()).log(Level.SEVERE, null, ex);
            }
        } */
    }

    public List<CadastroDeServico> ListaDePesquisaConsulta(CadastroDeServico ser) {
        List<CadastroDeServico> lista = new ArrayList<>();

        Conexao();

        try {
            PreparedStatement buscar = con.prepareStatement("SELECT * FROM clientes where nome like '%" + ser.getCliente() + "%' ORDER BY nome");

            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {
                CadastroDeServico ser2 = new CadastroDeServico();

                ser2.setCliente(rs.getString("nome"));

                lista.add(ser2);

            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(FormOrdemDeServico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void MostrarPesquisaConsulta() {
        /* int Linha = Lista.getSelectedIndex();
        if(Linha >=0){
            Conexao();
            try {
                PreparedStatement buscar = con.prepareStatement("SELECT * FROM clientes where nome like '%"
                        +txtPesquisaCliente.getText()+"%' ORDER BY nome LIMI"+ Linha +" , 1");
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(FormOrdemDeServico.class.getName()).log(Level.SEVERE, null, ex);
            }
        } */
    }

    public void BuscaDadosFilialPDF(CadastroDeFilial fil) {
        Conexao();

        try {
            PreparedStatement busca = con.prepareStatement("SELECT * FROM filial");

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {
                fil.setRazaoSocial(rs.getString("razao_social"));
                fil.setCnpj(rs.getString("cnpj"));
                fil.setInscricaoEstadual(rs.getString("inscricao_estadual"));
                fil.setCep(rs.getString("cep"));
                fil.setEndereco(rs.getString("endereco"));
                fil.setNumero(rs.getString("numero"));
                fil.setBairro(rs.getString("bairro"));
                fil.setCidade(rs.getString("cidade"));
                fil.setEstado(rs.getString("estado"));
                fil.setTelefoneComercial(rs.getString("telefone_comercial"));
                fil.setTelefoneCelular(rs.getString("telefone_celular"));
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrdemDeServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BuscarClienteParaGerarPDF(CadastroDeServico ser, CadastroDeClientes cli) {
        Conexao();

        try (PreparedStatement busca = con.prepareStatement("select * from clientes")) {

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {
                String Nome = rs.getString("nome");

                if (Nome.trim().equals(ser.getCliente())) {

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

                    break;
                }

            }
            con.close();
        } catch (SQLException ex) {
            System.err.println("Erro" + ex);

        }

    }

    public void VerificaCodigoNoBanco(CadastroDeServico ser) {

        Conexao();
        String Codigo = "" + ser.getOs();
        try {
            PreparedStatement busca = con.prepareStatement("SELECT codigo FROM servicos");

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {
                String codigo = rs.getString("codigo");

                if (codigo.trim().equals(Codigo)) {

                    ser.setValidadorNota(true);

                }
                // ser.setOs(Integer.parseInt(rs.getString("codigo")));
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrdemDeServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void VerificaCodigoDeItensNoBanco(CadastroDeServico ser) {

        Conexao();
        String Codigo = "" + ser.getOs();
        try {
            PreparedStatement busca = con.prepareStatement("SELECT * FROM itens_servico WHERE cod_servico = '" + ser.getOs() + "'");

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {
                String codigo = rs.getString("cod_servico");

                if (codigo.trim().equals(Codigo)) {

                    ser.setValidadorItens(true);

                }
                ser.setOs(Integer.parseInt(rs.getString("cod_servico")));
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrdemDeServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<CadastroDeServico> BuscarItensDoServico(CadastroDeServico ser) {

        Conexao();

        List<CadastroDeServico> lista = new ArrayList<>();

        try {
            PreparedStatement busca = con.prepareStatement("SELECT * FROM itens_servico WHERE cod_servico = '" + ser.getOs() + "'");

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {
                CadastroDeServico ser1 = new CadastroDeServico();

                ser1.setDescricao(rs.getString("descricao"));
                ser1.setValorTotal(Double.parseDouble(rs.getString("valor")));
                ser1.setQuantidade(Integer.parseInt(rs.getString("quantidade")));

                lista.add(ser1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrdemDeServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<CadastroDeServico> BuscarNotasDeServico(CadastroDeServico ser) { // Busca todas as notas de Serviços
        List<CadastroDeServico> lista = new ArrayList<>();

        Conexao();

        try {

            if (ser.getClicked() == "OS") { // Busca por OS

                PreparedStatement busca = con.prepareStatement("SELECT * FROM servicos WHERE codigo = ? ORDER BY codigo");

                busca.setString(1, "" + ser.getOs());

                ResultSet rs = busca.executeQuery();

                while (rs.next()) {

                    CadastroDeServico ser1 = new CadastroDeServico();

                    ser1.setOs(Integer.parseInt(rs.getString("codigo")));
                    ser1.setCliente(rs.getString("cliente"));
                    ser1.setDataAgendamento(rs.getString("data_agendamento"));
                    ser1.setData(rs.getString("data_os"));
                    ser1.setStatus((rs.getString("status_os")));
                    ser1.setHorarioAgendamento((rs.getString("horario_agendamento")));

                    lista.add(ser1);

                }

            } // Fim da Busca por OS

            if (ser.getClicked() == "CLIENTE") { // Busca por CLIENTE

                PreparedStatement busca = con.prepareStatement("SELECT * FROM servicos WHERE cliente = ? ORDER BY codigo");

                busca.setString(1, "" + ser.getCliente());

                ResultSet rs = busca.executeQuery();

                while (rs.next()) {

                    CadastroDeServico ser1 = new CadastroDeServico();

                    ser1.setOs(Integer.parseInt(rs.getString("codigo")));
                    ser1.setCliente(rs.getString("cliente"));
                    ser1.setDataAgendamento(rs.getString("data_agendamento"));
                    ser1.setData(rs.getString("data_os"));
                    ser1.setStatus((rs.getString("status_os")));
                    ser1.setHorarioAgendamento((rs.getString("horario_agendamento")));

                    lista.add(ser1);

                }

            } // Fim da Busca por CLIENTE

            if (ser.getClicked() == "ABERTO") { // Busca por Notas em Aberto

                PreparedStatement busca = con.prepareStatement("SELECT * FROM servicos WHERE status_os = ? ORDER BY codigo");

                busca.setString(1, "" + ser.getStatus());

                ResultSet rs = busca.executeQuery();

                while (rs.next()) {

                    CadastroDeServico ser1 = new CadastroDeServico();

                    ser1.setOs(Integer.parseInt(rs.getString("codigo")));
                    ser1.setCliente(rs.getString("cliente"));
                    ser1.setDataAgendamento(rs.getString("data_agendamento"));
                    ser1.setData(rs.getString("data_os"));
                    ser1.setStatus((rs.getString("status_os")));
                    ser1.setHorarioAgendamento((rs.getString("horario_agendamento")));

                    lista.add(ser1);

                }

            } // Fim da Busca por CLIENTE em ABERTO

            if (ser.getClicked() == "FECHADO") { // Busca por Notas em Fechado

                PreparedStatement busca = con.prepareStatement("SELECT * FROM servicos WHERE status_os = ? ORDER BY codigo");

                busca.setString(1, "" + ser.getStatus());

                ResultSet rs = busca.executeQuery();

                while (rs.next()) {

                    CadastroDeServico ser1 = new CadastroDeServico();

                    ser1.setOs(Integer.parseInt(rs.getString("codigo")));
                    ser1.setCliente(rs.getString("cliente"));
                    ser1.setDataAgendamento(rs.getString("data_agendamento"));
                    ser1.setData(rs.getString("data_os"));
                    ser1.setStatus((rs.getString("status_os")));
                    ser1.setHorarioAgendamento((rs.getString("horario_agendamento")));

                    lista.add(ser1);

                }

            } // Fim da Busca por CLIENTE em Fechado
            
            if (ser.getClicked() == "CANCELADO") { // Busca por Cancelados

               PreparedStatement busca = con.prepareStatement("SELECT * FROM servicos WHERE status_os = ? ORDER BY codigo");

                busca.setString(1, "" + ser.getStatus());

                ResultSet rs = busca.executeQuery();

                while (rs.next()) {

                    CadastroDeServico ser1 = new CadastroDeServico();

                    ser1.setOs(Integer.parseInt(rs.getString("codigo")));
                    ser1.setCliente(rs.getString("cliente"));
                    ser1.setDataAgendamento(rs.getString("data_agendamento"));
                    ser1.setData(rs.getString("data_os"));
                    ser1.setStatus((rs.getString("status_os")));
                    ser1.setHorarioAgendamento((rs.getString("horario_agendamento")));

                    lista.add(ser1);

                }

            }// Fim da Busca por Cancelados

            if (ser.getClicked() == "TODOS") { // Busca por TODOS

                PreparedStatement busca = con.prepareStatement("SELECT * FROM servicos ORDER BY codigo");

                ResultSet rs = busca.executeQuery();

                while (rs.next()) {

                    CadastroDeServico ser1 = new CadastroDeServico();

                    ser1.setOs(Integer.parseInt(rs.getString("codigo")));
                    ser1.setCliente(rs.getString("cliente"));
                    ser1.setDataAgendamento(rs.getString("data_agendamento"));
                    ser1.setData(rs.getString("data_os"));
                    ser1.setStatus((rs.getString("status_os")));
                    ser1.setHorarioAgendamento((rs.getString("horario_agendamento")));

                    lista.add(ser1);

                }

            }// Fim da Busca por TODOS

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrdemDeServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public void SalvarStatusDaNota(CadastroDeServico ser) { // salvar o status de aberto para fechado
        Conexao();

        try {
            PreparedStatement alterar = con.prepareStatement("UPDATE servicos SET status_os = ? where codigo = ?");

            alterar.setString(1, ser.getStatus());

            alterar.setString(2, "" + ser.getOs());

            alterar.executeUpdate();


            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrdemDeServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
