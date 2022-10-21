package com.nnk.springboot.domain;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
public class BidList implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name ="Id")
    private Integer bidListId;

    @Column(name ="account")
    @NotNull
    private String account;

    @Column(name ="type")
    @NotNull
    private String type;

    @Column(name ="bidQuantity")
    private double bidQuantity;

    @Column(name ="askQuantity")
    private double askQuantity;

    @Column(name ="bid")
    private double bid;

    @Column(name ="ask")
    private double ask;

    @Column(name ="benchmark")
    private String benchmark;

    @Column(name ="bidListDate")
    private Date bidListDate;

    @Column(name ="commentary")
    private String commentary;

    @Column(name ="security")
    private String security;

    @Column(name ="status")
    private String status;

    @Column(name ="trader")
    private String trader;

    @Column(name ="book")
    private String book;

    @Column(name ="creationName")
    private String creationName;

    @Column(name ="creationDate")
    private Date creationDate;

    @Column(name ="revisionName")
    private String revisionName;

    @Column(name ="revisionDate")
    private Date revisionDate;

    @Column(name ="dealName")
    private String dealName;

    @Column(name ="dealType")
    private String dealType;

    @Column(name ="sourceListId")
    private String sourceListId;

    @Column(name ="side")
    private String side;

    public Integer getBidListId() {
        return bidListId;
    }

    public void setBidListId(Integer bidListId) {
        this.bidListId = bidListId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public double getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(double askQuantity) {
        this.askQuantity = askQuantity;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Date getBidListDate() {
        return bidListDate;
    }

    public void setBidListDate(Date bidListDate) {
        this.bidListDate = bidListDate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getRevisionName() {
        return revisionName;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}

