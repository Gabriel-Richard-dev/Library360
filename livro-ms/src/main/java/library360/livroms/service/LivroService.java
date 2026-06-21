package library360.livroms.service;


import library360.livroms.dto.LivroFilterDto;
import library360.livroms.model.Livro;
import library360.livroms.repository.LivroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LivroService extends BaseService<Livro, Long> {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository repository) {
        super(repository);
        livroRepository = repository;
    }

    public Page<Livro> getPaged(LivroFilterDto request, Pageable pageable){
        return livroRepository.search(request.getTitulo(),
                request.getGenero(),
                request.getAnoPublicacao(),
                request.getAutorId(),
                pageable);

    }
}
