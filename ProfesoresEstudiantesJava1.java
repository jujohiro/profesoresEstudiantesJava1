package arrayList;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProfesoresEstudiantesJava1 {

    // Declaración de las listas estáticas
    static ArrayList<String> listProfesores;
    static ArrayList<String> listEstudiantes; 
    static ArrayList<ArrayList<String>> listaGeneralEstudiantes;

    // Método principal, punto de entrada del programa
    public static void main(String[] args) {
        // Inicialización de las listas
        ProfesorEstudiantes();
        // Muestra el menú de opciones al usuario
        mostrarMenu();
    }

    // Método para inicializar las listas
    public static void ProfesorEstudiantes() {
        listProfesores = new ArrayList<>();
        listEstudiantes = new ArrayList<>();
        listaGeneralEstudiantes = new ArrayList<>();
    }

    // Método que muestra el menú de opciones al usuario y maneja la selección
    private static void mostrarMenu() {
        // Definición de las opciones del menú
        String[] opciones = {
            "Registrar Profesores",
            "Registrar Estudiantes",
            "Consultar Listas de Profesores y Estudiantes",
            "Consultar Profesor",
            "Consultar Estudiantes por Profesor",
            "Consultar Estudiante",
            "Salir"
        };
        
        boolean salir = false;
        // Ciclo para mostrar el menú hasta que el usuario decida salir
        while (!salir) {
            // Mostrar el menú y obtener la selección del usuario
            String opcion = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:", "Menú", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if (opcion == null || opcion.equals("Salir")) {
                salir = true;
            } else {
                // Manejar la selección del usuario
                switch (opcion) {
                    case "Registrar Profesores":
                        RegistrarProfesores();
                        break;
                    case "Registrar Estudiantes":
                        RegistrarEstudiantes();
                        break;
                    case "Consultar Listas de Profesores y Estudiantes":
                        // Validar que existan profesores y estudiantes antes de consultar
                        if (listProfesores.isEmpty() || listaGeneralEstudiantes.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Debe registrar primero profesores y estudiantes.");
                        } else {
                            consultarListasProfesoresYEstudiantes();
                        }
                        break;
                    case "Consultar Profesor":
                        // Validar que existan profesores antes de consultar
                        if (listProfesores.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Debe registrar primero profesores.");
                        } else {
                            consultarProfesor();
                        }
                        break;
                    case "Consultar Estudiantes por Profesor":
                        // Validar que existan profesores y estudiantes antes de consultar
                        if (listProfesores.isEmpty() || listaGeneralEstudiantes.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Debe registrar primero profesores y estudiantes.");
                        } else {
                            consultarEstudiantePorProfesor();
                        }
                        break;
                    case "Consultar Estudiante":
                        // Validar que existan estudiantes antes de consultar
                        if (listaGeneralEstudiantes.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Debe registrar primero estudiantes.");
                        } else {
                            consultarEstudiante();
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    // Método para registrar profesores
    private static void RegistrarProfesores() {
        int contador = 0;
        System.out.println("\n<<<Registro de Profesores>>>\n");

        // Pedir al usuario cuántos profesores desea registrar
        try {
            contador = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos Profesores desea registrar?"));
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida. Por favor, introduzca un número.");
            return;
        }

        // Ciclo para registrar cada profesor
        for (int i = 0; i < contador; i++) {
            String profesor;
            // Asegurarse de que no se registren nombres duplicados
            do {
                profesor = JOptionPane.showInputDialog("Ingrese el nombre del profesor " + (i + 1));
                if (listProfesores.contains(profesor)) {
                    JOptionPane.showMessageDialog(null, "El nombre del profesor ya existe. Ingrese un nombre diferente.");
                }
            } while (listProfesores.contains(profesor));

            // Añadir el nombre del profesor a la lista
            listProfesores.add(profesor);
        }

        System.out.println("Registro de profesores exitoso!!\n");
    }

    // Método para registrar estudiantes
    private static void RegistrarEstudiantes() {
        System.out.println("\n<<<Registro de Estudiantes>>>\n");

        // Ciclo para cada profesor registrado
        for (String profesor : listProfesores) {
            JOptionPane.showMessageDialog(null, "Ingrese los estudiantes para el profesor " + profesor);
            listEstudiantes = new ArrayList<>();

            int contador = 0;
            // Pedir al usuario cuántos estudiantes desea registrar
            try {
                contador = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos estudiantes desea registrar?"));
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduzca un número.");
                return;
            }

            // Ciclo para registrar cada estudiante
            for (int x = 0; x < contador; x++) {
                String estudiante;
                // Asegurarse de que no se registren nombres duplicados
                do {
                    estudiante = JOptionPane.showInputDialog("Ingrese el nombre del estudiante " + (x + 1));
                    if (listEstudiantes.contains(estudiante)) {
                        JOptionPane.showMessageDialog(null, "El nombre del estudiante ya existe en este grupo. Ingrese un nombre diferente.");
                    }
                } while (listEstudiantes.contains(estudiante));

                // Añadir el nombre del estudiante a la lista
                listEstudiantes.add(estudiante);
            }

            // Añadir la lista de estudiantes del profesor a la lista general
            listaGeneralEstudiantes.add(listEstudiantes);
        }

        System.out.println("Registro de estudiantes exitoso!!");
    }

    // Método para consultar y mostrar listas de profesores y estudiantes
    private static void consultarListasProfesoresYEstudiantes() {
        System.out.println("\n<<<Lista de Profesores y Estudiantes>>>\n");

        // Ciclo para cada lista de estudiantes asociada a un profesor
        for (int i = 0; i < listaGeneralEstudiantes.size(); i++) {
            ArrayList<String> listaTemporal = listaGeneralEstudiantes.get(i);
            System.out.print("Profesor: " + listProfesores.get(i) + " = [");
            // Ciclo para mostrar cada estudiante en la lista temporal
            for (int x = 0; x < listaTemporal.size(); x++) {
                System.out.print(listaTemporal.get(x));
                if (x < listaTemporal.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

    // Método para consultar los estudiantes de un profesor específico
    private static void consultarProfesor() {
        System.out.println("\n<<<Consulta de Profesor >>>");
        // Pedir al usuario el nombre del profesor a consultar
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor a consultar");

        if (listProfesores.contains(nombre)) {
            int posicion = listProfesores.indexOf(nombre);
            ArrayList<String> listaEstudiantesTemporal = listaGeneralEstudiantes.get(posicion);

            System.out.print("\nProfesor: " + nombre + " = [");
            // Ciclo para mostrar cada estudiante asociado al profesor consultado
            for (int i = 0; i < listaEstudiantesTemporal.size(); i++) {
                System.out.print(listaEstudiantesTemporal.get(i));
                if (i < listaEstudiantesTemporal.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        } else {
            System.out.println("No se encuentra el profesor " + nombre);
        }
    }

    // Método para consultar la cantidad de estudiantes de un profesor específico
    private static void consultarEstudiantePorProfesor() {
        System.out.println("\n<<< Consulta cantidad de estudiantes de profesores >>> \n");
        // Pedir al usuario el nombre del profesor a consultar
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor para saber la cantidad de estudiantes asociados");

        if (listProfesores.contains(nombre)) {
            int posicion = listProfesores.indexOf(nombre);
            ArrayList<String> listaEstudiantesTemporal = listaGeneralEstudiantes.get(posicion);
            System.out.println("La cantidad de estudiantes asociados al profesor " + nombre + " es " + listaEstudiantesTemporal.size());
        } else {
            System.out.println("No se encuentra el profesor " + nombre + "\n");
        }
    }

    // Método para consultar si un estudiante pertenece a algún profesor
    private static void consultarEstudiante() {
        System.out.println("\n<<<Consulta de Estudiante >>>\n");
        // Pedir al usuario el nombre del estudiante a consultar
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante a consultar");
        boolean encontrado = false;

        // Ciclo para recorrer cada lista de estudiantes
        for (int i = 0; i < listaGeneralEstudiantes.size(); i++) {
            ArrayList<String> listaTemporal = listaGeneralEstudiantes.get(i);
            // Verificar si el estudiante se encuentra en la lista
            if (listaTemporal.contains(nombre)) {
                System.out.println("El estudiante " + nombre + " pertenece al grupo del profesor " + listProfesores.get(i));
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encuentra el nombre del estudiante " + nombre + " en ninguna lista.");
        }
    }
}
