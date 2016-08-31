package com.av.rfid.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Chetan
 */
@Entity
@Table(name = "rfid_transaction")
public class RfidTransaction implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Epc epc;

	@Column(name = "report_date_time", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date reportDateTime;

	private Integer x;

	private Integer y;

	@ManyToOne
	private Reader xArrayID;

	private String type;

	@OneToOne(mappedBy = "lastTransaction")
	private TrackedObject trackedObject;

	public RfidTransaction() {
	}

	public RfidTransaction(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEpc(Epc epc) {
		this.epc = epc;
	}

	public Epc getEpc() {
		return epc;
	}

	public Date getReportDateTime() {
		return reportDateTime;
	}

	public void setReportDateTime(Date reportDateTime) {
		this.reportDateTime = reportDateTime;
	}

	public void setTrackedObject(TrackedObject trackedObject) {
		this.trackedObject = trackedObject;
	}

	public TrackedObject getTrackedObject() {
		return trackedObject;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public void setxArrayID(Reader xArrayID) {
		this.xArrayID = xArrayID;
	}

	public Reader getxArrayID() {
		return xArrayID;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof RfidTransaction)) {
			return false;
		}
		RfidTransaction other = (RfidTransaction) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.av.rfid.spring.entities.RfidTransactions[ id=" + id + " ]";
	}

}
