package edu.pucmm.eict.vaadin14.data.service;

import edu.pucmm.eict.vaadin14.data.entity.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class EstudianteService {

    @Autowired
    EstudianteRepository estudianteRepository;

    public void crearEstudiante(Estudiante estudiante){
        estudianteRepository.save(estudiante);
    }

    public List<Estudiante> listaEstudiantes(){
        return estudianteRepository.findAll();
    }

    public Stream<Estudiante> listaEstudiantes(Pageable pageable){
        return estudianteRepository.findAll(pageable).stream();
    }

    public long cantidadEstudiante(){
        return estudianteRepository.count();
    }

    public void borrarEstudiante(Estudiante estudianteSeleccionado) {
        estudianteRepository.delete(estudianteSeleccionado);
    }
}
