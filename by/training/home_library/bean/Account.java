package by.training.home_library.bean;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {
	
	private static final long serialVersionUID = -3606228445898368064L;
	
	private Role role;
	private String name;
	private String login;
	private String password;
	
	public Account() {}
	
	public Account(Role role, String name, String login, String password) {
		super();
		this.role = role;
		this.name = name;
		this.login = login;
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, name, password, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(login, other.login) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && role == other.role;
	}

	@Override
	public String toString() {
		return "Account [role=" + role + ", name=" + name + ", login=" + login + ", password=" + password + "]";
	}
	
}
