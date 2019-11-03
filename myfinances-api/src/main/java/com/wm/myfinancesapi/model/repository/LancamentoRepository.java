package com.wm.myfinancesapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wm.myfinancesapi.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}