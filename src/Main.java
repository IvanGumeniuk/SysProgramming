
import java.io.*;
import java.util.Scanner;
import java.util.Stack;


public class Main {

     private static String readFromFile(){
          final String FILENAME = "F:\\Java\\IDEA_Projects\\Lessons\\untitled\\checkingText.txt";
         String sCurrentLine;
         String resultLine = "";

         try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
             while ((sCurrentLine = br.readLine()) != null) {
                 resultLine+=sCurrentLine;
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
         return resultLine;
     }

     private static void writeToFile(String content){
         final String FILENAME = "F:\\Java\\IDEA_Projects\\Lessons\\untitled\\createdText.txt";
         BufferedWriter bw = null;
         FileWriter fw = null;

         try {

             fw = new FileWriter(FILENAME);
             bw = new BufferedWriter(fw);
             bw.write(content);

             System.out.println("Done");

         } catch (IOException e) {

             e.printStackTrace();

         } finally {

             try {

                 if (bw != null)
                     bw.close();

                 if (fw != null)
                     fw.close();

             } catch (IOException ex) {

                 ex.printStackTrace();

             }

         }

     }

     private static boolean isValid(char c){
         String s = ""+c;
         return s.codePointAt(0)>96 && s.codePointAt(0)<123;
     }

     private static void checkingTextFromFile(String [] checkingText, String regularLine){
         if(!regularLine.contains("@")) return;

         //знаходження слів з довжиною = довжині регулярного виразу
         String len = "";
         for(String s : checkingText){
             if(s.length()==regularLine.length())
                 len=len+s+" ";
         }
         if(len.length()==0){
             System.out.println("Matches are not found");
             return;
         }
         checkingText = len.split(" ");
         String t = "";
         int it;
         for (int i=0; i<checkingText.length;i++)
         {
             it=0;
             for (int j=0; j<regularLine.length();j++)
             {
                 if(regularLine.charAt(j)=='@'){
                     if(isValid(checkingText[i].charAt(j))) {
                         it++;
                         continue;
                     }
                     else break;
                 } else
                     if(checkingText[i].charAt(j)==regularLine.charAt(j)) it++;
             }
             if (it == regularLine.length()) t+=checkingText[i]+" ";

         }

         checkingText = t.split(" ");
         Stack<String> outText = new Stack<>();
         for(String s : checkingText)
             outText.push(s);

         t="";
         while (!outText.isEmpty())
             t+=outText.pop()+" ";

         writeToFile(t);

     }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String regularLine = scanner.next();

        String [] textFromFile = readFromFile().split(" ");
        checkingTextFromFile(textFromFile,regularLine);


        scanner.close();
    }


}
