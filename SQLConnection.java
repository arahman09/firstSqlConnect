package MySQL_Connection;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


    public class SQLConnection  {

    public static Connection sqlConnection = null;
    public static PreparedStatement preparedStatement = null;

    public static Properties loadProperties() throws IOException
    {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream("/Volumes/D/GitClone/mysql/mysql.properties");
        properties.load(inputStream);
        inputStream.close();
        return properties;
    }

    public static Connection connectToSQLDatabase() throws IOException, SQLException, ClassNotFoundException
    {
        Properties properties = loadProperties();

        String driverClass = properties.getProperty("MYSQLJDBC.driver");
        String url = properties.getProperty("MYSQLJDBC.url");
        String userName = properties.getProperty("MYSQLJDBC.userName");
        String password = properties.getProperty("MYSQLJDBC.password");

        Class.forName(driverClass);
        sqlConnection = DriverManager.getConnection(url,userName,password);
        System.out.println("Database is connected");
        return sqlConnection;
    }

    public static void insertProfileToSQLTable(String tableName, String columnName1, String columnName2, String columnName3, String columnName4, String columnName5, String columnName6 )

    {
        try

        {
            connectToSQLDatabase();

            preparedStatement = sqlConnection.prepareStatement("INSERT INTO " + tableName + " ( " + columnName1 + "," + columnName2 + "," + columnName3 + "," + columnName4 + "," + columnName5 +"," +columnName6+")VALUES(?,?,?,?,?,?)");

            preparedStatement.setString(1,"6");
            preparedStatement.setString(2, "Jovi");
            preparedStatement.setString(3, "Bon");
            preparedStatement.setString(4,"110 Cortelyou Rd");
            preparedStatement.setString(5,"Brooklyn");
            preparedStatement.setString(6,"11219");
            preparedStatement.executeUpdate();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static List<User> readUserProfileFromSQLTable()throws IOException, SQLException, ClassNotFoundException
    {
        List<User> list = new ArrayList<User>();
        User user = null;

        try
        {
            Connection connection = connectToSQLDatabase();
            String query = "SELECT * FROM Customer";

            // create the java statement
            Statement connectionStatement = connection.createStatement();

            // execute the query, and get a java resultset
            ResultSet resultSet = connectionStatement.executeQuery(query);

            // iterate through the java resultset
            while (resultSet.next())
            {
                String serial = resultSet.getString("SerialNumber");
                String lastName = resultSet.getString("LastName");
                String firstName = resultSet.getString("FirstName");
                String address = resultSet.getString("Address");
                String city = resultSet.getString("City");
                String zip = resultSet.getString("ZipCode");

                //System.out.format("%s, %s\n", name, id);
                user = new User(serial,lastName, firstName,address,city,zip);
                list.add(user);

            }
            connectionStatement.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static void main(String[] args)throws IOException, SQLException, ClassNotFoundException
    {

        insertProfileToSQLTable("Customer","SerialNumber", "LastName", "FirstName","Address", "City", "ZipCode");

        List<User> list = readUserProfileFromSQLTable();

        for(User user:list)
        {
            System.out.println(user.getSerialNumber() + " " + user.getLastName()+ " " + user.getFirstName()+ " " + user.getAddress()+ " " + user.getCity()+ " " + user.getZipCode());
        }
    }
}