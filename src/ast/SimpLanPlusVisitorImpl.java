package ast;

import org.antlr.v4.runtime.Token;
import parser.SimpLanPlusBaseVisitor;
import parser.SimpLanPlusParser;

import java.util.ArrayList;

public class SimpLanPlusVisitorImpl extends SimpLanPlusBaseVisitor<Node> {
    /*
     * classe è utilizzata per creare un visitor per l'analisi sintattica di un linguaggio di programmazione = SimpLan
     * visitor  utilizzato per attraversare l'albero sintattico generato da un parser ANTLR e creare un'altra struttura dati =  Abstract Syntax Tree (AST) che rappresenta il programma in modo più manipolabile e strutturato*/
    @Override
    public Node visitSingleExp(SimpLanPlusParser.SingleExpContext ctx) {
        //restituisce il risultato della visita all'interno dell'espressione
        return new ProgNode(visit(ctx.exp()));
    }

    @Override
    public Node visitMultiDec(SimpLanPlusParser.MultiDecContext ctx) {
        ArrayList<Node> declarations = new ArrayList<>();
        ArrayList<Node> statements = new ArrayList<>();
        Node expression = null;

        // Visita e aggiungi le dichiarazioni
        for (SimpLanPlusParser.DecContext dc : ctx.dec()) {
            declarations.add(visit(dc));
        }

        // Visita l'espressione se presente e aggiunge le istruzioni
        if (ctx.stm() != null) {
            for (SimpLanPlusParser.StmContext st : ctx.stm()) {
                statements.add(visit(st));
            }
        }

        // Visita l'espressione se presente
        if (ctx.exp() != null) {
            expression = visit(ctx.exp());
        }

        return new MultiDecNode(declarations, statements, expression);
    }

    @Override
    public Node visitVarDec(SimpLanPlusParser.VarDecContext ctx) {
        return new DecNode(ctx.ID().getText(), (Type) visit(ctx.type()));
    }

    @Override
    public Node visitFunDec(SimpLanPlusParser.FunDecContext ctx) {
        ArrayList<ParamNode> _param = new ArrayList<ParamNode>();

        for (SimpLanPlusParser.ParamContext paramCtx : ctx.param()) {
            if (paramCtx instanceof SimpLanPlusParser.ParaContext) {
                SimpLanPlusParser.ParaContext paraCtx = (SimpLanPlusParser.ParaContext) paramCtx;
                _param.add(new ParamNode(paraCtx.ID().getText(), (Type) visit(paraCtx.type())));
            }
        }

        if (!ctx.body().getText().equals("")) {
            return new FunNode(ctx.ID().getText(), (Type) visit(ctx.type()), _param, visit(ctx.body()));
        }

        return new FunNode(ctx.ID().getText(), (Type) visit(ctx.type()), _param, null);
    }

    @Override
    public Node visitPara(SimpLanPlusParser.ParaContext ctx) {
        Type type = (Type) visit(ctx.type());
        String id = ctx.ID().getText();
        return new ParamNode(id, type);
    }

    @Override
    public Node visitBody1(SimpLanPlusParser.Body1Context ctx) {
        ArrayList<Node> declarations = new ArrayList<>();
        ArrayList<Node> statements = new ArrayList<>();

        // Visita e aggiungi le dichiarazioni
        for (SimpLanPlusParser.DecContext dc : ctx.dec()) {
            declarations.add(visit(dc));
        }

        // Visita e aggiungi le istruzioni
        for (SimpLanPlusParser.StmContext st : ctx.stm()) {
            statements.add(visit(st));
        }

        // Visita l'espressione se presente
        if (ctx.exp() != null) {
            Node exp = visit(ctx.exp());
            return new BodyNode(declarations, statements, exp);
        }
        return new BodyNode(declarations, statements, null);
    }

    @Override
    public Node visitType(SimpLanPlusParser.TypeContext ctx) {
        if (ctx.getText().equals("int")) {
            return new IntType();
        } else if (ctx.getText().equals("bool")) {
            return new BoolType();
        } else return new VoidType();
    }

