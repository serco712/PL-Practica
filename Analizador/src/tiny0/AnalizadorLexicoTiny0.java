package tiny0;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;

public class AnalizadorLexicoTiny0 {

   private Reader input;
   private StringBuffer lex;
   private int sigCar;
   private int filaInicio;
   private int columnaInicio;
   private int filaActual;
   private int columnaActual;
   private static String NL = System.getProperty("line.separator");
   
   private static enum Estado {
    INICIO, REC_MAYOR, REC_MAYOR_IGUAL, REC_MENOR, REC_MENOR_IGUAL, REC_1DISTINTO, REC_DISTINTO, REC_ASIG, REC_IGUAL,
    REC_COMA, REC_PUNTO_COMA, REC_PCI, REC_PAP, REC_DIV, REC_MUL, REC_RESTA, REC_SUMA, REC_BOOL, REC_0, REC_ENT,
    REC_PUNTO_REAL, REC_REAL, REC_0REAL, REC_EXP1REAL, REC_EXP2REAL, REC_EXP3REAL, REC_ID, REC_1COM, REC_COM, REC_EVAL,
    REC_1FIN, REC_FIN, REC_EOF
   }

   private Estado estado;

   public AnalizadorLexicoTiny0(Reader input) throws IOException {
    this.input = input;
    lex = new StringBuffer();
    sigCar = input.read();
    filaActual=1;
    columnaActual=1;
   }
   
