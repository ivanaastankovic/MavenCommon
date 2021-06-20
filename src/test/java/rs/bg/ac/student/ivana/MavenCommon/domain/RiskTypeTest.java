package rs.bg.ac.student.ivana.MavenCommon.domain;

import static org.junit.jupiter.api.Assertions.*;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;


class RiskTypeTest {
	RiskType riskType;
	
	@BeforeEach
	void setUp() throws Exception {
		riskType= new RiskType();
	}

	@AfterEach
	void tearDown() throws Exception {
		riskType=null;
		resultSet = null;
	}


	@Test
	void testRiskType() {
		riskType = new RiskType();
		assertNotNull(riskType);
	}

	@Test
	void testRiskTypeLongStringStringStringBigDecimalBigDecimal() {
		RiskType rt = new RiskType(1l, "n1", "d1", "d1", new BigDecimal(1), new BigDecimal(2));
		assertNotNull(rt);
		assertEquals(1l,rt.getRiskTypeID());
		assertEquals("n1",rt.getName());
		assertEquals("d1",rt.getDomain());
		assertEquals("d1",rt.getDescription());
		assertEquals(new BigDecimal(1),rt.getMinSum());
		assertEquals(new BigDecimal(2),rt.getMaxSum());
	}

	@Test
	void testGetTableName() {
		assertEquals("riskType", riskType.getTableName());
	}

	@Test
	void testGetColumnNamesForInsert() {
		assertEquals("id, name, domain, description, minSum, maxSum",riskType.getColumnNamesForInsert());
	}

	@Test
	void testGetInsertValues() {
		StringBuilder sb = new StringBuilder();
        sb.append(1l).append(",")
                .append("'").append("n1").append("',")
                .append("'").append("d1").append("',")
                .append("'").append("d1").append("',")
                .append(new BigDecimal(1)).append(",")
                .append(new BigDecimal(2)).append(" ");
        assertEquals("1,'n1','d1','d1',1,2 ",sb.toString());
	}

	@Test
	void testGetJoinCondition() {
		assertEquals("",riskType.getJoinCondition());
	}

	@Test
	void testGetRiskTypeID() {
		riskType.setRiskTypeID(1l);
		assertEquals(1l,riskType.getRiskTypeID());
	}

	@Test
	void testSetRiskTypeID() {
		riskType.setRiskTypeID(1l);
		assertEquals(1l,riskType.getRiskTypeID());
	}

	@Test
	void testGetName() {
		riskType.setName("n1");
		assertEquals("n1",riskType.getName());
	}

	@Test
	void testSetName() {
		riskType.setName("n1");
		assertEquals("n1",riskType.getName());
	}

	@Test
	void testGetDomain() {
		riskType.setDomain("d1");
		assertEquals("d1",riskType.getDomain());
	}

	@Test
	void testSetDomain() {
		riskType.setDomain("d1");
		assertEquals("d1",riskType.getDomain());
	}

	@Test
	void testGetDescription() {
		riskType.setDescription("d1");
		assertEquals("d1",riskType.getDescription());
	}

	@Test
	void testSetDescription() {
		riskType.setDescription("d1");
		assertEquals("d1",riskType.getDescription());
	}

	@Test
	void testGetMinSum() {
		riskType.setMinSum(new BigDecimal(1));
		assertEquals(new BigDecimal(1),riskType.getMinSum());
	}

	@Test
	void testSetMinSum() {
		riskType.setMinSum(new BigDecimal(1));
		assertEquals(new BigDecimal(1),riskType.getMinSum());
	}

	@Test
	void testGetMaxSum() {
		riskType.setMaxSum(new BigDecimal(2));
		assertEquals(new BigDecimal(2),riskType.getMaxSum());
	}

	@Test
	void testSetMaxSum() {
		riskType.setMaxSum(new BigDecimal(2));
		assertEquals(new BigDecimal(2),riskType.getMaxSum());
	}

	@ParameterizedTest  
	@CsvSource({
		"1,2,false",
		"1,1,true"
	})
	void testEqualsObject(String id1,String id2, boolean eq) {
		riskType.setRiskTypeID(Long.valueOf(id1));
		RiskType rt2=new RiskType();
		rt2.setRiskTypeID(Long.valueOf(id2));
		assertEquals(eq,riskType.equals(rt2));
	}

	@Test
	void testToString() {
		riskType.setName("n1");
		assertEquals("name: n1",riskType.toString());
	}

	@Test
	void testGetUpdateString() {
		riskType.setRiskTypeID(1l);
		riskType.setName("n1");
		riskType.setDomain("d1");
		riskType.setDescription("d1");
		riskType.setMinSum(new BigDecimal(1));
		riskType.setMaxSum(new BigDecimal(2));
		String s="name='"+riskType.getName()+"', "
				+ "domain='"+riskType.getDomain()+"', "
				+ "description='"+riskType.getDescription()+"', "
				+ "minSum="+riskType.getMinSum()+","
				+ "maxSum="+riskType.getMaxSum()+","
				+ "WHERE id="+riskType.getRiskTypeID();
		assertEquals("name='n1', domain='d1', description='d1', minSum=1,maxSum=2,WHERE id=1",s);
	}

	@Test
	void testSetId() {
		riskType.setRiskTypeID(1l);
		assertEquals(1l,riskType.getRiskTypeID());
	}

	@Mock
	ResultSet resultSet;
	
	@Test
	void testgetRS(){
        try {
        	resultSet = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet.getLong("id")).thenReturn(1l);
            Mockito.when(resultSet.getString("name")).thenReturn("n1");
            Mockito.when(resultSet.getString("domain")).thenReturn("d1");
            Mockito.when(resultSet.getString("description")).thenReturn("d1");
            Mockito.when(resultSet.getBigDecimal("minsum")).thenReturn(new BigDecimal(1));
            Mockito.when(resultSet.getBigDecimal("maxsum")).thenReturn(new BigDecimal(2));
        	Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        	
            List<DomainType> riskTypes = riskType.getRS(resultSet);
            RiskType rt= (RiskType)riskTypes.get(0);

            assertNotNull(riskTypes);
            assertNotNull(rt);
    		assertEquals(1l, rt.getRiskTypeID());
    		assertEquals("n1", rt.getName());
    		assertEquals("d1", rt.getDomain());
    		assertEquals("d1", rt.getDescription());
    		assertEquals(new BigDecimal(1), rt.getMinSum());
    		assertEquals(new BigDecimal(2), rt.getMaxSum());
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}

	@Test
	void testGetDeleteString() {
		riskType.setRiskTypeID(1l);
		assertEquals(" id= 1", riskType.getDeleteString());
	}

}
