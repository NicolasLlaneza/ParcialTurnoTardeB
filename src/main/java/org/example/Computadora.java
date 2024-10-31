package org.example;
import java.util.ArrayList;

public class Computadora {
    // Atributos privados
    private String marca;
    private String modelo;
    private long codigoBarras;
    private double precioTotal;
    private double porcentajeAumento;
    private double montoFinal;
    private ArrayList<String[]> componentes = new ArrayList<>();
    // Constructor vacío
    public Computadora() {}

    // Métodos Get y Set
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public long getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(long codigoBarras) { this.codigoBarras = codigoBarras; }

    public double getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(double precioTotal) { this.precioTotal = precioTotal; }

    public double getPorcentajeAumento() { return porcentajeAumento; }
    public void setPorcentajeAumento(double porcentajeAumento) { this.porcentajeAumento = porcentajeAumento; }

    public double getMontoFinal() { return montoFinal; }
    public void setMontoFinal(double montoFinal) { this.montoFinal = montoFinal; }

    public ArrayList<String[]> getComponentes() { return componentes; }
    public void setComponentes(int cantidad) { this.componentes = new ArrayList<>(); }


    public boolean agregarComponente(String codigo, String[][] componentesDisponibles) {
        for (String[] componente : componentes) {
            if (componente[0].equals(codigo)) {
                System.out.println("El componente ya fue agregado anteriormente.");
                return false;
            }
        }
        for (String[] disponible : componentesDisponibles) {
            if (disponible[0].equals(codigo)) {
                componentes.add(disponible.clone());
                precioTotal += Double.parseDouble(disponible[2]); // Actualiza el precio total
                return true;
            }
        }
        System.out.println("El código ingresado es incorrecto.");
        return false;
    }

    public double calcularTotalPrecios() {
        double total = 0.0;
        for (String[] componente : componentes) {
            if (componente != null) {
                total += Double.parseDouble(componente[2]);
            }
        }
        return total;
    }

    public double calcularMontoFinal(double recargo) {
        this.montoFinal = calcularTotalPrecios() + recargo;
        return this.montoFinal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Marca: ").append(marca).append("\n")
                .append("Modelo: ").append(modelo).append("\n")
                .append("Código de Barras: ").append(codigoBarras).append("\n")
                .append("Componentes Agregados:\n");

        for (String[] componente : componentes) {
            if (componente != null) {
                sb.append("- Código: ").append(componente[0])
                        .append(", Denominación: ").append(componente[1])
                        .append(", Precio: ").append(componente[2]).append("\n");
            }
        }
        sb.append("Monto Final: ").append(montoFinal);
        return sb.toString();
    }
}
