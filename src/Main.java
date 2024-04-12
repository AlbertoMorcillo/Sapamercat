import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        mostrarMenuInicial();
    }
    public static void mostrarMenuInicial() {
        int opcio;
        do {
            System.out.println("--------MENU INICIAL--------");
            System.out.println("1. Introduir producte");
            System.out.println("2. Passar per caixa");
            System.out.println("3. Mostrar carrito de compra");
            System.out.println("0. Acabar");
            System.out.print("\nElije qué quieres hacer: ");

            opcio = scan.nextInt();
            scan.nextLine();
            System.out.println();
            switch (opcio) {
                case 1:
                    mostrarMenuIntroducirProducto();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("ATENCIÓ!!! \nHa de ser un valor entre 0 i 3"); //Cambiamos el mensaje de poner entre 0 i 5 a 0 i 3.
            }
        } while (opcio != 0);
    }

    public static void mostrarMenuIntroducirProducto() {
        int opcionProducto;
        do {
            System.out.println("--------PRODUCTE--------");
            System.out.println("Quin tipus de producte vols introduir?");
            System.out.println("1. Alimentació");
            System.out.println("2. Tèxtil");
            System.out.println("3. Electrònica");
            System.out.println("0. Tornar enrere");
            System.out.print("\nElije qué quieres hacer: ");

            opcionProducto = scan.nextInt();
            scan.nextLine();
            System.out.println();
            switch (opcionProducto) {
                case 1:

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    mostrarMenuInicial();
                    break;
                default:
                    System.out.println("ATENCIÓ!!! \nHa de ser un valor entre 0 i 3"); //Cambiamos el mensaje de poner entre 0 i 5 a 0 i 3.
            }
        } while (opcionProducto != 0);
    }
}