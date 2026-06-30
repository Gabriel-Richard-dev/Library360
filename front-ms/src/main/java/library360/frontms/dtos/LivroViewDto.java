package library360.frontms.dtos;

public class LivroViewDto {

    private final LivroDto livro;
    private final String autorNome;

    public LivroViewDto(LivroDto livro, String autorNome) {
        this.livro = livro;
        this.autorNome = autorNome;
    }

    public LivroDto getLivro() {
        return livro;
    }

    public String getAutorNome() {
        return autorNome;
    }
}
