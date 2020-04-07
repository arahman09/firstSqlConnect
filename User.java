package MySQL_Connection;
public class User
{

    String SerialNumber;
    String LastName;
    String FirstName;
    String Address;
    String City;
    String ZipCode;

//    public User(){}
//
//    public User(String stName, String stID)
//    {
//        this.stName = stName;
//        this.stID = stID;
//    }

    public User(String SerialNumber, String LastName, String FirstName, String Address, String City, String ZipCode)
    {
        this.SerialNumber= SerialNumber;
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.Address = Address;
        this.City = City;
        this.ZipCode = ZipCode;
    }
    public  String getSerialNumber() {
        return SerialNumber;
    }

    public String getLastName()
    {
        return LastName;
    }

  //  public void setStName(String stName)
//    {
//        this.stName = stName;
//    }

    public String getFirstName ()

    {
        return FirstName;
    }

//    public void setStID(String stID)
//    {
//        this.stID = stID;
//    }

    public String getAddress()
    {
        return Address;
    }

//    public void setStDOB(String stDOB)
//    {
//        this.stDOB = stDOB;
//    }

    public String getCity()
    {
        return City;

    }
    public String getZipCode()
    {
        return ZipCode;
    }
}
