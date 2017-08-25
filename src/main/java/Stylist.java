import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

//class
public class Stylist{
  private int id;
  private String name;
  private String speciality;
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

  //returns all database instances of Stylist created
  public static List<Stylist> all(){
    String sql = "SELECT id, name, speciality FROM stylists";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
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

//saving new instance of stylist to database
public void save(){
  try(Connection con = DB.sql2o.open()){
    String sql = "INSERT INTO stylists (name, speciality) VALUES (:name, :speciality)";
    this.id = (int) con.createQuery(sql, true)
    .addParameter("name", this.name)
    .addParameter("speciality", this.speciality)
    .executeUpdate();
    getKey();
  }
}
}
