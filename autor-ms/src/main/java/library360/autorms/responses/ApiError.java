package library360.autorms.responses;

public record ApiError(
        Integer status,
        String message
) {
}
