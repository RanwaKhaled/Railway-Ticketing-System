/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package railwayproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static railwayproject.LogIn.window;
import railwayproject.RailwayProject.Address;
import railwayproject.RailwayProject.Client;
import railwayproject.RailwayProject.GoldenClients;
import railwayproject.RailwayProject.Journey;
import railwayproject.RailwayProject.Ticket;
import railwayproject.RailwayProject.Train;
import static railwayproject.UserAccount.QRlabel;
import static railwayproject.UserAccount.ticketPanel;


/**
 *
 * @author ASUS
 */
public class excelDB {
    public static Client c1=new Client();
    public static GoldenClients g1= new GoldenClients();
    public static Address a1=new Address();
    
    public static void logIn(/*String sheet,*/ String username,String password) throws FileNotFoundException, IOException{
        File railwayDB=new File("D://Uni stuff//1st year//2nd semester//Programming//final project//railway.xlsx");
        FileInputStream fis=new FileInputStream(railwayDB);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        Sheet clients = wb.getSheet("Clients");
        Row credential;
        Cell usernames;
        Cell passwords;
        boolean flag=false;
        for(int i=1;i<=clients.getLastRowNum();i++){
           credential = clients.getRow(i);
           usernames=credential.getCell(0);
           passwords=credential.getCell(1);
          // System.out.println(usernames.getStringCellValue()+" - "+passwords.getNumericCellValue());
           if(username.equals(usernames.getStringCellValue())&&Double.valueOf(password).equals(passwords.getNumericCellValue())){
             System.out.println("Log in successful");
             //initialize a client object with the retrieved data
// 
             c1.setName( credential.getCell(2).getStringCellValue());
             c1.setId((int) credential.getCell(3).getNumericCellValue());
             c1.setAge((int) credential.getCell(4).getNumericCellValue());
             a1.city=credential.getCell(5).getStringCellValue();
             c1.address=a1;
             c1.clientStatus=credential.getCell(6).getStringCellValue();
             c1.totalKmTravelled=credential.getCell(7).getNumericCellValue();
             System.out.print(credential.getCell(10).getStringCellValue()+"\nage: "+c1.getAge());
             if(credential.getCell(10).getStringCellValue().equals("true")){
                 c1.setPensioner(true);
             }
             else 
                 c1.setPensioner(false);
             if(credential.getCell(6).getStringCellValue().equals("golden")){
                 g1=new GoldenClients(c1.getAge(),c1.getId(),c1.getName(),c1.getAddress(),c1.getPensioner(),c1.numberOfTravels,c1.totalKmTravelled);
                 g1.birthDate= credential.getCell(8).getStringCellValue();
                 g1.prefStation=credential.getCell(9).getStringCellValue();
             }
             
             //filling the table with values upon successful log in
             fillTable();
            
             //ticket will be invisible until the journey is selected 
             
             java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new UserAccount().setVisible(true);
                ticketPanel.setVisible(false);
                window.setVisible(false);
                QRlabel.setVisible(false);
            }
        });

             flag=true;
             break;
           }
