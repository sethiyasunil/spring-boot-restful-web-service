package com.appsdeveloperblog.app.ws.io.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class RoleEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, length=20)
	private String name;
	
	
	@ManyToMany(mappedBy = "roles")
	private Collection<UserEntity> users;
	
	@ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
	@JoinTable(name="roles_authorities", 
				joinColumns=@JoinColumn(name="role_id"), 
				inverseJoinColumns = @JoinColumn(name="authority_id", referencedColumnName = "id"))	
	private Collection<AuthorityEntity> authorities;

	public RoleEntity(String name) {
		this.name = name;
	}

	public RoleEntity() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Collection<UserEntity> users) {
		this.users = users;
	}

	public Collection<AuthorityEntity> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<AuthorityEntity> authorities) {
		this.authorities = authorities;
	}

}
