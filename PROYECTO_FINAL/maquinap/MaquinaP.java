package maquinap;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.io.InputStreamReader;





public class MaquinaP {
   public static class EAccesoIlegitimo extends RuntimeException {} 
   public static class EAccesoAMemoriaNoInicializada extends RuntimeException {
      public EAccesoAMemoriaNoInicializada(int pc,int dir) {
         super("pinst:"+pc+" dir:"+dir); 
      } 
   } 
   public static class EAccesoFueraDeRango extends RuntimeException {} 
   
   private GestorMemoriaDinamica gestorMemoriaDinamica;
   private GestorPilaActivaciones gestorPilaActivaciones;
    
   private class Valor {
      public boolean valorBool() {throw new EAccesoIlegitimo();} 
      public int valorInt() {throw new EAccesoIlegitimo();}  
      public float valorReal() {throw new EAccesoIlegitimo();}  
      public String valorString() {throw new EAccesoIlegitimo();} 
      public boolean esBool() {return false;}
      public boolean esInt() {return false;}
      public boolean esReal() {return false;}
      public boolean esString() {return false;}
      public boolean menorQue(Valor v) {return false;}
      public boolean igualQue(Valor v) {return false;}
      public boolean mayorQue(Valor v) {return false;}
   } 

   private class ValorBool extends Valor {
      private boolean valor;
      public ValorBool(boolean valor) {
         this.valor = valor; 
      }
      public boolean valorBool() {return valor;}
      public boolean esBool() {return true;}
      public boolean menorQue(Valor v) {
         return !valor && v.valorBool();
      }
      public boolean igualQue(Valor v) {
         return valor == v.valorBool();
      }
      public boolean mayorQue(Valor v) {
         return valor && !v.valorBool();
      }
      public String toString() {
        return String.valueOf(valor);
      }
   }
   private class ValorInt extends Valor {
      private int valor;
      public ValorInt(int valor) {
         this.valor = valor; 
      }
      public int valorInt() {return valor;}
      public boolean esInt() {return true;}
      public boolean menorQue(Valor v) {
         return valor < v.valorInt();
      }
      public boolean igualQue(Valor v) {
         return valor == v.valorInt();
      }
      public boolean mayorQue(Valor v) {
         return valor > v.valorInt();
      }
      public String toString() {
        return String.valueOf(valor);
      }
   }
   private class ValorReal extends Valor {
      private float valor;
      public ValorReal(float valor) {
         this.valor = valor; 
      }
      public float valorReal() {return this.valor;}
      public boolean esReal() {return true;}
      public boolean menorQue(Valor v) {
         return valor < v.valorReal();
      }
      public boolean igualQue(Valor v) {
         return valor == v.valorReal();
      }
      public boolean mayorQue(Valor v) {
         return valor > v.valorReal();
      }
      public String toString() {
        return String.valueOf(valor);
      }
   }
   private class ValorString extends Valor {
      private String valor;
      public ValorString(String valor) {
         this.valor = valor; 
      }
      public String valorString() {return valor;}
      public boolean esString() {return true;}
      public boolean menorQue(Valor v) {
         boolean menorque;
         int n;
         if (valor.length() < v.valorString().length()) {
            menorque = true;
            n = v.valorString().length();
         }
         else {
            menorque = false;
            n = valor.length();
         }

         for (int i = 0; i < n; i++) {
            if (valor.charAt(i) > v.valorString().charAt(i))
               return false;
         }

         return menorque;
      }
      public boolean igualQue(Valor v) {
         return valor.equals(v.valorString());
      }
      public boolean mayorQue(Valor v) {
         return !menorQue(v) && !igualQue(v);
      }
      public String toString() {
        return String.valueOf(valor);
      }
   }

   private List<Instruccion> codigoP;
   private Stack<Valor> pilaEvaluacion;
   private Valor[] datos; 
   private int pc;

