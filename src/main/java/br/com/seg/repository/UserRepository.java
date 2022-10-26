package br.com.seg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.seg.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
