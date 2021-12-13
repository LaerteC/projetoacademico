
package BEANS;

public class IngredienteBean {
    
    private int idIngrediente;
    private String nomeIngrediente;
    private int idTipoIngrediente;
    
    public IngredienteBean(){
    }
    
    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNomeIngrediente() {
        return nomeIngrediente;
    }

    public void setNomeIngrediente(String nomeIngrediente) {
        this.nomeIngrediente = nomeIngrediente;
    }

    public int getIdTipoIngrediente() {
        return idTipoIngrediente;
    }

    public void setIdTipoIngrediente(int idTipoIngrediente) {
        this.idTipoIngrediente = idTipoIngrediente;
    }
    
}
