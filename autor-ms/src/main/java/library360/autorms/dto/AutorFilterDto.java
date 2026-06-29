package library360.autorms.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AutorFilterDto {
    private String nome;
    private String nacionalidade;
    private Integer anoNascimento;
}
