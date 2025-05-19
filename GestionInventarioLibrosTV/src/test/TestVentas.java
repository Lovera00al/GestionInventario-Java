package test;

import modelos.Libro;
import modelos.Venta;
import reportes.MenuReportes;
import utilidades.UtilArchivo;

import java.util.List;
import java.util.Scanner;

import gestion.GestionInventario;

public class TestVentas {
	private static final String ARCHIVO_LIBROS = "libros.csv";
	private static final String ARCHIVO_VENTAS = "ventas.csv";

	public static void main(String[] args) {
		// Cargar los datos desde archivos utilizando clase utilitaria
		List<Libro> libros = UtilArchivo.obtenerLibros(ARCHIVO_LIBROS);
		List<Venta> ventas = UtilArchivo.obtenerVentas(ARCHIVO_VENTAS, libros);

		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		while (!salir) {
			menuPrincipal();
			int opcion = sc.nextInt();

			switch (opcion) {
				case 1 -> System.out.println("Función de registrar venta aún no implementada.");
				case 2 -> {
					System.out.println("--------------Inventario----------------------------");
					libros.forEach(System.out::println);
				}
				case 3 -> {
					System.out.println("----------------------Ventas--------------------------");
					ventas.forEach(System.out::println);
				}
				case 4 -> MenuReportes.mostrarReportes(libros, ventas);

				case 5 -> GestionInventario.agregarLibro(libros, sc);
				case 6 -> {
					// Guardar datos al salir
					UtilArchivo.guardarLibros(ARCHIVO_LIBROS, libros);
					UtilArchivo.guardarVentas(ARCHIVO_VENTAS, ventas);
					System.out.println("Datos guardados. ¡Hasta pronto!");
					salir = true;
				}
				default -> System.out.println("Opción incorrecta. Seleccione una opción válida (1-5).");
			}
		}
	}

	private static void menuPrincipal() {
		System.out.println("======== MENU PRINCIPAL ========");
		System.out.println("1. Registrar nueva venta");
		System.out.println("2. Ver inventario");
		System.out.println("3. Ver ventas");
		System.out.println("4. Reportes");
		System.out.println("5. Agregar un nuevo libro");
		System.out.println("6. Guardar y salir");
		System.out.print("Seleccione una opción: ");
	}
}
