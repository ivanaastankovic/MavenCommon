package rs.bg.ac.student.ivana.MavenCommon.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AdminTest {
	
	private Admin admin;
	@BeforeEach
	void setUp() throws Exception {
		admin = new Admin();
	}

	@AfterEach
	void tearDown() throws Exception {
		admin=null;
	}

	@Test
	void testAdmin() {
		admin = new Admin();
		assertNotNull(admin);
	}

	@Test
	void testAdminStringString() {
		admin = new Admin("ivana","ivana123");
		assertNotNull(admin);
		assertEquals("ivana", admin.getUsername());
		assertEquals("ivana123",admin.getPassword());
	}

	@Test
	void testGetPassword() {
		admin.setPassword("ivana123");
		assertEquals("ivana123",admin.getPassword());
	}

	
	@Test
	void testSetPassword() {		
		admin.setPassword("ivana123");
		assertEquals("ivana123",admin.getPassword());
	}
	@Test
	void testGetUsername() {
		admin.setUsername("ivana");
		assertEquals("ivana", admin.getUsername());
	}

	@Test
	void testSetUsername() {
		admin.setUsername("ivana");
		assertEquals("ivana",admin.getUsername());
	}
	
	@ParameterizedTest  
	@CsvSource({
		"pera,pera123,pera,pera123,true",
		"pera,pera123,pera,zika123,false",
		"pera,pera123,zika,pera123,false",
		"pera,pera123,zika,zika123,false"
	})
	void testEqualsObject(String u1, String p1, String u2, String p2, boolean eq) {
		Admin a1= new Admin(u1,p1);
		Admin a2 = new Admin(u2,p2);
		
		assertEquals(eq,a1.equals(a2));
		
	}

}
