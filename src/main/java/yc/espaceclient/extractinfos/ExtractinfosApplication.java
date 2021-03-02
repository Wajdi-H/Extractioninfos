package yc.espaceclient.extractinfos;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class ExtractinfosApplication {

    public static void main(String[] args) {
        PieceComptable Pc =new PieceComptable();
          //  File imageFile = new File("/home/wajdi/Downloads/555.bmp");
                //File imageFile = new File("/home/wajdi/Desktop/PFE/pngimgeconverted/abc.jpg");
          File imageFile = new File("/home/wajdi/Desktop/PFE/facttest.jpeg");

        ITesseract instance = new Tesseract();  // JNA Interface Mapping
            // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
            instance.setDatapath("tessdata"); // path to tessdata directory
            instance.setLanguage("fra");

                try {
                    String result = instance.doOCR(imageFile);
                    result= result.toUpperCase();
                    result= result.replaceAll(",",".");
                    String [] Tabresult = result.split("\n");
                   //  Pc.setMontanttotal(Float.parseFloat(Tabresult[1].substring(1,4)));

                  for (int i=0 ; i<Tabresult.length;i++){

                      if((Tabresult[i].contains("TOTAL"))&&(Tabresult[i].contains("HT"))) {

                          try {
                              /*Pc.setMontanttotalHT(Float.parseFloat(Tabresult[i].chars()            // or codePoints()
                                      .filter(ch -> Character.isDigit(ch))
                                      .collect(StringBuilder::new, StringBuilder::appendCodePoint,
                                              StringBuilder::append)
                                      .toString()));

                              String Str =Tabresult[i].replaceAll(",",".");

                              Pc.setMontanttotalHT(Float.parseFloat(Str.replaceAll("[a-zA-Z$€]","")));*/

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
                      /*  Pc.setMontantTva(Float.parseFloat(Tabresult[i].chars()
                                .filter(ch -> Character.isDigit(ch))
                                .collect(StringBuilder::new, StringBuilder::appendCodePoint,
                                        StringBuilder::append)
                                .toString()));
                               Tabresult[i].replaceAll(",",".");

                               Pc.setMontantTva(Float.parseFloat(Tabresult[i].replaceAll("[a-zA-Z$€]","")));*/


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
                                   m.matches();
                                   while (m1.find()) {
                                       System.out.println(">> " + m1.group());
                                       Pc.setTauxTVA(m.group());
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


                      if((Tabresult[i].contains("DAT")) ){
                          try{

                              Pattern p = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
                              Matcher m = p.matcher(Tabresult[i]);
                              m.matches();
                              while(m.find()){
                                  try {
                                      Pc.setDatepiececomptable(LocalDate.parse(m.group()));
                                  }
                                  catch(Exception e){
                                      System.out.println("Invalide Date Format");
                                  }

                                  System.out.println(">> "+ m.group());
                              }
                              if (Pc.getDatepiececomptable()==null){
                                  Pc.setDatepiececomptable(LocalDate.now());
                              }
                          } catch (Exception e ){
                              System.out.println("errTOTAL");
                          }
                      }


                      if(((Tabresult[i].contains("FACTURE")))&&((Tabresult[i].contains("No"))||(Tabresult[i].contains("N°"))) ){
                          try{

                         /*     Pattern p = Pattern.compile("^°");
                              Matcher m = p.matcher(Tabresult[i]);
                              m.matches();

                              while(m.find()){
                                  System.out.println(">> "+ m.group());

                              }*/
                                      String[] FactureNumero =Tabresult[i].split(" ");
                                      for(int j=0;j<FactureNumero.length;j++)
                              {
                                       if((FactureNumero[j].contains("°"))||(FactureNumero[j].contains("NO"))){
                                        Pc.setNumfacture(FactureNumero[j+1]);
                                  }
                              }
                          } catch (Exception e ){
                              System.out.println("errTOTAL");
                          }
                      }
                  //  System.out.println(Tabresult[i]);
                }
                    System.out.println(result);


                } catch (TesseractException e) {
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
/*  public static void main(String[] args) {


      // For complete examples and data files, please go to https://github.com/aspose-ocr/Aspose.OCR-for-Java
// The path to the documents directory.

// The image path
      String imagePath = "/home/wajdi/Desktop/PFE/555.png";

//Create api instance
      AsposeOCR api = new AsposeOCR();

// Recognize page by full path to file
      try {
          String result = api.RecognizePage(imagePath);
          System.out.println("Result: " + result);
      } catch (IOException e) {
          e.printStackTrace();
      }
      SpringApplication.run(ExtractinfosApplication.class, args);
  }*/

/*
    public static void main(String[] args) {


        try {
            Platform.setLibPath("/home/wajdi/Downloads/LEADTOOLS21/Bin/Lib/x64");
            OcrEngine ocrEngine = OcrEngineManager.createEngine(OcrEngineType.LEAD);
        ocrEngine.startup(null, null, null, null);
        ocrEngine.getAutoRecognizeManager().run("/home/wajdi/Desktop/PFE/facttest.jpeg", "/home/wajdi/Desktop/PFE/facttest2.pdf",
                DocumentFormat.PDF, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SpringApplication.run(ExtractinfosApplication.class, args);

    }*/
}
