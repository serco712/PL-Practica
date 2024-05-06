package errors;

import c_ast_ascendente.UnidadLexica;
import asint.SintaxisAbstractaTiny.Tipado;

public class GestionErroresTiny {
   public class ErrorLexico extends RuntimeException {
       public ErrorLexico(String msg) {
           super(msg);
       }
   } 
   public class ErrorSintactico extends RuntimeException {
       public ErrorSintactico(String msg) {
           super(msg);
       }
   } 
   public void errorLexico(int fila, int columna, String lexema) {
     throw new ErrorLexico("ERROR fila "+fila+": Caracter inexperado: "+lexema); 
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
     throw new ErrorSintactico("ERROR fila "+unidadLexica.fila()+", columna "+unidadLexica.columna()+" : Elemento inexperado "+unidadLexica.lexema());
   }
   public void errorTipoInadecuado(Tipado tipoMal, Tipado tipoBien) {
	   System.err.println("ERROR el tipo debe ser " + tipoBien.name() + " pero es " + tipoMal.name());
   }
   public void errorNoDesignador() {
	   System.err.println("ERROR el operando tiene que ser designador");	   
   }
   public void errorParametrosNoCoincidentes() {
	   System.err.println("ERROR los parametros no coinciden");	   
   }
   public void errorTiposIncompatibles(Tipado tipo1, Tipado tipo2) {
	   System.err.println("ERROR los tipos " + tipo1.name() + " y " + tipo2.name() + " no son compatibles");
   }
   public void errorTamanioArray() {
	   System.err.println("ERROR el tamaño del array tiene que ser mayor que 0");
   }

}
