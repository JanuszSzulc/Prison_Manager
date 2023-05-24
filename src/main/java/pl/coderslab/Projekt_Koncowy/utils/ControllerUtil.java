package pl.coderslab.Projekt_Koncowy.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

public class ControllerUtil {

    private ControllerUtil(){}

    public static <T> ResponseEntity<Wrapper<T>> handle(Executable<T> executable){
        try{
            return ResponseEntity.ok(new Wrapper<>(executable.execute(), "OK"));
        } catch (IllegalArgumentException | IllegalStateException exception){
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Wrapper<>(null, exception.getMessage()));
        } catch (NoSuchElementException exception){
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Wrapper<>(null, exception.getMessage()));
        } catch (ConstraintViolationException exception){
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new Wrapper<>(null, exception.getMessage()));
        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Wrapper<>(null, exception.getMessage()));
        }
    }
}
