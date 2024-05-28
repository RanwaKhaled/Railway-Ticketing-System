/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package railwayproject;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Random;
import railwayproject.RailwayProject.Journey;
import railwayproject.RailwayProject.Ticket;
import railwayproject.RailwayProject.Train;
import static railwayproject.UserAccount.QRindex;
import static railwayproject.UserAccount.journey;
import static railwayproject.UserAccount.ticket;
import static railwayproject.UserAccount.train;

/**
 *
 * @author ASUS
 */
public class QRcodes {
        
        
        public static void generateQRcode(String text, int width, int height, String filePath) throws WriterException, IOException{
          QRCodeWriter qc =new QRCodeWriter();
          BitMatrix bm = qc.encode(text, BarcodeFormat.QR_CODE, width, height);
          Path pobj = FileSystems.getDefault().getPath(filePath);
          MatrixToImageWriter.writeToPath(bm, "PNG", pobj);
          QRindex++;
        }
        
        public static  int generateTicketID() {
            Random r = new Random( System.currentTimeMillis() );
            return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
        }
        
        public static String encodeTicketDetails(Ticket ticket, Train train, Journey journey){
            String data="Ticket ID: "+generateTicketID()+"\nTrain number: "
                    +train.number+"\nDate: "+journey.date+"\nFrom: "+ticket.start+"\nTo: "+ticket.destination+
                    "\nDeparture time: "+journey.startTime+"\nduration: "+journey.duration;
            return data;
        }
}
