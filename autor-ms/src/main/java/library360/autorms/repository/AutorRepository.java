package library360.autorms.repository;

import library360.autorms.model.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends BaseRepository<Autor, Long> {

    @Query("""
        FROM Autor a
            where (:nome is null or lower(a.nome) like lower(concat('%', :nome, '%')))
            and (:nacionalidade is null or lower(a.nacionalidade) like lower(concat('%', :nacionalidade, '%')))
            ORDER BY a.nome asc
    """)
    Page<Autor> search(
            @Param("nome") String nome,
            @Param("nacionalidade") String nacionalidade,
            Pageable pageable
    );
}
