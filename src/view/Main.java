package view;

import controller.ConvertController;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        ConvertController control = new ConvertController();
        Scanner input = new Scanner(System.in);
        String expression = "";
        boolean get = false;

        do {
            System.out.print("Digite a expressão: ");
            expression = input.next();

            if (!control.validateExpression(expression)) {
                System.out.println("Expressão inválida.\n\n");
            } else {
                if (!control.verified(expression)) {
                    System.out.println("Expressão inválida.\n\n");
                } else {
                    get = true;
                }
            }

        } while (!get);

        try {
            
            control.convert(expression);

            StringBuilder fixed = new StringBuilder(control.getPilhaConstante());
            String fixedFinal = fixed.reverse().toString();
            System.out.println("\nPós fixa: " + fixedFinal);
            System.out.printf("Resultado: %.4f\n\n", control.calculator(fixedFinal));
            
        } catch (Exception exc) {
            System.out.print("\nHouve um erro ao executar a operação! Tente novamente.\n\n");
            Main.main(args);
        }

    }
}
