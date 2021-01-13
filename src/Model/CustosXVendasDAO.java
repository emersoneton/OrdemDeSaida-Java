/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.CadastroDeServico;
import Controller.DespesasFinanceiras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emerson
 */
public class CustosXVendasDAO {
    
    Connection con;
    
    private void Conexao(){
        this.con = Database.getConnection();
    }
    
    //Buscar Custos para FormCustosXVendas
    public List<DespesasFinanceiras> BuscarCustosDeCustosXVendas(DespesasFinanceiras des) {
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
                
                // Transforma data de padrão Americano para o Brasileiro
                Date d = rs.getDate("data"); // a data 
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // formato de data desejado 
                String data = sdf.format(d); // data formatada
                
                des1.setReferente(rs.getString("referente"));
                des1.setData(data);
                des1.setValor(Double.parseDouble(rs.getString("valor")));
                
                lista.add(des1);
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DespesasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }

    
    
    
    
    
    
    //Buscar Vendas para FormCustosXVendas
    public List<CadastroDeServico> BuscarVendasDeCustosXVendas(CadastroDeServico ser, DespesasFinanceiras des) {
        Conexao();

        List<CadastroDeServico> lista = new ArrayList<>();
        

        // transformar a data de formato brasileiro para Americano
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicial = LocalDate.parse(des.getDataInicial(), formato);
        LocalDate dataFinal = LocalDate.parse(des.getDataFinal(), formato);

        try {
            PreparedStatement buscar = con.prepareStatement("SELECT * FROM `servicos` where data_agendamento BETWEEN '"+dataInicial+"' and '"+dataFinal+"' and status_os = 'FECHADO'");

            ResultSet rs = buscar.executeQuery();

            while (rs.next()) {
                CadastroDeServico ser1 = new CadastroDeServico();
                
                // Transforma data de padrão Americano para o Brasileiro
                Date d = rs.getDate("data_agendamento"); // a data 
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // formato de data desejado 
                String data = sdf.format(d); // data formatada
                
                ser1.setOs(Integer.parseInt(rs.getString("codigo")));
                ser1.setData(data);
                ser1.setValorTotal(Double.parseDouble(rs.getString("valor")));
                ser1.setDesconto(Double.parseDouble(rs.getString("desconto")));
                
                lista.add(ser1);
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DespesasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }

}
