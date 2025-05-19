package gestion;

import java.util.List;
import java.util.Scanner;

import modelos.Libro;

public class GestionInventario {
    public static void agregarLibro(List<Libro> libros, Scanner sc) {
        sc.nextLine();
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Género: ");
        String genero = sc.nextLine();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(sc.nextLine());
        System.out.print("Stock: ");
        int stock = Integer.parseInt(sc.nextLine());
        libros.add(new Libro(titulo, autor, genero, precio, stock));
        System.out.println("Libro agregado correctamente.");
    }
}
