package rs.bg.ac.student.ivana.MavenCommon.domain;

import static org.junit.jupiter.api.Assertions.*;

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
class ClientContactsTest {

	ClientContacts clientContacts;
	@BeforeEach
	void setUp() throws Exception {
		clientContacts= new ClientContacts();
	}

	@AfterEach
	void tearDown() throws Exception {
		clientContacts=null;
		resultSet = null;
	}

	@Test
	void testClientContacts() {
		clientContacts = new ClientContacts();
		assertNotNull(clientContacts);
	}

	@Test
	void testClientContactsLongStringStringStringStringClient() {
		Client client= new Client();
		clientContacts = new ClientContacts(1l, "a1", "t1", "p1", "em",client);
		assertNotNull(clientContacts);
		assertEquals(1l,clientContacts.getContactID());
		assertEquals("a1",clientContacts.getAddress());
		assertEquals("t1",clientContacts.getTown());
		assertEquals("p1",clientContacts.getPhone());
		assertEquals("em",clientContacts.getEmail());
		assertEquals(client,clientContacts.getClient());
	}

	@Test
	void testGetTableName() {
		assertEquals("clientContacts", clientContacts.getTableName());
	}

	@Test
	void testGetColumnNamesForInsert() {
		assertEquals("id, address, town,  phone, email, clientID", clientContacts.getColumnNamesForInsert());
	}

	@Test
	void testGetInsertValues() {
		StringBuilder sb = new StringBuilder();
		Client client = new Client();
		client.setClientID(1l);
        sb.append(1l).append(",")
                .append("'").append("a1").append("',")
                .append("'").append("t1").append("',")
                .append("'").append("p1").append("',")
                .append("'").append("em").append("',")
                .append(client.getClientID());
        assertEquals("1,'a1','t1','p1','em',1",sb.toString());
	}

	@Test
	void testGetJoinCondition() {
		assertEquals("",clientContacts.getJoinCondition());
	}

	@Test
	void testGetUpdateString() {
		clientContacts.setAddress("a1");
		clientContacts.setTown("t1");
		clientContacts.setPhone("p1");
		clientContacts.setEmail("em");
		clientContacts.setContactID(1l);
		String s="address='"+clientContacts.getAddress()+"', "
                + "town='"+clientContacts.getTown()+"', "
                + "phone='"+clientContacts.getPhone()+"', "
                + "email='"+clientContacts.getEmail()+"'  "
        + "WHERE id="+clientContacts.getContactID();
		assertEquals("address='a1', town='t1', phone='p1', email='em'  WHERE id=1",s);
	}

	@Test
	void testGetContactID() {
		clientContacts.setContactID(1l);
		assertEquals(1l,clientContacts.getContactID() );
	}

	@Test
	void testSetContactID() {
		clientContacts.setContactID(1l);
		assertEquals(1l,clientContacts.getContactID() );
	}

	@Test
	void testGetAddress() {
		clientContacts.setAddress("a1");
		assertEquals("a1",clientContacts.getAddress());
	}

	@Test
	void testSetAddress() {
		clientContacts.setAddress("a1");
		assertEquals("a1",clientContacts.getAddress());
	}

	@Test
	void testGetTown() {
		clientContacts.setTown("t1");
		assertEquals("t1",clientContacts.getTown());
	}

	@Test
	void testSetTown() {
		clientContacts.setTown("t1");
		assertEquals("t1",clientContacts.getTown());
	}

	@Test
	void testGetPhone() {
		clientContacts.setPhone("p1");
		assertEquals("p1",clientContacts.getPhone());
	}

	@Test
	void testSetPhone() {
		clientContacts.setPhone("p1");
		assertEquals("p1",clientContacts.getPhone());
	}

	@Test
	void testGetEmail() {
		clientContacts.setEmail("em");
		assertEquals("em",clientContacts.getEmail());
	}

	@Test
	void testSetEmail() {
		clientContacts.setEmail("em");
		assertEquals("em",clientContacts.getEmail());
	}

	@Test
	void testGetClient() {
		Client client = new Client();
		clientContacts.setClient(client);
		assertEquals(client,clientContacts.getClient());
	}

	@Test
	void testSetClient() {
		Client client = new Client();
		clientContacts.setClient(client);
		assertEquals(client,clientContacts.getClient());
	}

	@ParameterizedTest  
	@CsvSource({
		"1,2,false",
		"1,1,true"
	})
	void testEqualsObject(String id1,String id2, boolean eq) {
		clientContacts.setContactID(Long.valueOf(id1));
		ClientContacts clientContacts2 = new ClientContacts();
		clientContacts2.setContactID(Long.valueOf(id2));
		assertEquals(eq, clientContacts.equals(clientContacts2));
	}

	@Test
	void testSetId() {
		clientContacts.setId(1l);
		assertEquals(1l,clientContacts.getContactID());
	}

	@Mock
	ResultSet resultSet;
	@Test
	void testGetRS() {
		try {
        	resultSet = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet.getLong("id")).thenReturn(1l);
            Mockito.when(resultSet.getString("address")).thenReturn("a1");
            Mockito.when(resultSet.getString("town")).thenReturn("t1");
            Mockito.when(resultSet.getString("email")).thenReturn("em");
            Mockito.when(resultSet.getString("phone")).thenReturn("p1");
            Mockito.when(resultSet.getLong("clientid")).thenReturn(1l);
        	Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        	
            List<DomainType> contacts= clientContacts.getRS(resultSet);
            ClientContacts contact= (ClientContacts)contacts.get(0);

            assertNotNull(contacts);
            assertNotNull(contact);
    		assertEquals(1l, contact.getContactID());
    		assertEquals("a1", contact.getAddress());
    		assertEquals("t1", contact.getTown());
    		assertEquals("em", contact.getEmail());
    		assertEquals("p1", contact.getPhone());
    		assertEquals(1l, contact.getClient().getClientID());
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}

	@Test
	void testGetDeleteString() {
		clientContacts.setContactID(1l);
		Client client = new Client();
		client.setClientID(1l);
		clientContacts.setClient(client);
		assertEquals(" id= 1 and clientID= 1",clientContacts.getDeleteString());
	}

}
