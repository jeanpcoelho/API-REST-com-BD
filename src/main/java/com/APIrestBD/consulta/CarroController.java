package com.APIrestBD.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Carro cadastrar(@RequestBody Carro carro) {
        return carroRepository.save(carro);
    }

    @GetMapping
    public List<Carro> listar() {
        return carroRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarPorId(@PathVariable Long id) {
        return carroRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirPorId(@PathVariable Long id) {
        if (carroRepository.existsById(id)) {
            carroRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizarPorId(@PathVariable Long id, @RequestBody Carro carro) {
        return carroRepository.findById(id)
                .map(existingCarro -> {
                    carro.setId(id);
                    return ResponseEntity.ok(carroRepository.save(carro));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
