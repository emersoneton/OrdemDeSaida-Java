/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Emerson
 */
public class ProdutoTableModelConsultaOrdemDeServico extends AbstractTableModel{
    
    private List<CadastroDeServico> dados = new ArrayList<>();      // Arrai do dados da classe de Cadastro de Serviços que vão ser informados na tabela
    private String[] colunas = {"OS","Cliente","Data do Agendamento","Data da OS","Status"}; // Criação das Colunas no Jtable

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
               return dados.get(linha).getCliente();  // Insere os dados na Tabela
           case 2:
               return dados.get(linha).getDataAgendamento();      // Insere os dados na Tabela
           case 3:
               return dados.get(linha).getData(); // Insere os dados na Tabela
           case 4:
               return dados.get(linha).getStatus(); // Insere os dados na Tabela
       }
       return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
         switch(coluna){
            case 0:
                dados.get(linha).setOs(Integer.parseInt((String) valor)); // Insere os dados na Tabela
                break;  
           case 1:
                dados.get(linha).setCliente((String) valor);  // Insere os dados na Tabela
                break;
           case 2:
                dados.get(linha).setDataAgendamento((String) valor);      // Insere os dados na Tabela
                break;
           case 3:
                dados.get(linha).setData((String) valor); // Insere os dados na Tabela
                break;
           case 4:
                dados.get(linha).setStatus(((String) valor)); // Insere os dados na Tabela
                break;     
       }
         this.fireTableRowsUpdated(linha, linha);
    }
    
    
    
    public void addRow(CadastroDeServico p){
        this.dados.add(p);            // Insere os dados na Jtable
        this.fireTableDataChanged(); // Atualiza a Jtable
    }
    
    public void removeRow(int linha){
        this.dados.remove(linha);                 // Remove os dados na Jtable
        this.fireTableRowsDeleted(linha, linha);  // Atualiza a Jtable
    }
    
}
