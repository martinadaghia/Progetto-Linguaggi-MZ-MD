package ast;

public class VoidType extends Type{
    public String toPrint(String s) {
        return "//start VoidType\n" + s + "Void " + "//end VoidType\n";
    }

}
