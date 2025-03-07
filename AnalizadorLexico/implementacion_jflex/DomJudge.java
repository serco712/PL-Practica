import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import alextiny.ALexOperations.ECaracterInesperado;
import alextiny.AnalizadorLexicoTiny;
import alextiny.ClaseLexica;
import alextiny.UnidadLexica;

public class DomJudge {

  private static void imprime(UnidadLexica unidad) {
		switch(unidad.clase()) {
		   case IDEN: case LIT_ENT: case LIT_REAL: case LIT_CAD: System.out.println(unidad.lexema()); break;
               default: System.out.println(unidad.clase().getImage());
		}
	}

   public static void main(String[] args) throws FileNotFoundException, IOException {
     Reader input  = new InputStreamReader(new FileInputStream("implementacion_jflex/pruebas_tiny/ej2.in"));
     AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
     UnidadLexica unidad = null;
     do {
       try {  
         unidad = al.yylex();
         imprime(unidad);
       }
       catch(ECaracterInesperado e) {
          System.out.println("ERROR");
       }
     }
     while (unidad == null || unidad.clase() != ClaseLexica.EOF);
    }        
} 