   public interface Instruccion {
      void ejecuta();  
   }
   private ISuma ISUMA;
   private class ISuma implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         if (opnd1.esInt())
            pilaEvaluacion.push(new ValorInt(opnd1.valorInt()+opnd2.valorInt()));
         else
            pilaEvaluacion.push(new ValorReal(opnd1.valorReal()+opnd2.valorReal())); 
         pc++;
      } 
      public String toString() {return "suma";};
   }
   private IResta IRESTA;
   private class IResta implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         if (opnd1.esInt())
            pilaEvaluacion.push(new ValorInt(opnd1.valorInt()-opnd2.valorInt()));
         else
            pilaEvaluacion.push(new ValorReal(opnd1.valorReal()-opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "resta";};
   }
   
   private IMul IMUL;
   private class IMul implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         if (opnd1.esInt())
            pilaEvaluacion.push(new ValorInt(opnd1.valorInt()*opnd2.valorInt()));
         else
            pilaEvaluacion.push(new ValorReal(opnd1.valorReal()*opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "mul";};
   }
   
   private IDiv IDIV;
   private class IDiv implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         if (opnd1.esInt())
            pilaEvaluacion.push(new ValorInt(opnd1.valorInt()/opnd2.valorInt()));
         else 
            pilaEvaluacion.push(new ValorReal(opnd1.valorReal()/opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "div";};
   }
   private IMod IMOD;
   private class IMod implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorInt(opnd1.valorInt()%opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "mod";};
   }
   private IAnd IAND;
   private class IAnd implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorBool()&&opnd2.valorBool()));
         pc++;
      } 
      public String toString() {return "and";};
   }
   private IOr IOR;
   private class IOr implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorBool()||opnd2.valorBool()));
         pc++;
      } 
      public String toString() {return "or";};
   }
   private IMayor IMAYOR;
   private class IMayor implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.mayorQue(opnd2)));
         pc++;
      } 
      public String toString() {return ">";};
   }
   private IMayorIgual IMAYORIGUAL;
   private class IMayorIgual implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.mayorQue(opnd2) || opnd1.igualQue(opnd2)));
         pc++;
      } 
      public String toString() {return ">=";};
   }
   private IMenor IMENOR;
   private class IMenor implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.menorQue(opnd2)));
         pc++;
      } 
      public String toString() {return "<";};
   }
   private IMenorIgual IMENORIGUAL;
   private class IMenorIgual implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.menorQue(opnd2) || opnd1.igualQue(opnd2)));
         pc++;
      } 
      public String toString() {return "<=";};
   }
   private IIgual IIGUAL;
   private class IIgual implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         if (opnd1.esInt())
            pilaEvaluacion.push(new ValorBool(opnd1.igualQue(opnd2)));
         pc++;
      } 
      public String toString() {return "==";};
   }
   private IDistinto IDISTINTO;
   private class IDistinto implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(!opnd1.igualQue(opnd2)));
         pc++;
      } 
      public String toString() {return "!=";};
   }
   private IMenos IMENOS;
   private class IMenos implements Instruccion {
      public void ejecuta() {
         Valor opnd1 = pilaEvaluacion.pop();
         if (opnd1.esInt())
            pilaEvaluacion.push(new ValorInt(-opnd1.valorInt()));
         else
            pilaEvaluacion.push(new ValorReal(-opnd1.valorReal()));
         pc++;
      } 
      public String toString() {return "menos";};
   }
   private INot INOT;
   private class INot implements Instruccion {
      public void ejecuta() {
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(!opnd1.valorBool()));
         pc++;
      } 
      public String toString() {return "!=";};
   }
   private class IApilaInt implements Instruccion {
      private int valor;
      public IApilaInt(int valor) {
        this.valor = valor;  
      }
      public void ejecuta() {
         pilaEvaluacion.push(new ValorInt(valor)); 
         pc++;
      } 
      public String toString() {return "apila-int("+valor+")";};
   }

   private class IApilaBool implements Instruccion {
      private boolean valor;
      public IApilaBool(boolean valor) {
        this.valor = valor;  
      }
      public void ejecuta() {
         pilaEvaluacion.push(new ValorBool(valor)); 
         pc++;
      } 
      public String toString() {return "apila-bool("+valor+")";};
   }
   
   private class IApilaReal implements Instruccion {
      private float valor;
      public IApilaReal(float valor) {
        this.valor = valor;  
      }
      public void ejecuta() {
         pilaEvaluacion.push(new ValorReal(this.valor)); 
         pc++;
      } 
      public String toString() {return "apilaReal("+this.valor+")";};
   }
   
   private class IApilaCadena implements Instruccion {
      private String valor;
      public IApilaCadena(String valor) {
        this.valor = valor;  
      }
      public void ejecuta() {
         pilaEvaluacion.push(new ValorString(valor)); 
         pc++;
      } 
      public String toString() {return "apilaReal("+valor+")";};
   }
   
   private IInt2Real IINT2REAL;
   private class IInt2Real implements Instruccion {
      public void ejecuta(){   
         Valor valor = pilaEvaluacion.peek();  
         
         if(valor.esInt()){
             pilaEvaluacion.pop();
             pilaEvaluacion.push(new ValorReal((float)valor.valorInt()));
             pc++;
         }
      }
   }

   private class IIrA implements Instruccion {
      private int dir;
      public IIrA(int dir) {
        this.dir = dir;  
      }
      public void ejecuta() {
            pc=dir;
      } 
      public String toString() {return "ir-a("+dir+")";};
   }

   private class IIrF implements Instruccion {
      private int dir;
      public IIrF(int dir) {
        this.dir = dir;  
      }
      public void ejecuta() {
         if(! pilaEvaluacion.pop().valorBool()) { 
            pc=dir;
         }   
         else {
            pc++; 
         }
      } 
      public String toString() {return "ir-f("+dir+")";};
   }
   
   private class IIrV implements Instruccion {
      private int dir;
      public IIrV(int dir) {
        this.dir = dir;  
      }
      public void ejecuta() {
         if(pilaEvaluacion.pop().valorBool()) { 
            pc=dir;
         }   
         else {
            pc++; 
         }
      } 
      public String toString() {return "ir-v("+dir+")";};
   }
   
   
   private class ICopia implements Instruccion {
      private int tam;
      public ICopia(int tam) {
        this.tam = tam;  
      }
      public void ejecuta() {
            int dirOrigen = pilaEvaluacion.pop().valorInt();
            int dirDestino = pilaEvaluacion.pop().valorInt();
            if ((dirOrigen + (tam-1)) >= datos.length)
                throw new EAccesoFueraDeRango();
            if ((dirDestino + (tam-1)) >= datos.length)
                throw new EAccesoFueraDeRango();
            for (int i=0; i < tam; i++) 
                datos[dirDestino+i] = datos[dirOrigen+i]; 
            pc++;
      } 
      public String toString() {return "copia("+tam+")";};
   }
   
   private IDesapila IDESAPILA;
   private class IDesapila implements Instruccion {
      public void ejecuta(){
         pilaEvaluacion.pop();
         pc++;
      }
      public String toString() {return "desapila";};     
   }
   
   private IApilaind IAPILAIND;
   private class IApilaind implements Instruccion {
      public void ejecuta() {
        int dir = pilaEvaluacion.pop().valorInt();
        if (dir >= datos.length) throw new EAccesoFueraDeRango();
        if (datos[dir] == null)  throw new EAccesoAMemoriaNoInicializada(pc,dir);
        pilaEvaluacion.push(datos[dir]);
        pc++;
      } 
      public String toString() {return "apila-ind";};
   }

   private IDesapilaind IDESAPILAIND;
   private class IDesapilaind implements Instruccion {
      public void ejecuta() {
        Valor valor = pilaEvaluacion.pop();
        int dir = pilaEvaluacion.pop().valorInt();
        if (dir >= datos.length) throw new EAccesoFueraDeRango();
        datos[dir] = valor;
        pc++;
      } 
      public String toString() {return "desapila-ind";};
   }

