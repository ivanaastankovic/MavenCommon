package rs.bg.ac.student.ivana.MavenCommon.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa koja implementira DomainType interfejs i predstavlja kontakt klijenta 
 * @author Ivana
 *
 */
public class ClientContacts implements DomainType{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Long kao identifikacioni broj kontakta
	 */
	private Long contactID;
	
	/**
	 * String kao naziv adrese klijenta
	 */
    private String address;
    
    /**
     * String kao naziv grada klijenta
     */
    private String town;
    
    /**
     * String kao broj telefona klijenta
     */
    private String phone;
    
    /**
     * String kao mejl adresa klijenta
     */
    private String email;
    
    /**
     * Client kao instanca klase Client 
     */
    private Client client;

    /**
     * Konstruktor koji inicijalizuje objekat klase ClientContacts 
     */
    public ClientContacts() {
    }

    /**
     * Parametarski konstruktor koji inicijalizuje objekat i postavlja vrednosti za atribute contactID, address, town, phone, email, Client
     * @param contactID Long kao identifikacioni broj kontakta
     * @param address String kao naziv adrese klijenta
     * @param town String kao naziv grada klijenta
     * @param phone String kao broj telefona klijenta
     * @param email String kao mejl adresa klijenta
     * @param client Client kao instanca klase Client 
     */
    public ClientContacts(Long contactID, String address, String town, String phone, String email, Client client) {
        this.contactID = contactID;
        this.address = address;
        this.town = town;
        this.phone = phone;
        this.email = email;
        this.client = client;
    }
    
    
    
    @Override
    public String getTableName() {
       return "clientContacts";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "id, address, town,  phone, email, clientID";
        
    }

    @Override
    public String getInsertValues() {
          StringBuilder sb = new StringBuilder();
        sb.append(contactID).append(",")
                .append("'").append(address).append("',")
                .append("'").append(town).append("',")
                .append("'").append(phone).append("',")
                .append("'").append(email).append("',")
                .append(client.getClientID());
        return sb.toString();
    }

    @Override
    public String getJoinCondition() {
      return "";
    }

    @Override
    public String getUpdateString(DomainType d) {
         ClientContacts type=(ClientContacts)d;
         String s="address='"+type.getAddress()+"', "
                    + "town='"+type.getTown()+"', "
                    + "phone='"+type.getPhone()+"', "
                    + "email='"+type.getEmail()+"'  "
            + "WHERE id="+type.getContactID();
         
         return s;
    }

    /**
     * Vraca identifikacioni broj kontakta klijenta.
     * @return Long kao identifikacioni broj kontakta
     */
    public Long getContactID() {
        return contactID;
    }

    /**
     * Postavlja identifikacioni broj kontakta na novu vrednost.
     * @param contactID Long kao identifikacioni broj kontakta
     */
    public void setContactID(Long contactID) {
        this.contactID = contactID;
    }

    /**
     * Vraca adresu klijenta.
     * @return String kao naziv adrese klijenta
     */
    public String getAddress() {
        return address;
    }

    /**
     * Postavlja adresu klijenta na novu vrednost.
     * @param address String kao naziv adrese klijenta
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Vraca grad klijenta.
     * @return String kao grad klijenta.
     */
    public String getTown() {
        return town;
    }

    /**
     * Postavlja grad klijenta na novu vrednost.
     * @param town String kao grad klijenta.
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * Vraca telefon klijenta.
     * @return String kao telefon klijenta
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Postavlja telefon klijenta na novu vrednost.
     * @param phone String kao telefon klijenta
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Vraca mejl adresu klijenta.
     * @return String kao mejl adresa klijenta
     */
    public String getEmail() {
        return email;
    }

    /**
     * Postavlja mejl adresu klijenta na novu vrednost. 
     * @param email String kao mejl adresa klijenta
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Vraca intancu klijenta.
     * @return Client kao instanca klase Client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Postavlja intancu klijenta.
     * @param client Client kao instanca klase Client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    
    /**
     * @return <ul>
     * 	<li>true - Ako su oba objekta klase ClientContacts i imaju isti ContactID</li>
     *  <li>false - U svim ostalim slucajevima</li>
     * </ul>
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
        final ClientContacts other = (ClientContacts) obj;
        if (!Objects.equals(this.contactID, other.contactID)) {
            return false;
        }
        return true;
    }

    @Override
    public void setId(Long id) {
      contactID=id;
    }

    @Override
    public List<DomainType> getRS(ResultSet rs) {
         List<DomainType> list=new ArrayList<>();
        try {
            
            while(rs.next()){
                ClientContacts c=new ClientContacts();
                c.setContactID(rs.getLong("id"));
                c.setAddress(rs.getString("address"));
                c.setTown(rs.getString("town"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("phone"));
                Client cl=new Client();
                cl.setClientID(rs.getLong("clientid"));
                c.setClient(cl);
                System.out.println(c.getClient().getClientID());
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Claim.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public String getDeleteString() {
         return " id= "+contactID+" and clientID= "+ client.getClientID();
    }
    
    
    
    
}
