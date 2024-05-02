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
    	etiquetado_acc_val(exp.exp());
        etq++;
        exp.sig = etq;
    }

    public void procesa(Una_exp exp){
        exp.prim = etq;
        exp.exp().procesa(this);
        etq = etq +2;
        exp.sig = etq;
    }

    public void procesa(Inst_eval inst){
    	inst.prim = etq;
    	inst.exp().procesa(this);
        etq = etq +2;
        inst.sig = etq;
    }

    public void procesa(Inst_if inst){
    	inst.prim = etq;
    	inst.exp().procesa(this);
    	etiquetado_acc_val(inst.exp());
        etq++;
        inst.bloq().procesa(this);
        etq++;
        inst.sig = etq;
    }
    
    public void procesa(Inst_else inst){
    	inst.prim = etq;
    	inst.exp().procesa(this);
    	etiquetado_acc_val(inst.exp());
        etq++;
        inst.bloq1().procesa(this);
        etq++;
        inst.bloq2().procesa(this);
        etq++;
        inst.sig = etq;
    }
    
    public void procesa(Inst_while inst){
    	inst.prim = etq;
    	inst.exp().procesa(this);
    	etiquetado_acc_val(inst.exp());
        etq++;
        inst.bloq().procesa(this);
        etq++;
        inst.sig = etq;
    }

    public void procesa(Inst_new inst){
    	inst.prim = etq;
    	inst.exp().procesa(this);
        etq = etq +2;
        inst.sig = etq;
    }
    
    public void procesa(Inst_delete inst){
    	inst.prim = etq;
    	inst.exp().procesa(this);
        etq = etq +2;
        inst.sig = etq;
    }
    
    public void procesa(Inst_read inst){
    	inst.prim = etq;
    	inst.exp().procesa(this);
        etq = etq +2;
        inst.sig = etq;
    }

    public void procesa(Inst_write inst){
    	inst.prim = etq;
    	inst.exp().procesa(this);
        etq = etq +2;
        inst.sig = etq;
    }
    
    public void procesa(Inst_call inst){
    	inst.prim = etq;
    	etq++;
    	//TODO
    	etiquetado_paso_param(inst.vincula(),inst.pr());
        etq++;
        inst.sig = etq;
    }
    
    public void procesa(Inst_nl inst){}
    

    public void procesa(Inst_blo inst){
    	inst.prim = etq;
    	inst.bloq().procesa(this);
        inst.sig = etq;
    }
    
    public void procesa(Exp_asig exp){
    	inst.prim = etq;
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.sig = etq;
    }
    
    public void procesa(Exp_menor exp){
    	inst.prim = etq;
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.sig = etq;
    }
    
    public void procesa(Exp_menIgual exp){
    	inst.prim = etq;
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.sig = etq;
    }
    
    public void procesa(Exp_mayor exp){
    	inst.prim = etq;
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.sig = etq;
    }
    
    public void procesa(Exp_mayIgual exp){
    	inst.prim = etq;
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.sig = etq;
    }
    
    public void procesa(Exp_igual exp){
    	inst.prim = etq;
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.sig = etq;
    }
    
    public void procesa(Exp_dist exp){
    	inst.prim = etq;
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.sig = etq;
    }
    
    public void procesa(Exp_suma exp){
    	inst.prim = etq;
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.sig = etq;
    }
    
    public void procesa(Exp_resta exp){
    	inst.prim = etq;
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.sig = etq;
    }
    
    public void procesa(Exp_mult exp){
    	inst.prim = etq;
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.sig = etq;
    }
    
    public void procesa(Exp_div exp){
    	inst.prim = etq;
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.sig = etq;
    }
    
    public void procesa(Exp_mod exp){
    	inst.prim = etq;
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.sig = etq;
    }
    
    public void procesa(Exp_and exp){
    	inst.prim = etq;
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.sig = etq;
    }
    
    public void procesa(Exp_or exp){
    	inst.prim = etq;
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.sig = etq;
    }
    
    public void procesa(Exp_menos exp){
        exp.prim = etq;
        exp.exp().procesa(this);
    	etiquetado_acc_val(exp.exp());
        exp.sig = etq;
    }
    
    public void procesa(Exp_not exp){
        exp.prim = etq;
        exp.exp().procesa(this);
    	etiquetado_acc_val(exp.exp());
    	etq = etq+2;
        exp.sig = etq;
    }
    
    public void procesa(Exp_index exp){
        exp.prim = etq;
        etiquetado_opnds(exp.exp1(),exp.exp2());
        etq++;
        exp.sig = etq;
    }
    
    public void procesa(Exp_reg exp){
        exp.prim = etq;
        etq++;
        exp.exp().procesa(this);
        etq++;
        exp.sig = etq;
    }
    
    public void procesa(Exp_indir exp){
        exp.prim = etq;
        exp.exp().procesa(this);
        etq++;
        exp.sig = etq;
    }
    
    public void procesa(Exp_true exp){}
    
    public void procesa(Exp_false exp){}
    
    public void procesa(Exp_litEnt exp){
        exp.prim = etq;
        etq++;
        exp.sig = etq;
    }
    
    public void procesa(Exp_litReal exp){
        exp.prim = etq;
        etq++;
        exp.sig = etq;
    }
    
    public void procesa(Exp_litCadena exp){
        exp.prim = etq;
        etq++;
        exp.sig = etq;
    }
    
    public void procesa(Exp_iden exp){
        exp.prim = etq;
        if(exp.nivel == 0) {
        	etq++;
        }
        else {
        	etq = etq +3;
        }
        exp.sig = etq;
    }
    
    public void procesa(Exp_null exp){}
    
    public void etiquetado_opnds(Exp exp1,Exp exp2) {
    	exp1.procesa(this);
    	etiquetado_acc_val(exp1);
    	exp2.procesa(this);
    	etiquetado_acc_val(exp2);
    }
    
    public void etiquetado_acc_val(Exp exp) {
    	if(exp.esDesignador()) {
    		etq++;
    	}
    }
    
    
    
    
    
    
    
    
    
    
}
