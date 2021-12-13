
package BEANS;

public class AlmocoBean{
    
    //Id do cardapio
    private int idAlmoco;
    private int idCardapio;
    private int idArroz;
    private int idFeijao;
    private int idAcompanhamento;
    private int idSalada;
    private int idSobremesa;
    //Nome dos ingredientes do almoço
    private String almoco;
    private String cardapio;
    private String arroz;
    private String feijao;
    private String acompanhamento;
    private String salada;
    private String sobremesa;
    //Quantidade de comida
    private double qtdeArroz;
    private double qtdeFeijao;
    private double qtdeAcompanhamento;
    private double qtdeSalada;
    private double qtdeSobremesa;
    
    public AlmocoBean(){
    }
    
    public int getIdAlmoco() {
        return idAlmoco;
    }

    public void setIdAlmoco(int idAlmoco) {
        this.idAlmoco = idAlmoco;
    }

    public int getIdCardapio() {
        return idCardapio;
    }

    public void setIdCardapio(int idCardapio) {
        this.idCardapio = idCardapio;
    }

    public int getIdArroz() {
        return idArroz;
    }

    public void setIdArroz(int idArroz) {
        this.idArroz = idArroz;
    }

    public int getIdFeijao() {
        return idFeijao;
    }

    public void setIdFeijao(int idFeijao) {
        this.idFeijao = idFeijao;
    }

    public int getIdAcompanhamento() {
        return idAcompanhamento;
    }

    public void setIdAcompanhamento(int idAcompanhamento) {
        this.idAcompanhamento = idAcompanhamento;
    }

    public int getIdSalada() {
        return idSalada;
    }

    public void setIdSalada(int idSalada) {
        this.idSalada = idSalada;
    }

    public int getIdSobremesa() {
        return idSobremesa;
    }

    public void setIdSobremesa(int idSobremesa) {
        this.idSobremesa = idSobremesa;
    }

    public double getQtdeArroz() {
        return qtdeArroz;
    }

    public void setQtdeArroz(double qtdeArroz) {
        this.qtdeArroz = qtdeArroz;
    }

    public double getQtdeFeijao() {
        return qtdeFeijao;
    }

    public void setQtdeFeijao(double qtdeFeijao) {
        this.qtdeFeijao = qtdeFeijao;
    }

    public double getQtdeAcompanhamento() {
        return qtdeAcompanhamento;
    }

    public void setQtdeAcompanhamento(double qtdeAcompanhamento) {
        this.qtdeAcompanhamento = qtdeAcompanhamento;
    }

    public double getQtdeSalada() {
        return qtdeSalada;
    }

    public void setQtdeSalada(double qtdeSalada) {
        this.qtdeSalada = qtdeSalada;
    }

    public double getQtdeSobremesa() {
        return qtdeSobremesa;
    }

    public void setQtdeSobremesa(double qtdeSobremesa) {
        this.qtdeSobremesa = qtdeSobremesa;
    }
    
    //Variaveis auxiliares
    
    public String getAlmoco() {
        return almoco;
    }

    public void setAlmoco(String almoco) {
        this.almoco = almoco;
    }

    public String getCardapio() {
        return cardapio;
    }

    public void setCardapio(String cardapio) {
        this.cardapio = cardapio;
    }

    public String getArroz() {
        return arroz;
    }

    public void setArroz(String arroz) {
        this.arroz = arroz;
    }

    public String getFeijao() {
        return feijao;
    }

    public void setFeijao(String feijao) {
        this.feijao = feijao;
    }

    public String getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(String acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    public String getSalada() {
        return salada;
    }

    public void setSalada(String salada) {
        this.salada = salada;
    }

    public String getSobremesa() {
        return sobremesa;
    }

    public void setSobremesa(String sobremesa) {
        this.sobremesa = sobremesa;
    }
    
}
