package BEANS;

public class AlunoBean {
 
    private int id;
    private String nome;
    private String cpf;
    private java.util.Date dataNascimento;
    private int idCidade;
    private String rua;
    private String cep;
    private String email;
    private byte estadoCadastro;
    private java.util.Date anoIngresso;
    private String curso;
    private String grr;
    
    public AlunoBean(){
    
    }
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public java.util.Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(java.util.Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte getEstadoCadastro() {
        return estadoCadastro;
    }

    public void setEstadoCadastro(byte estadoCadastro) {
        this.estadoCadastro = estadoCadastro;
    }
    
    public java.util.Date getAnoIngresso() {
        return anoIngresso;
    }

    public void setAnoIngresso(java.util.Date anoIngresso) {
        this.anoIngresso = anoIngresso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getGrr() {
        return grr;
    }

    public void setGrr(String grr) {
        this.grr = grr;
    }
    
}
