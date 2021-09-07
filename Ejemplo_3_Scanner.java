import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Podemos usar una instancia de la clase Scanner para poder leer datos de forma más comoda que con
 * un Reader "pelado". Recordamos que un reader solo nos provee operaciones para leer o un byte o
 * una linea completa (hasta el siguiente \n o \r ).
 *
 * Scanner nos permite encapsular un objeto Reader, un Stream (como System.in) o un String y obtener a
 * partir de ese objeto secuencialmente datos de tipos primitivos o incluso substrings separados sin
 * que necesitemos controlar manualmente como se reconocen esos datos.
 *
 * https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html
 * @author jpinero
 */
public class Ejemplo_3_Scanner {
	static final int CANTNUMEROS = 10;
	static final int MAX_VALOR = 1000000;
	static final String NOMBRE_ARCHIVO = "numeros.txt";

	private static void leerArchivo_1() {
		try {
			BufferedReader buff = new BufferedReader(new FileReader(NOMBRE_ARCHIVO));
			Scanner s = new Scanner(buff);
			for (int i = 0; i < CANTNUMEROS; i++) {
				//System.out.println( Integer.parseInt(buff.readLine()));
				System.out.println(s.nextInt());
			}
			buff.close();
		} catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage() + "\nSignifica que el archivo del " + "que queriamos leer no existe.");
		} catch (IOException ex) {
			System.err.println("Error leyendo o escribiendo en algun archivo.");
		}
	}

	private static void leerArchivo_2() {
		try {
			BufferedReader buff = new BufferedReader(new FileReader("ejemplo_scanner.txt"));
			Scanner s = new Scanner(buff);
			//Si falla la lectura del float, cambiar el '.' por coma, cuestion de configuracion de idiomas.
			s = new Scanner("123141   4243,433     unaPalabra\no muchas palabras seguidas.");

			System.out.println("Scanner nos da herramientas comodas para leer datos primitivos de distinto tipo");
			// En 53 cambié el último s.next() por s.nextLine() para que la linea 54 imprima todo el texto restante,
			System.out.println(s.nextInt() + " " + s.nextFloat() + " " + s.nextLine() + " ");
			System.out.println(s.nextLine());
			buff.close();
		} catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage() + "\nSignifica que el archivo del " + "que queriamos leer no existe.");
		} catch (IOException ex) {
			System.err.println("Error leyendo o escribiendo en algun archivo.");
		}
	}

	public static void main(String[] args) {
		//leerArchivo_1();
		//leerArchivo_2();
		ejercicio4(50);	// (topeSuperiorNumeroMisterioso)

		// Inicio ejercicio9.
		System.out.println("----------Inicio resolución ejercicio 9----------");
		ArrayList<Integer> listaA = generarListaConNumerosAleatorios(100, 100); // (longitud, topeNumeroAleatorio)
		System.out.println("Longitud listaA: " + listaA.size());
		System.out.println("Lista A desordenada: " + listaA.toString());
		ArrayList<Integer> listaB = generarListaConNumerosAleatorios(60, 100); // (longitud, topeNumeroAleatorio)
		System.out.println("Longitud listaB: " + listaB.size());
		System.out.println("Lista B desordenada: " + listaB.toString());
		long tiempoInicio = System.nanoTime();
		ArrayList<Integer> listaC = ejercicio9(listaA, listaB);
		long tiempoFinal = System.nanoTime();
		// desactivar el ejercicio 4 si se va a tomar el tiempo de ejecución aquí.
		//System.out.println("Tiempo tomado para resolver el ejercicio 9: "+ (tiempoFinal - tiempoInicio));	
		System.out.println("Longitud listaC: " + listaC.size());
		System.out.println("Lista C: " + listaC.toString());
	}

	public static void ejercicio4(int topeSuperiorNumeroMisterioso) {
		int numeroMisterioso = (int) (Math.random() * topeSuperiorNumeroMisterioso); // Se genera el número misterioso de manera aleatoria.
		//System.out.println(numeroMisterioso); // Debug.
		int numeroUsuario; // El número elegido por el usuario.
		boolean exito = false; // Cortar el while si el usuario adivina el número misterioso.
		Scanner sc = new Scanner(System.in);
		System.out.println("Adivine el número misterioso y llevese un increible premio!");
		while (!exito) {
			System.out.println("Ingrese un número: ");
			numeroUsuario = sc.nextInt();
			if (numeroUsuario == numeroMisterioso) { // El usuario acertó.
				System.out.println("Felicidades, adivinó el número y se ganó un chalet en Marbella");
				exito = true;
			} else {
				if (numeroMisterioso > numeroUsuario) { // El número misterioso es mayor al que ingresó el usuario.
					System.out.println("Incorrecto, el número misterioso es mayor al ingresado.");
				} else { // El número misterioso es menor al que ingresó el usuario.
					System.out.println("Incorrecto, el número misterioso es menor al ingresado");
				}
			}
		}
	}

	public static ArrayList<Integer> ejercicio9(ArrayList<Integer> listaA, ArrayList<Integer> listaB) {
		ArrayList<Integer> listaC = new ArrayList<Integer>(listaA.size()+listaB.size());
		int indiceA = 0, indiceB = 0;
		// ordenando las listas por dos métodos diferentes.
		listaA.sort((o1, o2) -> (o1).compareTo(o2));
		quicksort(listaB, 0, listaB.size() - 1);

		System.out.println("Lista A ordenada: " + listaA.toString());
		System.out.println("Lista B ordenada: " + listaB.toString());
		while (indiceA < listaA.size() && indiceB < listaB.size()) {
			if (listaA.get(indiceA) <= listaB.get(indiceB)) {
				listaC.add(listaA.get(indiceA));
				indiceA++;
			} else {
				listaC.add(listaB.get(indiceB));
				indiceB++;
			}
		}
		if (indiceA < listaA.size()) { // Aún quedan elementos restantes en listaA.
			while (indiceA < listaA.size()) {
				listaC.add(listaA.get(indiceA));
				indiceA++;
			}
		} else {
			if (indiceB < listaB.size()) { // Aún quedan elementos restantes en listaB
				while (indiceB < listaB.size()) {
					listaC.add(listaB.get(indiceB));
					indiceB++;
				}
			}
		}
		return listaC;
	}

	public static void quicksort(ArrayList<Integer> lista, int izq, int der) {

		int pivote = lista.get(izq); // tomamos primer elemento como pivote
		int i = izq; // i realiza la búsqueda de izquierda a derecha
		int j = der; // j realiza la búsqueda de derecha a izquierda
		int aux;

		while (i < j) { // mientras no se crucen las búsquedas                                   
			while (lista.get(i) <= pivote && i < j)
				i++; // busca elemento mayor que pivote
			while (lista.get(j) > pivote)
				j--; // busca elemento menor que pivote
			if (i < j) { // si no se han cruzado                      
				aux = lista.get(i); // los intercambia
				lista.add(i, lista.get(j));
				// tengo que remover el item porque el add de arriba no sobreescribe (como lo haría un arreglo), 
				//   si no que desplaza el elemento i a la derecha.
				lista.remove(i + 1);
				lista.add(j, aux);
				lista.remove(j + 1);
			}
		}
		lista.add(izq, lista.get(j));
		lista.remove(izq + 1);
		lista.add(j, pivote);
		lista.remove(j + 1);

		if (izq < j - 1)
			quicksort(lista, izq, j - 1); // ordenamos subarray izquierdo
		if (j + 1 < der)
			quicksort(lista, j + 1, der); // ordenamos subarray derecho

	}

	public static ArrayList<Integer> generarListaConNumerosAleatorios(int longitud, int topeNumeroAleatorio) {
		ArrayList<Integer> lista = new ArrayList<Integer>(longitud);
		// Genero una lista con número aleatorios.
		for (int i = 0; i < longitud; i++) {
			lista.add((int) (Math.random() * topeNumeroAleatorio));
		}
		return lista;
	}

}