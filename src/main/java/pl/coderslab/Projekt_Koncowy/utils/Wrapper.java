package pl.coderslab.Projekt_Koncowy.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Wrapper<T> {

    private final T object;
    private final String message;
}
