package library360.autorms.repository;

import library360.autorms.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<E extends BaseEntity, T extends Number> extends JpaRepository<E, T> {

}
