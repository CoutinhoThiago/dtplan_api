package com.dtplan.controller;

import com.dtplan.domain.dieta.Dieta;
import com.dtplan.domain.dieta.DietaRepository;
import com.dtplan.domain.dieta.DietaService;
import com.dtplan.domain.dieta.dto.CadastrarDietaDTO;
import com.dtplan.domain.dieta.dto.DetalharDietaDTO;
import com.dtplan.domain.dieta.dto.EditarDietaDTO;
import com.dtplan.domain.dieta.dto.ListarDietaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/dietas")
	public class DietaController {

	@Autowired
	private DietaRepository dietaRepository;

	@Autowired
	private DietaService dietaService;

	@PostMapping("/criar")
	public ResponseEntity<CadastrarDietaDTO> cadastrar(@RequestBody CadastrarDietaDTO dados, UriComponentsBuilder uriBuilder) {
		var dto = dietaService.cadastrarDieta(dados);

		return ResponseEntity.ok(dto);
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<EditarDietaDTO> editar(@PathVariable long id, @RequestBody EditarDietaDTO dados) {
		var dto = dietaService.editarDieta(id, dados);

		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/listar")
    public ResponseEntity<Page<?>> listar(@PageableDefault(size = 10) Pageable paginacao) {
		var page = dietaRepository.findAll(paginacao).map(ListarDietaDTO::new);
		
		return ResponseEntity.ok(page);
    }

	@GetMapping("/listar/{id}")
	public ResponseEntity<?> detalhar(@PathVariable long id) {
		Dieta dieta = dietaRepository.findById(id).orElseThrow(() -> new RuntimeException("Dieta não encontrado"));

		DetalharDietaDTO detalharDieta = new DetalharDietaDTO(dieta);
		return ResponseEntity.ok(detalharDieta);
	}
}
