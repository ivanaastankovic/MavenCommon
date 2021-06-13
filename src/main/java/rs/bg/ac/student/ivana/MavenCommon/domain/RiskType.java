package rs.bg.ac.student.ivana.MavenCommon.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RiskType implements DomainType{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long riskTypeID;
    private String name;
    private String domain;
    private String description;
    private BigDecimal minSum;
    private BigDecimal maxSum;

    public RiskType() {
    }

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

    public Long getRiskTypeID() {
        return riskTypeID;
    }

    public void setRiskTypeID(Long riskTypeID) {
        this.riskTypeID = riskTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMinSum() {
        return minSum;
    }

    public void setMinSum(BigDecimal minSum) {
        this.minSum = minSum;
    }

    public BigDecimal getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(BigDecimal maxSum) {
        this.maxSum = maxSum;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
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
