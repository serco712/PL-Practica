package asint;

import asint.SintaxisAbstractaTiny.Prog;
import asint.SintaxisAbstractaTiny.Blo;
import asint.SintaxisAbstractaTiny.Dec_proc;
import asint.SintaxisAbstractaTiny.Dec_simple;
import asint.SintaxisAbstractaTiny.Dec_type;
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
import asint.SintaxisAbstractaTiny.Tipo_punt;
import asint.SintaxisAbstractaTiny.Tipo_real;
import asint.SintaxisAbstractaTiny.Tipo_string;
import asint.SintaxisAbstractaTiny.Tipo_struct;
import asint.SintaxisAbstractaTiny.Un_pformal;
import asint.SintaxisAbstractaTiny.Una_dec;
import asint.SintaxisAbstractaTiny.Una_exp;
import asint.SintaxisAbstractaTiny.Una_inst;
import asint.SintaxisAbstractaTiny.Una_var;
import asint.SintaxisAbstractaTiny.Var;

public interface Procesamiento {
	public void procesa(Prog prog);

	public void procesa(Blo blo);

	public void procesa(Si_decs decs);

	public void procesa(No_decs decs);

	public void procesa(Muchas_decs decs);

	public void procesa(Una_dec decs);

	public void procesa(Var v);

	public void procesa(Muchas_var vars);

	public void procesa(Una_var vars);

	public void procesa(Dec_simple dec);

	public void procesa(Dec_type dec);

	public void procesa(Dec_proc dec);

	public void procesa(Tipo_array tipo);

	public void procesa(Tipo_punt tipo);

	public void procesa(Tipo_bool tipo);

	public void procesa(Tipo_int tipo);

	public void procesa(Tipo_real tipo);

	public void procesa(Tipo_string tipo);

	public void procesa(Tipo_ident tipo);

	public void procesa(Tipo_struct tipo);

	public void procesa(Si_inst insts);

	public void procesa(No_inst insts);

	public void procesa(Muchas_inst insts);

	public void procesa(Una_inst insts);

	public void procesa(Si_pformal pfmls);

	public void procesa(No_pformal pfmls);

	public void procesa(Muchos_pformal pfmls);

	public void procesa(Un_pformal pfmls);

	public void procesa(Pformal_ref pfml);

	public void procesa(Pformal_noref pfml);

	public void procesa(Si_preales preales);

	public void procesa(No_preales preales);

	public void procesa(Muchas_exp lpreal);

	public void procesa(Una_exp lpreal);

	public void procesa(Inst_eval inst);

	public void procesa(Inst_if inst);

	public void procesa(Inst_else inst);

	public void procesa(Inst_while inst);

	public void procesa(Inst_new inst);

	public void procesa(Inst_delete inst);

	public void procesa(Inst_read inst);

	public void procesa(Inst_write inst);

	public void procesa(Inst_call inst);

	public void procesa(Inst_nl inst);

	public void procesa(Inst_blo inst);

	public void procesa(Exp_asig exp);

	public void procesa(Exp_menor exp);

	public void procesa(Exp_menIgual exp);

	public void procesa(Exp_mayor exp);

	public void procesa(Exp_mayIgual exp);

	public void procesa(Exp_igual exp);

	public void procesa(Exp_dist exp);

	public void procesa(Exp_suma exp);

	public void procesa(Exp_resta exp);

	public void procesa(Exp_mult exp);

	public void procesa(Exp_div exp);

	public void procesa(Exp_mod exp);

	public void procesa(Exp_and and);

	public void procesa(Exp_or or);

	public void procesa(Exp_menos menos);

	public void procesa(Exp_not not);

	public void procesa(Exp_index index);

	public void procesa(Exp_reg reg);

	public void procesa(Exp_indir indir);

	public void procesa(Exp_true ex_true);

	public void procesa(Exp_false ex_false);

	public void procesa(Exp_litEnt litEnt);

	public void procesa(Exp_litReal litReal);

	public void procesa(Exp_litCadena litCadena);

	public void procesa(Exp_iden iden);

	public void procesa(Exp_null ex_null);
}
