package com.forgetPassword.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class Users implements Serializable {

	private static final long serialVersionUID = 1948638898199176136L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@Column(name = "username", unique = true, nullable = false, length = 100)
	private String username;

	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Column(name = "email",nullable = false)
	private String email;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "locked")
	private Boolean locked;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "user", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "role", nullable = false, updatable = false) })
	private Set<Roles> roleses = new HashSet<Roles>(0);

	public Users() {
	}

	public Users(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Users(String username, String password,String email,
				 Boolean enabled, Boolean locked,
				 Set<Roles> roleses) {
		this.username = username;
		this.password = password;
		this.email=email;
		this.enabled = enabled;

		this.locked = locked;
		this.roleses = roleses;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {return email; }

	public void setEmail(String email) {this.email = email; }

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getLocked() {
		return this.locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Set<Roles> getRoleses() {
		return this.roleses;
	}

	public void setRoleses(Set<Roles> roleses) {
		this.roleses = roleses;
	}

}
