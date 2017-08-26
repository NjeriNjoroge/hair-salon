import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest{

//telling the tests to use only this dedicated database
  @Before
    public void setUp() {
      DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "gnjoroge", "1234");
    }


//clearing the test database
@After
public void tearDown() {
try(Connection con = DB.sql2o.open()) {
  String deleteClientsQuery = "DELETE FROM clients *;";
  String deleteStylistsQuery = "DELETE FROM stylists *;";
  con.createQuery(deleteClientsQuery).executeUpdate();
  con.createQuery(deleteStylistsQuery).executeUpdate();
}
}

//instance of Client
  @Test
  public void client_createsInstanceOfClient_true(){
    Client testClient = new Client("Grace", 710123456);
    assertEquals(true, testClient instanceof Client);
  }

//returns attributes of client class
  @Test
  public void phone_returnsClientsPhoneNumber_integer(){
    Client testClient = new Client("Grace", 710123456);
    assertEquals(710123456, testClient.getPhone());
  }

//compares client objects
@Test
public void equals_returnsTrueIfNamesAretheSame(){
  Client testClient = new Client("Grace", 710123456);
  Client firstClient = new Client("Grace", 710123456);
  assertTrue(testClient.equals(firstClient));
}

//returns all new instances of client
    @Test
    public void all_displaysAllClientInstances_array(){
      Client testClient = new Client("Ingrid", 710123456);
      Client firstClient = new Client("Jon Snow", 710123456);
      assertEquals(true, Client.all().contains(testClient));
      assertEquals(true, Client.all().contains(firstClient));
    }

//assigning and accessing client IDs
    @Test
    public void fetchId_locatesClientWithId_firstClient(){
      Client firstClient = new Client("Jon Snow", 710123456);
      assertEquals(1, firstClient.fetchId());
    }


//locating specific Clients using their IDs
  @Test
  public void find_locatesClientWithId_secondClient(){
    Client firstClient = new Client("Jon Snow", 710123456);
    Client secondClient = new Client("Ingrid", 710123456);
    assertEquals(Client.find(secondClient.fetchId()), secondClient);
  }
}
