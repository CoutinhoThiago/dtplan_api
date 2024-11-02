package com.dtplan.domain.refeicao;

import com.dtplan.domain.alimento.Alimento;
import com.dtplan.domain.dieta.Dieta;
import com.dtplan.domain.exercicio.Exercicio;
import com.dtplan.domain.refeicao.dto.CadastrarRefeicaoDTO;
import com.dtplan.domain.treino.Treino;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "refeicoes")
@Entity(name = "Refeicao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Refeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "dieta_id") // , nullable = false)
    private Dieta dieta;

    @ManyToMany
    @JoinTable(
            name = "refeicao_alimento",
            joinColumns = @JoinColumn(name = "refeicoes_id"),
            inverseJoinColumns = @JoinColumn(name = "alimentos_id")
    )
    private List<Alimento> alimentos = new ArrayList<>();

    public Refeicao(String nome, Dieta dieta, List<Alimento> alimentos) {
        this.nome = nome;
        this.dieta = dieta;
        this.alimentos = alimentos;
    }
}
