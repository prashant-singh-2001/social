package com.mephisto.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

/**
 * Entity representing a user in the application.
 * 
 * @author Prashant Singh
 *         on February 24, 2025.
 */
@Entity(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String role;

	@ManyToMany
	@JoinTable(name = "user_friends", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
	private List<User> friends;

	/**
	 * Default constructor.
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Parameterized constructor.
	 * 
	 * @param id       the user ID
	 * @param username the username
	 * @param password the password
	 * @param email    the email address
	 * @param role     the role of the user
	 */
	public User(Long id, String username, String password, String email, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	/**
	 * Gets the user ID.
	 * 
	 * @return the user ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the user ID.
	 * 
	 * @param id the user ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the username.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 * 
	 * @param username the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the email address.
	 * 
	 * @return the email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address.
	 * 
	 * @param email the email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the role of the user.
	 * 
	 * @return the role of the user
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role of the user.
	 * 
	 * @param role the role of the user
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets the list of friends.
	 * 
	 * @return the list of friends
	 */
	public List<User> getFriends() {
		return friends;
	}

	/**
	 * Sets the list of friends.
	 * 
	 * @param friends the list of friends
	 */
	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	/**
	 * Returns a string representation of the user.
	 * 
	 * @return a string representation of the user
	 */
	@Override
	public String toString() {
		return "User {" +
				"id=" + id +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", role='" + role + '\'' +
				'}';
	}
}