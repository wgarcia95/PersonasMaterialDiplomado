package com.example.android.personasmaterialdiplomado;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void igualescorrecto(){
        Persona p1 = new Persona(0, "123", "Pedro", "Perez", 0);
        Persona p2 = new Persona(0, "123", "Pedro", "Perez", 0);

        assertTrue(Metodos.iguales(p1, p2));

    }

    @Test
    public void igualesIncorrecto(){
        Persona p1 = new Persona(1, "23", "Pedro", "Perez", 0);
        Persona p2 = new Persona(0, "123", "Pedro", "Perez", 0);

        assertFalse(Metodos.iguales(p1, p2));
    }

    @Test
    public void obtenerPersonaCorrecto(){
        ArrayList<Persona> personas = new ArrayList<>();
        Persona p1 = new Persona(1, "123", "Pedro", "Perez", 0);
        Persona p2 = new Persona(1, "1234", "Maria", "Gutierrez", 1);
        Persona p3 = new Persona(1, "12345", "Juan", "Mejia", 0);
        Persona p4 = new Persona(1, "123456", "Rosa", "Mercado", 1);
        personas.add(p1);
        personas.add(p2);
        personas.add(p3);
        personas.add(p4);

        assertEquals(1, Metodos.obtenerPosicionGuardada(personas, p2));

    }

    @Test
    public void obtenerPersonaIncorrecto(){
        ArrayList<Persona> personas = new ArrayList<>();
        Persona p1 = new Persona(1, "123", "Pedro", "Perez", 0);
        Persona p2 = new Persona(1, "1234", "Maria", "Gutierrez", 1);
        Persona p3 = new Persona(1, "12345", "Juan", "Mejia", 0);
        Persona p4 = new Persona(1, "123456", "Rosa", "Mercado", 1);
        personas.add(p1);
        personas.add(p2);
        personas.add(p3);
        personas.add(p4);

        assertNotEquals(3, Metodos.obtenerPosicionGuardada(personas, p1));

    }
}