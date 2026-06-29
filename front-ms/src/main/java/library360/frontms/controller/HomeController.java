package library360.frontms.controller;

import library360.frontms.dtos.LivroDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model){
        RestClient restClient = RestClient.create();

        LivroDto livro = restClient
                .get()
                .uri("http://localhost:80/api/livro/livro/1")
                .retrieve()
                .body(LivroDto.class);

        System.out.println(livro.getTitulo());
        model.addAttribute("titulo", livro.getTitulo());
        return "home.html";
    }

}
