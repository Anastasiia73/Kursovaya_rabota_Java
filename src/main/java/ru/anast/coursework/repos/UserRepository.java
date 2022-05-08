package ru.anast.coursework.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anast.coursework.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
