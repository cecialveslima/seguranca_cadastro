package br.com.seg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.seg.entity.Modulo;

@Repository
public interface ModuleRepository extends JpaRepository<Modulo, String>{

}
