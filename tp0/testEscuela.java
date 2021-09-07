package tp0;
import java.util.ArrayList;

public class testEscuela {
    public static void main(String[] args) {
        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
        Alumno alumno1 = new Alumno("Juancito", 7, 9.50, 5, 7, 8);
        alumnos.add(alumno1);
        Alumno alumno2 = new Alumno("Carolina", 5, 2, 7, 6.50, 8);
        alumnos.add(alumno2);
        Alumno alumno3 = new Alumno("Richard", 10, 10, 10, 10, 10);
        alumnos.add(alumno3);
        Alumno alumno4 = new Alumno("Ximena", 6, 7.50, 8.50, 8.50, 9);
        alumnos.add(alumno4);
        Alumno alumno5 = new Alumno("Matias", 4, 10, 5, 7, 8);
        alumnos.add(alumno5);
        Escuela escuela = new Escuela(alumnos);
        System.out.println("Listado antes de ordenar por promedio: \n" + escuela.listadoAlumnos());
        escuela.promedios();

        // Elegir alguno de los dos métodos de ordenamiento a continuación.
        //escuela.ordenarPorPromedio();             // Ordena de mayor a menor.
        quicksort(alumnos, 0, alumnos.size()-1);    // Ordena de menor a mayor.

        System.out.println("Listado después de ordenar por promedio: \n" + escuela.listadoAlumnos());

    }

    // quicksort de https://puntocomnoesunlenguaje.blogspot.com/2012/12/java-quicksort.html
    // Adaptado para usar ArrayList.
    public static void quicksort(ArrayList<Alumno> alumnos, int izq, int der) {

        Alumno pivote = alumnos.get(izq); // tomamos primer elemento como pivote
        int i = izq; // i realiza la búsqueda de izquierda a derecha
        int j = der; // j realiza la búsqueda de derecha a izquierda
        Alumno aux;

        while (i < j) { // mientras no se crucen las búsquedas                                   
            while (alumnos.get(i).calcularPromedioNotas() <= pivote.calcularPromedioNotas() && i < j)
                i++; // busca elemento mayor que pivote
            while (alumnos.get(j).calcularPromedioNotas() > pivote.calcularPromedioNotas())
                j--; // busca elemento menor que pivote
            if (i < j) { // si no se han cruzado                      
                aux = alumnos.get(i); // los intercambia
                alumnos.add(i, alumnos.get(j));
                // tengo que remover el item porque el add de arriba no sobreescribe (como lo haría un arreglo), 
                //   si no que desplaza el elemento i a la derecha.
                alumnos.remove(i+1);    
                alumnos.add(j,aux);
                alumnos.remove(j+1);
            }
        }
        alumnos.add(izq, alumnos.get(j));
        alumnos.remove(izq+1);
        alumnos.add(j, pivote);
        alumnos.remove(j+1);

        if (izq < j - 1)
            quicksort(alumnos, izq, j - 1); // ordenamos subarray izquierdo
        if (j + 1 < der)
            quicksort(alumnos, j + 1, der); // ordenamos subarray derecho

    }

}
