    package com.quetzify.proyectoQuetzify.exception;

    import com.kennethcaneda.tienda.exception.ResourceNotFoundException;
    import jakarta.validation.ConstraintViolationException;
    import org.springframework.dao.DataIntegrityViolationException;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.http.converter.HttpMessageNotReadableException;
    import org.springframework.web.bind.MethodArgumentNotValidException;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.RestControllerAdvice;
    import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
    import org.springframework.web.servlet.NoHandlerFoundException;

    import java.util.List;
    import java.util.Map;

    @RestControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<?> handleValidation(ResourceNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)

                    .body(Map.of("message", ex.getMessage()));
        }

        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<?> handleValidation(ConstraintViolationException ex) {
            String msg = ex.getConstraintViolations()
                    .iterator()
                    .next()
                    .getMessage();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", msg));
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<?> handleBadJson(HttpMessageNotReadableException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "JSON inválido o tipo de dato incorrecto."));
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<?> handleBodyValidation(MethodArgumentNotValidException ex) {

            List<String> mensajes = ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(err -> err.getDefaultMessage())
                    .toList();

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("errors", mensajes));
        }

        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<?> handleValidation(DataIntegrityViolationException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "La llave foránea no existe."));
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<?> handleValidation(IllegalArgumentException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", ex.getMessage()));
        }

        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<?> handleValidation(MethodArgumentTypeMismatchException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "El tipo de dato en la ruta es inválido."));
        }

        @ExceptionHandler(NoHandlerFoundException.class)
        public ResponseEntity<?> handleNotFound(NoHandlerFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "error", "URL no encontrada.",
                            "message", "No existe la ruta: " + ex.getHttpMethod() + " " + ex.getRequestURL()
                    ));
        }
    }