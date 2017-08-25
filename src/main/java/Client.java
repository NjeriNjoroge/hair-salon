import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

//class
public class Client{
  private int mId;
  private String mName;
  private int mPhone;
  private static List<Client> instances = new ArrayList<Client>();

  //Constructor
  public Client (String name, int phone){
    mName = name;
    mPhone = phone;
    instances.add(this);
    mId = instances.size();
  }

//returns phone attributes
public int getPhone(){
  return mPhone;
}

//returns name attributes
public String getName(){
  return mName;
}

//returns all Client instances
public static List<Client> all(){
  return instances;
}

//gets client id
public int getId(){
  return mId;
}

//clears all instances of client
public static void clear(){
  instances.clear();
}

//locating Client with their assigned id
public static Client find(int id){
  return instances.get(id - 1);
}
}
