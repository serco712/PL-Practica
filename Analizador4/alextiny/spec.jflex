package tiny;

%%
%line
%column
%class AnalizadorLexicoTiny
%type  UnidadLexica
%unicode

%{
  private ALexOperations ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra = ([A-Z]|[a-z])
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = ({digitoPositivo}{digito}*)|0
parteDecimal = ({digito}* {digitoPositivo})|0
parteExponencial = (e|E)[\+,\-]?{parteEntera}

separador = [ \t\r\b\n]
comentario = ##[^\n]* 

bool = (b|B)(o|O)(o|O)(l|L)
int = (i|I)(n|N)(t|T)
real = (r|R)(e|E)(a|A)(l|L)
string = (s|S)(t|T)(r|R)(i|I)(n|N)(g|G)
and = (a|A)(n|N)(d|D)
or = (o|O)(r|R)
not = (n|N)(o|O)(t|T)
true = (t|T)(r|R)(u|U)(e|E)
false = (f|F)(a|A)(l|L)(s|S)(e|E)
null = (n|N)(u|U)(l|L)(l|L)
proc = (p|P)(r|R)(o|O)(c|C)
if = (i|I)(f|F)
else = (e|E)(l|L)(s|S)(e|E)
while = (w|W)(h|H)(i|I)(l|L)(e|E)
struct = (s|S)(t|T)(r|R)(u|U)(c|C)(t|T)
new = (n|N)(e|E)(w|W)
delete = (d|D)(e|E)(l|L)(e|E)(t|T)(e|E)
read = (r|R)(e|E)(a|A)(d|D)
write = (w|W)(r|R)(i|I)(t|T)(e|E)
nl = (n|N)(l|L)
type = (t|T)(y|Y)(p|P)(e|E)
call = (c|C)(a|A)(l|L)(l|L)
literalReal = {literalEntero}({punto}{parteDecimal}|{parteExponencial}|{punto}{parteDecimal}{parteExponencial})
literalEntero = [\+,\-]?{parteEntera}
literalCadena = \"[^\"]*\"
identificador = (\_|{letra})({letra}|{digito}|\_)*
operadorSuma = \+
operadorResta = \-
operadorMul = \*
operadorDiv = \/
operadorMod = \%
operadorMenor = \<
operadorMayor = \>
operadorIgual = \=\=
operadorMenIgual = \<\=
operadorMayIgual = \>\=
operadorAsig = \=
parentesisAp = \(
parentesisCi = \)
puntoYComa = \;
arroba = \@
coma  = \,
indireccion = \^
final = \&\&
porReferencia = \&
llaveAp = \{
llaveCi = \}
corcheteAp = \[
corcheteCi = \]
punto = \.

%%
{bool}  				  {return ops.unidadBool();}
{int}  					  {return ops.unidadInt();}
{real}  				  {return ops.unidadReal();}
{string}   			      {return ops.unidadString();}
{and}					  {return ops.unidadAnd();}
{or} 					  {return ops.unidadOr();}
{not}  					  {return ops.unidadNot();}
{true} 					  {return ops.unidadTrue();}
{false}  				  {return ops.unidadFalse();}
{null} 					  {return ops.unidadNull();}
{proc}					  {return ops.unidadProc();}
{if}  					  {return ops.unidadIf();}
{else} 					  {return ops.unidadElse();}
{while}					  {return ops.unidadWhile();}
{struct} 				  {return ops.unidadStruct();}
{new}					  {return ops.unidadNew();}
{delete} 				  {return ops.unidadDel();}
{read} 					  {return ops.unidadRead();}
{write}					  {return ops.unidadWrite();}
{nl} 					  {return ops.unidadNl();}
{type} 					  {return ops.unidadType();}
{call} 					  {return ops.unidadCall();}
{literalReal}			  {return ops.unidadlitReal();}
{literalEntero}			  {return ops.unidadlitEnt();}
{literalCadena} 		  {return ops.unidadlitCad();}

{separador}               {}
{comentario}              {}

{identificador}           {return ops.unidadIden();}
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorMul}             {return ops.unidadMult();}
{operadorDiv}             {return ops.unidadDiv();}
{operadorMod}             {return ops.unidadMod();}
{operadorMenor}           {return ops.unidadMenor();}
{operadorMayor}           {return ops.unidadMayor();}
{operadorIgual}           {return ops.unidadIgual();}
{operadorMenIgual}        {return ops.unidadMenIgual();}
{operadorMayIgual}        {return ops.unidadMayIgual();}
{operadorAsig}            {return ops.unidadAsig();}
{parentesisAp}            {return ops.unidadPAp();}
{parentesisCi}        {return ops.unidadPCi();} 
{puntoYComa}              {return ops.unidadPuntoYComa();} 
{arroba}                  {return ops.unidadArroba();} 
{coma}                    {return ops.unidadComa();}
{indireccion}             {return ops.unidadIndireccion();}
{final}                   {return ops.unidadFinal();}
{porReferencia}           {return ops.unidadReferencia();}
{llaveAp}                 {return ops.unidadLlaveAp();}
{llaveCi}                 {return ops.unidadLlaveCi();}
{corcheteAp}              {return ops.unidadCorcheteAp();}
{corcheteCi}              {return ops.unidadCorcheteCi();}
{punto}                   {return ops.unidadPunto();}
[^]                       {ops.error();}  