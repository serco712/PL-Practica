package asint;

import java.util.Map;

public class SintaxisAbstractaTiny {

    private static void imprimeOpnd(Exp opnd, int np) {
        if(opnd.prioridad() < np) {System.out.println("(");};
        opnd.imprime();
        if(opnd.prioridad() < np) {System.out.println(")");};        
    }
    private static void imprimeExpBin(Exp opnd0, String op, Exp opnd1, int np0, int np1, int fila, int col) {
        imprimeOpnd(opnd0,np0);
        System.out.print(op);
        System.out.format("$f:%d,c:%d$%n", fila, col);
        imprimeOpnd(opnd1,np1);
    }

    public enum Tipado{
        literalEntero, bool, literalReal, literalCadena, error, ok, tipoPuntero, nl, tipoStruct, tipoArray, id,
        dec_proc, dec_simple, dec_type, pf_noref, pf_ref
    }

    public static abstract class Nodo  {
       private Boolean ok;
       private int prim = -1;
	   private int sig = -1;
	   private int nivel = -1;
	   private int espacio = 0;
	   private int dir = -1;
       private Nodo vinculo;
       private Tipo tipo;
       public Nodo() {
           ok = true;
		   fila=col=-1;
       }   
      
	   private int fila;
	   private int col;
	   public Nodo ponFila(int fila) {
		    this.fila = fila;
            return this;			
	   }
	   public Nodo ponCol(int col) {
		    this.col = col;
            return this;			
	   }
	   public int getPrim(){
           return prim;
       }
       public int getSig(){
           return sig;
       }
       public int getNivel(){
           return nivel;
       }
       public int getEspacio(){
           return espacio;
       }
       public int getDir(){
           return dir;
       }
       public void setPrim(int prim){
           this.prim = prim;
       }
       public void setSig(int sig){
           this.sig =  sig;
       }
       public void setNivel(int nivel){
           this.nivel =  nivel;
       }
       public void setEspacio(int espacio){
           this.espacio =  espacio;
       }
       public void setDir(int dir){
           this.dir =  dir;
       }
	   public int leeFila() {
		  return fila+1; 
	   }
	   public int leeCol() {
		  return col; 
	   }

       public abstract void imprime();
       public abstract void procesa(Procesamiento p);
       public Tipo tipo() {throw new IllegalArgumentException();}

       public boolean getOk() {return this.ok;}
       public void setOk(Boolean ok) {this.ok = ok;}
    }
    
    public static class Prog extends Nodo {
        private Blo b;
        public Prog(Blo b) {
            super();
            this.b = b;
        }
        public Blo bloq() { return b; }
        public void imprime() {
            b.imprime();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
             return "prog("+b+")";
         } 
    }

    public static class Blo extends Nodo {
        protected Decs decla;
        protected Insts instr;
        public Blo(Decs d, Insts i) {
            super();
            decla = d;
            instr = i;
        }
        public Decs decla() { return decla; }
        public Insts instr() { return instr; }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public void imprime() {
            System.out.println("{");
            decla.imprime();
            instr.imprime();
            System.out.println("}");
        }
    }
    
    public static abstract class Decs extends Nodo {
        public Decs() {
            super();
        }
        public LDecs decs() { throw new UnsupportedOperationException(); }
    }

    public static abstract class LDecs extends Nodo {
        public LDecs() {
            super();
        }
        public LDecs decs() { throw new UnsupportedOperationException(); }
        public Dec dec() { throw new UnsupportedOperationException(); }
    }

    public static abstract class LVar extends Nodo {
        public LVar() {
            super();
        }
        public LVar vars() { throw new UnsupportedOperationException(); }
        public Var var() { throw new UnsupportedOperationException(); }
    }

    public static class Var extends Nodo {
    	private int despl;
        private Tipo tipo;
        private String str;
        public Var(Tipo tipo, String str) {
           super();
           this.tipo = tipo;
           this.str = str;
        }
        public Tipo tipo() { return tipo; }
        public String id() { return str; }
        public void setDesplaza(int desp) { this.despl = desp; }
        public int getDesplaza() { return despl; }
        public void imprime() {
            tipo.imprime();
            System.out.format("%s$f:%d,c:%d$%n", str, leeFila(), leeCol());
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
            return "var("+tipo+","+str+")";   
        }
    }

    public abstract static class Dec extends Nodo {
        protected Tipado t;
        protected int num_datos;
        public Dec() {
            super();
        }
        public Var var() { throw new UnsupportedOperationException(); }
        public String id() { throw new UnsupportedOperationException(); }
        public PFmls par_for() { throw new UnsupportedOperationException(); }
        public Blo bloq() { throw new UnsupportedOperationException(); }
        public Tipado getTipado() {return t;}
        public void setTipado(Tipado t) {this.t = t;}
        public void setNumDatos(int numdatos) {
        	num_datos = numdatos;
        }
        public int getNumDatos() {
        	return num_datos;
        }
    }

    public static abstract class Tipo extends Nodo {
        protected Tipado t;
        public Tipo() {
            super();
        }
        public Tipado getTipado() {return t;}
        public void setTipado(Tipado t) {this.t = t;}
        public Tipo tipo() { throw new UnsupportedOperationException(); }
        public String litEnt() { throw new UnsupportedOperationException(); }
        public String id() { throw new UnsupportedOperationException(); }
        public LVar lvar() { throw new UnsupportedOperationException(); }
    }
    
    public static abstract class Insts extends Nodo {
        public Insts() {
            super();
        }
        public LInst insts() { throw new UnsupportedOperationException(); }
    }

    public static abstract class LInst extends Nodo {
        public LInst() {
            super();
        }
        public LInst insts() { throw new UnsupportedOperationException(); }
        public Inst inst() { throw new UnsupportedOperationException(); }
    }

