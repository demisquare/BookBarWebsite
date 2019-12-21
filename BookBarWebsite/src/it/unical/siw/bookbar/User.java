package it.unical.siw.bookbar;

public class User {
	private String username;
	private int id;
	private String role;
	
	public User(int id, String username, String role) {
		this.username = username;
		this.id = id;
		this.role = role;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
