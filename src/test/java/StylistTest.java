import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest{

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

  //instance of Stylist
@Test
public void stylist_createsInstanceOfStylist_true(){
  Stylist testStylist = new Stylist("Carol", "Loctician");
  assertEquals(true, testStylist instanceof Stylist);
}

//return attributes of Stylist class
@Test
public void name_returnsNameOfCreatedStylist_string(){
  Stylist testStylist = new Stylist("Sansa", "Loctician");
  assertEquals("Sansa", testStylist.getName());
}

//returns all new instances of Stylist
@Test
public void all_displaysAllInstancesOfStylist_true(){
  Stylist testStylist = new Stylist("Sansa", "Loctician");
  testStylist.save();
  Stylist firstStylist = new Stylist("Arya", "Shampooist");
  firstStylist.save();
  assertEquals(true, Stylist.all().get(0).equals(testStylist));
  assertEquals(true, Stylist.all().get(1).equals(firstStylist));
}

//accessing stylist IDs
@Test
public void getId_stylistIsCreatedWithId_1(){
  Stylist testStylist = new Stylist("Sansa", "Loctician");
  testStylist.save();
  assertTrue(testStylist.getId() > 0);
}

//clearing previously created Stylis
// @Test
// public void clear_clearAllPreviousStylists_0(){
//   Stylist testStylist = new Stylist("Sansa", "Loctician");
//   assertEquals(Stylist.all().size(), 0);
// }

//locating specific Stylists using their IDs
@Test
public void find_locatesStylistWithId_testStylist(){
  Stylist testStylist = new Stylist("Sansa", "Loctician");
  testStylist.save();
  Stylist firstStylist = new Stylist("Arya", "Shampooist");
  firstStylist.save();
  assertEquals(Stylist.find(firstStylist.getId()), firstStylist);
}

//makes sure Stylists instantiates with empty client List
//   @Test
// public void getClients_initiallyReturnsEmptyList_ArrayList() {
//   Stylist testStylist = new Stylist("Sansa", "Loctician");
//   assertEquals(0, testStylist.getClient().size());
// }

//adding client to Stylist
// @Test
// public void addClient_addsClientToStylist_true(){
//   Stylist testStylist = new Stylist("Sansa", "Loctician");
//   Client goodClient = new Client("Ingrid", 710123456);
//   testStylist.addClient(goodClient);
//   assertTrue(testStylist.getClient().contains(goodClient));
// }

//comparing objects we retrieve from database by overriding equals()
@Test
public void equals_returnsTrueIfNamesAretheSame(){
  Stylist testStylist = new Stylist("Arya", "Shampooist");
  Stylist firstStylist = new Stylist("Arya", "Shampooist");
  assertTrue(testStylist.equals(firstStylist));
}

//saves objects into database
@Test
public void save_returnsTrueIfNamesAretheSame(){
  Stylist testStylist = new Stylist("Sansa", "Loctician");
  testStylist.save();
  assertTrue(Stylist.all().get(0).equals(testStylist));
}
//assigns unique ids when saved to DB
@Test
public void save_assignsIdToStylistObject(){
  Stylist testStylist = new Stylist("Sansa", "Loctician");
  testStylist.save();
  Stylist savedStylist = Stylist.all().get(0);
  assertEquals(testStylist.getId(), savedStylist.getId());
}


}
