package Controller;

import Model.ClientesDAO;
import Model.OrdemDeServicoDAO;
import Model.ProdutosDAO;
import View.FormOrdemDeServico;
import com.itextpdf.text.Image;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.io.IOException;
import com.itextpdf.text.DocumentException;
import java.io.FileOutputStream;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorDePdf {

    private static Font fonteCabecalho = new Font(Font.FontFamily.COURIER, 18,
            Font.BOLD);
    private static Font fontePadrao = new Font(Font.FontFamily.TIMES_ROMAN, 10);
    private static Font fonteVermelha = new Font(Font.FontFamily.TIMES_ROMAN,
            12, Font.NORMAL, BaseColor.RED);
    private static Font negritoPequena = new Font(Font.FontFamily.HELVETICA,
            10, Font.BOLD);
    private static Font negrito = new Font(Font.FontFamily.TIMES_ROMAN,
            12, Font.BOLD);

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
            figura.scaleToFit(400, 200);
            figura.setAlignment(1);
            
            PdfPTable table1 = new PdfPTable(new float[]{50, 120f});
            PdfPCell foto = new PdfPCell(new Phrase("Descrição"));
            foto.setHorizontalAlignment(Element.ALIGN_CENTER);
            foto.setVerticalAlignment(0);
            PdfPCell dados = new PdfPCell(new Phrase("Quantidade"));
            table1.addCell(figura);
            // Dados do Emissor
            table1.addCell(" Razão Social: " + fil.getRazaoSocial() + "\n CNPJ: " + fil.getCnpj() + "\n Cep: " + fil.getCep()
                    + "\n Endereço: " + fil.getEndereco() + ", nº " + fil.getNumero() + "\n Bairro: " + fil.getBairro()
                    + "\n Cidade: " + fil.getCidade() + " / " + fil.getEstado() + "\n Telefone: " + fil.getTelefoneCelular());
            document.add(table1);

            
            
            // Numero da Ordem de Serviço
            Paragraph p = new Paragraph("ORDEM DE SERVIÇO (" + ser.getOs() + ")", fonteCabecalho);
            p.setAlignment(1);
            document.add(p);

            document.add(new Paragraph(" "));
            serDao.BuscarClienteParaGerarPDF(ser, cli);
            document.add(new Paragraph("Cliente: " + cli.getNome(), fontePadrao));
            document.add(new Paragraph("CPF/CNPJ: " + cli.getCpf() + " / " + cli.getCnpj(), fontePadrao));
            document.add(new Paragraph("Endereço: " + cli.getEndereco() + ", nº " + cli.getNumero(), fontePadrao));
            document.add(new Paragraph("Cidade: " + cli.getCidade() + " / " + cli.getEstado(), fontePadrao));
            document.add(new Paragraph("Telefone: " + cli.getTelefone(), fontePadrao));

            document.add(new Paragraph(" "));

            document.add(new Paragraph("Descrição de Serviço: " + ser.getComplemento(), fontePadrao));

            document.add(new Paragraph(" "));

            // Buscar Itens do Banco
            serDao.BuscarItensDoServico(ser);
            List<CadastroDeServico> lista = serDao.BuscarItensDoServico(ser);

            PdfPTable table = new PdfPTable(new float[]{20f, 10f, 10f}); // crio a tabela para ser vista de fora ou dentro do IF
            if (lista.size() > 1) {
                PdfPCell Nome = new PdfPCell(new Phrase("Descrição"));
                Nome.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell Quantidade = new PdfPCell(new Phrase("Quantidade"));
                Quantidade.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell Valor = new PdfPCell(new Phrase("Valor"));
                Valor.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(Nome);
                table.addCell(Quantidade);
                table.addCell(Valor);
            }
            //Crio o tabela de produtos
            int Quantidade = 0;
            for (int x = 0; x < lista.size(); x++) {
                 Quantidade = lista.get(x).getQuantidade();
                
                PdfPCell celula1 = new PdfPCell(new Phrase(lista.get(x).getDescricao()));
                PdfPCell celula2 = new PdfPCell(new Phrase(String.valueOf(lista.get(x).getQuantidade())));
                celula2.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell celula3 = new PdfPCell(new Phrase(String.valueOf(lista.get(x).getValorTotal())));

                table.addCell(celula1);
                table.addCell(celula2);
                table.addCell(celula3);

            }

            document.add(table); // Adiciono dados na tabela  
            
            document.add(new Paragraph(Quantidade));
                            
            if (lista.size() > 1) {
                 // Valor Total
            Paragraph valorTotal = new Paragraph("Valor Total:        ");
            valorTotal.add(new Phrase("" + ser.getValorTotal(), negrito));
            valorTotal.setAlignment(0);
            document.add(valorTotal);

            // Valor de Desconto
            Paragraph desconto = new Paragraph("Valor Desconto: ");
            desconto.add(new Phrase("" + ser.getDesconto(), negrito));
            desconto.setAlignment(0);
            document.add(desconto);
            }
           

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Ordem de Serviço Gerada em: " + ser.getData(), fontePadrao));
            document.add(new Paragraph("Agendamento Marcado para: " + ser.getDataAgendamento() + " - " + ser.getHorarioAgendamento(), fontePadrao));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("* o serviço será realizado em até 20 dias úteis, salvo imprevisto.", negrito));
            document.add(new Paragraph("* qualquer serviço adicional será cobrado valores de acordo com a tabela.", negrito));
            document.add(new Paragraph("* aparelhos não retirados no prazo de 90 dias, serão descartados.", negrito));
            document.add(new Paragraph("* todos os serviços realizados tem garantia de 90 dias ao contar da data de entrega.", negrito));
            document.add(new Paragraph("* o serviço só será realizado após aprovação do cliente.", negrito));
            document.add(new Paragraph("* o ponto de luz é por conta do cliente, caso executado será cobrado a parte.", negrito));

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            Paragraph tec = new Paragraph("______________________________                   ______________________________");
            tec.setAlignment(1);
            document.add(tec);

            document.add(new Paragraph("                                TÉCNICO                                                                      CLIENTE ", negrito));

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            Paragraph fim = new Paragraph("AGRADECEMOS PELA SUA PREFERÊNCIA", fonteVermelha);
            fim.setAlignment(1);
            document.add(fim);

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
