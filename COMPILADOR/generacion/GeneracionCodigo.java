package generacion;

import Comprobacion_tipos.Tipado;
import asint.SintaxisAbstractaTiny.*;

public class GeneracionCodigo {
	private MaquinaP m;
	
	public GeneracionCodigo(MaquinaP m) {
		this.m = m;
	}
	
	public void procesa(Prog prog) {
        prog.bloq().procesa(this);
    } 

	public void procesa(Blo blo) {
        recolecta_procs(blo.decla().procesa(this));
        blo.instr().procesa(this);
        m.stop();
        /*
        while not es_vacia(sub_pendientes)
	        sub = cima(sub_pendientes)
	        desapila(sub_pendientes)
	        let sub = dec_proc(id,PFml,Decs,Is) in
	            emit desapilad(sub.nivel)
	            recolecta_procs(Decs)
	            gen_cod(Is)
	            emit desactiva(sub.nivel, sub.tam)
	            emid ir_ind()
	        end let
	    end while 
        */
	}
	
	public void procesa(Si_inst insts) {
		insts.insts().procesa(this);
	}
	
	public void procesa(No_inst insts) {}
	
	public void procesa(Muchas_inst insts) {
		insts.insts().procesa(this);
		insts.inst().procesa(this);	
	}


	public void procesa(Una_inst insts) {
		insts.inst().procesa(this);	
	}

	public void procesa(Si_preales preales) {
		preales.lpr().procesa(this);
	}

	public void procesa(No_preales preales) {}


	public void procesa(Muchas_exp lpreal) {
		lpreal.lpr().procesa(this);
		lpreal.exp().procesa(this);
	}

	public void procesa(Una_exp lpreal) {
		lpreal.exp().procesa(this);		
	}

	public void procesa(Inst_eval inst) {
        inst.exp().procesa(this);
        gen_acc_val(inst.exp())
	}
	
	public void procesa(Inst_if inst) {
        inst.exp().procesa(this);
	    gen_acc_val(inst.exp())
	    m.ir_f(inst.getSig());
        inst.bloq().procesa(this);
	}
	
	public void procesa(Inst_else inst) {
        inst.exp().procesa(this);
        gen_acc_val(inst.exp())
        m.ir_v(inst.getSig());
        inst.bloq1().procesa(this);
        m.ir_f(inst.getSig());
        inst.bloq2().procesa(this);
	}
	
	public void procesa(Inst_while inst) {
        inst.exp().procesa(this);
        gen_acc_val(inst.exp())
        m.ir_f(inst.getSig());
        inst.bloq().procesa(this);
        m.ir_a(inst.getPrim());
	}
	
	public void procesa(Inst_new inst) {
        inst.exp().procesa(this);
        if (exp().getTipado() == Tipado.tipoPuntero) {
        	Tipo_punt p = (Tipo_punt)exp().getTipado();
        	m.alloc(p.tipo().getEspacio());
        }
        m.desapila_ind();
	}
	
	public void procesa(Inst_delete inst) {
        inst.exp().procesa(this);
        m.apila_ind();
        if (exp().getTipado() == Tipado.tipoPuntero) {
        	Tipo_punt p = (Tipo_punt)exp().getTipado();
        	m.dealloc(p.tipo().getEspacio());
        }
	}
	
	public void procesa(Inst_read inst) {
        inst.exp().procesa(this);
	}
	
	public void procesa(Inst_write inst) {
        inst.exp().procesa(this);
	}
	
	public void procesa(Inst_call inst) {
		m.activa(inst.getVinculo().getNivel(), inst.getVinculo().getEspacio, inst.getSig());
		gen_paso_PFml(inst.vinculo(),E)
		m.ir_a(inst.getVinculo().getPrim());
	}
	
	public void procesa(Inst_nl inst) {
        m.INl();
		
	}
	
	public void procesa(Inst_blo inst) {
        inst.bloq().procesa(this);		
	}

	public void procesa(Exp_asig exp) {
		exp.exp1().procesa(this);
		exp.exp2().procesa(this);
		if (exp.exp1().esDesignador()) {
			m.copia(exp.getTipado().getEspacio());
		}
		else {
			m.desapila_ind();
		}
	}
	
	public void procesa(Exp_menor exp) {
		exp.exp1().procesa(this);
	    gen_acc_val(exp.exp1())
		exp.exp2().procesa(this);
	    gen_acc_val(exp.exp2())
	    m.menor();
	}    
	
	public void procesa(Exp_menIgual exp) {
		exp.exp1().procesa(this);
	    gen_acc_val(exp.exp1())
		exp.exp2().procesa(this);
	    gen_acc_val(exp.exp2())
	    m.menorIgual();
	}
	
