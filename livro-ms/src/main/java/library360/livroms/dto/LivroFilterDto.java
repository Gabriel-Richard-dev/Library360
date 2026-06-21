package library360.livroms.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LivroFilterDto {
    private String titulo;
    private String genero;
    private Boolean disponivel;
    private Integer anoPublicacao;
    private Long autorId;
}
