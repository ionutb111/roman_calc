package homework.interfaces.brackets;

import homework.interfaces.Token;

/**
 * Clasa pentru paranteze.
 *
 * @author Brinzan Ionut 324 CD
 *
 */
public class Bracket extends Token implements IBracket {

    /**
     * Constructor ce creaza un Bracket.
     * @param token reprezinta simbolul
     */
    public Bracket(final String token) {

        super.setSymbol(token);
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

}
