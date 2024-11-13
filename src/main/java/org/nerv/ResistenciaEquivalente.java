package org.nerv;

import java.util.Scanner;
import java.util.Locale;

public class ResistenciaEquivalente {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        boolean continuar = true;

        while (continuar) {
            imprimirDivision("Seleccione el tipo de circuito");
            System.out.println("Seleccione el tipo de circuito (1 para serie, 2 para paralelo): ");
            int tipoCircuito = scanner.nextInt();

            imprimirDivision("Ingrese el número de resistencias");
            System.out.println("Ingrese el número de resistencias: ");
            int numResistencias = scanner.nextInt();

            double[] resistencias = new double[numResistencias];
            for (int i = 0; i < numResistencias; i++) {
                imprimirDivision("Ingrese el valor de la resistencia " + (i + 1));
                System.out.print("Ingrese el valor de la resistencia " + (i + 1) + " en ohmios (puede usar decimales): ");
                resistencias[i] = scanner.nextDouble();
            }

            imprimirDivision("Cálculo de la Resistencia Equivalente");
            double resistenciaEquivalente = calcularResistenciaEquivalente(tipoCircuito, resistencias);
            if (resistenciaEquivalente != -1) {
                System.out.println("La resistencia equivalente del circuito es: " + resistenciaEquivalente + " Ω");
            } else {
                System.out.println("Tipo de circuito no válido.");
            }

            imprimirDivision("Desea continuar?");
            System.out.print("¿Desea calcular otra resistencia? (s para sí, cualquier otra tecla para salir): ");
            char respuesta = scanner.next().toLowerCase().charAt(0);
            if (respuesta != 's') {
                continuar = false;
            }
        }

        imprimirDivision("Gracias");
        System.out.println("Gracias por usar el programa. ¡Hasta luego!");
        scanner.close();
    }

    public static double calcularResistenciaEquivalente(int tipoCircuito, double[] resistencias) {
        double resistenciaEquivalente = 0;

        if (tipoCircuito == 1) { // Circuito en serie
            for (double resistencia : resistencias) {
                resistenciaEquivalente += resistencia;
            }
        } else if (tipoCircuito == 2) { // Circuito en paralelo
            double sumaInversas = 0;
            for (double resistencia : resistencias) {
                if (resistencia != 0) {
                    sumaInversas += 1 / resistencia;
                } else {
                    System.out.println("Error: una resistencia no puede tener un valor de 0 en un circuito en paralelo.");
                    return -1;
                }
            }
            resistenciaEquivalente = 1 / sumaInversas;
        } else {
            return -1; // Tipo de circuito no válido
        }

        return resistenciaEquivalente;
    }

    public static void imprimirDivision(String titulo) {
        System.out.println("\n==========================");
        System.out.println(" " + titulo);
        System.out.println("==========================");
    }
}
