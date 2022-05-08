package ru.anast.coursework.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anast.coursework.entities.Cart;
import ru.anast.coursework.repos.CartRepository;
import ru.anast.coursework.entities.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public List<Cart> findCartByUserID(User user){
        return cartRepository.findAllByUserID(user);
    }

    @Transactional
    public void addCart(Cart cart){
        cartRepository.save(cart);
    }

    @Transactional
    public Cart findCartByUserAndBNumber(User user, int pizza_number){
        return cartRepository.findCartByUserIDAndPizzaNumber(user, pizza_number);
    }

    @Transactional
    public void deleteCart(Cart cart){
        cartRepository.deleteById(cart.getId());
    }

    @Transactional
    public void deleteCarts(Set<Cart> cartSet){
        cartRepository.deleteAll(cartSet);
    }

    @Transactional
    public void changeCountOfPizza(Cart cart, boolean plus){
        if (plus){
            cart.setpizzaCount(cart.getpizzaCount() + 1);
        } else
            cart.setpizzaCount(cart.getpizzaCount() - 1);
        cartRepository.save(cart);
    }

}
