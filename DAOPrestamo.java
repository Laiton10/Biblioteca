package Biblioteca;

import java.time.LocalDate;

public class DAOPrestamo extends DAOGenerico<Prestamo> {
    public DAOPrestamo() {
        super(Prestamo.class);
    }

    public LocalDate registrarDevolucion(Prestamo prestamo) {
        Ejemplar ejemplar = prestamo.getEjemplar();
        ejemplar.setEstado("Disponible");
        LocalDate fechaHaSidoDevuelto = LocalDate.now();
        return fechaHaSidoDevuelto;
    }

    public Prestamo realizarPrestamo(Usuario usu, Ejemplar eje, LocalDate fechaInicio) {
        boolean validar = true;
        String mensaje = null;
        if(eje.getEstado().equals("No disponible")) {
            mensaje = "No se puede hacer el préstamo, ejemplar no disponible";
            validar = false;
        }

        if(usu.getPrestamos().size() > 3){
            mensaje = "No se puede hacer el préstamo, ya que el usuario ya tiene 3 préstamos activos";
            validar = false;
        }

        if(usu.getPenalizacionHasta() != null){
            mensaje = "No se puede hacer el préstamo, ya que el usuario tiene una penalización";
            validar = false;
        }

        if(!validar){
            System.out.println(mensaje);
        }else{
            eje.setEstado("Prestado");
            Prestamo prestamo = new Prestamo(eje, fechaInicio, usu);
            return prestamo;
        }
        return null;
    }

    public void establecerPenalizacion(Usuario usu) {
        int dias = 0;
        for(Prestamo p : usu.getPrestamos()){
            LocalDate fechaDevuelto = registrarDevolucion(p);
            if(fechaDevuelto.isAfter(p.getFechaDevolucion())){
                dias+= 15;
            }
        }

        if(dias > 0) {
            usu.setPenalizacionHasta(LocalDate.now().plusDays(dias));
        }
    }
}