	public void procesa(Exp_mayor exp) {
		exp.exp1().procesa(this);
		gen_acc_val(exp.exp1())
		exp.exp2().procesa(this);
		gen_acc_val(exp.exp2())
	    m.mayor();
	}
	
	public void procesa(Exp_mayIgual exp) {
		exp.exp1().procesa(this);
		gen_acc_val(exp.exp1())
		exp.exp2().procesa(this);
		gen_acc_val(exp.exp2())
	    m.mayorIgual();
	}
	
	public void procesa(Exp_igual exp) {
		exp.exp1().procesa(this);
		gen_acc_val(exp.exp1())
		exp.exp2().procesa(this);
		gen_acc_val(exp.exp2())
	    m.igual();
	}
	
	public void procesa(Exp_dist exp) {
		exp.exp1().procesa(this);
		gen_acc_val(exp.exp1())
		exp.exp2().procesa(this);
		gen_acc_val(exp.exp2())
	    m.distinto();
	}

	public void procesa(Exp_suma exp) {
		exp.exp1().procesa(this);
		gen_acc_val(exp.exp1())
		exp.exp2().procesa(this);
		gen_acc_val(exp.exp2())
	    m.suma();
	}
	
	public void procesa(Exp_resta exp) {
		exp.exp1().procesa(this);
		gen_acc_val(exp.exp1())
		exp.exp2().procesa(this);
		gen_acc_val(exp.exp2())
	    m.resta();
	}

	public void procesa(Exp_mult exp) {
		exp.exp1().procesa(this);
		gen_acc_val(exp.exp1())
		exp.exp2().procesa(this);
		gen_acc_val(exp.exp2())
	    m.mul();
	}

	public void procesa(Exp_div exp) {
		exp.exp1().procesa(this);
		gen_acc_val(exp.exp1())
		exp.exp2().procesa(this);
		gen_acc_val(exp.exp2())
	    m.div();
	}

	public void procesa(Exp_mod exp){
    	exp.exp1().procesa(this);
    	gen_acc_val(exp.exp1());
    	exp.exp2().procesa(this);
    	gen_acc_val(exp.exp2());
    	m.mod();
    }
    
    public void procesa(Exp_and exp){
    	exp.exp1().procesa(this);
    	gen_acc_val(exp.exp1());
    	exp.exp2().procesa(this);
    	gen_acc_val(exp.exp2());
    	m.and();
    }
    
    public void procesa(Exp_or exp){
		exp.exp1().procesa(this);
    	gen_acc_val(exp.exp1());
    	exp.exp2().procesa(this);
    	gen_acc_val(exp.exp2());
    	m.or();
    }
    
    public void procesa(Exp_menos exp){
        exp.exp().procesa(this);
    	gen_acc_val(exp.exp());
    	m.menos();
    }
	
	public void procesa(Exp_not exp){
        exp.exp().procesa(this);
    	gen_acc_val(exp.exp());
    	m.not();
    }
    
    public void procesa(Exp_index exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        gen_acc_val(exp.exp2());
        m.apila_int(exp.exp2().getEspacio());
        m.mul();
        m.suma();
    }
    
    public void procesa(Exp_reg exp){
        exp.exp().procesa(this);
        if (exp.getTipado() == Tipado.tipoStruct) {
        	Tipo_struct s = (Tipo_struct)exp.exp1().getVinculo().tipo();
        	m.apila_int(s.lvar().var().getDesplaza());
        }
    }
    
    public void procesa(Exp_indir exp){
        exp.exp().procesa(this);
        m.apila_ind();
    }
	    
	public void procesa(Exp_true exp){
		m.apila_bool(true);
	}
    
    public void procesa(Exp_false exp){
		m.apila_bool(false);
	}
    
    public void procesa(Exp_litEnt exp){
        m.apila_int(Integer.parseInt(exp.litEnt()));
    }
    
    public void procesa(Exp_litReal exp){
    	m.apila_real(Double.parseDouble(exp.litReal()));
    }
    	
	public void procesa(Exp_litCadena exp){
		m.apila_cadena(exp.litCad());
	}
	
	public void procesa(Exp_iden exp){
		m.apila_int(exp.getViculo().getDir());
	}
	   
	public void procesa(Exp_null exp){}

	private void gen_acc_val(Exp e) {
		if (e.esDesignador()) {
			e.apila_ind();
		}
	}
	
	private void gen_paso_PFml(Dec_proc proc, PReales pr) {
	    m.dup();
	    m.apila_int(proc.par_for().getDir());
	    m.suma();
	    pr.procesa(this);
	    
	    if (proc.par_for().getTipado() == Tipado.pf_ref || !pr.esDesignador())
	        m.desapila_ind();
	    else
	    	if (proc.par_for().getTipado() == Tipado.pf_noref) {
	    		m.copia(proc.par_for().tipo().getEspacio());
	    	}
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
