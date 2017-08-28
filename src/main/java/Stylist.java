import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

//class
public class Stylist{
  private int id;
  private String name;
  private String speciality;


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

//overrides the equals method
  @Override
  public boolean equals(Object otherStylist){
    if (!(otherStylist instanceof Stylist)){
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName());
    }
  }

  //returns all database instances of Stylist created
  public static List<Stylist> all(){
    String sql = "SELECT id, name, speciality FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

//adds clients to stylist
public List<Client> getClients(){
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM clients where stylistId=:id";
    return con.createQuery(sql)
    .addParameter("id", this.id)
    .executeAndFetch(Client.class);
  }
}
  //gets Stylist id
  public int getId(){
    return id;
  }

//locating Stylist with their assigned id
  public static Stylist find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM stylists where id=:id";
      Stylist stylist = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }


//saving new instance of stylist to database
public void save() {
  try(Connection con = DB.sql2o.open()){
    String sql = "INSERT INTO stylists (name, speciality) VALUES (:name, :speciality)";
    this.id = (int) con.createQuery(sql, true)
    .addParameter("name", this.name)
    .addParameter("speciality", this.speciality)
    .executeUpdate()
    .getKey();
  }
}

//deleting stylist objects
public void delete() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM stylists WHERE id = :id";
    con.createQuery(sql)
    .addParameter("id", id)
    .executeUpdate();
  }
}

//updating stylist details
public void update(String name, String speciality) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE stylists SET name = :name, speciality = :speciality WHERE id = :id";
    con.createQuery(sql)
    .addParameter("name", name)
    .addParameter("speciality", speciality)
    .addParameter("id", id)
    .executeUpdate();
  }
}

}
