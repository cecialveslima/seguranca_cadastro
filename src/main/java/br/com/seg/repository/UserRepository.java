package br.com.seg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.seg.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	//Para utilizar findBy + nome do campo no entity
	List<User> findByCodVendedor(int CodVendedor);
	
	//Para utilizar findBy + nome do campo no entity
	List<User> findByuserMail(String userMail);	

}
