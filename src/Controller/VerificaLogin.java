
package Controller;


public class VerificaLogin {
    String login = null, 
            senha = null,
            validaLogin = null,
            validaSenha = null,
            nome = null,
            tipo = null;;
    boolean validador = false;
    

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isValidador() {
        return validador;
    }

    public void setValidador(boolean validador) {
        this.validador = validador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValidaLogin() {
        return validaLogin;
    }

    public void setValidaLogin(String validaLogin) {
        this.validaLogin = validaLogin;
    }

    public String getValidaSenha() {
        return validaSenha;
    }

    public void setValidaSenha(String validaSenha) {
        this.validaSenha = validaSenha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
