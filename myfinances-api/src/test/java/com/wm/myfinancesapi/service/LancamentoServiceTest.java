package com.wm.myfinancesapi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wm.myfinancesapi.exception.RegraNegocioException;
import com.wm.myfinancesapi.model.entity.Lancamento;
import com.wm.myfinancesapi.model.enums.StatusLancamento;
import com.wm.myfinancesapi.model.repository.LancamentoRepository;
import com.wm.myfinancesapi.model.repository.LancamentoRepositoryTest;
import com.wm.myfinancesapi.service.impl.LancamentoServiceImpl;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class LancamentoServiceTest {
	
	@SpyBean
	LancamentoServiceImpl service;
	
	@MockBean
	LancamentoRepository repository;
	
	@Test
	public void deveSalvarUmLancamento() {
		//cenário
		Lancamento lancamentoASalvar = LancamentoRepositoryTest.criarLancamento();
		doNothing().when(service).validar(lancamentoASalvar);
		
		Lancamento lancamentoSalvo = LancamentoRepositoryTest.criarLancamento();
		lancamentoSalvo.setId(1l);
		lancamentoSalvo.setStatus(StatusLancamento.PENDENTE);
		when(repository.save(lancamentoASalvar)).thenReturn(lancamentoSalvo);
		
		//execucao
		Lancamento lancamento = service.salvar(lancamentoASalvar);
		
		//verificação
		assertThat( lancamento.getId() ).isEqualTo(lancamentoSalvo.getId());
		assertThat(lancamento.getStatus()).isEqualTo(StatusLancamento.PENDENTE);
	}
	
	@Test
	public void naoDeveSalvarUmLancamentoQuandoHouverErroDeValidacao() {
		//cenário
		Lancamento lancamentoASalvar = LancamentoRepositoryTest.criarLancamento();
		doThrow( RegraNegocioException.class ).when(service).validar(lancamentoASalvar);
		
		//execucao e verificacao
		catchThrowableOfType( () -> service.salvar(lancamentoASalvar), RegraNegocioException.class );
		verify(repository, never()).save(lancamentoASalvar);
	}
	
}
