
package Controller;


public class CadastroDeServico {
    String cliente = null, complemento = null, descricao = null, data = null, 
            dataAgendamento = null, horarioAgendamento = null, status = "ABERTO", 
            clicked = null; // serve para ver os itens clicado na busca por notas
    int quantidade = 0, os = 0, contador = 0;
    double valorTotal = 0, desconto = 0;
    boolean validadorNota = false, validadorItens = false;


    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getOs() {
        return os;
    }

    public void setOs(int os) {
        this.os = os;
    }

   
    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }


    public boolean isValidadorNota() {
        return validadorNota;
    }

    public void setValidadorNota(boolean validadorNota) {
        this.validadorNota = validadorNota;
    }

    public boolean isValidadorItens() {
        return validadorItens;
    }

    public void setValidadorItens(boolean validadorItens) {
        this.validadorItens = validadorItens;
    }

    
    
    public String getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getHorarioAgendamento() {
        return horarioAgendamento;
    }

    public void setHorarioAgendamento(String horarioAgendamento) {
        this.horarioAgendamento = horarioAgendamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClicked() {
        return clicked;
    }

    public void setClicked(String clicked) {
        this.clicked = clicked;
    }

    
}
