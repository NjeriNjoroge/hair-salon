import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import org.sql2o;

//class
public class Client{
  private int id;
  private String name;
  private int phone;
  //private static List<Client> instances = new ArrayList<Client>();

  //Constructor
  public Client (String name, int phone){
    this.name = name;
    this.phone = phone;
    // instances.add(this);
    // mId = instances.size();
  }



//returns phone attributes
public int getPhone(){
  return phone;
}

//returns name attributes
public String getName(){
  return name;
}

//returns all Client created
public static List<Client> all(){
  String sql = "SELECT id, name, phone FROM clients";
  try(Connection con = DB.sql2o.open()){
    return con.createQuery(sql).executeAndFetch(Client.class);
  }
}

//comparing client objects using .equals
@override
public boolean equals(Object otherClient){
  if (!(otherClient instanceof Client)) {
    return false;
  } else {
    Client newClient = (Client) otherClient;
    return this.getName().equals(newClient.getName());
  }
}
//
public List<Stylist> getStylist(){

}

//gets client id
public int fetchId(){
  return id;
}

//saving objects to DB
public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO clients (name, phone) VALUES (:name, :phone)";
    con.createQuery(sql)
    .addParameter("name", this.name)
    .addParameter("phone", this.phone)
    .executeUpdate();
  }
}

//locating Client with their assigned id
public static Client find(int id){

}
}