//           else{
//               System.out.println("Log in unsuccessful");
//               System.out.println("you entered: "+username+" - "+password
//                       +"\nActual credentials: "+usernames.getStringCellValue()+" "+passwords.getNumericCellValue());
//           }
           
        }
        if(flag==false){
               System.out.println("Log in unsuccessful");
           }
    }
    
    
    
    //journeys:
    public static  Journey j1=new Journey();
    public static  Journey j2=new Journey();
    public static  Journey j3=new Journey();
    public static  Journey j4=new Journey();
    public static  Journey j5=new Journey();
    //Trains:
    public static Train t1=new Train();
    public static Train t2=new Train();
    public static Train t3=new Train();
    public static Train t4=new Train();
    public static Train t5=new Train();
    //tickets: (=billets)
    public static Ticket b1=new Ticket();
    public static Ticket b2=new Ticket();
    public static Ticket b3=new Ticket();
    public static Ticket b4=new Ticket();
    public static Ticket b5=new Ticket();
    
    //for each journey initialize the variables for the journeys' table
    public static void fillTable() throws IOException{
      //journey 1
      j1.journeyNbr=1;
      j1.date=getCredentialString("Journeys",1,1);
      j1.startTime=getCredentialString("Journeys",1,2);
      j1.nbrOfKm=getCredentialNumerical("Journeys",1,4);
      for(int i=1;i<6;i++){
          if(getCredentialNumerical("Trains",i,3)==j1.journeyNbr){
              //when we need to print at the table we write t1.services[0] 3ala toul
              t1.services[0]=getCredentialString("Trains",i,2);
              t1.number= (int) getCredentialNumerical("Trains",i,0);
          }
          if(getCredentialNumerical("Tickets",i,1)==t1.number){
              b1.start=getCredentialString("Tickets",i,2);
              b1.destination=getCredentialString("Tickets",i,3);
              b1.price=getCredentialNumerical("Tickets",i,0);
              b1.nbrKm=j1.nbrOfKm;
          }
      }
      //journey 2
      j2.journeyNbr=2;
      j2.date=getCredentialString("Journeys",2,1);
      j2.startTime=getCredentialString("Journeys",2,2);
      j2.nbrOfKm=getCredentialNumerical("Journeys",2,4);
      for(int i=1;i<6;i++){
          if(getCredentialNumerical("Trains",i,3)==j2.journeyNbr){
              //when we need to print at the table we write t1.services[0] 3ala toul
              t2.services[0]=getCredentialString("Trains",i,2);
              t2.number= (int) getCredentialNumerical("Trains",i,0);
          }
      }
       for(int i=1;i<6;i++){
          if(getCredentialNumerical("Tickets",i,1)==t2.number){
              b2.start=getCredentialString("Tickets",i,2);
              b2.destination=getCredentialString("Tickets",i,3);
              b2.price=getCredentialNumerical("Tickets",i,0);
              b2.nbrKm=j2.nbrOfKm;
          }
      }
      //journey 3
      j3.journeyNbr=3;
      j3.date=getCredentialString("Journeys",3,1);
      j3.startTime=getCredentialString("Journeys",3,2);
      j3.nbrOfKm=getCredentialNumerical("Journeys",3,4);
      for(int i=1;i<6;i++){
          if(getCredentialNumerical("Trains",i,3)==j3.journeyNbr){
              //when we need to print at the table we write t1.services[0] 3ala toul
              t3.services[0]=getCredentialString("Trains",i,2);
              t3.number= (int) getCredentialNumerical("Trains",i,0);
          }
          if(getCredentialNumerical("Tickets",i,1)==t3.number){
              b3.start=getCredentialString("Tickets",i,2);
              b3.destination=getCredentialString("Tickets",i,3);
              b3.price=getCredentialNumerical("Tickets",i,0);
              b3.nbrKm=j3.nbrOfKm;
          }
      }
      //journey 4
      j4.journeyNbr=4;
      j4.date=getCredentialString("Journeys",4,1);
      j4.startTime=getCredentialString("Journeys",4,2);
      j4.nbrOfKm=getCredentialNumerical("Journeys",4,4);
      for(int i=1;i<6;i++){
          if(getCredentialNumerical("Trains",i,3)==j4.journeyNbr){
              //when we need to print at the table we write t1.services[0] 3ala toul
              t4.services[0]=getCredentialString("Trains",i,2);
              t4.number= (int) getCredentialNumerical("Trains",i,0);
          }
          if(getCredentialNumerical("Tickets",i,1)==t4.number){
              b4.start=getCredentialString("Tickets",i,2);
              b4.destination=getCredentialString("Tickets",i,3);
              b4.price=getCredentialNumerical("Tickets",i,0);
              b4.nbrKm=j4.nbrOfKm;
          }
      }
      //journey 5
      j5.journeyNbr=5;
      j5.date=getCredentialString("Journeys",5,1);
      j5.startTime=getCredentialString("Journeys",5,2);
      j5.nbrOfKm=getCredentialNumerical("Journeys",5,4);
      for(int i=1;i<6;i++){
          if(getCredentialNumerical("Trains",i,3)==j5.journeyNbr){
              //when we need to print at the table we write t1.services[0] 3ala toul
              t5.services[0]=getCredentialString("Trains",i,2);
              t5.number= (int) getCredentialNumerical("Trains",i,0);
          }
      }
      for(int i=1;i<6;i++){
          if(getCredentialNumerical("Tickets",i,1)==t5.number){
              b5.start=getCredentialString("Tickets",i,2);
              b5.destination=getCredentialString("Tickets",i,3);
              b5.price=getCredentialNumerical("Tickets",i,0);
              b5.nbrKm=j5.nbrOfKm;
          }
      }
      
    }
    
    
    
    //"D:\Uni stuff\1st year\2nd semester\Programming\final project\railway.xlsx"
    //get string data from excel
    public static String getCredentialString(String sheet, int row,int col)throws FileNotFoundException, IOException{
        String data;
        File railwayDB=new File("D://Uni stuff//1st year//2nd semester//Programming//final project//railway.xlsx");
        FileInputStream fis=new FileInputStream(railwayDB);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        Sheet students = wb.getSheet(sheet);
        Row username = students.getRow(row);
        Cell cell= username.getCell(col);
        data=cell.getStringCellValue();
        return data;
    }
    //get numerical data from excel
    public static double getCredentialNumerical(String sheet, int row,int col)throws FileNotFoundException, IOException{
        double data;
        File railwayDB=new File("D://Uni stuff//1st year//2nd semester//Programming//final project//railway.xlsx");
        FileInputStream fis=new FileInputStream(railwayDB);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        Sheet students = wb.getSheet(sheet);
        Row username = students.getRow(row);
        Cell cell= username.getCell(col);
        data=cell.getNumericCellValue();
        return data;
    }
    
    public static void writeDataString(String sheet,int row,int col, String data) throws FileNotFoundException, IOException{
        File railwayDB=new File("D://Uni stuff//1st year//2nd semester//Programming//final project//railway.xlsx");
        FileInputStream fis=new FileInputStream(railwayDB);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        Sheet students = wb.getSheet(sheet);
        Row prog = students.getRow(row);
        Cell c=prog.createCell(col);
        c.setCellValue(data);
        FileOutputStream fos= new FileOutputStream(railwayDB);
        wb.write(fos);
    }
    public static void writeDataNumeric(String sheet,int row,int col, double data) throws FileNotFoundException, IOException{
        File railwayDB=new File("D://Uni stuff//1st year//2nd semester//Programming//final project//railway.xlsx");
        FileInputStream fis=new FileInputStream(railwayDB);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        Sheet students = wb.getSheet(sheet);
        Row prog = students.getRow(row);
        Cell c=prog.createCell(col);
        c.setCellValue(data);
        FileOutputStream fos= new FileOutputStream(railwayDB);
        wb.write(fos);
    }
    
    public static int getLastRowNumber(String sheetName) throws FileNotFoundException, IOException{
       File railwayDB=new File("D://Uni stuff//1st year//2nd semester//Programming//final project//railway.xlsx");
        FileInputStream fis=new FileInputStream(railwayDB);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        Sheet sheet= wb.getSheet(sheetName);
        return sheet.getLastRowNum();
    }
    
    public static void createRow(String sheetName, int row) throws FileNotFoundException, IOException{
        File railwayDB=new File("D://Uni stuff//1st year//2nd semester//Programming//final project//railway.xlsx");
        FileInputStream fis=new FileInputStream(railwayDB);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        Sheet students = wb.getSheet(sheetName);
        Row prog = students.createRow(row);
        Cell c=prog.createCell(0);
        c.setCellValue(0);
        FileOutputStream fos= new FileOutputStream(railwayDB);
        wb.write(fos);
    }

}
