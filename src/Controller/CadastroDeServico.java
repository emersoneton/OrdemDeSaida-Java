
package Controller;


public class CadastroDeServico {
    String cliente = null, complemento = null, descricao = null, data = null, dataAgendamento = null, horarioAgendamento = null;
    int quantidade = 0, os = 0;
    double valorTotal = 0, desconto = 0, contador = 0;
    boolean validador = false;


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

    public double getContador() {
        return contador;
    }

    public void setContador(double contador) {
        this.contador = contador;
    }

    public boolean isValidador() {
        return validador;
    }

    public void setValidador(boolean validador) {
        this.validador = validador;
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

    
}
