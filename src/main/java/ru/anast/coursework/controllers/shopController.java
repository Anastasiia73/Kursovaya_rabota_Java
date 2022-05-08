package ru.anast.coursework.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.anast.coursework.entities.Cart;
import ru.anast.coursework.entities.User;
import ru.anast.coursework.services.CartService;
import ru.anast.coursework.services.UserService;

@Controller
public class shopController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    private void workWithCart(User user, int pizzaNumber, boolean plus){
        if (user.getPCount(pizzaNumber) == 0){
            if (!plus)
                return;
            Cart cart = new Cart(user, pizzaNumber);
            cart.setpizzaCount(1);
            user.getList().add(cart);
            cartService.addCart(cart);
        }   else {
                Cart cart = user.getP(pizzaNumber);
                if (user.getPCount(pizzaNumber) == 1 & !plus){
                    user.getList().remove(cart);
                    userService.rootResaveUser(user);
                    cartService.deleteCart(cart);
                }
                else
                    cartService.changeCountOfPizza(cart, plus);
            }
    }

    @GetMapping("/add_p{number:\\d+}")
    public String addP(@PathVariable int number){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        workWithCart((User) auth.getPrincipal(), number, true);
        return "redirect:/shop#p" + String.valueOf(number);
    }

    @GetMapping("/rem_p{number:\\d+}")
    public String remP(@PathVariable int number){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        workWithCart((User) auth.getPrincipal(), number, false);
        return "redirect:/shop#p" + String.valueOf(number);
    }


}
