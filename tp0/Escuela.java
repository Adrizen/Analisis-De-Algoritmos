package tp0;
import java.util.ArrayList;

public class Escuela {
    private ArrayList<Alumno> alumnos = new ArrayList<Alumno>();

    public Escuela(ArrayList<Alumno> a) {
        this.alumnos = a;
    }

    public String listadoAlumnos() {
        String listado = "";
        for (int i = 0; i < alumnos.size(); i++) {
            Alumno alumno = (Alumno) (alumnos.get(i));
            listado = listado + " " + alumno.getNombre();
        }
        return listado;
    }

    public void promedios() {
        double sumaTotalClase = 0, sumaBiologia = 0, sumaIngles = 0, sumaFilosofia = 0, sumaMatematicas = 0, sumaLengua = 0;
        int cantidadAlumnos = alumnos.size();
        for (int i = 0; i < cantidadAlumnos; i++) {
            Alumno alumno = (Alumno) alumnos.get(i);
            sumaBiologia = sumaBiologia + alumno.getNotaBiologia();
            sumaIngles = sumaIngles + alumno.getNotaIngles();
            sumaFilosofia = sumaFilosofia + alumno.getNotaFilosofia();
            sumaMatematicas = sumaMatematicas + alumno.getNotaMatematicas();
            sumaLengua = sumaLengua + alumno.getNotaLengua();
            double promedioAlumno = alumno.calcularPromedioNotas();
            System.out.println("Media de " + alumno.getNombre() + " es: " + promedioAlumno);
            sumaTotalClase = sumaTotalClase + promedioAlumno;
        }
        System.out.println("La media de Biología es: " + sumaBiologia / cantidadAlumnos);
        System.out.println("La media de Inglés es: " + sumaIngles / cantidadAlumnos);
        System.out.println("La media de Filosofía es: " + sumaFilosofia / cantidadAlumnos);
        System.out.println("La media de Matemáticas es: " + sumaMatematicas / cantidadAlumnos);
        System.out.println("La media de Lengua es: " + sumaLengua / cantidadAlumnos);
        System.out.println("La media de la clase es: " + sumaTotalClase / cantidadAlumnos);
    }

    public void ordenarPorPromedio() {
        alumnos.sort((o1, o2) -> ((Alumno) o1).compareTo(o2));
    }


}