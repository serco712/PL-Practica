package vinculacion;

import java.util.HashMap;
import java.util.Map;

import asint.SintaxisAbstractaTiny.Tipado;
import asint.SintaxisAbstractaTiny.Tipo;
import asint.SintaxisAbstractaTiny.Tipo_array;
import asint.SintaxisAbstractaTiny.Tipo_ident;
import asint.SintaxisAbstractaTiny.Tipo_punt;
import asint.SintaxisAbstractaTiny.Tipo_struct;
import asint.SintaxisAbstractaTiny.Var;
import asint.SintaxisAbstractaTiny.Decs;
import asint.SintaxisAbstractaTiny.LDecs;
import asint.SintaxisAbstractaTiny.LVar;
import asint.SintaxisAbstractaTiny.Muchas_decs;
import asint.SintaxisAbstractaTiny.Muchas_var;
import asint.SintaxisAbstractaTiny.Nodo;
import asint.SintaxisAbstractaTiny.Blo;
import asint.SintaxisAbstractaTiny.Dec;
import asint.SintaxisAbstractaTiny.Dec_simple;
import asint.SintaxisAbstractaTiny.Dec_type;
import asint.SintaxisAbstractaTiny.Prog;
import asint.SintaxisAbstractaTiny.Si_decs;
import asint.Procesamiento;

public class Vinculacion {

    public static class ErrorVinculacion extends RuntimeException {} 

    private class TablaValores {
        private Map<String,Nodo> diccionario;
        private TablaValores tv;

        public TablaValores() { 
            diccionario = new HashMap<>(); 
            tv = null;
        }
        public TablaValores(TablaValores tv) { 
            diccionario = new HashMap<>(); 
            this.tv = tv;
        }
        public void inserta(String s, Nodo n) {
            diccionario.put(s, n);
        }
        public boolean contiene(String s) {
            return diccionario.containsKey(s);
        }
        public TablaValores abreAmbito() {
            return new TablaValores(this);
        }
        public TablaValores cierraAmbito() {
            return tv;
        }
        public Map<String,Nodo> devuelveAmbito() {
            return diccionario;
        }
        public Nodo vinculoDe(String s) {
            return diccionario.get(s);
        }
    }


    TablaValores ts;


    public void vincula(Prog p) {
        ts = new TablaValores();
        vincula(p.bloq());
    }

    public void vincula(Blo b) {
        ts = ts.abreAmbito();
        vincula(b.decla());
        vincula(b.instr());
        ts = ts.cierraAmbito();
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
        vincula1(v.tipo());
        if (ts.contiene(v.id()))
            throw new ErrorVinculacion();
        
        ts.inserta(v.id(), v);
    }

    public void vincula2(Var v) {
        vincula2(v.tipo());
    }

    public void vincula1(Dec d) {
        if (claseDe(d, Dec_simple.class) || claseDe(d, Dec_type.class)) {
            Var v = d.var();
            vincula1(v.tipo());
            if (ts.contiene(v.id()))
                throw new ErrorVinculacion();
            
            ts.inserta(v.id(), d);
        }
        else {
            if (ts.contiene(d.id()))
                throw new ErrorVinculacion();

            ts.inserta(d.id(), d);   
        }
    }

