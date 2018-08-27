package homework.interfaces.operators;

import homework.interfaces.IToken;
/**
 * Clasa ce foloseste Factory Pattern pentru crearea de operatori.
 *
 * @author Brinzan Ionut 324CD
 *
 */
public final class OperatorsFactory implements IOperatorsFactory {

    // SINGLETON
    private static OperatorsFactory operatorsFactory = null;

    private OperatorsFactory() {
    }

    public static OperatorsFactory getInstance() {

        if (operatorsFactory == null) {
            operatorsFactory = new OperatorsFactory();
        }
        return operatorsFactory;
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.operators
     * .IOperatorsFactory#createOperator(java.lang.String)
     */
    @Override
    public IOperator createOperator(final String token) {

        if (token.equalsIgnoreCase("+")) {
            return new PlusOperator(token);
        }
        if (token.equalsIgnoreCase("-")) {
            return new MinusOperator(token);
        }
        if (token.equalsIgnoreCase("*")) {
            return new MultiplyOperator(token);
        }
        if (token.equalsIgnoreCase("/")) {
            return new DivideOperator(token);
        }
        if (token.equalsIgnoreCase("^")) {
            return new PowOperator(token);
        }
        if (token.equalsIgnoreCase("log")) {
            return new LogOperator(token);
        }
        if (token.equalsIgnoreCase("sqrt")) {
            return new SqrtOperator(token);
        }
        return null;
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.operators.
     * IOperatorsFactory#isOperator(homework.interfaces.IToken)
     */
    @Override
    public boolean isOperator(final IToken token) {

        if (token.getSymbol().equals("+") || token.getSymbol().equals("-")
                || token.getSymbol().equals("*")
                || token.getSymbol().equals("/")
                || token.getSymbol().equals("^")
                || token.getSymbol().equals("log")
                || token.getSymbol().equals("sqrt")) {
            return true;
        }
        return false;
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.operators.IOperatorsFactory
     * #isUnaryOperator(homework.interfaces.operators.IOperator)
     */
    @Override
    public boolean isUnaryOperator(final IOperator operator) {

        if (operator.getSymbol().equals("sqrt")
                || operator.getSymbol().equals("log")) {
            return true;
        }
        return false;
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.operators.IOperatorsFactory
     * #isBinaryOperator(homework.interfaces.operators.IOperator)
     */
    @Override
    public boolean isBinaryOperator(final IOperator operator) {

        if (operator.getSymbol().equals("sqrt")
                || operator.getSymbol().equals("log")) {
            return false;
        }
        return true;
    }

}
