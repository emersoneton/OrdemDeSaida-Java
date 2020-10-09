package Classes;

import Controller.CadastroDeClientes;
import Controller.CadastroDeServico;
import javax.swing.JOptionPane;
import org.apache.commons.mail.*;

public class EnviarEmail {

    public void EnviarEmailsDeNotas(CadastroDeClientes cli, CadastroDeServico ser) {

        String meuEmail = "climatizacao.nelsonrodrigues@gmail.com";
        String minhaSenha = "997865036";
      //  String meuEmail = "treinosweb@gmail.com";
      //  String minhaSenha = "treinos123456";
        int porta = 465;

        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(porta);
        email.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha));
        email.setSSLOnConnect(true);
        email.setTLS(true);

        try {

            email.setFrom(meuEmail);
            email.setSubject("Ordem de Serviço");          
            
            email.setMsg("Prezado Sr(a) "+ ser.getCliente() +", \n\n"
                    + "Em anexo, o PDF de sua Ordem de Serviço \n\n"
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

}
