package homework.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import homework.interfaces.brackets.Bracket;
import homework.interfaces.brackets.BracketsFactory;
import homework.interfaces.operands.IOperand;
import homework.interfaces.operands.Operand;
import homework.interfaces.operands.OperandsFactory;
import homework.interfaces.operators.DivideOperator;
import homework.interfaces.operators.IOperator;
import homework.interfaces.operators.LogOperator;
import homework.interfaces.operators.MinusOperator;
import homework.interfaces.operators.MultiplyOperator;
import homework.interfaces.operators.OperatorsFactory;
import homework.interfaces.operators.PlusOperator;
import homework.interfaces.operators.PowOperator;
import homework.interfaces.operators.SqrtOperator;
/**
 * Clasa Server reprezinta clasa clasa de legatura si creierul
 * programului.
 * @author Brinzan Ionut 324CD
 *
 */
public final class Server implements IServer {

    private Stack<IToken> myStack = new Stack<IToken>();
    private List<String> results = new ArrayList<String>();
    private List<String> validOperators = new ArrayList<String>();

    // SINGLETON
    private static Server server = null;

    private Server() {
    }

    public static Server getInstance() {
        if (server == null) {
            server = new Server();
        }
        return server;
    }

    /**
     * (non-Javadoc).
     *
     * @see homework.interfaces.IServer#canPublish(java.lang.String[])
     */
    @Override
    public boolean canPublish(final String[] tokens) {

        int i = 0;
        while (i < tokens.length) {
            if (tokens[i].equals("+") || tokens[i].equals("-")
                    || tokens[i].equals("*") || tokens[i].equals("/")
                    || tokens[i].equals("^") || tokens[i].equals("sqrt")
                    || tokens[i].equals("log")) {

                int j = 0;
                int canPublish = 0;
                while (j < validOperators.size()) {
                    if (validOperators.get(j).equals(tokens[i])) {
                        canPublish = 1;
                    }
                    j++;

                }
                if (canPublish == 0) {
                    return false;
                }

            }
            i++;
        }

        return true;
    }

    /**
     * (non-Javadoc).
     *
     * @see homework.interfaces.IServer#publish(java.lang.String)
     */
    @Override
    public void publish(final String command) {

        String[] tokens = command.split(" ");
        if (!canPublish(tokens)) {
            results.add("IMPOSSIBRU");
            return;
        }

        IToken[] tokensBuffer = new IToken[tokens.length];
        IToken[] infixForm = new IToken[tokens.length];

        int i = 0;
        while (i < tokens.length) {
            tokensBuffer[i] = new Token();
            tokensBuffer[i].setSymbol(tokens[i]);

            i++;
        }

        BracketsFactory bracketsFactory = BracketsFactory.getInstance();
        OperatorsFactory operatorsFactory = OperatorsFactory.getInstance();
        OperandsFactory operandsFactory = OperandsFactory.getInstance();

        i = 0;
        while (i < tokens.length) {
            if (bracketsFactory.isBracket(tokensBuffer[i])) {
                infixForm[i] = bracketsFactory
                        .createBracket(tokensBuffer[i].getSymbol());
            } else if (operatorsFactory.isOperator(tokensBuffer[i])) {
                infixForm[i] = operatorsFactory
                        .createOperator(tokensBuffer[i].getSymbol());
            } else {
                infixForm[i] = operandsFactory
                        .createOperand(tokensBuffer[i].getSymbol());
            }

            i++;
        }

        ArrayList<IToken> postfixed = postfixForm(infixForm);
        IOperand<Number> verify = calculateResult(postfixed);
        if (verify.getSymbol().equals("IMPOSSIBRU")) {
            results.add("IMPOSSIBRU");
        } else {
            double doubleResult = Math.floor((double) verify.getSymbolValue());
            long longResult = (long) doubleResult;
            IOperand<Number> numberResult = OperandsFactory.getInstance()
                    .convertToRomanNumber(longResult);
            results.add(numberResult.getSymbol());
        }
    }

