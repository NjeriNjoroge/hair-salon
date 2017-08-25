import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest{


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
    public void getId_locatesClientWithId_firstClient(){
      Client firstClient = new Client("Jon Snow", 710123456);
      assertEquals(1, firstClient.getId());
    }

//clearing previously created Client
    @Test
    public void clear_clearAllPreviouslyCreatedClients_0(){
      Client firstClient = new Client("Jon Snow", 710123456);
      Client testClient = new Client("Ingrid", 710123456);
      Client.clear();
      assertEquals(Client.all().size(), 0);
    }

//locating specific Clients using their IDs
  @Test
  public void find_locatesClientWithId_secondClient(){
    Client firstClient = new Client("Jon Snow", 710123456);
    Client secondClient = new Client("Ingrid", 710123456);
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }
}
