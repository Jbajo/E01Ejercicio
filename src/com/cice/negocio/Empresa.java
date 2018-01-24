package com.cice.negocio;

import java.util.ArrayList;
import java.util.Scanner;

import com.cice.controlador.ValidadorDNI;
import com.cice.modelo.Director;
import com.cice.modelo.Empleado;
import com.cice.modelo.Gerente;
import com.cice.modelo.ValidadorDNI;


/**
 * @author Javier Bajo Chacon  javier.bajochacon@gmail.com
 */

public class Empresa {

    private ArrayList <Empleado> listaEmpleados= new ArrayList <>();
    private ArrayList <String> listaDepartamentos= new ArrayList <>();
    private ArrayList <Gerente> listaGerentes = new ArrayList <>();
    private ArrayList <Director> listaDirectores = new ArrayList<>();

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
            Gerente gerente = new Gerente (aux.getNombre(),aux.getSalario(), aux.getFechaNacimiento(), aux.getDni(), listaDepartamentos.get(opcion2-1));;
            comprobarAgregarGerenteDepartamento(gerente,listaDepartamentos.get(opcion2-1));
        }
        else if (listaEmpleados.size()==0)
            System.out.println("Debe crear antes un Empleado");

        else
            System.out.println("Debe crear antes un Departamento");

    }

    /**
     * Método comprobarAgregarGerenteDepartamento comprueba que el empleado no sea Gerente de más de un Departamento
     * ,que el Departamento no tenga Gerente y que el empleado no sea todavía Gerente del Departamento
     * @param empleado empleado a procesar
     * @param departamento en el que realizar la bśuqueda
     */
    private void comprobarAgregarGerenteDepartamento(Gerente empleado, String departamento) {
        int i = 0;
        int aux =0;
        boolean bandera = false;

        bandera = comprobarGerenteDirector(empleado);
        if(listaGerentes.size()>0&&!bandera)
            for (Gerente gerente:listaGerentes) {
                bandera = validacionesGerente(gerente, empleado, departamento);
                if (i==listaGerentes.size()&&!bandera) {
                    empleado.setDepartamento(departamento);
                    listaGerentes.add(empleado);
                    int j = 0;
                    for(Empleado emp: listaEmpleados) {
                        if(emp.getDni().equals(empleado.getDni()))
                            aux = j;
                        j++;
                    }
                    listaEmpleados.remove(aux);
                    listaEmpleados.add(empleado);
                }
                i++;

            }
        else if(listaEmpleados.size() >0&&!bandera) {
            empleado.setDepartamento(departamento);
            listaGerentes.add(empleado);
            int j = 0;
            for(Empleado emp: listaEmpleados) {
                if(emp.getDni().equals(empleado.getDni()))
                    aux = j;
                j++;
            }
            listaEmpleados.remove(aux);
            listaEmpleados.add(empleado);
        }
    }

    /**
     * Método comprobarGerenteDirector comprueba que el Gerente no sea Director de un Departamento
     * @param empleado Director
     * @return true o false
     */
    private boolean comprobarGerenteDirector(Empleado empleado) {
        if(listaDirectores.size()>0)
            for(Director director:listaDirectores) {
                if(empleado.getDni().equals(director.getDni())){
                    System.out.println("Este Empleado ya es Director de un Departamento");
                    return true;
                }
            }
        return false;
    }

    /**
     * Método validacionesGerente con las distintas validaciones a la hora de crear un Gerente
     * @param gerente
     * @param empleado
     * @param departamento
     * @return true o false
     */

    private boolean validacionesGerente(Gerente gerente, Empleado empleado, String departamento) {

        if(gerente.getDni().equals(empleado.getDni()) && gerente.getDepartamento().equals(departamento)) {
            System.out.println("Este empleado ya es Gerente de este Departamento");
            return true;
        }
        else if (gerente.getDni().equals(empleado.getDni()) &&!gerente.getDepartamento().equals(departamento)) {
            System.out.println("El empleado no puede ser Gerente de más de un Departamento");
            return true;
        }
        else if (!gerente.getDni().equals(empleado.getDni()) && gerente.getDepartamento().equals(departamento)) {
            System.out.println("Este Departamento ya tiene un Gerente");
            return true;
        }
        return false;
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
            Director director = new Director (aux.getNombre(),aux.getSalario(), aux.getFechaNacimiento(), aux.getDni());
            director.setMatriculaCoche(comprobarMatricula());
            comprobarAgregarDirectorDepartamento(director,listaDepartamentos.get(opcion2-1));
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
            if(listaDirectores.size()>0)
                for(Director director: listaDirectores) {
                    if(director.getMatriculaCoche().equals(matricula))
                        bandera = true;
                    else
                        bandera = false;
                }
            bandera = false;
        }while(bandera);

        return matricula;
    }

    /**
     * Método comprobarAgregarDirectorDepartamento comprueba que el empleado no sea Director de más de un Departamento
     * ,que el Departamento no tenga Director y que el empleado no sea todavía Director del Departamento
     * @param empleado empleado a procesar
     * @param departamento en el que realizar la bśuqueda
     */
    private void comprobarAgregarDirectorDepartamento(Director empleado, String departamento) {
        int i = 0;
        int aux = 0;
        boolean bandera = false;

        comprobarEliminarGerente(empleado);
        if(listaDirectores.size()>0)
            for (Director director:listaDirectores) {
                bandera = validacionesDirector(director, empleado, departamento);
                if (i==listaDirectores.size()&&!bandera) {
                    empleado.setDepartamento(departamento);
                    listaDirectores.add(empleado);
                    int j = 0;
                    for(Empleado emp: listaEmpleados) {
                        if(emp.getDni().equals(empleado.getDni()))
                            aux = j;
                        j++;
                    }
                    listaEmpleados.remove(aux);
                    listaEmpleados.add(empleado);
                }
                i++;

            }
        else if(listaEmpleados.size() >0) {
            empleado.setDepartamento(departamento);
            listaDirectores.add(empleado);
            int j = 0;
            for(Empleado emp: listaEmpleados) {
                if(emp.getDni().equals(empleado.getDni())) {
                    aux = j;
                }
                j++;
            }
            listaEmpleados.remove(aux);
            listaEmpleados.add(empleado);
        }

    }

    /**
     * Método validacionesDirector con las distintas validaciones a la hora de crear un Director
     * @param director
     * @param empleado
     * @param departamento
     * @return true o false
     */

    private boolean validacionesDirector(Director director, Empleado empleado, String departamento) {
        if(director.getDni().equals(empleado.getDni()) && director.getDepartamento().equals(departamento)) {
            System.out.println("Este empleado ya es Director de este Departamento");
            return true;
        }
        else if (director.getDni().equals(empleado.getDni()) &&!director.getDepartamento().equals(departamento)) {
            System.out.println("El empleado no puede ser Director de más de un Departamento");
            return true;
        }
        else if (!director.getDni().equals(empleado.getDni()) && director.getDepartamento().equals(departamento)) {
            System.out.println("Este Departamento ya tiene un Director");
            return true;
        }
        return false;
    }

    /**
     * Método ComprobarEliminarGerente comprueba que el Director no sea previamente Gerente si es así lo elimina de la lista de Gerentes
     * @param empleado Director
     */

    private void comprobarEliminarGerente(Director empleado){
        boolean bandera = false;
        int i = 0;
        int aux = 0;

        if(listaGerentes.size() > 0)
            for (Gerente gerente : listaGerentes) {
                if(empleado.getDni().equals(gerente.getDni())) {
                    aux = i;
                    bandera = true;
                }
                i++;
            }
        if(bandera)
            listaGerentes.remove(aux);
    }

    /**
     * Método mostrarGerentes() muestra los Gerentes del ArrayList de Gerentes
     */

    private void mostrarGerentes() {
        int i = 0;
        if (listaGerentes.size()>0) {
            for (Gerente gerente : listaGerentes)
                System.out.println(i+ ") "+ gerente.toString());
            i++;
        }
    }
    /**
     * Método mostrarDirectores() muestra los Directores del ArrayList de Directores
     */

    private void mostrarDirectores() {
        int i = 0;
        if (listaDirectores.size()>0) {
            for (Director director : listaDirectores)
                System.out.println(i+ ") "+ director.toString());
            i++;
        }
    }

}
