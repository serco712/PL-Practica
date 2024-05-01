package espacio;


import asint.SintaxisAbstractaTiny.*;

public class Espacio {
    public static class ErrorVinculacion extends RuntimeException {} 
    private int dir = 0;
	private int nivel = 0;


    public void asigEspacio(Prog p) {
        asigEspacio(p.bloq());
        dir = aux;
    }

    public void asigEspacio(Blo b) {
        int aux = dir;
        asigEspacio(b.decla());
        asigEspacio(b.instr());
        dir = aux;
    }

    public void asigEspacio(Si_decs d) {
        asigEspacio1(d.decs());
        asigEspacio2(d.decs());
    }
    
    public void asigEspacio(No_decs d) { }

    public void asigEspacio1(Muchas_decs d) {
        asigEspacio1(d.decs());
        asigEspacio1(d.dec());
    }

    public void asigEspacio2(Muchas_decs d) {
        asigEspacio2(d.decs());
        asigEspacio2(d.dec());
    }

    public void asigEspacio1(Una_dec d) {
        asigEspacio1(d.dec());
    }

    public void asigEspacio2(Una_dec d) {
        asigEspacio2(d.dec());
    }

    public void asigEspacio1(Muchas_var v) {
        asigEspacio1(v.vars());
        asigEspacio1(v.var());
    }

    public void asigEspacio2(Muchas_var v) {
        asigEspacio2(v.vars());
        asigEspacio2(v.var());
    }

    public void asigEspacio1(Una_var v) {
        asigEspacio1(v.vars());
    }

    public void asigEspacio2(Una_var v) {
        asigEspacio2(v.vars());
    }

    public void asigEspacio1(Var v) {
        v.dir = dir;
        v.nivel = nivel;
        asigEspacioTipo(v.tipo());
        dir = dir + v.tipo().tam;
    }

    public void asigEspacio2(Var v) {}

    public void asigEspacio1(Dec_simple d) {
        asigEspacio1(d.var());
    }

    public void asigEspacio2(Dec_simple d) {
        asigEspacio2(d.var());
    }

    public void asigEspacio1(Dec_type d) {
       asigEspacio1(d.var());
    }

    public void asigEspacio2(Dec_type d) {
        asigEspacio2(d.var());
    }

    public void asigEspacio1(Dec_proc d) {
       int aux = dir;
       nivel++;
       d.nivel = nivel;
       dir = 0;
       asigEspacio1(d.par_for());
       asigEspacio1(d.bloq());
       d.tam_datos = dir;
       dir = aux;
       nivel--;
    }

    public void asigEspacio2(Dec_proc d) {
        asigEspacio2(d.par_for());
        asigEspacio2(d.bloq());
    }

    public void asigEspacioTipo(Tipo t){
        if(t.tam == null){
            asigEspacioTipo1(t);
            asigEspacioTipo2(t);
        }

    }
    public void asigEspacioTipo1(Tipo_array t) {
        t.tam = t.litEnt().tam;
        if (t.tipo().getClass() != Tipo_ident.class) {
            asigEspacioTipo1(t);
        }
    }

    public void asigEspacio2(Tipo_array t) {
        if (t.tipo().getClass() == Tipo_ident.class) {
            if (t.tipo().getClass().getVinculo() == Dec_type.class){
               asigEspacioTipo(Dec_type.tam);
            }
            t.tam = t.litEnt().tam *Dec_type.tam;
        }
    }

    public void asigEspacioTipo1(Tipo_punt t) {
        t.tam = 1;
        if (t.tipo().getClass() != Tipo_ident.class)
            asigEspacioTipo1(t.tipo());
    }

    public void asigEspacio2(Tipo_punt t) {
        if (t.tipo().getClass() == Tipo_ident.class) {
            if (t.tipo().getClass().getVinculo() == Dec_type.class){
               asigEspacioTipo(Dec_type.tam);
            }
             t.tam = Dec_type.tam;
        }

    }

    public void asigEspacioTipo1(Tipo_bool t) {
        t.tam = 1;
     }

    public void asigEspacioTipo2(Tipo_bool t) { }

    public void asigEspacioTipo1(Tipo_int t) { 
        t.tam = 1;
    }

    public void asigEspacioTipo2(Tipo_int t) { }

    public void asigEspacioTipo1(Tipo_real t) { 
        t.tam = 1;
    }

    public void asigEspacioTipo2(Tipo_real t) { }

    public void asigEspacioTipo1(Tipo_string t) {
        t.tam = 1;
    }

    public void asigEspacioTipo2(Tipo_string t) { }

    public void asigEspacioTipo1(Tipo_ident t) {
        asigEspacioTipo1(t.getClass().getVinculo());
        if (t.getClass().getVinculo() != Dec_type.class){
            t.tam = Dec_type.tam;
        }
           
    }

    public void asigEspacioTipo2(Tipo_ident t) { }

    public void asigEspacio(Tipo_struct t) {
         asigEspacio(t.vars());
    }

    public void asigEspacio(Si_inst i) {
        asigEspacio(i.insts());
    }

    public void asigEspacio(No_inst i) { }

    public void asigEspacio(Muchas_inst i) {
        asigEspacio(i.insts());
        asigEspacio(i.inst());
    }

    public void asigEspacio(Una_inst i) {
        asigEspacio(i.inst());
    }

    public void asigEspacio1(Si_pformal pf) {
        asigEspacio1(pf.lpfml());
    }

    public void asigEspacio2(Si_pformal pf) {
        asigEspacio2(pf.lpfml());
    }

    public void asigEspacio1(No_pformal pf) {}

