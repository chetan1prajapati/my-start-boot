package com.av.rfid.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Chetan
 */
@Entity
@Table(name = "vault")
@DiscriminatorValue("Vault")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Vault extends TrackedObject implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "width")
	private Integer w;

	@Column(name = "length")
	private Integer l;

	@Column(name = "height")
	private Integer h;

	public Vault() {
	}

	public Vault(Job job) {
		super(job);
	}

	public Integer getW() {
		return w;
	}

	public void setW(Integer w) {
		this.w = w;
	}

	public Integer getL() {
		return l;
	}

	public void setL(Integer l) {
		this.l = l;
	}

	public Integer getH() {
		return h;
	}

	public void setH(Integer h) {
		this.h = h;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Vault)) {
			return false;
		}
		Vault other = (Vault) object;
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
		return "com.av.rfid.spring.entities.Vaults[ id=" + id + " ]";
	}

	public void setHeight(Integer height) {
		this.h = height;

	}

	public void setLength(Integer length) {
		this.l = length;
	}

	public void setWidth(Integer width) {
		this.w = width;
	}

	@JsonIgnore
	public Integer getWidth() {
		return w;
	}

	@JsonIgnore
	public Integer getLength() {
		return l;
	}

	@JsonIgnore
	public Integer getHeight() {
		return h;
	}

}
