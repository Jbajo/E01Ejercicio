package com.cice.negocio;

import java.util.ArrayList;
import java.util.Scanner;

import com.cice.controlador.ValidadorDNI;
import com.cice.modelo.Director;
import com.cice.modelo.Empleado;
import com.cice.modelo.Gerente;

/**
 * @author Javier Bajo Chacon  javier.bajochacon@gmail.com
 */

public class Empresa {

    private ArrayList <Empleado> listaEmpleados= new ArrayList <>();
    private ArrayList <String> listaDepartamentos= new ArrayList <>();

    /**
     * Método showMenu muestra el menú de la aplicación
     */
    public void showMenu() {
        Scanner sc = new Scanner (System.in);
        int opcion = 0;

        do {
            System.out.println("Bienvenido a tu gestion de empresa");
            System.out.println("Esto es lo que puedo hacer por ti:");
            System.out.println("1. Crear Empleado");
            System.out.println("2. Crear Departamento");
            System.out.println("3. Asignar Gerente");
            System.out.println("4. Asignar Director");
            System.out.println("5. Mostrar todos los Empleados");
            System.out.println("6. Mostrar todos los Gerentes");
            System.out.println("7. Mostrar todos los Directores");
            System.out.println("0. Salir");
            opcion = sc.nextInt();
            controles (opcion);
        }while (opcion!=0);
        sc.close();
    }

    /**
     * Método controles llama a los distintos métodos de la aplicación
     * @param opcion seleccionada
     */
    private void controles (int opcion) {
        switch (opcion){
            case 0:
                break;
            case 1:
                //Crear Empleado
                crearEmpleado();
                break;
            case 2:
                //Crear Departamento
                crearDepartamento();
                break;
            case 3:
                //Asignar Gerente
                asignarGerente();
                break;
            case 4:
                //Asignar Director
                asignarDirector();
                break;
            case 5:
                //Mostrar todos los Empleados
                mostrarEmpleado();
                break;
            case 6:
                //Mostrar todos los Gerentes
                mostrarGerentes();
                break;
            case 7:
                //Mostrar todos los Directores
                mostrarDirectores();
                break;
            default:
                System.out.println("la opcion seleccionada no es valida");
                break;
        }
    }
    /**
     * Método mostrarDepartamento muestra todos los Departamentos del ArrayList de Departamentos
     */
    private void mostrarDepartamento() {
        int i = 0;
        for (String dept:listaDepartamentos) {
            System.out.println(i+1 + ") " +dept);
            i++;
        }
    }

    /**
     * Método crearEmpleado crea un Empleado pidiendo los datos al usuario y lo introduce en el ArrayList de Empleados
     */

    private void crearEmpleado() {
        Scanner sc = new Scanner (System.in);
        Empleado empleado = new Empleado();

        System.out.println("Introduce un nombre");
        empleado.setNombre(sc.nextLine());
        System.out.println("Introduce un salario");
        empleado.setSalario(Float.parseFloat(sc.nextLine()));
        System.out.println("Introduce una fecha de nacimiento");
        empleado.setFechaNacimiento(sc.nextLine());
        System.out.println("Introduce un DNI");
        empleado.setDni(sc.nextLine());
        while(!ValidadorDNI.validar(empleado.getDni())){
            System.out.println("DNI ERRONEO...");
            System.out.println("Introduce un DNI");
            String dni = sc.nextLine();
            empleado.setDni(dni);
        }
        while(comprobarEmpleado(empleado.getDni())) {
            System.out.println("Ya existe un empleado con ese DNI");
            System.out.println("Introduce un DNI");
            String dni = sc.nextLine();
            empleado.setDni(dni);
        }
        listaEmpleados.add(empleado);
    }

    /**
     * Método comprobarEmpleado comprueba que el dni no exista
     * @param dni del empleado a comprobar
     * @return true o false
     */
    private boolean comprobarEmpleado (String dni) {

        if(listaEmpleados.size()>0)
            for(Empleado empleado : listaEmpleados)
                if(empleado.getDni().equals(dni))
                    return true;
        return false;
    }

    /**
     * Método crearDepartamento crea un Departamento y lo introduce en el ArrayList de Departamentos
     */
    private void crearDepartamento() {
        Scanner sc = new Scanner (System.in);
        String respuesta = "";
        String dept;

        do {
            System.out.println("Introduce un nombre de Departamento");
            dept=(sc.nextLine());
            while(comprobarDepartamento(dept)) {
                System.out.println("Ya existe un departamento con ese nombre");
                System.out.println("Introduce otro nombre");
                dept=(sc.nextLine());
            }
            System.out.println("¿Quieres crear otro Departamento (s/n)");
            System.out.println("-------------");
            listaDepartamentos.add(dept);
            respuesta = sc.nextLine();
        }while(respuesta.equals("s"));
    }

