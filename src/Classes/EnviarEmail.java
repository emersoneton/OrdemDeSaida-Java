package Classes;

import Controller.CadastroDeClientes;
import Controller.CadastroDeServico;
import javax.swing.JOptionPane;
import org.apache.commons.mail.*;

public class EnviarEmail {
    String meuEmail = "climatizacao.nelsonrodrigues@gmail.com";
    //String meuEmail = "treinosweb@gmail.com";
    
    MultiPartEmail email = new MultiPartEmail();
    
    private void ConfiguraçãoDeEmail(){
        int porta = 465;
        String minhaSenha = "997865036"; 
       // String minhaSenha = "treinos123456";

        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(porta);
        email.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha));
        email.setSSLOnConnect(true);
        email.setTLS(true);
        
    }
    public void EnviarEmailsDeNotas(CadastroDeClientes cli, CadastroDeServico ser) {

       ConfiguraçãoDeEmail();
        
        try {

            email.setFrom(meuEmail);
            email.setSubject("Ordem de Serviço ("+ser.getOs()+")");

            email.setMsg("Prezado Sr(a) " + ser.getCliente() + ", \n\n"
                    + "Em anexo, o PDF de sua Ordem de Serviço Nº: " + ser.getOs() + "\n\n"
                    + "Data da OS: " + ser.getData() + "\n\n"
                    + "Data e Hora do Agendamento: "+ser.getDataAgendamento()+" - "+ser.getHorarioAgendamento()+"\n\n"
                    + "Att: NR Climatização \n\n"
                    + "AGRADECEMOS PELA SUA PREFERÊNCIA!");
            email.addTo(cli.getEmail());

            EmailAttachment anexo = new EmailAttachment();
            anexo.setPath("c:/SISOS/PDF/OS/OS_" + ser.getOs() + "_" + ser.getCliente() + ".pdf");
            anexo.setName("OS " + ser.getOs() + ".pdf");
            email.attach(anexo);

            email.send();

            JOptionPane.showMessageDialog(null, "Email enviado com Sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void EnviarEmailRecibos(CadastroDeClientes cli, CadastroDeServico ser){
         ConfiguraçãoDeEmail();
        
        try {

            email.setFrom(meuEmail);
            email.setSubject("Recibo da OS ("+ser.getOs()+")");

            email.setMsg("Prezado Sr(a) " + ser.getCliente() + ", \n\n"
                    + "Em anexo, o PDF de seu Recibo Nº: " + ser.getOs() + "\n\n"
                    + "Att: NR Climatização \n\n"
                    + "AGRADECEMOS PELA SUA PREFERÊNCIA!");
            email.addTo(cli.getEmail());

            EmailAttachment anexo = new EmailAttachment();
            anexo.setPath("c:/SISOS/PDF/Recibos/cliente_" + ser.getCliente() + "_OS_" + ser.getOs() + ".pdf");
            anexo.setName("Recibo da OS " + ser.getOs() + ".pdf");
            email.attach(anexo);

            email.send();

            JOptionPane.showMessageDialog(null, "Email enviado com Sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
