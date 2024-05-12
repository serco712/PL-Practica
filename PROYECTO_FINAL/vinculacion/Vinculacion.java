package vinculacion;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import asint.SintaxisAbstractaTiny.Tipado;
import asint.SintaxisAbstractaTiny.Tipo;
import asint.SintaxisAbstractaTiny.Tipo_array;
import asint.SintaxisAbstractaTiny.Tipo_ident;
import asint.SintaxisAbstractaTiny.Tipo_punt;
import asint.SintaxisAbstractaTiny.Tipo_struct;
import asint.SintaxisAbstractaTiny.Var;
import asint.SintaxisAbstractaTiny.Decs;
import asint.SintaxisAbstractaTiny.Exp;
import asint.SintaxisAbstractaTiny.Exp_and;
import asint.SintaxisAbstractaTiny.Exp_asig;
import asint.SintaxisAbstractaTiny.Exp_dist;
import asint.SintaxisAbstractaTiny.Exp_div;
import asint.SintaxisAbstractaTiny.Exp_iden;
import asint.SintaxisAbstractaTiny.Exp_igual;
import asint.SintaxisAbstractaTiny.Exp_index;
import asint.SintaxisAbstractaTiny.Exp_indir;
import asint.SintaxisAbstractaTiny.Exp_mayIgual;
import asint.SintaxisAbstractaTiny.Exp_mayor;
import asint.SintaxisAbstractaTiny.Exp_menIgual;
import asint.SintaxisAbstractaTiny.Exp_menor;
import asint.SintaxisAbstractaTiny.Exp_menos;
import asint.SintaxisAbstractaTiny.Exp_mod;
import asint.SintaxisAbstractaTiny.Exp_mult;
import asint.SintaxisAbstractaTiny.Exp_not;
import asint.SintaxisAbstractaTiny.Exp_or;
import asint.SintaxisAbstractaTiny.Exp_reg;
import asint.SintaxisAbstractaTiny.Exp_resta;
import asint.SintaxisAbstractaTiny.Exp_suma;
import asint.SintaxisAbstractaTiny.Inst;
import asint.SintaxisAbstractaTiny.Inst_blo;
import asint.SintaxisAbstractaTiny.Inst_call;
import asint.SintaxisAbstractaTiny.Inst_delete;
import asint.SintaxisAbstractaTiny.Inst_else;
import asint.SintaxisAbstractaTiny.Inst_eval;
import asint.SintaxisAbstractaTiny.Inst_if;
import asint.SintaxisAbstractaTiny.Inst_new;
import asint.SintaxisAbstractaTiny.Inst_read;
import asint.SintaxisAbstractaTiny.Inst_while;
import asint.SintaxisAbstractaTiny.Inst_write;
import asint.SintaxisAbstractaTiny.Insts;
import asint.SintaxisAbstractaTiny.LDecs;
import asint.SintaxisAbstractaTiny.LInst;
import asint.SintaxisAbstractaTiny.LPFml;
import asint.SintaxisAbstractaTiny.LPReal;
import asint.SintaxisAbstractaTiny.LVar;
import asint.SintaxisAbstractaTiny.Muchas_decs;
import asint.SintaxisAbstractaTiny.Muchas_exp;
import asint.SintaxisAbstractaTiny.Muchas_inst;
import asint.SintaxisAbstractaTiny.Muchas_var;
import asint.SintaxisAbstractaTiny.Muchos_pformal;
import asint.SintaxisAbstractaTiny.Nodo;
import asint.SintaxisAbstractaTiny.PFml;
import asint.SintaxisAbstractaTiny.PFmls;
import asint.SintaxisAbstractaTiny.PReales;
import asint.SintaxisAbstractaTiny.Pformal_noref;
import asint.SintaxisAbstractaTiny.Pformal_ref;
import asint.SintaxisAbstractaTiny.Blo;
import asint.SintaxisAbstractaTiny.Dec;
import asint.SintaxisAbstractaTiny.Dec_proc;
import asint.SintaxisAbstractaTiny.Dec_simple;
import asint.SintaxisAbstractaTiny.Dec_type;
import asint.SintaxisAbstractaTiny.Prog;
import asint.SintaxisAbstractaTiny.Si_decs;
import asint.SintaxisAbstractaTiny.Si_inst;
import asint.SintaxisAbstractaTiny.Si_pformal;
import asint.SintaxisAbstractaTiny.Si_preales;
import asint.Procesamiento;