    /**
     * Méetodo comprobarDepartamento comprueba que el Departamento no esté creado aún
     * @param nombre del Departamento
     * @return true o false
     */
    private boolean comprobarDepartamento (String nombre) {
        if(listaDepartamentos.size()>0)
            for(String dept : listaDepartamentos)
                if(dept.equals(nombre))
                    return true;
        return false;

    }

    /**
     * Método asignarGerente() asigna un Gerente a un Departamento dado haciendo comprobaciones
     */

    private void asignarGerente() {
        Scanner sc = new Scanner (System.in);
        int opcion1 = 1;
        int opcion2 =  1;


        if (listaEmpleados.size()>0 && listaDepartamentos.size()>0) {
            do {
                if(opcion1-1>listaEmpleados.size()-1 || opcion1-1 <0)
                    System.out.println("Opccion Erronea");
                System.out.println("Seleccione un empleado");
                mostrarEmpleado();
                opcion1 = sc.nextInt();
            }while(opcion1-1>listaEmpleados.size()-1 || opcion1-1 <0);
            int i =0;
            do {
                if(opcion2-1>listaDepartamentos.size()-1 || opcion2-1 <0)
                    System.out.println("Opccion Erronea");
                System.out.println("Seleccione un Departamento");
                mostrarDepartamento();
                opcion2 = sc.nextInt();
            }while(opcion2-1>listaDepartamentos.size()-1 || opcion2-1 <0);
            Empleado aux = listaEmpleados.get(opcion1-1);
            if(aux instanceof Gerente)
                comprobarAgregarGerenteDepartamento((Gerente)aux,listaDepartamentos.get(opcion2-1));
            if (aux instanceof Director)
                System.out.println("El empleado ya es Director de un Departamento");
            else {
                Gerente gerente = new Gerente(aux.getNombre(), aux.getSalario(), aux.getFechaNacimiento(), aux.getDni(), listaDepartamentos.get(opcion2 - 1));
                comprobarAgregarGerenteDepartamento(gerente, listaDepartamentos.get(opcion2 - 1));
            }
        }
        else if (listaEmpleados.size()==0)
            System.out.println("Debe crear antes un Empleado");

        else
            System.out.println("Debe crear antes un Departamento");

    }

    /**
     * Método comprobarAgregarGerenteDepartamento comprueba que el empleado no sea Gerente del Departamento seleccionado
     * y que que el Departamento no tenga Gerente todavía
     * @param empleado empleado a procesar
     * @param departamento en el que realizar la bśuqueda
     */
    private void comprobarAgregarGerenteDepartamento(Gerente empleado, String departamento) {
        int i = 0;
        int aux =0;
        boolean bandera = false;
        if(listaEmpleados.size()>0){
        for (Empleado emp : listaEmpleados) {
           if (emp.devuelvePuesto().equals("Empleado") && emp.getDni().equals(empleado.getDni())) {
               aux = i;
               bandera = true;
           }
           else if(emp instanceof  Gerente){
               if(emp.getDni().equals(empleado.getDni())){
                   System.out.println("Este empleado ya es Gerente de un Departamento");
                   bandera=false;
               }
               else if(!emp.getDni().equals(empleado.getDni())&&((Gerente) emp).getDepartamento().equals(departamento)){
                   System.out.println("Este Departamento ya tiene un Gerente");
                   bandera=false;

               }

           }
           i++;

       }
            if(bandera) {
                listaEmpleados.remove(aux);
                empleado.setDepartamento(departamento);
                listaEmpleados.add(empleado);
            }


       }
       else{
            empleado.setDepartamento(departamento);
            listaEmpleados.add(empleado);
        }
    }

    /**
     * Méetodo mostrarEmpleado muestra los Empleados del ArrayList de Empleados
     */
    private void mostrarEmpleado() {
        int i = 0;

        System.out.println("-------------");
        for (Empleado empleado : listaEmpleados) {
            System.out.println(i+1 + ") " + empleado.toString());
            i++;
        }
        System.out.println("-------------");
    }

    /**
     * Método asignarDirector asigna un Director a un departamento dado haciendo comprobaciones
     */

