package library360.livroms.repository;

import library360.livroms.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends BaseRepository<Livro, Long> {

    @Query("""
        FROM Livro l
            WHERE (:titulo is null or lower(l.titulo) like (lower(concat('%', :titulo, '%'))))
            AND (:genero is null or lower(l.genero) like (lower(concat('%', :genero, '%'))))
            AND (:anoPublicacao is null or l.anoPublicacao = :anoPublicacao)
            AND (:autorId is null or l.autorId = :autorId)
            ORDER BY l.titulo asc
    """)
    Page<Livro> search(
            @Param("titulo") String titulo,
            @Param("genero") String genero,
            @Param("anoPublicacao") Integer anoPublicacao,
            @Param("autorId") Long autorId,
            Pageable pageable
    );
}
