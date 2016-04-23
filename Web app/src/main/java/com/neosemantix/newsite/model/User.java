// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.model;

/**
 * @author umesh
 *
 */
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NS_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID", nullable = false)
	private int id;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "EMAIL", unique = true, nullable = false)
	private String email;

	@Column(name = "USER_NAME", unique = true, nullable = false)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "ENABLED")
	private Boolean isActive;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Role.class)
	@JoinColumn(name = "ROLE")
	//private List<Role> role;
	private Role role;

	/************************** Constructors ************************/
	public User() {
		super();
	}

	public User(final String email, final String password) {
		this.email = email;
		this.password = password;
	}

	public User(final User user) {
		this(user.id, user.firstName, user.lastName, user.email, user.username,
				user.password, user.role);
	}

	public User(final int id, final String firstName, final String lastName,
			final String email, final String username, final String password,
			final Role role) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	/**
	 * Convenient constructor which creates a new user account with password same as the user name (with the assumption
	 * that the user or admin account will reset the password). By default the account is also activated.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param userName
	 * @param email
	 * @param r
	 */
	public User(String firstName, String lastName, String userName, String email, Role r){
		this();
		setEmail(email);
		setFirstName(firstName);
		setIsActive(true);
		setLastName(lastName);
		setPassword(userName);
		setRole(r);
		setUsername(userName);
	}

	/********************** Getters and Setters **********************/
	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(final Boolean isActive) {
		this.isActive = isActive;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/********************* Equals and HashCode **********************/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (this.email == null ? 0 : this.email.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final User other = (User) obj;
		if (this.email == null) {
			if (other.email != null)
				return false;
		} else if (!this.email.equals(other.email))
			return false;
		return true;
	}

	/*************************** ToString ***************************/
	@Override
	public String toString() {
		return "User [id=" + this.id + ", firstName=" + this.firstName
				+ ", lastName=" + this.lastName + ", email=" + this.email
				+ ", username=" + this.username + ", password=" + this.password
				+ ", role=" + this.role + "]";
	}

}
