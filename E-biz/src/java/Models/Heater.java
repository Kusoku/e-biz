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

public class Heater {
    int id;
    int capacity;
    String type;
    
    public void setId(int value) { id = value; }
    public int getId() { return id; }
    
    public void setCapacity(int value) { capacity = value; }
    public int getCapacity() { return capacity; }
    
    public void setType(String value) { type = value; }
    public String getType() { return type; }
}
