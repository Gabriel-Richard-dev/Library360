package library360.autorms.repository;

import library360.autorms.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity, T extends Number> extends JpaRepository<E, T> {

}
