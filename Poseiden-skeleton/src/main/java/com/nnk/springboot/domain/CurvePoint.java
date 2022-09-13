package com.nnk.springboot.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "curvepoint")
public class CurvePoint implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name ="Id")
    private Integer id;

    @Column(name ="asOfDate")
    private Date asOfDate;


    @Column(name ="term")
    private double term;

    @Column(name ="creationDate")
    private Date creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Date asOfDate) {
        this.asOfDate = asOfDate;
    }

    public double getTerm() {
        return term;
    }

    public void setTerm(double term) {
        this.term = term;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
