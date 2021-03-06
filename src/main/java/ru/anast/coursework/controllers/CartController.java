package ru.anast.coursework.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.anast.coursework.entities.Cart;
import ru.anast.coursework.entities.User;
import ru.anast.coursework.services.CartService;
import ru.anast.coursework.services.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String getCart(Model model){
        User current_user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("pizzas", current_user.getList());
        return "cart";
    }

    @GetMapping("/cart-clear")
    public String clearCart(Model model){
        User current_user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Cart> current_cart = current_user.getList();
        Set<Cart> list = new HashSet<Cart>();
        current_user.setList(list);
        model.addAttribute("infoSetting", true);
        model.addAttribute("infoMessage", "Ваша корзина успешно очищена");
        userService.rootResaveUser(current_user);
        cartService.deleteCarts(current_cart);
        return "cart";
    }

    @GetMapping("/cart-order")
    public String orderCart(Model model){
        User current_user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Cart> current_cart = current_user.getList();
        String totalPrice = current_user.getAllTotalCost();
        userService.sendGoods(current_user, current_cart, totalPrice);
        Set<Cart> list = new HashSet<Cart>();
        current_user.setList(list);
        model.addAttribute("infoSetting", true);
        model.addAttribute("infoMessage", "Информация о заказе отправлена на " + current_user.getEmail());
        userService.rootResaveUser(current_user);
        cartService.deleteCarts(current_cart);
        return "cart";
    }

}
