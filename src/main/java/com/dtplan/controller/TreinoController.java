package com.dtplan.controller;

import com.dtplan.domain.exercicio.ExercicioService;
import com.dtplan.domain.ficha.Ficha;
import com.dtplan.domain.ficha.FichaRepository;
import com.dtplan.domain.treino.Treino;
import com.dtplan.domain.treino.TreinoService;
import com.dtplan.domain.treino.dto.DadosDetalharTreinoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.dtplan.domain.treino.dto.CadastroTreinoDTO;
import com.dtplan.domain.treino.dto.ListarTreinoDTO;
import com.dtplan.domain.treino.TreinoRepository;
import jakarta.transaction.Transactional;

import java.util.List;

@RestController
@RequestMapping("/treinos")
public class TreinoController {
	
	@Autowired
	private TreinoRepository treinoRepository;
	@Autowired
	private FichaRepository fichaRepository;

	@Autowired
	private ExercicioService exercicioService;
	@Autowired
	private TreinoService treinoService;

	@PostMapping
	@Transactional
	public CadastroTreinoDTO cadastrar(@RequestBody CadastroTreinoDTO dados, UriComponentsBuilder uriBuilder) {
		return treinoService.cadastrarTreino(dados);
	}
	
	@GetMapping("/listar")
    public ResponseEntity<Page<ListarTreinoDTO>> listar(@PageableDefault(size = 10) Pageable paginacao) {
		var page = treinoRepository.findAll(paginacao).map(ListarTreinoDTO::new);
		
		return ResponseEntity.ok(page);
    }

	@GetMapping("/detalhar/{id}")
	public ResponseEntity<?> detalhar(@PathVariable long id) {
		Treino treino = treinoRepository.findById(id).orElseThrow(() -> new RuntimeException("Treino não encontrado"));
		List<Ficha> fichas = fichaRepository.findByTreinoId(treino.getId());

		DadosDetalharTreinoDTO detalhesTreino = new DadosDetalharTreinoDTO(treino, fichas);
		return ResponseEntity.ok(detalhesTreino);
	}
}
