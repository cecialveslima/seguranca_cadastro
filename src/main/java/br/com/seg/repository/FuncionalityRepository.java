package br.com.seg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.seg.entity.Funcionalidade;

@Repository
public interface FuncionalityRepository extends JpaRepository<Funcionalidade, String> {

}
