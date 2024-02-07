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

    @SuppressWarnings("null")
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Carro cadastrar(@RequestBody Carro carro) {
        return carroRepository.save(carro);
    }

    @GetMapping
    public List<Carro> listar() {
        return carroRepository.findAll();
    }

    @SuppressWarnings("null")
    @GetMapping("/{id}")
    public Carro buscarPorId(@PathVariable Long id) {
        var carroOptional = carroRepository.findById(id);
        if (carroOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return carroOptional.get();
    }

    @SuppressWarnings("null")
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluirPorId(@PathVariable Long id) {
        var carroOptional = carroRepository.findById(id);
        if (carroOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        carroRepository.delete(carroOptional.get());
    }

    @SuppressWarnings("null")
    @PutMapping("/{id}")
    public Carro atualizarPorId(@PathVariable Long id, @RequestBody Carro carro) {
        var carroOptional = carroRepository.findById(id);
        if (carroOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        carro.setId(id);
        return carroRepository.save(carro);
    }

}