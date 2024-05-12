// Package not detected, please report project structure on CodeTogether's GitHub Issues
package tipado;

import asint.SintaxisAbstractaTiny.Tipado;
import asint.SintaxisAbstractaTiny.Tipo;
import asint.SintaxisAbstractaTiny.Prog;
import asint.SintaxisAbstractaTiny.Blo;
import asint.SintaxisAbstractaTiny.Dec_proc;
import asint.SintaxisAbstractaTiny.Dec_simple;
import asint.SintaxisAbstractaTiny.Dec_type;
import asint.SintaxisAbstractaTiny.Exp;
import asint.SintaxisAbstractaTiny.Exp_and;
import asint.SintaxisAbstractaTiny.Exp_asig;
import asint.SintaxisAbstractaTiny.Exp_dist;
import asint.SintaxisAbstractaTiny.Exp_div;
import asint.SintaxisAbstractaTiny.Exp_false;
import asint.SintaxisAbstractaTiny.Exp_iden;
import asint.SintaxisAbstractaTiny.Exp_igual;
import asint.SintaxisAbstractaTiny.Exp_index;
import asint.SintaxisAbstractaTiny.Exp_indir;
import asint.SintaxisAbstractaTiny.Exp_litCadena;
import asint.SintaxisAbstractaTiny.Exp_litEnt;
import asint.SintaxisAbstractaTiny.Exp_litReal;
import asint.SintaxisAbstractaTiny.Exp_mayIgual;
import asint.SintaxisAbstractaTiny.Exp_mayor;
import asint.SintaxisAbstractaTiny.Exp_menIgual;
import asint.SintaxisAbstractaTiny.Exp_menor;
import asint.SintaxisAbstractaTiny.Exp_menos;
import asint.SintaxisAbstractaTiny.Exp_mod;
import asint.SintaxisAbstractaTiny.Exp_mult;
import asint.SintaxisAbstractaTiny.Exp_not;
import asint.SintaxisAbstractaTiny.Exp_null;
import asint.SintaxisAbstractaTiny.Exp_or;
import asint.SintaxisAbstractaTiny.Exp_reg;
import asint.SintaxisAbstractaTiny.Exp_resta;
import asint.SintaxisAbstractaTiny.Exp_suma;
import asint.SintaxisAbstractaTiny.Exp_true;
import asint.SintaxisAbstractaTiny.Inst_blo;
import asint.SintaxisAbstractaTiny.Inst_call;
import asint.SintaxisAbstractaTiny.Inst_delete;
import asint.SintaxisAbstractaTiny.Inst_else;
import asint.SintaxisAbstractaTiny.Inst_eval;
import asint.SintaxisAbstractaTiny.Inst_if;
import asint.SintaxisAbstractaTiny.Inst_new;
import asint.SintaxisAbstractaTiny.Inst_nl;
import asint.SintaxisAbstractaTiny.Inst_read;
import asint.SintaxisAbstractaTiny.Inst_while;
import asint.SintaxisAbstractaTiny.Inst_write;
import asint.SintaxisAbstractaTiny.Muchas_decs;
import asint.SintaxisAbstractaTiny.Muchas_exp;
import asint.SintaxisAbstractaTiny.Muchas_inst;
import asint.SintaxisAbstractaTiny.Muchas_var;
import asint.SintaxisAbstractaTiny.Muchos_pformal;
import asint.SintaxisAbstractaTiny.No_decs;
import asint.SintaxisAbstractaTiny.No_inst;
import asint.SintaxisAbstractaTiny.No_pformal;
import asint.SintaxisAbstractaTiny.No_preales;
import asint.SintaxisAbstractaTiny.Nodo;
import asint.SintaxisAbstractaTiny.PFml;
import asint.SintaxisAbstractaTiny.PFmls;
import asint.SintaxisAbstractaTiny.PReales;
import asint.SintaxisAbstractaTiny.Pformal_noref;
import asint.SintaxisAbstractaTiny.Pformal_ref;
import asint.SintaxisAbstractaTiny.Si_decs;
import asint.SintaxisAbstractaTiny.Si_inst;
import asint.SintaxisAbstractaTiny.Si_pformal;
import asint.SintaxisAbstractaTiny.Si_preales;
import asint.SintaxisAbstractaTiny.Tipo_array;
import asint.SintaxisAbstractaTiny.Tipo_bool;
import asint.SintaxisAbstractaTiny.Tipo_ident;
import asint.SintaxisAbstractaTiny.Tipo_int;
import asint.SintaxisAbstractaTiny.Tipo_null;
import asint.SintaxisAbstractaTiny.Tipo_ok;
import asint.SintaxisAbstractaTiny.Tipo_punt;
import asint.SintaxisAbstractaTiny.Tipo_real;
import asint.SintaxisAbstractaTiny.Tipo_string;
import asint.SintaxisAbstractaTiny.Tipo_struct;
import asint.SintaxisAbstractaTiny.Un_pformal;
import asint.SintaxisAbstractaTiny.Una_dec;
import asint.SintaxisAbstractaTiny.Una_exp;
import asint.SintaxisAbstractaTiny.Una_inst;
import asint.SintaxisAbstractaTiny.Tipo_ok;
import asint.SintaxisAbstractaTiny.Tipo_null;
import asint.SintaxisAbstractaTiny.Tipo_error;
import asint.SintaxisAbstractaTiny.Una_var;
import asint.SintaxisAbstractaTiny.Var;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import asint.Procesamiento;
import asint.SintaxisAbstractaTiny;
import errors.GestionErroresTiny;

