package com.mindtree.entity;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "COVID_DATA")
public class CovidEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Date")
    private Date date;

    @Column(name = "State")
    private String state;

    @Column(name = "District")
    private String district;

    @Column(name = "Tested")
    private String tested;

    @Column(name = "Confirmed")
    private String confirmed;

    @Column(name = "Recovered")
    private String recovered;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTested() {
        return tested;
    }

    public void setTested(String tested) {
        this.tested = tested;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public CovidEntity() {
		super();}
    
	public CovidEntity(Long id, Date date, String state, String district, String tested, String confirmed,
			String recovered) {
		super();
		this.id = id;
		this.date = date;
		this.state = state;
		this.district = district;
		this.tested = tested;
		this.confirmed = confirmed;
		this.recovered = recovered;
	}

	@Override
	public String toString() {
		return "CovidDao [id=" + id + ", date=" + date + ", state=" + state + ", district=" + district + ", tested="
				+ tested + ", confirmed=" + confirmed + ", recovered=" + recovered + "]";
	}
    
    
}
