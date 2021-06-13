package rs.bg.ac.student.ivana.MavenCommon.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientContacts implements DomainType{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long contactID;
    private String address;
    private String town;
    private String phone;
    private String email;
    private Client client;

    public ClientContacts() {
    }

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

    public Long getContactID() {
        return contactID;
    }

    public void setContactID(Long contactID) {
        this.contactID = contactID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
