package library360.frontms.controller;

import library360.frontms.dtos.AutorDto;
import library360.frontms.dtos.LivroDto;
import library360.frontms.dtos.PageResponse;
import library360.frontms.service.CatalogoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Controller
public class HomeController {

    private static final int PAGE_SIZE = 10;

    private final CatalogoService catalogoService;

    public HomeController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    @GetMapping("/")
    public String home(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) Integer anoPublicacao,
            @RequestParam(required = false) Long autorId,
            @RequestParam(required = false) String cadastro,
            @RequestParam(required = false) String erroCadastro,
            @RequestParam(defaultValue = "0") int page,
            Model model) {
        adicionarFiltrosAoModel(model, titulo, genero, anoPublicacao, autorId);
        adicionarResultadoCadastroAoModel(model, cadastro, erroCadastro);

        try {
            List<AutorDto> autores = catalogoService.buscarAutores();
            PageResponse<LivroDto> livros = catalogoService.buscarLivros(
                    titulo, genero, anoPublicacao, autorId, page, PAGE_SIZE);

            model.addAttribute("autores", autores);
            model.addAttribute("livros", catalogoService.combinarLivrosComAutores(
                    livros.getContent(), autores));
            model.addAttribute("pagina", livros);
        } catch (RestClientException exception) {
            model.addAttribute("autores", List.of());
            model.addAttribute("livros", List.of());
            model.addAttribute("pagina", new PageResponse<>());
            model.addAttribute(
                    "erro",
                    "Não foi possivel encntrar, tente novamente mais tarde.");
        }

        return "home";
    }

    @PostMapping("/autores")
    public String cadastrarAutor(
            @RequestParam String nome,
            @RequestParam String nacionalidade,
            @RequestParam Integer anoNascimento) {
        AutorDto autor = new AutorDto();
        autor.setNome(nome.trim());
        autor.setNacionalidade(nacionalidade.trim());
        autor.setAnoNascimento(anoNascimento);

        try {
            catalogoService.cadastrarAutor(autor);
            return "redirect:/?cadastro=autor";
        } catch (RestClientException exception) {
            return "redirect:/?erroCadastro=autor";
        }
    }

    @PostMapping("/livros")
    public String cadastrarLivro(
            @RequestParam String titulo,
            @RequestParam String genero,
            @RequestParam Integer anoPublicacao,
            @RequestParam Long autorId,
            @RequestParam(defaultValue = "false") Boolean disponivel) {
        LivroDto livro = new LivroDto();
        livro.setTitulo(titulo.trim());
        livro.setGenero(genero.trim());
        livro.setAnoPublicacao(anoPublicacao);
        livro.setAutorId(autorId);
        livro.setDisponivel(disponivel);

        try {
            catalogoService.cadastrarLivro(livro);
            return "redirect:/?cadastro=livro";
        } catch (RestClientException exception) {
            return "redirect:/?erroCadastro=livro";
        }
    }

    @PostMapping("/autores/editar")
    public String editarAutor(
            @RequestParam Long id,
            @RequestParam String nome,
            @RequestParam String nacionalidade,
            @RequestParam Integer anoNascimento) {
        AutorDto autor = new AutorDto();
        autor.setId(id);
        autor.setNome(nome.trim());
        autor.setNacionalidade(nacionalidade.trim());
        autor.setAnoNascimento(anoNascimento);

        try {
            catalogoService.editarAutor(autor);
            return "redirect:/?cadastro=autor-editado";
        } catch (RestClientException exception) {
            return "redirect:/?erroCadastro=autor-edicao";
        }
    }

    @PostMapping("/livros/editar")
    public String editarLivro(
            @RequestParam Long id,
            @RequestParam String titulo,
            @RequestParam String genero,
            @RequestParam Integer anoPublicacao,
            @RequestParam Long autorId,
            @RequestParam(defaultValue = "false") Boolean disponivel) {
        LivroDto livro = new LivroDto();
        livro.setId(id);
        livro.setTitulo(titulo.trim());
        livro.setGenero(genero.trim());
        livro.setAnoPublicacao(anoPublicacao);
        livro.setAutorId(autorId);
        livro.setDisponivel(disponivel);

        try {
            catalogoService.editarLivro(livro);
            return "redirect:/?cadastro=livro-editado";
        } catch (RestClientException exception) {
            return "redirect:/?erroCadastro=livro-edicao";
        }
    }

    @PostMapping("/autores/{id}/excluir")
    public String excluirAutor(@org.springframework.web.bind.annotation.PathVariable Long id) {
        try {
            if (catalogoService.autorPossuiLivros(id)) {
                return "redirect:/?erroCadastro=autor-em-uso";
            }
            catalogoService.excluirAutor(id);
            return "redirect:/?cadastro=autor-excluido";
        } catch (RestClientException exception) {
            return "redirect:/?erroCadastro=autor-exclusao";
        }
    }

    @PostMapping("/livros/{id}/excluir")
    public String excluirLivro(@org.springframework.web.bind.annotation.PathVariable Long id) {
        try {
            catalogoService.excluirLivro(id);
            return "redirect:/?cadastro=livro-excluido";
        } catch (RestClientException exception) {
            return "redirect:/?erroCadastro=livro-exclusao";
        }
    }

    private void adicionarResultadoCadastroAoModel(
            Model model,
            String cadastro,
            String erroCadastro) {
        if ("autor".equals(cadastro)) {
            model.addAttribute("sucesso", "Autor cadastrado com sucesso.");
        } else if ("livro".equals(cadastro)) {
            model.addAttribute("sucesso", "Livro cadastrado com sucesso.");
        } else if ("autor-editado".equals(cadastro)) {
            model.addAttribute("sucesso", "Autor atualizado com sucesso.");
        } else if ("livro-editado".equals(cadastro)) {
            model.addAttribute("sucesso", "Livro atualizado com sucesso.");
        } else if ("autor-excluido".equals(cadastro)) {
            model.addAttribute("sucesso", "Autor excluído com sucesso.");
        } else if ("livro-excluido".equals(cadastro)) {
            model.addAttribute("sucesso", "Livro excluído com sucesso.");
        }

        if ("autor".equals(erroCadastro)) {
            model.addAttribute(
                    "erroCadastro",
                    "Não foi possível cadastrar o autor. Verifique os dados e tente novamente.");
        } else if ("livro".equals(erroCadastro)) {
            model.addAttribute(
                    "erroCadastro",
                    "Não foi possível cadastrar o livro. Verifique os dados e tente novamente.");
        } else if ("autor-edicao".equals(erroCadastro)) {
            model.addAttribute("erroCadastro", "Não foi possível atualizar o autor.");
        } else if ("livro-edicao".equals(erroCadastro)) {
            model.addAttribute("erroCadastro", "Não foi possível atualizar o livro.");
        } else if ("autor-exclusao".equals(erroCadastro)) {
            model.addAttribute("erroCadastro", "Não foi possível excluir o autor.");
        } else if ("livro-exclusao".equals(erroCadastro)) {
            model.addAttribute("erroCadastro", "Não foi possível excluir o livro.");
        } else if ("autor-em-uso".equals(erroCadastro)) {
            model.addAttribute(
                    "erroCadastro",
                    "Este autor possui livros cadastrados. Exclua ou altere esses livros primeiro.");
        }
    }

    private void adicionarFiltrosAoModel(
            Model model,
            String titulo,
            String genero,
            Integer anoPublicacao,
            Long autorId) {
        model.addAttribute("titulo", titulo);
        model.addAttribute("genero", genero);
        model.addAttribute("anoPublicacao", anoPublicacao);
        model.addAttribute("autorId", autorId);
    }
}
