package com.dtplan.infra.config;

import com.dtplan.domain.exercicio.ExercicioRepository;
import com.dtplan.domain.exercicio.ExercicioService;
import com.dtplan.domain.exercicio.dto.CadastrarExercicioDTO;
import com.dtplan.domain.ficha.FichaRepository;
import com.dtplan.domain.ficha.FichaService;
import com.dtplan.domain.ficha.dto.CadastrarFichaDTO;
import com.dtplan.domain.treino.Tipo;
import com.dtplan.domain.treino.TreinoRepository;
import com.dtplan.domain.treino.TreinoService;
import com.dtplan.domain.treino.dto.CadastroTreinoDTO;
import com.dtplan.domain.usuario.Permissao;
import com.dtplan.domain.usuario.UsuarioRepository;
import com.dtplan.domain.usuario.UsuarioService;
import com.dtplan.domain.usuario.dto.CadastrarUsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final ExercicioRepository exercicioRepository;
    private final TreinoRepository treinoRepository;
    private final FichaRepository fichaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ExercicioService exercicioService;

    @Autowired
    private TreinoService treinoService;

    @Autowired
    private FichaService fichaService;

    public DataInitializer(UsuarioRepository usuarioRepository, ExercicioRepository exercicioRepository, TreinoRepository treinoRepository, FichaRepository fichaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.exercicioRepository = exercicioRepository;
        this.treinoRepository = treinoRepository;
        this.fichaRepository = fichaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verifica se já existe algum usuário no banco de dados
        if (usuarioRepository.count() == 0) {
            CadastrarUsuarioDTO dados = new CadastrarUsuarioDTO(
                    "tcoutinhossilva@gmail.com",
                    "12345678",
                    Permissao.ADMIN,
                    "admin",
                    "08866966584"
            );

            usuarioService.cadastrarUsuario(dados);
            System.out.println("Usuário admin criado com sucesso!");
        }

        // Cadastrando exercícios se não houver nenhum no banco
        if (exercicioRepository.count() == 0) {
            List<CadastrarExercicioDTO> exercicios = List.of(
                    // Exercícios para o treino A - Push (Peito e Ombros)
                    // ----------------------------------------------------------
                    new CadastrarExercicioDTO("Supino reto", true, 1, "Exercício para peito", "Peito", 3, 12, 20, 40, 60, 3),
                    new CadastrarExercicioDTO("Crucifixo com halteres", true, 1, "Exercício para peito", "Peito", 3, 12, 20, 30, 60, 3),
                    new CadastrarExercicioDTO("Supino inclinado", true, 1, "Exercício para peito", "Peito", 3, 12, 20, 40, 60, 3),
                    new CadastrarExercicioDTO("Supino declinado", true, 1, "Exercício para peito", "Peito", 3, 12, 20, 40, 60, 3),
                    new CadastrarExercicioDTO("Tríceps coice com halter", true, 1, "Exercício para tríceps", "Braços", 3, 12, 20, 20, 60, 3),
                    new CadastrarExercicioDTO("Tríceps pulley corda", true, 1, "Exercício para tríceps", "Braços", 3, 12, 20, 30, 60, 3),
                    new CadastrarExercicioDTO("Elevação lateral com halteres", true, 1, "Exercício para ombros", "Ombros", 3, 12, 20, 15, 60, 3),
                    new CadastrarExercicioDTO("Desenvolvimento com halteres (pegada neutra)", true, 1, "Exercício para ombros", "Ombros", 3, 12, 20, 25, 60, 3),

                    // Exercícios para o treino B - Pull (Costas e Bíceps)
                    // ----------------------------------------------------------
                    new CadastrarExercicioDTO("Puxador frontal pegada pronada", true, 1, "Exercício para costas", "Costas", 3, 12, 20, 40, 60, 3),
                    new CadastrarExercicioDTO("Puxador frontal unilateral", true, 1, "Exercício para costas", "Costas", 3, 12, 20, 30, 60, 3),
                    new CadastrarExercicioDTO("Remada cavalinho (pegada neutra)", true, 1, "Exercício para costas", "Costas", 3, 12, 20, 50, 60, 3),
                    new CadastrarExercicioDTO("Remada cavalinho (pegada pronada)", true, 1, "Exercício para costas", "Costas", 3, 12, 20, 50, 60, 3),
                    new CadastrarExercicioDTO("Rosca direta com barra W", true, 1, "Exercício para bíceps", "Braços", 3, 12, 20, 15, 60, 3),
                    new CadastrarExercicioDTO("Rosca scott com barra W", true, 1, "Exercício para bíceps", "Braços", 3, 12, 20, 15, 60, 3),
                    new CadastrarExercicioDTO("Rosca inversa com barra W", true, 1, "Exercício para bíceps", "Braços", 3, 12, 20, 10, 60, 3),
                    new CadastrarExercicioDTO("Barra Fixa", true, 1, "Exercício para costas", "Costas", 3, 12, 20, 0, 60, 4),
                    new CadastrarExercicioDTO("Face Pull", true, 1, "Exercício para ombros e costas", "Ombros", 3, 12, 20, 25, 60, 3),
                    new CadastrarExercicioDTO("Pulldown", true, 1, "Exercício para costas", "Costas", 3, 12, 20, 40, 60, 3),

                    // Exercícios para o treino C - Legs (Pernas)
                    // ----------------------------------------------------------
                    new CadastrarExercicioDTO("Agachamento búlgaro", true, 1, "Exercício para pernas", "Pernas", 3, 12, 20, 0, 60, 3),
                    new CadastrarExercicioDTO("Agachamento smith", true, 1, "Exercício para pernas", "Pernas", 3, 12, 20, 60, 60, 3),
                    new CadastrarExercicioDTO("Agachamento sumô no smith", true, 1, "Exercício para pernas", "Pernas", 3, 12, 20, 60, 60, 3),
                    new CadastrarExercicioDTO("Cadeira extensora", true, 1, "Exercício para pernas", "Pernas", 3, 12, 20, 40, 60, 3),
                    new CadastrarExercicioDTO("Cadeira abdutora", true, 1, "Exercício para pernas", "Pernas", 3, 12, 20, 40, 60, 3),
                    new CadastrarExercicioDTO("Mesa flexora", true, 1, "Exercício para pernas", "Pernas", 3, 12, 20, 40, 60, 3),
                    new CadastrarExercicioDTO("Stiff", true, 1, "Exercício para pernas e costas", "Pernas", 3, 12, 20, 60, 60, 3),
                    new CadastrarExercicioDTO("Panturrilha sentado máquina", true, 1, "Exercício para panturrilhas", "Pernas", 3, 12, 20, 40, 60, 3),
                    new CadastrarExercicioDTO("Leg Press", true, 1, "Exercício para pernas", "Pernas", 3, 12, 20, 100, 60, 3),
                    new CadastrarExercicioDTO("Agachamento Frontal", true, 1, "Exercício para pernas", "Pernas", 3, 12, 20, 60, 60, 3),
                    new CadastrarExercicioDTO("Leg Curl", true, 1, "Exercício para pernas", "Pernas", 3, 12, 20, 40, 60, 3),
                    new CadastrarExercicioDTO("Avanço", true, 1, "Exercício para pernas", "Pernas", 3, 12, 20, 50, 60, 3),

                    // Exercícios HIIT
                    // ----------------------------------------------------------
                    new CadastrarExercicioDTO("HIIT", true, 1, "Exercício para resistência", "HIIT", 12, 10, 12, 8, 18, 12),

                    // Exercícios Abdominais
                    // ----------------------------------------------------------
                    new CadastrarExercicioDTO("Abdominais", true, 1, "Exercício para abdômen", "Abdômen", 4, 15, 20, 0, 60, 3)
            );

            // Cadastra cada exercício no banco de dados
            exercicios.forEach(exercicioService::cadastrarExercicio);
            System.out.println("Exercícios cadastrados com sucesso!");
        }

        // Cadastrando treinos se não houver nenhum no banco
        if (treinoRepository.count() == 0) {
            List<CadastroTreinoDTO> treinos = List.of(
                    new CadastroTreinoDTO("Treino ABC", "tcoutinhossilva@gmail.com", Tipo.MUSCULACAO, usuarioRepository.findByEmail("tcoutinhossilva@gmail.com")),
                    new CadastroTreinoDTO("Rotina de Cárdio", "tcoutinhossilva@gmail.com", Tipo.AEROBICO, usuarioRepository.findByEmail("tcoutinhossilva@gmail.com")),
                    new CadastroTreinoDTO("Rotina de Abdominal", "tcoutinhossilva@gmail.com", Tipo.MUSCULACAO, usuarioRepository.findByEmail("tcoutinhossilva@gmail.com")),
                    new CadastroTreinoDTO("Treino sem ficha (teste)", "tcoutinhossilva@gmail.com", Tipo.MUSCULACAO, usuarioRepository.findByEmail("tcoutinhossilva@gmail.com"))
            );

            // Cadastra cada treino no banco de dados
            treinos.forEach(treinoService::cadastrarTreino);
            System.out.println("Treinos cadastrados com sucesso!");
        }

        // Agora você pode adicionar as fichas para o treino
        if (fichaRepository.count() == 0) {
            List<CadastrarFichaDTO> fichas = List.of(
                    // Fichas para o treino ABC
                    new CadastrarFichaDTO("Ficha A - Push", 1L, getExerciseIdsByName(List.of(
                            "Supino reto",
                            "Crucifixo com halteres",
                            "Supino inclinado",
                            "Supino declinado",
                            "Tríceps coice com halter",
                            "Tríceps pulley corda",
                            "Elevação lateral com halteres",
                            "Desenvolvimento com halteres (pegada neutra)"
                    ))),

                    new CadastrarFichaDTO("Ficha B - Pull", 1L, getExerciseIdsByName(List.of(
                            "Puxador frontal pegada pronada",
                            "Puxador frontal unilateral",
                            "Remada cavalinho (pegada neutra)",
                            "Remada cavalinho (pegada pronada)",
                            "Rosca direta com barra W",
                            "Rosca scott com barra W",
                            "Rosca inversa com barra W"
                    ))),

                    new CadastrarFichaDTO("Ficha C - Legs", 1L, getExerciseIdsByName(List.of(
                            "Agachamento búlgaro",
                            "Agachamento smith",
                            "Agachamento sumô no smith",
                            "Cadeira extensora",
                            "Cadeira abdutora",
                            "Mesa flexora",
                            "Stiff",
                            "Panturrilha sentado máquina"
                    ))),
                    new CadastrarFichaDTO("Ficha HIIT", 2L, getExerciseIdsByName(List.of("HIIT"))),
                    new CadastrarFichaDTO("Ficha Abdominal", 3L, getExerciseIdsByName(List.of("Abdominais")))
            );

            // Cadastra cada ficha no banco de dados
            fichas.forEach(fichaService::cadastrarFicha);
            System.out.println("Fichas cadastradas com sucesso!");
        }
    }

    private List<Long> getExerciseIdsByName(List<String> exerciseNames) {
        return exerciseNames.stream()
                .map(name -> exercicioRepository.findByNome(name)
                        .map(exercicio -> exercicio.getId())
                        .orElseThrow(() -> new RuntimeException("Exercício não encontrado: " + name)))
                .collect(Collectors.toList());
    }
}
