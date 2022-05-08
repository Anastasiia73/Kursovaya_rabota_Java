package ru.anast.coursework.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anast.coursework.entities.Cart;
import ru.anast.coursework.entities.User;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserID(User user);
    Cart findCartByUserIDAndPizzaNumber(User user, int pizzaNumber);
}
