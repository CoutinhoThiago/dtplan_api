package com.dtplan.domain.dieta;

import com.dtplan.domain.dieta.dto.CadastrarDietaDTO;
import com.dtplan.domain.dieta.dto.EditarDietaDTO;
import com.dtplan.domain.refeicao.Refeicao;
import com.dtplan.domain.refeicao.RefeicaoRepository;
import com.dtplan.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "dietas")
@Entity(name = "Dieta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Dieta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String autor;

    @Enumerated(EnumType.STRING)
    private TipoDieta tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id") // , nullable = false)
    private Usuario usuario;

    private Float calorias;
    private Float proteina;
    private Float gordura;
    private Float carboidrato;

    @ManyToMany
    @JoinTable(
            name = "dietas_refeicoes",
            joinColumns = @JoinColumn(name = "dieta_id"),
            inverseJoinColumns = @JoinColumn(name = "refeicao_id")
    )
    private List<Refeicao> refeicoes;

    public Dieta(CadastrarDietaDTO dados) {
        this.descricao = dados.descricao();
        this.autor = dados.autor();
        this.tipo = dados.tipo();
        this.usuario = dados.usuario();
    }

    public void atualizaInformacoes(EditarDietaDTO dados) {
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.autor() != null) {
            this.autor = dados.autor();
        }
        if (dados.tipo() != null) {
            this.tipo = dados.tipo();
        }
        if (dados.usuario() != null) {
            this.usuario = dados.usuario();
        }

        if (dados.calorias() != null) {
            this.calorias = dados.calorias();
        }
        if (dados.proteina() != null) {
            this.proteina = dados.proteina();
        }
        if (dados.gordura() != null) {
            this.gordura = dados.gordura();
        }
        if (dados.carboidrato() != null) {
            this.carboidrato = dados.carboidrato();
        }
    }

    public void adicionarRefeicoesPorIds(List<Long> idsRefeicoes, RefeicaoRepository refeicaoRepository) {
        List<Refeicao> refeicoesNovas = refeicaoRepository.findByIdIn(idsRefeicoes);
        if (this.refeicoes == null) {
            this.refeicoes = refeicoesNovas; // Inicializa a lista se estiver vazia
        } else {
            this.refeicoes.addAll(refeicoesNovas); // Adiciona as novas refeições à lista existente
        }
    }
}
