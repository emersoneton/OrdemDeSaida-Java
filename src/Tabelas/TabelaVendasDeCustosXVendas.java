/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabelas;

import Controller.CadastroDeServico;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Emerson
 */
public class TabelaVendasDeCustosXVendas extends AbstractTableModel{

    private List<CadastroDeServico> dados = new ArrayList<>();      // Arrai do dados da classe de Cadastro de Serviços que vão ser informados na tabela
    private String[] colunas = {"OS","Data","Valor","Desconto","Total"}; // Criação das Colunas no Jtable
    
    @Override
    public String getColumnName(int culumn) {
       return colunas[culumn];
    }
    

    @Override
    public int getRowCount() {
        return dados.size(); 
    }

    @Override
    public int getColumnCount() {
       return colunas.length; 
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
       switch(coluna){
           case 0:
               return dados.get(linha).getOs();  // Insere os dados na Tabela
           case 1:
               return dados.get(linha).getData();  // Insere os dados na Tabela
           case 2:
               return dados.get(linha).getValorTotal();      // Insere os dados na Tabela
           case 3:
               return dados.get(linha).getDesconto();      // Insere os dados na Tabela  
           case 4:
               return dados.get(linha).getValorTotalMenosDescontos();      // Insere os dados na Tabela      
       }
       return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
         switch(coluna){
           case 0:
                dados.get(linha).setOs(Integer.parseInt((String) valor));  // Insere os dados na Tabela
                break;  
           case 1:
                dados.get(linha).setData((String) valor);  // Insere os dados na Tabela
                break;
           case 2:
                dados.get(linha).setValorTotal(Double.parseDouble((String) valor));      // Insere os dados na Tabela
                break;
           case 3:
                dados.get(linha).setDesconto(Double.parseDouble((String) valor));      // Insere os dados na Tabela
                break;
           case 4:
                dados.get(linha).setValorTotalMenosDescontos(Double.parseDouble((String) valor));      // Insere os dados na Tabela
                break;     
       }
         this.fireTableRowsUpdated(linha, linha);
    }
    
    
    
    public void addRow(CadastroDeServico ser){
        this.dados.add(ser);            // Insere os dados na Jtable
        this.fireTableDataChanged(); // Atualiza a Jtable
    }
    
    public void removeRow(int linha){
        this.dados.remove(linha);                 // Remove os dados na Jtable
        this.fireTableRowsDeleted(linha, linha);  // Atualiza a Jtable
    }
    
    
}
