package asint;

import alex.UnidadLexica;
import java.io.IOException;
import java.io.Reader;

public class AnalizadorSintacticoTiny0DJ extends AnalizadorSintacticoTiny0 {
       public AnalizadorSintacticoTiny0DJ(Reader input) throws IOException {
          super(input); 
       }
     protected final void traza_emparejamiento(UnidadLexica unidad) {
         switch(unidad.clase()) {
		   case IDEN: case LIT_ENT: case LIT_REAL: System.out.println(unidad.lexema()); break;
                   default: System.out.println(unidad.clase().getImage());
	 }
     } 
}
