package rs.bg.ac.student.ivana.MavenCommon.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
class ClientTest {
	Client client;

	@BeforeEach
	void setUp() throws Exception {
		client = new Client();
	}

	@AfterEach
	void tearDown() throws Exception {
		client = null;
	}

	@Test
	void testClient() {
		Client client = new Client();
		assertNotNull(client);
	}

	@Test
	void testGetTableName() {
		assertEquals("clients", client.getTableName());
	}

	@Test
	void testGetColumnNamesForInsert() {
		assertEquals("id, firstName, lastName, jmbg, signatureDate, contractType", client.getColumnNamesForInsert());
	}

	@Test
	void testGetInsertValues() {
		  StringBuilder sb = new StringBuilder();
		  Date signatureDate = new Date();
		  
	        sb.append(1l).append(",")
	                .append("'").append("f").append("',")
	                .append("'").append("l").append("',")
	                .append("'").append("j").append("',")
	                .append("'").append(new java.sql.Date(signatureDate.getTime())).append("',")
	                .append("'").append(ContractType.ONE_YEAR).append("'");
		assertEquals("1,'f','l','j','"+new java.sql.Date(signatureDate.getTime())+"','ONE_YEAR'",sb.toString());
	}

	@Test
	void testGetJoinCondition() {
		assertEquals("",client.getJoinCondition());
	}

	@Test
	void testGetUpdateString() {
		client.setFirstName("f");
		client.setLastName("l");
		client.setJmbg("j");
		Date signatureDate = new Date();
		client.setClientID(1l);
		client.setSignatureDate(signatureDate);
		client.setContractType(ContractType.ONE_YEAR);
         String s="firstName='"+client.getFirstName()+"', "
                    + "lastName='"+client.getLastName()+"', "
                    + "jmbg='"+client.getJmbg()+"', "
                    + "signatureDate='"+client.getSignatureDate()+"', "
                    + "contractType='"+client.getContractType()+"'  "
            + "WHERE id="+client.getClientID();
         assertEquals("firstName='f', lastName='l', jmbg='j', signatureDate='"+signatureDate+"', contractType='ONE_YEAR'  WHERE id=1", s);
	}

	@Test
	void testGetClientID() {
		client.setClientID(1l);
		assertEquals(1l, client.getClientID());
	}

	@Test
	void testSetClientID() {
		client.setClientID(1l);
		assertEquals(1l, client.getClientID());
	}
	@Test
	void testGetFirstName() {
		client.setFirstName("f");
		assertEquals("f", client.getFirstName());
	}

	@Test
	void testSetFirstName() {
		client.setFirstName("f");
		assertEquals("f", client.getFirstName());
	}

	@Test
	void testGetLastName() {
		client.setLastName("l");
		assertEquals("l", client.getLastName());
	}

	@Test
	void testSetLastName() {
		client.setLastName("l");
		assertEquals("l", client.getLastName());
	}

	@Test
	void testGetJmbg() {
		client.setJmbg("j");
		assertEquals("j", client.getJmbg());
	}

	@Test
	void testSetJmbg() {
		client.setJmbg("j");
		assertEquals("j", client.getJmbg());
	}

	@Test
	void testGetSignatureDate() {
		Date d = new Date();
		client.setSignatureDate(d);
		assertEquals(d,client.getSignatureDate());
	}

	@Test
	void testSetSignatureDate() {
		Date d = new Date();
		client.setSignatureDate(d);
		assertEquals(d,client.getSignatureDate());
	}
	@Test
	void testGetContacts() {
		List<ClientContacts> contacts = new LinkedList<ClientContacts>();
		client.setContacts(contacts);
		assertEquals(contacts, client.getContacts());
	}

	@Test
	void testSetContacts() {
		List<ClientContacts> contacts = new LinkedList<ClientContacts>();
		client.setContacts(contacts);
		assertEquals(contacts, client.getContacts());
	}

	@Test
	void testGetContractType() {
		client.setContractType(ContractType.ONE_YEAR);
		assertEquals(ContractType.ONE_YEAR, client.getContractType());
	}

	@Test
	void testSetContractType() {
		client.setContractType(ContractType.ONE_YEAR);
		assertEquals(ContractType.ONE_YEAR, client.getContractType());
	}

	@ParameterizedTest  
	@CsvSource({
		"1,2,false",
		"1,1,true"
	})
	void testEqualsObject(String id1,String id2, boolean eq) {
		client.setClientID(Long.valueOf(id1));
		Client client2 = new Client();
		client2.setClientID(Long.valueOf(id2));
		assertEquals(eq, client.equals(client2));
	}

	@Test
	void testToString() {
		client.setFirstName("f");
		client.setLastName("l");
		client.setClientID(1l);
		assertEquals("Client: 1, f, l", client.toString());
	}

	@Test
	void testSetId() {
		client.setClientID(1l);
		assertEquals(1l, client.getClientID());
	}
	@Mock
	ResultSet resultSet;
	@Test
	void testGetRS() {
		
		try {
			Date signatureDate = new Date();
        	resultSet = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet.getLong("id")).thenReturn(1l);
            Mockito.when(resultSet.getString("firstName")).thenReturn("f");
            Mockito.when(resultSet.getString("lastName")).thenReturn("l");
            Mockito.when(resultSet.getString("jmbg")).thenReturn("j");
            Mockito.when(resultSet.getString("contractType")).thenReturn("ONE_YEAR");
            Mockito.when(resultSet.getDate("signatureDate")).thenReturn(new java.sql.Date(signatureDate.getTime()));
        	Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        	
            List<DomainType> clients= client.getRS(resultSet);
            Client client= (Client)clients.get(0);

            assertNotNull(clients);
            assertNotNull(client);
    		assertEquals(1l, client.getClientID());
    		assertEquals("f", client.getFirstName());
    		assertEquals("l", client.getLastName());
    		assertEquals("j", client.getJmbg());
    		ContractType ct =  ContractType.ONE_YEAR;
    		assertEquals(ct, client.getContractType());
    		assertEquals(signatureDate, client.getSignatureDate());
		} catch (SQLException e) {
        	e.printStackTrace();
        }
	}

	@Test
	void testGetDeleteString() {
		client.setJmbg("j");
		assertEquals(" jmbg= j", client.getDeleteString());
	}

}
