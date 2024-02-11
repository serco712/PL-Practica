package abc;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import tiny0.AnalizadorLexicoTiny0;
import tiny0.AnalizadorLexicoTiny0.ECaracterInesperado;
import tiny0.ClaseLexica;
import tiny0.UnidadLexica;

public class DomJudge {

   public static void main(String[] args) throws FileNotFoundException, IOException {
     Reader input  = new InputStreamReader(System.in);
     AnalizadorLexicoTiny0 al = new AnalizadorLexicoTiny0(input);
     UnidadLexica unidad = null;
     do {
       try {  
         unidad = al.sigToken();
         System.out.println(unidad.clase().getImage());
       }
       catch(ECaracterInesperado e) {
              System.out.println("ERROR");
       }
     }
     while (unidad.clase() != ClaseLexica.EOF);
    }        
} 