    /**
     * Metoda transforma forma infixata a unui calcul matematic
     * intr-o forma prefixata.
     * @param infixForm reprezinta un vector de Tokeni ce retine
     * forma infixata a calcului.
     * @return o lista de Tokeni ce retine forma postfixata
     * a calcului.
     */
    public ArrayList<IToken> postfixForm(final IToken[] infixForm) {

        ArrayList<IToken> postfix = new ArrayList<IToken>();

        int i = 0;
        while (i < infixForm.length) {
            if (OperatorsFactory.getInstance().isOperator(infixForm[i])) {

                while (!(myStack.isEmpty())
                        && (OperatorsFactory.getInstance()
                                .isOperator(myStack.peek()))
                        && (((IOperator) infixForm[i])
                                .getPriority() <= ((IOperator) myStack
                                        .peek()).getPriority())) {

                    postfix.add(myStack.pop());

                }

                myStack.push(infixForm[i]);
            } else if (BracketsFactory.getInstance()
                    .isBracket(infixForm[i])) {

                if (BracketsFactory.getInstance()
                        .isOpenedBracket((Bracket) infixForm[i])) {

                    myStack.push(infixForm[i]);
                } else if (BracketsFactory.getInstance()
                        .isClosedBracket((Bracket) infixForm[i])) {

                    while (!(BracketsFactory.getInstance()
                            .isBracket(myStack.peek()))
                            || !(BracketsFactory.getInstance()
                                    .isBracketPair(
                                            (Bracket) myStack.peek(),
                                            (Bracket) infixForm[i]))) {

                        postfix.add(myStack.pop());
                    }
                    myStack.pop();
                }
            } else {
                postfix.add(infixForm[i]);
            }
            i++;
        }

        // Golire stiva pentru refolosire la calculateResult
        while (!myStack.isEmpty()) {
            postfix.add(myStack.pop());
        }
        return postfix;
    }

    /**
     * Metoda calculeaza rezultatul unui calcul matematic
     * folosind forma postfixata.
     * @param postfix reprezinta o lista de Tokeni ce retine
     * forma postfixata.
     * @return un Operand ce are ca symbol un String ce reprezinta
     * rezultatul calcului matematic.
     */
    public IOperand<Number> calculateResult(final ArrayList<IToken> postfix) {
        IOperand<Number> result = null;
        int i = 0;

        while (i < postfix.size()) {

            if (OperatorsFactory.getInstance()
                    .isOperator(postfix.get(i))) {

                if (OperatorsFactory.getInstance()
                        .isUnaryOperator((IOperator) postfix.get(i))) {

                    IToken op = myStack.pop();
                    double opValue = Double.valueOf(op.getSymbol());
                    IOperator operator = OperatorsFactory.getInstance()
                            .createOperator(postfix.get(i).getSymbol());

                    IOperand<Number> opResult = null;
                    //Acest switch este facut pentru a evita un warning
                    //de cast.Mai multe detalii in readme.
                    switch (operator.getSymbol()) {
                    case "log":
                        opResult = ((LogOperator) operator)
                        .calculate(opValue);
                         break;
                    case "sqrt":
                        opResult = ((SqrtOperator) operator)
                        .calculate(opValue);
                        break;
                    default:
                        break;
                }
                    myStack.push(opResult);

                } else {

                    IToken op2 = myStack.pop();
                    IToken op1 = myStack.pop();
                    double op1Value = Double.valueOf(op1.getSymbol());
                    double op2Value = Double.valueOf(op2.getSymbol());
                    IOperator operator = OperatorsFactory.getInstance()
                            .createOperator(postfix.get(i).getSymbol());

                    IOperand<Number> opResult = null;
                    //Acest switch este facut pentru a evita un warning
                    //de cast.Mai multe detalii in readme.
                    switch (operator.getSymbol()) {
                    case "+":
                        opResult = ((PlusOperator) operator)
                             .calculate(op1Value, op2Value);
                        break;
                    case "-":
                        opResult = ((MinusOperator) operator)
                        .calculate(op1Value, op2Value);
                        break;
                    case "*":
                        opResult = ((MultiplyOperator) operator)
                        .calculate(op1Value, op2Value);
                        break;
                    case "/":
                        opResult = ((DivideOperator) operator)
                        .calculate(op1Value, op2Value);
                        break;
                    case "^":
                        opResult = ((PowOperator) operator)
                        .calculate(op1Value, op2Value);
                        break;
                    default:
                        break;
                    }
                    if (opResult.getSymbol().equals("IMPOSSIBRU")) {

                        while (!myStack.isEmpty()) {
                            myStack.pop();
                        }
                        result = new Operand();
                        result.setSymbol("IMPOSSIBRU");
                        return result;
                    } else {
                        myStack.push(opResult);
                    }
                }

            } else {
                myStack.push(postfix.get(i));
            }
            i++;
        }
        result = new Operand(myStack.pop().getSymbol());
        return result;
    }

    /**
     * (non-Javadoc).
     *
     * @see homework.interfaces.IServer#subscribe(java.lang.String)
     */
    @Override
    public void subscribe(final String operator) {

        validOperators.add(operator);
    }

    /**
     * (non-Javadoc).
     *
     * @see homework.interfaces.IServer#getResults()
     */
    @Override
    public List<String> getResults() {

        return results;
    }

}