public class Comprobacion_tipos implements Procesamiento {
    //private enum Tipado {Exp, Tipo, Boolean, Error};

    private Boolean ok;
    private GestionErroresTiny GestionErrores;

    public Comprobacion_tipos() {
        ok = true;
        GestionErrores = new GestionErroresTiny();
    }

    public Boolean isOk() { return ok; }

    public void procesa(Prog p) {
        p.bloq().procesa(this);
        p.setTipo(p.bloq().tipo());
    }

    public void procesa(Blo b) {
        b.decla().procesa(this);
        b.instr().procesa(this);
        if (!ambos_ok(b.decla(), b.instr())) {
            ok &= false;
            b.setOk(false);
            b.setTipo(SintaxisAbstractaTiny.tipo_error());
        }
        else
            b.setTipo(SintaxisAbstractaTiny.tipo_ok());

    }


    public void procesa(Si_decs decs) {
        decs.decs().procesa(this);
        decs.setTipo(decs.decs().tipo());
	}

	public void procesa(No_decs decs) {
        decs.setTipo(SintaxisAbstractaTiny.tipo_ok());
	}

    public void procesa(Muchas_decs decs) {
        decs.decs().procesa(this);
        decs.dec().procesa(this);
        if (!ambos_ok(decs.decs(),decs.dec())) {
            ok &= false;
            decs.setOk(false);
            decs.setTipo(SintaxisAbstractaTiny.tipo_error());
        }
        else 
            decs.setTipo(SintaxisAbstractaTiny.tipo_ok());
	}

	public void procesa(Una_dec decs) {
        decs.dec().procesa(this);
        decs.setTipo(decs.dec().tipo());
	}

    public void procesa(Muchas_var vars) {
        vars.vars().procesa(this);
		vars.var().procesa(this);
        if (!ambos_ok(vars.vars(), vars.var())) {
            ok &= false;
            vars.setOk(false);
            vars.setTipo(SintaxisAbstractaTiny.tipo_error());
        }
        else
            vars.setTipo(SintaxisAbstractaTiny.tipo_ok());
	}

	public void procesa(Una_var vars) {
        vars.var().procesa(this);
        if (vars.var().tipo().getClass() != Tipo_error.class)
            vars.setTipo(SintaxisAbstractaTiny.tipo_ok());
        else vars.setTipo(SintaxisAbstractaTiny.tipo_error());
	}

    public void procesa(Var v) {
        v.tipo().procesa(this);
        v.setTipo(v.tipo());
	}

    public void procesa(Dec_simple dec) {
        dec.var().procesa(this);
        if (dec.var().tipo().getClass() != Tipo_error.class)
            dec.setTipo(SintaxisAbstractaTiny.tipo_ok());
        else dec.setTipo(SintaxisAbstractaTiny.tipo_error());
	}
    
    public void procesa(Dec_type dec) {
        dec.var().procesa(this);
        dec.setTipo(dec.var().tipo());
	}
    
    public void procesa(Dec_proc dec) {
        dec.par_for().procesa(this);
        dec.bloq().procesa(this);
        dec.setTipo(dec.bloq().tipo());
	}

    public void procesa(Tipo_array tipo) {
        int tam = Integer.parseInt(tipo.litEnt());
		if (tam <= 0) {
            ok &= false;
            tipo.setOk(false);
            GestionErrores.errorTamanioArray();
        }
        else {
            tipo.tipo().procesa(this);
        }
        tipo.setTam(tam);
	}
    
    public void tipado(Tipo_punt tipo) {
        tipo.tipo().procesa(this);
        tipo.setTipo(tipo.tipo().tipo());
    }

