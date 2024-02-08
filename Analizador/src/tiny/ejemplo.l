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
parteEntera = {digitoPositivo}{digito}*
parteDecimal = {digito}* {digitoPositivo}
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
%LI
while = (w|W)(h|H)(i|I)(l|L)(e|E)
struct = (s|S)(t|T)(r|R)(u|U)(c|C)(t|T)
new = (n|N)(e|E)(w|W)
delete = (d|D)(e|E)(l|L)(e|E)(t|T)(e|E)
read = (r|R)(e|E)(a|A)(d|D)
write = (w|W)(r|R)(i|I)(t|T)(e|E)
nl = (n|N)(l|L)
type = (t|T)(y|Y)(p|P)(e|E)
call = (c|C)(a|A)(l|L)(l|L)
literalEntero = [\+,\-]?{parteEntera}
literalReal = [\+,\-]?{parteEntera}(\.{parteDecimal}|{parteExponencial}|\.{parteDecimal}{parteExponencial})
literalCadena = \"[^\"]\"
%Laura
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
%Rodrigo
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
{separador}               {}
{comentario}              {}
{evalua}                  {return ops.unidadEvalua();}
{donde}                   {return ops.unidadDonde();}
{identificador}           {return ops.unidadId();}
{numeroEntero}            {return ops.unidadEnt();}
{numeroReal}              {return ops.unidadReal();}
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorMultiplicacion}  {return ops.unidadMul();}
{operadorDivision}        {return ops.unidadDiv();}
{parentesisApertura}      {return ops.unidadPAp();}
{parentesisCierre}        {return ops.unidadPCierre();} 
{igual}                   {return ops.unidadIgual();} 
{coma}                    {return ops.unidadComa();}
[^]                       {ops.error();}  