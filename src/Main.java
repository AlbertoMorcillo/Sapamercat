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
            System.out.println("1. Comparar números y decir cúal de los dos es más grande (aaa el primero bbb el resto) ");
            System.out.println("2. Calcular equación de segundo grado");
            System.out.println("3. Ver un producto y calcular su precio total y modificarlo si es necesario");
            System.out.println("0. Acabar");
            System.out.print("\nElije qué quieres hacer: ");

            opcio = scan.nextInt();
            scan.nextLine();
            System.out.println();
            switch (opcio) {
                case 1:
                    break;
                case 2:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("ATENCIÓ!!! \nHa de ser un valor entre 0 i 3"); //Cambiamos el mensaje de poner entre 0 i 5 a 0 i 3.
            }
        } while (opcio != 0);
    }
}