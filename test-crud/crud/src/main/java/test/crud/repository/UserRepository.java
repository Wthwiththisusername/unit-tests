package test.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.crud.users.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
}
