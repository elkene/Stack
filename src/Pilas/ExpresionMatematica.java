package Pilas;
import javax.swing.JOptionPane;
import java.util.Stack;

public class ExpresionMatematica {

    // Método para verificar si una expresión está balanceada
    public static boolean estaBalanceada(String expresion) {
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char caracter = expresion.charAt(i);

            if (caracter == '(' || caracter == '{' || caracter == '[') {
                pila.push(caracter);
            } else if (caracter == ')' || caracter == '}' || caracter == ']') {
                if (pila.isEmpty()) {
                    return false; // Paréntesis de cierre sin apertura correspondiente
                }

                char tope = pila.pop();
                if ((caracter == ')' && tope != '(') ||
                    (caracter == '}' && tope != '{') ||
                    (caracter == ']' && tope != '[')) {
                    return false;
                }
            }
        }

        return pila.isEmpty(); // Si la pila está vacía, está balanceada
    }

    // Método para evaluar una expresión en notación postfija
    public static int evaluarPostfija(String expresion) {
        Stack<Integer> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char caracter = expresion.charAt(i);

            if (Character.isDigit(caracter)) {
                pila.push(Character.getNumericValue(caracter));
            } else {
                int operando2 = pila.pop();
                int operando1 = pila.pop();

                switch (caracter) {
                    case '+':
                        pila.push(operando1 + operando2);
                        break;
                    case '-':
                        pila.push(operando1 - operando2);
                        break;
                    case '*':
                        pila.push(operando1 * operando2);
                        break;
                    case '/':
                        pila.push(operando1 / operando2);
                        break;
                }
            }
        }

        return pila.pop(); // El último elemento es el resultado
    }

    // Menú amigable para el usuario utilizando JOptionPane
    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            // Mostrar menú con opciones
            String opcionStr = JOptionPane.showInputDialog(null, 
                "Menú:\n1. Verificar si una expresión matemática está balanceada\n"
                + "2. Evaluar una expresión matemática en notación postfija\n"
                + "3. Salir\nElige una opción:", 
                "Menú de Opciones", 
                JOptionPane.QUESTION_MESSAGE);

            if (opcionStr == null) {
                salir = true; // El usuario canceló o cerró el diálogo
                break;
            }

            int opcion;
            try {
                opcion = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, elige un número.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            switch (opcion) {
                case 1:
                    // Verificar si la expresión está balanceada
                    String expresionBalanceada = JOptionPane.showInputDialog(null, 
                        "Ingresa la expresión matemática:", 
                        "Verificar Balanceo", 
                        JOptionPane.QUESTION_MESSAGE);

                    if (expresionBalanceada != null) {
                        if (estaBalanceada(expresionBalanceada)) {
                            JOptionPane.showMessageDialog(null, 
                                "La expresión está balanceada.", 
                                "Resultado", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, 
                                "La expresión no está balanceada.", 
                                "Resultado", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 2:
                    // Evaluar expresión en notación postfija
                    String expresionPostfija = JOptionPane.showInputDialog(null, 
                        "Ingresa la expresión matemática en notación postfija:", 
                        "Evaluar Expresión Postfija", 
                        JOptionPane.QUESTION_MESSAGE);

                    if (expresionPostfija != null) {
                        try {
                            int resultado = evaluarPostfija(expresionPostfija);
                            JOptionPane.showMessageDialog(null, 
                                "El resultado de la expresión es: " + resultado, 
                                "Resultado", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, 
                                "Error al evaluar la expresión. Asegúrate de ingresar una expresión postfija válida.", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 3:
                    // Salir
                    salir = true;
                    JOptionPane.showMessageDialog(null, 
                        "Gracias por usar el programa.", 
                        "Salir", JOptionPane.INFORMATION_MESSAGE);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, 
                        "Opción no válida. Por favor, elige una opción correcta.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
