import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

//class
public class Stylist{
  private int id;
  private String name;
  private String Speciality;
  private List<Client> mClient;

  //Constructor
  public Stylist (String name, String speciality){
    this.name = name;
    this.speciality = speciality;
  }

  //gets name attributes
  public String getName(){
    return name;
  }

  //gets speciality attribute
  public String getSpeciality(){
    return speciality;
  }

  //returns all instances of Stylist created
  public static List<Stylist> all(){

  }

  //gets Stylist id
  public int getId(){
    return id;
  }

//locating Stylist with their assigned id
  public static Stylist find(int id){
    
  }

//makes sure Stylists instantiates with empty client List
public List<Client> getClient(){
  return mClient;
}

//adding client to Stylist
public void addClient(Client client){
  mClient.add(client);
}
}
