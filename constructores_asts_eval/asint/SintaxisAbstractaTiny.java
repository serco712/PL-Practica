package asint;

public class SintaxisAbstractaTiny {

    public static abstract class Nodo  {
       public Nodo() {
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
	   public int leeFila() {
		  return fila; 
	   }
	   public int leeCol() {
		  return col; 
	   }
    }

    public static abstract class Blo extends Nodo {
        protected Decs decla;
        protected Insts instr;
        public Blo(Decs d, Insts i) {
            super();
            decla = d;
            instr = i;
        }
    }
    
    public static abstract class Decs extends Nodo {
        public Decs() {
            super();
        }
    }

    public static abstract class LDecs extends Nodo {
        public LDecs() {
            super();
        }
    }

    public static abstract class LVar extends Nodo {
        public LVar() {
            super();
        }
    }

    public abstract static class Dec extends Nodo {
        public Dec() {
            super();
        }
    }

    public static abstract class Tipo extends Nodo {
        public Tipo() {
            super();
        }
    }
    
    public static abstract class Insts extends Nodo {
        public Insts() {
            super();
        }
    }

    public static abstract class LInst extends Nodo {
        public LInst() {
            super();
        }
    }

    public static abstract class PFmls extends Nodo {
        public PFmls() {
            super();
        }
    }

    public static abstract class LPFml extends Nodo {
        public LPFml() {
            super();
        }
    }

    public static abstract class PFml extends Nodo {
        public PFml() {
            super();
        }
    }

    public static abstract class PReales extends Nodo {
        public PReales() {
            super();
        }
    }

    public static abstract class LPReal extends Nodo {
        public LPReal() {
            super();
        }
    }

    public static abstract class Inst extends Nodo {
        public Inst() {
            super();
        }
    }

    public static abstract class Exp  extends  Nodo {
       public Exp() {
		   super();
       }   
    }
   
   // ----------------------------------------------------

    private static abstract class ExpBin extends Exp {
        protected Exp opnd0;
        protected Exp opnd1;
        public ExpBin(Exp opnd0, Exp opnd1) {
            super();
            this.opnd0 = opnd0;
            this.opnd1 = opnd1;
        }

    }

    public static class Prog extends Nodo {
        private Blo b;
        public Prog(Blo b) {
            super();
            this.b = b;
        }   
        public String toString() {
             return "prog("+b+")";
         } 
    }
    
    
    public static class Bloq extends Blo {
        public Bloq(Decs decla, Insts instr) {
            super(decla, instr);
        }
        public String toString() {
            return "bloq("+decla+","+instr+")";
        }
    }
    
    public static class Si_decs extends Decs {
       private LDecs decs; 
       public Si_decs(LDecs decs) {
          super();
          this.decs = decs;
       }   
        public String toString() {
            return "si_decs("+decs+")";
        } 
    }
    
    public static class No_decs extends Decs {
       public No_decs() {
          super();
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
       public String toString() {
            return "una_dec("+dec+")";
        } 
    } 
    
    public static class Muchas_var extends LVar {
       private LVar vars;
       private Dec dec;
       public Muchas_var(LVar vars, Dec dec) {
          super();
          this.dec = dec;
          this.vars = vars;
       }
       public String toString() {
            return "muchas_var("+vars+","+dec+")";
        } 
    }

    public static class Una_var extends LVar {
       private Dec dec;
       public Una_var(Dec dec) {
          super();
          this.dec = dec;
       }
       public String toString() {
            return "una_var("+dec+")";
        } 
    } 
    
     public static class Dec_simple extends Dec {
       private Tipo tipo;
       private String str;
       public Dec_simple(Tipo tipo, String str) {
          super();
          this.tipo = tipo;
          this.str = str;
       }
       public String toString() {
            return "dec_simple("+tipo+","+str+")";
        } 
    }   
    
    public static class Dec_type extends Dec {
       private Tipo tipo;
       private String str;
       public Dec_type(Tipo tipo, String str) {
          super();
          this.tipo = tipo;
          this.str = str;
       }
       public String toString() {
            return "dec_type("+tipo+","+str+")";
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
       }
       public String toString() {
            return "dec_proc("+str+","+parfor+","+bloq+")";
        } 
    }
    
    public static class Tipo_array extends Tipo {
      private Tipo tipo;
      private String str;
      public Tipo_array(Tipo tipo, String str) {
          super();
          this.tipo = tipo;
          this.str = str;
      }
      public String toString() {
            return "tipo_array("+tipo+","+str+")";
      } 
    }
         
    public static class Tipo_punt extends Tipo {
      private Tipo tipo;
      public Tipo_punt(Tipo tipo) {
          super();
          this.tipo = tipo;
      }
      public String toString() {
            return "tipo_array("+tipo+")";
      } 
    }
    
    public static class Tipo_bool extends Tipo {
      public Tipo_bool() {
          super();
      }
      public String toString() {
            return "tipo_bool()";
      } 
    }

	public static class Tipo_int extends Tipo {
      public Tipo_int() {
          super();
      }
      public String toString() {
            return "tipo_int()";
      } 
    }
    
