package org.generation.blogvagalume.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogvagalume.model.PostagemVagalume;
import org.generation.blogvagalume.repository.PostagemRepository;
import org.generation.blogvagalume.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	private static final HttpStatus NOT_FOUND = null;
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping
	public ResponseEntity<List<PostagemVagalume>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<PostagemVagalume> getById(@PathVariable  Long id){
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<PostagemVagalume>> getByTitulo(@PathVariable  String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	@PostMapping
	public ResponseEntity<PostagemVagalume> post(@Valid @RequestBody PostagemVagalume postagem){
		if (temaRepository.existsById(postagem.getTema().getId()))
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemRepository.save(postagem));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	@PutMapping
	public ResponseEntity<PostagemVagalume> put(@Valid @RequestBody PostagemVagalume postagem){
		if (postagemRepository.existsById(postagem.getId())) {
			
		if (temaRepository.existsById(postagem.getTema().getId()))
		return ResponseEntity.status(HttpStatus.OK)
				.body(postagemRepository.save(postagem));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<PostagemVagalume> postagem = postagemRepository.findById(id);
		
		if (postagem.isEmpty())
			throw new ResponseStatusException(NOT_FOUND);
		postagemRepository.deleteById(id);
	}
}