    public void asigEspacio2(No_pformal pf) {}

    public void asigEspacio1(Muchos_pformal pf) {
        asigEspacio1(pf.lpfml());
        asigEspacio1(pf.pfml());
    }

    public void asigEspacio2(Muchos_pformal pf) {
        asigEspacio2(pf.lpfml());
        asigEspacio2(pf.pfml());
    }

    public void asigEspacio1(Un_pformal pf) {
        asigEspacio1(pf.pfml());
    }

    public void asigEspacio2(Un_pformal pf) {
        asigEspacio2(pf.pfml());
    }

    public void asigEspacio1(Pformal_ref pf) {
        pf.dir = dir;
        pf.nivel = nivel;
        asigEspacioTipo(pf.tipo());
        dir = dir + pf.tipo().tam;
    }

    public void asigEspacio2(Pformal_ref pf) {
        asigEspacioTipo(pf.tipo());
    }

    public void asigEspacio1(Pformal_noref pf) {
        pf.dir = dir;
        pf.nivel = nivel;
        asigEspacioTipo(pf.tipo());
        dir = dir + pf.tipo().tam;
    }

    public void asigEspacio2(Pformal_noref pf) {
        asigEspacioTipo(pf.tipo());
    }

    public void asigEspacio(Si_preales pr) {
        asigEspacio(pr.lpr());
    }

    public void asigEspacio(No_preales pr) { }

    public void asigEspacio(Muchas_exp e) {
        asigEspacio(e.lpr());
        asigEspacio(e.exp());
    }

    public void asigEspacio(Una_exp e) {
        asigEspacio(e.exp());
    }

    public void asigEspacio(Inst_eval i) {}

    public void asigEspacio(Inst_if i) {
        asigEspacio(i.bloq());
    }

    public void asigEspacio(Inst_else i) {
        asigEspacio(i.bloq1());
        asigEspacio(i.bloq2());
    }

    public void asigEspacio(Inst_while i) {
        asigEspacio(i.bloq());
    }

    public void asigEspacio(Inst_new i) {}

    public void asigEspacio(Inst_delete i) {}

    public void asigEspacio(Inst_read i) {}

    public void asigEspacio(Inst_write i) {}

    public void asigEspacio(Inst_call i) {}

    public void asigEspacio(Inst_nl i) { }

    public void asigEspacio(Inst_blo i) {
        asigEspacio(i.bloq());
    }

    public void asigEspacio(Exp_asig exp) {
        asigEspacio(exp.exp1());
        asigEspacio(exp.exp2());
    }

    public void asigEspacio(Exp_menor exp) {
        asigEspacio(exp.exp1());
        asigEspacio(exp.exp2());
    }

    public void asigEspacio(Exp_menIgual exp) {
        asigEspacio(exp.exp1());
        asigEspacio(exp.exp2());
    }

    public void asigEspacio(Exp_mayor exp) {
        asigEspacio(exp.exp1());
        asigEspacio(exp.exp2());
    }

    public void asigEspacio(Exp_mayIgual exp) {
        asigEspacio(exp.exp1());
        asigEspacio(exp.exp2());
    }

    public void asigEspacio(Exp_igual exp) {
        asigEspacio(exp.exp1());
        asigEspacio(exp.exp2());
    }

    public void asigEspacio(Exp_dist exp) {
        asigEspacio(exp.exp1());
        asigEspacio(exp.exp2());
    }

    public void asigEspacio(Exp_suma exp) {
        asigEspacio(exp.exp1());
        asigEspacio(exp.exp2());
    }

    public void asigEspacio(Exp_resta exp) {
        asigEspacio(exp.exp1());
        asigEspacio(exp.exp2());
    }

    public void asigEspacio(Exp_mult exp) {
        asigEspacio(exp.exp1());
        asigEspacio(exp.exp2());
    }

    public void asigEspacio(Exp_div exp) {
        asigEspacio(exp.exp1());
        asigEspacio(exp.exp2());
    }

    public void asigEspacio(Exp_mod exp) {
        asigEspacio(exp.exp1());
        asigEspacio(exp.exp2());
    }

    public void asigEspacio(Exp_and exp) {
        asigEspacio(exp.exp1());
        asigEspacio(exp.exp2());
    }

    public void asigEspacio(Exp_or exp) {
        asigEspacio(exp.exp1());
        asigEspacio(exp.exp2());
    }

    public void asigEspacio(Exp_menos exp) {
        asigEspacio(exp.exp1());
    }

    public void asigEspacio(Exp_not exp) {
        asigEspacio(exp.exp1());
    }

    public void asigEspacio(Exp_index exp) {
        asigEspacio(exp.exp1());
        asigEspacio(exp.exp2());
    }

    public void asigEspacio(Exp_reg exp) {
        asigEspacio(exp.getClass().getVinculo());
        if (exp.getClass().getVinculo() == Tipo_struct.class){
            exp.tam = Tipo_struct.tam;
        }
    }

    public void asigEspacio(Exp_indir exp) {
        asigEspacio(exp.exp1());
        asigEspacio(exp.exp2());
    }

    public void asigEspacio(Exp_true exp) { }

    public void asigEspacio(Exp_false exp) { }

    public void asigEspacio(Exp_litEnt exp) { }

    public void asigEspacio(Exp_litReal exp) { }

    public void asigEspacio(Exp_litCadena exp) { }

    public void asigEspacio(Exp_iden exp) {
        asigEspacio(exp.getClass().getVinculo());
        if (exp.getClass().getVinculo() == Dec_type.class){
            exp.tam = Dec_type.tam;
        }
    }

    public void asigEspacio(Exp_null exp) { }

}


