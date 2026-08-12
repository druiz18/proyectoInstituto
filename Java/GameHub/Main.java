import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        // Creamos el objeto Scanner para leer información del teclado
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

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

        // ============================================
        // VALIDACIÓN DE EDAD Y SELECCIÓN DE JUEGO
        // ============================================

        if (edad > 25) {
            // ============================================
            // JUEGO: ADIVINAR EL NÚMERO (Mayores de 25)
            // ============================================
            System.out.println("\n========================================");
            System.out.println("   ¡BIENVENIDO AL JUEGO DE ADIVINANZA!");
            System.out.println("========================================");
            System.out.println("Debes adivinar un número entre 1 y 100.");
            System.out.println("Tienes máximo 5 intentos.");
            System.out.println("Te diré si el número es mayor o menor.\n");

            int numeroSecreto = random.nextInt(100) + 1; // Número entre 1 y 100
            int intentos = 0;
            int maxIntentos = 5;
            boolean adivinado = false;

            while (intentos < maxIntentos && !adivinado) {
                System.out.print("Intento " + (intentos + 1) + " de " + maxIntentos + ". Ingresa un número: ");
                
                // Validar que el usuario ingrese un número
                while (!scanner.hasNextInt()) {
                    System.out.println("Por favor, ingresa un número válido.");
                    System.out.print("Intento " + (intentos + 1) + " de " + maxIntentos + ". Ingresa un número: ");
                    scanner.next();
                }
                
                int numeroUsuario = scanner.nextInt();
                intentos++;

                if (numeroUsuario == numeroSecreto) {
                    System.out.println("\n🎉 ¡FELICIDADES! ¡Adivinaste el número " + numeroSecreto + "!");
                    System.out.println("Lo lograste en " + intentos + " intentos.");
                    puntos += (maxIntentos - intentos + 1) * 50; // Más puntos por menos intentos
                    monedas += 50;
                    adivinado = true;
                } else if (numeroUsuario < numeroSecreto) {
                    System.out.println("📈 El número secreto es MAYOR que " + numeroUsuario + ".\n");
                } else {
                    System.out.println("📉 El número secreto es MENOR que " + numeroUsuario + ".\n");
                }
            }

            if (!adivinado) {
                System.out.println("\n😢 ¡Se acabaron los intentos! El número secreto era: " + numeroSecreto);
                vidas--;
            }

        } else {
            // ============================================
            // JUEGO: PIEDRA, PAPEL O TIJERA (Menores de 25)
            // ============================================
            System.out.println("\n========================================");
            System.out.println("   ¡PIEDRA, PAPEL O TIJERA!");
            System.out.println("========================================");
            System.out.println("Jugarás contra la computadora.");
            System.out.println("Gana quien gane 2 de 3 rondas.\n");

            int rondasJugadas = 0;
            int rondasGanadasJugador = 0;
            int rondasGanadasComputadora = 0;
            int rondasNecesarias = 2; // Para ganar el juego

            while (rondasJugadas < 3 && rondasGanadasJugador < rondasNecesarias && rondasGanadasComputadora < rondasNecesarias) {
                
                System.out.println("\n--- RONDA " + (rondasJugadas + 1) + " ---");
                System.out.println("Marcador: Tú " + rondasGanadasJugador + " - " + rondasGanadasComputadora + " Computadora");
                System.out.println("Elige: 1 = Piedra | 2 = Papel | 3 = Tijera");
                System.out.print("Tu elección: ");

                // Validar entrada del usuario
                while (!scanner.hasNextInt()) {
                    System.out.println("Opción inválida. Ingresa 1, 2 o 3.");
                    System.out.print("Tu elección: ");
                    scanner.next();
                }

                int eleccionJugador = scanner.nextInt();

                // Validar que sea entre 1 y 3
                if (eleccionJugador < 1 || eleccionJugador > 3) {
                    System.out.println("Opción inválida. Debes elegir 1, 2 o 3.");
                    continue; // Repetir la ronda sin contarla
                }

                // Elección de la computadora (1-3)
                int eleccionComputadora = random.nextInt(3) + 1;

                // Mostrar elecciones
                String[] opciones = {"Piedra", "Papel", "Tijera"};
                System.out.println("Tú elegiste: " + opciones[eleccionJugador - 1]);
                System.out.println("Computadora eligió: " + opciones[eleccionComputadora - 1]);

                // Determinar ganador de la ronda
                if (eleccionJugador == eleccionComputadora) {
                    System.out.println("🤝 ¡Empate en esta ronda!");
                    // No contamos la ronda, se repite
                    continue;
                } else if (
                    (eleccionJugador == 1 && eleccionComputadora == 3) || // Piedra vence Tijera
                    (eleccionJugador == 2 && eleccionComputadora == 1) || // Papel vence Piedra
                    (eleccionJugador == 3 && eleccionComputadora == 2)    // Tijera vence Papel
                ) {
                    System.out.println("✅ ¡Ganaste esta ronda!");
                    rondasGanadasJugador++;
                } else {
                    System.out.println("❌ ¡La computadora ganó esta ronda!");
                    rondasGanadasComputadora++;
                }

                rondasJugadas++;
            }

            // Resultado final del juego
            System.out.println("\n========================================");
            System.out.println("         RESULTADO");
            System.out.println("========================================");
            System.out.println("Marcador final: Tú " + rondasGanadasJugador + " - " + rondasGanadasComputadora + " Computadora");

            if (rondasGanadasJugador > rondasGanadasComputadora) {
                System.out.println("🏆 ¡FELICIDADES " + nombre.toUpperCase() + "! ¡GANASTE EL JUEGO!");
                puntos += 100;
                monedas += 30;
            } else {
                System.out.println("😢 La computadora ganó el juego. ¡Suerte para la próxima!");
                vidas--;
            }
        }

        // ============================================
        // RESUMEN FINAL
        // ============================================
        System.out.println("\n========== RESUMEN FINAL ==========");
        System.out.println("Jugador: " + nombre);
        System.out.println("Personaje: " + personaje);
        System.out.println("Nivel: " + nivel);
        System.out.println("Vidas restantes: " + vidas);
        System.out.println("Monedas: " + monedas);
        System.out.println("Puntos totales: " + puntos);
        System.out.println("===================================");
        System.out.println("   ¡Gracias por jugar en GAME HUB!");
        System.out.println("===================================");

        // Cerrar Scanner
        scanner.close();
    }
}