package com.cice.modelo;

/**
 *
 * @author Javier Bajo Chacon  javier.bajochacon@gmail.com
 *
 */
public class Director extends Empleado{

    private String matriculaCoche;
    private String departamento;

    private void incentivo() {
        this.setSalario(this.getSalario()*(float)1.10+(float)100);
    }

    public Director(String nombre, float salario, String fechaNacimiento, String matriculaCoche,String dni, String departamento) {
        super(nombre, salario, fechaNacimiento, dni);
        this.matriculaCoche = matriculaCoche;
        this.departamento = departamento;
        this.incentivo();
    }

    public Director(String nombre, float salario, String fechaNacimiento, String dni) {
        super(nombre, salario, fechaNacimiento, dni);
        this.incentivo();
    }

    public String getMatriculaCoche() {
        return matriculaCoche;
    }

    public void setMatriculaCoche(String matriculaCoche) {
        this.matriculaCoche = matriculaCoche;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Director [DNI= "+ this.getDni() + ", matriculaCoche=" + matriculaCoche + ", departamento=" + departamento + "]";
    }

    public String devuelvePuesto() {
        return "Director";
    }
}
