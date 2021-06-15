package domain.users;

public final class UserNull implements User {
    @Override
    public int getId() {
	return -1;
    }

    @Override
    public void setId(int id) {
	// do nothing
    }

    @Override
    public String getEmail() {
	return "";
    }

    @Override
    public void setEmail(String email) {
	// do nothing
    }

    @Override
    public String getPassword() {
	return "";
    }

    @Override
    public void setPassword(String password) {
	// do nothing
    }

    @Override
    public int getRoleId() {
	return -1;
    }

    @Override
    public void setRoleId(int roleId) {
	// do nothing
    }

    @Override
    public String getMobilePhoneNumber() {
	return "";
    }

    @Override
    public void setMobilePhoneNumber(String mobilePhoneNumber) {
	// do nothing
    }

    @Override
    public String getFirstName() {
	return "";
    }

    @Override
    public void setFirstName(String firstName) {
	// do nothing
    }

    @Override
    public String getLastName() {
	return "";
    }

    @Override
    public void setLastName(String lastName) {
	// do nothing
    }

    @Override
    public String getShippingAddress() {
	return "";
    }

    @Override
    public void setShippingAddress(String shippingAddress) {
	// do nothing
    }
}
