package it.unical.siw.bookbar;

public class User {
	public String username;
	public int id;
	public String role;
	public User(int _id, String _username, String _role) {
		username = _username;
		id = _id;
		role = _role;
	}
	public String getUsername() {
		return username;
	}
	public int getId() {
		return id;
	}
	public String getRole() {
		return role;
	}
}
