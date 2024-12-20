package Biblioteca;

public class Main {
    public static void main(String[] args) {
        DAOUsuario dao = new DAOUsuario();
        DAOLibro daoLibro = new DAOLibro();
        DAOEjemplar daoEjemplar = new DAOEjemplar();
        Libro libro = daoLibro.buscarPorIsbn("9781234567890");

        System.out.println(daoEjemplar.calcularStock(libro.getEjemplares()));
    }
}
