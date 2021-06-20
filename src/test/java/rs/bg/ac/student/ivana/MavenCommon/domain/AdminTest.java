package rs.bg.ac.student.ivana.MavenCommon.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;

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
	
	@Test
	void getDeleteString()
	{
		assertThrows(UnsupportedOperationException.class,()->admin.getDeleteString());
	}
	
	@Mock
	ResultSet resultSet;
	@Test
	void testGetRS() {
		
		try {
        	resultSet = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet.getString("username")).thenReturn("u");
            Mockito.when(resultSet.getString("password")).thenReturn("p");
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        	
            List<DomainType> admins= admin.getRS(resultSet);
            Admin admin= (Admin)admins.get(0);

            assertNotNull(admins);
            assertNotNull(admin);
    		assertEquals("u", admin.getUsername());
    		assertEquals("p", admin.getPassword());
		} catch (SQLException e) {
        	e.printStackTrace();
        }
	}
	
	@Test
	void getTableName() {
		assertEquals("admin", admin.getTableName());
	}
}
