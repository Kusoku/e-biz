/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author Konrad
 */
public class Aquarium {
    int id;
    int user_id;
    
    Date created_at;
    
    Tank tank;
    Heater heater;
    Filter filter;
    
    public void setId(int value) { id = value; }
    public int getId() { return id; }    
    
    public void setUserId(int value) { user_id = value; }
    public int getUserId() { return user_id; }
    
    public void setTank(Tank value) { tank = value; }
    public Tank getTank() { return tank; }
    
    public void setHeater(Heater value) { heater = value; }
    public Heater getHeater() { return heater; }
    
    public void setFilter(Filter value) { filter = value; }
    public Filter getFilter() { return filter; }
    
    public void setDate(Date value) { created_at = value; }
    public Date getDate() { return created_at; }
}
