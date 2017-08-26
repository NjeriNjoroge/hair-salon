import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

//class
public class Client{
  private int id;
  private int stylistId;
  private String name;
  private int phone;

  //Constructor
  public Client (String name, int phone, int stylistId){
    this.name = name;
    this.phone = phone;
    this.stylistId = stylistId;
  }

//returns phone attributes
public int getPhone(){
  return phone;
}

//returns name attributes
public String getName(){
  return name;
}

//gets the stylist id
public int getStylistId(){
  return stylistId;
}

//returns all Client created
public static List<Client> all(){
  String sql = "SELECT id, name, phone, stylistId FROM clients";
  try(Connection con = DB.sql2o.open()){
    return con.createQuery(sql).executeAndFetch(Client.class);
  }
}

//comparing client objects using .equals
@Override
public boolean equals(Object otherClient){
  if (!(otherClient instanceof Client)) {
    return false;
  } else {
    Client newClient = (Client) otherClient;
    return this.getName().equals(newClient.getName()) && this.getId() == newClient.getId() && this.getStylistId() == newClient.getStylistId();
  }
}
//


//gets client id
public int getId(){
  return id;
}

//saving objects to DB
public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO clients (name, phone, stylistId) VALUES (:name, :phone, :stylistId)";
    this.id = (int) con.createQuery(sql, true)
    .addParameter("name", this.name)
    .addParameter("phone", this.phone)
    .addParameter("stylistId", this.stylistId)
    .executeUpdate()
    .getKey();
  }
}

//locating Client with their assigned id
public static Client find(int id){
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM clients where id=:id";
    Client client = con.createQuery(sql)
    .addParameter("id", id)
    .executeAndFetchFirst(Client.class);
    return client;
  }
}

}
