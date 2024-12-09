package com.dtplan.domain.ficha;

import com.dtplan.domain.exercicio.Exercicio;
import com.dtplan.domain.exercicio.ExercicioRepository;
import com.dtplan.domain.exercicio.dto.EditarExercicioDTO;
import com.dtplan.domain.ficha.dto.CadastrarFichaDTO;
import com.dtplan.domain.treino.Treino;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Table(name = "fichas")
@Entity(name = "Ficha")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Ficha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    //private String descricao;
    //private String observacoes;

    @ManyToOne
    @JoinColumn(name = "treino_id") // , nullable = false)
    private Treino treino;

    @ManyToMany
    @JoinTable(
            name = "fichas_exercicios", // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "fichas_id"), // Coluna na tabela de junção que aponta para a chave primária da entidade Ficha
            inverseJoinColumns = @JoinColumn(name = "exercicios_id") // Coluna na tabela de junção que aponta para a chave primária da entidade Exercicio
    )
    private List<Exercicio> exercicios = new ArrayList<>();


    public Ficha(String nome, Treino  treino,  List<Exercicio> exercicios) {
        this.nome = nome;
        this.treino = treino;
        this.exercicios = exercicios;
    }
    public void atualizarInformacoes(String nome, List<Exercicio> exercicios) {
        if (nome != null) {
            this.nome = nome;
        }
        if (exercicios != null) {
            this.exercicios = exercicios;
        }
    }
}