import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Creamos el objeto Scanner para leer información del teclado
        Scanner scanner = new Scanner(System.in);

        // Encabezado del programa
        System.out.println("==================================");
        System.out.println("          GAME HUB");
        System.out.println("==================================");

        // Solicitar el nombre del jugador
        System.out.print("Nombre del jugador: ");
        String nombre = scanner.nextLine();

        // Solicitar la edad
        System.out.print("Edad: ");
        int edad = scanner.nextInt();

        // Limpiar el buffer para poder leer texto nuevamente
        scanner.nextLine();

        // Solicitar el país
        System.out.print("País: ");
        String pais = scanner.nextLine();

        // Solicitar el nombre del personaje
        System.out.print("Nombre del personaje: ");
        String personaje = scanner.nextLine();

        // Solicitar el color favorito
        System.out.print("Color favorito: ");
        String color = scanner.nextLine();

        // Variables iniciales del juego
        int nivel = 1;
        int vidas = 3;
        int monedas = 100;
        int puntos = 0;

        // Mostrar toda la información almacenada
        System.out.println("\n========== PERFIL ==========");

        System.out.println("Jugador: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("País: " + pais);
        System.out.println("Personaje: " + personaje);
        System.out.println("Color favorito: " + color);
        System.out.println("Nivel: " + nivel);
        System.out.println("Vidas: " + vidas);
        System.out.println("Monedas: " + monedas);
        System.out.println("Puntos: " + puntos);

        // Cerrar Scanner
        scanner.close();

    }

}