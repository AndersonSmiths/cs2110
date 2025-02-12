package cs2110;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.NoSuchElementException;

public class RpnParser {

    /**
     * Parse the RPN expression in `exprString` and return the corresponding expression tree. Tokens
     * must be separated by whitespace.  Valid tokens include decimal numbers (scientific notation
     * allowed), arithmetic operators (+, -, *, /, ^), function names (with the suffix "()"), and
     * variable names (anything else).  When a function name is encountered, the corresponding
     * function will be retrieved from `funcDefs` using the name (without "()" suffix) as the key.
     *
     * @throws IncompleteRpnException     if the expression has too few or too many operands
     *                                    relative to operators and functions.
     * @throws UndefinedFunctionException if a function name applied in `exprString` is not present
     *                                    in `funcDefs`.
     */
    public static Expression parse(String exprString, Map<String, UnaryFunction> funcDefs)
            throws IncompleteRpnException, UndefinedFunctionException {
        assert exprString != null;
        assert funcDefs != null;

        // Each token will result in a subexpression being pushed onto this stack.  If the
        // subexpression requires arguments, they are first popped off of this stack.
        Deque<Expression> stack = new ArrayDeque<>();

        // Loop over each token in the expression string from left to right
        for (Token token : Token.tokenizer(exprString)) {
            if (token instanceof Token.Number) {
                Token.Number numToken = (Token.Number) token;
                stack.push(new Constant(numToken.doubleValue()));
            } else if (token instanceof Token.Variable) {
                Token.Variable varToken = (Token.Variable) token;
                stack.push(new Variable(varToken.value()));
            } else if (token instanceof Token.Operator) {
                Token.Operator opToken = (Token.Operator) token;
                if (stack.size() < 2) {
                    throw new IncompleteRpnException("Not enough operands", stack.size());
                }
                Expression rightOperand = stack.pop();
                Expression leftOperand = stack.pop();
                stack.push(new Operation(opToken.opValue(), leftOperand, rightOperand));
            } else if (token instanceof Token.Function) {
                Token.Function funcToken = (Token.Function) token;
                UnaryFunction func = funcDefs.get(funcToken.name());
                if (func == null) {
                    throw new UndefinedFunctionException("The function is undefined");
                }
                else if (stack.isEmpty()) {
                    throw new IncompleteRpnException("Not enough operands", 0);
                }
                Expression argument = stack.pop();
                stack.push(new Application(func, argument));
            }
        }

        if (stack.size() != 1) {
            throw new IncompleteRpnException("Too many/Not enough operands", stack.size());
        }

        return stack.pop();

    }
}
