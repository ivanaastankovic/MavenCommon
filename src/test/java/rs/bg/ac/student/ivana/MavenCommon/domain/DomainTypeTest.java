package rs.bg.ac.student.ivana.MavenCommon.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainTypeTest {

	protected DomainType domainType;
	@Test
	void testGetTableName() {
		domainType = new Admin();
		
		assertEquals("admin",domainType.getTableName());
		
		domainType=new Claim();
		assertEquals("claim",domainType.getTableName());
		
		domainType=new Client();
		assertEquals("clients",domainType.getTableName());
		
		domainType=new ClientContacts();
		assertEquals("clientContacts",domainType.getTableName());
				
		domainType=new RiskType();
		assertEquals("riskType",domainType.getTableName());
		
	}

	@Test
	void testGetColumnNamesForInsert() {
		
	}

	@Test
	void testGetInsertValues() {
		fail("Not yet implemented");
	}

	@Test
	void testSetId() {
		fail("Not yet implemented");
	}

	@Test
	void testGetJoinCondition() {
		fail("Not yet implemented");
	}

	@Test
	void testGetUpdateString() {
		fail("Not yet implemented");
	}

	@Test
	void testGetRS() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDeleteString() {
		fail("Not yet implemented");
	}

}
