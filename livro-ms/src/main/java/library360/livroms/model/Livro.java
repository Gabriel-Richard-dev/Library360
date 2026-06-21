package library360.livroms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Table(name= "livro")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Livro extends BaseEntity<Long> {
    @Column(name="titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name="genero", length = 60, nullable = false)
    private String genero;

    @Positive
    @Column(name="ano_publicacao", nullable = false)
    private int anoPublicacao;

    @Column(name = "disponivel", nullable = false)
    private Boolean disponivel = Boolean.TRUE;

    @Positive
    @Column(name="autor_id", nullable = false)
    private Long autorId;
}
