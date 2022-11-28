package br.com.seg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.seg.entity.Modulo;

@Repository
public interface ModuleRepository extends JpaRepository<Modulo, String>{

	List<Modulo> findBymoduleId(String moduleId);
}
