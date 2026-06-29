package library360.autorms.repository;

import library360.autorms.model.BaseEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Primary
@Repository
public interface BaseRepository<E extends BaseEntity<T>, T extends Number> extends JpaRepository<E, T> {

}