    public void procesa(Tipo_bool tipo) {tipo.setTipo(SintaxisAbstractaTiny.tipo_bool());}
	public void procesa(Tipo_int tipo) {tipo.setTipo(SintaxisAbstractaTiny.tipo_int());}
	public void procesa(Tipo_real tipo) {tipo.setTipo(SintaxisAbstractaTiny.tipo_real());}
	public void procesa(Tipo_string tipo) {tipo.setTipo(SintaxisAbstractaTiny.tipo_string());}
    public void procesa(Tipo_ident tipo) {tipo.setTipo(tipo.getVinculo().tipo());}
    
    public void procesa(Tipo_struct tipo) {
    	boolean repetido = false;
        Set<String> noRepetidos = new HashSet<>();
        
        for (String clave : tipo.accedeReg().keySet()) {
            if (noRepetidos.contains(clave)) {
                repetido = true;
                break;
            } else {
            	noRepetidos.add(clave);
            }
        }
        if (!repetido) tipo.lvar().procesa(this);
	}

    public void procesa(Si_inst insts) {
		insts.insts().procesa(this);
		insts.setTipo(insts.insts().tipo());
	}

	public void procesa(No_inst insts) {
		insts.setTipo(SintaxisAbstractaTiny.tipo_ok());
	}

    public void procesa(Muchas_inst insts) {
		insts.insts().procesa(this);
        insts.inst().procesa(this);	
        if (!ambos_ok(insts.insts(), insts.inst())) {
            ok &= false;
            insts.setOk(false);
            insts.setTipo(SintaxisAbstractaTiny.tipo_error());
        }
        else 
            insts.setTipo(SintaxisAbstractaTiny.tipo_ok());
	}

	public void procesa(Una_inst insts) {
		insts.inst().procesa(this);
		insts.setTipo(insts.inst().tipo());
	}

	public void procesa(Si_pformal pfmls) {
        pfmls.lpfml().procesa(this);
		pfmls.setTipo(pfmls.lpfml().tipo());
	}

	public void procesa(No_pformal pfmls) {
		pfmls.setTipo(SintaxisAbstractaTiny.tipo_ok());
	}

    public void procesa(Muchos_pformal pfmls) {
		pfmls.lpfml().procesa(this);
		pfmls.pfml().procesa(this);
        if (!(pfmls.lpfml().getOk() && pfmls.pfml().getOk())) {
            ok &= false;
            pfmls.setOk(false);
            pfmls.setTipo(SintaxisAbstractaTiny.tipo_error());
        }
        else 
            pfmls.setTipo(SintaxisAbstractaTiny.tipo_ok());
	}

	public void procesa(Un_pformal pfmls) {
		pfmls.pfml().procesa(this);
		pfmls.setTipo(pfmls.lpfml().tipo());
	}

    public void procesa(Si_preales preales) {
		preales.lpr().procesa(this);
		preales.setTipo(preales.lpr().tipo());
	}

	public void procesa(No_preales preales) {
		preales.setTipo(SintaxisAbstractaTiny.tipo_ok());
	}

	public void procesa(Muchas_exp lpreal) {
		lpreal.lpr().procesa(this);
        lpreal.exp().procesa(this);
        if (!(lpreal.lpr().getOk() && lpreal.exp().getOk())) {
            ok &= false;
            lpreal.setOk(false);
            lpreal.setTipo(SintaxisAbstractaTiny.tipo_error());
        }
        else 
            lpreal.setTipo(SintaxisAbstractaTiny.tipo_ok());
	}

	public void procesa(Una_exp lpreal) {
		lpreal.exp().procesa(this);		
		lpreal.setTipo(lpreal.exp().tipo());
	}

    public void procesa(Pformal_ref pfml) {
		pfml.tipo().procesa(this);
		pfml.setTipo(pfml.tipo());
	}

	public void procesa(Pformal_noref pfml) {
		pfml.tipo().procesa(this);
		pfml.setTipo(pfml.tipo());
	}

    public void procesa(Inst_eval inst) {
        inst.exp().procesa(this);
        if (claseDe(inst.exp().tipo(), Tipo_error.class)) {
            ok &= false;
            inst.setTipo(SintaxisAbstractaTiny.tipo_error());
        }
        else inst.setTipo(SintaxisAbstractaTiny.tipo_ok());
	}

