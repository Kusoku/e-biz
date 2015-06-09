/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Konrad
 */
public class Aquarium {
    int userID;
    Tank tank;
    Heater heater;
    Filter filter;
    
    public void setUserId(int value) { userID = value; }
    public int getUserId() { return userID; }
    
    public void setTank(Tank value) { tank = value; }
    public Tank getTank() { return tank; }
    
    public void setHeater(Heater value) { heater = value; }
    public Heater getHeater() { return heater; }
    
    public void setFilter(Filter value) { filter = value; }
    public Filter getFilter() { return filter; }
}
