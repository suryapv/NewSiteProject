// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.model;

/**
 * @author umesh
 *
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID", nullable = false)
	private Short id;

	@Column(name = "NAME", nullable = false)
	private final String name;
	
	public Role(){
		this.name = "NA";
	}

	public Short getId() {
		return this.id;
	}

	public void setId(final Short id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public Role(final String role) {
		this.name = role;
	}

	@Override
	public int hashCode() {
		final int prime = 37;
		int result = 1;
		result = prime * result + (this.id == null ? 0 : this.id.hashCode());
		result = prime * result
				+ (this.name == null ? 0 : this.name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		final Role other = (Role) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [id=" + this.id + ", name=" + this.name + "]";
	}
}
