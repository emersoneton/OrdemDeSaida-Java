
package Controller;

public class CadastroDeProdutos {
   public String codigoBarras = null
           , descricao = null
           , valor = null
           , quantidade = null
           , codigo = null;

    public String getCodigo() {
        return codigoBarras;
    }

    public void setCodigo(String codigo) {
        this.codigoBarras = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
    
    
}