    @Override
    public Node visitAssignExp(SimpLanPlusParser.AssignExpContext ctx) {
        return new AssNode(ctx.ID().getText(), visit(ctx.exp()));
    }

    @Override
    public Node visitFunExp(SimpLanPlusParser.FunExpContext ctx) {
        ArrayList<Node> _param = new ArrayList<Node>();

        for (SimpLanPlusParser.ExpContext vc : ctx.exp()) {
            _param.add(visit(vc));
        }

        return new CallFunNode(ctx.ID().getText(), _param);
    }

    @Override
    public Node visitIfStm(SimpLanPlusParser.IfStmContext ctx) {
        Node condExp = visit(ctx.cond);

        ArrayList<Node> thenStms = new ArrayList<Node>();
        ArrayList<Node> elseStms = new ArrayList<Node>();

        boolean elseBranch = false;
        for (org.antlr.v4.runtime.tree.ParseTree i : ctx.children) {
            if (i.getText().equals("else"))
                elseBranch = true;
            if (i.getClass().equals(parser.SimpLanPlusParser.AssignExpContext.class) ||
                    i.getClass().equals(SimpLanPlusParser.IfStmContext.class) ||
                    i.getClass().equals(SimpLanPlusParser.FunExpContext.class)) {
                if (!elseBranch)
                    thenStms.add(visit(i));
                else
                    elseStms.add(visit(i));
            }
        }
        return new IfStmNode(condExp, thenStms, elseStms);
    }

    @Override
    public Node visitIfExp(SimpLanPlusParser.IfExpContext ctx) {

        Node condExp = visit(ctx.cond);

        ArrayList<Node> thenStms = new ArrayList<Node>();
        ArrayList<Node> elseStms = new ArrayList<Node>();
        Node thenExp = null;
        Node elseExp = null;

        boolean elseBranch = false;
        for (org.antlr.v4.runtime.tree.ParseTree i : ctx.children) {
            if (i.getText().equals("else"))
                elseBranch = true;
            if (i.getClass().equals(parser.SimpLanPlusParser.AssignExpContext.class) ||
                    i.getClass().equals(SimpLanPlusParser.IfStmContext.class) ||
                    i.getClass().equals(SimpLanPlusParser.FunExpContext.class)) {
                if (!elseBranch)
                    thenStms.add(visit(i));
                else
                    elseStms.add(visit(i));
            } else if (i.getClass().equals(SimpLanPlusParser.ExpContext.class) ||
                    i.getClass().equals(SimpLanPlusParser.GleContext.class) ||
                    i.getClass().equals(SimpLanPlusParser.MuldivContext.class) ||
                    i.getClass().equals(SimpLanPlusParser.PlussubContext.class) ||
                    i.getClass().equals(SimpLanPlusParser.NotContext.class) ||
                    i.getClass().equals(SimpLanPlusParser.AndorContext.class) ||
                    i.getClass().equals(SimpLanPlusParser.IfExpContext.class) ||
                    i.getClass().equals(SimpLanPlusParser.BaseExpContext.class) ||
                    i.getClass().equals(SimpLanPlusParser.SingleExpContext.class) ||
                    i.getClass().equals(SimpLanPlusParser.VarExpContext.class) ||
                    i.getClass().equals(SimpLanPlusParser.IntValContext.class) ||
                    i.getClass().equals(SimpLanPlusParser.BoolValContext.class) ||
                    i.getClass().equals(SimpLanPlusParser.FunExp2Context.class)
            ) {
                if (!elseBranch)
                    thenExp = (visit(i));
                else
                    elseExp = (visit(i));
            }

        }

        return new IfExpNode(condExp, thenStms, thenExp, elseStms, elseExp);

    }

    @Override
    public Node visitBaseExp(SimpLanPlusParser.BaseExpContext ctx) {
        return super.visitBaseExp(ctx);
    }

