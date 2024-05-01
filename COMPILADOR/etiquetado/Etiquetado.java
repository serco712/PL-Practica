public class Etiquetado implements Procesamiento{
    private int etq = 0;
    public void procesa(Prog prog) {
        prog.bloq().procesa(this);
    }

    public void procesa(Bloq bloq) {
        bloq.prim = etq;
        prog.insts().procesa(this);
        etq++;
        while(1){
            //TODO Ver despues como hacer esto
            prog.decs().procesa(this);
        }
        prog.sig = etq;
    }
    public void procesa(Si_Decs dec) {
        dec.prim = etq;
        dec.decs().procesa(this);
        dec.sig = etq;
    }

    public void procesa(No_Decs dec) {}
    
    public void procesa(Muchas_Decs dec) {
        dec.prim = etq;
        dec.decs().procesa(this);
        dec.dec().procesa(this);
        dec.sig = etq;
    }

    public void procesa(Una_Dec dec) {
        dec.prim = etq;
        dec.dec().procesa(this);
        dec.sig = etq;
    }

    public void procesa(Muchas_Var var) {
        var.prim = etq;
        var.vars().procesa(this);
        var.var().procesa(this);
        var.sig = etq;
    }

    public void procesa(Una_Var var) {
        var.prim = etq;
        var.var().procesa(this);
        var.sig = etq;
    }

    public void procesa(Var var) {
        var.prim = etq;
        var.tipo().procesa(this);
        var.sig = etq;
    }

    public void procesa(Dec_simple dec) {
        dec.prim = etq;
        dec.var().procesa(this);
        dec.sig = etq;
    }

    public void procesa(Dec_Tipado dec) {
        dec.prim = etq;
        dec.var().procesa(this);
        dec.sig = etq;
    }

    public void procesa(Dec_Proc dec) {
        dec.prim = etq;
        dec.par_for().procesa(this);
        dec.bloq().procesa(this);
        dec.sig = etq;
    }

    public void procesa(Tipo_array tipo) {
        tipo.prim = etq;
        tipo.tipo().procesa(this);
        tipo.sig = etq;
    }

    public void procesa(Tipo_punt tipo) {
        tipo.prim = etq;
        tipo.tipo().procesa(this);
        tipo.sig = etq;
    }

    public void procesa(Tipo_bool tipo) {}

    public void procesa(Tipo_int tipo) {}

    public void procesa(Tipo_real tipo) {}

    public void procesa(Tipo_string tipo) {}

    public void procesa(Tipo_ident tipo) {
        tipo.prim = etq;
        etq++;
        tipo.sig = etq;
    }

    public void procesa(Tipo_struct tipo) {
        tipo.prim = etq;
        tipo.lvar().procesa(this);
        tipo.sig = etq;
    }

    public void procesa(Si_inst inst) {
        inst.prim = etq;
        inst.insts().procesa(this);
        inst.sig = etq;
    }

    public void procesa(No_inst inst) {}

    public void procesa(Muchas_inst inst) {
        inst.prim = etq;
        inst.insts().procesa(this);
        inst.inst().procesa(this);
        inst.sig = etq;
    }

    public void procesa(Una_inst inst) {
        inst.prim = etq;
        inst.inst().procesa(this);
        inst.sig = etq;
    }

    public void procesa(Si_pformal pf) {
        pf.prim = etq;
        pf.lpfml().procesa(this);
        pf.sig = etq;
    }

    public void procesa(No_pformal pf) {}

    public void procesa(Muchos_pformal pf) {
        pf.prim = etq;
        pf.lpfml().procesa(this);
        pf.pfml().procesa(this);
        pf.sig = etq;
    }

    public void procesa(Un_pformal pf) {
        pf.prim = etq;
        pf.pfml().procesa(this);
        pf.sig = etq;
    }

    public void procesa(Pformal_ref pf) {
        pf.prim = etq;
        pf.tipo().procesa(this);
        pf.sig = etq;
    }

    public void procesa(Pformal_noref pf) {
        pf.prim = etq;
        pf.tipo().procesa(this);
        pf.sig = etq;
    }

    public void procesa(Si_preales pr){
        pr.prim = etq;
        pr.lpr().procesa(this);
        pf.sig = etq;
    }

    public void procesa(No_preales pr){}

    public void procesa(Muchas_exp exp){
        exp.prim = etq;
        exp.lpr().procesa(this);
        etq++;
        exp.exp().procesa(this);
        exp.exp().procesa_acc_val(this);
        etq++;
        exp.sig = etq;
    }

    public void procesa(Una_exp exp){
        exp.prim = etq;
        exp.exp().procesa(this);
        etq = etq +2;
        exp.sig = etq;
    }

    public void procesa(Inst_eval exp){
        exp.prim = etq;
        exp.exp().procesa(this);
        etq = etq +2;
        exp.sig = etq;
    }

    public void procesa(Inst_eval exp){
        exp.prim = etq;
        exp.exp().procesa(this);
        exp.exp().procesa_acc_val(this);
        etq++;
        exp.bloq().procesa(this);
        etq++;
        exp.sig = etq;
    }



}
