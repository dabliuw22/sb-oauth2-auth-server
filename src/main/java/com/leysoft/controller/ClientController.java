
package com.leysoft.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leysoft.dto.ClientDto;
import com.leysoft.service.inter.ClientService;

@RestController
@RequestMapping(
        value = {
            "/clients"
        })
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<ClientDto> getByClientId(@RequestParam(
            value = "client-id") String clientId) {
        return ResponseEntity.ok(clientService.findByClientId(clientId));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody ClientDto dto) {
        Map<String, Object> body = new HashMap<>();
        body.put("created", clientService.save(dto));
        return ResponseEntity.ok(body);
    }
}
