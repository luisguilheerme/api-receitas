package com.luisguilherme.apireceitas.models.entities;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
@Document(collection = "users")
public class Role implements GrantedAuthority {
	
    @Id
	private Long id;
	private String authority;
	
	public Role() {
		
	}

	public Role(Long id, String authority) {
		this.id = id;
		this.authority = authority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String getAuthority() {
		return authority;
	}	
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authority);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(authority, other.authority);
	}


}
