package br.com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.springboot.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u where u.id > :id")
    public List<User> findMoreThan(@Param("id") int id);

    public List<User> findByIdGreaterThan(int id);

    public List<User> findByNameIgnoreCase(String name);

}
