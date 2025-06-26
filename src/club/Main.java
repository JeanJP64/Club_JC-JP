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