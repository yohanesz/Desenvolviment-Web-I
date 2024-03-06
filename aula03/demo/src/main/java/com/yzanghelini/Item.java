package com.yzanghelini;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Item implements Comparable<Item>{
    
    String codDisciplina;
    String nomeDisciplina;
    String professor;
    String notas;
    String faltas;

      private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    private static int extractNumber(String input) {
        Matcher matcher = NUMBER_PATTERN.matcher(input);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        return 0; // Retorna 0 se não encontrar nenhum número na string
    }

    @Override
    public int compareTo(Item outroItem) {
        // Compare por professor e, em caso de empate, por faltas
        int comparacaoProfessor = this.getProfessor().compareTo(outroItem.getProfessor());
        if (comparacaoProfessor != 0) {
            return comparacaoProfessor;
        } else {
            // Ordene por faltas crescentes
            return Integer.compare(extractNumber(this.getFaltas()), extractNumber(outroItem.getFaltas()));
        }
    }

    public Item(String codDisciplina, String nomeDisciplina, String professor, String notas, String faltas) {
        this.codDisciplina = codDisciplina;
        this.nomeDisciplina = nomeDisciplina;
        this.professor = professor;
        this.notas = notas;
        this.faltas = faltas;
    }

    @Override
    public String toString() {
        return "Item{" +
                "codDisciplina='" + codDisciplina + '\'' +
                ", nomeDisciplina='" + nomeDisciplina + '\'' +
                ", professor='" + professor + '\'' +
                ", notas='" + notas + '\'' +
                ", faltas='" + faltas + '\'' +
                '}';
    }

    public String getCodDisciplina() {
        return codDisciplina;
    }

    public void setCodDisciplina(String codDisciplina) {
        this.codDisciplina = codDisciplina;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getFaltas() {
        return faltas;
    }

    public void setFaltas(String faltas) {
        this.faltas = faltas;
    }

}