   public UnidadLexica sigToken() throws IOException {
     estado = Estado.INICIO;
     filaInicio = filaActual;
     columnaInicio = columnaActual;
     lex.delete(0,lex.length());
     while(true) {
         switch(estado) {
           case INICIO: 
              if(iniIden())  transita(Estado.REC_ID);
              else if (hayDigitoPos()) transita(Estado.REC_ENT);
              else if (hayCero()) transita(Estado.REC_0);
              else if (haySuma()) transita(Estado.REC_SUMA);
              else if (hayResta()) transita(Estado.REC_RESTA);
              else if (hayMul()) transita(Estado.REC_MUL);
              else if (hayDiv()) transita(Estado.REC_DIV);
              else if (hayPAp()) transita(Estado.REC_PAP);
              else if (hayPCierre()) transita(Estado.REC_PCI);
              else if (hayIgual()) transita(Estado.REC_ASIG);
              else if (hayComa()) transita(Estado.REC_COMA);
              else if (hayPuntoComa()) transita(Estado.REC_PUNTO_COMA);
              else if (hayAlmohadilla()) transitaIgnorando(Estado.REC_1COM);
              else if (haySep()) transitaIgnorando(Estado.INICIO);
              else if (hayEOF()) transita(Estado.REC_EOF);
              else if (hayArroba()) transita(Estado.REC_EVAL);
              else if (hayAmpersand()) transita(Estado.REC_1FIN);
              else if (hayMenor()) transita(Estado.REC_MENOR);
              else if (hayMayor()) transita(Estado.REC_MAYOR);
              else if (hayExclamacion()) transita(Estado.REC_1DISTINTO);
              else error();
              break;
           case REC_ID: 
              if (hayLetra() || hayDigito() || haySubrayado()) transita(Estado.REC_ID);
              else return unidadId();               
              break;
           case REC_ENT:
               if (hayDigito()) transita(Estado.REC_ENT);
               else if (hayPunto()) transita(Estado.REC_PUNTO_REAL);
               else if (hayExponencial()) transita(Estado.REC_EXP1REAL);
               else return unidadLitEnt();
               break;
           case REC_0:
               if (hayPunto()) transita(Estado.REC_PUNTO_REAL);
               else return unidadLitEnt();
               break;
           case REC_SUMA:
               if (hayDigitoPos()) transita(Estado.REC_ENT);
               else if(hayCero()) transita(Estado.REC_0);
               else return unidadSuma();
               break;
           case REC_RESTA: 
               if (hayDigitoPos()) transita(Estado.REC_ENT);
               else if(hayCero()) transita(Estado.REC_0);
               else return unidadResta();
               break;
           case REC_MUL: return unidadMul();
           case REC_DIV: return unidadDiv();              
           case REC_PAP: return unidadPAp();
           case REC_PCI: return unidadPCi();
           case REC_ASIG: 
        	   if (hayIgual()) transita(Estado.REC_IGUAL);
        	   else return unidadAsig();
        	   break;
           case REC_IGUAL: return unidadIgual();
        	   
           case REC_COMA: return unidadComa();
           case REC_PUNTO_COMA: return unidadPComa();
           case REC_1COM: 
               if (hayAlmohadilla()) transitaIgnorando(Estado.REC_COM);
               else error();
               break;
           case REC_COM: 
               if (hayNL()) transitaIgnorando(Estado.INICIO);
               else if (hayEOF()) transita(Estado.REC_EOF);
               else transitaIgnorando(Estado.REC_COM);
               break;
           case REC_EVAL: return unidadEval();
           case REC_1DISTINTO:
        	   if (hayIgual()) transita(Estado.REC_DISTINTO);
        	   else error();
        	   break;
           case REC_DISTINTO: return unidadDistinto();
           case REC_MENOR:
        	   if (hayIgual()) transita(Estado.REC_MENOR_IGUAL);
        	   else return unidadMenor();
        	   break;
           case REC_MENOR_IGUAL: return unidadMenorIgual();
           case REC_MAYOR:
        	   if (hayIgual()) transita(Estado.REC_MAYOR_IGUAL);
        	   else return unidadMayor();
        	   break;
           case REC_MAYOR_IGUAL: return unidadMayorIgual();
           case REC_1FIN:
        	   if (hayAmpersand()) transita(Estado.REC_FIN);
        	   else error();
        	   break;
           case REC_FIN: return unidadEof();
           case REC_PUNTO_REAL:
        	   if (hayDigito()) transita(Estado.REC_REAL);
        	   else error();
        	   break;
           case REC_REAL:
        	   if (hayDigitoPos()) transita(Estado.REC_REAL);
        	   else if (hayCero()) transita(Estado.REC_0REAL);
        	   else if (hayExponencial()) transita(Estado.REC_EXP1REAL);
        	   else unidadLitReal();
        	   break;
           case REC_0REAL:
               if (hayDigitoPos()) transita(Estado.REC_REAL);
               else if (hayCero()) transita(Estado.REC_0REAL);
               else error();
               break;
           case REC_EXP1REAL:
        	   if (haySuma() || hayResta()) transita(Estado.REC_EXP2REAL);
        	   else if (hayDigitoPos()) transita(Estado.REC_EXP3REAL);
        	   else error();
        	   break;
           case REC_EXP2REAL:
        	   if (hayDigitoPos()) transita(Estado.REC_EXP3REAL);
        	   else error();
        	   break;
           case REC_EXP3REAL:
        	   if (hayDigito()) transita(Estado.REC_EXP3REAL);
        	   else unidadLitReal();
        	   break;
           case REC_EOF: return unidadEof();
         }
     }    
   }
   private void transita(Estado sig) throws IOException {
     lex.append((char)sigCar);
     sigCar();         
     estado = sig;
   }
   private void transitaIgnorando(Estado sig) throws IOException {
     sigCar();         
     filaInicio = filaActual;
     columnaInicio = columnaActual;
     estado = sig;
   }
   private void sigCar() throws IOException {
     sigCar = input.read();
     if (sigCar == NL.charAt(0)) saltaFinDeLinea();
     if (sigCar == '\n') {
        filaActual++;
        columnaActual=0;
     }
     else {
       columnaActual++;  
     }
   }
   private void saltaFinDeLinea() throws IOException {
      for (int i=1; i < NL.length(); i++) {
          sigCar = input.read();
          if (sigCar != NL.charAt(i)) error();
      }
      sigCar = '\n';
   }
   
