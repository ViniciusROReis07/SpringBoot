package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
    
}
