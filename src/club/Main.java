package club;

import java.util.ArrayList;
import java.util.Scanner;
import club.Socio.Tipo;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int op;
        Club c = new Club();

        do {
            System.out.println("\n--- Menú del Club ---");
            System.out.println("1. Afiliar un socio al club");
            System.out.println("2. Registrar una persona autorizada por un socio");
            System.out.println("3. Pagar una factura");
            System.out.println("4. Registrar un consumo en la cuenta de un socio");
            System.out.println("5. Aumentar fondos de la cuenta de un socio");
            System.out.println("6. Salir");
            System.out.print("Ingrese una opción: ");
            op = Integer.parseInt(sc.next());

            switch (op) {
                case 1: {
                    System.out.print("Ingrese la cédula del socio: ");
                    String cedula = sc.next();
                    System.out.print("Ingrese el nombre del socio: ");
                    String nombre = sc.next();
                    System.out.print("Ingrese el tipo de suscripción (VIP o REGULAR): ");
                    String tipoStr = sc.next().toUpperCase();

                    if (tipoStr.equals("VIP")) {
                        c.afiliarSocio(cedula, nombre, Tipo.VIP);
                    } else if (tipoStr.equals("REGULAR")) {
                        c.afiliarSocio(cedula, nombre, Tipo.REGULAR);
                    } else {
                        System.out.println("Tipo no válido. Use VIP o REGULAR.");
                    }
                }
                break;

                case 2: {
                    System.out.print("Ingrese la cédula del socio: ");
                    String cedula = sc.next();
                    System.out.print("Ingrese el nombre del autorizado: ");
                    String autorizado = sc.next();
                    c.agregarAutorizadoSocio(cedula, autorizado);
                }
                break;

                case 3: {
                    System.out.print("Ingrese la cédula del socio: ");
                    String cedula = sc.next();
                    ArrayList<Factura> facturas = c.darFacturasSocio(cedula);

                    if (facturas.isEmpty()) {
                        System.out.println("No hay facturas pendientes.");
                    } else {
                        for (int i = 0; i < facturas.size(); i++) {
                            System.out.println(i + ". " + facturas.get(i));
                        }
                        System.out.print("Ingrese el número de la factura a pagar: ");
                        int indice = sc.nextInt();
                        c.pagarFacturaSocio(cedula, indice);
                    }
                }
                break;

                case 4: {
                    System.out.print("Ingrese la cédula del socio: ");
                    String cedula = sc.next();
                    System.out.print("Ingrese el nombre de quien realiza el consumo: ");
                    String nombre = sc.next();
                    System.out.print("Ingrese el concepto del consumo: ");
                    String concepto = sc.next();
                    System.out.print("Ingrese el valor del consumo: ");
                    double valor = sc.nextDouble();
                    c.registrarConsumo(cedula, nombre, concepto, valor);
                }
                break;

                case 5: {
                    System.out.print("Ingrese la cédula del socio: ");
                    String cedula = sc.next();
                    System.out.print("Ingrese el monto a aumentar: ");
                    double monto = sc.nextDouble();
                    c.aumentarFondosSocio(cedula, monto);
                }
                break;

                case 6:
                    System.out.println("Gracias por usar el sistema del club.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (op != 6);

        sc.close();
    }
}