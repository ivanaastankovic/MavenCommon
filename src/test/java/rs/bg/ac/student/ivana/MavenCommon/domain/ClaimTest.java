package rs.bg.ac.student.ivana.MavenCommon.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ClaimTest {

	private Claim claim;

	@BeforeEach
	void setUp() throws Exception {
		claim = new Claim();
	}

	@AfterEach
	void tearDown() throws Exception {
		claim = null;
	}

	@Test
	void testClaim() {
		claim = new Claim();
		assertNotNull(claim);
	}

	@Test
	void testClaimDateStringBigDecimalClientStatusRiskType() {
		Client c = new Client();
		Date d = new Date();
		RiskType r = new RiskType();
		claim = new Claim(d, "activity", new BigDecimal(100),c, Status.FILED,r);
		assertNotNull(claim);
		assertEquals(d,claim.getFileDate());
		assertEquals("activity", claim.getActivity());
		assertEquals(new BigDecimal(100), claim.getPaymentSum());
		assertEquals(c, claim.getClient());
		assertEquals(Status.FILED, claim.getStatus());
		assertEquals(r,claim.getRiskType());
	}

	@Test
	
	void testGetRiskType() {
		RiskType rt = new RiskType();
		claim.setRiskType(rt);
		assertEquals(rt,claim.getRiskType());
	}

	@Test
	void testSetRiskType() {
		RiskType rt = new RiskType();
		claim.setRiskType(rt);
		assertEquals(rt,claim.getRiskType());
	}

	@Test
	void testGetClaimID() {
		claim.setClaimID(1l);
		assertEquals(1l,claim.getClaimID());
	}

	@Test
	void testSetClaimID() {
		claim.setClaimID(1l);
		assertEquals(1l,claim.getClaimID());
	}

	@Test
	void testGetFileDate() {
		Date fileDate= new Date();
		claim.setFileDate(fileDate);
		assertEquals(fileDate,claim.getFileDate());
	}

	@Test
	void testSetFileDate() {
		Date fileDate= new Date();
		claim.setFileDate(fileDate);
		assertEquals(fileDate,claim.getFileDate());
	}

	@Test
	void testGetActivity() {
		claim.setActivity("A1");
		assertEquals("A1",claim.getActivity());
	}

	@Test
	void testSetActivity() {
		claim.setActivity("A1");
		assertEquals("A1",claim.getActivity());
	}

	@Test
	void testGetPaymentSum() {
		claim.setPaymentSum(new BigDecimal(100));
		assertEquals(new BigDecimal(100),claim.getPaymentSum());
	}

	@Test
	void testSetPaymentSum() {
		claim.setPaymentSum(new BigDecimal(100));
		assertEquals(new BigDecimal(100),claim.getPaymentSum());
	}

	@Test
	void testGetClient() {
		Client c = new Client();
		claim.setClient(c);
		assertEquals(c,claim.getClient());
	}

	@Test
	void testSetClient() {
		Client c = new Client();
		claim.setClient(c);
		assertEquals(c,claim.getClient());
	}

	@Test
	void testGetStatus() {
		claim.setStatus(Status.FILED);
		assertEquals(Status.FILED, claim.getStatus());
	}

	@Test
	void testSetStatus() {
		claim.setStatus(Status.FILED);
		assertEquals(Status.FILED, claim.getStatus());
	}

	@ParameterizedTest  
	@CsvSource({
		"1,2,false",
		"1,1,true"
	})
	void testEqualsObject(String c1,String c2, boolean eq) {
		claim.setClaimID(Long.valueOf(c1));
		Claim claim2=new Claim();
		claim2.setClaimID(Long.valueOf(c2));
		assertEquals(eq, claim.equals(claim2));
	}

	@Test
	void testToString() {
		claim.setId(5l);
		assertEquals("claimID "+ claim.getClaimID(), claim.toString());
	}
	
	@Mock
	ResultSet resultSet;
	@Test
	void testGetRS() {
		
		try {
			Date date = new Date();
			
        	resultSet = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet.getLong("c.id")).thenReturn(1l);
            Mockito.when(resultSet.getString("c.activity")).thenReturn("a");
            Mockito.when(resultSet.getBigDecimal("c.paymentSum")).thenReturn(new BigDecimal(1));
            Mockito.when(resultSet.getDate("c.fileDate")).thenReturn(new java.sql.Date(date.getTime()));
            Mockito.when(resultSet.getLong("c.clientID")).thenReturn(1l);
            Mockito.when(resultSet.getString("c.status")).thenReturn("FILED");
            Mockito.when(resultSet.getString("cl.jmbg")).thenReturn("j");
            Mockito.when(resultSet.getString("cl.firstName")).thenReturn("f");
            Mockito.when(resultSet.getString("cl.LastName")).thenReturn("l");
            Mockito.when(resultSet.getLong("c.riskTypeID")).thenReturn(1l);
            Mockito.when(resultSet.getString("r.name")).thenReturn("n");
            Mockito.when(resultSet.getBigDecimal("r.minSum")).thenReturn(new BigDecimal(1));
            Mockito.when(resultSet.getBigDecimal("r.maxSum")).thenReturn(new BigDecimal(2));
        	Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        	
            List<DomainType> claims= claim.getRS(resultSet);
            Claim claim= (Claim)claims.get(0);
            
            assertNotNull(claims);
            assertNotNull(claim);
    		assertEquals(1l, claim.getClaimID());
    		assertEquals("a", claim.getActivity());
    		assertEquals(new BigDecimal(1), claim.getPaymentSum());
    		assertEquals(date, claim.getFileDate());
    		assertEquals(Status.FILED, claim.getStatus());
    		assertEquals(1l, claim.getClient().getClientID());
    		
    		assertEquals("j", claim.getClient().getJmbg());
    		assertEquals(date, claim.getFileDate());
    		assertEquals("f", claim.getClient().getFirstName());
    		assertEquals("cl.LastName", claim.getClient().getLastName());
    		assertEquals(1l, claim.getRiskType().getRiskTypeID());
    		assertEquals("n", claim.getRiskType().getName());
    		assertEquals(new BigDecimal(0), claim.getRiskType().getMinSum());
    		assertEquals(new BigDecimal(0), claim.getRiskType().getMaxSum());
		} catch (SQLException e) {
        	e.printStackTrace();
        }
	}

	@Test
	void testGetDeleteString() {
		claim.setClaimID(1l);
		assertEquals(" c.id= 1", claim.getDeleteString());
	}
	
	@Test
	void testGetUpdateString() {
		claim.setStatus(Status.FILED);
		claim.setPaymentSum(new BigDecimal(1));
		claim.setClaimID(1l);
         String s="status='"+claim.getStatus()+"', "
                    + "paymentSum= "+claim.getPaymentSum()+" "
                    + "WHERE id="+claim.getClaimID();
         assertEquals("status='FILED', paymentSum= 1 WHERE id=1", s);
	}
	
	@Test
	void testGetJoinCondition()
	{
		assertEquals(" c JOIN clients cl ON (c.clientID=cl.id) JOIN riskType r ON (c.riskTypeid=r.id) ", claim.getJoinCondition());
	}
}
