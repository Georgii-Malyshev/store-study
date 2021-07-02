package service.domain.store;

import service.domain.users.AppUser;

public class Order {
    private int id;
    private AppUser user;
    private OrderStatus status;
    private String shippingAddress;
    private int price;
}
