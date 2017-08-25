import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest{

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
public void all_displaysAllInstancesOfStylist_array(){
  Stylist testStylist = new Stylist("Sansa", "Loctician");
  Stylist firstStylist = new Stylist("Arya", "Shampooist");
  assertEquals(true, Stylist.all().contains(testStylist));
  assertEquals(true, Stylist.all().contains(firstStylist));
}

//assiging and accessing stylist IDs
@Test
public void getId_locatesStylistWithId_testStylist(){
  Stylist testStylist = new Stylist("Sansa", "Loctician");
  assertEquals(1, testStylist.getId());
}

//clearing previously created Stylis
@Test
public void clear_clearAllPreviousStylists_0(){
  Stylist testStylist = new Stylist("Sansa", "Loctician");
  Stylist.clear();
  assertEquals(Stylist.all().size(), 0);
}

//locating specific Stylists using their IDs
@Test
public void find_locatesStylistWithId_testStylist(){
  Stylist testStylist = new Stylist("Sansa", "Loctician");
  Stylist firstStylist = new Stylist("Arya", "Shampooist");
  Stylist secondStylist = new Stylist("Ken", "Barber");
  assertEquals(Stylist.find(testStylist.getId()), testStylist);
}

}
