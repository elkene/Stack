package Pilas;
import javax.swing.JOptionPane;
import java.util.Stack;

public class ExpresionMatematica {

    // Método para verificar si una expresión está balanceada (paréntesis, llaves, corchetes)
    public static boolean estaBalanceada(String expresion) {
        Stack<Character> pila = new Stack<>(); // Usamos una pila para manejar los símbolos de apertura

        // Recorremos la expresión caracter por caracter
        for (int i = 0; i < expresion.length(); i++) {
            char caracter = expresion.charAt(i);

            // Si el caracter es un símbolo de apertura, lo agregamos a la pila
            if (caracter == '(' || caracter == '{' || caracter == '[') {
                pila.push(caracter);
            } 
            // Si es un símbolo de cierre, verificamos si hay un símbolo correspondiente en la pila
            else if (caracter == ')' || caracter == '}' || caracter == ']') {
                if (pila.isEmpty()) {
                    return false; // Si no hay símbolo de apertura, la expresión está desbalanceada
                }

                char tope = pila.pop(); // Sacamos el último símbolo de apertura

                // Verificamos que el símbolo de apertura y cierre correspondan
                if ((caracter == ')' && tope != '(') ||
                    (caracter == '}' && tope != '{') ||
                    (caracter == ']' && tope != '[')) {
                    return false; // Si no coinciden, la expresión está desbalanceada
                }
            }
        }

        // Si la pila está vacía al final, la expresión está completamente balanceada
        return pila.isEmpty();
    }

    // Menú amigable para el usuario utilizando JOptionPane
    public static void main(String[] args) {
        boolean salir = false; // Controla si el programa continúa o no

        while (!salir) {
            // Mostrar menú con opciones
            String opcionStr = JOptionPane.showInputDialog(null, 
                "Menú:\n1. Verificar si una expresión matemática está balanceada\n"
                + "2. Salir\nElige una opción:", 
                "Menú de Opciones", 
                JOptionPane.QUESTION_MESSAGE);

            // Si el usuario cancela el diálogo, salimos del bucle
            if (opcionStr == null) {
                salir = true;
                break;
            }

            int opcion;
            try {
                // Convertimos la opción a número
                opcion = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                // Si la opción no es un número, mostramos un mensaje de error
                JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, elige un número.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                continue; // Volvemos a mostrar el menú
            }

            switch (opcion) {
                case 1:
                    // Verificar si la expresión está balanceada
                    String expresionBalanceada = JOptionPane.showInputDialog(null, 
                        "Ingresa la expresión matemática:", 
                        "Verificar Balanceo", 
                        JOptionPane.QUESTION_MESSAGE);

                    // Si el usuario no cancela, verificamos la expresión ingresada
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
                    // Salir del programa
                    salir = true;
                    JOptionPane.showMessageDialog(null, 
                        "Saliendo del programa...", 
                        "Salir", JOptionPane.INFORMATION_MESSAGE);
                    break;

                default:
                    // Si la opción no es válida, mostramos un mensaje de error
                    JOptionPane.showMessageDialog(null, 
                        "Opción no válida. Por favor, elige una opción correcta.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
