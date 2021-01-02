/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabelas;

import Controller.CadastroDeUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Emerson
 */
public class UsuariosTableModel extends AbstractTableModel{

     private List<CadastroDeUsuario> dados = new ArrayList<>();      // Arrai do dados da classe de Cadastro de Serviços que vão ser informados na tabela
    private String[] colunas = {"Nome","Login de Usuário","TIPO"}; // Criação das Colunas no Jtable

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
               return dados.get(linha).getNome();  // Insere os dados na Tabela
           case 1:
               return dados.get(linha).getLogin();  // Insere os dados na Tabela
           case 2:
               return dados.get(linha).getTipo();      // Insere os dados na Tabela
       }
       return null;
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
         switch(coluna){
           case 0:
                dados.get(linha).setNome((String) valor);  // Insere os dados na Tabela
                break;  
           case 1:
                dados.get(linha).setLogin((String) valor);  // Insere os dados na Tabela
                break;
           case 2:
                dados.get(linha).setTipo((String) valor);      // Insere os dados na Tabela
                break;
       }
         this.fireTableRowsUpdated(linha, linha);
    }
    
    
    
    public void addRow(CadastroDeUsuario usu){
        this.dados.add(usu);            // Insere os dados na Jtable
        this.fireTableDataChanged(); // Atualiza a Jtable
    }
    
    public void removeRow(int linha){
        this.dados.remove(linha);                 // Remove os dados na Jtable
        this.fireTableRowsDeleted(linha, linha);  // Atualiza a Jtable
    }
    
}
