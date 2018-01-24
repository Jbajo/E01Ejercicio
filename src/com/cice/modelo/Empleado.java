package com.cice.modelo;

/**
 *
 * @author Javier Bajo Chacon  javier.bajochacon@gmail.com
 *
 */

public class Empleado {
    private String nombre;
    private String dni;
    private float salario;
    private String fechaNacimiento;

    public Empleado(String nombre, float salario, String fechaNacimiento, String dni) {

        this.nombre = nombre;
        this.salario = salario;
        this.fechaNacimiento = fechaNacimiento;
        this.dni=dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Empleado() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Empleado nombre=" + nombre + ", salario=" + salario;
    }

    public String devuelvePuesto() {
        return "Empleado";
    }

    protected void incentivo(){
        this.salario=salario;
    }

}
