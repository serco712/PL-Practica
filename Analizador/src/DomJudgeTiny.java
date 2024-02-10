import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import tiny.ALexOperations.ECaracterInesperado;
import tiny.AnalizadorLexicoTiny;
import tiny.ClaseLexica;
import tiny.UnidadLexica;

public class DomJudge {
	private static void imprime(UnidadLexica unidad) {
		switch(unidad.clase()) {
		   case IDEN: case ENT: case REAL: System.out.println(unidad.lexema()); break;
                   default: System.out.println(unidad.clase().getImage());
		}
	}	

   public static void main(String[] args) throws FileNotFoundException, IOException {
     Reader input  = new InputStreamReader(System.in);
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
     while (unidad.clase() != ClaseLexica.EOF);
    }        
} 
