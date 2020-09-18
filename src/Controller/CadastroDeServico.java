
package Controller;


public class CadastroDeServico {
    public String descricao = null;
    public double valor = 0;
    public int quantidade = 1;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    
    
    public double valor(){
        return valor++;
    }
}
