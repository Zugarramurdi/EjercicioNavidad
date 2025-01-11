/**
 * Autor: Juanma Segura - DAM
 *
 * Pequeño programa que simula un laberinto con 4 salas, en la que hay que llegar a la sala 3 con al menos 30 de energia
 * o mas, a demas de que el usuario tiene diferentes opciones a realizar, desde moverse, inspeccionar sala, ver las energias
 * generadas en las diferentes salas y salir del programa, las energias en las salas y la inspeccion dependeran de un evento aleatorio
 * de numeros para poder determinar que ocurre.
 *
 * Pseudo código - Laberinto de Navidad:
 * INICIO - while (juegoActivo):
 * genera array de 4 salas con valores aleatorios de energia de entre -20 y +20
 * mostrar: Estas en [sala actual] y tienes [energía] puntos de energía.
 * mostrar: ¿Qué deseas hacer?
 * mostrar: 1) Mover de sala. 2) Inspeccionar sala. 3) Salir del juego.
 *
 * leer: opción del usuario.
 * wsitch (opciónUsuario):
 * caso 1: // Mover de sala
 *          mostrar: ¿A que direccion deseas moverte? (izquierda/derecha)
 *          leer: dirección.
 *          segun (dirección): // Mover de sala[salas conectadas]
 *
 *          salas conectadas: Sala 1 → Sala 2, Sala 3.
 *                            Sala 2 → Sala 1, Sala 4.
 *                            Sala 4 → Sala 1, Sala 2.
 *
 *caso 2: // Inspeccionar sala
 *         mostrar: Inspeccionando sala...
 *         switch (eventoAleatorio)
 *caso 3: // Salir del juego
 *        mostrar: Saliendo del juego...
 *        juegoActivo = false;
 *default: mostrar: Opción inválida.
 * fin switch
 *
 * // Verificar opciones de victoria o derrota
 * si (posicionActual == 3 y energia >= 30):
 *      mostrar: Has ganado el juego.
 *      juegoActivo = false;
 *sino (energia <= 0):
 *      mostrar: Has perdido el juego.
 *      juegoActivo = false;
 *fin while
 * FIN
 */

import java.util.Random;
import java.util.Scanner;

