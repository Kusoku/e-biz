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
            
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //------------------Posts------------------    
 /*   public Models.Aquarium GetAquarium (int id) throws SQLException
    {
        connect();
        String query_string = "select p.id, p.user_id, u.name, p.title, p.content, p.created_at from POSTS as p LEFT JOIN users as u ON p.user_id = u.id where p.id = "+Integer.toString(id);
        PreparedStatement pstmt = con.prepareStatement(query_string);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next())
        {
            Models.Post post = createPost(rs);
            con.close();
            return post;
        }
        con.close();
        return null;
    }
 */       
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
