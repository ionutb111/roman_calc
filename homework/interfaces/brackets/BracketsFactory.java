package homework.interfaces.brackets;

import homework.interfaces.IToken;
/**
 * Clasa ce foloseste Factory Pattern pentru a crea Bracket.
 *
 * @author Brinzan Ionut 324CD
 *
 */
public final class BracketsFactory implements IBracketsFactory {

    // SINGLETON
    private static BracketsFactory bracketsFactory = null;

    private BracketsFactory() {
    }

    public static BracketsFactory getInstance() {
        if (bracketsFactory == null) {
            bracketsFactory = new BracketsFactory();
        }
        return bracketsFactory;
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.brackets.IBracketsFactory
     * #createBracket(java.lang.String)
     */
    @Override
    public IBracket createBracket(final String token) {

        if (token.equals("(")) {
            return new Bracket(token);
        }
        if (token.equals("[")) {
            return new Bracket(token);
        }
        if (token.equals("{")) {
            return new Bracket(token);
        }
        if (token.equals("}")) {
            return new Bracket(token);
        }
        if (token.equals("]")) {
            return new Bracket(token);
        }
        if (token.equals(")")) {
            return new Bracket(token);
        }
        return null;
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.brackets.IBracketsFactory
     * #isBracket(homework.interfaces.IToken)
     */
    @Override
    public boolean isBracket(final IToken token) {

        if (token.getSymbol().equals("(") || token.getSymbol().equals(")")
                || token.getSymbol().equals("[")
                || token.getSymbol().equals("]")
                || token.getSymbol().equals("{")
                || token.getSymbol().equals("}")) {
            return true;
        }
        return false;
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.brackets.IBracketsFactory
     * #isOpenedBracket(homework.interfaces.brackets.IBracket)
     */
    @Override
    public boolean isOpenedBracket(final IBracket bracket) {

        if (bracket.getSymbol().equals("(")
                || bracket.getSymbol().equals("[")
                || bracket.getSymbol().equals("{")) {
            return true;
        }
        return false;
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.brackets.IBracketsFactory
     * #isClosedBracket(homework.interfaces.brackets.IBracket)
     */
    @Override
    public boolean isClosedBracket(final IBracket bracket) {

        if (bracket.getSymbol().equals(")")
                || bracket.getSymbol().equals("]")
                || bracket.getSymbol().equals("}")) {
            return true;
        }
        return false;
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.brackets.IBracketsFactory
     * #isBracketPair(homework.interfaces.brackets.IBracket,
     *  homework.interfaces.brackets.IBracket)
     */
    @Override
    public boolean isBracketPair(final IBracket openBracket,
            final IBracket closeBracket) {

        if (openBracket.getSymbol().equals("(")
                && closeBracket.getSymbol().equals(")")
                || openBracket.getSymbol().equals("[")
                        && closeBracket.getSymbol().equals("]")
                || openBracket.getSymbol().equals("{")
                        && closeBracket.getSymbol().equals("}")) {
            return true;
        }
        return false;
    }

}
