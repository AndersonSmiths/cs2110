package cs2110;

import java.util.HashSet;
import java.util.Set;

public class Variable implements Expression {


    /**
     * The name of this variable.
     */
    private final String name;

    /**
     * Create a node representing the variable 'name.'
     */
    public Variable(String name) {
        assert (name != null);
        this.name = name;
    }

    /**
     * Return whether `other` is a Variable of the same class with the same name.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        Variable v = (Variable) other;
        return v.name.equals(this.name);
    }

    /**
     * A Variable node evaluates to its value if it is in the var map.\
     * Throws UnboundVariableException if Variable is not in vars.
     */
    @Override
    public double eval(VarTable vars) throws UnboundVariableException {
        assert vars!= null;
        return vars.get(this.name);
    }

    /**
     * No operations are required to evaluate the value of a Variable.
     */
    @Override
    public int opCount() { return 0;}

    /**
     * Returns the name of this Variable node.
     */
    @Override
    public String infixString() {
        return this.name;
    }

    /**
     * Returns the name of this Variable node.
     */
    @Override
    public String postfixString() {
        return this.name;
    }

    /**
     * A variable can only be optimized if it has an assigned value in
     * the provided variable table.
     */
    @Override
    public Expression optimize(VarTable vars) {
        assert vars!= null;
        if (vars.contains(this.name)) {
            try {
                return new Constant(vars.get(this.name));
            } catch (UnboundVariableException e) {
                //empty
            }

        }
        return this;
    }

    /**
     * Returns a set containing the name of this variable; an
     * expression is dependent on its variables.
     */
    @Override
    public Set<String> dependencies() {
        return Set.of(this.name);
    }
}
