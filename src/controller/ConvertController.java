package controller;

public class ConvertController {

    private static final Pilha<Character> pilhaConstante = new Pilha();
    private static final Pilha<Character> pilhaOperadores = new Pilha();
    private static final String operators = "()+-*/^";
    private static final String constant = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String getPilhaConstante() throws Exception {
        StringBuilder newExpression = new StringBuilder();

        do {

            newExpression.append(pilhaConstante.pop());

        } while (!pilhaConstante.isEmpty());

        return newExpression.toString();
    }

    public void convert(String expression) throws Exception {
        
        int cont = -1;
        int next;
        int[] parent = new int[10];
        int aParent = -1;
        int aAnterior = -1;
        int anterior = -1;
        int[] operatorA = new int[10];
        boolean quit = false;

        for (int i = 0; i < expression.length(); i++) {
            for (int j = 0; j < operators.length(); j++) {
                if (expression.charAt(i) == operators.charAt(j)) {
                    quit = false;
                    switch (expression.charAt(i)) {
                        case '(':
                            next = 0;
                            cont++;
                            break;
                        case '+':
                        case '-':
                            next = 1;
                            cont++;
                            break;
                        case '*':
                        case '/':
                            next = 2;
                            cont++;
                            break;
                        case '^':
                            next = 3;
                            cont++;
                            break;
                        default:
                            next = 4;
                            break;
                    }

                    do {
                        if (next == 0) {
                            anterior = next;
                            pilhaOperadores.push(expression.charAt(i));
                            aAnterior++;
                            aParent++;
                            operatorA[aAnterior] = next;
                            parent[aParent] = cont;
                            quit = true;
                        } else {
                            if (next == 4) {
                                while (parent[aParent] < cont) {
                                    pilhaConstante.push(pilhaOperadores.pop());
                                    cont--;
                                    aAnterior--;
                                }

                                pilhaOperadores.pop();
                                aParent--;
                                aAnterior--;
                                quit = true;

                                if (aAnterior == -1) {
                                    anterior = -1;
                                } else {
                                    anterior = operatorA[aAnterior];
                                }
                            } else {
                                if (anterior >= next) {
                                    pilhaConstante.push(pilhaOperadores.pop());
                                    cont--;
                                    aAnterior--;
                                    if (aAnterior == -1) {
                                        anterior = -1;
                                    } else {
                                        anterior = operatorA[aAnterior];
                                    }
                                } else {
                                    aAnterior++;
                                    pilhaOperadores.push(expression.charAt(i));
                                    operatorA[aAnterior] = next;
                                    quit = true;
                                    anterior = next;
                                }
                            }
                        }
                    } while (!quit);
                }
            }

            for (int k = 0; k < constant.length(); k++) {
                if (expression.charAt(i) == constant.charAt(k)) {
                    pilhaConstante.push(expression.charAt(i));
                    break;
                }
            }
        }
        while (!pilhaOperadores.isEmpty()) {
            pilhaConstante.push(pilhaOperadores.pop());
        }
    }

    public Boolean validateExpression(String expression) {
        boolean quit = false;

        for (int i = 0; i < expression.length(); i++) {
            quit = false;

            for (int j = 0; j < operators.length(); j++) {
                if (expression.charAt(i) == operators.charAt(j)) {
                    quit = true;
                    break;
                }
            }

            for (int k = 0; k < constant.length(); k++) {
                if (expression.charAt(i) == constant.charAt(k)) {
                    quit = true;
                    break;
                }
            }

            if (!quit) {
                break;
            }
        }

        return quit;
    }

    public Boolean verified(String chain) throws Exception {
        Pilha<String> p = new Pilha();
        int i = 0;
        while (i < chain.length()) {
            if (chain.charAt(i) == '(') {
                p.push("(");
            } else if (chain.charAt(i) == ')') {
                if (!p.isEmpty()) {
                    p.pop();
                } else {
                    p.push(")");
                    break;
                }
            }

            i++;
        }
        return p.isEmpty();
    }

    public double calculator(String expression) throws Exception {
        Pilha<Double> operands = new Pilha();

        String[] stringVec = expression.split("");

        for (String str : stringVec) {
            if (str.trim().equals("")) {
                continue;
            }

            switch (str) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                    double right = operands.pop();
                    double left = operands.pop();
                    double value = 0;
                    switch (str) {
                        case "+":
                            value = left + right;
                            break;
                        case "-":
                            value = left - right;
                            break;
                        case "*":
                            value = left * right;
                            break;
                        case "/":
                            value = left / right;
                            break;
                        case "^":
                            value = Math.pow(left, right);
                        default:
                            break;
                    }
                    operands.push(value);
                    break;
                default:
                    operands.push(Double.parseDouble(str));
                    break;
            }
        }
        return operands.pop();
    }
}
