import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

//class
public class Stylist{
  private int mId;
  private String mName;
  private String mSpeciality;
  private static List<Stylist> instances = new ArrayList<Stylist>();

  //Constructor
  public Stylist (String name, String speciality){
    mName = name;
    mSpeciality = speciality;
    instances.add(this);
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
}
