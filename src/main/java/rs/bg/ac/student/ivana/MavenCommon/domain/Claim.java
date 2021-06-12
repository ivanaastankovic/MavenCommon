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

public class Claim implements DomainType{
    private Long claimID;
    private Date fileDate;
    private String activity;
    private BigDecimal paymentSum;
    private Client client;
    private Status status;
    private RiskType riskType;

    public Claim() {
    }

    public Claim(Date fileDate, String activity, BigDecimal paymentSum, Client client, Status status, RiskType riskType) {
       
        this.fileDate = fileDate;
        this.activity = activity;
        this.paymentSum = paymentSum;
        this.client = client;
        this.status = status;
        this.riskType = riskType;
    }

    public RiskType getRiskType() {
        return riskType;
    }

    public void setRiskType(RiskType riskType) {
        this.riskType = riskType;
    }

    public Long getClaimID() {
        return claimID;
    }

    public void setClaimID(Long claimID) {
        this.claimID = claimID;
    }

    public Date getFileDate() {
        return fileDate;
    }

    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public BigDecimal getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(BigDecimal paymentSum) {
        this.paymentSum = paymentSum;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
         return "id, fileDate, activity, paymentSum, clientID, status, riskTypeID";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(claimID).append(",")
                .append("'").append(new java.sql.Date(fileDate.getTime())).append("',")
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
                c.setFileDate(new Date(rs.getDate("c.fileDate").getTime()));
                c.setStatus(Status.valueOf(rs.getString("c.status")));
                Client cl=new Client();
                cl.setClientID(rs.getLong("c.clientID"));
                cl.setJmbg(rs.getString("cl.jmbg"));
                cl.setFirstName(rs.getString("cl.firstName"));
                cl.setLastName("cl.LastName");
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
