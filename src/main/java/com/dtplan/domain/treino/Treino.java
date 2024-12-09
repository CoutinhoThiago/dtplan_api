package com.dtplan.domain.treino;

import com.dtplan.domain.treino.dto.CadastroTreinoDTO;
import com.dtplan.domain.treino.dto.EditarTreinoDTO;
import com.dtplan.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "treinos")
@Entity(name = "Treino")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String autor;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id") // , nullable = false)
    private Usuario usuario;

    public Treino(CadastroTreinoDTO dados) {
        this.descricao = dados.descricao();
        this.autor = dados.autor();
        this.tipo = dados.tipo();
        this.usuario = dados.usuario();
    }

    public void atualizarInformacoes(EditarTreinoDTO dados) {
        if (descricao != null) {
            this.descricao = dados.descricao();
        }
        if (autor != null) {
            this.autor = dados.autor();
        }
        if (tipo != null) {
            this.tipo = Tipo.valueOf(dados.tipo());
        }
    }
}