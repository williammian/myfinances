package com.wm.myfinancesapi.api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.myfinancesapi.api.dto.LancamentoDTO;
import com.wm.myfinancesapi.exception.RegraNegocioException;
import com.wm.myfinancesapi.model.entity.Lancamento;
import com.wm.myfinancesapi.model.entity.Usuario;
import com.wm.myfinancesapi.model.enums.StatusLancamento;
import com.wm.myfinancesapi.model.enums.TipoLancamento;
import com.wm.myfinancesapi.service.LancamentoService;
import com.wm.myfinancesapi.service.UsuarioService;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoResource {

	private LancamentoService service;
	
	private UsuarioService usuarioService;

	public LancamentoResource(LancamentoService service, UsuarioService usuarioService) {
		this.service = service;
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity salvar(@RequestBody LancamentoDTO dto) {
		try {
			Lancamento entidade = converter(dto);
			entidade = service.salvar(entidade);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
		}catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody LancamentoDTO dto) {
		return service.obterPorId(id).map( entity -> {
			try {
				Lancamento lancamento = converter(dto);
				lancamento.setId(entity.getId());
				service.atualizar(lancamento);
				return ResponseEntity.ok(lancamento);
			}catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet( () -> 
			new ResponseEntity("Lançamento não encontrado na base de dados", HttpStatus.BAD_REQUEST));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") Long id) {
		return service.obterPorId(id).map( entity -> {
			service.deletar(entity);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> 
			new ResponseEntity("Lançamento não encontrado na base de dados", HttpStatus.BAD_REQUEST));
	}
	
	private Lancamento converter(LancamentoDTO dto) {
		Lancamento lancamento = new Lancamento();
		
		lancamento.setId(dto.getId());
		lancamento.setDescricao(dto.getDescricao());
		lancamento.setAno(dto.getAno());
		lancamento.setMes(dto.getMes());
		lancamento.setValor(dto.getValor());
		
		Usuario usuario = usuarioService
			.obterPorId(dto.getUsuario())
			.orElseThrow(() -> new RegraNegocioException("Usuário não encontrado para o id informado"));
		
		lancamento.setUsuario(usuario);
		lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
		lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));
		
		return lancamento;
	}
	
}
