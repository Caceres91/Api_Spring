package com.api.desarrollo.controller;

import com.api.desarrollo.entitys.Publicacion;
import com.api.desarrollo.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publicacion")
public class Publicacionontroller {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<List<Publicacion>> getPublicaciones(){
        List<Publicacion> publicacions = publicacionRepository.findAll();
        return ResponseEntity.ok(publicacions);
    }

    @RequestMapping(
            value = "{publicacionId}",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<Publicacion> getPublicaion(@PathVariable Integer publicacionId){
        Optional<Publicacion> optionalPublicacion = publicacionRepository.findById(publicacionId);
        return optionalPublicacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            produces = "application/json")
    public ResponseEntity<Publicacion> CreatePublicacion(@RequestBody Publicacion publicacion){
        Publicacion publicacion1 = publicacionRepository.save(publicacion);
        return ResponseEntity.ok(publicacion);
    }

    @RequestMapping(
            value = "/update",
            method = RequestMethod.PUT,
            produces = "application/json")
    public ResponseEntity<Publicacion> UpdateUser(@RequestBody Publicacion publicacion){
        Optional<Publicacion> userOptional = publicacionRepository.findById(publicacion.getId());
        if(userOptional.isPresent()){
            Publicacion publicacionUpdate = userOptional.get();
            publicacionUpdate.setImagen(publicacion.getImagen());
            publicacionUpdate.setDescripcion(publicacion.getDescripcion());
            publicacionRepository.save(publicacionUpdate);
            return ResponseEntity.ok(publicacionUpdate);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(
            value = "{publicacionId}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    public ResponseEntity<Void> DeleteUser(@PathVariable Integer publicacionId){
        publicacionRepository.deleteById(publicacionId);
        return ResponseEntity.ok(null);
    }
}
