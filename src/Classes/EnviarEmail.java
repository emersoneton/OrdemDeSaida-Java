
package Classes;

import Controller.CadastroDeClientes;
import Controller.CadastroDeServico;
import javax.swing.JOptionPane;
import org.apache.commons.mail. *;

public class EnviarEmail {
    
    public void EnviarEmailsDeNotas(CadastroDeClientes cli, CadastroDeServico ser){
            
        String meuEmail = "climatizacao.nelsonrodrigues@gmail.com";
        String minhaSenha = "997865036";
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
            email.setMsg("Em anexo abaixo, o PDF de sua Ordem de Serviço");
            email.addTo(cli.getEmail());
            
            EmailAttachment anexo = new EmailAttachment();
            anexo.setPath("c:/SISOS/PDF/OS/OS_"+ser.getOs()+"_"+ser.getCliente()+".pdf");
            anexo.setName("OS_"+ser.getOs()+".pdf");
            email.attach(anexo);
            
            email.send();

            JOptionPane.showMessageDialog(null, "Email enviado com Sucesso!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

 
    
}
