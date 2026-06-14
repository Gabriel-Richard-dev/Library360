package library360.livroms.repository;

import library360.livroms.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity, T extends Number> extends JpaRepository<E, T> {

}
