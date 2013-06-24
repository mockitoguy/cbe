package pl.codebyexample;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: andrzej.wislowski
 * Date: 24.06.2013
 * Time: 15:52
 * To change this template use File | Settings | File Templates.
 */
public class FlightInfo {
    private String number;
    private Date date;

    public FlightInfo(String number, Date date) {
        this.number = number;
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public Date getDate() {
        return date;
    }
}
