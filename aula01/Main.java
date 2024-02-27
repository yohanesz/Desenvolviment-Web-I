import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        
    Geral geral = new Geral();
    Scanner scanner = new Scanner(System.in);
    Formatacao fmt = new Formatacao();
    boolean verificador = true;

    do {

        fmt.limparConsole();
        fmt.linha();
        System.out.println("Selecione uma opção:");
        fmt.linha();
        System.out.println("[1] - Cadastrar aluno");
        System.out.println("[2] - Remover aluno");
        System.out.println("[3] - Listar alunos");
        System.out.println("[4] - Listar informações do aluno");
        System.out.println("[5] - Maior IAA");
        System.out.println("[6] - Encerrar programa");
        fmt.linha();
        System.out.print("R: ");
        int option = scanner.nextInt();
        
        

        switch (option) {
            case 1:
                // cadastrar aluno
                scanner.nextLine();
                fmt.limparConsole();
                fmt.linha();

                System.out.println("Insira o nome do aluno: ");
                fmt.linha();
                System.out.println("");
                System.out.print("R: ");
                String nomeAluno = scanner.nextLine();
                fmt.limparConsole();
                fmt.linha();

                System.out.println("Insira a idade do aluno:");
                fmt.linha();
                System.out.println("");
                System.out.print("R: ");
                int idadeAluno = scanner.nextInt();
                fmt.limparConsole();
                fmt.linha();

                System.out.println("Insira o IAA do aluno: ");
                fmt.linha();
                System.out.println("");
                System.out.print("R: ");
                int iaaAluno = scanner.nextInt();
                fmt.limparConsole();
                fmt.linha();


                System.out.println("Selecione o curso desejado: ");
                fmt.linha();
                System.out.println("[1] - Ciência da Computação");
                System.out.println("[2] - Matemática");
                System.out.println("[3] - Pedagogia");
                System.out.println("[4] - Física");
                fmt.linha();
                System.out.println("");
                System.out.print("R: ");
                
                int indiceCursoSelecionado = scanner.nextInt();

                System.out.print("Pressione enter para continuar...");
                scanner.nextLine();

                Curso cursoSelecionado = null;

                switch (indiceCursoSelecionado) {
                    case 1:
                        cursoSelecionado = Curso.CIENCIADACOMPUTACAO;
                    break;

                    case 2:
                        cursoSelecionado = Curso.MATEMATICA;
                    break;

                    case 3:
                        cursoSelecionado = Curso.PEDAGOGIA;
                    break;

                    case 4:
                        cursoSelecionado = Curso.FISICA;
                    break;
                
                    default:
                        break;
                }
                
                Aluno novoAluno = new Aluno(nomeAluno, idadeAluno, iaaAluno, cursoSelecionado);
                geral.inserirAluno(novoAluno);

                fmt.limparConsole();
                fmt.linha();
                System.out.println("Aluno Cadastrado com sucesso!");
                fmt.linha();
                System.out.println("");
                System.out.print("(Pressione enter para continuar...)");
                scanner.nextLine();

                break;

            case 2:
            // remover aluno
                fmt.limparConsole();
                fmt.linha();
                System.out.println("Selecione o aluno que deseja remover: ");

                geral.listarAlunos();
                System.out.println("");
                int indiceAluno = scanner.nextInt();

                try {
                    geral.removerAluno(indiceAluno);
                } catch (Exception e) {
                    System.out.println("Erro ao remover aluno.");
                }

                scanner.nextLine();
                

            break;

            case 3:
                //listar alunos
                fmt.limparConsole();
                geral.listarAlunos();
                System.out.println("");
                System.out.print("Pressione enter para continuar...");
                scanner.nextLine();

            break;

            case 4:
                // listar informações do aluno
                fmt.limparConsole();

                System.out.println("Selecione um aluno:");
                geral.listarAlunos();
                System.out.println("");
                fmt.linha();
                System.out.print("R: ");
                int alunoSelecionado = scanner.nextInt();
                geral.listarInformacoesAluno(alunoSelecionado);

                System.out.println("");
                System.out.print("Pressione enter para continuar...");
                scanner.nextLine();

            break;

            case 5:
                // maior IAA

                geral.listarMaiorIaa();

            break;

            case 6:
                //Encerrar programa
                fmt.limparConsole();
                fmt.linha();
                System.out.println("Pressione enter para continuar...");
                fmt.linha();
                scanner.nextLine();
                verificador = false;

            break;
        
            default:
                break;
        }


    } while (verificador);

    }
}