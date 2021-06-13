package rs.bg.ac.student.ivana.MavenCommon.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa koja implementira DomainType interfejs i predstavlja klijenta 
 * @author Ivana
 *
 */
public class Client implements DomainType{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Long kao identifikacioni broj klijenta
	 */
	private Long clientID;
	
	/**
	 * String kao ime klijenta
	 */
    private String firstName;
    
    /**
     * String kao prezime klijenta
     */
    private String lastName;
    
    /**
     * String kao maticni broj klijenta
     */
    private String jmbg;
    
    /**
     * Date kao datum registracije klijenta
     */
    private Date signatureDate;
    
    /**
     * List kao lista instanci klase ClientContacts
     */
    private List<ClientContacts> contacts;
    
    /**
     * ContractType instanca klase ContractType kao tip ugovora koji je klijent potpisao
     */
    private ContractType contractType;

    
    /**
     *  Konstruktor koji inicijalizuje objekat klase Client 
     */
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

    
    /**
     * Vraca identifikacioni broj klijenta.
     * @return Long kao identifikacioni broj klijenta
     */
    public Long getClientID() {
        return clientID;
    }

    /**
     * Postavlja identifikacioni broj klijenta na novu vrednost.
     * @param clientID Long kao identifikacioni broj klijenta
     */
    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }
    /**
     * Vraca String kao ime klijenta
     * @return String kao ime klijenta
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Postavlja ime klijenta na novu vrednost
     * @param firstName String kao ime klijenta
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Vraca String kao prezime klijenta
     * @return String kao prezime klijenta
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Postavlja prezime klijenta na novu vrednost.
     * @param lastName String kao prezime klijenta
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Vraca String kao maticni broj klijenta
     * @return String kao maticni broj klijenta
     */
    public String getJmbg() {
        return jmbg;
    }
    
    /**
     * Postavlja maticni broj klijenta na novu vrednost.
     * @param jmbg String kao maticni broj klijenta
     */
    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    /**
     * Vraca Date kao datum registracije klijenta. 
     * @return Date kao datum registracije klijenta
     */
    public Date getSignatureDate() {
        return signatureDate;
    }

    /**
     * Postavlja datum registracije klijenta na novu vrednost.
     * @param signatureDate Date kao datum registracije klijenta
     */
    public void setSignatureDate(Date signatureDate) {
        this.signatureDate = signatureDate;
    }

    /**
     * Vraca List kao listu instanci klase ClientContacts.
     * @return List kao lista instanci klase ClientContacts
     */
    public List<ClientContacts> getContacts() {
        return contacts;
    }
    
    /**
     * Postavlja listu instanci klase ClientContacts na novu vrednost.
     * @param contacts List kao lista instanci klase ClientContacts
     */
    public void setContacts(List<ClientContacts> contacts) {
        this.contacts = contacts;
    }
    
    /**
     * Vraca instancu klase ContractType
     * @return ContractType instanca klase ContractType kao tip ugovora koji je klijent potpisao
     */
    public ContractType getContractType() {
        return contractType;
    }

    /**
     * Postavlja instancu klase ContractType na novu vrednost
     * @param contractType ContractType instanca klase ContractType kao tip ugovora koji je klijent potpisao
     */
    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    /**
     * @return <ul>
     * 		<li>true - Ako su oba objekta klase Client i imaju isti ClientID</li>
     * 		<li>false - U svim ostalim slucajevima</li>
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
