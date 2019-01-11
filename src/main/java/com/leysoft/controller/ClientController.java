
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

import com.leysoft.entity.CustomClientDetails;
import com.leysoft.service.JpaClientDetailsService;

@RestController
@RequestMapping(
        value = {
            "/clients"
        })
public class ClientController {

    @Autowired
    private JpaClientDetailsService clientDetailsService;

    @GetMapping
    public ResponseEntity<CustomClientDetails> getByClientId(@RequestParam(
            value = "client-id") String clientId) {
        return ResponseEntity
                .ok((CustomClientDetails) clientDetailsService.loadClientByClientId(clientId));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>>
            create(@RequestBody CustomClientDetails clientDetails) {
        clientDetailsService.addClientDetails(clientDetails);
        Map<String, Object> body = new HashMap<>();
        body.put("created", true);
        return ResponseEntity.ok(body);
    }
}
