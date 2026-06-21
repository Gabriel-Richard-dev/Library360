package library360.livroms.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import library360.livroms.dto.LivroFilterDto;
import library360.livroms.model.Livro;
import library360.livroms.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestScope
@RequestMapping(value = "livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Livro> saveOrUpdate(@Valid @RequestBody Livro livro) {
        return ok().body(livroService.saveOrUpdate(livro));
    }

    @GetMapping("{id}")
    public ResponseEntity<Livro> findById(@PathVariable("id") Long id) {
        return ok().body(livroService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Livro> deleteById(@PathVariable("id") Long id) {
        return ok().body(livroService.deleteById(id));
    }

    @GetMapping("search")
    public ResponseEntity<Page<Livro>> search(@ModelAttribute LivroFilterDto request,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size){
        return ok().body(livroService.getPaged(request, PageRequest.of(page, size)));
    }
}