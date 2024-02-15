import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoTiny;
import alex.AnalizadorLexicoTiny.ECaracterInesperado;
import alex.ClaseLexica;
import alex.UnidadLexica;

public class DomJudge {

  private static void imprime(UnidadLexica unidad) {
		switch(unidad.clase()) {
		   case IDEN: case LIT_ENT: case LIT_REAL: System.out.println(unidad.lexema()); break;
               default: System.out.println(unidad.clase().getImage());
		}
	}

   public static void main(String[] args) throws FileNotFoundException, IOException {
     Reader input  = new InputStreamReader(System.in);
     AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
     UnidadLexica unidad = null;
     do {
       try {  
         unidad = al.sigToken();
         imprime(unidad);
       }
       catch(ECaracterInesperado e) {
          System.out.println("ERROR");
       }
     }
     while (unidad == null || unidad.clase() != ClaseLexica.EOF);
    }        
} 
