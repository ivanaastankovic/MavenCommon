package rs.bg.ac.student.ivana.MavenCommon.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements DomainType{
    private Long clientID;
    private String firstName;
    private String lastName;
    private String jmbg;
    private Date signatureDate;
    private List<ClientContacts> contacts;
    private ContractType contractType;

    public Client() {
    }
    
    
    

    @Override
    public String getTableName() {
        return "clients ";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "id, firstName, lastName, jmbg, signatureDate, contractType";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(clientID).append(",")
                .append("'").append(firstName).append("',")
                .append("'").append(lastName).append("',")
                .append("'").append(jmbg).append("',")
                .append("'").append(new java.sql.Date(signatureDate.getTime())).append("',")
                .append("'").append(contractType).append("'");
        return sb.toString();
    }

    @Override
    public String getJoinCondition() {
        return "";
    }

    @Override
    public String getUpdateString(DomainType d) {
        Client type=(Client)d;
         String s="firstName='"+type.getFirstName()+"', "
                    + "lastName='"+type.getLastName()+"', "
                    + "jmbg='"+type.getJmbg()+"', "
                    + "signatureDate='"+type.getSignatureDate()+"', "
                    + "contractType='"+type.getContractType()+"'  "
            + "WHERE id="+type.getClientID();
         
         return s;
    }

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public Date getSignatureDate() {
        return signatureDate;
    }

    public void setSignatureDate(Date signatureDate) {
        this.signatureDate = signatureDate;
    }

    public List<ClientContacts> getContacts() {
        return contacts;
    }

    public void setContacts(List<ClientContacts> contacts) {
        this.contacts = contacts;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
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
        final Client other = (Client) obj;
        if (!Objects.equals(this.clientID, other.clientID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Client: "+ clientID + ", " + firstName + ", " + lastName;
    }

    @Override
    public void setId(Long id) {
       clientID=id;
    }

    @Override
    public List<DomainType> getRS(ResultSet rs) {
        List<DomainType> list=new ArrayList<>();
        try {
            
            while(rs.next()){
                Client c=new Client();
                c.setClientID(rs.getLong("id"));
                c.setFirstName(rs.getString("firstName"));
                c.setLastName(rs.getString("lastName"));
                c.setJmbg(rs.getString("jmbg"));
                c.setSignatureDate(new java.sql.Date(rs.getDate("signatureDate").getTime()));
                c.setContractType(ContractType.valueOf(rs.getString("contractType")));
                
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Claim.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public String getDeleteString() {
        return " jmbg= "+ jmbg;
    }
    
    
    
    
    
    
}
