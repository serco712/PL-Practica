package vinculacion;

import java.util.HashMap;
import java.util.Map;

import asint.SintaxisAbstractaTiny.*;

public class Vinculacion {
    public static class ErrorVinculacion extends RuntimeException {} 
    private class TablaValores {
        Map<String,Nodo> diccionario;
        TablaValores tv;

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

    public void vincula(Si_decs d) {
        vincula1(d.decs());
        vincula2(d.decs());
    }
    
    public void vincula(No_decs d) { }

    public void vincula1(Muchas_decs d) {
        vincula1(d.decs());
        vincula1(d.dec());
    }

    public void vincula2(Muchas_decs d) {
        vincula2(d.decs());
        vincula2(d.dec());
    }

    public void vincula1(Una_dec d) {
        vincula1(d.dec());
    }

    public void vincula2(Una_dec d) {
        vincula2(d.dec());
    }

    public void vincula1(Muchas_var v) {
        vincula1(v.vars());
        vincula1(v.var());
    }

    public void vincula2(Muchas_var v) {
        vincula2(v.vars());
        vincula2(v.var());
    }

    public void vincula1(Una_var v) {
        vincula1(v.vars());
    }

    public void vincula2(Una_var v) {
        vincula2(v.vars());
    }

    public void vincula2(Var v) {
        vincula2(v.tipo());
    }

    public void vincula1(Dec_simple d) {
        Var v = d.var();
        vincula1(v.tipo());
        if (ts.contiene(v.id()))
            throw new ErrorVinculacion();
        
        ts.inserta(v.id(), d);
    }

    public void vincula2(Dec_simple d) {
        vincula2(d.var());
    }

    public void vincula1(Dec_type d) {
        Var v = d.var();
        vincula1(v.tipo());
        if (ts.contiene(v.id()))
            throw new ErrorVinculacion();
        
        ts.inserta(v.id(), d);
    }

    public void vincula2(Dec_type d) {
        vincula2(d.var());
    }

    public void vincula1(Dec_proc d) {
        
    }

    public void vincula2(Dec_proc d) {
        
    }

    public void vincula1(Tipo_array t) {
        if (t.tipo().getClass() != Tipo_ident.class) {
            vincula1(t);
        }
    }

    public void vincula2(Tipo_array t) {
        if (t.tipo().getClass() == Tipo_ident.class) {
            Nodo n = ts.vinculoDe(s);
            if (n.getClass() != Dec_type.class)
                throw new ErrorVinculacion();
        }
        else
            vincula2(t.tipo());
    }

    public void vincula1(Tipo_punt t) {
        if (t.tipo() != Tipo_ident.class)
            vincula1(t.tipo());
    }

    public void vincula2(Tipo_punt t) {
        if (t.tipo() == Tipo_ident.class) {
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
        Nodo n = ts.vinculoDe(t.id());
        if (n.getClass() != Dec_type.class)
            throw new ErrorVinculacion();
    }

    public void vincula2(Tipo_ident t) { }

    public void vincula1(Tipo_struct t) {

    }

    public void vincula(Si_inst i) {
        vincula(i.insts());
    }

    public void vincula(No_inst i) { }

    public void vincula(Muchas_inst i) {
        vincula(i.insts());
        vincula(i.inst());
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
    }

    public void vincula(Exp_null exp) { }

}
