/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package gui1;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author user
 */
class user {
    private String pn,loc,bname;
    private Date d;
    private Time t;
    public user(String pn,String loc,Date d,Time t,String bname)
    {
        this.pn=pn;
        this.loc=loc;
        this.d=d;
        this.t=t;
        this.bname=bname;
    }

    user(String string, String string0, java.sql.Date date, Time time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String getpn()
    {
    return pn;
    }
    public String getloc()
    {
        return loc;
    }
    public Date getd()
    {
        return d;
    }
    public Time getT()
    {
        return t;
    }
    public String getbname()
    {
        return bname;
    }
            
}
