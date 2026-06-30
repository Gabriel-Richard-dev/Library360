package library360.frontms.service;

import library360.frontms.dtos.AutorDto;
import library360.frontms.dtos.LivroDto;
import library360.frontms.dtos.LivroViewDto;
import library360.frontms.dtos.PageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CatalogoService {

    private static final ParameterizedTypeReference<PageResponse<LivroDto>> LIVRO_PAGE_TYPE =
            new ParameterizedTypeReference<>() {};
    private static final ParameterizedTypeReference<PageResponse<AutorDto>> AUTOR_PAGE_TYPE =
            new ParameterizedTypeReference<>() {};

    private final RestClient livroClient;
    private final RestClient autorClient;

    public CatalogoService(
            RestClient.Builder restClientBuilder,
            @Value("${services.livro.url}") String livroBaseUrl,
            @Value("${services.autor.url}") String autorBaseUrl) {
        this.livroClient = restClientBuilder.clone().baseUrl(livroBaseUrl).build();
        this.autorClient = restClientBuilder.clone().baseUrl(autorBaseUrl).build();
    }

    public PageResponse<LivroDto> buscarLivros(
            String titulo,
            String genero,
            Integer anoPublicacao,
            Long autorId,
            int page,
            int size) {
        UriComponentsBuilder uri = UriComponentsBuilder
                .fromPath("/livro/search")
                .queryParam("page", Math.max(page, 0))
                .queryParam("size", size)
                .queryParam("titulo", normalizarTexto(titulo))
                .queryParam("genero", normalizarTexto(genero));

        adicionarSeNaoNulo(uri, "anoPublicacao", anoPublicacao);
        adicionarSeNaoNulo(uri, "autorId", autorId);

        PageResponse<LivroDto> response = livroClient.get()
                .uri(uri.build().encode().toUriString())
                .retrieve()
                .body(LIVRO_PAGE_TYPE);

        return response == null ? new PageResponse<>() : response;
    }

    public List<AutorDto> buscarAutores() {
        String uri = UriComponentsBuilder
                .fromPath("/autor/search")
                .queryParam("page", 0)
                .queryParam("size", 500)
                .queryParam("nome", "")
                .queryParam("nacionalidade", "")
                .build()
                .encode()
                .toUriString();

        PageResponse<AutorDto> response = autorClient.get()
                .uri(uri)
                .retrieve()
                .body(AUTOR_PAGE_TYPE);

        return response == null ? List.of() : response.getContent();
    }

    public void cadastrarAutor(AutorDto autor) {
        autorClient.post()
                .uri("/autor")
                .body(autor)
                .retrieve()
                .toBodilessEntity();
    }

    public void cadastrarLivro(LivroDto livro) {
        livroClient.post()
                .uri("/livro")
                .body(livro)
                .retrieve()
                .toBodilessEntity();
    }

    public void editarAutor(AutorDto autor) {
        cadastrarAutor(autor);
    }

    public void editarLivro(LivroDto livro) {
        cadastrarLivro(livro);
    }

    public void excluirAutor(Long id) {
        autorClient.delete()
                .uri("/autor/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

    public void excluirLivro(Long id) {
        livroClient.delete()
                .uri("/livro/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

    public boolean autorPossuiLivros(Long autorId) {
        return buscarLivros(null, null, null, autorId, 0, 1).getTotalElements() > 0;
    }

    public List<LivroViewDto> combinarLivrosComAutores(
            List<LivroDto> livros,
            List<AutorDto> autores) {
        Map<Long, String> nomesPorId = autores.stream()
                .collect(Collectors.toMap(AutorDto::getId, AutorDto::getNome, (primeiro, ignorado) -> primeiro));

        return livros.stream()
                .map(livro -> new LivroViewDto(
                        livro,
                        nomesPorId.getOrDefault(livro.getAutorId(), "Autor não encontrado")))
                .toList();
    }

    private String normalizarTexto(String valor) {
        return StringUtils.hasText(valor) ? valor.trim() : "";
    }

    private void adicionarSeNaoNulo(UriComponentsBuilder uri, String nome, Object valor) {
        if (valor != null) {
            uri.queryParam(nome, valor);
        }
    }

}
