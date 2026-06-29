package library360.autorms.service;

import jakarta.transaction.Transactional;
import library360.autorms.exceptions.NotFoundException;
import library360.autorms.model.BaseEntity;
import library360.autorms.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseService<entity extends BaseEntity<T>, T extends Number> {
    private final BaseRepository<entity, T> repository;

    public BaseService(BaseRepository<entity, T> repository){
        this.repository = repository;
    }

    @Transactional
    public entity saveOrUpdate(entity e) {
        return repository.saveAndFlush(e);
    }

    public entity findById(T id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Not found autor with this id: " + id));
    }

    @Transactional
    public entity deleteById(T id) {
        entity E = findById(id);
        repository.delete(E);
        return E;
    }
}