    public void procesa(Inst_if inst) {
        inst.exp().procesa(this);
        inst.bloq().procesa(this);
        if (!(claseDe(inst.exp().tipo(), Tipo_bool.class) || claseDe(inst.bloq().tipo(), Tipo_ok.class))) {
            ok &= false;
            inst.setOk(false);
            if (!claseDe(inst.exp().tipo(), Tipo_bool.class))
            	GestionErrores.errorTipoInadecuado(inst.exp().tipo(), Tipo_bool.class);
            inst.setTipo(SintaxisAbstractaTiny.tipo_error());
        }
        else inst.setTipo(SintaxisAbstractaTiny.tipo_ok());
	}

    public void procesa(Inst_else inst) {
        inst.exp().procesa(this);
        inst.bloq1().procesa(this);
        inst.bloq2().procesa(this);
        if (!(claseDe(inst.exp().tipo(), Tipo_bool.class) || claseDe(inst.bloq1().tipo(), Tipo_ok.class) || claseDe(inst.bloq2().tipo(), Tipo_ok.class) )){
            ok &= false;
            inst.setOk(false);
            if (!claseDe(inst.exp().tipo(), Tipo_bool.class))
            	GestionErrores.errorTipoInadecuado(inst.exp().tipo(), Tipo_bool.class);
            inst.setTipo(SintaxisAbstractaTiny.tipo_error());
        }
        else inst.setTipo(SintaxisAbstractaTiny.tipo_ok());
	}

    public void procesa(Inst_while inst) {
        inst.exp().procesa(this);
        inst.bloq().procesa(this);
        if (!(claseDe(inst.exp().tipo(), Tipo_bool.class) || claseDe(inst.bloq().tipo(), Tipo_ok.class))) {
            ok &= false;
            inst.setOk(false);
            if (!claseDe(inst.exp().tipo(), Tipo_bool.class))
            	GestionErrores.errorTipoInadecuado(inst.exp().tipo(), Tipo_bool.class);
            inst.setTipo(SintaxisAbstractaTiny.tipo_error());
        }
        else inst.setTipo(SintaxisAbstractaTiny.tipo_ok());
	
	}

    public void procesa(Inst_new inst) {
        inst.exp().procesa(this);
        if (!claseDe(inst.exp().tipo(), Tipo_punt.class)) {
            ok &= false;
            inst.setOk(false);
            GestionErrores.errorTipoInadecuado(inst.exp().tipo(), Tipo_punt.class);
            inst.setTipo(SintaxisAbstractaTiny.tipo_error());
        }
        else
            inst.setTipo(SintaxisAbstractaTiny.tipo_ok());
	}

    public void procesa(Inst_delete inst) {
        inst.exp().procesa(this);
        if (!claseDe(inst.exp().tipo(), Tipo_punt.class)) {
            ok &= false;
            inst.setOk(false);
            GestionErrores.errorTipoInadecuado(inst.exp().tipo(), Tipo_punt.class);
            inst.setTipo(SintaxisAbstractaTiny.tipo_error());
        }
        else
            inst.setTipo(SintaxisAbstractaTiny.tipo_ok());
	}
    
    public void procesa(Inst_read inst){
        inst.exp().procesa(this);
        Tipo t = inst.exp().tipo();
        if (!(inst.exp().esDesignador() && (claseDe(t, Tipo_int.class) || claseDe(t, Tipo_real.class) || claseDe(t, Tipo_string.class)))) {
            ok &= false;
            inst.setOk(false);
            if (!(claseDe(t, Tipo_int.class)))
             	GestionErrores.errorTipoInadecuado(inst.exp().tipo(), Exp_litEnt.class);
            inst.setTipo(SintaxisAbstractaTiny.tipo_error());
        }
        else inst.setTipo(SintaxisAbstractaTiny.tipo_ok());
    }

    public void procesa(Inst_write inst){
        inst.exp().procesa(this);
        Tipo t = inst.exp().tipo();
        if (!(claseDe(t, Tipo_bool.class) || claseDe(t, Tipo_int.class) || claseDe(t, Tipo_real.class) || claseDe(t, Tipo_string.class))) {
            ok &= false;
            inst.setOk(false);
            if (!(claseDe(t, Tipo_int.class)))
             	GestionErrores.errorTipoInadecuado(inst.exp().tipo(), Exp_litEnt.class);
            inst.setTipo(SintaxisAbstractaTiny.tipo_error());
        }
        else inst.setTipo(SintaxisAbstractaTiny.tipo_ok());
	}
    
