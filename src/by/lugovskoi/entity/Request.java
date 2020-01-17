package by.lugovskoi.entity;

import java.util.Date;

public class Request {

    private Number number;

    private Date startDate;

    private Date endDate;

    private User user;

    public Request(Number number, Date startDate, Date endDate, User user) {
        this.number = number;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
