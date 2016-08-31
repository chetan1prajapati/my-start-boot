package com.av.rfid.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="forklift")
@DiscriminatorValue("Forklift")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Forklift extends TrackedObject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "code")
	private String code;

	public Forklift() {
	}

	public Forklift(Job job) {
		super(job);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Forklift)) {
			return false;
		}
		Forklift other = (Forklift) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		if (!super.equals(object)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.av.rfid.spring.entities.Forklift[ id=" + id + " ]";
	}

}