    public static class Tipo_real extends Tipo {
      public Tipo_real() {
          super();
      }
      public String toString() {
            return "tipo_real()";
      } 
    }

	public static class Tipo_string extends Tipo {
      public Tipo_string() {
          super();
      }
      public String toString() {
            return "tipo_string()";
      } 
    }
    
    public static class Tipo_ident extends Tipo {
      private String ident;
      public Tipo_ident(String ident) {
          super();
          this.ident = ident;
      }
      public String toString() {
            return "tipo_ident("+ident+")";
      } 
    }
    
    public static class Tipo_struct extends Tipo {
      private LVar lvar;
      public Tipo_struct(LVar lvar) {
          super();
          this.lvar = lvar;
      }
      public String toString() {
            return "tipo_struct("+lvar+")";
      } 
    }
    
    public static class Si_inst extends Insts {
       private LInst insts;
       public Si_inst(LInst insts) {
          super();
          this.insts = insts;
       }
       public String toString() {
            return "si_inst("+insts+")";
        } 
    }
    
    public static class No_inst extends Insts {
       public No_inst() {
          super();
       }
       public String toString() {
            return "no_inst()";
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
       public String toString() {
            return "muchas_inst("+insts+","+inst+")";
        } 
    }

    public static class Una_inst extends LInst {
       private Inst inst;
       public Una_inst(Inst inst) {
          super();
          this.inst = inst;
       }
       public String toString() {
            return "una_inst("+inst+")";
        } 
    } 
    
    public static class Si_pformal extends PFmls {
       private LPFml lpfml;
       public Si_pformal(LPFml lpfml) {
          super();
          this.lpfml = lpfml;
       }
       public String toString() {
            return "si_pformal("+lpfml+")";
        } 
    }
    
    public static class No_pformal extends PFmls {
       public No_pformal() {
          super();
       }
       public String toString() {
            return "no_pformal()";
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
       public String toString() {
            return "muchas_pformal("+pfml+","+lpfml+")";
        } 
    }

    public static class Un_pformal extends LPFml {
       private PFml pfml;
       public Un_pformal(PFml pfml) {
          super();
          this.pfml = pfml;
       }
       public String toString() {
            return "una_pformal("+pfml+")";
        } 
    }
    
    public static class Pformal_ref extends PFml {
       private Tipo tipo;
       private String str;
       public Pformal_ref(Tipo tipo, String str) {
          super();
          this.tipo = tipo;
          this.str = str;
       }
       public String toString() {
            return "pformal_ref("+tipo+","+str+")";
        } 
    }
    
    public static class Pformal_noref extends PFml {
        private Tipo tipo;
        private String str;
        public Pformal_noref(Tipo tipo, String str) {
           super();
           this.tipo = tipo;
           this.str = str;
        }
        public String toString() {
             return "pformal_noref("+tipo+","+str+")";
         } 
    }

    public static class Si_preales extends PReales {
        private LPReal lpr;
        public Si_preales(LPReal lpr) {
           super();
           this.lpr = lpr;
        }
        public String toString() {
             return "si_preales("+lpr+")";
        } 
    }

    public static class No_preales extends PReales {
        public No_preales() {
           super();
        }
        public String toString() {
             return "no_preales()";
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
        public String toString() {
             return "una_exp("+e+","+lpr+")";
        } 
    }

    public static class Una_exp extends LPReal {
        private Exp e;
        public Una_exp(Exp e) {
           super();
           this.e = e;
        }
        public String toString() {
             return "una_exp("+e+")";
        } 
    }

    public static class Inst_eval extends Inst {
        private Exp e;
        public Inst_eval(Exp e) {
            super();
            this.e = e;
        }

