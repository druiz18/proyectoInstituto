import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // ======================================================
        // ACTIVIDAD 2 - Declaración de variables
        // ======================================================

        System.out.println("======================================");
        System.out.println("ACTIVIDAD 2 - DECLARACIÓN DE VARIABLES");
        System.out.println("======================================");

        String nombre = "María Pérez";
        int edad = 20;
        double estatura = 1.65;
        double peso = 58.5;
        String ciudad = "Bogotá";
        boolean esEstudiante = true;

        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad + " años");
        System.out.println("Estatura: " + estatura + " m");
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Ciudad: " + ciudad);
        System.out.println("¿Es estudiante?: " + esEstudiante);

        // ======================================================
        // ACTIVIDAD 3 - Entrada y salida de datos
        // ======================================================

        System.out.println("\n======================================");
        System.out.println("ACTIVIDAD 3 - ENTRADA Y SALIDA DE DATOS");
        System.out.println("======================================");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su nombre completo: ");
        String nombreCompleto = scanner.nextLine();

        System.out.print("Ingrese su edad: ");
        int edadUsuario = scanner.nextInt();
        scanner.nextLine(); // Limpiar el salto de línea

        System.out.print("Ingrese su ciudad de residencia: ");
        String ciudadResidencia = scanner.nextLine();

        System.out.print("Ingrese su programa académico: ");
        String programa = scanner.nextLine();

        System.out.println();
        System.out.println("Hola " + nombreCompleto +
                ", tienes " + edadUsuario +
                " años, vives en " + ciudadResidencia +
                " y estudias " + programa + ".");

        scanner.close();

    }

}