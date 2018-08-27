package homework.interfaces.operators;

import homework.interfaces.Token;
import homework.interfaces.operands.IOperand;
import homework.interfaces.operands.Operand;
import homework.interfaces.operators.unaryOperators.IUnaryOperator;
/**
 * Clasa ce defineste operatorul pentru logaritmare.
 *
 * @author Brinzan Ionut 324CD
 *
 */
public class LogOperator extends Token implements
        IUnaryOperator<Number> {

    private static final int PRIOR_THREE = 3;

    /**
     * Constructor ce creaza un LogOperator.
     * @param token reprezinta simbolul
     */
    public LogOperator(final String token) {

        setSymbol(token);
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.operators.IOperator#getPriority()
     */

    @Override
    public int getPriority() {

        return PRIOR_THREE;
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.IToken#getSymbol()
     */
    @Override
    public String getSymbol() {

        return super.getSymbol();
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.IToken#setSymbol(java.lang.String)
     */
    @Override
    public void setSymbol(final String dummySymbol) {

        super.setSymbol(dummySymbol);
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.operators.unaryOperators
     * .IUnaryOperator#calculate(java.lang.Number)
     */
    @Override
    public IOperand<Number> calculate(final Number operand) {

        double resultValue = 0;
        IOperand<Number> resultToken = new Operand();
        resultValue = Math.log(operand.doubleValue());
        resultToken.setSymbolValue(resultValue);
        return resultToken;
    }
}