    public static abstract class PFmls extends Nodo {
        protected Tipado t;
        public PFmls() {
            super();
        }
        public LPFml lpfml() { throw new UnsupportedOperationException(); }
        public Tipado getTipado() {return t;}
        public void setTipado(Tipado t) {this.t = t;}
    }

    public static abstract class LPFml extends Nodo {
        public LPFml() {
            super();
        }
        public PFml pfml() { throw new UnsupportedOperationException(); }
        public LPFml lpfml() { throw new UnsupportedOperationException(); }
    }

    public static abstract class PFml extends Nodo {
        protected Tipado t;
        public PFml() {
            super();
        }
        public Tipado getTipado() {return t;}
        public void setTipado(Tipado t) {this.t = t;}
        public Tipo tipo() { throw new UnsupportedOperationException(); }
        public String id() { throw new UnsupportedOperationException(); }
    }

    public static abstract class PReales extends Nodo {
        public PReales() {
            super();
        }
        public LPReal lpr() { throw new UnsupportedOperationException(); }
        public boolean esDesignador() { 
    	   return false; 
       }

    }

    public static abstract class LPReal extends Nodo {
        protected Tipado t;
        public LPReal() {
            super();
        }
        public Tipado getTipado() {return t;}
        public void setTipado(Tipado t) {this.t = t;}
        public Exp exp() { throw new UnsupportedOperationException(); }
        public LPReal lpr() { throw new UnsupportedOperationException(); }
    }

    public static abstract class Inst extends Nodo {
        protected Tipado t;
        public Inst() {
            super();
        }
        public LInst insts() { throw new UnsupportedOperationException(); }
        public Exp exp() { throw new UnsupportedOperationException(); }
        public Blo bloq() { throw new UnsupportedOperationException(); }
        public Blo bloq1() { throw new UnsupportedOperationException(); }
        public Blo bloq2() { throw new UnsupportedOperationException(); }
		public String id() { throw new UnsupportedOperationException(); }
        public PReales pr() { throw new UnsupportedOperationException(); }

        public void setTipado(Tipado t){this.t = t;}
        public Tipado getTipado(){return this.t;}
        public Nodo getVinculo(){
        throw new UnsupportedOperationException("Vinculo erróneo");
        }
        public void vincula(Nodo n){
        throw new UnsupportedOperationException("Vinculo no fijado");
        } 
    }

    public static abstract class Exp  extends  Nodo {
       Tipado t;
       public Exp() {
		   super();
       }   
       public Exp exp1(){throw new UnsupportedOperationException(); }
       public Exp exp2(){throw new UnsupportedOperationException(); }
       public String litEnt(){throw new UnsupportedOperationException(); }
       public String litReal(){throw new UnsupportedOperationException(); }
       public String litCad(){throw new UnsupportedOperationException(); }
       public String valor(){throw new UnsupportedOperationException(); }
       public String id(){throw new UnsupportedOperationException(); }

       public boolean esDesignador() { 
    	   return false; 
       }
       public Nodo getVinculo(){
        throw new UnsupportedOperationException("Vinculo erróneo");
       }
       public void vincula(Nodo n){
        throw new UnsupportedOperationException("Vinculo no fijado");
       } 

       public void setTipado(Tipado t){this.t = t;}
       public Tipado getTipado(){return this.t;}

       public abstract int prioridad();
    }
   
   // ----------------------------------------------------
    
