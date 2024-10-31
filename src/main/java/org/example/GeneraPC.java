package org.example;

import java.util.Scanner;


public class GeneraPC {
    static String[][] componentesPc = {
            {"AAA", "Placa Madre", "20000", "S"},
            {"BBB", "Procesador", "25000", "S"},
            {"CCC", "Memoria RAM", "5000", "S"},
            {"DDD", "Placa de Red", "3000", "N"},
            {"EEE", "Disco Rigido SSD", "22000", "S"},
            {"FFF", "Placa de Video", "42000", "N"},
            {"GGG", "Monitor Led 21", "32000", "N"},
            {"HHH", "Monitor Led 25", "41000", "N"},
            {"JJJ", "Kit Teclado - Mouse", "9000", "N"},
            {"KKK", "Gabinete", "6500", "S"},
            {"LLL", "Fuente Alimentación", "6500", "S"},
            {"MMM", "Placa de Sonido", "16500", "N"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Computadora computadora = new Computadora();

        System.out.print("Ingrese la marca de la computadora: ");
        computadora.setMarca(scanner.nextLine());

        System.out.print("Ingrese el modelo de la computadora: ");
        computadora.setModelo(scanner.nextLine());

        long codigoBarras;
        while (true) {
            System.out.print("Ingrese el código de barras (entre 7 y 15 dígitos): ");
            String codigoInput = scanner.nextLine();
            if (codigoInput.length() >= 7 && codigoInput.length() <= 15) {
                try {
                    codigoBarras = Long.parseLong(codigoInput);
                    computadora.setCodigoBarras(codigoBarras);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Código inválido.");
                }
            } else {
                System.out.println("Código fuera del rango permitido.");
            }
        }

        int cantidadComponentes;
        while (true) {
            System.out.print("Ingrese la cantidad de componentes (entre 5 y 12): ");
            cantidadComponentes = scanner.nextInt();
            if (cantidadComponentes >= 5 && cantidadComponentes <= 12) {
                break;
            } else {
                System.out.println("Cantidad inválida.");
            }
        }

        computadora.setComponentes(cantidadComponentes);
        scanner.nextLine();

        System.out.println("\nComponentes de la Computadora:");
        for (int i = 0; i < cantidadComponentes; i++) {
            while (true) {
                System.out.print("Ingrese el código del componente: ");
                String codigoComponente = scanner.nextLine();
                if (computadora.agregarComponente(codigoComponente, componentesPc)) {
                    break;
                }
            }
        }

        double recargo = verificarComponentesObligatorios(computadora);
        double montoFinal = computadora.calcularMontoFinal(recargo);
        System.out.println("El monto final de la computadora es: " + montoFinal);

        System.out.println("\nLa computadora especificada es:");
        System.out.println(computadora.toString());

        scanner.close();
    }

    private static double verificarComponentesObligatorios(Computadora computadora) {
        String[] obligatorios = {"AAA", "BBB", "CCC", "EEE", "KKK"};
        int faltantes = 0;

        for (String codigo : obligatorios) {
            boolean encontrado = false;
            for (String[] componente : computadora.getComponentes()) {
                if (componente != null && componente[0].equals(codigo)) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                faltantes++;
            }
        }

        if (faltantes > 0) {
            System.out.println("Atención, " + faltantes + " componente(s) obligatorio(s) no fueron agregados. Recargo del 20%.");
            return computadora.calcularTotalPrecios() * 0.2;
        }
        return 0;
    }
}
