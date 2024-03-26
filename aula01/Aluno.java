

public class Aluno {

    private String nome;
    private int idade;
    private float IAA;
    private Curso curso;
    
    public Aluno(String nome, int idade, float iAA, Curso curso) {
        this.nome = nome;
        this.idade = idade;
        this.IAA = iAA;
        this.curso = curso;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public float getIAA() {
        return IAA;
    }
    public void setIAA(float iAA) {
        IAA = iAA;
    }
    
    public Curso getCurso() {
        return curso;
    }
    
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    
}
