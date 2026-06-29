package library360.frontms.dtos;

public class LivroDto {
    private String titulo;

    public LivroDto(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
