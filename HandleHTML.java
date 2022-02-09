import java.util.List;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;

public class HandleHTML {

  public static void main(String[] args) {

    ParkingBackEnd parking = new ParkingBackEnd();
    parking.inputData();
    String lotAddress = null;
    try {
      lotAddress = java.net.URLDecoder.decode(args[0].replaceAll("^search", ""), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    List<ParkingLot> potLots = parking.sort(lotAddress);


    System.out.println(parking.toString());


    
    
    String searched = args[0];
    System.out.println("<!DOCTYPE html>");
    System.out.println("<html><head><title>" + "UW Madison Parking" + "</title></head>");
    System.out.println("<body><p>" + "Testing..." + "</p></body></html>");
     
    
  }

//    // System.out.println(hash.toString());
//    String html = "";
//
//    try {
//      BufferedReader in = new BufferedReader(new FileReader("HTMLTemplate.html"));
//      String str;
//      while ((str = in.readLine()) != null) {
//        if (str.equals("</body>")) {
//          // html += "<p>" + hash.toString() + "</p>";
//
//
//          String hashString = hash.toString(); // splits the string by every
//          String[] lines = hashString.split("\\r?\\n");
//          html += "<table>\n<th>";
//          for (String line : lines) {
//
//          }
//        }
//        html += str + "\n";
//      }
//      in.close();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    
//    inputPage2();
//    
//    // File f = new File("HTMLTemplate.html");
//    // try {
//    // BufferedWriter bw = new BufferedWriter(new FileWriter(f));
//    // bw.write(html);
//    // bw.close();
//    //
//    // } catch (IOException e) {
//    // e.printStackTrace();
//    // }
//    //
//   
//  }
//  
//  public static void inputPage2() {
//    
//  }
}
