package com.av.rfid.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Chetan
 */
@Entity
@Table(name = "tracked_object")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "object_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("TargetObject")
public abstract class TrackedObject implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@Column(unique = true)
	protected String name;

	@JsonIgnore
	@OneToMany(mappedBy = "trackedObject")
	private List<Epc> epcs;

	@ManyToOne
	private Job job;

	@JsonIgnore
	@OneToOne
	private RfidTransaction lastTransaction;

	public TrackedObject() {
	}

	public TrackedObject(Integer id) {
		this.id = id;
	}

	public TrackedObject(Job job) {
		super();
		this.job = job;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Epc> getEpcs() {
		return epcs;
	}

	public void setEpcs(List<Epc> epcs) {
		this.epcs = epcs;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public RfidTransaction getLastTransaction() {
		return lastTransaction;
	}

	public void setLastTransaction(RfidTransaction lastTransaction) {
		this.lastTransaction = lastTransaction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof TrackedObject)) {
			return false;
		}
		TrackedObject other = (TrackedObject) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}

		if ((this.lastTransaction == null && other.lastTransaction != null)
				|| (this.lastTransaction != null && !this.lastTransaction.equals(other.lastTransaction))) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "com.av.rfid.spring.entities.TrackedObjects[ id=" + id + ", lastTransaction="
				+ lastTransaction.toString() + " ]";
	}

	public void addEpc(Epc epc) {
		if (epcs == null)
			epcs = new ArrayList<Epc>();
		epc.setTrackedObject(this);
		epcs.add(epc);

	}

	@Transient
	public String getTrackedObjectType() {
		DiscriminatorValue val = this.getClass().getAnnotation(DiscriminatorValue.class);

		return val == null ? null : val.value();
	}

}
