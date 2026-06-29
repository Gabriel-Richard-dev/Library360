package library360.autorms.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import library360.autorms.dto.AutorFilterDto;
import library360.autorms.model.Autor;
import library360.autorms.service.AutorService;
import library360.autorms.vo.AutorPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestScope
@RequestMapping(value = "autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    public ResponseEntity<Autor> saveOrUpdate(@Valid @RequestBody Autor autor) {
        return ok().body(autorService.saveOrUpdate(autor));
    }

    @GetMapping("{id}")
    public ResponseEntity<Autor> findById(@PathVariable("id") Long id) {
        return ok().body(autorService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Autor> deleteById(@PathVariable("id") Long id) {
        return ok().body(autorService.deleteById(id));
    }

    @GetMapping("search")
    public ResponseEntity<Page<Autor>> search(@ModelAttribute AutorFilterDto request,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size){
        return ok().body(autorService.getPaged(request, PageRequest.of(page, size)));
    }
}