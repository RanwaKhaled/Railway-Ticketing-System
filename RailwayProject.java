/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package railwayproject;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class RailwayProject {

     public static class Address{
        String city;
        String country;
        public Address(){
            
        }
        public Address(String city,String country){
            this.city=city;
            this.country=country;
        }
        @Override
        public String toString(){
            return city;
        }
    }

  public static class Client{
     private int age;
     private int id;
     private String name;
     Address address;
     private boolean pensionerField;
     double numberOfTravels;
     double totalKmTravelled;
     String username;
     String clientStatus;
     
     
     public Client(){
         
      }
     public Client(int age,int id, String name, Address address, boolean pensionerField){
         this.age=age;
         this.id=id;
         this.name=name;
         this.address=address;
         this.pensionerField=pensionerField;
        
        
     }
     public Client(int age,int id, String name, Address address, boolean pensionerField, double numberOfTravels ,double totalKmTravelled){
         this.age=age;
         this.id=id;
         this.name=name;
         this.address=address;
         this.pensionerField=pensionerField;
         this.numberOfTravels=numberOfTravels;
         this.totalKmTravelled=totalKmTravelled;
        
     }
     
        
     
     public void setName(String name){
            this.name=name;
        }
        public String getName(){
            return name;
        }
        public void setId(int id){
            this.id=id;
        }
        public int getId(){
            return id;
        }
        public void setAge(int age){
            this.age=age;
        }
        public int getAge(){
            return age;
        }
        public void setAddress(Address address){
            this.address=address;
        }
        public Address getAddress(){
            return address;
        }
        public void setPensioner(boolean pensionerField){
            this.pensionerField=pensionerField;
        }
        public boolean getPensioner(){
            return pensionerField;
        }
     
     public void calculateFinalPrice(Ticket ticket){
         if(pensionerField&&age>70){
              ticket.FinalPrice=(ticket.price)-(ticket.price*0.7);
         }
         else if(pensionerField){
             ticket.FinalPrice=(ticket.price)-(ticket.price*0.2); 
         }
         else {
             ticket.FinalPrice=ticket.price;
         }
     }
     public void buyTicket(Ticket ticket){
         
         numberOfTravels++;
         totalKmTravelled+=ticket.nbrKm;
         
         System.out.println("ticket bought successfully");
         
//         if(numberOfTravels>50 || totalKmTravelled>10000){
//             
//            Scanner sc=new Scanner(System.in);
//            System.out.println("Enter client's birthday: ");
//              String b=sc.nextLine();
//            System.out.println("Enter client's preferred station: ");
//              String p=sc.nextLine();
//              sc.close();
//            Client client=new Client(age,id,name,address,pensionerField,numberOfTravels,totalKmTravelled);
//            convertToGolden(client,b,p).toString();
//         
//         }
         
     }   
     @Override
     public String toString(){
         return "Client information:\n"+"Name: "+name+"\nAge: "+age+"\nID: "+id+"\nAddress: \n"+address+"\nNumber of travels: "+numberOfTravels+"\nTotal Km travelled: "+totalKmTravelled+"\n";
     }
     public static GoldenClients convertToGolden(Client client,String birthDate,String prefStation){
         
                GoldenClients newGolden=new GoldenClients(client.getAge(),client.getId(),client.getName(),client.getAddress(),client.getPensioner(),client.numberOfTravels,client.totalKmTravelled);
                newGolden.birthDate=birthDate;
                newGolden.prefStation=prefStation;
                System.out.println(newGolden);
                return newGolden;
                
        }
    }

    
    public static class GoldenClients extends Client{
        String birthDate;
        String prefStation;
        Journey journey;  
       static final String goldenStatus = "golden";
        
        public GoldenClients(){
            super();
        }
        public GoldenClients(int age,int id, String name, Address address, boolean pensionerField){
        super(age,id,name,address,pensionerField);
        }

        public GoldenClients(int age,int id, String name, Address address, boolean pensionerField,double numberOfTravels ,double totalKmTravelled){
        super(age,id,name,address,pensionerField,numberOfTravels,totalKmTravelled);
         
        }
        public GoldenClients(int age,int id, String name, Address address, boolean pensionerField,double numberOfTravels ,double totalKmTravelled,String birthDate,String prefStation, Journey journey){
        super(age,id,name,address,pensionerField,numberOfTravels,totalKmTravelled);
         this.birthDate=birthDate;
         this.prefStation=prefStation;
         this.journey=journey;
        }
        @Override
        public void calculateFinalPrice(Ticket ticket){
            if(getPensioner()&&getAge()>70){
              ticket.FinalPrice=(ticket.price)-(ticket.price*0.7);
         }
             else if(getPensioner()) {
             ticket.FinalPrice=(ticket.price)-(ticket.price*0.2); 
         }  
           if(bdayDiscount(journey.date)){
               ticket.FinalPrice=0;
           }
           else if(stationDiscount(ticket.destination)){
               ticket.FinalPrice=(ticket.price)-(ticket.price*0.5);
           }
           else{
             ticket.FinalPrice=(ticket.price)-(ticket.price*0.2);
         }
        }
        @Override 
         public void buyTicket(Ticket ticket){
             
           numberOfTravels++;
         totalKmTravelled+=ticket.nbrKm;
          
         }
                
         boolean bdayDiscount(String journeydate){
           boolean flag=false;
             if(birthDate.equals(journeydate)){
              flag=true;
            }
             return flag;
            }
         
         boolean stationDiscount(String station){
              boolean flag=false;
             if(prefStation.equals(station)){
              flag=true;
            }
             return flag;
            }
         @Override
     public String toString(){
         return "Golden client information:\n"+"Name: "+getName()+"\nAge: "+getAge()+"\nID: "+getId()+"\nAddress: \n"+getAddress()+"\nNumber of travels: "+numberOfTravels+"\nTotal Km travelled: "+totalKmTravelled+"\nBirth date: "+birthDate
                 +"\nPreferred station: "+prefStation+"\nJourney: \n"+journey+"\n";
     }
    }
     public static class Ticket{
        int id;
        String data;
        double price;
        double FinalPrice;
        int trNbr;
        String trType;
        String trServices;
        String start;
        String destination;
        double nbrKm;
        public Ticket(){
            
        }
        public Ticket(int id,String data, double price, int trNbr,String trType, String trServices,String start,String destination,double nbrKm){
            this.id=id;
            this.data=data;
            this.price=price;
            this.trNbr=trNbr;
            this.trType=trType;
            this.trServices=trServices;
            this.start=start;
            this.destination=destination;
            this.nbrKm=nbrKm;
        }
        @Override
        public String toString(){
           return  "Ticket information:\n"+"ID: "+id+"\nData: "+data+"\nPrice: "+price+"\nFinal price: "+FinalPrice+"\nNumber: "+trNbr+"\nType: "+trType
                   +"\nServices: "+trServices+"\nStarting station: "+start+"\nDestination: "+destination+"\nNumber of Km: "+nbrKm+"\n";
        }
        
    }
    public static class Train{
    int number;
    String type;
    double speed;
    String[]services=new String[5];
    Engine engine;
    
    public Train(){
        
    }
    public Train(int number,String type,double speed,String[]services,Engine engine){
        this.number = number;
        this.type = type;
        this.speed = speed;
        this.services = services;
        this.engine = engine;
      }
    public void performJourney(Journey journey){
        engine.nbrKm+=journey.nbrOfKm;
        if(engine.nbrKm>20000){
            engine.ChangeOil();
        }
        if(engine.nbrKm>100000){
          engine.Maintenance();
        }
    }
    @Override
    public String toString(){
        return "Train informations: \n"+"Number: "+number+"\nType: "+type+"\nSpeed: "+speed+"\nServices: "+Arrays.toString(services)+"\nEngine: \n"+engine+"\n";
    }
    }
  public static class Engine {
    int ID;
    String type;
    double nbrKm;
    public Engine(){
        
    }
    public Engine(int id,String type,double nbrKm){
        this.ID=id;
        this.type=type;
        this.nbrKm=nbrKm;
    }
   public void ChangeOil(){
    System.out.println("oil has been changed");
   }
   public void Maintenance(){
       System.out.println("engine has been maintained");
   }
    @Override
   public String toString(){
       return "Engine informations: \n"+"ID: "+ID+"\nType: "+type+"\nNumber of Km: "+nbrKm+"\n";
   }
  }
  public static class Journey{
    String date;
    String startTime;
    String duration;
    double nbrOfKm;
    Ticket tickets[];
    int journeyNbr;
    
   public Journey(){
       
   }
   public Journey(String date,String startTime,String duration,double nbrKm,Ticket[] tickets){
       this.date=date;
       this.startTime=startTime;
       this.duration=duration;
       this.nbrOfKm=nbrKm;
       this.tickets=tickets;
   }
    @Override
   public String toString(){
       return "Journey informations: \n"+"Date: "+date+"\nStart time: "+startTime+"\nDuration: "+duration+"\nNumber of Km: "+nbrOfKm+"\nTickets: \n"+Arrays.toString(tickets)+"\n";
   }
  }
   
    public static void main(String[] args) {
       Address a1=new Address("Alex","Egypt");
        Ticket t1=new Ticket(101,"ggg",90,3,"c","wifi","alex","cairo",170);
        Client c1=new Client(20,1234,"nour",a1,true,49,30);
        Ticket t2=new Ticket(120,"ffg",60,5,"d","screens","cairo","alex",150);
        // Print updated client data
    System.out.println(c1.toString());
    System.out.println("\t\t\t------------------------");
    // Print ticket data 
    c1.buyTicket(t1);
    System.out.println(t1.toString());
    System.out.println(c1.toString());
    System.out.println("\t\t\t------------------------");
    c1.buyTicket(t2);
    System.out.println(t2.toString());
    System.out.println(c1.toString());
    System.out.println("\t\t\t------------------------");
    System.out.println(c1.toString());
    //System.out.println("\t\t\t------------------------");
    //Client.convertToGolden(c1," 9 feb","jj");
    //System.out.println(Client.convertToGolden(c1," 9 feb","jj"));
    
    
    System.out.println("\t\t\t------------------------");
    Engine e1=new Engine(23,"g",47.5);
    String[] services={"wifi","screens","drinks"};
    Train tr1=new Train(54,"r",32.5,services,e1);
    System.out.println(tr1.toString());
    System.out.println("\t\t\t------------------------");
    
    Ticket[] t3=new Ticket[3];
    t3[0]=new Ticket(104,"ggg",90,3,"c","wifi","alex","cairo",170);
    t3[1]=new Ticket(102,"ggg",95,3,"c","wifi","alex","cairo",170);
    t3[2]=new Ticket(103,"ggg",92,3,"c","wifi","alex","cairo",170);
    
    Journey j1=new Journey("2 april","1:00pm",2.5+" hours",75,t3);
    
    GoldenClients g=new GoldenClients(70,34565,"kim",a1,true,50,1000,"2 april","luxuor",j1);
    g.buyTicket(t3[0]);
    g.buyTicket(t3[1]);
    System.out.println(g.toString());
    System.out.println("\t\t\t------------------------");
    }
    
}
