package homework.interfaces;
/**
 * Clasa Token reprezinta un obiect cu symbol.
 * @author Brinzan Ionut 324 CD
 *
 */
public class Token implements IToken {

    private String symbol;

    /**
     * (non-Javadoc).
     * @see homework.interfaces.IToken#getSymbol()
     */
    @Override
    public String getSymbol() {

        return this.symbol;
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.IToken#setSymbol(java.lang.String)
     */
    @Override
    public void setSymbol(final String dummySymbol) {

        this.symbol = dummySymbol;
    }

}