   private boolean haySubrayado() { return sigCar == '_'; }
   private boolean hayLetra() {return sigCar >= 'a' && sigCar <= 'z' ||
                                      sigCar >= 'A' && sigCar <= 'z';}
   private boolean iniIden() {return hayLetra() || haySubrayado();}
   private boolean hayDigitoPos() {return sigCar >= '1' && sigCar <= '9';}
   private boolean hayCero() {return sigCar == '0';}
   private boolean hayDigito() {return hayDigitoPos() || hayCero();}
   private boolean haySuma() {return sigCar == '+';}
   private boolean hayResta() {return sigCar == '-';}
   private boolean hayMul() {return sigCar == '*';}
   private boolean hayDiv() {return sigCar == '/';}
   private boolean hayPAp() {return sigCar == '(';}
   private boolean hayPCierre() {return sigCar == ')';}
   private boolean hayIgual() {return sigCar == '=';}
   private boolean hayMenor() {return sigCar == '<';}
   private boolean hayMayor() {return sigCar == '>';}
   private boolean hayExclamacion() {return sigCar == '!';}
   private boolean hayComa() {return sigCar == ',';}
   private boolean hayPunto() {return sigCar == '.';}
   private boolean hayPuntoComa() {return sigCar == ';';}
   private boolean hayAlmohadilla() {return sigCar == '#';}
   private boolean haySep() {return sigCar == ' ' || sigCar == '\t' || sigCar=='\n';}
   private boolean hayNL() {return sigCar == '\r' || sigCar == '\b' || sigCar == '\n';}
   private boolean hayEOF() {return sigCar == -1;}
   private boolean hayArroba() {return sigCar == '@';}
   private boolean hayExponencial() {return sigCar == 'e' || sigCar == 'E';}
   private boolean hayAmpersand() {return sigCar == '&';}
   private UnidadLexica unidadId() {
     switch(lex.toString()) {
         case "true":
         case "false":  
            return unidadLitBool();
         case "and":
        	return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.AND);
         case "or":
        	return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.OR);
         case "not":
        	return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.NOT);
         case "int":
        	return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.ENT);
         case "real":
        	return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.REAL);
         case "bool":
         	return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.BOOL);
         default:    
            return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.IDEN,lex.toString());     
      }
   }  
   private UnidadLexica unidadLitBool() {
     return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.LIT_BOOL,lex.toString());     
   }   
   private UnidadLexica unidadLitEnt() {
     return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.LIT_ENT,lex.toString());     
   }    
   private UnidadLexica unidadLitReal() {
     return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.LIT_REAL,lex.toString());     
   }   
   
   private UnidadLexica unidadSuma() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.SUMA);     
   }    
   private UnidadLexica unidadResta() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.RESTA);     
   }    
   private UnidadLexica unidadMul() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MULT);     
   }    
   private UnidadLexica unidadDiv() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.DIV);     
   }    
   private UnidadLexica unidadMenor() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MENOR);     
   }  
   private UnidadLexica unidadMayor() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MAYOR);     
   }  
    private UnidadLexica unidadMenorIgual() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MEN_IGUAL);     
   }  
    private UnidadLexica unidadMayorIgual() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MAY_IGUAL);     
   }  
   private UnidadLexica unidadPAp() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PAP);     
   }    
   private UnidadLexica unidadPCi() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PCIERRE);     
   }    
   private UnidadLexica unidadPComa() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PCOMA);     
   }    
   private UnidadLexica unidadIgual() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.IGUAL);
   }
   private UnidadLexica unidadDistinto() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.DISTINTO);
   }
    private UnidadLexica unidadAsig() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.ASIG);
   }
   private UnidadLexica unidadComa() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.COMA);     
   }    
   private UnidadLexica unidadEof() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.EOF);     
   }    
    private UnidadLexica unidadEval() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.ARROBA);     
   }   
   private void error() {
     System.err.println("("+filaActual+','+columnaActual+"):Caracter inexperado");  
     System.exit(1);
   }

   public static void main(String arg[]) throws IOException {
     Reader input = new InputStreamReader(new FileInputStream("input.txt"));
     AnalizadorLexicoTiny0 al = new AnalizadorLexicoTiny0(input);
     UnidadLexica unidad;
     do {
       unidad = al.sigToken();
       System.out.println(unidad);
     }
     while (unidad.clase() != ClaseLexica.EOF);
    } 
}