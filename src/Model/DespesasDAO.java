package Model;

import Controller.DespesasFinanceiras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DespesasDAO {

    private Connection con;

    private void Conexao() {
        this.con = Database.getConnection();
    }

    public void Salvar(DespesasFinanceiras des) {
        Conexao();

        try {

            PreparedStatement salvar = con.prepareStatement("INSERT INTO despesas(referente,data,valor) VALUES (?,?,?)");

            // transformar a data de formato brasileiro para Americano
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(des.getData(), formato);

            salvar.setString(1, des.getReferente());
            salvar.setString(2, "" + data);
            salvar.setString(3, "" + des.getValor());

            salvar.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NÃO FOI POSSÍVEL SALVAR AS DESPESAS!\n" + ex);
            Logger.getLogger(DespesasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Buscar dados para FormCustosXVendas
    public List<DespesasFinanceiras> BuscarCustosXVendas(DespesasFinanceiras des) {
        Conexao();

        List<DespesasFinanceiras> lista = new ArrayList<>();
        
        // transformar a data de formato brasileiro para Americano
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicial = LocalDate.parse(des.getDataInicial(), formato);
        LocalDate dataFinal = LocalDate.parse(des.getDataFinal(), formato);

        try {
            PreparedStatement buscar = con.prepareStatement("SELECT * FROM `despesas` where data BETWEEN '"+dataInicial+"' and '"+dataFinal+"'");

            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {
                DespesasFinanceiras des1 = new DespesasFinanceiras();
                
                des1.setReferente(rs.getString("referente"));
                des1.setData(rs.getString("data"));
                des1.setValor(Double.parseDouble(rs.getString("valor")));
                
                lista.add(des1);
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DespesasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }

}