    public void vincula2(Dec d) {
        if (claseDe(d, Dec_simple.class) || claseDe(d, Dec_type.class)) {
            vincula2(d.var());
        else {
            ts.abreAmbito();
            vincula1(d.par_for());
            vincul2(d.par_for());
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
            ts.abreAmbito();
            vincula1(t.lvar());
            vincula2(t.lvar());
            t.vincula(ts.devuelveAmbito());
            ts.cierraAmbito();
        }
    }

    public void vincula2(Tipo_array t) {
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


    public void vincula2(Tipo_punt t) {
        if (t.tipo().getClass() == Tipo_ident.class) {
            Nodo n = ts.vinculoDe(t.id());
            if (n.getClass() != Dec_type.class)
                throw new ErrorVinculacion();
        }
        else
            vincula2(t.tipo());

    }

    public void vincula1(Tipo_bool t) { }

    public void vincula2(Tipo_bool t) { }

    public void vincula1(Tipo_int t) { }

    public void vincula2(Tipo_int t) { }

    public void vincula1(Tipo_real t) { }

    public void vincula2(Tipo_real t) { }

    public void vincula1(Tipo_string t) { }

    public void vincula2(Tipo_string t) { }

    public void vincula1(Tipo_ident t) {
        
    }

    public void vincula2(Tipo_ident t) {
        Nodo n = ts.vinculoDe(t.id());
        if (n.getClass() != Dec_type.class)
            throw new ErrorVinculacion();
        t.vincula(n);
    }

    public void vincula1(Tipo_struct t) {
        ts.abreAmbito();
        vincula1(t.lvar());
        vincula2(t.lvar());
        t.vincula(ts.devuelveAmbito());
        ts.cierraAmbito();
    }

    public void vincula(Si_inst i) {
        vincula(i.insts());
    }

    public void vincula(No_inst i) { }

    public void vincula(Muchas_inst i) {
        vincula(i.insts());
        vincula(i.inst());
    }

    public void vincula(Una_inst i) {
        vincula(i.inst());
    }

    public void vincula1(Si_pformal pf) {
        vincula1(pf.lpfml());
    }

    public void vincula2(Si_pformal pf) {
        vincula2(pf.lpfml());
    }

    public void vincula1(Muchos_pformal pf) {
        vincula1(pf.lpfml());
        vincula1(pf.pfml());
    }

    public void vincula2(Muchos_pformal pf) {
        vincula2(pf.lpfml());
        vincula2(pf.pfml());
    }

    public void vincula1(Un_pformal pf) {
        vincula1(pf.pfml());
    }

    public void vincula2(Un_pformal pf) {
        vincula2(pf.pfml());
    }

    public void vincula1(Pformal_ref pf) {
        vincula1(pf.tipo());
        if (ts.contiene(pf.id()))
            throw new ErrorVinculacion();
        
        ts.inserta(pf.id(), pf);
    }

    public void vincula2(Pformal_ref pf) {
        vincula2(pf.tipo());
    }

    public void vincula1(Pformal_noref pf) {
        vincula1(pf.tipo());
        if (ts.contiene(pf.id()))
            throw new ErrorVinculacion();
        
        ts.inserta(pf.id(), pf);
    }

    public void vincula2(Pformal_noref pf) {
        vincula2(pf.tipo());
    }

    public void vincula(Si_preales pr) {
        vincula(pr.lpr());
    }

    public void vincula(No_preales pr) { }

    public void vincula(Muchas_exp e) {
        vincula(e.lpr());
        vincula(e.exp());
    }

    public void vincula(Una_exp e) {
        vincula(e.exp());
    }

    public void vincula(Inst_eval i) {
        vincula(i.exp());
    }

    public void vincula(Inst_if i) {
        vincula(i.exp());
        vincula(i.bloq());
    }

    public void vincula(Inst_else i) {
        vincula(i.exp());
        vincula(i.bloq1());
        vincula(i.bloq2());
    }

    public void vincula(Inst_while i) {
        vincula(i.exp());
        vincula(i.bloq());
    }

    public void vincula(Inst_new i) {
        vincula(i.exp());
    }

    public void vincula(Inst_delete i) {
        vincula(i.exp());
    }

    public void vincula(Inst_read i) {
        vincula(i.exp());
    }

    public void vincula(Inst_write i) {
        vincula(i.exp());
    }

    public void vincula(Inst_call i) {
        Nodo n = ts.vinculoDe(t.id());
        if (n.getClass() != Dec_proc.class)
            throw new ErrorVinculacion();
        i.vincula(n);
        vincula(i.pr());
    }

    public void vincula(Inst_nl i) { }

    public void vincula(Inst_blo i) {
        vincula(i.bloq());
    }

    public void vincula(Exp_asig exp) {
        vincula(exp.exp1());
        vincula(exp.exp2());
    }

    public void vincula(Exp_menor exp) {
        vincula(exp.exp1());
        vincula(exp.exp2());
    }

    public void vincula(Exp_menIgual exp) {
        vincula(exp.exp1());
        vincula(exp.exp2());
    }

    public void vincula(Exp_mayor exp) {
        vincula(exp.exp1());
        vincula(exp.exp2());
    }

    public void vincula(Exp_mayIgual exp) {
        vincula(exp.exp1());
        vincula(exp.exp2());
    }

    public void vincula(Exp_igual exp) {
        vincula(exp.exp1());
        vincula(exp.exp2());
    }

    public void vincula(Exp_dist exp) {
        vincula(exp.exp1());
        vincula(exp.exp2());
    }

    public void vincula(Exp_suma exp) {
        vincula(exp.exp1());
        vincula(exp.exp2());
    }

    public void vincula(Exp_resta exp) {
        vincula(exp.exp1());
        vincula(exp.exp2());
    }

    public void vincula(Exp_mult exp) {
        vincula(exp.exp1());
        vincula(exp.exp2());
    }

    public void vincula(Exp_div exp) {
        vincula(exp.exp1());
        vincula(exp.exp2());
    }

    public void vincula(Exp_mod exp) {
        vincula(exp.exp1());
        vincula(exp.exp2());
    }

    public void vincula(Exp_and exp) {
        vincula(exp.exp1());
        vincula(exp.exp2());
    }

    public void vincula(Exp_or exp) {
        vincula(exp.exp1());
        vincula(exp.exp2());
    }

    public void vincula(Exp_menos exp) {
        vincula(exp.exp1());
    }

    public void vincula(Exp_not exp) {
        vincula(exp.exp1());
    }

    public void vincula(Exp_index exp) {
        vincula(exp.exp1());
        vincula(exp.exp2());
    }

    public void vincula(Exp_reg exp) {
        vincula(exp.exp1());
        Nodo n = ts.vinculoDe(exp.id());
        if (n.getClass() != Tipo_struct.class)
            throw new ErrorVinculacion();
    }

    public void vincula(Exp_indir exp) {
        vincula(exp.exp1());
        vincula(exp.exp2());
    }

    public void vincula(Exp_true exp) { }

    public void vincula(Exp_false exp) { }

    public void vincula(Exp_litEnt exp) { }

    public void vincula(Exp_litReal exp) { }

    public void vincula(Exp_litCadena exp) { }

    public void vincula(Exp_iden exp) {
        Nodo n = ts.vinculoDe(n.id());
        if (n.getClass() != Dec_simple.class)
            throw new ErrorVinculacion();

        exp.vincula(n);
    }

    public void vincula(Exp_null exp) { }

    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    } 

}
