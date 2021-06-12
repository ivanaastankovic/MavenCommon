package rs.bg.ac.student.ivana.MavenCommon.domain;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin implements DomainType{
    private String username;
    private String password;
    
    @Override
    public String getTableName() {
         return "admin";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "";
    }

    @Override
    public String getInsertValues() {
        return "";
    }

    @Override
    public void setId(Long id) {
        
    }

    @Override
    public String getJoinCondition() {
        return "";
    }

    @Override
    public String getUpdateString(DomainType d) {
        return "";
    }

    /**
     * Vraca listu domenskog tipa kao rezultat upita 
     * @return 
     */
    @Override
    public List<DomainType> getRS(ResultSet rs) {
         List<DomainType> list=new ArrayList<>();
         
        try {
            while(rs.next()){
                Admin a=new Admin();
                a.setPassword(rs.getString("password"));
                a.setUsername(rs.getString("username"));
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         return list;
    }

    @Override
    public String getDeleteString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Konstruktor koji inicijalizuje objekat klase Admin
     */
    public Admin() {
    }
    /**
     * Parametarski konstruktor koji inicijalizuje objekat i postavlja vrednosti za Username i Password
     * @param username Username admina kao String 
     * @param password Password admina kao String 
     */
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Admin other = (Admin) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    
    
    
}
