package Controller;

import Model.EmailDAO;
import javax.swing.JOptionPane;
import org.apache.commons.mail.*;

public class EnviarEmail {

    
    MultiPartEmail email = new MultiPartEmail();
    
    private String ConfiguraçãoDeEmail(){
        
        CadastroEmail cad = new CadastroEmail();
        EmailDAO emailDao = new EmailDAO();
        
        emailDao.BuscarEmailParaEnviarEmail(cad);

        int porta = cad.getPorta();
        String meuEmail = cad.getEmail();
        String minhaSenha = cad.getSenha();
        String HostName = cad.getEmailSaida();
        boolean ssl;
        boolean tls;
        
        if(cad.getAceitaSsl() == 1) ssl = true;
        else ssl = false;
        
        if(cad.getAceitaTls() == 1) tls = true;
        else tls = false;
        

        email.setHostName(HostName);
        email.setSmtpPort(porta);
        email.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha));
        email.setSSLOnConnect(ssl);
        email.setTLS(tls);
        
        return meuEmail;
        
    }
    public void EnviarEmailsDeNotas(CadastroDeClientes cli, CadastroDeServico ser) {

        
        try {

            email.setFrom(ConfiguraçãoDeEmail());
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
            JOptionPane.showMessageDialog(null, "Email NÃO foi enviado!!!\n\n"
                    + "VERIFIQUE SE ESTÁ HABILITADO A CONFIGURAÇÃO DE SEGURANÇA DO E-MAIL");
        }

    }
    
    public void EnviarEmailRecibos(CadastroDeClientes cli, CadastroDeServico ser){

        
        try {

            email.setFrom(ConfiguraçãoDeEmail());
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
            JOptionPane.showMessageDialog(null, "Email NÃO foi enviado!!!\n\n"
                    + "VERIFIQUE SE ESTÁ HABILITADO A CONFIGURAÇÃO DE SEGURANÇA DO E-MAIL");
        }

    }

}
