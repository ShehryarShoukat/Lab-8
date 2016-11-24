package lab.pkg08;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import java.nio.file.*;
import java.util.Scanner; 
import java.io.*;

/**
 *
 * @author Shehryar
 */
 
    /**
     * @param args the command line arguments
     */
public class LAB08 {
private static void showmenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  ToyStopService tsService = new ToyStopService();
    public void init(){
        
        tsService.initEmployees();
        tsService.initStores();
        tsService.initToys();
        System.out.println("Init complete");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ToyStopInventoryManagementSystem tsims = new ToyStopInventoryManagementSystem();
        System.out.println("Entering data in Database...");
         tsims.init();
    }
     private void printAll() {
        System.out.println(this.tsService.stores);
    }
}
