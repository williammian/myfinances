package com.wm.myfinancesapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wm.myfinancesapi.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
