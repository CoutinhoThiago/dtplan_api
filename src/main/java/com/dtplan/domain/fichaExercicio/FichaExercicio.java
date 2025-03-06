package com.dtplan.domain.fichaExercicio;

import com.dtplan.domain.exercicio.Exercicio;
import com.dtplan.domain.ficha.Ficha;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "FichaExercicio")
@Table(name = "ficha_exercicio")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@EqualsAndHashCode(of = "id")
public class FichaExercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ficha_id", nullable = false)
    private Ficha ficha; // A ficha à qual este exercício pertence

    @ManyToOne
    @JoinColumn(name = "exercicio_id", nullable = false)
    private Exercicio exercicio; // O exercício relacionado à ficha

    private int repeticoes;
    //private Integer repeticoes_min;
    //private Integer repeticoes_max;

    private int series; // Número de séries para o exercício na ficha
    private double carga; // Peso atual para o exercício na ficha

    private int duracao_minutos;

    private int intensidade;


    public FichaExercicio(Ficha ficha, Exercicio exercicio, int repeticoes, int series, double carga, int duracao_minutos, int intensidade) {
        this.ficha = ficha;
        this.exercicio = exercicio;

        this.repeticoes = repeticoes;
        this.series = series;
        this.carga = carga;

        this.duracao_minutos = duracao_minutos;
        this.intensidade = intensidade;
    }

    public void atualizarInformacoes(int series, double carga) {
        if (series > 0) {
            this.series = series;
        }
        if (carga >= 0) {
            this.carga = carga;
        }
    }
}