    public void procesa(Inst_call instr){
        Dec_proc p = (Dec_proc)instr.getVinculo();
        
        if (p == null) {
        	ok &= false;
        	instr.setOk(false);
           	GestionErrores.errorTipoInadecuado(instr.getVinculo().tipo().tipo(), Dec_proc.class);
        }
        else {
        	instr.pr().procesa(this);
        	if (tipado_parametros(instr.pr(), p.par_for()))
        		instr.setTipo(SintaxisAbstractaTiny.tipo_ok());
        	else
        		instr.setTipo(SintaxisAbstractaTiny.tipo_error());
        		
        }
        if (claseDe(instr.tipo(), Error.class)) {
        	ok &= false;
        	instr.setOk(false);
        	GestionErrores.errorParametrosNoCoincidentes();
        }
        
    }   
    
    public boolean tipado_parametros(PReales pr, PFmls pfmls) {
        if(claseDe(pr, No_preales.class) && claseDe(pfmls, No_pformal.class)){
        	return true;
        }
        else if(claseDe(pr, Si_preales.class) && claseDe(pfmls, Si_pformal.class)){
        	if(claseDe(pr.lpr(), Muchas_exp.class) && claseDe(pfmls.lpfml(), Muchos_pformal.class))
        		return tipado_parametros((Muchas_exp)pr.lpr(), (Muchos_pformal)pfmls.lpfml());
        	else if(claseDe(pr.lpr(), Una_exp.class) && claseDe(pfmls.lpfml(), Un_pformal.class))
        		return tipado_parametros((Una_exp)pr.lpr(), (Un_pformal)pfmls.lpfml());
        }
		return false;
    }
    
    public boolean tipado_parametros(Muchas_exp lpreal, Muchos_pformal pfmls) {
    	if(claseDe(lpreal.lpr(), Una_exp.class) && claseDe(pfmls.lpfml(), Un_pformal.class)) {
    		return tipado_parametros((Una_exp)lpreal.lpr(), (Un_pformal)pfmls.lpfml()) &&
            		tipado_parametros(lpreal.exp(), pfmls.pfml());
    	}
    	else if(claseDe(lpreal.lpr(), Muchas_exp.class) && claseDe(pfmls.lpfml(), Muchos_pformal.class)) {
    		return tipado_parametros((Muchas_exp)lpreal.lpr(), (Muchos_pformal)pfmls.lpfml()) &&
            		tipado_parametros(lpreal.exp(), pfmls.pfml());
    	}
    	return false;
    }
    
    public boolean tipado_parametros(Una_exp lpreal, Un_pformal pfmls) {
    	return tipado_parametros(lpreal.exp(), pfmls.pfml());
    }
    
    public boolean tipado_parametros(Exp e, PFml pfml) {
    	if(claseDe(pfml, Pformal_noref.class)) {
    		if (compatibles(e.tipo(), pfml.tipo())) 
        		return true;
    	}
    	else if(claseDe(pfml, Pformal_ref.class)) {
    		if (e.esDesignador() || compatibles(e.tipo(), pfml.tipo()) || (claseDe(e.tipo(), Exp_litReal.class) && claseDe(pfml.tipo(), Exp_litReal.class))) 
        		return true;
        	else {
        		if (!e.esDesignador()) 
                    GestionErrores.errorNoDesignador();
        		else if (!compatibles(e.tipo(), pfml.tipo()))
        			GestionErrores.errorTiposIncompatibles(e.tipo(), pfml.tipo());
        	}
    	}
    	return false;
    }
            		
	public void procesa(Inst_nl inst) {
		inst.setTipo(SintaxisAbstractaTiny.tipo_ok());
	}

	public void procesa(Inst_blo inst) {
        inst.bloq().procesa(this);	
        inst.setTipo(inst.bloq().tipo());
	}

    public void procesa(Exp_asig exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        
        if (exp.exp1().esDesignador()){
            if (compatibles(exp.exp1(), exp.exp2()))
            	exp.setTipo(exp.exp1().tipo());
            
            else {
               	GestionErrores.errorTiposIncompatibles(exp.exp1().tipo(), exp.exp2().tipo());
				ok&=false;
				exp.setOk(false);
            }
        }
        else {
            ok &= false;
            exp.setOk(false);
            GestionErrores.errorNoDesignador();
        }
   }

	public void procesa(Exp_menor exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipo(tipo_bin2(exp.exp1(), exp.exp2()));
    }
    public void procesa(Exp_menIgual exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipo(tipo_bin2(exp.exp1(), exp.exp2()));
    }
    public void procesa(Exp_mayor exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipo(tipo_bin2(exp.exp1(), exp.exp2()));
    }
    public void procesa(Exp_mayIgual exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipo(tipo_bin2(exp.exp1(), exp.exp2()));
    }
	public void procesa(Exp_igual exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipo(tipo_bin3(exp.exp1(), exp.exp2()));
    }
  
