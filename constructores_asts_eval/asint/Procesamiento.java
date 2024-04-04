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
	void procesa(Prog prog);

	void procesa(Blo blo);

	void procesa(Si_decs decs);

	void procesa(No_decs decs);

	void procesa(Muchas_decs decs);

	void procesa(Una_dec decs);

	void procesa(Var v);

	void procesa(Muchas_var vars);

	void procesa(Una_var vars);

	void procesa(Dec_simple dec);

	void procesa(Dec_type dec);

	void procesa(Dec_proc dec);

	void procesa(Tipo_array tipo);

	void procesa(Tipo_punt tipo);

	void procesa(Tipo_bool tipo);

	void procesa(Tipo_int tipo);

	void procesa(Tipo_real tipo);

	void procesa(Tipo_string tipo);

	void procesa(Tipo_ident tipo);

	void procesa(Tipo_struct tipo);

	void procesa(Si_inst insts);

	void procesa(No_inst insts);

	void procesa(Muchas_inst insts);

	void procesa(Una_inst insts);

	void procesa(Si_pformal pfmls);

	void procesa(No_pformal pfmls);

	void procesa(Muchos_pformal pfmls);

	void procesa(Un_pformal pfmls);

	void procesa(Pformal_ref pfml);

	void procesa(Pformal_noref pfml);

	void procesa(Si_preales preales);

	void procesa(No_preales preales);

	void procesa(Muchas_exp lpreal);

	void procesa(Una_exp lpreal);

	void procesa(Inst_eval inst);

	void procesa(Inst_if inst);

	void procesa(Inst_else inst);

	void procesa(Inst_while inst);

	void procesa(Inst_new inst);

	void procesa(Inst_delete inst);

	void procesa(Inst_read inst);

	void procesa(Inst_write inst);

	void procesa(Inst_call inst);

	void procesa(Inst_nl inst);

	void procesa(Inst_blo inst);

	void procesa(Exp_asig exp);

	void procesa(Exp_menor exp);

	void procesa(Exp_menIgual exp);

	void procesa(Exp_mayor exp);

	void procesa(Exp_mayIgual exp);

	void procesa(Exp_igual exp);

	void procesa(Exp_dist exp);

	void procesa(Exp_suma exp);

	void procesa(Exp_resta exp);

	void procesa(Exp_mult exp);

	void procesa(Exp_div exp);

	void procesa(Exp_mod exp);

	void procesa(Exp_and and);

	void procesa(Exp_or or);

	void procesa(Exp_menos menos);

	void procesa(Exp_not not);

	void procesa(Exp_index index);

	void procesa(Exp_reg reg);

	void procesa(Exp_indir indir);

	void procesa(Exp_true ex_true);

	void procesa(Exp_false ex_false);

	void procesa(Exp_litEnt litEnt);

	void procesa(Exp_litReal litReal);

	void procesa(Exp_litCadena litCadena);

	void procesa(Exp_iden iden);

	void procesa(Exp_null ex_null);
}
