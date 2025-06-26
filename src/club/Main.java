package club;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import club.Socio.Tipo;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Club c = new Club();
        int op = 0;

        do {
            try {
                System.out.println("\n--- Menú del Club ---");
                System.out.println("1. Afiliar un socio al club");
                System.out.println("2. Registrar una persona autorizada por un socio");
                System.out.println("3. Pagar una factura");
                System.out.println("4. Registrar un consumo en la cuenta de un socio");
                System.out.println("5. Aumentar fondos de la cuenta de un socio");
                System.out.println("6. Salir");
                System.out.print("Ingrese una opción: ");

                op = leerEntero(sc);

                switch (op) {
                    case 1:
                        afiliarSocioHandler(c, sc);
                        break;

                    case 2:
                        agregarAutorizadoHandler(c, sc);
                        break;

                    case 3:
                        pagarFacturaHandler(c, sc);
                        break;

                    case 4:
                        registrarConsumoHandler(c, sc);
                        break;

                    case 5:
                        aumentarFondosHandler(c, sc);
                        break;

                    case 6:
                        System.out.println("Gracias por usar el sistema del club.");
                        break;

                    default:
                        System.out.println("Opción inválida. Por favor ingrese un número del 1 al 6.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine(); // Limpiar buffer
            }
        } while (op != 6);

        sc.close();
    }

    // Métodos auxiliares para manejo de excepciones

    private static int leerEntero(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor ingrese un número: ");
            }
        }
    }

    private static double leerDouble(Scanner sc) {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor ingrese un número válido: ");
            }
        }
    }

    private static void afiliarSocioHandler(Club c, Scanner sc) {
        System.out.print("Ingrese la cédula del socio: ");
        String cedula = sc.nextLine();

        System.out.print("Ingrese el nombre del socio: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese el tipo de suscripción (VIP o REGULAR): ");
        String tipoStr = sc.nextLine().toUpperCase();

        try {
            Tipo tipo = Tipo.valueOf(tipoStr);
            c.afiliarSocio(cedula, nombre, tipo);
            System.out.println("Socio afiliado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Tipo no válido. Debe ser VIP o REGULAR.");
        } catch (Exception e) {
            System.out.println("Error al afiliar socio: " + e.getMessage());
        }
    }

    private static void agregarAutorizadoHandler(Club c, Scanner sc) {
        System.out.print("Ingrese la cédula del socio: ");
        String cedula = sc.nextLine();

        System.out.print("Ingrese el nombre del autorizado: ");
        String autorizado = sc.nextLine();

        try {
            c.agregarAutorizadoSocio(cedula, autorizado);
            System.out.println("Autorizado registrado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al agregar autorizado: " + e.getMessage());
        }
    }

    private static void pagarFacturaHandler(Club c, Scanner sc) {
        System.out.print("Ingrese la cédula del socio: ");
        String cedula = sc.nextLine();

        try {
            ArrayList<Factura> facturas = c.darFacturasSocio(cedula);

            if (facturas.isEmpty()) {
                System.out.println("No hay facturas pendientes.");
                return;
            }

            System.out.println("Facturas pendientes:");
            for (int i = 0; i < facturas.size(); i++) {
                System.out.println(i + ". " + facturas.get(i));
            }

            System.out.print("Ingrese el número de la factura a pagar: ");
            int indice = leerEntero(sc);

            if (indice < 0 || indice >= facturas.size()) {
                System.out.println("Error: Número de factura inválido.");
                return;
            }

            c.pagarFacturaSocio(cedula, indice);
            System.out.println("Factura pagada exitosamente.");

        } catch (Exception e) {
            System.out.println("Error al procesar el pago: " + e.getMessage());
        }
    }

    private static void registrarConsumoHandler(Club c, Scanner sc) {
        System.out.print("Ingrese la cédula del socio: ");
        String cedula = sc.nextLine();

        System.out.print("Ingrese el nombre de quien realiza el consumo: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese el concepto del consumo: ");
        String concepto = sc.nextLine();

        System.out.print("Ingrese el valor del consumo: ");
        double valor = leerDouble(sc);

        try {
            c.registrarConsumo(cedula, nombre, concepto, valor);
            System.out.println("Consumo registrado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al registrar consumo: " + e.getMessage());
        }
    }

    private static void aumentarFondosHandler(Club c, Scanner sc) {
        System.out.print("Ingrese la cédula del socio: ");
        String cedula = sc.nextLine();

        System.out.print("Ingrese el monto a aumentar: ");
        double monto = leerDouble(sc);

        try {
            c.aumentarFondosSocio(cedula, monto);
            System.out.println("Fondos aumentados exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al aumentar fondos: " + e.getMessage());
        }
    }
}