    public static class Si_decs extends Decs {
       private LDecs decs; 
       public Si_decs(LDecs decs) {
          super();
          this.decs = decs;
        }   
        public void imprime() {
            decs.imprime();
            System.out.println("&&");
        }
        public LDecs decs() { return decs; }
        public String toString() {
            return "si_decs("+decs+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    public static class No_decs extends Decs {
       public No_decs() {
          super();
       } 
       public void imprime() {
       }  
       public void procesa(Procesamiento p) {
           p.procesa(this);
       }
       public String toString() {
            return "no_decs()";
        } 
    }     
    
    public static class Muchas_decs extends LDecs {
        private LDecs decs;
        private Dec dec;
        public Muchas_decs(LDecs decs, Dec dec) {
            super();
            this.dec = dec;
            this.decs = decs;
        }
        public LDecs decs() { return decs; }
        public Dec dec() { return dec; }
        public void imprime() {
            decs.imprime();
            System.out.println(";");
            dec.imprime();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        } 
        public String toString() {
                return "muchas_decs("+decs+","+dec+")";
        } 
    }

    public static class Una_dec extends LDecs {
        private Dec dec;
        public Una_dec(Dec dec) {
            super();
            this.dec = dec;
        }
        public Dec dec() { return dec; }
        public void imprime() {
            dec.imprime();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public String toString() {
                return "una_dec("+dec+")";
        } 
    } 
    
    public static class Muchas_var extends LVar {
        private LVar vars;
        private Var v;
        public Muchas_var(LVar vars, Var v) {
            super();
            this.v = v;
            this.vars = vars;
        }
        public LVar vars() { return vars; }
        public Var var() { return v; }
        public void imprime() {
            vars.imprime();
            System.out.println(",");
            v.imprime();
        }
        public String toString() {
            return "muchas_var("+vars+","+v+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

    public static class Una_var extends LVar {
        private Var v;
        public Una_var(Var v) {
            super();
            this.v = v;
        }
        public Var var() { return v; }
        public void imprime() {
            v.imprime();
        }
        public String toString() {
            return "una_var("+v+")";
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    } 
    
     public static class Dec_simple extends Dec {
        private Var v;
        public Dec_simple(Var v) {
            super();
            this.v = v;
            this.t = Tipado.dec_simple;
        }
        public Var var() { return v; }
        public void imprime() {
            v.imprime();
        }
        public String toString() {
            return "dec_simple("+v+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }   
    
    public static class Dec_type extends Dec {
        private Var v;
        public Dec_type(Var v) {
            super();
            this.v = v;
            this.t = Tipado.dec_type;
        }
        public Var var() { return v; }
        public void imprime() {
            System.out.println("<Tipado>");
            v.imprime();
        }
        public String toString() {
            return "dec_Tipado("+v+")";
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }       
    }
    
    public static class Dec_proc extends Dec {
        private String str;
        private PFmls parfor;
        private Blo bloq;
        public Dec_proc(String str, PFmls parfor, Blo bloq) {
            super();
            this.str = str;
            this.parfor = parfor;
            this.bloq = bloq;
            this.t = Tipado.dec_proc;
        }
        public String id() { return str; }
        public PFmls par_for() { return parfor; }
        public Blo bloq() { return bloq; }
        public void imprime() {
            System.out.println("<proc>");
            System.out.format("%s$f:%d,c:%d$%n", str, leeFila(), leeCol());
            parfor.imprime();
            bloq.imprime();
        }
        public String toString() {
                return "dec_proc("+str+","+parfor+","+bloq+")";
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        
    }
    
    public static class Tipo_array extends Tipo {
        private Tipo tipo;
        private String str;
        private int tam;
        public Tipo_array(Tipo tipo, String str) {
            super();
            this.tipo = tipo;
            this.str = str;
            this.t = Tipado.tipoArray;
        }
        public Tipo tipo() { return tipo; }
        public String litEnt() { return str; }
        public void imprime() {
            tipo.imprime();
            System.out.format("[%n%s%n]$f:%d,c:%d$%n", str, leeFila(), leeCol());
        }
        public String toString() {
                return "tipo_array("+tipo+","+str+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        public void setTam(int tam) {this.tam = tam;}
        public int getTam() {return tam;}
    }
         
    public static class Tipo_punt extends Tipo {
        private Tipo tipo;
        public Tipo_punt(Tipo tipo) {
            super();
            this.tipo = tipo;
            this.t = Tipado.tipoPuntero;
        }
        public Tipo tipo() { return tipo; }
        public void imprime() {
            System.out.println("^");
            tipo.imprime();
        }
        public String toString() {
                return "tipo_array("+tipo+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    public static class Tipo_bool extends Tipo {
        public Tipo_bool() {
            super();
            this.t = Tipado.bool;
        }
        public void imprime() {
            System.out.println("<bool>");
        }
        public String toString() {
            return "tipo_bool()";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

	public static class Tipo_int extends Tipo {
        public Tipo_int() {
            super();
            this.t = Tipado.literalEntero;
        }
        public void imprime() {
            System.out.println("<int>");
        }
        public String toString() {
            return "tipo_int()";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    public static class Tipo_real extends Tipo {
        public Tipo_real() {
            super();
            this.t = Tipado.literalReal;
        }
        public void imprime() {
            System.out.println("<real>");
        }
        public String toString() {
            return "tipo_real()";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

	public static class Tipo_string extends Tipo {
        public Tipo_string() {
            super();
            this.t = Tipado.literalCadena;
        }
        public void imprime() {
            System.out.println("<string>");
        }
        public String toString() {
            return "tipo_string()";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    public static class Tipo_ident extends Tipo {
      private String ident;
      private Nodo vinculo;
        public Tipo_ident(String ident) {
            super();
            this.ident = ident;
            this.t = Tipado.id;
        }
        public String id() { return ident; }
        public void imprime() {
            System.out.format("%s$f:%d,c:%d$%n", ident, leeFila(), leeCol());
        }
        public String toString() {
            return "tipo_ident("+ident+")";
        }
        public Nodo getVinculo() {
            return vinculo;
        }
        public void vincula(Nodo n) {
            vinculo = n;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    public static class Tipo_struct extends Tipo {
        private LVar lvar;
        private Map<String,Nodo> reg;
        public Tipo_struct(LVar lvar) {
            super();
            this.lvar = lvar;
            this.t = Tipado.tipoStruct;
        }
        public LVar lvar() { return lvar; }
        public void imprime() {
            System.out.println("<struct>");
            System.out.println("{");
            lvar.imprime();
            System.out.println("}");
        }
        public Map<String,Nodo> accedeReg() {return reg;}
        public void vincula(Map<String,Nodo> reg) {
            this.reg = reg;
        }
        public String toString() {
                return "tipo_struct("+lvar+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    public static class Si_inst extends Insts {
        private LInst insts;
        public Si_inst(LInst insts) {
            super();
            this.insts = insts;
        }
        public LInst insts() { return insts; }
        public void imprime() {
            insts.imprime();
        }
        public String toString() {
            return "si_inst("+insts+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    public static class No_inst extends Insts {
        public No_inst() {
            super();
        }
        public void imprime() {
        }
        public String toString() {
            return "no_inst()";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    public static class Muchas_inst extends LInst {
        private LInst insts;
        private Inst inst;
        public Muchas_inst(LInst insts, Inst inst) {
            super();
            this.insts = insts;
            this.inst = inst;
        }
        public LInst insts() { return insts; }
        public Inst inst() { return inst; }
        public void imprime() {
            insts.imprime();
            System.out.println(";");
            inst.imprime();
        }
        public String toString() {
            return "muchas_inst("+insts+","+inst+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

    public static class Una_inst extends LInst {
        private Inst inst;
        public Una_inst(Inst inst) {
            super();
            this.inst = inst;
        }
        public Inst inst() { return inst; }
        public void imprime() {
            inst.imprime();
        }
        public String toString() {
            return "una_inst("+inst+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    } 
    
    public static class Si_pformal extends PFmls {
        private LPFml lpfml;
        public Si_pformal(LPFml lpfml) {
            super();
            this.lpfml = lpfml;
        }
        public LPFml lpfml() { return lpfml; }
        public void imprime() {
        	System.out.println("(");
            lpfml.imprime();        	
            System.out.println(")");
        }
        public String toString() {
            return "si_pformal("+lpfml+")";
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    public static class No_pformal extends PFmls {
        public No_pformal() {
            super();
        }
        public void imprime() {
        	System.out.println("(");
        	System.out.println(")");
        }
        public String toString() {
            return "no_pformal()";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    public static class Muchos_pformal extends LPFml {
        private PFml pfml;
        private LPFml lpfml;
        public Muchos_pformal(PFml pfml, LPFml lpfml) {
            super();
            this.pfml = pfml;
            this.lpfml = lpfml;
        }
        public PFml pfml() { return pfml; }
        public LPFml lpfml() { return lpfml; }
        public void imprime() {
            lpfml.imprime();
            System.out.println(",");
            pfml.imprime();
        }
        public String toString() {
            return "muchas_pformal("+pfml+","+lpfml+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

    public static class Un_pformal extends LPFml {
        private PFml pfml;
        public Un_pformal(PFml pfml) {
            super();
            this.pfml = pfml;
        }
        public PFml pfml() { return pfml; }
        public void imprime() {
            pfml.imprime();
        }
        public String toString() {
            return "una_pformal("+pfml+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    public static class Pformal_ref extends PFml {
        private Tipo tipo;
        private String str;
        public Pformal_ref(Tipo tipo, String str) {
            super();
            this.tipo = tipo;
            this.str = str;
            this.t = Tipado.pf_ref;
        }
        public Tipo tipo() { return tipo; }
        public String id() { return str; }
        public void imprime() {
            tipo.imprime();
            System.out.format("&%n%s$f:%d,c:%d$%n", str, leeFila(), leeCol());
        }
        public String toString() {
            return "pformal_ref("+tipo+","+str+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }  
    }
    
    public static class Pformal_noref extends PFml {
        private Tipo tipo;
        private String str;
        public Pformal_noref(Tipo tipo, String str) {
           super();
           this.tipo = tipo;
           this.str = str;
           this.t = Tipado.pf_noref;
        }
        public Tipo tipo() { return tipo; }
        public String id() { return str; }
        public void imprime() {
            tipo.imprime();
            System.out.format("%s$f:%d,c:%d$%n", str, leeFila(), leeCol());
        }
        public String toString() {
             return "pformal_noref("+tipo+","+str+")";
         } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

    public static class Si_preales extends PReales {
        private LPReal lpr;
        public Si_preales(LPReal lpr) {
           super();
           this.lpr = lpr;
        }
        public LPReal lpr() { return lpr; }
        public void imprime() {
        	System.out.println("(");
            lpr.imprime();
            System.out.println(")");
        }
        public String toString() {
             return "si_preales("+lpr+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

    public static class No_preales extends PReales {
        public No_preales() {
           super();
        }
        public void imprime() {
        	System.out.println("(");
        	System.out.println(")");
        }
        public String toString() {
             return "no_preales()";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    public static class Muchas_exp extends LPReal {
        private Exp e;
        private LPReal lpr;
        public Muchas_exp(Exp e, LPReal lpr) {
           super();
           this.e = e;
           this.lpr = lpr;
        }
        public Exp exp() { return e; }
        public LPReal lpr() { return lpr; }
        public void imprime() {
            lpr.imprime();
            System.out.println(",");
            e.imprime();
        }
        public String toString() {
             return "una_exp("+lpr+","+e+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

    public static class Una_exp extends LPReal {
        private Exp e;
        public Una_exp(Exp e) {
           super();
           this.e = e;
        }
        public Exp exp() { return e; }
        public void imprime() {
            e.imprime();
        }
        public String toString() {
             return "una_exp("+e+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

    public static class Inst_eval extends Inst {
        private Exp e;
        public Inst_eval(Exp e) {
            super();
            this.e = e;
        }
        public Exp exp() { return e; }
        public void imprime() {
            System.out.println("@");
            e.imprime();
        }
        public String toString() {
            return "inst_eval("+e+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

    public static class Inst_if extends Inst {
        private Exp e;
        private Blo b;
        public Inst_if(Exp e, Blo b) {
            super();
            this.e = e;
            this.b = b;
        }
        public Exp exp() { return e; }
        public Blo bloq() { return b; }
        public void imprime() {
            System.out.println("<if>");
            e.imprime();
            b.imprime();
        }
        public String toString() {
            return "inst_if("+e+","+b+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

    public static class Inst_else extends Inst {
        private Exp e;
        private Blo b1;
        private Blo b2;
        public Inst_else(Exp e, Blo b1, Blo b2) {
            super();
            this.e = e;
            this.b1 = b1;
            this.b2 = b2;
        }
        public Exp exp() { return e; }
        public Blo bloq1() { return b1; }
        public Blo bloq2() { return b2; }
        public void imprime() {
            System.out.println("<if>");
            e.imprime();
            b1.imprime();
            System.out.println("<else>");
            b2.imprime();
        }
        public String toString() {
            return "inst_else("+e+","+b1+","+b2+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

    public static class Inst_while extends Inst {
        private Exp e;
        private Blo b;
        public Inst_while(Exp e, Blo b) {
            super();
            this.e = e;
            this.b = b;
        }
        public Exp exp() { return e; }
        public Blo bloq() { return b; }
        public void imprime() {
            System.out.println("<while>");
            e.imprime();
            b.imprime();
        }
        public String toString() {
            return "inst_while("+e+","+b+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

    public static class Inst_new extends Inst {
        private Exp e;
        public Inst_new(Exp e) {
            super();
            this.e = e;
        }
        public Exp exp() { return e; }
        public void imprime() {
            System.out.println("<new>");
            e.imprime();
        }
        public String toString() {
            return "inst_new("+e+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

    public static class Inst_delete extends Inst {
        private Exp e;
        public Inst_delete(Exp e) {
            super();
            this.e = e;
        }
        public Exp exp() { return e; }
        public void imprime() {
            System.out.println("<delete>");
            e.imprime();
        }
        public String toString() {
            return "inst_delete("+e+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

    public static class Inst_read extends Inst {
        private Exp e;
        public Inst_read(Exp e) {
            super();
            this.e = e;
        }
        public Exp exp() { return e; }
        public void imprime() {
            System.out.println("<read>");
            e.imprime();
        }
        public String toString() {
            return "inst_read("+e+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }

    public static class Inst_write extends Inst {
        private Exp e;
        public Inst_write(Exp e) {
            super();
            this.e = e;
        }
        public Exp exp() { return e; }
        public void imprime() {
            System.out.println("<write>");
            e.imprime();
        }
        public String toString() {
            return "inst_write("+e+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    } 

    public static class Inst_call extends Inst {
        private String id;
        private PReales pr;
        private Nodo vinculo;
        public Inst_call(String iden, PReales p) {
            super();
            id = iden;
            pr = p;
        }
        public String id() { return id; }
        public PReales pr() { return pr; }
        public void imprime() {
            System.out.println("<call>");
            System.out.format("%s$f:%d,c:%d$%n", id, leeFila(), leeCol());
            pr.imprime();
        }
        public String toString() {
            return "inst_call("+id+","+pr+")";
        }
        @Override
        public Nodo getVinculo() {
            return vinculo;
        }
        @Override
        public void vincula(Nodo n) {
            vinculo = n;
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    } 
    
    public static class Inst_nl extends Inst {
        public Inst_nl() {
            super();
        }
        public void imprime() {
        	System.out.println("<nl>");
        }        
        public String toString() {
            return "inst_nl()";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    } 

    public static class Inst_blo extends Inst {
        private Blo b;
        public Inst_blo(Blo b) {
            super();
            this.b = b;
        }
        public Blo bloq() { return b; }
        public void imprime() {
            b.imprime();
        }
        public String toString() {
            return "inst_blo("+b+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    
    public static class Exp_asig extends Exp{
        private Exp exp1;
        private Exp exp2;
        public Exp_asig(Exp exp1,Exp exp2){
            super();
            this.exp1 = exp1;
            this.exp2 = exp2;
        }
        public Exp exp1(){return exp1;}
        public Exp exp2(){return exp2;}
        public int prioridad() { return 0; }
        public void imprime() {
           imprimeExpBin(exp1, "=", exp2, 1, 0, leeFila(), leeCol());
       }
        public String toString() {
            return "exp_asig("+exp1+","+exp2+")";
        }  
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
		public boolean esDesignador() {
			return true;
		}
     }
    
     public static class Exp_menor extends Exp{
        private Exp exp1;
         private Exp exp2;
        public Exp_menor(Exp exp1,Exp exp2){
            super();
            this.exp1 = exp1;
            this.exp2 = exp2;
        }
        public Exp exp1(){return exp1;}
        public Exp exp2(){return exp2;}
        public int prioridad() { return 1; }
        public void imprime() {
           imprimeExpBin(exp1, "<", exp2, 1, 2, leeFila(), leeCol());
       }
        public String toString() {
            return "exp_menor("+exp1+","+exp2+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
		public boolean esDesignador() {
			return true;
		}
     }
    
     public static class Exp_menIgual extends Exp{
        private Exp exp1;
         private Exp exp2;
        public Exp_menIgual(Exp exp1,Exp exp2){
            super();
            this.exp1 = exp1;
            this.exp2 = exp2;
        }
        public Exp exp1(){return exp1;}
        public Exp exp2(){return exp2;}
        public int prioridad() { return 1; }
        public void imprime() {
           imprimeExpBin(exp1, "<=", exp2, 1, 2, leeFila(), leeCol());
       }
        public String toString() {
            return "exp_menIgual("+exp1+","+exp2+")";
        }  
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
		public boolean esDesignador() {
			return true;
		}
     }
    
     public static class Exp_mayor extends Exp{
        private Exp exp1;
        private Exp exp2;
        public Exp_mayor(Exp exp1,Exp exp2){
            super();
            this.exp1 = exp1;
            this.exp2 = exp2;
        }
        public Exp exp1(){return exp1;}
        public Exp exp2(){return exp2;}
        public int prioridad() { return 1; }
        public void imprime() {
           imprimeExpBin(exp1, ">", exp2, 1, 2, leeFila(), leeCol());
       }
        public String toString() {
            return "exp_mayor("+exp1+","+exp2+")";
        }  
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
		public boolean esDesignador() {
			return true;
		}
     }
    
    public static class Exp_mayIgual extends Exp{
        private Exp exp1;
        private Exp exp2;
        public Exp_mayIgual(Exp exp1,Exp exp2){
            super();
            this.exp1 = exp1;
            this.exp2 = exp2;
        }
        public Exp exp1(){return exp1;}
        public Exp exp2(){return exp2;}
        public int prioridad() { return 1; }
        public void imprime() {
           imprimeExpBin(exp1, ">=", exp2, 1, 2, leeFila(), leeCol());
       }
        public String toString() {
            return "exp_mayIgual("+exp1+","+exp2+")";
        }  
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
		public boolean esDesignador() {
			return true;
		}
     }
    
    public static class Exp_igual extends Exp{
        private Exp exp1;
        private Exp exp2;
        public Exp_igual(Exp exp1,Exp exp2){
            super();
            this.exp1 = exp1;
            this.exp2 = exp2;
        }
        public Exp exp1(){return exp1;}
        public Exp exp2(){return exp2;}
        public int prioridad() { return 1; }
        public void imprime() {
           imprimeExpBin(exp1, "==", exp2, 1, 2, leeFila(), leeCol());
       }
        public String toString() {
            return "exp_igual("+exp1+","+exp2+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
		public boolean esDesignador() {
			return true;
		}
     }
    
    public static class Exp_dist extends Exp{
        private Exp exp1;
         private Exp exp2;
        public Exp_dist(Exp exp1,Exp exp2){
            super();
            this.exp1 = exp1;
            this.exp2 = exp2;
        }
        public Exp exp1(){return exp1;}
        public Exp exp2(){return exp2;}
        public int prioridad() { return 1; }
        public void imprime() { 
            imprimeExpBin(exp1, "!=", exp2, 1, 2, leeFila(), leeCol());
        }
        public String toString() {
            return "exp_dist("+exp1+","+exp2+")";
        }  
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
		public boolean esDesignador() {
			return true;
		}
     }
    
    public static class Exp_suma extends Exp{
        private Exp exp1;
         private Exp exp2;
        public Exp_suma(Exp exp1,Exp exp2){
            super();
            this.exp1 = exp1;
            this.exp2 = exp2;
        }
        public Exp exp1(){return exp1;}
        public Exp exp2(){return exp2;}
        public int prioridad() { return 2; }
        public void imprime() { 
            imprimeExpBin(exp1, "+", exp2, 2, 3, leeFila(), leeCol());
        }
        public String toString() {
            return "exp_suma("+exp1+","+exp2+")";
        }  
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
		public boolean esDesignador() {
			return true;
		}
     }
    
    public static class Exp_resta extends Exp{
        private Exp exp1;
         private Exp exp2;
        public Exp_resta(Exp exp1,Exp exp2){
            super();
            this.exp1 = exp1;
            this.exp2 = exp2;
        }
        public Exp exp1(){return exp1;}
        public Exp exp2(){return exp2;}
        public int prioridad() { return 2; }
        public void imprime() { 
            imprimeExpBin(exp1, "-", exp2, 3, 3, leeFila(), leeCol());
        }
        public String toString() {
            return "exp_resta("+exp1+","+exp2+")";
        }  
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
		public boolean esDesignador() {
			return true;
		}
     }
    
    public static class Exp_mult extends Exp{
        private Exp exp1;
         private Exp exp2;
        public Exp_mult(Exp exp1,Exp exp2){
            super();
            this.exp1 = exp1;
            this.exp2 = exp2;
        }
        public Exp exp1(){return exp1;}
        public Exp exp2(){return exp2;}
        public int prioridad() { return 4; }
        public void imprime() { 
            imprimeExpBin(exp1, "*", exp2, 4, 5, leeFila(), leeCol());
        }
        public String toString() {
            return "exp_mult("+exp1+","+exp2+")";
        }  
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
		public boolean esDesignador() {
			return true;
		}
     }
    
    public static class Exp_div extends Exp{
        private Exp exp1;
         private Exp exp2;
        public Exp_div(Exp exp1,Exp exp2){
            super();
            this.exp1 = exp1;
            this.exp2 = exp2;
        }
        public Exp exp1(){return exp1;}
        public Exp exp2(){return exp2;}
        public int prioridad() { return 4; }
        public void imprime() { 
            imprimeExpBin(exp1, "/", exp2, 4, 5, leeFila(), leeCol());
        }
        public String toString() {
            return "exp_div("+exp1+","+exp2+")";
        }  
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
		public boolean esDesignador() {
			return true;
		}
     }
    public static class Exp_mod extends Exp{
        private Exp exp1;
        private Exp exp2;
        public Exp_mod(Exp exp1,Exp exp2){
            super();
            this.exp1 = exp1;
            this.exp2 = exp2;
        }
        public Exp exp1(){return exp1;}
        public Exp exp2(){return exp2;}
        public int prioridad() { return 4; }
        public void imprime() { 
            imprimeExpBin(exp1, "%", exp2, 4, 5, leeFila(), leeCol());
        }
        public String toString() {
            return "exp_mod("+exp1+","+exp2+")";
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
		public boolean esDesignador() {
			return true;
		}
     }
  
  public static class Exp_and extends Exp{
        private Exp exp1;
         private Exp exp2;
        public Exp_and(Exp exp1,Exp exp2){
            super();
            this.exp1 = exp1;
            this.exp2 = exp2;
        }
        public Exp exp1(){return exp1;}
        public Exp exp2(){return exp2;}
        public int prioridad() { return 3; }
        public void imprime() { 
            imprimeExpBin(exp1, "<and>", exp2, 4, 3, leeFila(), leeCol());
        }
        public String toString() {
            return "exp_and("+exp1+","+exp2+")";
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
		public boolean esDesignador() {
			return true;
		}
     }

	public static class Exp_or extends Exp{
        private Exp exp1;
         private Exp exp2;
        public Exp_or(Exp exp1,Exp exp2){
            super();
            this.exp1 = exp1;
            this.exp2 = exp2;
        }
        public Exp exp1(){return exp1;}
        public Exp exp2(){return exp2;}
        public int prioridad() { return 3; }
        public void imprime() { 
            imprimeExpBin(exp1, "<or>", exp2, 4, 4, leeFila(), leeCol());
        }
        public String toString() {
            return "exp_or("+exp1+","+exp2+")";
        }  
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
		public boolean esDesignador() {
			return true;
		}
     }
     
    public static class Exp_menos extends Exp{
        private Exp exp;
        public Exp_menos(Exp exp){
            super();
            this.exp = exp;
        }
        public Exp exp1(){return exp;}
        public int prioridad() { return 5; }
        public void imprime() { 
            System.out.format("-$f:%d,c:%d$%n", leeFila(), leeCol());
            imprimeOpnd(exp, 5);
        }
        public String toString() {
            return "exp_menos("+exp+")";
        }  
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
		public boolean esDesignador() {
			return true;
		}
     }
     
    public static class Exp_not extends Exp{
        private Exp exp;
        public Exp_not(Exp exp){
            super();
            this.exp = exp;
        }
        public Exp exp1(){return exp;}
        public int prioridad() { return 5; }
        public void imprime() { 
        	System.out.format("<not>$f:%d,c:%d$%n", leeFila(), leeCol());
            imprimeOpnd(exp, 5);
        }
        public String toString() {
            return "exp_not("+exp+")";
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
		public boolean esDesignador() {
			return true;
		}
     }
    
     public static class Exp_index extends Exp{
        private Exp exp1;
        private Exp exp2;
        public Exp_index(Exp exp1,Exp exp2){
            super();
            this.exp1 = exp1;
            this.exp2 = exp2;
        }
        public Exp exp1(){return exp1;}
        public Exp exp2(){return exp2;}
        public int prioridad() { return 6; }
        public void imprime() { 
            imprimeOpnd(exp1, 6);
            System.out.format("[$f:%d,c:%d$%n", leeFila(), leeCol());
            imprimeOpnd(exp2, 0);
            System.out.println("]");
        }
        public String toString() {
            return "exp_index("+exp1+","+exp2+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
        @Override
		public boolean esDesignador() {
			return true;
		}
    }
    
     public static class Exp_reg extends Exp{
        private Exp exp;
        private String s;
        public Exp_reg(Exp exp,String s){
            super();
            this.exp = exp;
            this.s = s;
        }
        public Exp exp1(){return exp;}
        public String id(){return s;}
        public int prioridad() { return 6; }
        public void imprime() { 
            imprimeOpnd(exp, 6);
            System.out.println(".");
            System.out.format("%s$f:%d,c:%d$%n",s , leeFila(), leeCol());
        }
        public String toString() {
            return "exp_reg("+exp+","+s+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    public static class Exp_indir extends Exp{
        private Exp exp;
        public Exp_indir(Exp exp){
            super();
            this.exp = exp;
        }
        public Exp exp1(){return exp;}
        public int prioridad() { return 6; }
        public void imprime() { 
            imprimeOpnd(exp, 6);
            System.out.format("^$f:%d,c:%d$%n", leeFila(), leeCol());
        }
        public String toString() {
            return "exp_indir("+exp+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    public static class Exp_true extends Exp{
        public Exp_true(){
 			super();
        }
        public int prioridad() { return 7; }
        public void imprime() { 
            System.out.format("<true>$f:%d,c:%d$%n", leeFila(), leeCol());
        }
        public String toString() {
            return "exp_true()";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    public static class Exp_false extends Exp{
        public Exp_false(){
 			super();
        }
        public int prioridad() { return 7; }
        public void imprime() { 
            System.out.format("<false>$f:%d,c:%d$%n", leeFila(), leeCol());
        }
        public String toString() {
            return "exp_false()";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    public static class Exp_litEnt extends Exp{
        private String s;
        public Exp_litEnt(String s){
            super();
            this.s = s;
        }
        public String litEnt() {
        	return s;
        }
        public int prioridad() { return 7; }
        public void imprime() { 
            System.out.format("%s$f:%d,c:%d$%n", s, leeFila(), leeCol());
        }
        public String toString() {
            return "exp_litEnt("+s+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
     public static class Exp_litReal extends Exp{
        private String s;
        public Exp_litReal(String s){
            super();
            this.s = s;
        }
        public String litReal() {
        	return s;
        }
        public int prioridad() { return 7; }
        public void imprime() { 
            System.out.format("%s$f:%d,c:%d$%n", s, leeFila(), leeCol());
        }
        public String toString() {
            return "exp_litReal("+s+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
     public static class Exp_litCadena extends Exp{
        private String s;
        public Exp_litCadena(String s){
            super();
            this.s = s;
        }
        public String litCad() {
        	return s;
        }

        public int prioridad() { return 7; }
        public void imprime() { 
            System.out.format("%s$f:%d,c:%d$%n", s, leeFila(), leeCol());
        }
        public String toString() {
            return "exp_litCad("+s+")";
        } 
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
    public static class Exp_iden extends Exp{
        private String s;
        private Nodo vinculo;
        public Exp_iden(String s){
            super();
            this.s = s;
        }
        public String id() {
        	return s;
        }
        public int prioridad() { return 7; }
        public void imprime() { 
            System.out.format("%s$f:%d,c:%d$%n", s, leeFila(), leeCol());
        }
        public String toString() {
            return "exp_iden("+s+")";
        }
        @Override
        public Nodo getVinculo() {
            return vinculo;
        }
        @Override
        public void vincula(Nodo n) {
            vinculo = n;
        }
        @Override
        public int getEspacio() {
            return vinculo.getEspacio();
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
    
	public static class Exp_null extends Exp{
        public Exp_null(){
            super();
        }
        public int prioridad() { return 7; }
        public void imprime() {
            System.out.format("<null>$f:%d,c:%d$%n", leeFila(), leeCol());
        }
        public String toString() {
            return "exp_null()";
        }
        public void procesa(Procesamiento p) {
            p.procesa(this);
        }
    }
	
     // Constructoras    
    public Prog prog(Blo b) {
        return new Prog(b);
    }
    public Blo bloq(Decs d, Insts i) {
        return new Blo(d,i);
    }
    public Decs si_decs(LDecs ld) {
        return new Si_decs(ld);
    }
    public Decs no_decs() {
        return new No_decs();
    }
    public LDecs muchas_decs(LDecs decs, Dec dec) {
        return new Muchas_decs(decs,dec);
    }
    public LDecs una_dec(Dec dec) {
        return new Una_dec(dec);
    }
    public LVar muchas_var(LVar vars, Var v) {
        return new Muchas_var(vars,v);
    }
    public LVar una_var(Var v) {
        return new Una_var(v);
    }
    public Var var(Tipo t, String str) {
        return new Var(t, str);
    }
    public Dec dec_simple(Var v) {
        return new Dec_simple(v);
    }
    public Dec dec_type(Var v) {
        return new Dec_type(v);
    }
    public Dec dec_proc(String id, PFmls pfs, Blo b) {
        return new Dec_proc(id, pfs, b);
    }
    public Tipo tipo_array(Tipo t, String id) {
        return new Tipo_array(t, id);
    }
    public Tipo tipo_punt(Tipo t) {
        return new Tipo_punt(t);
    }
    public Tipo tipo_bool() {
        return new Tipo_bool();
    }
    public Tipo tipo_int() {
        return new Tipo_int();
    }
    public Tipo tipo_real() {
        return new Tipo_real();
    }
    public Tipo tipo_string() {
        return new Tipo_string();
    }
    public Tipo tipo_ident(String id) {
        return new Tipo_ident(id);
    }
    public Tipo tipo_struct(LVar lv) {
        return new Tipo_struct(lv);
    }
    public Insts si_inst(LInst li) {
        return new Si_inst(li);
    }
    public Insts no_inst() {
        return new No_inst();
    }
    public LInst muchas_inst(LInst li, Inst i) {
        return new Muchas_inst(li,i);
    }
    public LInst una_inst(Inst i) {
        return new Una_inst(i);
    }
    public PFmls si_pformal(LPFml lpf) {
        return new Si_pformal(lpf);
    }
    public PFmls no_pformal() {
        return new No_pformal();
    }
    public LPFml muchos_pformal(PFml pfm, LPFml lpf) {
        return new Muchos_pformal(pfm,lpf);
    }
    public LPFml un_pformal(PFml pfm) {
        return new Un_pformal(pfm);
    }
    public PFml pformal_ref(Tipo t, String id) {
        return new Pformal_ref(t,id);
    }
    public PFml pformal_noref(Tipo t, String id) {
        return new Pformal_noref(t,id);
    }
    public PReales si_preales(LPReal lpr) {
        return new Si_preales(lpr);
    }
    public PReales no_preales() {
        return new No_preales();
    }
    
    public LPReal muchas_exp(Exp exp,LPReal lp){
        return new Muchas_exp(exp, lp);
    }

	public LPReal una_exp(Exp exp){
        return new Una_exp(exp);
    }
    
    public Inst inst_eval(Exp exp){
        return new Inst_eval(exp);
    }
    
    public Inst inst_if(Exp exp, Blo blo){
        return new Inst_if(exp, blo);
    }
    
    public Inst inst_else(Exp exp, Blo blo1, Blo blo2){
        return new Inst_else(exp, blo1,blo2);
    }
    
    public Inst inst_while(Exp exp, Blo blo){
        return new Inst_while(exp, blo);
    }
    
    public Inst inst_new(Exp exp){
        return new Inst_new(exp);
    }
    
    public Inst inst_delete(Exp exp){
        return new Inst_delete(exp);
    }
    
	public Inst inst_read(Exp exp){
        return new Inst_read(exp);
    }
    
    public Inst inst_write(Exp exp){
        return new Inst_write(exp);
    }
    
    public Inst inst_call(String s, PReales pr){
        return new Inst_call(s,pr);
    }
    
    public Inst inst_nl(){
        return new Inst_nl();
    }
    
    public Inst inst_blo(Blo blo){
        return new Inst_blo(blo);
    }
    
    public Exp exp_asig(Exp exp1, Exp exp2){
        return new Exp_asig(exp1,exp2);
    }
    
    public Exp exp_menor(Exp exp1, Exp exp2){
        return new Exp_menor(exp1,exp2);
    }
    
    public Exp exp_menIgual(Exp exp1, Exp exp2){
        return new Exp_menIgual(exp1,exp2);
    }
    
    public Exp exp_mayor(Exp exp1, Exp exp2){
        return new Exp_mayor(exp1,exp2);
    }
    
    public Exp exp_mayIgual(Exp exp1, Exp exp2){
        return new Exp_mayIgual(exp1,exp2);
    }
    
    public Exp exp_igual(Exp exp1, Exp exp2){
        return new Exp_igual(exp1,exp2);
    }
    
    public Exp exp_dist(Exp exp1, Exp exp2){
        return new Exp_dist(exp1,exp2);
    }
    
    public Exp exp_suma(Exp exp1, Exp exp2){
        return new Exp_suma(exp1,exp2);
    }
    
    public Exp exp_resta(Exp exp1, Exp exp2){
        return new Exp_resta(exp1,exp2);
    }
    
    public Exp exp_mult(Exp exp1, Exp exp2){
        return new Exp_mult(exp1,exp2);
    }
    
    public Exp exp_div(Exp exp1, Exp exp2){
        return new Exp_div(exp1,exp2);
    }
    
    public Exp exp_mod(Exp exp1, Exp exp2){
        return new Exp_mod(exp1,exp2);
    }
    
    public Exp exp_and(Exp exp1, Exp exp2){
        return new Exp_and(exp1,exp2);
    }
    
    public Exp exp_or(Exp exp1, Exp exp2){
        return new Exp_or(exp1, exp2);
    }
    
    public Exp exp_menos(Exp exp){
        return new Exp_menos(exp);
    }
    
    public Exp exp_not(Exp exp){
        return new Exp_not(exp);
    }
    
    public Exp exp_index(Exp exp1,Exp exp2){
        return new Exp_index(exp1,exp2);
    }
    
    public Exp exp_reg(Exp exp,String s){
        return new Exp_reg(exp,s);
    }
    
    public Exp exp_indir(Exp exp){
        return new Exp_indir(exp);
    }
	
	public Exp exp_true(){
        return new Exp_true();
    }
	
	public Exp exp_false(){
        return new Exp_false();
    }
    
	public Exp exp_litEnt(String s){
        return new Exp_litEnt(s);
    }

    public Exp exp_litReal(String s){
        return new Exp_litReal(s);
    }
    
    public Exp exp_litCad(String s){
        return new Exp_litCadena(s);
    }
    
    public Exp exp_iden(String s){
        return new Exp_iden(s);
    }
    
    public Exp exp_null(){
        return new Exp_null();
    }
}