public class EjercicioNavidad {
    public static final int MIN_OPCION = 1, MAX_OPCION = 3, MAX_OPCION2 = 4;
    public static void main(String[] args) {
        Random numeroAleatorio = new Random();
        Scanner scanner = new Scanner(System.in);

        double eventoAleatorio = -1;
        int energia = 100, posicionActual = 1,opcionUsuario = 0,direccionUsuario = 0,opcionesAleatorias = -1;
        boolean juegoActivo = true;
        int[] energiaSalas = new int[4];
        for (int i = 0; i < energiaSalas.length; i++) {
            energiaSalas[i] = (int) (Math.random() * 41) - 20;
        }
        System.out.println("\t*** Bienvenido al Laberinto ***");
        System.out.println("Las reglas son: llegar a la Sala 3 con al menos 30 de energia.\nEn caso de agotar toda la energia o rendirte, habras perdido. \nLas salas tienen energias aleatorias, cuidado! Porque te pueden favorecer o mermar... \nEmpecemos!");
        while(juegoActivo) {
            energia += energiaSalas[posicionActual - 1];
            System.out.println("Te Encuentras en la Sala " + posicionActual + " con " + energia + " puntos de energia");
            if (posicionActual == 3 && energia >=30){
                System.out.println("Has llegado a la Sala 3 con la suficiente energia. \n\t *** HAS GANADO! ***");
                juegoActivo=false;
            }else if (energia <=0){
                System.out.println("Te has quedado sin energia. \n\t *** HAS PERDIDO! ***");
                juegoActivo=false;
            }
            if(juegoActivo) {
                System.out.println("Que deseas hacer?: \n  (1) Moverse de Sala \n  (2) Inspeccionar Sala actual \n  (3) Revelar energia de las Salas \n  (4) Salir del juego");
                while(opcionUsuario < 1 || opcionUsuario > 4) {
                System.out.print("Introduce valor numerico ("+MIN_OPCION+" - "+MAX_OPCION2+"): ");
                        if (scanner.hasNextInt()) {
                            opcionUsuario = scanner.nextInt();
                            if (opcionUsuario < 1 || opcionUsuario > 4) {
                                System.out.println("Opcion Incorrecta");
                                scanner.nextLine();
                            }

                        } else {
                            scanner.nextLine();
                            System.out.println("Dato no valido, tiene que ser numerico");

                        }
                        scanner.nextLine();
                }
                    switch (opcionUsuario) {
                        case 1:
                            System.out.println("Que direccion quieres moverte? \n (1) Izquierda \n (2) Derecha \n (3) Salir del juego ");
                            while(direccionUsuario < 1 || direccionUsuario > 3) {
                                System.out.print("Opcion: ");
                                if (scanner.hasNextInt()) {
                                    direccionUsuario = scanner.nextInt();
                                    if (direccionUsuario < 1 || direccionUsuario > 3){
                                        System.out.println("Opcion Incorrecta");
                                        System.out.println("Introduce valor numerico ("+MIN_OPCION+" - "+MAX_OPCION+"): ");
                                        scanner.nextLine();
                                    }
                                } else {
                                    scanner.next();
                                    System.out.println("Dato no valido, tiene que ser numerico");
                                }
                            }

                            switch (direccionUsuario) {
                                case 1:
                                    if (posicionActual == 1) {
                                        posicionActual = 3;
                                    } else if (posicionActual == 2) {
                                        posicionActual = 4;
                                    } else if (posicionActual == 4) {
                                        posicionActual = 2;
                                    } else {
                                        posicionActual = 2;
                                    }
                                    scanner.nextLine();
                                    break;
                                case 2:
                                    if (posicionActual == 1) {
                                        posicionActual = 2;
                                    } else if (posicionActual == 2) {
                                        posicionActual = 1;
                                    } else if (posicionActual == 4) {
                                        posicionActual = 1;
                                    } else {
                                        posicionActual = 4;
                                    }
                                    scanner.nextLine();
                                    break;
                                case 3:
                                    System.out.println("Cerrando el juego... \n Nos vemos en la siguiente \n\t Gracias por jugar!");
                                    juegoActivo = false;
                                    break;

                                default:
                                    System.out.println("Opcion no valida");
                            }
                            break;


                        case 2:
                            eventoAleatorio = numeroAleatorio.nextDouble(1);
                            opcionesAleatorias = numeroAleatorio.nextInt(3) + 1;
                            scanner.nextLine();
                            System.out.println("Inspeccionando Sala...");
                            if (eventoAleatorio < (1.0 / 3.0)) {
                                switch (opcionesAleatorias) {
                                    case 1:
                                        System.out.println("Te tomas una pocion \n **Recuperas 10 de energia**");
                                        break;
                                    case 2:
                                        System.out.println("Te sana una elfa \n **Recuperas 10 de energia**");
                                        break;
                                    case 3:
                                        System.out.println("Respiras hondo \n **Recuperas 10 de energia**");
                                }
                                energia += 10;
                            } else if (eventoAleatorio < (2.0 / 3.0)) {
                                switch (opcionesAleatorias) {
                                    case 1:
                                        System.out.println("Te sientes mareado \n **Pierdes 15 de energia**");
                                        break;
                                    case 2:
                                        System.out.println("Una extraña aura te rodea \n **Pierdes 15 de energia**");
                                        break;
                                    case 3:
                                        System.out.println("Oyes una voz en tu cabeza \n **Pierdes 15 de energia**");
                                }
                                energia -= 15;
                            } else {
                                System.out.println("No encuentras nada");
                            }
                            break;
                        case 3:

                            System.out.println("Se han generado las siguientes energias: ");
                            for (int i=0; i<energiaSalas.length;i++){
                                System.out.println("Sala "+(i+1)+": "+energiaSalas[i]);
                            }
                            break;
                        case 4:
                            System.out.println("Cerrando el juego... \n Nos vemos en la siguiente \n\t Gracias por jugar!");
                            juegoActivo = false;
                            break;

                        default:
                            System.out.println("Opcion no valida");

                    }
                direccionUsuario=0;
                opcionUsuario=0;
            }
        }
        if(!juegoActivo){
            System.out.println("Juego terminado");
        }
        scanner.close();
    }
}