public class Vinculacion {

    public static class ErrorVinculacion extends RuntimeException {} 

    private class TablaValores {
        private Map<String,Nodo> diccionario;
        private Stack<Map<String,Nodo>> tv;

        public TablaValores() { 
            tv = new Stack<>();
            diccionario = new HashMap<>();
        }
        public void inserta(String s, Nodo n) {
            diccionario.put(s, n);
        }
        public boolean contiene(String s) {
            return diccionario.containsKey(s);
        }
        public void abreAmbito() {
            tv.push(diccionario);
        }
        public void cierraAmbito() {
            diccionario = tv.pop();
        }
        public Map<String,Nodo> devuelveAmbito() {
            return diccionario;
        }
        public Nodo vinculoDe(String s) {
            return diccionario.get(s);
        }
        public void printDicc() {
            for (String id : diccionario.keySet()) {
                System.out.println(id);
            }
        }
    }


    TablaValores ts;


    public void vincula(Prog p) {
        ts = new TablaValores();
        vincula(p.bloq());
    }

    public void vincula(Blo b) {
        ts.abreAmbito();
        vincula(b.decla());
        vincula(b.instr());
        //ts.printDicc();
        ts.cierraAmbito();
    }

    public void vincula(Decs d) {
        if (claseDe(d, Si_decs.class)) {
            vincula1(d.decs());
            vincula2(d.decs());
        }
    }

    public void vincula1(LDecs d) {
        if (claseDe(d, Muchas_decs.class))
            vincula1(d.decs());
        vincula1(d.dec());
    }

    public void vincula2(LDecs d) {
        if (claseDe(d, Muchas_decs.class))
            vincula2(d.decs());
        vincula2(d.dec());
    }

    public void vincula1(LVar v) {
        if (claseDe(v, Muchas_var.class))
            vincula1(v.vars());
        vincula1(v.var());
    }

    public void vincula2(LVar v) {
        if (claseDe(v, Muchas_var.class))
            vincula2(v.vars());
        vincula2(v.var());
    }

    public void vincula1(Var v) {
    	if (!ts.contiene(v.id())) {
            //throw new ErrorVinculacion();
            ts.inserta(v.id(), v);
        }
        vincula1(v.tipo());
    }

    public void vincula2(Var v) {
        vincula2(v.tipo());
    }

    public void vincula1(Dec d) {
        if (claseDe(d, Dec_simple.class) || claseDe(d, Dec_type.class)) {
            Var v = d.var();
            if (!ts.contiene(v.id())) {
                //throw new ErrorVinculacion();
	            ts.inserta(v.id(), d);
            }
            vincula1(v.tipo());
        }
        else {
            if (!ts.contiene(d.id())) {
               // throw new ErrorVinculacion();
            	ts.inserta(d.id(), d);  
            }
        }
    }

    public void vincula2(Dec d) {
        if (claseDe(d, Dec_simple.class) || claseDe(d, Dec_type.class)) {
            vincula2(d.var());
        }
        else {
            ts.abreAmbito();
            vincula1(d.par_for());
            vincula2(d.par_for());
            vincula(d.bloq());
            ts.cierraAmbito();
        }
    }

    public void vincula1(Tipo t) {
        if (claseDe(t, Tipo_array.class) || claseDe(t, Tipo_punt.class)) {
            if (!claseDe(t.tipo(), Tipo_ident.class)) {
                vincula1(t);
            }
        }
        else if (claseDe(t, Tipo_struct.class)) {
        	Tipo_struct t1 = (Tipo_struct)t;
            ts.abreAmbito();
            vincula1(t.lvar());
            vincula2(t.lvar());
            t1.vincula(ts.devuelveAmbito());
            ts.cierraAmbito();
        }
        else if (claseDe(t, Tipo_ident.class)){
        	Tipo_ident t1 = (Tipo_ident)t;
            Nodo n = ts.vinculoDe(t.id());
            if (n.getClass() != Dec_type.class)
                throw new ErrorVinculacion();
            t1.vincula(n);
        }
    }


    public void vincula2(Tipo t) {
        if (claseDe(t, Tipo_array.class) || claseDe(t, Tipo_punt.class)) {
            if (claseDe(t.tipo(), Tipo_ident.class)) {
                Nodo n = ts.vinculoDe(t.id());
                if (n.getClass() != Dec_type.class)
                    throw new ErrorVinculacion();
            }
            else
                vincula2(t.tipo());
        }
    }

