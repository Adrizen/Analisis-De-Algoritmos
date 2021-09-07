package tp0;
public class Alumno implements Comparable {
    private String nombre;
    private double notaBiologia;
    private double notaIngles;
    private double notaLengua;
    private double notaMatematicas;
    private double notaFilosofia;

    public Alumno(String n, double nb, double ni, double nl, double nm, double nf) {
        this.nombre = n;
        this.notaBiologia = nb;
        this.notaIngles = ni;
        this.notaLengua = nl;
        this.notaMatematicas = nm;
        this.notaFilosofia = nf;
    }

    public int compareTo(Object alumno) {
        double promedioParametro = ((Alumno) alumno).calcularPromedioNotas();
        double promedio = this.calcularPromedioNotas();
        if (promedio == promedioParametro) {
            return 0;
        } else {
            if (promedio < promedioParametro) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public double calcularPromedioNotas() {
        return (notaBiologia + notaIngles + notaLengua + notaMatematicas + notaFilosofia) / 5;
    }

    public double getNotaIngles() {
        return notaIngles;
    }

    public double getNotaLengua() {
        return notaLengua;
    }

    public double getNotaMatematicas() {
        return notaMatematicas;
    }

    public double getNotaFilosofia() {
        return notaFilosofia;
    }

    public String getNombre() {
        return nombre;
    }

    public double getNotaBiologia() {
        return notaBiologia;
    }

}
