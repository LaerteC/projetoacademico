
package BEANS;

import java.util.Date;

public class CardapioBean {
    
    private int idCardapio;
    private int idAlmoco;
    private int idJanta;
    private int idNutricionista;   
    
    private Date dataHora;
    private Date dataHoraAuxiliar;
    
    public CardapioBean(){
    }
    
    public int getIdCardapio() {
        return idCardapio;
    }

    public void setIdCardapio(int idCardapio) {
        this.idCardapio = idCardapio;
    }

    public int getIdAlmoco() {
        return idAlmoco;
    }

    public void setIdAlmoco(int idAlmoco) {
        this.idAlmoco = idAlmoco;
    }

    public int getIdJanta() {
        return idJanta;
    }

    public void setIdJanta(int idJanta) {
        this.idJanta = idJanta;
    }

    public int getIdNutricionista() {
        return idNutricionista;
    }

    public void setIdNutricionista(int idNutricionista) {
        this.idNutricionista = idNutricionista;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Date getDataHoraAuxiliar() {
        return dataHoraAuxiliar;
    }

    public void setDataHoraAuxiliar(Date dataHoraAuxiliar) {
        this.dataHoraAuxiliar = dataHoraAuxiliar;
    }
    
}
