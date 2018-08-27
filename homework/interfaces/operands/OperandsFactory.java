package homework.interfaces.operands;
/**
 * Clasa OperandsFactory reprezinta clasa care produce
 * operanzi.
 * @author Brinzan Ionut 324CD
 *
 */
public final class OperandsFactory implements IOperandsFactory<Number> {

    private static OperandsFactory operandsFactory = null;
    private static final long ONE = 1;
    private static final long FOUR = 4;
    private static final long FIVE = 5;
    private static final long NINE = 9;
    private static final long TEN = 10;
    private static final long FORTY = 40;
    private static final long FIFTY = 50;
    private static final long NINETY = 90;
    private static final long ONE_HUNDRED = 100;
    private static final long FOUR_HUNDRED = 400;
    private static final long FIVE_HUNDRED = 500;
    private static final long NINE_HUNDRED = 900;
    private static final long ONE_THOUSAND = 1000;

    private OperandsFactory() {
    }

    public static OperandsFactory getInstance() {
        if (operandsFactory == null) {
            operandsFactory = new OperandsFactory();
        }
        return operandsFactory;
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.operands.
     * IOperandsFactory#createOperand(java.lang.String)
     */
    @Override
    public IOperand<Number> createOperand(final String token) {

        return convertToArabNumber(token);
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.operands.
     * IOperandsFactory#convertToRomanNumber(java.lang.Number)
     */
    @Override
    public IOperand<Number> convertToRomanNumber(final Number arabNumber) {

        String number = "";
        IOperand<Number> result = new Operand();
        if (arabNumber.longValue() < 0) {
            number = number + "- ";
        }
        long myNumber = Math.abs(arabNumber.longValue());
        while (myNumber != 0) {
            while (myNumber >= ONE_THOUSAND) {
                number = number + "M";
                myNumber = myNumber - ONE_THOUSAND;
            }
            if (myNumber >= NINE_HUNDRED) {
                number = number + "CM";
                myNumber = myNumber - NINE_HUNDRED;
            }
            while (myNumber >= FIVE_HUNDRED) {
                number = number + "D";
                myNumber = myNumber - FIVE_HUNDRED;
            }
            if (myNumber >= FOUR_HUNDRED) {
                number = number + "CD";
                myNumber = myNumber - FOUR_HUNDRED;
            }
            while (myNumber >= ONE_HUNDRED) {
                number = number + "C";
                myNumber = myNumber - ONE_HUNDRED;
            }
            if (myNumber >= NINETY) {
                number = number + "XC";
                myNumber = myNumber - NINETY;
            }
            while (myNumber >= FIFTY) {
                number = number + "L";
                myNumber = myNumber - FIFTY;
            }
            if (myNumber >= FORTY) {
                number = number + "XL";
                myNumber = myNumber - FORTY;
            }
            while (myNumber >= TEN) {
                number = number + "X";
                myNumber = myNumber - TEN;
            }

            if (myNumber == NINE) {
                number = number + "IX";
                myNumber = 0;
            }
            if (myNumber >= FIVE) {
                number = number + "V";
                myNumber = myNumber - FIVE;
            }
            if (myNumber == FOUR) {
                number = number + "IV";
                myNumber = 0;
            }
            while (myNumber > 0) {
                number = number + "I";
                myNumber = myNumber - ONE;
            }

        }
        result.setSymbol(number);
        return result;
    }

    /**
     * (non-Javadoc).
     * @see homework.interfaces.operands.
     * IOperandsFactory#convertToArabNumber(java.lang.String)
     */
    @Override
    public IOperand<Number> convertToArabNumber(final String romanNumber) {

        IOperand<Number> operand = new Operand();

        char[] romanLetters = romanNumber.toCharArray();
        int i = 0;
        long nr = 0;
        while (i < romanLetters.length - 1) {
            if (romanLetters[i] == 'I') {
                if (romanLetters[i + 1] == 'I') {
                    nr = nr + ONE;
                } else {
                    nr = nr - ONE;
                }
            }
            if (romanLetters[i] == 'V') {
                if (romanLetters[i + 1] == 'I'
                        || romanLetters[i + 1] == 'V') {
                    nr = nr + FIVE;
                } else {
                    nr = nr - FIVE;
                }
            }
            if (romanLetters[i] == 'X') {
                if (romanLetters[i + 1] == 'I'
                        || romanLetters[i + 1] == 'V'
                        || romanLetters[i + 1] == 'X') {
                    nr = nr + TEN;
                } else {
                    nr = nr - TEN;
                }
            }
            if (romanLetters[i] == 'L') {
                if (romanLetters[i + 1] == 'I'
                        || romanLetters[i + 1] == 'V'
                        || romanLetters[i + 1] == 'X'
                        || romanLetters[i + 1] == 'L') {
                    nr = nr + FIFTY;
                } else {
                    nr = nr - FIFTY;
                }
            }
            if (romanLetters[i] == 'C') {
                if (romanLetters[i + 1] == 'I'
                        || romanLetters[i + 1] == 'V'
                        || romanLetters[i + 1] == 'X'
                        || romanLetters[i + 1] == 'L'
                        || romanLetters[i + 1] == 'C') {
                    nr = nr + ONE_HUNDRED;
                } else {
                    nr = nr - ONE_HUNDRED;
                }
            }
            if (romanLetters[i] == 'D') {
                if (romanLetters[i + 1] == 'I'
                        || romanLetters[i + 1] == 'V'
                        || romanLetters[i + 1] == 'X'
                        || romanLetters[i + 1] == 'L'
                        || romanLetters[i + 1] == 'C'
                        || romanLetters[i + 1] == 'D') {
                    nr = nr + FIVE_HUNDRED;
                } else {
                    nr = nr - FIVE_HUNDRED;
                }
            }
            if (romanLetters[i] == 'M') {
                if (romanLetters[i + 1] == 'I'
                        || romanLetters[i + 1] == 'V'
                        || romanLetters[i + 1] == 'X'
                        || romanLetters[i + 1] == 'L'
                        || romanLetters[i + 1] == 'C'
                        || romanLetters[i + 1] == 'D'
                        || romanLetters[i + 1] == 'M') {
                    nr = nr + ONE_THOUSAND;
                } else {
                    nr = nr - ONE_THOUSAND;
                }
            }
            i++;
        }
        if (romanLetters[i] == 'I') {
            nr = nr + ONE;
        }
        if (romanLetters[i] == 'V') {
            nr = nr + FIVE;
        }
        if (romanLetters[i] == 'X') {
            nr = nr + TEN;
        }
        if (romanLetters[i] == 'L') {
            nr = nr + FIFTY;
        }
        if (romanLetters[i] == 'C') {
            nr = nr + ONE_HUNDRED;
        }
        if (romanLetters[i] == 'D') {
            nr = nr + FIVE_HUNDRED;
        }
        if (romanLetters[i] == 'M') {
            nr = nr + ONE_THOUSAND;
        }
        operand.setSymbolValue(nr);
        return operand;
    }

}
