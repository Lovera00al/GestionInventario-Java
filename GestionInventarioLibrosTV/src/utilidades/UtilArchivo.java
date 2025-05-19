package utilidades;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import modelos.Libro;
import modelos.Venta;

public class UtilArchivo {

    public static List<Libro> obtenerLibros(String archivo) {
        List<Libro> libros = new ArrayList<>();
        if (!Files.exists(Paths.get(archivo)))
            return libros;

        try {
            libros = Files.lines(Paths.get(archivo), StandardCharsets.UTF_8) // Especificamos la codificación UTF-8
                    .skip(1) // Salta la primera línea (cabecera)
                    .filter(linea -> !linea.trim().isEmpty()) // Ignora líneas vacías
                    .map(linea -> {
                        try {
                            return Libro.fromCSV(linea);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.err.println("Línea mal formateada: " + linea);
                            return null; // Retorna null si la línea está mal formada
                        }
                    })
                    .filter(Objects::nonNull) // Filtra los nulos (líneas mal formateadas)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return libros;
    }

    public static List<Venta> obtenerVentas(String archivo, List<Libro> inventario) {
        List<Venta> ventas = new ArrayList<>();
        if (!Files.exists(Paths.get(archivo)))
            return ventas;

        try {
            ventas = Files.lines(Paths.get(archivo), StandardCharsets.UTF_8) // Especificamos la codificación UTF-8
                    .skip(1) // Salta la primera línea (cabecera)
                    .filter(linea -> !linea.trim().isEmpty()) // Ignora líneas vacías
                    .map(linea -> Venta.fromCSV(linea, inventario))
                    .filter(Objects::nonNull) // Filtra los nulos (líneas mal formateadas)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ventas;
    }

    // Guardar los libros y ventas con la codificación UTF-8
    public static void guardarLibros(String archivo, List<Libro> inventario) {
        StringBuilder contenido = new StringBuilder();
        contenido.append("titulo|autor|genero|precio|stock\n");

        inventario.forEach(libro -> contenido.append(libro.toCSV()).append("\n"));

        try {
            Files.write(Paths.get(archivo), contenido.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarVentas(String archivo, List<Venta> ventas) {
        StringBuilder contenido = new StringBuilder();
        contenido.append("libro|cantidad|fecha\n");

        ventas.forEach(venta -> contenido.append(venta.toCSV()).append("\n"));

        try {
            Files.write(Paths.get(archivo), contenido.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
