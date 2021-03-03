package yc.espaceclient.extractinfos;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class ExtractinfosApplication {

    public static void main(String[] args) {
        PieceComptable Pc =new PieceComptable();
          //  File imageFile = new File("/home/wajdi/Downloads/555.bmp");
            //    File imageFile = new File("/home/wajdi/Desktop/PFE/pngimgeconverted/abc.jpg");
         File imageFile = new File("/home/wajdi/Desktop/PFE/123456.jpg");


        ITesseract instance = new Tesseract();  // JNA Interface Mapping
            // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
            instance.setDatapath("tessdata"); // path to tessdata directory
            instance.setLanguage("fra");

                try {
                    String result = instance.doOCR(imageFile);
                    result= result.toUpperCase();
                    result= result.replaceAll(",",".");
                    String [] Tabresult = result.split("\n");
                  for (int i=0 ; i<Tabresult.length;i++){

                      if((Tabresult[i].contains("TOTAL"))&&(Tabresult[i].contains("HT"))) {

                          try {

                              Pattern p = Pattern.compile("\\d*\\.\\d+");
                              Matcher m = p.matcher(Tabresult[i]);
                              m.matches();

                              while(m.find()){
                                  System.out.println(">> "+ m.group());
                                  Pc.setMontanttotalHT(Float.parseFloat(m.group()));


                              }

                          }
                          catch (Exception e )
                          {
                              System.out.println("errHT");
                          }
                      }
                       if (Tabresult[i].contains("TVA")){
                           try {

                                   Pattern p = Pattern.compile("\\d*\\.\\d+");

                                   Matcher m = p.matcher(Tabresult[i]);
                                   m.matches();
                                   while (m.find()) {
                                       System.out.println(">> " + m.group());
                                       Pc.setMontantTva(Float.parseFloat(m.group()));
                                   }

                               if(Tabresult[i].contains("%")){
                                   Pattern p1 = Pattern.compile("\\d*%");

                                   Matcher m1 = p1.matcher(Tabresult[i]);
                                   m1.matches();
                                   while (m1.find()) {
                                       System.out.println(">> " + m1.group());
                                       Pc.setTauxTVA(m1.group());
                                   }

                               }


                           }
                           catch (Exception e){
                               System.out.println("errTVA");
                           }
                       }

                      if((Tabresult[i].contains("TOTAL"))||(Tabresult[i].contains("TTC")) ){
                          try{

                              Pattern p = Pattern.compile("\\d*\\.\\d+");
                              Matcher m = p.matcher(Tabresult[i]);
                              m.matches();

                              while(m.find()){
                                  System.out.println(">> "+ m.group());
                                  Pc.setMontanttotal(Float.parseFloat(m.group()));

                              }
                          } catch (Exception e ){
                              System.out.println("errTOTAL");
                          }
                      }


                     // if((Tabresult[i].contains("DAT")) ){
                          if((Tabresult[i].contains("/")) ){
                          try{
                              Pattern p = Pattern.compile("^(?:(?:31(/|-|\\.)(?:0?[13578]|1[02]))\\1x|(?:(?:29|30)(/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
                             // Pattern p = Pattern.compile("\\d*/*\\d*/\\d*");
                              Matcher m = p.matcher(Tabresult[i]);
                              m.matches();
                              while(m.find()) {
                                  try {
                                      System.out.println(">> "+ m.group());

                                      Pc.setDatepiececomptable(LocalDate.parse(m.group()));

                                  } catch (Exception e) {
                                      System.out.println("Invalide Date Format");
                                  }
                              }


                          }
                              catch (Exception e ){
                              System.out.println("errTOTAL");
                          }
                      }

                      if (Pc.getDatepiececomptable()==null) { // if the date is in format 03 mars 2020
                          DateFormatSymbols dfs = new DateFormatSymbols(Locale.FRENCH);
                          String[] FrenshMonths = dfs.getMonths();
                          for (int l=0 ;l<FrenshMonths.length-1;l++) {
                              if ((Tabresult[i].contains(FrenshMonths[l].toUpperCase()))) {
                                  String Matecheddate="";

                                  Pattern p1 = Pattern.compile("[A-Z]*\\d*[A-Z]*\\d+");
                                  Matcher m1 = p1.matcher(Tabresult[i]);
                                  m1.matches();
                                  while (m1.find()){
                                      try {
                                            Matecheddate=Matecheddate+m1.group();
                                           } catch (Exception e) {
                                          System.out.println("Invalide Date Format");
                                      }
                                  }
                                  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
                                  Pc.setDatepiececomptable(LocalDate.parse(Matecheddate.substring(0,2)+"/"+getmonthnumber(FrenshMonths[l].toUpperCase())+"/"+Matecheddate.substring(2,6), formatter));
                              }

                          }
                      }
                      if(((Tabresult[i].contains("FACTURE"))||((Tabresult[i].contains("TICKET")))&&((Tabresult[i].contains("No"))||(Tabresult[i].contains("N°")) ||(Tabresult[i].contains(":"))))){
                          try{

                                      String[] FactureNumero =Tabresult[i].split(" ");
                                      for(int j=0;j<FactureNumero.length;j++)
                              {
                                       if((FactureNumero[j].contains("°"))||(FactureNumero[j].contains("NO"))||(FactureNumero[j].contains(":"))){
                                           if(j==FactureNumero.length-1){
                                               Pc.setNumfacture(FactureNumero[j].substring(FactureNumero[j].indexOf("°")+1,FactureNumero[j].length()));
                                           }
                                           else {
                                               Pc.setNumfacture(FactureNumero[j + 1]);
                                           }
                                           }
                              }
                          } catch (Exception e ){
                              System.out.println("errTOTAL");
                          }
                      }
                }
                    System.out.println(result);


                } catch (TesseractException | ParseException e) {
                System.err.println(e.getMessage());
            }


        System.out.println("Total:   "+Pc.getMontanttotal());
        System.out.println("TOTALHT:   "+Pc.getMontanttotalHT());
        System.out.println("TVA:   "+Pc.getMontantTva());
        System.out.println("TauxTVa:  "+Pc.getTauxTVA());
        System.out.println("NumeroFacture:   "+Pc.getNumfacture());
        System.out.println("DateFacture:   "+Pc.getDatepiececomptable());


        SpringApplication.run(ExtractinfosApplication.class, args);
    }
    public static int getmonthnumber(String month) throws ParseException {
        Date date = new SimpleDateFormat("MMMM", Locale.FRENCH).parse(month);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        System.out.println(cal.get(Calendar.MONTH));
        return cal.get(Calendar.MONTH)+1;
    }
}
