package domain;


public class User {
	
	private String username;
	private String password;
	private String email;
	private Privilege privilege;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUsertype(Privilege privilege) { 
		this.privilege = privilege;
	}
	public Privilege getUsertype() { 
		return privilege;
	}
}
