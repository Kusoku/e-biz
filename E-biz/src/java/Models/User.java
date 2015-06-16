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
public class User 
{
    int id;
    String username;
    String email;
    
    String token;
    Date created_at;
    
    boolean mod;

    public void setId(int value) { id = value; mod=false; }
    public int getId() { return id; }
    
    public void setUsername(String value) { username = value; }
    public String getUsername() { return username; }

    public void setEmail(String value) { email = value; }
    public String getEmail() { return email; }
    
    public void setDate(Date value) { created_at = value; }
    public Date getDate() { return created_at; }
    
    public void setMod(boolean value) { mod = value; }
    public boolean isMod() { return mod; }   
}