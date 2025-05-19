package reportes;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import modelos.Libro;
import modelos.Venta;

public class MenuReportes {

	public static void mostrarReportes(List<Libro> libros, List<Venta> ventas) {
		Scanner sc = new Scanner(System.in);
		int opcion;
		do {
			System.out.println("==== SUBMENÚ DE REPORTES ====");
			System.out.println("1. Libros con stock bajo (<5)");
			System.out.println("2. Total de ventas");
			System.out.println("3. Libro más vendido");
			System.out.println("4. Volver");
			System.out.print("Opción: ");
			opcion = Integer.parseInt(sc.nextLine());

			switch (opcion) {
				case 1 -> libros.stream().filter(l -> l.getStock() < 5).forEach(System.out::println);
				case 2 -> {
					int total = ventas.stream().mapToInt(Venta::getCantidadVendida).sum();
					System.out.println("Total vendidos: " + total);
				}
				case 3 -> {
					ventas.stream()
						.collect(Collectors.groupingBy(v -> v.getLibro().getTitulo(), Collectors.summingInt(Venta::getCantidadVendida)))
						.entrySet().stream()
						.max(Map.Entry.comparingByValue())
						.ifPresent(e -> System.out.println("Más vendido: " + e.getKey() + " (" + e.getValue() + " unidades)"));
				}
			}
		} while (opcion != 4);
	}

}
