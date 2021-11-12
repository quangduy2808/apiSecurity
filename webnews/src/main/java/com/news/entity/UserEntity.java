package com.news.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity{
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private Integer status;

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name ="user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<RoleEntity> roles = new ArrayList<RoleEntity>();
	public List<RoleEntity> getRoles() {
		return roles;
	}
	public void setRole(RoleEntity role) {
		this.roles.add(role);
	}
	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
