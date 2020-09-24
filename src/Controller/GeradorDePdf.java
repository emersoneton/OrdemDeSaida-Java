package Controller;

import Model.ClientesDAO;
import Model.OrdemDeServicoDAO;
import Model.ProdutosDAO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import static java.awt.Color.blue;
import java.io.FileOutputStream;
import java.io.IOException;

public class GeradorDePdf {

    // GERA PDF DO CADASTRO DE CLIENTES
    public void GeraPDFClientes() {
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("Clientes.pdf"));

            document.open();
            document.open();
            Paragraph p = new Paragraph("RELATÓRIO DE CLIENTES");
            p.setAlignment(1);
            document.add(p);
            
            Paragraph linha = new Paragraph("________________________");
            linha.setAlignment(1);
            document.add(linha);

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

            for (int x = 0; x < lista.size(); x++) {
                document.add(new Paragraph((x + 1) + " - " + lista.get(x).getNome() + " - " + lista.get(x).getEndereco()
                        + " " + lista.get(x).getNumero() + " " + lista.get(x).getBairro() + " - " + lista.get(x).getTelefone()
                        + " - " + lista.get(x).getCidade() + " - " + lista.get(x).getEstado()));
                document.add(new Paragraph(" "));
            }

            document.add(new Paragraph("  "
                    + ""
                    + ""));
            
            Paragraph fim = new Paragraph("FIM DA LISTA");
            fim.setAlignment(1);
            document.add(fim);
            
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

    // GERA PDF DO CADASTRO DE PRODUTOS
    public void GeraPDFProdutos() {
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("Produtos.pdf"));

            document.open();
            Paragraph p = new Paragraph("RELATÓRIO DE PRODUTOS");
            p.setAlignment(1);
            document.add(p);
            
            Paragraph linha = new Paragraph("________________________");
            linha.setAlignment(1);
            document.add(linha);

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

            for (int x = 0; x < lista.size(); x++) {
                document.add(new Paragraph((x + 1) + " -- Nome: " + lista.get(x).getDescricao() + " -- Código: " + lista.get(x).getCodigo()
                        + " -- Código de Barras: " + lista.get(x).getCodigoBarras() + " -- Valor: " + lista.get(x).getValor() + " -- Quantidade: " + lista.get(x).getQuantidade()));
                document.add(new Paragraph(" "));
            }

            document.add(new Paragraph("  "
                    + ""
                    + ""));
            Paragraph fim = new Paragraph("FIM DA LISTA");
            fim.setAlignment(1);
            document.add(fim);
            
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

    //GERAR PDF DA ORDEM DE SERVIÇO
    public void GeraPDFOrdemDeServico(CadastroDeServico ser, CadastroDeClientes cli) {

        CadastroDeFilial fil = new CadastroDeFilial();
        
        OrdemDeServicoDAO serDao = new OrdemDeServicoDAO();
        serDao.BuscaDadosFilialPDF(fil);
        
        Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream("Ordem De Serviço.pdf"));
            document.open();
            Image figura = Image.getInstance("imagem.jpg");
            document.add(figura);

            // Dados do Emissor
            document.add(new Paragraph("_______________________________Dados do Emissor________________________________"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Razão Social: "+fil.getRazaoSocial()+"                  CNPJ: "+fil.getCnpj()));
            document.add(new Paragraph("Cep: "+fil.getCep()));
            document.add(new Paragraph("Endereço: "+fil.getEndereco()+", nº "+fil.getNumero()));
            document.add(new Paragraph("Bairro: "+fil.getBairro()));
            document.add(new Paragraph("Cidade: "+fil.getCidade()+" / "+fil.getEstado()));
            document.add(new Paragraph("Telefone: "+fil.getTelefoneCelular()));
            document.add(new Paragraph("______________________________________________________________________________"));
            
            // Numero da Ordem de Serviço
            document.add(new Paragraph(" "));
            serDao.BuscarOS(ser);
            Paragraph p = new Paragraph("ORDEM DE SERVIÇO ("+ser.getOs()+")");
            p.setAlignment(1);
            document.add(p);
           
        
            document.add(new Paragraph(" "));
            serDao.BuscarClienteParaGerarPDF(ser, cli);
            document.add(new Paragraph("Cliente: "+cli.getNome()));
            document.add(new Paragraph("CPF/CNPJ: "+cli.getCpf()+" / "+cli.getCnpj()));
            document.add(new Paragraph("Endereço: "+cli.getEndereco()+", nº "+cli.getNumero()));
            document.add(new Paragraph("Telefone: "+cli.getTelefone()));
            
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();
        
        try {
            Desktop.getDesktop().open(new File("Ordem De Serviço.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(GeradorDePdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
