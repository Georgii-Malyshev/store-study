package domain.users;

public interface User {
    public int getId();

    public void setId(int id);

    public String getEmail();

    public void setEmail(String email);

    public String getPassword();

    public void setPassword(String password);

    public int getRoleId();

    public void setRoleId(int roleId);

    public String getMobilePhoneNumber();

    public void setMobilePhoneNumber(String mobilePhoneNumber);

    public String getFirstName();

    public void setFirstName(String firstName);

    public String getLastName();

    public void setLastName(String lastName);

    public String getShippingAddress();

    public void setShippingAddress(String shippingAddress);
}