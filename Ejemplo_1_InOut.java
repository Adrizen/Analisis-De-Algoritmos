import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Ejemplo_1_InOut {

    public static void main(String[] args) {
        /**
         * Para abrir un archivo, tanto en modo lectura como en modo escritura,
         * necesitamos que el archivo exista en el sistema de archivos (en el lugar
         * desde el que lo estamos tratando de abrir). En caso de que el archivo no
         * exista, o que nuestro manejo del archivo provoque fallas, errores, o
         * cualquier tipo de excepción, debemos manejar esas condiciones.
         *
         * NOTA: Si en vez de ejecutarlo desde un IDE como netbeans o eclipse, lo
         * ejecutan por consola, quiten de la ruta de los archivos el
         * "src/ejemplo_InOut/" porque va a fallar la creacion y apertura de archivos al
         * apuntar a rutas que no existan.
         */
        String nombreArchivoEntrada = "entrada.txt";
        String nombreArchivoSalida = "salida.txt";
        /**
         * Mientras manipulamos archivos de text, será común que operemos con strings
         * que representaran lineas de texto.
         */
        String linea = null;
        try {
            /*
             * FileReader es una clase que como indica el nombre, nos permite leer texto
             * desde un archivo https://docs.oracle.com/javase/7/docs/api/java/io/FileReader.html
             */
            FileReader lectorArchivo = new FileReader(nombreArchivoEntrada);
            FileWriter escritorArchivo = new FileWriter(nombreArchivoSalida);
            /*
             * De la misma manera, nos interesa poder escribir en un archivo, no solo por la
             * salida estandar (consola) https://docs.oracle.com/javase/7/docs/api/java/io/FileWriter.html
             */
            // Ejercicio 1.
            FileWriter escritorArchivoSinEspacios = new FileWriter("sinEspacios.txt");
            // Ejercicio 2.
            FileWriter escritorArchivoLineasImpares = new FileWriter("lineasImpares.txt");
            // Ejercicio 3.
            FileWriter escritorArchivoNumerosAleatorios = new FileWriter("numerosAleatorios.txt");
            // Ejercicio 4.
            FileWriter escritorArchivoCadenasAleatorias = new FileWriter("cadenasAleatorias.txt");
            // Ejercicio 5.
            FileWriter escritorArchivoNumeros1a1000Aleatorios = new FileWriter("numeros1a1000Aleatorios.txt");
            /*
             * Si bien no es obligatorio, se recomienda fuertemente: Usar buffer para la
             * entrada/salida de un archivo de texto Mejorar la performance de las
             * operaciones de lectura y escritura, pero también es más robusto-
             * https://docs.oracle.com/javase/7/docs/api/java/io/BufferedReader.html
             * https://docs.oracle.com/javase/7/docs/api/java/io/BufferedWriter.html
             */
            BufferedReader bufferLectura = new BufferedReader(lectorArchivo);
            BufferedWriter bufferEscritura = new BufferedWriter(escritorArchivo);

            // Ejercicio 1.
            BufferedWriter bufferEscrituraSinEspacios = new BufferedWriter(escritorArchivoSinEspacios);
            // Ejercicio 2.
            BufferedWriter bufferEscrituraLineasImpares = new BufferedWriter(escritorArchivoLineasImpares);
            int numeroLinea = 1; // La primer linea de entrada.txt es la número 1 (impar).
            // Ejercicio 3.
            BufferedWriter bufferEscributraNumerosAleatorios = new BufferedWriter(escritorArchivoNumerosAleatorios);
            // Ejercicio 4.
            BufferedWriter bufferEscrituraCadenasAleatorias = new BufferedWriter(escritorArchivoCadenasAleatorias);
            // Ejercicio 5.
            BufferedWriter bufferEscrituraNumeros1a1000Aleatorios = new BufferedWriter(
                    escritorArchivoNumeros1a1000Aleatorios);
            /*
             * Mientras el buffer de lectura tenga algo por leer desde el archivo,
             * imprimimos cada linea por pantalla y la grabamos tal cual en el archivo.
             */
            while ((linea = bufferLectura.readLine()) != null) {
                System.out.println(linea); // Escribe en la consola.
                bufferEscritura.write(linea + "\n"); // Escribe en el archivo.
                ejercicio1(linea, bufferEscrituraSinEspacios); // lineas sin espacios.
                ejercicio2(linea, bufferEscrituraLineasImpares, numeroLinea); // lineas impares.
                numeroLinea++;
            }
            ejercicio3(bufferEscributraNumerosAleatorios,-100,100);
            ejercicio4(bufferEscrituraCadenasAleatorias, 10, 10);
            ejercicio5(bufferEscrituraNumeros1a1000Aleatorios,1000);
            /*
             * Para evitar que los archivos corran riesgo de quedar corruptos, es
             * conveniente cerrarlos. Cerrando el buffer que envuelve un archivo este se
             * ocupa de cerrar tambien su correspondiente archivo.
             */
            bufferLectura.close();
            bufferEscritura.close();
            bufferEscrituraSinEspacios.close();
            bufferEscrituraLineasImpares.close();
            bufferEscributraNumerosAleatorios.close();
            bufferEscrituraCadenasAleatorias.close();
            bufferEscrituraNumeros1a1000Aleatorios.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + "\nSignifica que el archivo del " + "que queriamos leer no existe.");
        } catch (IOException ex) {
            System.err.println("Error leyendo o escribiendo en algun archivo.");
        }
    }

    // Método correspondiente al Ejercicio 1.
    public static void ejercicio1(String linea, BufferedWriter bufferEscrituraSinEspacios) throws IOException {
        // Toma el texto de entrada.txt y lo coloca en sinEspacios.txt sin ningun espacio entre lineas.
        String lineaSinEspacios;
        lineaSinEspacios = linea.replaceAll("\\s", ""); // \s stands for "whitespace character".
        bufferEscrituraSinEspacios.write(lineaSinEspacios + "\n");
    }

    // Método correspondiente al Ejercicio 2.
    public static void ejercicio2(String linea, BufferedWriter bufferEscrituraLineasImpares, int numeroLinea)
            throws IOException {
        // Toma el texto de entrada.txt y coloca las lineas impares en lineasImpares.txt
        if (numeroLinea % 2 == 1) {
            bufferEscrituraLineasImpares.write(linea + "\n");
        }
    }

    // Método correspondiente al Ejercicio 3.
    public static void ejercicio3(BufferedWriter bufferEscributraNumerosAleatorios, int topeMinimo, int topeMaximo) throws IOException {
        Random r = new Random();
        DecimalFormat formatoDecimal = new DecimalFormat("#.##"); // Un máximo de 2 decimales para cada double.
        for (int i = 0; i < 100; i++) {
            double valorAleatorio = topeMinimo + (topeMaximo - (topeMinimo)) * r.nextDouble(); // Valor aleatorio entre -100 y 100.
            bufferEscributraNumerosAleatorios.write(formatoDecimal.format(valorAleatorio) + "\n"); // Se escribe en el txt.

        }
    }

    // Método correspondiente al Ejercicio 4.
    public static void ejercicio4(BufferedWriter bufferEscrituraCadenasAleatorias, int tamañoCadena, int cantidadCadenasAleatorias) throws IOException {
        String cadenaAlfanumerica = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        for (int i = 0; i < cantidadCadenasAleatorias; i++) {
            // StringBuilder permite construir un String utilizando varios métodos dinámicos.
            StringBuilder sb = new StringBuilder(tamañoCadena);
            for (int j = 0; j < tamañoCadena; j++) {
                // Elijo un char aleatorio de cadenaAlfanumerica.
                int indice = (int) (cadenaAlfanumerica.length() * Math.random());
                // Concateno el char anterior al StringBuilder.
                sb.append(cadenaAlfanumerica.charAt(indice));
            }
            bufferEscrituraCadenasAleatorias.write(sb.toString() + "\n");
        }
    }

    // Método correspondiente al Ejercicio 5.
    public static void ejercicio5(BufferedWriter bufferEscrituraNumeros1a1000Aleatorios, int tamañoLista) throws IOException{
        ArrayList<Integer> lista = new ArrayList<Integer>(1000);
        // Genero una lista con los números del 1 al 1000.
        for (int i = 0; i < tamañoLista; i++) {
            lista.add(i);
        }
        // Por cada elemento de la lista, elijo uno al azar y lo escribo en el txt. Luego lo elimino de la lista para no repetirlo.
        for (int i = 0; i < tamañoLista; i++) {
            int indice = (int) (lista.size() * Math.random());
            bufferEscrituraNumeros1a1000Aleatorios.write(lista.get(indice) + "\n");
            lista.remove(indice);
        }

    }

}