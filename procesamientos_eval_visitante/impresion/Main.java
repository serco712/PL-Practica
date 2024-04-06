package impresion;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;

public class Main {
   public static void main(String[] args) throws Exception {
	   try {
		   ConstructorASTsTiny asint = new ConstructorASTsTiny(new FileReader("C:/hlocal/PL-Practica/constructores_asts_eval/prueba.in"));
	       asint.disable_tracing();
	       asint.analiza().procesa(new Impresion());
	   }
      catch(ParseException e) {
         System.out.println("ERROR_SINTACTICO"); 
      }
      catch(TokenMgrError e) {
         System.out.println("ERROR_LEXICO"); 
      }
    }
}   