private class IMueve implements Instruccion {
      private int tam;
      public IMueve(int tam) {
        this.tam = tam;  
      }
      public void ejecuta() {
            int dirOrigen = pilaEvaluacion.pop().valorInt();
            int dirDestino = pilaEvaluacion.pop().valorInt();
            if ((dirOrigen + (tam-1)) >= datos.length)
                throw new EAccesoFueraDeRango();
            if ((dirDestino + (tam-1)) >= datos.length)
                throw new EAccesoFueraDeRango();
            for (int i=0; i < tam; i++) 
                datos[dirDestino+i] = datos[dirOrigen+i]; 
            pc++;
      } 
      public String toString() {return "mueve("+tam+")";};
   }

   private class IAlloc implements Instruccion {
      private int tam;
      public IAlloc(int tam) {
        this.tam = tam;  
      }
      public void ejecuta() {
        int inicio = gestorMemoriaDinamica.alloc(tam);
        pilaEvaluacion.push(new ValorInt(inicio));
        pc++;
      } 
      public String toString() {return "alloc("+tam+")";};
   }

   private class IDealloc implements Instruccion {
      private int tam;
      public IDealloc(int tam) {
        this.tam = tam;  
      }
      public void ejecuta() {
        int inicio = pilaEvaluacion.pop().valorInt();
        gestorMemoriaDinamica.free(inicio,tam);
        pc++;
      } 
      public String toString() {return "dealloc("+tam+")";};
   }
   
   private class IActiva implements Instruccion {
       private int nivel;
       private int tamdatos;
       private int dirretorno;
       public IActiva(int nivel, int tamdatos, int dirretorno) {
           this.nivel = nivel;
           this.tamdatos = tamdatos;
           this.dirretorno = dirretorno;
       }
       public void ejecuta() {
          int base = gestorPilaActivaciones.creaRegistroActivacion(tamdatos);
          datos[base] = new ValorInt(dirretorno);
          datos[base+1] = new ValorInt(gestorPilaActivaciones.display(nivel));
          pilaEvaluacion.push(new ValorInt(base+2));
          pc++;
       }
       public String toString() {
          return "activa("+nivel+","+tamdatos+","+dirretorno+")";                 
       }
   }
   
   private class IDesactiva implements Instruccion {
       private int nivel;
       private int tamdatos;
       public IDesactiva(int nivel, int tamdatos) {
           this.nivel = nivel;
           this.tamdatos = tamdatos;
       }
       public void ejecuta() {
          int base = gestorPilaActivaciones.liberaRegistroActivacion(tamdatos);
          gestorPilaActivaciones.fijaDisplay(nivel,datos[base+1].valorInt());
          pilaEvaluacion.push(datos[base]); 
          pc++;
       }
       public String toString() {
          return "desactiva("+nivel+","+tamdatos+")";                 
       }

   }
   
   private class IDesapilad implements Instruccion {
       private int nivel;
       public IDesapilad(int nivel) {
         this.nivel = nivel;  
       }
       public void ejecuta() {
         gestorPilaActivaciones.fijaDisplay(nivel,pilaEvaluacion.pop().valorInt());  
         pc++;
       }
       public String toString() {
          return "desapilad("+nivel+")";                 
       }
   }
   private IDup IDUP;
   private class IDup implements Instruccion {
       public void ejecuta() {
           pilaEvaluacion.push(pilaEvaluacion.peek());
           pc++;
       }
       public String toString() {
          return "dup";                 
       }
   }
   private Instruccion ISTOP;
   private class IStop implements Instruccion {
       public void ejecuta() {
           pc = codigoP.size();
       }
       public String toString() {
          return "stop";                 
       }
   }
   
   
   private class IApilad implements Instruccion {
       private int nivel;
       public IApilad(int nivel) {
         this.nivel = nivel;  
       }
       public void ejecuta() {
         pilaEvaluacion.push(new ValorInt(gestorPilaActivaciones.display(nivel)));
         pc++;
       }
       public String toString() {
          return "apilad("+nivel+")";                 
       }

   }
   
   private IWrite IWRITE;
   private class IWrite implements Instruccion{
      
      public void ejecuta(){
         Valor valor = pilaEvaluacion.pop();
         if(valor.esInt()) 
        	 System.out.print(valor.valorInt());
         else if(valor.esReal())
        	 System.out.print(valor.valorReal());
         else if (valor.esString())
        	 System.out.print(valor.valorString());
         else if (valor.esBool())
            System.out.print(valor.valorBool());
         pc++;
      }
      public String toString() {return "write";};
   }
   
   private IRead IREAD;
   private class IRead implements Instruccion{
	      
	public void ejecuta(){
		String str ="";
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
     
		
      try{
         str=reader.readLine();
      } catch(IOException e){
         e.printStackTrace();
      }
     
      try{
         int i = Integer.parseInt(str);
         pilaEvaluacion.push(new ValorInt(i));
      } catch(Exception e){
         
         try{
          	  
            float r = Float.parseFloat(str);
            
            pilaEvaluacion.push(new ValorReal(r));
         } catch(Exception e1){
            
        	pilaEvaluacion.push(new ValorString(str));
         }
      }
         pc++;
    }
      
      public String toString() {return "read";};
   }
   
   private INl INL;
   private class INl implements Instruccion {
      public void ejecuta(){
         System.out.println("");
         pc++;
      }
      public String toString() {return "nl";};
   } 
   
   private Instruccion IIRIND;
   private class IIrind implements Instruccion {
       public void ejecuta() {
          pc = pilaEvaluacion.pop().valorInt();  
       }
       public String toString() {
          return "ir-ind";                 
       }
   }
   
  


   public Instruccion suma() {return ISUMA;}
   public Instruccion resta() {return IRESTA;}
   public Instruccion menos() {return IMENOS;}
   public Instruccion mul() {return IMUL;}
   public Instruccion div() {return IDIV;}
   public Instruccion mod() {return IMOD;}
   public Instruccion and() {return IAND;}
   public Instruccion or() {return IOR;}
   public Instruccion not() {return INOT;}
   public Instruccion menor() {return IMENOR;}
   public Instruccion menorIgual() {return IMENORIGUAL;}
   public Instruccion mayor() {return IMAYOR;}
   public Instruccion mayorIgual() {return IMAYORIGUAL;}
   public Instruccion igual() {return IIGUAL;}
   public Instruccion distinto() {return IDISTINTO;}
   
   public Instruccion apila_int(int val) {return new IApilaInt(val);}
   public Instruccion apila_bool(boolean val) {return new IApilaBool(val);}
   public Instruccion apila_real(float val) {return new IApilaReal(val);}
   public Instruccion apila_cadena(String val) {return new IApilaCadena(val);}
 
   public Instruccion desapila() {return IDESAPILA;}
   public Instruccion apila_ind() {return IAPILAIND;}
   public Instruccion desapila_ind() {return IDESAPILAIND;}
   public Instruccion mueve(int tam) {return new IMueve(tam);}
   public Instruccion int2real() {return IINT2REAL;}
   
   public Instruccion nl() {return INL;}
   public Instruccion ir_a(int dir) {return new IIrA(dir);}
   public Instruccion ir_f(int dir) {return new IIrF(dir);}
   public Instruccion ir_v(int dir) {return new IIrV(dir);}
   public Instruccion ir_ind() {return IIRIND;}
   
   public Instruccion alloc(int tam) {return new IAlloc(tam);} 
   public Instruccion dealloc(int tam) {return new IDealloc(tam);} 
   public Instruccion copia(int tam) {return new ICopia(tam);}
   
   public Instruccion activa(int nivel,int tam, int dirretorno) {return new IActiva(nivel,tam,dirretorno);}
   public Instruccion desactiva(int nivel, int tam) {return new IDesactiva(nivel,tam);}
   public Instruccion apilad(int nivel) {return new IApilad(nivel);}
   public Instruccion desapilad(int nivel) {return new IDesapilad(nivel);}
   public Instruccion dup() {return IDUP;}
   public Instruccion stop() {return ISTOP;}
   
   public Instruccion read() {return IREAD;}
   public Instruccion write() {return IWRITE;}
   public void emit(Instruccion i) {
      codigoP.add(i); 
   }

   private int tamdatos;
   private int tamheap;
   private int ndisplays;
   public MaquinaP(int tamdatos, int tampila, int tamheap, int ndisplays) {
      this.tamdatos = tamdatos;
      this.tamheap = tamheap;
      this.ndisplays = ndisplays;
      this.codigoP = new ArrayList<>();  
      pilaEvaluacion = new Stack<>();
      datos = new Valor[tamdatos+tampila+tamheap];
      this.pc = 0;
      ISUMA = new ISuma();
      IRESTA= new IResta();
      IMENOS= new IMenos();
      IAND = new IAnd();
      IMUL = new IMul();
      IOR= new IOr();
      INOT= new INot();
      IMENOR = new IMenor();
      IMENORIGUAL =new IMenorIgual();
      IMAYOR = new IMayor();
      IMAYORIGUAL = new IMayorIgual();
      IIGUAL = new IIgual();
      IDISTINTO = new IDistinto();
      IDESAPILA = new IDesapila();
      IINT2REAL = new IInt2Real();
      IAPILAIND = new IApilaind();
      IDESAPILAIND = new IDesapilaind();
      IIRIND = new IIrind();
      IDUP = new IDup();
      ISTOP = new IStop();
      INL = new INl();
      IREAD = new IRead();
      IWRITE = new IWrite();
      gestorPilaActivaciones = new GestorPilaActivaciones(tamdatos,(tamdatos+tampila)-1,ndisplays); 
      gestorMemoriaDinamica = new GestorMemoriaDinamica(tamdatos+tampila,(tamdatos+tampila+tamheap)-1);
   }
   public void ejecuta() {
      while(pc != codigoP.size()) {
          codigoP.get(pc).ejecuta();
      } 
   }
   public void muestraCodigo() {
     System.out.println("CodigoP");
     for(int i=0; i < codigoP.size(); i++) {
        System.out.println(" "+i+":"+codigoP.get(i));
     }
   }
   public void muestraEstado() {
     System.out.println("Tam datos:"+tamdatos);  
     System.out.println("Tam heap:"+tamheap); 
     System.out.println("PP:"+gestorPilaActivaciones.pp());    
     System.out.print("Displays:");
     for (int i=1; i <= ndisplays; i++)
         System.out.print(i+":"+gestorPilaActivaciones.display(i)+" ");
     System.out.println();
     System.out.println("Pila de evaluacion");
     for(int i=0; i < pilaEvaluacion.size(); i++) {
        System.out.println(" "+i+":"+pilaEvaluacion.get(i));
     }
     System.out.println("Datos");
     for(int i=0; i < datos.length; i++) {
        System.out.println(" "+i+":"+datos[i]);
     }
     System.out.println("PC:"+pc);
   }
}
