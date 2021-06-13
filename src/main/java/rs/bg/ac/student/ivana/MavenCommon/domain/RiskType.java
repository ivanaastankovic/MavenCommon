package rs.bg.ac.student.ivana.MavenCommon.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Klasa koja implementira DomainType interfejs i predstavlja tip rizika  
 * @author Ivana
 *
 */
public class RiskType implements DomainType{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Long kao identifikacioni broj tipa rizika
	 */
	private Long riskTypeID;
	
	/**
	 * String kao naziv tipa rizika
	 */
    private String name;
    
    /**
     * String kao domen tipa rizika
     */
    private String domain;
    
    /**
     * String kao opis tipa rizika
     */
    private String description;
    
    /**
     * BigDecimal kao minimalna suma za isplatu osiguranja
     */
    private BigDecimal minSum;
    
    /**
     * BigDecimal kao maksimalna suma za isplatu osiguranja
     */
    private BigDecimal maxSum;

    /**
     * Konstruktor koji inicijalizuje objekat klase RiskType
     */
    public RiskType() {
    }

    /**
     * Parametarski konstruktor koji inicijalizuje objekat i postavlja vrednosti za atribute riskTypeID, name, domain, description, minSum, maxSum
     * @param riskTypeID Long kao identifikacioni broj tipa rizika
     * @param name String kao naziv tipa rizika
     * @param domain String kao domen tipa rizika
     * @param description String kao opis tipa rizika
     * @param minSum BigDecimal kao minimalna suma za isplatu osiguranja
     * @param maxSum BigDecimal kao maksimalna suma za isplatu osiguranja
     */
    public RiskType(Long riskTypeID, String name, String domain, String description, BigDecimal minSum, BigDecimal maxSum) {
        this.riskTypeID = riskTypeID;
        this.name = name;
        this.domain = domain;
        this.description = description;
        this.minSum = minSum;
        this.maxSum = maxSum;
    }
    
    

    @Override
    public String getTableName() {
        return "riskType";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "id, name, domain, description, minSum, maxSum";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(riskTypeID).append(",")
                .append("'").append(name).append("',")
                .append("'").append(domain).append("',")
                .append("'").append(description).append("',")
                .append(minSum).append(",")
                .append(maxSum).append(" ");
        return sb.toString();
    }
    
    

    


    @Override
    public String getJoinCondition() {
       return "";
    }

    /**
     * Vraca identifikacioni broj tipa rizika
     * @return Long kao identifikacioni broj tipa rizika
     */
    public Long getRiskTypeID() {
        return riskTypeID;
    }

    /**
     * Postavlja identifikacioni broj rizika na novu vrednost.
     * @param riskTypeID Long kao identifikacioni broj tipa rizika
     */
    public void setRiskTypeID(Long riskTypeID) {
        this.riskTypeID = riskTypeID;
    }

    /**
     * Vraca naziv tipa rizika.
     * @return String kao naziv tipa rizika
     */
    public String getName() {
        return name;
    }

    
    /**
     * Postavlja naziv tipa rizika na novu vrednost.
     * @param name String kao naziv tipa rizika
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Vraca domen tipa rizika.
     * @return String kao domen tipa rizika
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Postavlja domen tipa rizika na novu vrednost.
     * @param domain String kao domen tipa rizika
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * Vraca opis tipa rizika.
     * @return String kao opis tipa rizika
     */
    public String getDescription() {
        return description;
    }

    /**
     * Postavlja opis tipa rizika na novu vrednost.
     * @param description String kao opis tipa rizika
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Vraca minimalnu sumu za isplatu osiguranja
     * @return BigDecimal kao minimalna suma za isplatu osiguranja
     */
    public BigDecimal getMinSum() {
        return minSum;
    }

    /**
     * Postavlja minimalnu sumu za isplatu osiguranja
     * @param minSum BigDecimal kao minimalna suma za isplatu osiguranja
     */
    public void setMinSum(BigDecimal minSum) {
        this.minSum = minSum;
    }

    /**
     * Vraca maksimalnu sumu za isplatu osiguranja
     * @return maxSum BigDecimal kao maksimalna suma za isplatu osiguranja
     */
    public BigDecimal getMaxSum() {
        return maxSum;
    }

    /**
     * Postavlja maksimalnu sumu za isplatu osiguranja
     * @param maxSum BigDecimal kao maksimalna suma za isplatu osiguranja 
     */
    public void setMaxSum(BigDecimal maxSum) {
        this.maxSum = maxSum;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /**
     * @return <ul>
     * 	<li>true - Ako su oba objekta klase RiskType i imaju isti RiskTypeID</li>
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
        final RiskType other = (RiskType) obj;
        if (!Objects.equals(this.riskTypeID, other.riskTypeID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "name: " + name;
    }

   

    @Override
    public String getUpdateString(DomainType d) {
        RiskType type=(RiskType)d;
         String s="name='"+type.getName()+"', "
                    + "domain='"+type.getDomain()+"', "
                    + "description='"+type.getDescription()+"', "
                    + "minSum="+type.getMinSum()+","
                    + "maxSum="+type.getMaxSum()+","
            + "WHERE id="+type.getRiskTypeID();
         
         return s;
    }

    @Override
    public void setId(Long id) {
       riskTypeID=id;
    }

    @Override
    public List<DomainType> getRS(ResultSet rs) {
         List<DomainType> list=new ArrayList<>();
        try {
            
            while(rs.next()){
                RiskType c=new RiskType();
                c.setRiskTypeID(rs.getLong("id"));
                c.setName(rs.getString("name"));
                c.setDomain(rs.getString("domain"));
                c.setDescription(rs.getString("description"));
                c.setMaxSum(rs.getBigDecimal("maxsum"));
                c.setMinSum(rs.getBigDecimal("minsum"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Claim.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public String getDeleteString() {
        return " id= "+riskTypeID;
    }
    
    
    
    
}
