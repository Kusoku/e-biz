package Common;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Konrad
 */
import java.sql.*;
import java.util.Vector;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO 
{
    private Connection con = null;
    
    public DAO() 
    {
    }
    
    private void connect() throws SQLException
    {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/Aquarium", "root", "admin");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //------------------Aquarium------------------         
    
    // ********* Tank Methods
    private Models.Tank createTank(ResultSet rs) throws SQLException 
    {
        Models.Tank tank = new Models.Tank();
        tank.setId(rs.getInt(1));
        tank.setCapacity(rs.getInt(2));
        tank.setType(rs.getString(3));
        
        return tank;       
    }
    
    public Models.Tank GetTank (int id) throws SQLException
    {
        connect();
        String query_string = "SELECT capacity, type FROM tank WHERE id=?";
        PreparedStatement pstmt = con.prepareStatement(query_string);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next())
        {
            Models.Tank tank = createTank(rs);
            con.close();
            return tank;
        }
        con.close();
        return null;
    }
    
    public Vector<Models.Tank> GetTanks () throws SQLException
    {
        Vector<Models.Tank> vector = new Vector<Models.Tank>();
        connect();
        PreparedStatement pstmt;
        pstmt = con.prepareStatement("SELECT capacity, type FROM tank");
        ResultSet rs = pstmt.executeQuery();    
        while(rs.next())
        {
            vector.add(createTank(rs));
        }
        con.close();
        return vector;
    }
    
    // ********* Heater Methods
    private Models.Heater createHeater(ResultSet rs) throws SQLException 
    {
        Models.Heater heater = new Models.Heater();
        heater.setId(rs.getInt(1));
        heater.setCapacity(rs.getInt(2));
        heater.setType(rs.getString(3));
        
        return heater;       
    }
    
    public Models.Heater GetHeater (int id) throws SQLException
    {
        connect();
        String query_string = "SELECT capacity, type FROM heater WHERE id=?";
        PreparedStatement pstmt = con.prepareStatement(query_string);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next())
        {
            Models.Heater heater = createHeater(rs);
            con.close();
            return heater;
        }
        con.close();
        return null;
    }
    
    public Vector<Models.Heater> GetHeaters () throws SQLException
    {
        Vector<Models.Heater> vector = new Vector<Models.Heater>();
        connect();
        PreparedStatement pstmt;
        pstmt = con.prepareStatement("SELECT capacity, type FROM heater");
        ResultSet rs = pstmt.executeQuery();    
        while(rs.next())
        {
            vector.add(createHeater(rs));
        }
        con.close();
        return vector;
    }
    
    // ********* Filter Methods
    
    private Models.Filter createFilter(ResultSet rs) throws SQLException 
    {
        Models.Filter filter = new Models.Filter();
        filter.setId(rs.getInt(1));
        filter.setCapacity(rs.getInt(2));
        filter.setType(rs.getString(3));
        
        return filter;       
    }
    
    public Models.Filter GetFilter (int id) throws SQLException
    {
        connect();
        String query_string = "SELECT capacity, type FROM filter WHERE id=?";
        PreparedStatement pstmt = con.prepareStatement(query_string);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next())
        {
            Models.Filter filter = createFilter(rs);
            con.close();
            return filter;
        }
        con.close();
        return null;
    }
    
    public Vector<Models.Filter> GetFilters () throws SQLException
    {
        Vector<Models.Filter> vector = new Vector<Models.Filter>();
        connect();
        PreparedStatement pstmt;
        pstmt = con.prepareStatement("SELECT capacity, type FROM filter");
        ResultSet rs = pstmt.executeQuery();    
        while(rs.next())
        {
            vector.add(createFilter(rs));
        }
        con.close();
        return vector;
    }
    
    
    
    
    
    
    
    
    //------------------Users------------------
    private Models.User createUser(ResultSet rs) throws SQLException 
    {
        Models.User user = new Models.User();
        user.setId(rs.getInt(1));
        user.setUsername(rs.getString(2));
        user.setEmail(rs.getString(3));
        user.setDate(rs.getTimestamp(7));
        user.setAdmin(rs.getBoolean(8));
        user.setMod(rs.getBoolean(9));
        
        return user;       
    }
    
    public Models.User GetUser (String email) throws SQLException
    {
        connect();
        String query_string = "select id, name, email, salt, encrypted_password, token, created_at, admin, mod FROM users WHERE email = ?";
        PreparedStatement pstmt = con.prepareStatement(query_string);
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next())
        {
            Models.User user = createUser(rs);
            con.close();
            return user;
        }
        con.close();
        return null;
    }
    
    public Models.User GetUser (int id) throws SQLException
    {
        connect();
        String query_string = "select id, name, email, salt, encrypted_password, token, created_at, admin, mod FROM users WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(query_string);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next())
        {
            Models.User user = createUser(rs);
            con.close();
            return user;
        }
        con.close();
        return null;
    }   
    
    public Vector<Models.User> GetUsers () throws SQLException
    {
        Vector<Models.User> vector = new Vector<Models.User>();
        connect();
        PreparedStatement pstmt = con.prepareStatement("SELECT id, name, email, salt, encrypted_password, token, created_at, admin, mod FROM users ORDER BY name");

        ResultSet rs = pstmt.executeQuery();

        while(rs.next())
        {
            vector.add(createUser(rs));
        }
        con.close();
        return vector;
    }
    
    public void EditUser(String email, String name, int id) throws SQLException
    {
        connect();
        String query_string = "UPDATE users SET name= ?, email= ? WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(query_string);
        pstmt.setString(1, name);
        pstmt.setString(2, email);
        pstmt.setInt(3, id);
        pstmt.executeUpdate();
        con.close();
    }
    
    public void EditUserPassword(String password, int id) throws SQLException
    {
        connect();
        
        PreparedStatement pstmt = con.prepareStatement("select salt FROM users WHERE id = ?");
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) throw new SQLException("Użytkownik nie istnieje");
        MessageDigest md = null; 
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] res = md.digest((rs.getString(1) + password).getBytes());
        String encrypted = new String(res);
        
        String query_string = "UPDATE users SET encrypted_password= ?  WHERE id = ?";
        pstmt = con.prepareStatement(query_string);
        pstmt.setString(1, encrypted);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
        con.close();
    }
    
    public void AuthUser(boolean admin, boolean mod, int id) throws SQLException
    {
        connect();
        String query_string = "UPDATE users SET admin= ?, mod= ?  WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(query_string);
        pstmt.setBoolean(1, admin);
        pstmt.setBoolean(2, mod);
        pstmt.setInt(3, id);
        pstmt.executeUpdate();
        con.close();
    }
    
    public void DeleteUser(int id) throws SQLException
    {
        connect();
        String query_string = "DELETE FROM users WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(query_string);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        con.close();
    }
    
    //------------------Login/Reg------------------
    public Models.User Login (String email, String password) throws SQLException 
    {
        Models.User user = new Models.User();
        connect();
        PreparedStatement pstmt = con.prepareStatement("select salt FROM users WHERE email = ?");
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) throw new SQLException("Zły login lub hasło.");

        MessageDigest md = null; 
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] res = md.digest((rs.getString(1) + password).getBytes());
        String encrypted = new String(res);

        pstmt = con.prepareStatement("select id, name, email, salt, encrypted_password, token, created_at, admin, mod FROM users WHERE email = ?");
        pstmt.setString(1, email);
        //pstmt.setString(2, encrypted);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            if(!encrypted.equals(rs.getString(5)))
                throw new SQLException("Złe hasło");
            user.setId(rs.getInt(1));
            user.setUsername(rs.getString(2));
            user.setEmail(rs.getString(3));
            user.setDate(rs.getTimestamp(7));
            user.setAdmin(rs.getBoolean(8));
            user.setMod(rs.getBoolean(9));
        } else {
            throw new SQLException("Zły login");
        }
        return user;
    }
     
    public void Register (String email, String name, String password) throws SQLException 
    {
        connect();
        PreparedStatement pstmt = con.prepareStatement("insert into USERS (name,email,salt,encrypted_password,token,created_at) values ( ? , ? , ? , ? , null , CURRENT_TIMESTAMP )");
        
        MessageDigest md = null; 
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date salt = new Date();
        byte[] res = md.digest((salt.toString() + password).getBytes());
        String encrypted = new String(res);
        
        pstmt.setString(1, name);
        pstmt.setString(2, email);
        pstmt.setString(3, salt.toString());
        pstmt.setString(4, encrypted);
        pstmt.executeUpdate();
    } 
    
}