    public void procesa(Exp_dist exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipo(tipo_bin3(exp.exp1(), exp.exp2()));
    }
    public void procesa(Exp_suma exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipo(tipo_bin1(exp.exp1(), exp.exp2()));
    }
    public void procesa(Exp_resta exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipo(tipo_bin1(exp.exp1(), exp.exp2()));
    }

    public void procesa(Exp_mult exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipo(tipo_bin1(exp.exp1(), exp.exp2()));
    }

    public void procesa(Exp_div exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipo(tipo_bin1(exp.exp1(), exp.exp2()));
    }
	public void procesa(Exp_mod exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        if  (claseDe(exp.exp1().tipo(), Exp_litEnt.class) && claseDe(exp.exp2().tipo(), Exp_litEnt.class)) {
        	exp.setTipo(exp.exp1().tipo());
        }
        else {
        	ok &= false;
    		exp.setOk(false);
    		if (!claseDe(exp.exp1().tipo(), Exp_litEnt.class))
              	GestionErrores.errorTipoInadecuado(exp.exp1().tipo(), Exp_litEnt.class);
    		if (!claseDe(exp.exp2().tipo(), Exp_litEnt.class))
              	GestionErrores.errorTipoInadecuado(exp.exp2().tipo(), Exp_litEnt.class);
        }
    }

	public void procesa(Exp_and exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        if  (claseDe(exp.exp1().tipo(), Tipo_bool.class) &&  claseDe(exp.exp2().tipo(), Tipo_bool.class)){
        	exp.setTipo(exp.exp1().tipo());
        }
        else {
        	ok &= false;
        	exp.setOk(false);
        	if (!claseDe(exp.exp1().tipo(), Exp_litEnt.class))
              	GestionErrores.errorTipoInadecuado(exp.exp1().tipo(), Tipo_bool.class);
    		if (!claseDe(exp.exp2().tipo(), Exp_litEnt.class))
              	GestionErrores.errorTipoInadecuado(exp.exp2().tipo(), Tipo_bool.class);
        }
    }

    public void procesa(Exp_or exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        if  (claseDe(exp.exp1().tipo(),Tipo_bool.class) &&  claseDe(exp.exp2().tipo(), Tipo_bool.class)){
        	exp.setTipo(exp.exp1().tipo());
        }
        else {
        	ok &= false;
        	exp.setOk(false);
        	if (!claseDe(exp.exp1().tipo(),Tipo_bool.class))
              	GestionErrores.errorTipoInadecuado(exp.exp1().tipo(), Tipo_bool.class);
    		if (!claseDe(exp.exp2().tipo(),Tipo_bool.class))
              	GestionErrores.errorTipoInadecuado(exp.exp2().tipo(), Tipo_bool.class);
        }
    }
         
    public void procesa(Exp_menos menos) {
        menos.exp1().procesa(this);
        if (!claseDe(menos.exp1().tipo(), Exp_litEnt.class) && !claseDe(menos.exp1().tipo(), Exp_litReal.class)) {
            ok &= false;
            menos.setOk(false);
            if (!claseDe(menos.exp1().tipo(), Exp_litEnt.class))
              	GestionErrores.errorTipoInadecuado(menos.exp1().tipo(), Exp_litEnt.class);
            else
            	GestionErrores.errorTipoInadecuado(menos.exp1().tipo(), Exp_litReal.class);
        }
        else {
            menos.setTipo(menos.exp1().tipo());
        }
	}

    public void procesa(Exp_not not) {
		not.exp1().procesa(this);
        if (!claseDe(not.exp1().tipo(), Tipo_bool.class)) {
            ok &= false;
            not.setOk(false);
            GestionErrores.errorTipoInadecuado(not.exp1().tipo(), Tipo_bool.class);
            not.setTipo(SintaxisAbstractaTiny.tipo_error());
        }
        else {
            not.setTipo(not.exp1().tipo());
        }
	}

    public void procesa(Exp_index index) {
		index.exp1().procesa(this);
		index.exp2().procesa(this);
        if (!(claseDe(index.exp1().tipo(), Tipo_array.class) && claseDe(index.exp2().tipo(), Exp_litEnt.class))) {
            ok &= false;
            index.setOk(false);
            if (!claseDe(index.exp1().tipo(), Tipo_array.class))
              	GestionErrores.errorTipoInadecuado(index.exp1().tipo(), Tipo_array.class);
            if (!claseDe(index.exp2().tipo(), Exp_litEnt.class))
              	GestionErrores.errorTipoInadecuado(index.exp2().tipo(), Exp_litEnt.class);
        }
        else {
            index.setTipo(index.exp1().getVinculo().tipo().tipo());
        }
	}

