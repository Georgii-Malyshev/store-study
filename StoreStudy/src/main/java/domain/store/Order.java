package domain.store;

import domain.users.User;

public class Order {
    private int id;
    private User user;
    private OrderStatus status;
    private String shippingAddress;
    private int price;
}
