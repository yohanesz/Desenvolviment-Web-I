import java.util.ArrayList;
import java.util.Scanner;

public class Geral {



    ArrayList <Aluno> listaAlunos = new ArrayList<>();
    Formatacao fmt = new Formatacao();
    Scanner scanner = new Scanner(System.in);

    public void inserirAluno(Aluno novoAluno) {
        listaAlunos.add(novoAluno);
    }

    public void listarMaiorIaa() {

        float alunoMaiorIAA = 0;
        String NomeAlunoMaiorIaa = null;

        for (int i=0; i < listaAlunos.size(); i++) {

            if (listaAlunos.get(i).getIAA() > alunoMaiorIAA) {

                alunoMaiorIAA = listaAlunos.get(i).getIAA();
                NomeAlunoMaiorIaa = listaAlunos.get(i).getNome();
                
        }
    }

    public void listarInformacoesAluno(int alunoSelecionado) {
        fmt.limparConsole();
        fmt.linha();
        System.out.println("Nome: " + listaAlunos.get(alunoSelecionado - 1).getNome());
        System.out.println("Idade: " + listaAlunos.get(alunoSelecionado - 1).getIdade() + " anos");
        System.out.println("IAA: " + listaAlunos.get(alunoSelecionado - 1).getIAA());
        System.out.println("Curso: " + listaAlunos.get(alunoSelecionado - 1).getCurso());
        fmt.linha();
    }

    public void listarAlunos() {
        
        fmt.linha();

        if (listaAlunos.isEmpty()) {
            System.out.println("A lista de alunos está vazia.");
            return;
        }

        for(int i=0; i < listaAlunos.size(); i++) {
            int indicador = i+1;
            System.out.println("[" + indicador + "]" + listaAlunos.get(i).getNome());
        }

        fmt.linha();
    }

    public void removerAluno(int indiceAluno) {


        String alunoRemovido = listaAlunos.get(indiceAluno).getNome();

        listaAlunos.remove(indiceAluno - 1);
        
        fmt.linha();
        System.out.println("O aluno " + alunoRemovido + "foi removido");
        fmt.linha();

    }

    public ArrayList<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public void setListaAlunos(ArrayList<Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }

    public Formatacao getFmt() {
        return fmt;
    }

    public void setFmt(Formatacao fmt) {
        this.fmt = fmt;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    
}