    public void procesa(Exp_reg reg) {
		reg.exp1().procesa(this);
        if (!claseDe(reg.exp1().tipo(), Tipo_struct.class)) {
            ok &= false;
            reg.setOk(false);
          	GestionErrores.errorTipoInadecuado(reg.exp1().tipo(), Tipo_struct.class);
        }
        else {
        	Tipo_struct s = (Tipo_struct)reg.exp1().tipo();
        	if (s.accedeReg().containsKey(reg.id()))
        		reg.setTipo(reg.exp1().getVinculo().tipo().tipo());
        	else 
        		reg.setTipo(SintaxisAbstractaTiny.tipo_error());
        }
	}

    public void procesa(Exp_indir indir) {
		indir.exp1().procesa(this);
        if (!claseDe(indir.exp1().tipo(), Tipo_punt.class)) {
            ok &= false;
            indir.setOk(false);
          	GestionErrores.errorTipoInadecuado(indir.exp1().tipo(), Tipo_punt.class);
        }
        else {
            indir.setTipo(SintaxisAbstractaTiny.tipo_ok());
        }        
	}

    public void procesa(Exp_true e) {
        e.setTipo(SintaxisAbstractaTiny.tipo_bool());
     }

    public void procesa(Exp_false e) {
        e.setTipo(SintaxisAbstractaTiny.tipo_bool());
	}

    public void procesa(Exp_litEnt litEnt) {
        litEnt.setTipo(SintaxisAbstractaTiny.tipo_int());
	}

    public void procesa(Exp_litReal litReal) {
        litReal.setTipo(SintaxisAbstractaTiny.tipo_real());
	}

    public void procesa(Exp_litCadena litCadena) {
        litCadena.setTipo(SintaxisAbstractaTiny.tipo_string());
	}

    public void procesa(Exp_iden iden) {
    	if (claseDe(iden.getVinculo(), Dec_simple.class)) {
    		Dec_simple d = (Dec_simple)iden.getVinculo();
    		iden.setTipo(d.tipo());
    	}
    	else if (claseDe(iden.getVinculo(), Pformal_ref.class)) {
    		Pformal_ref d = (Pformal_ref)iden.getVinculo();
    		iden.setTipo(d.tipo());
    	}
    	else if (claseDe(iden.getVinculo(), Pformal_noref.class)) {
    		Pformal_noref d = (Pformal_noref)iden.getVinculo();
    		iden.setTipo(d.tipo());
    	}
    	else 
    		iden.setTipo(SintaxisAbstractaTiny.tipo_error());
	}

    public void procesa(Exp_null ex_null) {
        ex_null.setTipo(SintaxisAbstractaTiny.tipo_null());
	}


    private Tipo tipo_bin1(Exp e0, Exp e1) {
    	Class t0 = e0.tipo().getClass();
    	Class t1 = e1.tipo().getClass();
    	
    	if (compatibles(e0,e1)) 
    		return e0.tipo();
    	else if (t1 == Exp_litReal.class) {
    		if (t0 == Exp_litReal.class || t0 == Exp_litEnt.class)
    			return e1.tipo();
    		else 
    			return SintaxisAbstractaTiny.tipo_error();
    	}    	
    	else if (t0 == Exp_litReal.class) {
    		if (t1 == Exp_litReal.class || t1 == Exp_litEnt.class)
    			return e0.tipo();
    		else 
    			return SintaxisAbstractaTiny.tipo_error();
    	}
    	else 
    		return SintaxisAbstractaTiny.tipo_error();
    }

    private Tipo tipo_bin2(Exp e0, Exp e1) {
    	Class t0 = e0.tipo().getClass();
    	Class t1 = e1.tipo().getClass();
    	
    	if ((t0 == Exp_litReal.class || t0 == Exp_litEnt.class) && (t1 == Exp_litReal.class || t0 == Exp_litEnt.class))
    		return SintaxisAbstractaTiny.tipo_bool();
    	else if (t0 == Tipo_bool.class && t1 == Tipo_bool.class)
    		return SintaxisAbstractaTiny.tipo_bool();
    	else if (t0 == Exp_litCadena.class && t1 == Exp_litCadena.class)
    		return SintaxisAbstractaTiny.tipo_bool();
    	else
    		return SintaxisAbstractaTiny.tipo_error();
    }


