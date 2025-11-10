import java.util.ArrayList;

public interface IFiltrable {
    ArrayList<Contenido> filtrarPorTipo(String tipo);
    ArrayList<Contenido> filtrarPorCategoria(Categoria categoria);
}
