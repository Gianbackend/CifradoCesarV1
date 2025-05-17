import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n*** MENÚ CIFRADO CÉSAR ***");
            System.out.println("1. Cifrar texto");
            System.out.println("2. Descifrar texto");
            System.out.println("3. Tomar datos de archivo .txt");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el texto a cifrar: ");
                    String textoCifrar = scanner.nextLine();
                    System.out.print("Ingrese el desplazamiento: ");
                    int shiftCifrar = scanner.nextInt();
                    scanner.nextLine(); // limpiar buffer
                    String cifrado = CifradoCesar1.encrypt(textoCifrar, shiftCifrar);
                    System.out.println("Texto cifrado: " + cifrado);
                    break;

                case 2:
                    System.out.print("Ingrese el texto a descifrar: ");
                    String textoDescifrar = scanner.nextLine();
                    System.out.print("Ingrese el desplazamiento: ");
                    int shiftDescifrar = scanner.nextInt();
                    scanner.nextLine(); // limpiar buffer
                    String descifrado = CifradoCesar1.decrypt(textoDescifrar, shiftDescifrar);
                    System.out.println("Texto descifrado: " + descifrado);
                    break;

                case 3:
                    System.out.print("Ingrese la ruta del archivo .txt: ");
                    String rutaArchivo = scanner.nextLine();

                    System.out.println("¿Qué desea hacer con el contenido?");
                    System.out.println("1. Cifrar");
                    System.out.println("2. Descifrar");
                    int eleccionBuffered = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Ingrese el desplazamiento: ");
                    int shiftBuffered = scanner.nextInt();
                    scanner.nextLine();

                    try (BufferedReader br = new BufferedReader(new java.io.FileReader(rutaArchivo))) {
                        String linea;
                        StringBuilder resultado = new StringBuilder();

                        while ((linea = br.readLine()) != null) {
                            String procesado = (eleccionBuffered == 1)
                                    ? CifradoCesar1.encrypt(linea, shiftBuffered)
                                    : CifradoCesar1.decrypt(linea, shiftBuffered);
                            resultado.append(procesado).append(System.lineSeparator());
                        }

                        String nombreArchivo = generarNombreArchivoSalida();
                        try (java.io.FileWriter writer = new java.io.FileWriter(nombreArchivo)) {
                            writer.write(resultado.toString());
                        }

                        System.out.println("Resultado guardado en: " + nombreArchivo);

                    } catch (IOException e) {
                        System.out.println("Error al procesar el archivo: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 4);

        scanner.close();


        }
    private static String generarNombreArchivoSalida() {
        int contador = 0;
        String base = "salida";
        String extension = ".txt";
        File archivo;

        do {
            String nombre = (contador == 0) ? base + extension : base + (contador + 1) + extension;
            archivo = new File(nombre);
            contador++;
        } while (archivo.exists());

        return archivo.getName();
    }
}