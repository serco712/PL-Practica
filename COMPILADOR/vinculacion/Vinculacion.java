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

}
