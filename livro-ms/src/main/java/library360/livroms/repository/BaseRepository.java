package library360.livroms.repository;

import library360.livroms.model.BaseEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Primary
@Repository
public interface BaseRepository<E extends BaseEntity<T>, T extends Number> extends JpaRepository<E, T> {

}
