
package Controller;

import Model.ClientesDAO;
import Model.ProdutosDAO;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;


public class GeradorDePdf {
    
    public void GeraPDFClientes(){
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("Clientes.pdf"));

            document.open();
            document.add(new Paragraph("                                                       RELATÓRIO DE CLIENTES"));
            document.add(new Paragraph("                                                      ________________________"
                    + ""
                    + ""));

            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "
                    + ""
                    + ""));
            document.add(new Paragraph("Os dados do relatório contém: Nome, Endereço, Número, Bairro, Telefone, Cidade e Estado."));
            document.add(new Paragraph("****************************************************************************************************************"));
            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));
            
            ClientesDAO cliDao = new ClientesDAO();
            
            List<CadastroDeClientes> lista = cliDao.GerarPDF();

            for(int x=0; x < lista.size(); x++){                
                document.add(new Paragraph((x+1) + " - " + lista.get(x).getNome() + " - " + lista.get(x).getEndereco()+ 
                        " " + lista.get(x).getNumero()+ " " + lista.get(x).getBairro()+ " - " + lista.get(x).getTelefone()+ 
                        " - " + lista.get(x).getCidade()+ " - " + lista.get(x).getEstado()));
                document.add(new Paragraph(" "));
            }
           
            document.add(new Paragraph("  "
                    + ""
                    + ""));
            document.add(new Paragraph("                                                             Fim da Lista!"));
        } catch (FileNotFoundException | DocumentException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            document.close();
        }

        try {
            Desktop.getDesktop().open(new File("clientes.pdf"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    
    
    public void GeraPDFProdutos(){
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("Produtos.pdf"));

            document.open();
            document.add(new Paragraph("                                                       RELATÓRIO DE PRODUTOS"));
            document.add(new Paragraph("                                                      ________________________"
                    + ""
                    + ""));

            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "
                    + ""
                    + ""));
            document.add(new Paragraph("Os dados do relatório contém: Descrição, Código, Codigo de Barras, valor e Quantidade."));
            document.add(new Paragraph("****************************************************************************************************************"));
            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));
            
            ProdutosDAO proDao = new ProdutosDAO();
            
            List<CadastroDeProdutos> lista = proDao.GerarPDF();

            for(int x=0; x < lista.size(); x++){                
                document.add(new Paragraph((x+1) + " -- Nome: " + lista.get(x).getDescricao() + " -- Código: " +  lista.get(x).getCodigo() + 
                        " -- Código de Barras: " + lista.get(x).getCodigoBarras()+ " -- Valor: " + lista.get(x).getValor()+ " -- Quantidade: " + lista.get(x).getQuantidade()));
                document.add(new Paragraph(" "));
            }
           
            document.add(new Paragraph("  "
                    + ""
                    + ""));
            document.add(new Paragraph("                                                             Fim da Lista!"));
        } catch (FileNotFoundException | DocumentException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            document.close();
        }

        try {
            Desktop.getDesktop().open(new File("Produtos.pdf"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
