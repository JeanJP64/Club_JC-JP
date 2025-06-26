package club;

import java.util.ArrayList;
import club.Socio.Tipo;

public class Club {
    public final static int MAXIMO_VIP = 3;
    private ArrayList<Socio> socios;

    public Club() {
        socios = new ArrayList<>();
    }

    public ArrayList<Socio> darSocios() {
        return socios;
    }

    public void afiliarSocio(String pCedula, String pNombre, Tipo pTipo) {
        Socio s = buscarSocio(pCedula);
        if (pTipo == Tipo.VIP && contarSociosVIP() == MAXIMO_VIP) {
            System.out.println("El club no acepta más socios VIP.");
        } else if (s == null) {
            socios.add(new Socio(pCedula, pNombre, pTipo));
        } else {
            System.out.println("El socio ya existe.");
        }
    }

    public Socio buscarSocio(String pCedulaSocio) {
        for (Socio s : socios) {
            if (s.darCedula().equals(pCedulaSocio)) return s;
        }
        return null;
    }

    public int contarSociosVIP() {
        int conteo = 0;
        for (Socio s : socios) {
            if (s.darTipo() == Tipo.VIP) conteo++;
        }
        return conteo;
    }

    public ArrayList<String> darAutorizadosSocio(String pCedulaSocio) {
        Socio s = buscarSocio(pCedulaSocio);
        ArrayList<String> autorizados = new ArrayList<>();
        autorizados.add(s.darNombre());
        autorizados.addAll(s.darAutorizados());
        return autorizados;
    }

    public void agregarAutorizadoSocio(String pCedulaSocio, String pNombreAutorizado) {
        Socio s = buscarSocio(pCedulaSocio);
        s.agregarAutorizado(pNombreAutorizado);
    }

    public void eliminarAutorizadoSocio(String pCedulaSocio, String pNombreAutorizado) {
        Socio s = buscarSocio(pCedulaSocio);
        s.eliminarAutorizado(pNombreAutorizado);
    }

    public void registrarConsumo(String pCedulaSocio, String pNombreCliente, String pConcepto, double pValor) {
        Socio s = buscarSocio(pCedulaSocio);
        s.registrarConsumo(pNombreCliente, pConcepto, pValor);
    }

    public ArrayList<Factura> darFacturasSocio(String pCedulaSocio) {
        return buscarSocio(pCedulaSocio).darFacturas();
    }

    public void pagarFacturaSocio(String pCedulaSocio, int pFacturaIndice) {
        Socio s = buscarSocio(pCedulaSocio);
        s.pagarFactura(pFacturaIndice);
    }

    public void aumentarFondosSocio(String pCedulaSocio, double pValor) {
        Socio s = buscarSocio(pCedulaSocio);
        s.aumentarFondos(pValor);
    }

    public String metodo1() {
        return "respuesta1";
    }

    public String metodo2() {
        return "respuesta2";
    }
}