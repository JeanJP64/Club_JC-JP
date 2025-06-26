
package club;
import java.util.ArrayList;

public class Socio {
    public enum Tipo { VIP, REGULAR }

    public final static double FONDOS_INICIALES_REGULARES = 50;
    public final static double FONDOS_INICIALES_VIP = 100;
    public final static double MONTO_MAXIMO_REGULARES = 1000;
    public final static double MONTO_MAXIMO_VIP = 5000;

    private String cedula;
    private String nombre;
    private double fondos;
    private Tipo tipoSubscripcion;
    private ArrayList<Factura> facturas;
    private ArrayList<String> autorizados;

    public Socio(String pCedula, String pNombre, Tipo pTipo) {
        cedula = pCedula;
        nombre = pNombre;
        tipoSubscripcion = pTipo;
        fondos = (pTipo == Tipo.VIP) ? FONDOS_INICIALES_VIP : FONDOS_INICIALES_REGULARES;
        facturas = new ArrayList<>();
        autorizados = new ArrayList<>();
    }

    public String darNombre() { return nombre; }
    public String darCedula() { return cedula; }
    public double darFondos() { return fondos; }
    public Tipo darTipo() { return tipoSubscripcion; }
    public ArrayList<Factura> darFacturas() { return facturas; }
    public ArrayList<String> darAutorizados() { return autorizados; }

    private boolean existeAutorizado(String pNombreAutorizado) {
        return autorizados.contains(pNombreAutorizado);
    }

    private boolean tieneFacturaAsociada(String pNombreAutorizado) {
        for (Factura f : facturas)
            if (f.darNombre().equals(pNombreAutorizado)) return true;
        return false;
    }

    public void aumentarFondos(double pFondos) {
        if (tipoSubscripcion == Tipo.VIP && fondos + pFondos > MONTO_MAXIMO_VIP)
            System.out.println("Excede el máximo para VIP.");
        else if (tipoSubscripcion == Tipo.REGULAR && fondos + pFondos > MONTO_MAXIMO_REGULARES)
            System.out.println("Excede el máximo para REGULAR.");
        else
            fondos += pFondos;
    }

    public void registrarConsumo(String pNombre, String pConcepto, double pValor) {
        if (pValor > fondos)
            System.out.println("Fondos insuficientes.");
        else
            facturas.add(new Factura(pNombre, pConcepto, pValor));
    }

    public void agregarAutorizado(String pNombreAutorizado) {
        if (pNombreAutorizado.equals(nombre))
            System.out.println("No puede agregarse a sí mismo.");
        else if (fondos == 0)
            System.out.println("Fondos insuficientes.");
        else if (existeAutorizado(pNombreAutorizado))
            System.out.println("El autorizado ya existe.");
        else
            autorizados.add(pNombreAutorizado);
    }

    public void eliminarAutorizado(String pNombreAutorizado) {
        if (tieneFacturaAsociada(pNombreAutorizado))
            System.out.println(pNombreAutorizado + " tiene una factura sin pagar.");
        else
            autorizados.remove(pNombreAutorizado);
    }

    public void pagarFactura(int pIndiceFactura) {
        Factura f = facturas.get(pIndiceFactura);
        if (f.darValor() > fondos)
            System.out.println("Fondos insuficientes.");
        else {
            fondos -= f.darValor();
            facturas.remove(pIndiceFactura);
        }
    }

    public String toString() {
        return cedula + " - " + nombre;
    }
}
