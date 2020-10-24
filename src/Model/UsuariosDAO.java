package Model;

import Controller.CadastroDeUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UsuariosDAO {

    // chama o metodo de conexão
    private Connection con;

    // cria a conexão
    private void Conexao() {
        this.con = Database.getConnection();
    }

    // Salvar Cadastros de Usuários
    public void Salvar(CadastroDeUsuario usu) {
        Conexao();

        String login = "";
        try {
            boolean validaNomeUsuario = false;
            PreparedStatement busca = con.prepareStatement("select * from usuarios where login = '" + usu.getLogin() + "'");

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {
                login = rs.getString("login");
                if (login.trim().equals(usu.getLogin())){
                    validaNomeUsuario = true;
                }
            }

            if (validaNomeUsuario) {
                JOptionPane.showMessageDialog(null, "O login "+ usu.getLogin() +" já existe cadastrado na base de dados!");
            } else {
                PreparedStatement salvar = con.prepareStatement("INSERT INTO usuarios(nome, login, senha, tipo) VALUES(?,?,?,?)");

                salvar.setString(1, usu.getNome());
                salvar.setString(2, usu.getLogin());
                salvar.setString(3, usu.getSenha());
                salvar.setString(4, usu.getTipo());

                salvar.executeUpdate();

                usu.setValida(true);
                JOptionPane.showMessageDialog(null, "Cadastro de usuário SALVO com sucesso!");
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Buscar Cadastro de Usuários
    public void Buscar(CadastroDeUsuario usu) {
        Conexao();

        boolean valida = false;
        try {
            PreparedStatement busca = con.prepareStatement("SELECT * FROM usuarios");

            ResultSet rs = busca.executeQuery();

            while (rs.next()) {
                String login = rs.getString("login");

                if (login.trim().equals(usu.getLogin())) {
                    usu.setNome(rs.getString("nome"));
                    usu.setTipo((rs.getString("tipo")));
                    usu.setSenha((rs.getString("senha")));

                    valida = true;
                }
            }

            if (valida == false) {
                JOptionPane.showMessageDialog(null, "Não foi encontrado registros para o LOGIN informado!");
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    // Alterar Cadastro de Usuarios
    public void Alterar(CadastroDeUsuario usu){
        Conexao();

        try {

                PreparedStatement salvar = con.prepareStatement("UPDATE usuarios SET nome = ?, login = ?, senha = ?, tipo = ? WHERE login = ?");

                salvar.setString(1, usu.getNome());
                salvar.setString(2, usu.getLogin());
                salvar.setString(3, usu.getSenha());
                salvar.setString(4, usu.getTipo());
                
                salvar.setString(5, usu.getLogin());

                salvar.executeUpdate();

                usu.setValida(true);
                JOptionPane.showMessageDialog(null, "Cadastro de usuário ALTERADO com sucesso!");
            

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // Deletar Cadastro de usuarios
    public void Deletar(CadastroDeUsuario usu){
         Conexao();

        try {

                PreparedStatement salvar = con.prepareStatement("DELETE FROM usuarios WHERE login = ?");

                salvar.setString(1, usu.getLogin());

                salvar.executeUpdate();

                usu.setValida(true);
                JOptionPane.showMessageDialog(null, "Cadastro de usuário EXCLUIDO com sucesso!");
            

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public List<CadastroDeUsuario> BuscarUsuarios(){
        
        List<CadastroDeUsuario> lista = new ArrayList<>();
        Conexao();
        
        try {
            PreparedStatement busca = con.prepareStatement("SELECT * FROM usuarios");
            
            ResultSet rs = busca.executeQuery();
            
            while(rs.next()){
                CadastroDeUsuario usu = new CadastroDeUsuario();
                
                usu.setNome(rs.getString("nome"));
                usu.setLogin(rs.getString("login"));
                usu.setTipo(rs.getString("tipo"));
                
                lista.add(usu);
                
            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
}
