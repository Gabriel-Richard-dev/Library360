package library360.livroms.responses;

public record ApiError(
        Integer status,
        String message
) {
}
