package com.av.rfid.data.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Chetan
 */
@JsonAutoDetect
@Entity
@Table(name = "job")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(unique=true)
	private String name;
	
	@Column(name = "start_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startDate;

	@Column(name = "expected_end_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expectedEndDate;

	@Column(name = "actual_end_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date actualEndDate;

	@JsonIgnore
	@OneToMany(mappedBy = "job")
	private List<TrackedObject> trackedObjects;

	@ManyToOne
	private Customer customer;

	public Job() {
	}

	public Job(Integer id) {
		this.id = id;
	}

	public Job(Date startDate, Date expectedEndDate, Date actualEndDate, List<TrackedObject> trackedObjects,
			Customer customer) {
		super();
		this.startDate = startDate;
		this.expectedEndDate = expectedEndDate;
		this.actualEndDate = actualEndDate;
		this.trackedObjects = trackedObjects;
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	//@JsonSerialize(using=CustomDateSerializer.class)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpectedEndDate() {
		return expectedEndDate;
	}

	public void setExpectedEndDate(Date expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public List<TrackedObject> getTrackedObjects() {
		return this.trackedObjects;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTrackedObjects(List<TrackedObject> trackedObjects) {
		this.trackedObjects = trackedObjects;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Job)) {
			return false;
		}
		Job other = (Job) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.av.rfid.spring.entities.Jobs[ id=" + id + " ]";
	}

}
