package Controller;

import Classes.CurrencyWriter;
import Model.ClientesDAO;
import Model.OrdemDeServicoDAO;
import Model.ProdutosDAO;
import com.itextpdf.text.BadElementException;
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
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class GeradorDePdf {

    private static Font fonteCabecalho = new Font(Font.FontFamily.COURIER, 18,
            Font.BOLD);
    private static Font fontePadrao = new Font(Font.FontFamily.TIMES_ROMAN, 10);
    private static Font fontePadraoNegrito = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
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
            PdfWriter.getInstance(document, new FileOutputStream("c:/SISOS/PDF/Cadastros/Clientes.pdf"));

            document.open();
            document.open();
            Paragraph p = new Paragraph("RELATÓRIO DE CLIENTES", fonteCabecalho);
            p.setAlignment(1);
            document.add(p);

            Paragraph linha = new Paragraph("_____________________________________");
            linha.setAlignment(1);
            document.add(linha);

            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "
                    + ""
                    + ""));
            document.add(new Paragraph("Os dados do relatório contém: Nome, Endereço, Número, Bairro, Telefone, celular, Cidade, Estado e situação."));
            document.add(new Paragraph("****************************************************************************************************************"));
            document.add(new Paragraph("  "));
            document.add(new Paragraph("  "));

            ClientesDAO cliDao = new ClientesDAO();

            List<CadastroDeClientes> lista = cliDao.GerarPDF();

            for (int x = 0; x < lista.size(); x++) {
                document.add(new Paragraph((x + 1) + " - " + lista.get(x).getNome() + " - " + lista.get(x).getEndereco()
                        + " ,nº" + lista.get(x).getNumero() + " - " + lista.get(x).getBairro() + " - " + lista.get(x).getTelefone()
                        + " - " + lista.get(x).getTelefoneCelular() + " - " + lista.get(x).getCidade() + " - " + lista.get(x).getEstado() + " - " + lista.get(x).getSituacao()));
                document.add(new Paragraph(" "));
            }

            document.add(new Paragraph("  "
                    + ""
                    + ""));

            Paragraph fim = new Paragraph("FIM DA LISTA", fonteVermelha);
            fim.setAlignment(1);
            document.add(fim);

        } catch (FileNotFoundException | DocumentException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            document.close();
        }

        try {
            Desktop.getDesktop().open(new File("c:/SISOS/PDF/Cadastros/Clientes.pdf"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    // GERA PDF DO CADASTRO DE PRODUTOS
    public void GeraPDFProdutos() {
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("c:/SISOS/PDF/Cadastros/Produtos.pdf"));

            document.open();
            Paragraph p = new Paragraph("RELATÓRIO DE PRODUTOS", fonteCabecalho);
            p.setAlignment(1);
            document.add(p);

            Paragraph linha = new Paragraph("_____________________________________");
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

            PdfPTable table = new PdfPTable(new float[]{50f, 7f, 25f, 7f, 7f}); // crio a tabela para ser vista de fora ou dentro do IF
            if (lista.size() > 1) {
                PdfPCell Nome = new PdfPCell(new Phrase("DESCRIÇÃO", negritoPequena));
                Nome.setBorder(Rectangle.NO_BORDER);// Tabela sem Bordas
                Nome.setPaddingLeft(-70);

                PdfPCell codigo = new PdfPCell(new Phrase("Código", negritoPequena));
                codigo.setHorizontalAlignment(Element.ALIGN_CENTER);
                codigo.setBorder(Rectangle.NO_BORDER);// Tabela sem Bordas
                codigo.setPaddingRight(-80); // Tabela sem margem

                PdfPCell codigoBarras = new PdfPCell(new Phrase("Codigo de Barras", negritoPequena));
                codigoBarras.setHorizontalAlignment(Element.ALIGN_CENTER);
                codigoBarras.setBorder(Rectangle.NO_BORDER);// Tabela sem Bordas
                codigoBarras.setPaddingRight(-100); // Tabela sem margem

                PdfPCell Valor = new PdfPCell(new Phrase("valor", negritoPequena));
                Valor.setHorizontalAlignment(Element.ALIGN_CENTER);
                Valor.setBorder(Rectangle.NO_BORDER);// Tabela sem Bordas
                Valor.setPaddingRight(-120); // Tabela sem margem

                PdfPCell Quantidade = new PdfPCell(new Phrase("qtd", negritoPequena));
                Quantidade.setHorizontalAlignment(Element.ALIGN_CENTER);
                Quantidade.setBorder(Rectangle.NO_BORDER);// Tabela sem Bordas
                Quantidade.setPaddingRight(-150); // Tabela sem margem 

                table.addCell(Nome);
                table.addCell(codigo);
                table.addCell(codigoBarras);
                table.addCell(Valor);
                table.addCell(Quantidade);
                document.add(new Paragraph(" "));
            }

            //Crio o tabela de produtos
            int Quantidade = 0;
            for (int x = 0; x < lista.size(); x++) {
                Quantidade = lista.get(x).getQuantidade();

                PdfPCell celula1 = new PdfPCell(new Phrase((x + 1) + " - " + lista.get(x).getDescricao(), negritoPequena));
                celula1.setBorder(Rectangle.NO_BORDER); // Tabela sem Bordas
                celula1.setPaddingLeft(-70); // Tabela sem margem

                PdfPCell celula2 = new PdfPCell(new Phrase(String.valueOf(lista.get(x).getCodigo()), negritoPequena));
                celula2.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula2.setBorder(Rectangle.NO_BORDER); // Tabela sem Bordas
                celula2.setPaddingRight(-80); // Tabela sem margem

                PdfPCell celula3 = new PdfPCell(new Phrase(String.valueOf(lista.get(x).getCodigoBarras()), negritoPequena));
                celula3.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula3.setBorder(Rectangle.NO_BORDER); // Tabela sem Bordas
                celula3.setPaddingRight(-100); // Tabela sem margem

                PdfPCell celula4 = new PdfPCell(new Phrase(Double.toString(lista.get(x).getValor()).replace(".", ","), negritoPequena));
                celula4.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula4.setBorder(Rectangle.NO_BORDER); // Tabela sem Bordas
                celula4.setPaddingRight(-120); // Tabela sem margem

                PdfPCell celula5 = new PdfPCell(new Phrase(String.valueOf(lista.get(x).getQuantidade()), negritoPequena));
                celula5.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula5.setBorder(Rectangle.NO_BORDER); // Tabela sem Bordas
                celula5.setPaddingRight(-150); // Tabela sem margem 

                table.addCell(celula1);
                table.addCell(celula2);
                table.addCell(celula3);
                table.addCell(celula4);
                table.addCell(celula5);

            }

            document.add(table); // Adiciono dados na tabela  

            document.add(new Paragraph(Quantidade));

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("  "
                    + ""
                    + ""));
            Paragraph fim = new Paragraph("FIM DA LISTA", fonteVermelha);
            fim.setAlignment(1);
            document.add(fim);

        } catch (FileNotFoundException | DocumentException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            document.close();
        }

        try {
            Desktop.getDesktop().open(new File("c:/SISOS/PDF/Cadastros/Produtos.pdf"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void GerarRecibo(CadastroDeServico ser) {

        DecimalFormat df = new DecimalFormat("#####0.00");

        CadastroDeFilial fil = new CadastroDeFilial();

        OrdemDeServicoDAO serDao = new OrdemDeServicoDAO();
        serDao.BuscaDadosFilialPDF(fil);

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("c:/SISOS/PDF/Recibos/cliente_" + ser.getCliente() + "_OS_" + ser.getOs() + ".pdf"));
            document.open();
            
            Paragraph p = new Paragraph("Recibo de Pagamento da OS (" + ser.getOs() + ")", fonteCabecalho);
            p.setAlignment(1);
            document.add(p);
            document.add(new Paragraph(" "));

            Image figura = Image.getInstance("c:/SISOS/Imagem/imagem.jpg");
            figura.scaleToFit(50, 30);
            figura.setAlignment(0);

            PdfPTable table1 = new PdfPTable(new float[]{10, 40});

            String valorTotal = df.format(ser.getValorTotal()).replace(".", ",");
            PdfPCell celula2 = new PdfPCell(new Phrase(String.valueOf("R$ " + valorTotal), fonteCabecalho));
            celula2.setHorizontalAlignment(Element.ALIGN_CENTER);
            celula2.setBorder(Rectangle.NO_BORDER); // Tabela sem Bordas
            celula2.setPaddingRight(-250); // Tabela sem margem

            table1.addCell(figura);
            table1.addCell(celula2);

            document.add(table1);
            document.add(new Paragraph(" "));

            //Transformo numero decimal em numero por extenso
            CurrencyWriter cw = new CurrencyWriter();
            String extenso = cw.write(new BigDecimal(ser.getValorTotal()));

            document.add(new Paragraph("Recebi(emos) de " + ser.getCliente() + ", a importancia de " + extenso
                    + " referente á OS " + ser.getOs() + ", cujo problema " + ser.getComplemento() + "."));
            document.add(new Paragraph("Para maior clareza firmo(amos) o presente recibo para que produza os seus efeitos,"
                    + "dando plena, rasa e irrevogável quitação, pelo valor recebido."));
            document.add(new Paragraph(" "));

            Paragraph data = new Paragraph("CANOAS " + ser.getData());
            data.setAlignment(2);
            document.add(data);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            Paragraph assinatura = new Paragraph("____________________________________");
            assinatura.setAlignment(1);
            document.add(assinatura);

            // Razão Social
            Paragraph emitente = new Paragraph(fil.getRazaoFantasia());
            emitente.setAlignment(1);
            document.add(emitente);

            //cnpj
            Paragraph cnpj = new Paragraph(fil.getCnpj());
            cnpj.setAlignment(1);
            document.add(cnpj);

            //telefone
            Paragraph telefone = new Paragraph(fil.getTelefoneCelular());
            telefone.setAlignment(1);
            document.add(telefone);


        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeradorDePdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GeradorDePdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeradorDePdf.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.close();

        try {
            Desktop.getDesktop().open(new File("c:/SISOS/PDF/Recibos/cliente_" + ser.getCliente() + "_OS_" + ser.getOs() + ".pdf"));
        } catch (IOException ex) {
            Logger.getLogger(GeradorDePdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //GERAR PDF DA ORDEM DE SERVIÇO
    public void GeraPDFOrdemDeServico(CadastroDeServico ser, CadastroDeClientes cli) {
        DecimalFormat df = new DecimalFormat("#####0.00");

        CadastroDeFilial fil = new CadastroDeFilial();

        OrdemDeServicoDAO serDao = new OrdemDeServicoDAO();
        serDao.BuscaDadosFilialPDF(fil);

        Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream("c:/SISOS/PDF/OS/OS_" + ser.getOs() + "_" + ser.getCliente() + ".pdf"));
            document.open();

            Image figura = Image.getInstance("c:/SISOS/Imagem/imagem.jpg");
            figura.scaleToFit(400, 200);
            figura.setAlignment(1);

            PdfPTable table1 = new PdfPTable(new float[]{90, 100f});

            // Dados do Emissor
            PdfPCell dados = new PdfPCell(new Phrase(" Nome Fantasia: " + fil.getRazaoFantasia() + "\n CNPJ: " + fil.getCnpj() + " - I.M: " + fil.getInscricaoMunicipal() + "\n Cep: " + fil.getCep()
                    + "\n Endereço: " + fil.getEndereco() + ", nº " + fil.getNumero() + "\n Bairro: " + fil.getBairro()
                    + "\n Cidade: " + fil.getCidade() + " / " + fil.getEstado() + "\n Tel Cel: " + fil.getTelefoneCelular(), fontePadrao));

            table1.addCell(figura);
            table1.addCell(dados);

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
            document.add(new Paragraph("Telefone: " + cli.getTelefone() + " / Celular: " + cli.getTelefoneCelular(), fontePadrao));

            document.add(new Paragraph(" "));

            document.add(new Paragraph("Descrição de Serviço: " + ser.getComplemento(), fontePadrao));

            document.add(new Paragraph(" "));

            // Buscar Itens do Banco
            serDao.BuscarItensDoServico(ser);
            List<CadastroDeServico> lista = serDao.BuscarItensDoServico(ser);

            PdfPTable table = new PdfPTable(new float[]{30f, 8f, 8f, 8F}); // crio a tabela para ser vista de fora ou dentro do IF
            if (lista.size() > 1) {
                PdfPCell Nome = new PdfPCell(new Phrase("DESCRIÇÃO", negritoPequena));
                Nome.setBorder(Rectangle.NO_BORDER);// Tabela sem Bordas
                Nome.setPaddingLeft(-50);

                PdfPCell ValorUnitario = new PdfPCell(new Phrase("VALOR", negritoPequena));
                ValorUnitario.setHorizontalAlignment(Element.ALIGN_CENTER);
                ValorUnitario.setBorder(Rectangle.NO_BORDER);// Tabela sem Bordas
                ValorUnitario.setPaddingRight(-140); // Tabela sem margem

                PdfPCell Quantidade = new PdfPCell(new Phrase("QUANTIDADE", negritoPequena));
                Quantidade.setHorizontalAlignment(Element.ALIGN_CENTER);
                Quantidade.setBorder(Rectangle.NO_BORDER);// Tabela sem Bordas
                Quantidade.setPaddingRight(-140); // Tabela sem margem

                PdfPCell Valor = new PdfPCell(new Phrase("TOTAL", negritoPequena));
                Valor.setHorizontalAlignment(Element.ALIGN_CENTER);
                Valor.setBorder(Rectangle.NO_BORDER);// Tabela sem Bordas
                Valor.setPaddingRight(-140); // Tabela sem margem

                table.addCell(Nome);
                table.addCell(ValorUnitario);
                table.addCell(Quantidade);
                table.addCell(Valor);
                document.add(new Paragraph(" "));
            }

            //Crio o tabela de produtos
            int Quantidade = 0;
            for (int x = 0; x < lista.size(); x++) {

                Quantidade = lista.get(x).getQuantidade();

                PdfPCell celula1 = new PdfPCell(new Phrase((x + 1) + " - " + lista.get(x).getDescricao(), negritoPequena));
                celula1.setBorder(Rectangle.NO_BORDER); // Tabela sem Bordas
                celula1.setPaddingLeft(-50); // Tabela sem margem

                PdfPCell celula2 = new PdfPCell(new Phrase(String.valueOf(df.format(lista.get(x).getValorTotal())).replace(".", ","), negritoPequena));
                celula2.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula2.setBorder(Rectangle.NO_BORDER); // Tabela sem Bordas
                celula2.setPaddingRight(-140); // Tabela sem margem

                PdfPCell celula3 = new PdfPCell(new Phrase(String.valueOf(lista.get(x).getQuantidade()), negritoPequena));
                celula3.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula3.setBorder(Rectangle.NO_BORDER); // Tabela sem Bordas
                celula3.setPaddingRight(-140); // Tabela sem margem

                double totalItens = (lista.get(x).getValorTotal() * lista.get(x).getQuantidade());

                PdfPCell celula4 = new PdfPCell(new Phrase(String.valueOf(df.format(totalItens)).replace(".", ","), negritoPequena));
                celula4.setHorizontalAlignment(Element.ALIGN_CENTER);
                celula4.setBorder(Rectangle.NO_BORDER); // Tabela sem Bordas
                celula4.setPaddingRight(-140); // Tabela sem margem

                table.addCell(celula1);
                table.addCell(celula2);
                table.addCell(celula3);
                table.addCell(celula4);

            }

            document.add(table); // Adiciono dados na tabela  

            document.add(new Paragraph(Quantidade));

            if (lista.size() > 0) {
                document.add(new Paragraph(" "));
                // Valor Total
                String valorItens = df.format(ser.getValorTotal()).replace(".", ",");
                Paragraph valorTotal = new Paragraph("Valor Total Itens: ", fontePadrao);
                valorTotal.add(new Phrase(valorItens, negrito));
                valorTotal.setAlignment(2);
                document.add(valorTotal);

                // Valor de Desconto
                String quantidade = df.format(ser.getDesconto()).replace(".", ",");
                Paragraph desconto = new Paragraph("Valor Desconto: ", fontePadrao);
                desconto.add(new Phrase(quantidade, negrito));
                desconto.setAlignment(2);
                document.add(desconto);

                double valorTotalGeral = ser.getValorTotal() - ser.getDesconto();
                // Valor de TOTAL
                String valorTotalNota = df.format(valorTotalGeral).replace(".", ",");
                Paragraph totalGeral = new Paragraph("Valor TOTAL: ", fontePadrao);
                totalGeral.add(new Phrase(valorTotalNota, negrito));
                totalGeral.setAlignment(2);
                document.add(totalGeral);
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
            Desktop.getDesktop().open(new File("c:/SISOS/PDF/OS/OS_" + ser.getOs() + "_" + ser.getCliente() + ".pdf"));
        } catch (IOException ex) {
            Logger.getLogger(GeradorDePdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Consulta NOTAS
    public void GerarPDFBuscaDeNotas(CadastroDeServico ser) {

        OrdemDeServicoDAO serDao = new OrdemDeServicoDAO();
        List<CadastroDeServico> lista = serDao.BuscarNotasDeServico(ser);

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("c:/SISOS/PDF/Relatório_OS/Notas de Serviços.pdf"));
            document.open();

            // Numero da Ordem de Serviço
            Paragraph p = new Paragraph("NOTAS DE SERVIÇOS", fonteCabecalho);
            p.setAlignment(1);
            document.add(p);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            document.add(new Paragraph(" "));
            PdfPTable table = new PdfPTable(new float[]{10f, 40f, 30f, 30f, 30f}); // crio a tabela para ser vista de fora ou dentro do IF

            PdfPCell os = new PdfPCell(new Phrase("OS", fontePadrao));
            os.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cliente = new PdfPCell(new Phrase("CLIENTE", fontePadrao));
            cliente.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell dataAgendamento = new PdfPCell(new Phrase("DATA DO AGENDAMENTO", fontePadrao));
            dataAgendamento.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell dataNota = new PdfPCell(new Phrase("DATA DA NOTA", fontePadrao));
            dataNota.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell statusNota = new PdfPCell(new Phrase("STATUS DA NOTA", fontePadrao));
            statusNota.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(os);
            table.addCell(cliente);
            table.addCell(dataAgendamento);
            table.addCell(dataNota);
            table.addCell(statusNota);

            for (int x = 0; x < lista.size(); x++) {

                PdfPCell celula1 = new PdfPCell(new Phrase(String.valueOf(lista.get(x).getOs()), fontePadrao));
                celula1.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell celula2 = new PdfPCell(new Phrase(lista.get(x).getCliente(), fontePadrao));
                PdfPCell celula3 = new PdfPCell(new Phrase(lista.get(x).getDataAgendamento() + " - " + lista.get(x).getHorarioAgendamento(), fontePadrao));
                celula3.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell celula4 = new PdfPCell(new Phrase(lista.get(x).getData(), fontePadrao));
                celula4.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell celula5 = new PdfPCell(new Phrase(lista.get(x).getStatus(), fontePadrao));
                celula5.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(celula1);
                table.addCell(celula2);
                table.addCell(celula3);
                table.addCell(celula4);
                table.addCell(celula5);
            }

            document.add(table); // Adiciono dados na tabela 

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            Paragraph fim = new Paragraph("FIM DA LISTA", fonteVermelha);
            fim.setAlignment(1);
            document.add(fim);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeradorDePdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GeradorDePdf.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.close();

        try {
            Desktop.getDesktop().open(new File("c:/SISOS/PDF/Relatório_OS/Notas de Serviços.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(GeradorDePdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
