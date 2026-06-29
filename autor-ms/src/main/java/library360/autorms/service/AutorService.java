package library360.autorms.service;

import jakarta.transaction.Transactional;
import library360.autorms.dto.AutorFilterDto;
import library360.autorms.model.Autor;
import library360.autorms.model.BaseEntity;
import library360.autorms.repository.AutorRepository;
import library360.autorms.repository.BaseRepository;
import library360.autorms.vo.AutorPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AutorService extends BaseService<Autor, Long> {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository repository) {
        super(repository);
        autorRepository = repository;
    }

    public Page<Autor> getPaged(AutorFilterDto request, Pageable pageable){
        return autorRepository.search(request.getNome(),
                request.getNacionalidade(),
                pageable);

    }

}
