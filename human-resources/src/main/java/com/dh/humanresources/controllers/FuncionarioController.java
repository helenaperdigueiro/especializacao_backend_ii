package com.dh.humanresources.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping(value = "funcionarios")
public class FuncionarioController {

    @PostMapping
    @PreAuthorize("hasAnyRole('PESSOAL_RH', 'ADMIN_RH')")
    ResponseEntity<Void> create(@RequestBody Map<String, Object> fields) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand("ID").toUri()).build();
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN_RH')")
    ResponseEntity<Void> delete(@PathVariable String id) {
        return ResponseEntity.accepted().build();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('Funcionario')")
    ResponseEntity<Void> update(@RequestBody Map<String, Object> fields) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand("ID").toUri()).build();
    }

}
