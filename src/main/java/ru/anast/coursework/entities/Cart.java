package ru.anast.coursework.entities;

import javax.persistence.*;

@Entity(name = "cart")
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userID;

    private int pizzaNumber;
    private int pizzaCount;

    public int getpizzaCount() {
        return pizzaCount;
    }

    public void setpizzaCount(int pizzaCount) {
        this.pizzaCount = pizzaCount;
    }

    public Cart(User userID, int pizzaNumber) {
        this.userID = userID;
        this.pizzaNumber = pizzaNumber;
    }

    public Cart() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserID() {
        return userID;
    }

    public String getPhotoAddress(){
        return "pizza_" + String.valueOf(pizzaNumber) + ".png";
    }

    public String getPrice(boolean totalCost){
        switch (this.pizzaNumber){
            case 1:
                if (totalCost)
                    return String.valueOf(830 * pizzaCount) + " руб.";
                else
                    return String.valueOf(830) + " руб.";
            case 2:
                if (totalCost)
                    return String.valueOf(610 * pizzaCount) + " руб.";
                else
                    return String.valueOf(610) + " руб.";
            case 3:
                if (totalCost)
                    return String.valueOf(480 * pizzaCount) + " руб.";
                else
                    return String.valueOf(480) + " руб.";
            case 4:
                if (totalCost)
                    return String.valueOf(500 * pizzaCount) + " руб.";
                else
                    return String.valueOf(500) + " руб.";
            case 5:
                if (totalCost)
                    return String.valueOf(660 * pizzaCount) + " руб.";
                else
                    return String.valueOf(660) + " руб.";
            case 6:
                if (totalCost)
                    return String.valueOf(900 * pizzaCount) + " руб.";
                else
                    return String.valueOf(900) + " руб.";
            case 7:
                if (totalCost)
                    return String.valueOf(700 * pizzaCount) + " руб.";
                else
                    return String.valueOf(700) + " руб.";
            case 8:
                if (totalCost)
                    return String.valueOf(400 * pizzaCount) + " руб.";
                else
                    return String.valueOf(400) + " руб.";
            case 9:
                if (totalCost)
                    return String.valueOf(400 * pizzaCount) + " руб.";
                else
                    return String.valueOf(400) + " руб.";
            default:
                return "Неизвестно";
        }
    }

    public String getName(){
        switch (this.pizzaNumber){
            case 1:
                return "Говядина спайси";
            case 2:
                return "Мясная с ананасом";
            case 3:
                return "Терияки";
            case 4:
                return "Тунец с рукколой";
            case 5:
                return "Бургер";
            case 6:
                return "Диабло";
            case 7:
                return "Мельница Фирменная";
            case 8:
                return "Курица и грибы";
            case 9:
                return "Салями";
            default:
                return "Неизвестный букет";
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userID=" + userID +
                ", pizzaNumber=" + pizzaNumber +
                ", pizzaCount=" + pizzaCount +
                '}';
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public int getPizzaNumber() {
        return pizzaNumber;
    }

    public void setPizzaNumber(int pizzaNumber) {
        this.pizzaNumber = pizzaNumber;
    }
}