    private Tipo tipo_bin3(Exp e0, Exp e1) {
    	Class t0 = e0.tipo().getClass();
    	Class t1 = e1.tipo().getClass();
    	
    	 if ((t0 == Exp_litReal.class || t0 == Exp_litEnt.class) && (t1 == Exp_litReal.class || t0 == Exp_litEnt.class))
     		return SintaxisAbstractaTiny.tipo_bool();
     	else if (t0 == Tipo_bool.class && t1 == Tipo_bool.class)
     		return SintaxisAbstractaTiny.tipo_bool();
     	else if (t0 == Exp_litCadena.class && t1 == Exp_litCadena.class)
     		return SintaxisAbstractaTiny.tipo_bool();
        else if ((t0 == Tipo_punt.class || t0 == Inst_nl.class) && (t1 == Tipo_punt.class || t1 == Inst_nl.class))
            return SintaxisAbstractaTiny.tipo_bool();
        else
            return SintaxisAbstractaTiny.tipo_error();
    }


    //En las compatibilidades, ha de seguirse el algoritmo de unificación de tipos y llevar cuenta de las ecuaciones entre tipos compatibles
    //Añadir lista para mayor eficiencia con las compatibilidades
    private boolean compatibles(Exp e1, Exp e2) {
    	Class t1 = e1.tipo().getClass();
    	Class t2 = e2.tipo().getClass();
    	if (t1 == t2)
            return true;
        else if (t1 == Exp_litReal.class && t2 == Exp_litEnt.class)
        	return true;
        else if (t1 == Tipo_array.class && t1 == Tipo_array.class) {
        	Tipo_array a1 = (Tipo_array)e1.getVinculo().tipo();
        	Tipo_array a2 = (Tipo_array)e2.getVinculo().tipo();
        	if (a1.getTam() != a2.getTam())
        		return false;
        	else 
        		return true;
        }
        else if (t1 == Tipo_struct.class && t2 == Tipo_struct.class) {
        	Tipo_struct s1 = (Tipo_struct)e1.getVinculo().tipo();
        	Tipo_struct s2 = (Tipo_struct)e2.getVinculo().tipo();
        	Map<String,Nodo> r1 = s1.accedeReg();
        	Map<String,Nodo> r2 = s1.accedeReg();

        	if (r1 == null || r2 == null || r1.size() != r2.size())
        		return false;
        	
            for (String id : r1.keySet()) {
                if (!compatibles(r1.get(id).tipo().tipo(), r2.get(id).tipo().tipo())) {
                    return false;
                }
            }
        	
        	return true;
        }
        else if (t1 == Tipo_punt.class && t2 == Inst_nl.class)
        	return true;
        else if (t1 == Tipo_punt.class && t2 == Tipo_punt.class) {
        	Tipo_punt p1 = (Tipo_punt)e1.getVinculo().tipo();
        	Tipo_punt p2 = (Tipo_punt)e2.getVinculo().tipo();
        	if (p1.tipo().tipo() != p2.tipo().tipo())
        		return false;
        	else 
        		return true;
        }
        else
            return false;
    }

    private boolean compatibles(Tipo t1, Tipo t2) {
    	if (t1.getClass() == t2.getClass()) {
            if (claseDe(t1, Tipo_struct.class)) {
                Tipo_struct s1 = (Tipo_struct)t1;
                Tipo_struct s2 = (Tipo_struct)t2;
                Map<String,Nodo> r1 = s1.accedeReg();
                Map<String,Nodo> r2 = s1.accedeReg();

                if (r1 == null || r2 == null || r1.size() != r2.size())
        		    return false;
        	
                for (String id : r1.keySet()) {
                    if (!compatibles(r1.get(id).tipo().tipo(), r2.get(id).tipo().tipo())) {
                        return false;
                    }
                }
            }
            else if (claseDe(t1, Tipo_array.class)) {
                Tipo_array a1 = (Tipo_array)t1;
                Tipo_array a2 = (Tipo_array)t2;
                if (a1.getTam() != a2.getTam())
                    return false;
                else return compatibles(a1.tipo(), a2.tipo());
            }
            return true;
        }
        else if (claseDe(t1, Tipo_real.class) && claseDe(t2, Tipo_int.class))
            return true;
        else if (claseDe(t1, Tipo_punt.class) && claseDe(t2, Tipo_null.class))
            return true;
        else return false;
    }

	
	private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    } 


    private boolean ambos_ok(Nodo n1, Nodo n2) {
        return n1.tipo().getClass() == Tipo_ok.class && n2.tipo().getClass() == Tipo_ok.class;
    }

	@Override
	public void procesa(Tipo_punt tipo) {
		// TODO Auto-generated method stub
		
	}

}
