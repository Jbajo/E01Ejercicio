package com.cice.modelo;
/**
 *
 * @author Javier Bajo Chacon  javier.bajochacon@gmail.com
 *
 */
public class Gerente extends Empleado{

    private String departamento;


    public Gerente (){
        super();

    }
    public Gerente(String nombre, float salario, String fechaNacimiento, String dni, String departamento) {
        super(nombre, salario, fechaNacimiento, dni);
        this.departamento = departamento;
        this.incentivo();
    }


    public Gerente(String nombre, float salario, String fechaNacimiento, String dni) {
        super(nombre, salario, fechaNacimiento, dni);
    }



    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public void incentivo() {
        this.setSalario(this.getSalario()*1.05f);
    }

    @Override
    public String toString() {
        return super.toString()+",departamento=" + departamento;
    }

    @Override
    public String devuelvePuesto() {
        return "Gerente";
    }

    @Override
    public void eliminarIncentivo() {
        this.setSalario(this.getSalario()/1.05f);
    }
}
