package br.com.seg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.seg.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, String>{

}
