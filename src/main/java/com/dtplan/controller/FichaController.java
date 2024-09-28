package com.dtplan.controller;

import com.dtplan.domain.ficha.Ficha;
import com.dtplan.domain.ficha.FichaRepository;
import com.dtplan.domain.ficha.FichaService;
import com.dtplan.domain.ficha.dto.CadastrarFichaDTO;
import com.dtplan.domain.ficha.dto.DetalharFichaDTO;
import com.dtplan.domain.ficha.dto.ListarFichaDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/fichas")
public class FichaController {
	
	@Autowired
	private FichaRepository treinoRepository;
	@Autowired
	private FichaRepository fichaRepository;

	@Autowired
	private FichaService fichaService;

	@PostMapping
	@Transactional
	public DetalharFichaDTO cadastrar(@RequestBody CadastrarFichaDTO dados, UriComponentsBuilder uriBuilder) {
		return fichaService.cadastrarFicha(dados);
	}

	@GetMapping("/listar")
    public ResponseEntity<Page<ListarFichaDTO>> listarTodas(@PageableDefault(size = 10) Pageable paginacao) {
		var page = fichaRepository.findAll(paginacao).map(ListarFichaDTO::new);
		return ResponseEntity.ok(page);
    }
	@GetMapping("/listar/{id}")
	public ResponseEntity<Page<ListarFichaDTO>> listar(@PathVariable long id, @PageableDefault(size = 10) Pageable paginacao) {
		List<Ficha> listaFichas = fichaRepository.findByTreinoId(id);

		// Cria uma página de fichas com base na lista de fichas e na paginação fornecida
		Page<Ficha> paginaFichas = new PageImpl<>(listaFichas, paginacao, listaFichas.size());

		// Converte a página de fichas em uma página de DTOs
		Page<ListarFichaDTO> dto = paginaFichas.map(ListarFichaDTO::new);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/detalhar/{id}")
	public ResponseEntity<?> detalhar(@PathVariable long id) {
		Ficha ficha = fichaRepository.findById(id).orElseThrow(() -> new RuntimeException("Ficha não encontrado"));

		DetalharFichaDTO dto = new DetalharFichaDTO(ficha);
		return ResponseEntity.ok(dto);
	}
}
