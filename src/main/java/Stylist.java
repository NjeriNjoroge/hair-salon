import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

//class
public class Stylist{
  private int mId;
  private String mName;
  private String mSpeciality;
  private static List<Stylist> instances = new ArrayList<Stylist>();
  private List<Client> mClients;

  //Constructor
  public Stylist (String name, String speciality){
    mName = name;
    mSpeciality = speciality;
    instances.add(this);
    mId = instances.size();
    mClients = new ArrayList<Client>();
  }

  //gets name attributes
  public String getName(){
    return mName;
  }

  //gets speciality attribute
  public String getSpeciality(){
    return mSpeciality;
  }

  //returns all instances of Stylist created
  public static List<Stylist> all(){
    return instances;
  }

  //gets Stylist id
  public int getId(){
    return mId;
  }

  //clears all instances of stylist
  public static void clear(){
    instances.clear();
  }

  //locating Stylist with their assigned id
  public static Stylist find(int id){
    return instances.get(id - 1);
  }

//makes sure Stylists instantiates with empty client List
public List<Client> getClients(){
  return mClients;
}

//adding client to Stylist
public void addClient(Client client){
  mClients.add(client);
}
}
