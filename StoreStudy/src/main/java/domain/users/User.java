package domain.users;

public interface User {
    public int getId();

    public void setId(int id);
    
    void setEmail(String email);

	String getEmail();

    public String getPassword();

    public void setPassword(String password);
}