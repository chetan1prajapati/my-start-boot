package com.av.rfid.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Chetan
 */
@Entity
@Table(name = "epc")
public class Epc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "code", nullable = false, unique = true)
	private String code;

	@Column(name = "side", nullable = false, unique = false)
	private EpcSide side; // 1 - top; 2 - left; 3 - right; 4 - front; 5 - back;

	@ManyToOne(fetch = FetchType.EAGER)
	private TrackedObject trackedObject;

	public Epc() {
	}

	public Epc(Long id) {
		this.id = id;
	}

	public Epc(String code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setTrackedObject(TrackedObject trackedObject) {
		this.trackedObject = trackedObject;
	}

	public TrackedObject getTrackedObject() {
		return trackedObject;
	}

	public EpcSide getSide() {
		return side;
	}

	public void setSide(EpcSide side) {
		this.side = side;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Epc)) {
			return false;
		}
		Epc other = (Epc) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Epc [id=" + id + ", code=" + code + ", trackedObject=" + trackedObject + "]";
	}

}
