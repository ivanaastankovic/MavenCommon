package rs.bg.ac.student.ivana.MavenCommon.domain;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa koja implementira DomainType interfejs i predstavlja admina 
 * @author Ivana
 *
 */
public class Admin implements DomainType{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * String koji predstavlja korisnicko ime admina.
	 */
	private String username;
	/**
	 * String koji predstavlja lozinku admina.
	 */
    private String password;
    
    /**
     * Konstruktor koji inicijalizuje objekat klase Admin
     */
    public Admin() {
    }
    /**
     * Parametarski konstruktor koji inicijalizuje objekat i postavlja vrednosti za Username i Password
     * @param username Korisnicko ime admina kao String 
     * @param password Lozinka admina kao String 
     */
    public Admin(String username, String password) {
    	this.username = username;
    	this.password = password;
    }
   
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
     * Vraca listu domenskih objekata (Admin) kao rezultat upita 
     * @param rs ResultSet kao rezultat upita iz baze
     * @return List Lista domenskih objekata (Admin)
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

    /**
     * @throws UnsupportedOperationException Kada ne postoji String brisanje.
     */
    @Override
    public String getDeleteString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     * Vraca String kao lozinku admina.
     * @return password Lozinka admina kao String
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Postavlja lozinku admina na novu vrednost.
     * @param password String kao lozinka admina
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Vraca korisnicko ime admina.
     * @return username String kao korisnicko ime admina
     */
    public String getUsername() {
        return username;
    }

    /**
     * Postavlja korisnicko ime admina na novu vrednost.
     * @param username String kao korisnicko ime admina
     */
    public void setUsername(String username) {
        this.username = username;
    }

    
    /**
     * @return <ul>
     * 			<li>true - Ako su oba objekta klase Admin i imaju isto korisnicko ime i lozinku</li>
     * 			<li>false - U svim ostalim slucajevima</li>
     * 		  </ul>
     */
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
