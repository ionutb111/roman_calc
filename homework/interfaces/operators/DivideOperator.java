package homework.interfaces.operators;

import homework.interfaces.Token;
import homework.interfaces.operands.IOperand;
import homework.interfaces.operands.Operand;
import homework.interfaces.operators.binaryOperators.IBinaryOperator;
/**
 * Clasa ce defineste operatorul pentru impartire.
 *
 * @author Brinzan Ionut 324CD
 *
 */
public class DivideOperator extends Token implements
        IBinaryOperator<Number> {

    private static final int PRIOR_ONE = 1;

    /**
     * Constructor ce creaza un DivideOperator.
     * @param token reprezinta simbolul
     */
    public DivideOperator(final String token) {
        setSymbol(token);
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.operators.IOperator#getPriority()
     */

    @Override
    public int getPriority() {

        return PRIOR_ONE;
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
        int ok = 1;
        if (rightOperand.doubleValue() == 0) {
            ok = 0;
        } else {
            resultValue = leftOperand.doubleValue()
                    / rightOperand.doubleValue();
        }
        if (ok == 1) {
            resultToken.setSymbolValue(resultValue);
        } else {
            resultToken.setSymbol("IMPOSSIBRU");
        }
        return resultToken;
    }

}