    private void asignarDirector() {
        Scanner sc = new Scanner (System.in);
        int opcion1 = 1;
        int opcion2 =  1;
        boolean bandera = false;
        String matricula;

        if (listaEmpleados.size()>0 && listaDepartamentos.size()>0) {
            do {
                if(opcion1-1>listaEmpleados.size()-1 || opcion1-1 <0)
                    System.out.println("Opccion Erronea");
                System.out.println("Seleccione un empleado");
                mostrarEmpleado();
                opcion1 = Integer.parseInt(sc.nextLine());
            }while(opcion1-1>listaEmpleados.size()-1 || opcion1-1 <0);
            int i =0;
            do {
                if(opcion2-1>listaDepartamentos.size()-1 || opcion2-1 <0)
                    System.out.println("Opccion Erronea");
                System.out.println("Seleccione un Departamento");
                mostrarDepartamento();
                opcion2 =  Integer.parseInt(sc.nextLine());
            }while(opcion2-1>listaDepartamentos.size()-1 || opcion2-1 <0);
            Empleado aux = listaEmpleados.get(opcion1-1);
            if(aux instanceof  Gerente || aux instanceof Empleado) {
                Director director = new Director(aux.getNombre(), aux.getSalario(), aux.getFechaNacimiento(), aux.getDni());
                director.setMatriculaCoche(comprobarMatricula());
                comprobarAgregarDirectorDepartamento(director, listaDepartamentos.get(opcion2 - 1));
            }
            else if (aux instanceof Director){
                comprobarAgregarDirectorDepartamento(((Director)aux), listaDepartamentos.get(opcion2 - 1));
            }

            }

        else if (listaEmpleados.size()==0)
            System.out.println("Debe crear antes un Empleado");

        else
            System.out.println("Debe crear antes un Departamento");

    }

    /**
     * Método comprobarMatricula comprueba que la matricula no esté todavía asignada a un Director
     *
     */
    private String comprobarMatricula() {
        boolean bandera = false;
        Scanner sc = new Scanner (System.in);
        String matricula;

        do {
            if(bandera)
                System.out.println("Matricula ya Existente");
            System.out.println("Introduzca una matricula para el vehiculo");
            matricula = sc.nextLine();
            while(!matricula.matches(("^\\d{4}[A-Z]{3}"))) {
                System.out.println("Matricula Erronea");
                System.out.println("Introduzca una matricula para el vehiculo");
                matricula = sc.nextLine();
            }
            if(listaEmpleados.size()>0)
            for (Empleado empleado : listaEmpleados){
                    if(empleado instanceof Director)
                        if(((Director) empleado).getMatriculaCoche().equals(matricula)){
                            bandera = true;
                        }
                        else{
                            bandera = false;
                        }
            }


        }while(bandera);

        return matricula;
    }

    /**
     * Método comprobarAgregarDirectorDepartamento comprueba que el empleado no sea Director del Departamento seleccionado
     * y que que el Departamento no tenga Director todavía
     * @param empleado empleado a procesar
     * @param departamento en el que realizar la bśuqueda
     */

    private void comprobarAgregarDirectorDepartamento(Director empleado, String departamento) {
        int i = 0;
        int aux = 0;
        boolean bandera = false;

        if(listaEmpleados.size()>0){
            for (Empleado emp : listaEmpleados) {
                if (emp.devuelvePuesto().equals("Empleado") && emp.getDni().equals(empleado.getDni())) {
                    aux = i;
                    bandera = true;
                }
                else if(emp instanceof  Director){
                    if(emp.getDni().equals(empleado.getDni())){
                        System.out.println("Este empleado ya es Director");
                        bandera=false;
                    }
                    else if(!emp.getDni().equals(empleado.getDni())&&((Director) emp).getDepartamento().equals(departamento)){
                        System.out.println("Este Departamento ya tiene un Director");
                        bandera=false;

                    }

                }
                else if (emp instanceof Gerente) {
                    if (emp.getDni().equals(empleado.getDni())) {
                        aux = i;
                        bandera = true;
                    }

                }
                i++;
            }
            if(bandera) {
                listaEmpleados.remove(aux);
                empleado.setDepartamento(departamento);
                listaEmpleados.add(empleado);
            }


        }
        else{
            empleado.setDepartamento(departamento);
            listaEmpleados.add(empleado);
        }
    }


        /**
         * Método mostrarGerentes() muestra los Gerentes del ArrayList de Empleados
         */

    private void mostrarGerentes() {
        int i = 0;
        if (listaEmpleados.size()>0) {
            for (Empleado empleado : listaEmpleados) {
                if (empleado instanceof Gerente)
                    System.out.println(i + ") " + ((Gerente)empleado).toString());
                i++;
            }
        }
    }
    /**
     * Método mostrarDirectores() muestra los Directores del ArrayList de Empleados
     */

    private void mostrarDirectores() {
        int i = 0;
        if (listaEmpleados.size()>0) {
            for (Empleado empleado : listaEmpleados) {
                if (empleado instanceof Director)
                    System.out.println(i + ") " + ((Director)empleado).toString());
                i++;
            }
        }
    }

}
