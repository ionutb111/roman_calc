package homework.interfaces.operands;

import homework.interfaces.Token;

/**
 * Clasa pentru operand.
 *
 * @author Brinzan Ionut 324CD
 *
 */
public class Operand extends Token implements IOperand<Number> {

    /**
     * Constructor cu parametru String.
     * @param dummySymbol reprezinta symbolul ce va fi setat.
     */
    public Operand(final String dummySymbol) {

        super.setSymbol(dummySymbol);
    }

    /**
     * Constructor fara parametru.
     */
    public Operand() {

        super.setSymbol(null);
    }

    /**
     * (non-Javadoc).
     *
     * @see homework.interfaces.IToken#getSymbol()
     */
    @Override
    public String getSymbol() {

        return super.getSymbol();
    }

    /**
     * (non-Javadoc).
     *
     * @see homework.interfaces.IToken#setSymbol(java.lang.String)
     */
    @Override
    public void setSymbol(final String dummySymbol) {

        super.setSymbol(dummySymbol);
    }

    /**
     * (non-Javadoc).
     *
     * @see homework.interfaces.operands.IOperand#getSymbolValue()
     */
    @Override
    public Number getSymbolValue() {

        return Double.valueOf(super.getSymbol());
    }

    /**
     * (non-Javadoc).
     *
     * @see
     * homework.interfaces.operands.IOperand#setSymbolValue(java.lang.Number)
     */
    @Override
    public void setSymbolValue(final Number value) {

        super.setSymbol(String.valueOf(value));
    }

}
