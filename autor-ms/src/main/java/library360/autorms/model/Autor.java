package library360.autorms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Audited;

@Table(name= "autor")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Autor extends BaseEntity<Long> {
    @Column(name="nome", length = 100, nullable = false)
    private String nome;

    @Column(name="nacionalidade", length = 80, nullable = false)
    private String nacionalidade;

    @Positive
    @Column(name="anonascimento", nullable = false)
    private int anoNascimento;
}
