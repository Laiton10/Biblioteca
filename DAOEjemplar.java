package Biblioteca;
import java.util.List;

public class DAOEjemplar extends DAOGenerico<Ejemplar>{
    public DAOEjemplar() {
        super(Ejemplar.class);
    }

    public int calcularStock(List<Ejemplar>stock){
        int contador = 0;
        for(Ejemplar e : stock){
            if(e.getEstado().equals("Disponible")){
                contador++;
            }
        }
        return contador;
    }

    public void actualizarEjemplar(Ejemplar ejemplar){
        ejemplar.setEstado("Prestado");
        update(ejemplar);
    }
}
