package library360.livroms.service;

import jakarta.transaction.Transactional;
import library360.livroms.exceptions.NotFoundException;
import library360.livroms.repository.BaseRepository;
import library360.livroms.model.BaseEntity;
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
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Not found livro with this id: " + id));
    }

    @Transactional
    public entity deleteById(T id) {
        entity E = findById(id);
        repository.delete(E);
        return E;
    }
}
