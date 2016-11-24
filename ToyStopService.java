
package lab.pkg08;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.sql.*;
import java.sql.Connection;
import com.mysql.jdbc.Driver;
/**
 *
 * @author Fahad Satti
 */
public class ToyStopService {
     ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<Store> stores = new ArrayList<>();
    
static String DB_URL = "jdbc:mysql://localhost/lab8"; //change the name 7password of database
    static String USER = "root";
    static String PASS = "";
   static Connection conn = null;
   static Statement stmt = null; 
   Random randomGenerator = new Random();
   


    public void initEmployees(){
        //Create a List of first 200 Employees
        
     try{
    conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Class.forName("com.mysql.jdbc.Driver");
    stmt = conn.createStatement();
          
          
          
           for(int i=0; i<200; i++){
            Employee myEmployee = new Employee();
            myEmployee.setUID(i);
            myEmployee.setRandomName();
            myEmployee.setEmail(myEmployee.getName()+"@toystop.org");
            myEmployee.setStoreID(randomGenerator.nextInt(100));
           String sql = "INSERT INTO employee " +
                   "VALUES ('"+myEmployee.getName()+"',"+myEmployee.getUID()+",'"+myEmployee.getEmail()+"',"+myEmployee.getStoreID()+")";
      stmt.executeUpdate(sql);
            employees.add(myEmployee);
             }
           // System.out.printf("Serialized asubc"+myEmployee.getUID()+"\n");
        
           try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
          
           
          
          
          
          
          }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }

       
         
       
        
    }
    
    public void initStores(){
        //Create a List of Stores in a region
         try{
    conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Class.forName("com.mysql.jdbc.Driver");
    stmt = conn.createStatement();
        for(int i=0; i<100; i++){
            Store myStore = new Store();
            myStore.setUID(i);
            myStore.addRandomEmployees(employees);
            stores.add(myStore);
            myStore.setAddress(Util.getSaltAlphaNumString());
            myStore.setContactNo("+92"+Util.getSaltNum(9));
            Email storeEmail = new Email();
            storeEmail.setEmailAddress(myStore.getUID()+"@toystop.org");
            myStore.setEmail(storeEmail);
             String sql = "INSERT INTO store " +
                   "VALUES ("+myStore.getUID()+",'"+myStore.getAddress()+"','"+myStore.getContactNo()+"','"+myStore.getEmail()+"')";
      stmt.executeUpdate(sql);
          
            
        }
          try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
          
    }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }

       
         
       
    }
    
    public void initToys(){
        //Add Toys in random stores
        try{
    conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Class.forName("com.mysql.jdbc.Driver");
    stmt = conn.createStatement();
          
        for(int i=0; i<200000; i++){
            Toy newToy = new Toy();
            newToy.setUID(i);
            newToy.setMinAge(Util.getSaltNum(1));
            newToy.setMaxAge(Util.getSaltNum(18));
            newToy.setPrice(Util.getSaltNum(1000));
            newToy.setName(Util.getSaltAlphaString());
            newToy.setAddedOn(LocalDateTime.now());
            
            Random randStore = new Random();
            int index = randStore.nextInt(stores.size());
            Store selectedStore = (Store)stores.get(index);
             String sql = "INSERT INTO toy " +
                   "VALUES ("+newToy.getUID()+",'"+newToy.getName()+"',"+newToy.getMaxAge()+","+newToy.getMinAge()+",'"+newToy.getAddedOn()+"')";
      stmt.executeUpdate(sql);
            selectedStore.addToy(newToy);
        }
          try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
          
    }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }

       
         
       
    }
    //Only creates a new employee and returns the UID
    public int addEmployee(){
            Employee myEmployee = new Employee();
            
            myEmployee.setRandomName();
            myEmployee.setEmail(myEmployee.getName()+"@toystop.org");
            myEmployee.setUID(employees.size()+1);
            employees.add(myEmployee);
            return myEmployee.getUID();
    }
    
    //Creates a new store
    public int addStore(){
            Store myStore = new Store();
            myStore.setUID(Util.getSaltNum(-1));
            //This will assign any new employees or the ones remaining after previous store insertions.
            myStore.addRandomEmployees(employees);
            
            myStore.setAddress(Util.getSaltAlphaNumString());
            myStore.setContactNo("+92"+Util.getSaltNum(9));
            Email storeEmail = new Email();
            storeEmail.setEmailAddress(myStore.getUID()+"@toystop.org");
            myStore.setEmail(storeEmail);
            stores.add(myStore);
            return myStore.getUID();
    }
}
