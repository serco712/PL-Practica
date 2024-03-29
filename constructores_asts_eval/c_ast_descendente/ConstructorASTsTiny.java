/* Generated By:JavaCC: Do not edit this line. ConstructorASTsTiny.java */
package c_ast_descendente;
import asint.ClaseSemanticaTiny;
import asint.SintaxisAbstractaTiny.Prog;
import asint.SintaxisAbstractaTiny.Blo;
import asint.SintaxisAbstractaTiny.Decs;
import asint.SintaxisAbstractaTiny.LDecs;
import asint.SintaxisAbstractaTiny.LVar;
import asint.SintaxisAbstractaTiny.Var;
import asint.SintaxisAbstractaTiny.Dec;
import asint.SintaxisAbstractaTiny.Tipo;
import asint.SintaxisAbstractaTiny.Insts;
import asint.SintaxisAbstractaTiny.LInst;
import asint.SintaxisAbstractaTiny.PFmls;
import asint.SintaxisAbstractaTiny.LPFml;
import asint.SintaxisAbstractaTiny.PFml;
import asint.SintaxisAbstractaTiny.PReales;
import asint.SintaxisAbstractaTiny.LPReal;
import asint.SintaxisAbstractaTiny.Inst;
import asint.SintaxisAbstractaTiny.Exp;
import c_ast_ascendente.UnidadLexica.StringLocalizado;

public class ConstructorASTsTiny implements ConstructorASTsTinyConstants {
   private ClaseSemanticaTiny sem = new ClaseSemanticaTiny();