        public String toString() {
            return "inst_eval("+e+")";
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

        public String toString() {
            return "inst_if("+e+","+b+")";
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

        public String toString() {
            return "inst_else("+e+","+b1+","+b2+")";
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

        public String toString() {
            return "inst_while("+e+","+b+")";
        } 
    }

    public static class Inst_new extends Inst {
        private Exp e;
        public Inst_new(Exp e) {
            super();
            this.e = e;
        }

        public String toString() {
            return "inst_new("+e+")";
        } 
    }

    public static class Inst_delete extends Inst {
        private Exp e;
        public Inst_delete(Exp e) {
            super();
            this.e = e;
        }

        public String toString() {
            return "inst_delete("+e+")";
        } 
    }

    public static class Inst_read extends Inst {
        private Exp e;
        
        public Inst_read(Exp e) {
            super();
            this.e = e;
        }

        public String toString() {
            return "inst_read("+e+")";
        } 
    }

    public static class Inst_write extends Inst {
        private Exp e;
        
        public Inst_write(Exp e) {
            super();
            this.e = e;
        }

        public String toString() {
            return "inst_write("+e+")";
        } 
    } 

    public static class Inst_call extends Inst {
        private String id;
        private PReales pr;
        
        public Inst_call(String iden, PReales p) {
            super();
            id = iden;
            pr = p;
        }

        public String toString() {
            return "inst_call("+id+","+pr+")";
        } 
    } 
    
    public static class Inst_nl extends Inst {
        
        public Inst_nl() {
            super();
        }

        public String toString() {
            return "inst_nl()";
        } 
    } 

    public static class Inst_blo extends Inst {
        private Blo b;
        public Inst_blo(Blo b) {
            super();
            this.b = b;
        }

        public String toString() {
            return "inst_blo("+b+")";
        } 
    }
    
    public static class Lit_ent extends Exp {
        private String num;
        public Lit_ent(String num) {
            super();
            this.num = num;
        }
        public String toString() {
            return "lit_ent("+num+"["+leeFila()+","+leeCol()+"])";
        } 
    }

    public static class Lit_real extends Exp {
        private String num;
        public Lit_real(String num) {
            super();
            this.num = num;
        }
        public String toString() {
            return "lit_real("+num+"["+leeFila()+","+leeCol()+"])";
        } 
    }
	
	
    public static class Iden extends Exp {
        private String id;
        public Iden(String id) {
            super();
            this.id = id;
        }
        public String toString() {
            return "iden("+id+"["+leeFila()+","+leeCol()+"])";
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
        public String toString() {
            return "exp_asig("+exp1+","+exp2+")";
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
        public String toString() {
            return "exp_menor("+exp1+","+exp2+")";
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
        public String toString() {
            return "exp_menIgual("+exp1+","+exp2+")";
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
        public String toString() {
            return "exp_mayor("+exp1+","+exp2+")";
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
        public String toString() {
            return "exp_mayIgual("+exp1+","+exp2+")";
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
        public String toString() {
            return "exp_igual("+exp1+","+exp2+")";
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
        public String toString() {
            return "exp_dist("+exp1+","+exp2+")";
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
        public String toString() {
            return "exp_suma("+exp1+","+exp2+")";
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
        public String toString() {
            return "exp_resta("+exp1+","+exp2+")";
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
        public String toString() {
            return "exp_mult("+exp1+","+exp2+")";
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
        public String toString() {
            return "exp_div("+exp1+","+exp2+")";
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
        public String toString() {
            return "exp_mod("+exp1+","+exp2+")";
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
        public String toString() {
            return "exp_and("+exp1+","+exp2+")";
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
        public String toString() {
            return "exp_or("+exp1+","+exp2+")";
        }  
     }
     
    public static class Exp_menos extends Exp{
        private Exp exp;
        public Exp_menos(Exp exp){
            super();
            this.exp = exp;
        }
        public String toString() {
            return "exp_menos("+exp+")";
        }  
     }
     
    public static class Exp_not extends Exp{
        private Exp exp;
        public Exp_not(Exp exp){
            super();
            this.exp = exp;
        }
        public String toString() {
            return "exp_not("+exp+")";
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
        public String toString() {
            return "exp_index("+exp1+","+exp2+")";
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
        public String toString() {
            return "exp_reg("+exp+","+s+")";
        } 
    }
    
    public static class Exp_indir extends Exp{
        private Exp exp;
        public Exp_indir(Exp exp){
            super();
            this.exp = exp;
        }
        public String toString() {
            return "exp_indir("+exp+")";
        } 
    }
    
    public static class Exp_true extends Exp{
        public Exp_true(){
 			super();
        }
        public String toString() {
            return "exp_true()";
        } 
    }
    
    public static class Exp_false extends Exp{
        public Exp_false(){
 			super();
        }
        public String toString() {
            return "exp_false()";
        } 
    }
    
    public static class Exp_litEnt extends Exp{
        private String s;
        public Exp_litEnt(String s){
            super();
            this.s = s;
        }
        public String toString() {
            return "exp_litEnt("+s+")";
        } 
    }
    
     public static class Exp_litReal extends Exp{
        private String s;
        public Exp_litReal(String s){
            super();
            this.s = s;
        }
        public String toString() {
            return "exp_litReal("+s+")";
        } 
    }
    
     public static class Exp_litCadena extends Exp{
        private String s;
        public Exp_litCadena(String s){
            super();
            this.s = s;
        }
        public String toString() {
            return "exp_litCad("+s+")";
        } 
    }
    
    public static class Exp_iden extends Exp{
        private String s;
        public Exp_iden(String s){
            super();
            this.s = s;
        }
        public String toString() {
            return "exp_iden("+s+")";
        } 
    }
    
	public static class Exp_null extends Exp{
        private Exp exp;
        public Exp_null(Exp exp){
            super();
            this.exp = exp;
        }
        public String toString() {
            return "exp_null("+exp+")";
        } 
    }
    
     // Constructoras    
    public Prog prog(Blo b) {
        return new Prog(b);
    }
    public Blo bloq(Decs d, Insts i) {
        return new Bloq(d,i);
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
    public LVar muchas_var(LVar vars, Dec dec) {
        return new Muchas_var(vars,dec);
    }
    public LVar una_var(Dec dec) {
        return new Una_var(dec);
    }
    public Dec dec_simple(Tipo t, String id) {
        return new Dec_simple(t,id);
    }
    public Dec dec_type(Tipo t, String id) {
        return new Dec_type(t,id);
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
    
    public Exp exp_null(Exp exp){
        return new Exp_null(exp);
    }
}
