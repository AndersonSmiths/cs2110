package cs2110;

import java.util.HashSet;
import java.util.Set;

public class Application implements Expression {

    /**
     * An argument stored as a single child expression node.
     * Can be any non-empty subexpression.
     */
    private final Expression argument;

    /**
     * An instance of the UnaryFunction class which can be
     * evaluated on  a numeric argument by calling the apply() method.
     * Can report its name for identification purposes.
     */
    private final UnaryFunction func;


    /**
     * Creating a node application with an argument and function.
     */
    public Application(UnaryFunction func, Expression argument) {
        this.argument = argument;
        this.func = func;
    }

    /**
     * Returns the value resulting from calling the input function
     * on the input argument.
     */
    @Override
    public double eval(VarTable vars) throws UnboundVariableException {
        assert vars!= null;
        double argValue = this.argument.eval(vars);
        return func.apply(argValue);
    }

    /**
     * Return whether `other` is an Application of the same class with the
     * same function and argument
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        Application a = (Application) other;
        return (a.argument.equals(this.argument) && a.func.equals(this.func));
    }

    /**
     * Calling the input function on the input argument
     * counts as one operation.
     */
    @Override
    public int opCount() {
        return 1 + argument.opCount();
    }

    /**
     * The infix representation of an application node should contain
     * the expected argument infix representation with redundant
     * parentheses around the argument.
     */
    @Override
    public String infixString() {
        return this.func.name() + "(" + this.argument.infixString() + ")";
    }

    /**
     * The postfix representation of an application node should consist
     * of the post fix of its argument followed by a space, followed by its
     * function's name appended with parentheses.
     */
    @Override
    public String postfixString() {
        return this.argument.postfixString() + " " + this.func.name() + "()";
    }

    /**
     * An Application node can be fully optimized to a constant
     * if its children can be all evaluated to yield a number. If they don't
     * yield a number, then a new node is created with its children
     * in their optimized forms.
     */
    @Override
    public Expression optimize(VarTable vars) {
        assert vars != null;
        Expression optimized = argument.optimize(vars);
        if (optimized instanceof Constant) {
            try {
                double value = func.apply(optimized.eval(vars));
                return new Constant(value);
            } catch (UnboundVariableException e) {
                // empty
            }
        }
        return new Application(func, optimized);
    }

    /**
     * An application node is dependent on the dependencies of its argument.
     */
    @Override
    public Set<String> dependencies() {
        Set<String> dep = new HashSet<>(argument.dependencies());
        return dep;
    }
}
