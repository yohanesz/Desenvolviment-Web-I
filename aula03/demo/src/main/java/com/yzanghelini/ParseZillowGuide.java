package com.yzanghelini;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseZillowGuide {
    public static void main(String args[]) {

        ArrayList<Item> dataList = new ArrayList<>();
        String caminho = "demo/src/main/java/com/yzanghelini/index.html";

        File file = new File(caminho);

        try {

            Document document = Jsoup.parse(file, "UTF-8", "");
            Elements rows = document.select("tbody tr");

            float somaNotas = 0;
            int totalNotas = 0;

            Map<String, Integer> disciplinasPorProfessor = new HashMap<>();

            for (Element row : rows) {
                String codDisciplina = row.child(0).text();
                String nomeDisciplina = row.child(1).text();
                String professor = row.child(2).text();
                String notas = row.child(3).text();
                String faltas = row.child(4).text();

                Item item = new Item(codDisciplina, nomeDisciplina, professor, notas, faltas);
                dataList.add(item);

                // Manipulação das notas
                String[] notasArray = notas.split(",");
                for (String notaString : notasArray) {
                    float nota = Float.parseFloat(notaString.trim());
                    somaNotas += nota;
                    totalNotas++;
                }
            }

            // Ordena a lista de itens utilizando o compareTo implementado na classe Item
            Collections.sort(dataList);

            // Itera sobre a lista de objetos Item (movido para fora do loop principal)
            for (int i = 0; i < dataList.size(); i++) {
                Item objeto = dataList.get(i);
                String professorItem = objeto.getProfessor();

                // Verifica se o professor já está no Map
                if (disciplinasPorProfessor.containsKey(professorItem)) {
                    // Se o professor já está no Map, incrementa o contador
                    disciplinasPorProfessor.put(professorItem, disciplinasPorProfessor.get(professorItem) + 1);
                } else {
                    // Se o professor não está no Map, adiciona com contagem 1
                    disciplinasPorProfessor.put(professorItem, 1);
                }
            }

            // Encontrar o professor com mais disciplinas
            String professorComMaisDisciplinas = null;
            int maxDisciplinas = 0;

            for (Map.Entry<String, Integer> entry : disciplinasPorProfessor.entrySet()) {
                String professor = entry.getKey();
                int numeroDisciplinas = entry.getValue();

                // Atualiza se o número de disciplinas for maior que o máximo atual
                if (numeroDisciplinas > maxDisciplinas) {
                    professorComMaisDisciplinas = professor;
                    maxDisciplinas = numeroDisciplinas;
                }
            }

            // Calculando a média
            float mediaNotas = totalNotas > 0 ? somaNotas / totalNotas : 0;

            // Exibe os resultados
            System.out.println("Professor com mais disciplinas: " + professorComMaisDisciplinas
                    + ", Número de disciplinas: " + maxDisciplinas);
            System.out.println("Média de todas as notas: " + mediaNotas);

            // Exibe os resultados da lista ordenada
            System.out.println("Lista de itens ordenados:");
            for (Item item : dataList) {
                System.out.println(item);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
