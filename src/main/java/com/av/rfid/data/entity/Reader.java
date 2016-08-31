package com.av.rfid.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Chetan
 */
@Entity
@Table(name = "reader")
public class Reader implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "host_name",nullable=false, unique = true)
    private String hostName;
    
    private Integer x;

    private Integer y;
    
    private Integer heightCM;
    private Integer orient;
    private Integer session;
    private Boolean active;

    public Reader() {
    }

    public Reader(Integer id) {
        this.id = id;
    }

    public Reader(Integer id, String hostName) {
        this.id = id;
        this.hostName = hostName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
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
    

    public Integer getHeightCM() {
		return heightCM;
	}

	public void setHeightCM(Integer heightCM) {
		this.heightCM = heightCM;
	}

	public Integer getOrient() {
		return orient;
	}

	public void setOrient(Integer orient) {
		this.orient = orient;
	}

	public Integer getSession() {
		return session;
	}

	public void setSession(Integer session) {
		this.session = session;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Reader)) {
            return false;
        }
        Reader other = (Reader) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.av.rfid.spring.entities.Readers[ id=" + id + " ]";
    }
    
}
