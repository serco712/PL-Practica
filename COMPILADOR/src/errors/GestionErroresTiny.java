package errors;

import c_ast_ascendente.UnidadLexica;
import asint.SintaxisAbstractaTiny.Tipado;
import asint.SintaxisAbstractaTiny.Tipo;

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
   public void errorTipoInadecuado(Tipo tipoMal, Class tipoBien) {
	   System.err.println("ERROR el tipo debe ser " + tipoBien.toString() + " pero es " + tipoMal.toString());
   }
   public void errorNoDesignador() {
	   System.err.println("ERROR el operando tiene que ser designador");	   
   }
   public void errorParametrosNoCoincidentes() {
	   System.err.println("ERROR los parametros no coinciden");	   
   }
   public void errorTiposIncompatibles(Tipo tipo1, Tipo tipo2) {
	   System.err.println("ERROR los tipos " + tipo1.toString() + " y " + tipo2.toString() + " no son compatibles");
   }
   public void errorTamanioArray() {
	   System.err.println("ERROR el tamaño del array tiene que ser mayor que 0");
   }

}