    public void vincula(Insts i) {
        if(claseDe(i, Si_inst.class)){
            vincula(i.insts());
        }
    }

    public void vincula(LInst i) {
        if(claseDe(i, Muchas_inst.class)){
            vincula(i.insts());
        }
        vincula(i.inst());
    }

    public void vincula1(PFmls pf) {
        if(claseDe(pf, Si_pformal.class)){
            vincula1(pf.lpfml());
        }
    }

    public void vincula2(PFmls pf) {
        if(claseDe(pf, Si_pformal.class)){
            vincula2(pf.lpfml());
        }
    }

    public void vincula1(LPFml pf) {
        if(claseDe(pf, Muchos_pformal.class)){
            vincula1(pf.lpfml());
        }
        vincula1(pf.pfml());
    }

    public void vincula2(LPFml pf) {
        if(claseDe(pf, Muchos_pformal.class)){
            vincula2(pf.lpfml());
        }
        vincula2(pf.pfml());
    }
    
     public void vincula1(PFml pf) {
        if(claseDe(pf, Pformal_ref.class) || claseDe(pf, Pformal_noref.class)){
        	if (!ts.contiene(pf.id())) {
                //throw new ErrorVinculacion();
        		ts.inserta(pf.id(), pf);
        	}
	        vincula1(pf.tipo());        
        }
    }

	public void vincula2(PFml pf) {
        if(claseDe(pf, Pformal_ref.class) || claseDe(pf, Pformal_noref.class)){
	        vincula2(pf.tipo());
        }
    }

    public void vincula(PReales pr) {
        if(claseDe(pr, Si_preales.class) ){
        	 vincula(pr.lpr());
        }
    }

    public void vincula(LPReal e) {
        if(claseDe(e, Muchas_exp.class) ){
        	vincula(e.lpr());
        } 
        vincula(e.exp());
    }

     public void vincula(Inst i) {
        if(claseDe(i, Inst_if.class)|| claseDe(i, Inst_while.class)){
        	vincula(i.exp());
            vincula(i.bloq());
        }
        else if(claseDe(i, Inst_blo.class)){
            vincula(i.bloq());
        }
        else if(claseDe(i, Inst_else.class) ){
            vincula(i.exp());
            vincula(i.bloq1());
        	vincula(i.bloq2());
        }
        else if(claseDe(i, Inst_eval.class) || claseDe(i, Inst_new.class) || claseDe(i, Inst_delete.class) 
        || claseDe(i, Inst_read.class) || claseDe(i, Inst_write.class) ){
            vincula(i.exp());
        }
        else if(claseDe(i,Inst_call.class)){
            Nodo n = ts.vinculoDe(i.id());
            if (n.getClass() != Dec_proc.class)
                throw new ErrorVinculacion();
            i.vincula(n);
            vincula(i.pr());
        }
    }

    public void vincula(Exp exp) {
         if(claseDe(exp, Exp_asig.class)|| claseDe(exp, Exp_menor.class) || claseDe(exp, Exp_menIgual.class)
         || claseDe(exp, Exp_mayor.class) || claseDe(exp, Exp_mayIgual.class) || claseDe(exp, Exp_igual.class)
         || claseDe(exp, Exp_dist.class) || claseDe(exp, Exp_suma.class) || claseDe(exp, Exp_resta.class)
         || claseDe(exp, Exp_mult.class)|| claseDe(exp, Exp_div.class)|| claseDe(exp, Exp_mod.class)
         || claseDe(exp, Exp_and.class) || claseDe(exp, Exp_or.class)|| claseDe(exp, Exp_index.class)
         || claseDe(exp, Exp_indir.class)){
                vincula(exp.exp1());
        		vincula(exp.exp2());
          }
          else if(claseDe(exp, Exp_menos.class) || claseDe(exp, Exp_not.class) ){
               vincula(exp.exp1());
          }
          else if(claseDe(exp, Exp_reg.class)){
			    vincula(exp.exp1());
                Nodo n = ts.vinculoDe(exp.id());
                if (n.getClass() != Tipo_struct.class)
                    throw new ErrorVinculacion();
                }
          else if(claseDe(exp, Exp_iden.class)) {
        	    Nodo n = ts.vinculoDe(exp.id());
                if (!claseDe(n, Dec_simple.class))
                    throw new ErrorVinculacion();
                exp.vincula(n);
          }
    }

    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    } 

}
