package library360.autorms.service;

import jakarta.transaction.Transactional;
import library360.autorms.model.BaseEntity;
import library360.autorms.repository.BaseRepository;

public class BaseService<entity extends BaseEntity, T extends Number> {
    private BaseRepository<entity, T> repository;

    @Transactional
    public entity saveOrUpdate(entity e) {
        return repository.saveAndFlush(e);
    }

}