  final public Prog analiza() throws ParseException {
    trace_call("analiza");
    try {
       Prog prog;
      prog = programa();
      jj_consume_token(0);
             {if (true) return prog;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("analiza");
    }
  }

  final public Prog programa() throws ParseException {
    trace_call("programa");
    try {
     Blo bloq;
      bloq = bloq();
           {if (true) return sem.prog(bloq);}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("programa");
    }
  }

  final public Blo bloq() throws ParseException {
    trace_call("bloq");
    try {
      Insts insts; Decs decs;
      jj_consume_token(llaveAp);
      insts = instrucciones();
      decs = declaraciones();
      jj_consume_token(llaveCi);
            {if (true) return sem.bloq(decs, insts);}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("bloq");
    }
  }

  final public Decs declaraciones() throws ParseException {
    trace_call("declaraciones");
    try {
       LDecs decs;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case indireccion:
        decs = lista_declaraciones();
        jj_consume_token(FINAL);
            {if (true) return sem.si_decs(decs);}
        break;
      default:
        jj_la1[0] = jj_gen;
            {if (true) return sem.no_decs();}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("declaraciones");
    }
  }

  final public LDecs lista_declaraciones() throws ParseException {
    trace_call("lista_declaraciones");
    try {
        Dec dec; LDecs decs;
      dec = declaracion();
      decs = rlista_decs(sem.una_dec(dec));
            {if (true) return decs;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("lista_declaraciones");
    }
  }

  final public LDecs rlista_decs(LDecs decsh) throws ParseException {
    trace_call("rlista_decs");
    try {
        Dec dec; LDecs decs;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case puntoYComa:
        jj_consume_token(puntoYComa);
        dec = declaracion();
        decs = rlista_decs(sem.muchas_decs(decsh,dec));
            {if (true) return decs;}
        break;
      default:
        jj_la1[1] = jj_gen;
            {if (true) return decsh;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("rlista_decs");
    }
  }

  final public LVar lista_variables() throws ParseException {
    trace_call("lista_variables");
    try {
        Var v; LVar vars;
      v = variable();
      vars = rlista_var(sem.una_var(v));
            {if (true) return vars;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("lista_variables");
    }
  }

  final public LVar rlista_var(LVar varsh) throws ParseException {
    trace_call("rlista_var");
    try {
        Var v; LVar vars;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case coma:
        jj_consume_token(coma);
        v = variable();
        vars = rlista_decs(sem.muchas_decs(varsh,v));
            {if (true) return vars;}
        break;
      default:
        jj_la1[2] = jj_gen;
            {if (true) return varsh;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("rlista_var");
    }
  }

  final public Var variable() throws ParseException {
    trace_call("variable");
    try {
         Tipo t; Token id;
      t = tipo();
      id = jj_consume_token(iden);
             {if (true) return (Var)sem.var(t,id.image);}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("variable");
    }
  }

  final public Dec declaracion() throws ParseException {
    trace_call("declaracion");
    try {
        Var v; Token type, id; PFmls pfmls; Blo bloq;
      v = variable();
           {if (true) return (Dec)sem.dec_simple(v).ponFila(id.beginLine).ponCol(id.beginColumn);}
      v = variable();
           {if (true) return (Dec)sem.dec_type(v).ponFila(id.beginLine).ponCol(id.beginColumn);}
      id = jj_consume_token(iden);
      pfmls = par_formales();
      bloq = bloq();
           {if (true) return (Dec)sem.dec_proc(id.image, pfmls, bloq).ponFila(id.beginLine).ponCol(id.beginColumn);}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("declaracion");
    }
  }

  final public Tipo tipo() throws ParseException {
    trace_call("tipo");
    try {
            Tipo tip2, tiparray;
      tip2 = tipo2();
      tiparray = rtipo(tipo2());
                   {if (true) return tiparrays;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("tipo");
    }
  }

  final public Tipo rtipo(Tipo tiph) throws ParseException {
    trace_call("rtipo");
    try {
            Token lent; Tipo tip;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case corcheteAp:
        jj_consume_token(corcheteAp);
        lent = jj_consume_token(literalEntero);
        jj_consume_token(corcheteCi);
        tip = rtipo(sem.tipo_array(tiph, lent.image).ponFila(lent.beginLine).ponCol(lent.beginColumn));
                    {if (true) return tip;}
        break;
      default:
        jj_la1[3] = jj_gen;
            {if (true) return tiph;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("rtipo");
    }
  }

  final public Tipo tipo2() throws ParseException {
    trace_call("tipo2");
    try {
            Tipo tip;
      jj_consume_token(indireccion);
      tip = tipo2();
                   {if (true) return (Tipo)sem.tipo_punt(tip);}
      tip = tipo3();
                   {if (true) return tip;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("tipo2");
    }
  }

  final public Tipo tipo3() throws ParseException {
    trace_call("tipo3");
    try {
            Token tok; LVar vars;
      tok = jj_consume_token(bool);
                   {if (true) return (Tipo)sem.tipo_bool();}
      tok = jj_consume_token(INT);
                   {if (true) return (Tipo)sem.tipo_int();}
      tok = jj_consume_token(real);
                   {if (true) return (Tipo)sem.tipo_real();}
      tok = jj_consume_token(string);
                   {if (true) return (Tipo)sem.tipo_string();}
      tok = jj_consume_token(iden);
                   {if (true) return (Tipo)sem.tipo_ident(tok.image).ponFila(tok.beginLine).ponCol(tok.beginColumn);}
      tok = jj_consume_token(struct);
      jj_consume_token(llaveAp);
      vars = lista_variables();
      jj_consume_token(llaveCi);
                   {if (true) return (Tipo)sem.tipo_struct(vars);}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("tipo3");
    }
  }

  final public Insts instrucciones() throws ParseException {
    trace_call("instrucciones");
    try {
        LInst insts;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case arroba:
        insts = lista_instrucciones();
            {if (true) return (Insts)sem.si_inst(insts);}
        break;
      default:
        jj_la1[4] = jj_gen;
            {if (true) return (Insts)sem.no_inst();}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("instrucciones");
    }
  }

  final public LInst lista_instrucciones() throws ParseException {
    trace_call("lista_instrucciones");
    try {
        Inst inst; LInst insts;
      inst = instruccion();
      insts = rlista_inst(sem.una_inst(inst));
            {if (true) return insts;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("lista_instrucciones");
    }
  }

  final public LInst rlista_inst(LInst instsh) throws ParseException {
    trace_call("rlista_inst");
    try {
        Inst inst; LInst insts;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case puntoYComa:
        jj_consume_token(puntoYComa);
        inst = instruccion();
        insts = rlista_inst(sem.muchas_decs(instsh,instrucciones()));
            {if (true) return insts;}
        break;
      default:
        jj_la1[5] = jj_gen;
            {if (true) return instsh;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("rlista_inst");
    }
  }

  final public PFmls par_formales() throws ParseException {
    trace_call("par_formales");
    try {
        LPFml pfmls;
      jj_consume_token(parentesisAp);
      pfmls = rpar_formales();
            {if (true) return (PFmls)sem.no_pformal();}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("par_formales");
    }
  }

  final public PFmls rpar_formales() throws ParseException {
    trace_call("rpar_formales");
    try {
        LPFml pfmls;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case indireccion:
        pfmls = lista_par_formal();
            {if (true) return (PFml)sem.si_pformal(pfmls);}
        break;
      default:
        jj_la1[6] = jj_gen;
            {if (true) return (PFmls)sem.no_pformal();}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("rpar_formales");
    }
  }

  final public LPFml lista_par_formal() throws ParseException {
    trace_call("lista_par_formal");
    try {
        PFml pfml; LPFml pfmls;
      pfml = par_formal();
      pfmls = rlista_par_formal(sem.un_pformal(pfml));
            {if (true) return pfmls;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("lista_par_formal");
    }
  }

  final public LPFml rlista_par_formal(LPFml pfmlsh) throws ParseException {
    trace_call("rlista_par_formal");
    try {
        PFml pfml; LPFml pfmls;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case coma:
        jj_consume_token(coma);
        pfml = par_formal();
        pfmls = rlista_par_formal(sem.muchos_pformal(pfml, pfmlsh));
            {if (true) return pfmls;}
        break;
      default:
        jj_la1[7] = jj_gen;
            {if (true) return pfmlsh;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("rlista_par_formal");
    }
  }

  final public PFml par_formal() throws ParseException {
    trace_call("par_formal");
    try {
            Tipo tipo; LPFml lpfml; Token id;
      tipo = tipo();
      id = jj_consume_token(iden);
      lpfml = rpar_formal(sem.pformal_ref(tipo,id.image));
              {if (true) return (PFml)lpfml.ponFila(id.beginLine).ponCol(id.beginColumn);}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("par_formal");
    }
  }

  final public PFml rpar_formal() throws ParseException {
    trace_call("rpar_formal");
    try {
            Token id;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case porReferencia:
        jj_consume_token(porReferencia);
        id = jj_consume_token(iden);
              {if (true) return (PFml)sem.pformal_ref(tipo(), id.image).ponFila(id.beginLine).ponCol(id.beginColumn);}
        break;
      case iden:
        id = jj_consume_token(iden);
                  {if (true) return (PFml)sem.pformal_noref(tipo(), id.image).ponFila(id.beginLine).ponCol(id.beginColumn);}
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("rpar_formal");
    }
  }

  final public PReales par_reales() throws ParseException {
    trace_call("par_reales");
    try {
            LPReal lpreal;
      jj_consume_token(parentesisAp);
      lpreal = rpar_reales(sem.no_preales());
              {if (true) return lpreal;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("par_reales");
    }
  }

  final public PReales rpar_reales() throws ParseException {
    trace_call("rpar_reales");
    try {
        LPReal lpreal;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case not:
      case operadorResta:
        lpreal = lista_par_real();
        jj_consume_token(parentesisCi);
          {if (true) return (PReales)sem.si_preales(lpreal);}
        break;
      case parentesisCi:
        jj_consume_token(parentesisCi);
           {if (true) return (PReales)sem.no_preales();}
        break;
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("rpar_reales");
    }
  }

  final public LPReal lista_par_real() throws ParseException {
    trace_call("lista_par_real");
    try {
        Exp e0; LPReal rlpreal;
      e0 = e0();
      rlpreal = rlista_par_real(sem.una_exp(e0));
          {if (true) return rlpreal;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("lista_par_real");
    }
  }

  final public LPReal rlista_par_real(LPReal rlprealh, Exp e0) throws ParseException {
    trace_call("rlista_par_real");
    try {
        LPReal lpreal;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case coma:
        jj_consume_token(coma);
        lpreal = rlista_par_real(sem.muchas_exp(e0, rlprealh));
          {if (true) return lpreal;}
        break;
      default:
        jj_la1[10] = jj_gen;
                  {if (true) return sem.una_exp(e0);}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("rlista_par_real");
    }
  }

  final public Inst instruccion() throws ParseException {
    trace_call("instruccion");
    try {
        Exp e0; Blo bloq; Inst rif; Token tok; PReales preales;
      jj_consume_token(arroba);
      e0 = e0();
          {if (true) return sem.inst_eval(e0);}
      tok = jj_consume_token(IF);
      e0 = e0();
      bloq = bloq();
      rif = rif(sem.inst_if(e0, bloq));
          {if (true) return rif;}
      tok = jj_consume_token(WHILE);
      e0 = e0();
      bloq = bloq();
          {if (true) return sem.inst_while(e0, bloq);}
      tok = jj_consume_token(NEW);
      e0 = e0();
          {if (true) return sem.inst_new(e0);}
      tok = jj_consume_token(delete);
      e0 = e0();
          {if (true) return sem.inst_delete(e0);}
      tok = jj_consume_token(read);
      e0 = e0();
          {if (true) return sem.inst_read(e0);}
      tok = jj_consume_token(write);
      e0 = e0();
          {if (true) return sem.inst_write(e0);}
      tok = jj_consume_token(call);
      jj_consume_token(iden);
      preales = par_reales();
          {if (true) return sem.inst_call(id, preales);}
      tok = jj_consume_token(nl);
          {if (true) return sem.inst_nl();}
      bloq = bloq();
          {if (true) return sem.inst_bloq(bloq);}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("instruccion");
    }
  }

  final public Inst rif(Exp e0) throws ParseException {
    trace_call("rif");
    try {
        Blo bloq;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ELSE:
        jj_consume_token(ELSE);
        bloq = bloq();
          {if (true) return sem.inst_else(e0, bloq, bloq);}
        break;
      default:
        jj_la1[11] = jj_gen;
          {if (true) return inst_if(e0,bloq);}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("rif");
    }
  }

  final public Exp e0() throws ParseException {
    trace_call("e0");
    try {
         Exp e1,e2;
      e1 = e1();
      e2 = re0(e1);
             {if (true) return e2;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("e0");
    }
  }

  final public Exp re0(Exp e) throws ParseException {
    trace_call("re0");
    try {
         Exp e1;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case operadorAsig:
        jj_consume_token(operadorAsig);
        e1 = e0();
              {if (true) return (Exp)sem.exp_asig(e,e1);}
        break;
      default:
        jj_la1[12] = jj_gen;
              {if (true) return e;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("re0");
    }
  }

  final public Exp e1() throws ParseException {
    trace_call("e1");
    try {
         Exp e1,e2;
      e1 = e2();
      e2 = re1(e1);
             {if (true) return e2;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("e1");
    }
  }

  final public Exp re1(Exp eh) throws ParseException {
    trace_call("re1");
    try {
         String op; Exp e1,e2;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case operadorMenor:
      case operadorMayor:
      case operadorIgual:
      case operadorDistinto:
      case operadorMenIgual:
      case operadorMayIgual:
        op = op1();
        e1 = e2();
        e2 = re0(sem.mkopBin(op,eh,e1));
              {if (true) return e2;}
        break;
      default:
        jj_la1[13] = jj_gen;
              {if (true) return eh;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("re1");
    }
  }

  final public Exp e2() throws ParseException {
    trace_call("e2");
    try {
         Exp e1, e2, e3;
      e1 = e3();
      e2 = re2p(e1);
      e3 = re2((Exp)e2);
             {if (true) return e3;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("e2");
    }
  }

  final public Exp re2p(Exp eh) throws ParseException {
    trace_call("re2p");
    try {
         Exp e3;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case operadorResta:
        jj_consume_token(operadorResta);
        e3 = e3();
              {if (true) return (Exp)sem.exp_resta(eh,e3);}
        break;
      default:
        jj_la1[14] = jj_gen;
              {if (true) return eh;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("re2p");
    }
  }

  final public Exp re2(Exp eh) throws ParseException {
    trace_call("re2");
    try {
         Exp e1,e2;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case operadorSuma:
        jj_consume_token(operadorSuma);
        e1 = e3();
        e2 = re2(sem.exp_suma(eh,e1));
              {if (true) return e2;}
        break;
      default:
        jj_la1[15] = jj_gen;
              {if (true) return eh;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("re2");
    }
  }

  final public Exp e3() throws ParseException {
    trace_call("e3");
    try {
         Exp e1,e2;
      e1 = e4();
      e2 = re3(e1);
             {if (true) return e2;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("e3");
    }
  }

  final public Exp re3(Exp e) throws ParseException {
    trace_call("re3");
    try {
         Exp e1;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case and:
        jj_consume_token(and);
        e1 = e3();
             {if (true) return (Exp)sem.exp_and(e1,e);}
        break;
      case or:
        jj_consume_token(or);
        e1 = e4();
              {if (true) return (Exp)sem.exp_or(e1,e);}
        break;
      default:
        jj_la1[16] = jj_gen;
              {if (true) return e;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("re3");
    }
  }

  final public Exp e4() throws ParseException {
    trace_call("e4");
    try {
         Exp e1,e2;
      e1 = e5();
      e2 = re4(e1);
             {if (true) return e2;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("e4");
    }
  }

  final public Exp re4(Exp eh) throws ParseException {
    trace_call("re4");
    try {
         String op; Exp e1,e2;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case operadorMul:
      case operadorDiv:
      case operadorMod:
        op = op4();
        e1 = e5();
        e2 = re4(sem.mkopBin(op,eh,e1));
              {if (true) return e2;}
        break;
      default:
        jj_la1[17] = jj_gen;
              {if (true) return eh;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("re4");
    }
  }

  final public Exp e5() throws ParseException {
    trace_call("e5");
    try {
         String op; Exp e6;
      op = op5();
      e6 = e6();
             {if (true) return (Exp)sem.mkopUn(op,e6);}
      e6 = e6();
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("e5");
    }
  }

  final public Exp e6() throws ParseException {
    trace_call("e6");
    try {
         Exp e6,e7;
      e6 = e7();
      e7 = re6(e6);
              {if (true) return e7;}
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("e6");
    }
  }

  final public Exp re6(Exp eh) throws ParseException {
    trace_call("re6");
    try {
         Token t; Exp e6;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case corcheteAp:
        jj_consume_token(corcheteAp);
        e6 = e0();
        jj_consume_token(corcheteCi);
               {if (true) return (Exp)sem.exp_index(eh,e6);}
        break;
      case punto:
        jj_consume_token(punto);
        t = jj_consume_token(iden);
               {if (true) return (Exp)sem.exp_reg(eh,t);}
        break;
      case indireccion:
        jj_consume_token(indireccion);
               {if (true) return (Exp)sem.exp_indir(eh);}
        break;
      default:
        jj_la1[18] = jj_gen;
               {if (true) return eh;}
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("re6");
    }
  }

  final public Exp e7() throws ParseException {
    trace_call("e7");
    try {
        Token t; Exp e;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case TRUE:
        t = jj_consume_token(TRUE);
           {if (true) return (Exp)sem.exp_true();}
        break;
      case FALSE:
        t = jj_consume_token(FALSE);
           {if (true) return (Exp)sem.exp_false();}
        break;
      case iden:
        t = jj_consume_token(iden);
           {if (true) return (Exp)sem.exp_litEnt(t.image).ponFila(t.beginLine).ponCol(t.beginColumn);}
        break;
      case literalEntero:
        t = jj_consume_token(literalEntero);
           {if (true) return (Exp)sem.exp_litReal(t.image).ponFila(t.beginLine).ponCol(t.beginColumn);}
        break;
      case literalReal:
        t = jj_consume_token(literalReal);
           {if (true) return (Exp)sem.exp_iden(t.image).ponFila(t.beginLine).ponCol(t.beginColumn);}
        break;
      case literalCadena:
        t = jj_consume_token(literalCadena);
           {if (true) return (Exp)sem.exp_litCad(t.image).ponFila(t.beginLine).ponCol(t.beginColumn);}
        break;
      case parentesisAp:
        jj_consume_token(parentesisAp);
        e = e0();
        jj_consume_token(parentesisCi);
           {if (true) return e;}
        break;
      default:
        jj_la1[19] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("e7");
    }
  }

  final public String op1() throws ParseException {
    trace_call("op1");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case operadorMenor:
        jj_consume_token(operadorMenor);
          {if (true) return "<";}
        break;
      case operadorMenIgual:
        jj_consume_token(operadorMenIgual);
          {if (true) return "<=";}
        break;
      case operadorMayor:
        jj_consume_token(operadorMayor);
          {if (true) return ">";}
        break;
      case operadorMayIgual:
        jj_consume_token(operadorMayIgual);
          {if (true) return ">=";}
        break;
      case operadorIgual:
        jj_consume_token(operadorIgual);
          {if (true) return "==";}
        break;
      case operadorDistinto:
        jj_consume_token(operadorDistinto);
          {if (true) return "!=";}
        break;
      default:
        jj_la1[20] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("op1");
    }
  }

  final public String op4() throws ParseException {
    trace_call("op4");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case operadorMul:
        jj_consume_token(operadorMul);
           {if (true) return "*";}
        break;
      case operadorDiv:
        jj_consume_token(operadorDiv);
           {if (true) return "/";}
        break;
      case operadorMod:
        jj_consume_token(operadorMod);
           {if (true) return "%";}
        break;
      default:
        jj_la1[21] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("op4");
    }
  }

  final public String op5() throws ParseException {
    trace_call("op5");
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case not:
        jj_consume_token(not);
           {if (true) return "not";}
        break;
      case operadorResta:
        jj_consume_token(operadorResta);
           {if (true) return "-";}
        break;
      default:
        jj_la1[22] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    throw new Error("Missing return statement in function");
    } finally {
      trace_return("op5");
    }
  }

  /** Generated Token Manager. */
  public ConstructorASTsTinyTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[23];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x8000,0x0,0x200000,0x0,0x0,0x0,0x0,0x6000,0x0,0x80000000,0x30000,0x0,0x0,0x8000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x200000,0x40000,0x100000,0x4000000,0x80000,0x40000,0x200000,0x100000,0x800008,0x20020,0x100000,0x0,0x8000,0x7e00,0x20,0x10,0x0,0x1c0,0x4200000,0x1000f,0x7e00,0x1c0,0x20,};
   }

  /** Constructor with InputStream. */
  public ConstructorASTsTiny(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public ConstructorASTsTiny(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ConstructorASTsTinyTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public ConstructorASTsTiny(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ConstructorASTsTinyTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public ConstructorASTsTiny(ConstructorASTsTinyTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ConstructorASTsTinyTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      trace_token(token, "");
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
      trace_token(token, " (in getNextToken)");
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[60];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 23; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 60; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  private int trace_indent = 0;
  private boolean trace_enabled = true;

/** Enable tracing. */
  final public void enable_tracing() {
    trace_enabled = true;
  }

/** Disable tracing. */
  final public void disable_tracing() {
    trace_enabled = false;
  }

  private void trace_call(String s) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.println("Call:   " + s);
    }
    trace_indent = trace_indent + 2;
  }

  private void trace_return(String s) {
    trace_indent = trace_indent - 2;
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.println("Return: " + s);
    }
  }

  private void trace_token(Token t, String where) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.print("Consumed token: <" + tokenImage[t.kind]);
      if (t.kind != 0 && !tokenImage[t.kind].equals("\"" + t.image + "\"")) {
        System.out.print(": \"" + t.image + "\"");
      }
      System.out.println(" at line " + t.beginLine + " column " + t.beginColumn + ">" + where);
    }
  }

  private void trace_scan(Token t1, int t2) {
    if (trace_enabled) {
      for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
      System.out.print("Visited token: <" + tokenImage[t1.kind]);
      if (t1.kind != 0 && !tokenImage[t1.kind].equals("\"" + t1.image + "\"")) {
        System.out.print(": \"" + t1.image + "\"");
      }
      System.out.println(" at line " + t1.beginLine + " column " + t1.beginColumn + ">; Expected token: <" + tokenImage[t2] + ">");
    }
  }

}
