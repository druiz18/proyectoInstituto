import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================");
        System.out.println("        GAME HUB");
        System.out.println("==================================");
        System.out.println("Configura tu jugador\n");

        System.out.print("Nombre del jugador: ");
        String nombre = scanner.nextLine();

        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();

        System.out.print("País: ");
        String pais = scanner.nextLine();

        System.out.print("Nombre del personaje: ");
        String personaje = scanner.nextLine();

        System.out.print("Color favorito: ");
        String color = scanner.nextLine();

        int vidas = 3;
        int nivel = 1;
        int monedas = 100;
        int puntos = 0;

        System.out.println("\n==============================");
        System.out.println("PERFIL DEL JUGADOR");
        System.out.println("==============================");

        System.out.println("Jugador : " + nombre);
        System.out.println("Edad : " + edad);
        System.out.println("País : " + pais);
        System.out.println("Personaje : " + personaje);
        System.out.println("Color : " + color);
        System.out.println("Nivel : " + nivel);
        System.out.println("Vidas : " + vidas);
        System.out.println("Monedas : " + monedas);
        System.out.println("Puntos : " + puntos);

        scanner.close();
    }
}