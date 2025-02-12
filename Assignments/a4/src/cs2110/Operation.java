package cs2110;

import java.util.HashSet;
import java.util.Set;

public class Operation implements Expression {

    /**
     * An instance of the operator class, the operator of this node. (-, +, *, /, ^)
     * Can be evaluated on numeric operands by calling operate()
     */
    private final Operator op;

    /**
     * The left operand child of this node.
     */
    private final Expression leftOperand;

    /**
     * The right operand child of this node.
     */
    private final Expression rightOperand;

    /**
     * Create a node with Operator op, and Expressions leftOperand
     * and rightOperand.
     */
    public Operation(Operator op, Expression leftOperand, Expression rightOperand) {
        this.op = op;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * Evaluates both of the operand children and then combines
     * those values with the operator and returns.
     */
    @Override
    public double eval(VarTable vars) throws UnboundVariableException {
        return op.operate(leftOperand.eval(vars), rightOperand.eval(vars));
    }


    /**
     * Return whether `other` is a Operation  of the same class with the same operator
     * and Operands.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        Operation o = (Operation) other;
        return (o.op.equals(this.op) && o.rightOperand.equals(this.rightOperand) && o.leftOperand.equals(this.leftOperand));
    }

    /**
     * 1 Operation is required to evaluate the value of a
     * Operation node.
     */
    @Override
    public int opCount() {
        return this.leftOperand.opCount() + this.rightOperand.opCount() + 1;
    }

    /**
     * The inFixString of an operation node consists of its first
     * operand, its operator symbol surrounded by spaces, and its
     * second operand, all enclosed in parentheses.
     */
    @Override
    public String infixString() {
        return "(" + leftOperand.infixString() + " " + op.symbol() + " " + rightOperand.infixString() + ")";
    }

    /**
     * The postfix representation of an operation node consists of
     * its first operand, its second operand, and its operator symbol
     * separated by spaces
     */
    @Override
    public String postfixString() {
        return leftOperand.postfixString() + " " + rightOperand.postfixString() + " " + op.symbol();
    }

    /**
     * An operation node can be fully optimized to a constant
     * if its children can be all evaluated to yield a number. If they don't
     * yield a number, then a new node is created with its children
     * in their optimized forms.
     */
    @Override
    public Expression optimize(VarTable vars) {
        assert vars!= null;
        if (leftOperand.optimize(vars) instanceof Constant && rightOperand.optimize(vars) instanceof Constant){
            // try catch included to hand Unbound Variable exception
            try {
                double value = op.operate(leftOperand.optimize(vars).eval(vars), rightOperand.optimize(vars).eval(vars));
                return new Constant(value);
            } catch (UnboundVariableException e) {
                // empty
            }

        }
        return new Operation(op, leftOperand.optimize(vars), rightOperand.optimize(vars));
    }

    /**
     * An Operation node depends on the dependencies of both of its operands.
     */
    @Override
    public Set<String> dependencies() {
        Set<String> dep = new HashSet<>(leftOperand.dependencies());
        dep.addAll(rightOperand.dependencies());
        return dep;
    }
}
