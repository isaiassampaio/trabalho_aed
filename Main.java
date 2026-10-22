import java.util.Scanner;

import Controller.App;

public class Main {
    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in)) { new App(entrada).iniciar(); }
    }
}
