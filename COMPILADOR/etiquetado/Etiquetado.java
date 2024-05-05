public class Etiquetado implements Procesamiento{
    private int etq = 0;
    private MaquinaP sub_pendientes;
	
	public GeneracionCodigo(MaquinaP m) {
		sub_pendientes = m;
	}
	
    public void procesa(Prog prog) {
        prog.bloq().procesa(this);
    }

    public void procesa(Bloq bloq) {
        bloq.setPrim(etq);
        bloq.instr().procesa(this);
        etq++;
        while(1){
            //TODO
            recolecta_procs(bloq.decs())
            bloq.decs().procesa(this);
        }
        prog.setSig(etq);
    }
    public void procesa(Si_Decs dec) {
        dec.setPrim(etq);
        dec.decs().procesa(this);
        dec.setSig(etq);
    }

    public void procesa(No_Decs dec) {}
    
    public void procesa(Muchas_Decs dec) {
        dec.setPrim(etq);
        dec.decs().procesa(this);
        dec.dec().procesa(this);
        dec.setSig(etq);
    }

    public void procesa(Una_Dec dec) {
        dec.setPrim(etq);
        dec.dec().procesa(this);
        dec.setSig(etq);
    }

    public void procesa(Muchas_Var var) {
        var.setPrim(etq);
        var.vars().procesa(this);
        var.var().procesa(this);
        var.setSig(etq);
    }

    public void procesa(Una_Var var) {
        var.setPrim(etq);
        var.var().procesa(this);
        var.setSig(etq);
    }

    public void procesa(Var var) {
        var.setPrim(etq);
        var.tipo().procesa(this);
        var.setSig(etq);
    }

    public void procesa(Dec_simple dec) {
        dec.setPrim(etq);
        dec.var().procesa(this);
        dec.setSig(etq);
    }

    public void procesa(Dec_Tipado dec) {
        dec.setPrim(etq);
        dec.var().procesa(this);
        dec.setSig(etq);
    }

    public void procesa(Dec_Proc dec) {
        dec.setPrim(etq);
        dec.par_for().procesa(this);
        dec.bloq().procesa(this);
        dec.setSig(etq);
    }

    public void procesa(Tipo_array tipo) {
        tipo.setPrim(etq);
        tipo.tipo().procesa(this);
        tipo.setSig(etq);
    }

    public void procesa(Tipo_punt tipo) {
        tipo.setPrim(etq);
        tipo.tipo().procesa(this);
        tipo.setSig(etq);
    }

    public void procesa(Tipo_bool tipo) {}

    public void procesa(Tipo_int tipo) {}

    public void procesa(Tipo_real tipo) {}

    public void procesa(Tipo_string tipo) {}

    public void procesa(Tipo_ident tipo) {
        tipo.setPrim(etq);
        etq++;
        tipo.setSig(etq);
    }

    public void procesa(Tipo_struct tipo) {
        tipo.setPrim(etq);
        tipo.lvar().procesa(this);
        tipo.setSig(etq);
    }

    public void procesa(Si_inst inst) {
        inst.setPrim(etq);
        inst.insts().procesa(this);
        inst.setSig(etq);
    }

    public void procesa(No_inst inst) {}

    public void procesa(Muchas_inst inst) {
        inst.setPrim(etq);
        inst.insts().procesa(this);
        inst.inst().procesa(this);
        inst.setSig(etq);
    }

    public void procesa(Una_inst inst) {
        inst.setPrim(etq);
        inst.inst().procesa(this);
        inst.setSig(etq);
    }

    public void procesa(Si_pformal pf) {
        pf.setPrim(etq);
        pf.lpfml().procesa(this);
        pf.setSig(etq);
    }

    public void procesa(No_pformal pf) {}

    public void procesa(Muchos_pformal pf) {
        pf.setPrim(etq);
        pf.lpfml().procesa(this);
        pf.pfml().procesa(this);
        pf.setSig(etq);
    }

    public void procesa(Un_pformal pf) {
        pf.setPrim(etq);
        pf.pfml().procesa(this);
        pf.setSig(etq);
    }

    public void procesa(Pformal_ref pf) {
        pf.setPrim(etq);
        pf.tipo().procesa(this);
        pf.setSig(etq);
    }

    public void procesa(Pformal_noref pf) {
        pf.setPrim(etq);
        pf.tipo().procesa(this);
        pf.setSig(etq);
    }

    public void procesa(Si_preales pr){
        pr.setPrim(etq);
        pr.lpr().procesa(this);
        pr.setSig(etq);
    }

    public void procesa(No_preales pr){}

    public void procesa(Muchas_exp exp){
        exp.setPrim(etq);
        exp.lpr().procesa(this);
        etq++;
        exp.exp().procesa(this);
    	etiquetado_acc_val(exp.exp());
        etq++;
        exp.setSig(etq);
    }

    public void procesa(Una_exp exp){
        exp.setPrim(etq);
        exp.exp().procesa(this);
        etq = etq +2;
        exp.setSig(etq);
    }

    public void procesa(Inst_eval inst){
    	inst.setPrim(etq);
    	inst.exp().procesa(this);
        etq = etq +2;
        inst.setSig(etq);
    }

    public void procesa(Inst_if inst){
    	inst.setPrim(etq);
    	inst.exp().procesa(this);
    	etiquetado_acc_val(inst.exp());
        etq++;
        inst.bloq().procesa(this);
        etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Inst_else inst){
    	inst.setPrim(etq);
    	inst.exp().procesa(this);
    	etiquetado_acc_val(inst.exp());
        etq++;
        inst.bloq1().procesa(this);
        etq++;
        inst.bloq2().procesa(this);
        etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Inst_while inst){
    	inst.setPrim(etq);
    	inst.exp().procesa(this);
    	etiquetado_acc_val(inst.exp());
        etq++;
        inst.bloq().procesa(this);
        etq++;
        inst.setSig(etq);
    }

    public void procesa(Inst_new inst){
    	inst.setPrim(etq);
    	inst.exp().procesa(this);
        etq = etq +2;
        inst.setSig(etq);
    }
    
    public void procesa(Inst_delete inst){
    	inst.setPrim(etq);
    	inst.exp().procesa(this);
        etq = etq +2;
        inst.setSig(etq);
    }
    
    public void procesa(Inst_read inst){
    	inst.setPrim(etq);
    	inst.exp().procesa(this);
        etq = etq +2;
        inst.setSig(etq);
    }

    public void procesa(Inst_write inst){
    	inst.setPrim(etq);
    	inst.exp().procesa(this);
        etq = etq +2;
        inst.setSig(etq);
    }
    
    public void procesa(Inst_call inst){
    	inst.setPrim(etq);
    	etq++;
    	//TODO
    	etiquetado_paso_param(inst.getVinculo(),inst.pr());
        etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Inst_nl inst){}
    

    public void procesa(Inst_blo inst){
    	inst.setPrim(etq);
    	inst.bloq().procesa(this);
        inst.setSig(etq);
    }
    
    public void procesa(Exp_asig exp){
    	inst.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Exp_menor exp){
    	inst.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Exp_menIgual exp){
    	inst.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Exp_mayor exp){
    	inst.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Exp_mayIgual exp){
    	inst.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Exp_igual exp){
    	inst.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Exp_dist exp){
    	inst.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Exp_suma exp){
    	inst.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Exp_resta exp){
    	inst.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Exp_mult exp){
    	inst.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Exp_div exp){
    	inst.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Exp_mod exp){
    	inst.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Exp_and exp){
    	inst.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Exp_or exp){
    	inst.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Exp_menos exp){
        exp.setPrim(etq);
        exp.exp().procesa(this);
    	etiquetado_acc_val(exp.exp());
        exp.setSig(etq);
    }
    
    public void procesa(Exp_not exp){
        exp.setPrim(etq);
        exp.exp().procesa(this);
    	etiquetado_acc_val(exp.exp());
    	etq = etq+2;
        exp.setSig(etq);
    }
    
    public void procesa(Exp_index exp){
        exp.setPrim(etq);
        etiquetado_opnds(exp.exp1(),exp.exp2());
        etq++;
        exp.setSig(etq);
    }
    
    public void procesa(Exp_reg exp){
        exp.setPrim(etq);
        etq++;
        exp.exp().procesa(this);
        etq++;
        exp.setSig(etq);
    }
    
    public void procesa(Exp_indir exp){
        exp.setPrim(etq);
        exp.exp().procesa(this);
        etq++;
        exp.setSig(etq);
    }
    
    public void procesa(Exp_true exp){}
    
    public void procesa(Exp_false exp){}
    
    public void procesa(Exp_litEnt exp){
        exp.setPrim(etq);
        etq++;
        exp.setSig(etq);
    }
    
    public void procesa(Exp_litReal exp){
        exp.setPrim(etq);
        etq++;
        exp.setSig(etq);
    }
    
    public void procesa(Exp_litCadena exp){
        exp.setPrim(etq);
        etq++;
        exp.setSig(etq);
    }
    
    public void procesa(Exp_iden exp){
        exp.setPrim(etq);
        if(exp.getNivel() == 0) {
        	etq++;
        }
        else {
        	etq = etq +3;
        }
        exp.setSig(etq);
    }
    
    public void procesa(Exp_null exp){}
    
    private void etiquetado_opnds(Exp exp1,Exp exp2) {
    	exp1.procesa(this);
    	etiquetado_acc_val(exp1);
    	exp2.procesa(this);
    	etiquetado_acc_val(exp2);
    }
    
    private void etiquetado_acc_val(Exp exp) {
    	if(exp.esDesignador()) {
    		etq++;
    	}
    }
    
    private void etiquetado-paso-param(dec_proc(id,Param,Decs,Is),PReales p){
    	etq = etq+ 2;
    	p.lpr().procesa(this)
    	etq++;
    }
    
    
    private void recolecta_procs(Muchas_decs decs) {
	    recolecta_procs(decs.decs().procesa(this));
	    recolecta_procs(decs.dec().procesa(this));
	}
	
	private void recolecta_procs(Una_dec dec) {
	    recolecta_procs(dec.dec().procesa(this));
	}
	
	private void recolecta_procs(Dec_simple dec) {}
	
	private void recolecta_procs(Dec_type dec) {}
	
	private void recolecta_procs(Dec_proc dec) {
		//emit apila(sub_pendientes,$)
	}
    
    
    
    
    
    
    
    
    
}
