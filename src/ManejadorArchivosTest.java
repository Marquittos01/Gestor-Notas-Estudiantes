import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class ManejadorArchivosTest {

    private static final String ARCHIVO_TEST = "resources/notas_estudiantes.txt";
    private ManejadorArchivos manejador;

    @Before
    public void setUp() {
        manejador = new ManejadorArchivos();

        File file = new File(ARCHIVO_TEST);
        if (file.exists()) {
            file.delete();
        }
    }

    @After
    public void tearDown() {
        File file = new File(ARCHIVO_TEST);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testAñadirEstudiante() {
        Estudiante estudiante = new Estudiante("Pepe", 8.5);
        manejador.añadirEstudiante(estudiante);

        List<Estudiante> estudiantes = manejador.leerEstudiantes();
        assertEquals(1, estudiantes.size());
        assertEquals("Pepe", estudiantes.get(0).getNombre());
        assertEquals(8.5, estudiantes.get(0).getNota(), 0.01);
    }

    @Test
    public void testMostrarEstudiantes() {
        Estudiante e1 = new Estudiante("Pepe", 7.5);
        Estudiante e2 = new Estudiante("Juan", 9.0);
        manejador.añadirEstudiante(e1);
        manejador.añadirEstudiante(e2);

        List<Estudiante> estudiantes = manejador.leerEstudiantes();
        assertEquals(2, estudiantes.size());
        assertEquals("Pepe", estudiantes.get(0).getNombre());
        assertEquals(7.5, estudiantes.get(0).getNota(), 0.01);
        assertEquals("Juan", estudiantes.get(1).getNombre());
        assertEquals(9.0, estudiantes.get(1).getNota(), 0.01);
    }

    @Test
    public void testBuscarEstudiante() {
        Estudiante e1 = new Estudiante("Pepe", 6.8);
        Estudiante e2 = new Estudiante("Juan", 7.9);
        manejador.añadirEstudiante(e1);
        manejador.añadirEstudiante(e2);

        // Buscar a Carlos
        List<Estudiante> estudiantes = manejador.leerEstudiantes();
        Estudiante encontrado = null;
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNombre().equals("Pepe")) {
                encontrado = estudiante;
                break;
            }
        }
        assertNotNull(encontrado);
        assertEquals("Pepe", encontrado.getNombre());
        assertEquals(6.8, encontrado.getNota(), 0.01);
    }

    @Test
    public void testCalcularMedia() {
        Estudiante e1 = new Estudiante("Pepe", 5.0);
        Estudiante e2 = new Estudiante("Juan", 7.0);
        manejador.añadirEstudiante(e1);
        manejador.añadirEstudiante(e2);

        List<Estudiante> estudiantes = manejador.leerEstudiantes();
        double suma = 0;
        for (Estudiante estudiante : estudiantes) {
            suma += estudiante.getNota();
        }
        double mediaEsperada = suma / estudiantes.size();


        assertEquals(6.0, mediaEsperada, 0.01);
    }

    @Test
    public void testLeerEstudiantes() {
        // Añadir varios estudiantes
        manejador.añadirEstudiante(new Estudiante("Pepe", 8.0));
        manejador.añadirEstudiante(new Estudiante("Juan", 9.0));

        List<Estudiante> estudiantes = manejador.leerEstudiantes();

        assertEquals(2, estudiantes.size());
        assertEquals("Pepe", estudiantes.get(0).getNombre());
        assertEquals(8.0, estudiantes.get(0).getNota(), 0.01);
        assertEquals("Juan", estudiantes.get(1).getNombre());
        assertEquals(9.0, estudiantes.get(1).getNota(), 0.01);
    }

}