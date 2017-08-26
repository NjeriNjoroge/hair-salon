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
    Client testClient = new Client("Grace", 710123456, 1);
    assertEquals(true, testClient instanceof Client);
  }

//returns attributes of client class
  @Test
  public void phone_returnsClientsPhoneNumber_integer(){
    Client testClient = new Client("Grace", 710123456, 1);
    assertEquals(710123456, testClient.getPhone());
  }

//compares client objects
@Test
public void equals_returnsTrueIfNamesAretheSame(){
  Client testClient = new Client("Grace", 710123456, 1);
  Client firstClient = new Client("Grace", 710123456, 1);
  assertTrue(testClient.equals(firstClient));
}

//returns all new instances of client
    @Test
    public void all_displaysAllClientInstances_array(){
      Client testClient = new Client("Ingrid", 710123456, 1);
      testClient.save();
      Client firstClient = new Client("Jon Snow", 710123456, 1);
      firstClient.save();
      assertEquals(true, Client.all().get(0).equals(testClient));
      assertEquals(true, Client.all().get(1).equals(firstClient));
    }


//saving new clients to DB
@Test
public void save_savesIntoDatabase_true() {
    Client firstClient = new Client("Jon Snow", 710123456, 1);
    firstClient.save();
    assertTrue(Client.all().get(0).equals(firstClient));
}

//making sure the id assigned in DB is the same as the same as the objects
@Test
public void save_assignsIdToObject(){
  Client firstClient = new Client("Jon Snow", 710123456, 1);
  firstClient.save();
  Client savedClient = Client.all().get(0);
  assertEquals(firstClient.getId(), savedClient.getId());
}


//assigning and accessing client IDs
    @Test
    public void fetchId_locatesClientWithId_firstClient(){
      Client firstClient = new Client("Jon Snow", 710123456, 1);
      firstClient.save();
      assertTrue(firstClient.getId() > 0);
    }

//locating specific Clients using their IDs
  @Test
  public void find_locatesClientWithId_secondClient(){
    Client firstClient = new Client("Jon Snow", 710123456, 1);
    firstClient.save();
    Client secondClient = new Client("Ingrid", 710123456, 1);
    secondClient.save();
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }

  //updating  client details
  @Test
  public void update_updatesClientDetails_true(){
    Client myClient = new Client("grace", 7485934, 1);
    myClient.save();
    myClient.update("sansa", 123456);
    assertEquals("sansa", Client.find(myClient.getId()).getName());
  }

}
