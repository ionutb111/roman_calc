package homework.interfaces.operators;

import homework.interfaces.Token;
import homework.interfaces.operands.IOperand;
import homework.interfaces.operands.Operand;
import homework.interfaces.operators.binaryOperators.IBinaryOperator;
/**
 * Clasa ce defineste operatorul pentru adunare.
 *
 * @author Brinzan Ionut 324CD
 *
 */
public class PlusOperator extends Token implements IBinaryOperator<Number> {

    private static final int PRIOR_ZERO = 0;

    /**
     * Constructor ce creaza un PlusOperator.
     * @param token reprezinta simbolul
     */
    public PlusOperator(final String token) {

        setSymbol(token);
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.operators.IOperator#getPriority()
     */
    @Override
    public int getPriority() {

        return PRIOR_ZERO;
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
     * @see homework.interfaces.operators.binaryOperators
     * .IBinaryOperator#calculate(java.lang.Number, java.lang.Number)
     */
    @Override
    public IOperand<Number> calculate(final Number leftOperand,
            final Number rightOperand) {

        double resultValue = 0;
        IOperand<Number> resultToken = new Operand();
        resultValue = leftOperand.doubleValue()
                + rightOperand.doubleValue();
        resultToken.setSymbolValue(resultValue);
        return resultToken;
    }
}
