package rs.bg.ac.student.ivana.MavenCommon.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa koja implementira DomainType interfejs i predstavlja zalbu 
 * @author Ivana
 *
 */
public class Claim implements DomainType{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Long kao identifikacioni broj zalbe
	 */
	private Long claimID;
	
	/**
	 * Date kao datum unosa zalbe
	 */
    private Date fileDate;
    
    /**
     * String kao opis aktivnosti 
     */
    private String activity;
    
    /**
     * BigDecimal kao suma za isplatu osiguranja
     */
    private BigDecimal paymentSum;
    
    /**
     * Client instanca klase Client kao klijenta na koga se odnosi zalba
     */
    private Client client;
    
    /**
     * Status enum vrednost kao status u kom se zalba nalazi
     */
    private Status status;
    
    /**
     * RiskType instanca klase RiskType kao okvir rizika u kom se zalba nalazi. 
     */
    private RiskType riskType;

    /**
     *  Konstruktor koji inicijalizuje objekat klase Claim 
     */
    public Claim() {
    }
    /**
     * Parametarski konstruktor koji inicijalizuje objekat i postavlja vrednosti za atribute fileDate, activity, paymentSum, Client, Status i RiskType 
     * @param fileDate Date kao datum unosa zalbe
     * @param activity String kao opis aktivnosti 
     * @param paymentSum BigDecimal kao suma za isplatu osiguranja
     * @param client Client instanca klase Client kao klijenta na koga se odnosi zalba
     * @param status Status enum vrednost kao status u kom se zalba nalazi
     * @param riskType RiskType instanca klase RiskType kao okvir rizika u kom se zalba nalazi.
     */
    public Claim(Date fileDate, String activity, BigDecimal paymentSum, Client client, Status status, RiskType riskType) {
       
        this.fileDate = fileDate;
        this.activity = activity;
        this.paymentSum = paymentSum;
        this.client = client;
        this.status = status;
        this.riskType = riskType;
    }
    
    /**
     * Vraca RiskType kao tip rizika kome pripada zalba
     * @return RiskType instanca klase RiskType kao okvir rizika u kom se zalba nalazi.
     */
    public RiskType getRiskType() {
        return riskType;
    }
    /**
     * Postavlja RiskType na novu vrednost.
     * @param riskType RiskType instanca klase RiskType kao okvir rizika u kom se zalba nalazi.
     */
    public void setRiskType(RiskType riskType) {
        this.riskType = riskType;
    }

    /**
     * Vraca vrednost identifikacionog broja zalbe.
     * @return Long kao identifikacioni broj zalbe
     */
    public Long getClaimID() {
        return claimID;
    }
    
    /**
     * Postavlja identifikacioni broj zalbe na novu vrednost
     * @param claimID Long kao identifikacioni broj zalbe
     */
    public void setClaimID(Long claimID) {
        this.claimID = claimID;
    }
    
    /**
     * Vraca Date kao datum unosa zalbe.
     * @return Date kao datum unosa zalbe
     */
    public Date getFileDate() {
        return fileDate;
    }
    
    /**
     * Postavlja fileDate na novu vrednost
     * @param fileDate Date kao datum unosa zalbe
     */
    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }

    /**
     * Vraca String kao opis aktivnosti.
     * @return String kao opis aktivnosti 
     */
    public String getActivity() {
        return activity;
    }

    /**
     * Postavlja String aktivnost na novu vrednost
     * @param activity String kao opis aktivnosti 
     */
    public void setActivity(String activity) {
        this.activity = activity;
    }

    /**
     * Vraca BigDecimal kao sumu za isplatu osiguranja
     * @return BigDecimal kao suma za isplatu osiguranja
     */
    public BigDecimal getPaymentSum() {
        return paymentSum;
    }
    
    /**
     * Postavlja BigDecimal kao sumu za isplatu osiguranja na novu vrednost
     * @param paymentSum BigDecimal kao suma za isplatu osiguranja
     */
    public void setPaymentSum(BigDecimal paymentSum) {
        this.paymentSum = paymentSum;
    }
    
    /**
     * Vraca instancu klase Client na koga se odnosi zalba.
     * @return Client instanca klase Client kao klijenta na koga se odnosi zalba
     */
    public Client getClient() {
        return client;
    }

    /**
     * Postavlja instancu Client na koga se odnosi zalba na novu vrednost.
     * @param client Client instanca klase Client kao klijenta na koga se odnosi zalba
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Vraca enum Status kao status u kom se zalba nalazi.
     * @return Status enum vrednost kao status u kom se zalba nalazi
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Postavlja enum Status na novu vrednost.
     * @param status Status enum vrednost kao status u kom se zalba nalazi
     */
    public void setStatus(Status status) {
        this.status = status;
    }

   
    /**
     * @return <ul>
     * 	<li>true - Ako su oba objekta klase Claim i imaju isti ClaimID</li>
     * 	<li>false - U svim ostalim slucajevima</li>
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
        final Claim other = (Claim) obj;
        if (!Objects.equals(this.claimID, other.claimID)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "claimID " + claimID;
    }

   
    @Override
    public String getTableName() {
        return "claim";
    }

    @Override
    public String getColumnNamesForInsert() {
         return "id, activity, paymentSum, clientID, status, riskTypeID";

    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(claimID).append(",")
                .append("'").append(activity).append("',")
                .append(paymentSum).append(",")
                .append(client.getClientID()).append(",")
                .append("'").append(status).append("',")
                .append(riskType.getRiskTypeID()).append(" ");
      
        return sb.toString();
    }

    @Override
    public String getJoinCondition() {
       return " c JOIN clients cl ON (c.clientID=cl.id) JOIN riskType r ON (c.riskTypeid=r.id) ";
    }

    @Override
    public String getUpdateString(DomainType d) {
       Claim type=(Claim)d;
         String s="status='"+type.getStatus()+"', "
                    + "paymentSum= "+type.getPaymentSum()+" "
                    + "WHERE id="+type.getClaimID();
         
         return s;
    }

    @Override
    public void setId(Long id) {
        claimID=id;
    }

    
    @Override
    public List<DomainType> getRS(ResultSet rs) {
        List<DomainType> list=new ArrayList<>();
        try {
            
            while(rs.next()){
                Claim c=new Claim();
                c.setClaimID(rs.getLong("c.id"));
                c.setActivity(rs.getString("c.activity"));
                c.setPaymentSum(rs.getBigDecimal("c.paymentSum"));
                c.setFileDate(new Date());
                c.setStatus(Status.valueOf(rs.getString("c.status")));
                Client cl=new Client();
                cl.setClientID(rs.getLong("c.clientID"));
                cl.setJmbg(rs.getString("cl.jmbg"));
                cl.setFirstName(rs.getString("cl.firstName"));
                cl.setLastName("cl.LastName");								/// !
                c.setClient(cl);
                RiskType rt=new RiskType();
                rt.setRiskTypeID(rs.getLong("c.riskTypeID"));
                rt.setName(rs.getString("r.name"));
                rt.setMinSum(new BigDecimal(rs.getDouble("r.minSum")));
                System.out.println(rt.getMinSum().toString());
                rt.setMaxSum(new BigDecimal(rs.getDouble("r.maxSum")));
                c.setRiskType(rt);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Claim.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public String getDeleteString() {
       return " c.id= "+claimID;
    }
    
    
    
    
    
}