    @Override
    public Node visitAndor(SimpLanPlusParser.AndorContext ctx) {
        if (ctx.and != null) { //AND
            return new AndNode(visit(ctx.left), visit(ctx.right));
        } else if (ctx.or != null) { //OR
            return new OrNode(visit(ctx.left), visit(ctx.right));
        } else {
            return null;
        }
    }

    @Override
    public Node visitVarExp(SimpLanPlusParser.VarExpContext ctx) {
        //this corresponds to a variable access
        return new IdNode(ctx.ID().getText());
    }

    @Override
    public Node visitGle(SimpLanPlusParser.GleContext ctx) {
        Node left = visit(ctx.left);
        Node right = visit(ctx.right);

        Token great = ctx.great;
        Token less = ctx.less;
        Token greateq = ctx.greateq;
        Token lesseq = ctx.lesseq;
        Token equal = ctx.equal;

        if (great != null) { //great='>'
            return new GreatNode(left, right);
        } else if (less != null) { //less='<'
            return new LessNode(left, right);
        } else if (greateq != null) { //greateq='>='
            return new GreatEqualNode(left, right);
        } else if (lesseq != null) { //lesseq='<='
            return new LessEqualNode(left, right);
        } else if (equal != null) { //equal='=='
            return new EqualNode(left, right);
        } else {
            System.out.println("Errore, operatore di confronto non valido");
            return new ErrorType();
        }
    }

    @Override
    public Node visitIntVal(SimpLanPlusParser.IntValContext ctx) {
        // notice that this method is not actually a rule but a named production #intVal
        //there is no need to perform a check here, the lexer ensures this text is an int
        return new IntNode(Integer.parseInt(ctx.INTEGER().getText()));
    }

    @Override
    public Node visitNot(SimpLanPlusParser.NotContext ctx) {
        // Crea un nodo NotNode per rappresentare l'operazione di negazione
        return new NotNode(visit(ctx.right));
    }

    @Override
    public Node visitFunExp2(SimpLanPlusParser.FunExp2Context ctx) {
        //this corresponds to a function invocation
        //declare the result
        Node res;

        //get the invocation arguments
        ArrayList<Node> args = new ArrayList<Node>();

        for (SimpLanPlusParser.ExpContext exp : ctx.exp())
            args.add(visit(exp));

        // this is ad-hoc for this project...
        if (ctx.ID().getText().equals("print"))
            res = new PrintNode(args.get(0));

        else
            //instantiate the invocation
            res = new CallFunNode(ctx.ID().getText(), args);

        return res;
    }

    //per questo metodo vale la stessa cosa di quello sotto della moltiplicazione e divisione
    @Override
    public Node visitPlussub(SimpLanPlusParser.PlussubContext ctx) {
        if (ctx.plus != null) { //addizione
            return new PlusNode(visit(ctx.left), visit(ctx.right));
        } else if (ctx.sub != null) { //sottrazione
            return new SubNode(visit(ctx.left), visit(ctx.right));
        } else {
            return null;
        }
    }

    @Override
    public Node visitBoolVal(SimpLanPlusParser.BoolValContext ctx) {
        //there is no need to perform a check here, the lexer ensures this text is a boolean
        return new BoolNode(Boolean.parseBoolean(ctx.getText()));
    }

    //invece che fare i due metodi separati (visitMul e visitDiv) abbiamo dovuto farne due unici perchè abbiamo definito la grammatica in un certo modo
    @Override
    public Node visitMuldiv(SimpLanPlusParser.MuldivContext ctx) {
        if (ctx.mul != null) { //moltiplicazione

            return new MulNode(visit(ctx.left), visit(ctx.right));

        } else if (ctx.div != null) { //divisione

            return new DivNode(visit(ctx.left), visit(ctx.right));

        } else {
            return null; //sono indecisa se toglierlo dall'else e lasciarlo sotto da solo o lasciarlo dentro all'else
        }
    }


}
