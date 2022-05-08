package ru.anast.coursework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anast.coursework.entities.Cart;
import ru.anast.coursework.repos.UserRepository;
import ru.anast.coursework.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private MailService mailService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    @Transactional
    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User findUserById(int userId){
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    @Transactional
    @Async
    public void sendGoods(User user, Set<Cart> carts, String totalCost){
        String mailBody = "Спасибо за заказ в нашем магазине!\nВаш заказ:\n";
        for (Cart item: carts){
            mailBody += item.getName() + " | " + item.getPrice(false) + "/ед | " + item.getpizzaCount() + " шт. | " + item.getPrice(true) + "\n";
        }
        mailBody += "Итого: " + totalCost;
        mailService.sendEmail(user.getEmail(), "Заказ оформлен!", mailBody);
    }

    @Transactional
    public List<User> allUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void rootResaveUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public void rootResaveUserWithPassword(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public boolean saveUser(User user){
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null){
            return false;
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Transactional
    public boolean deleteUser(int userId){
        if (userRepository.findById(userId).isPresent()){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }


}

