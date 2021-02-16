package com.example.servicecompany.controllers;



import com.example.servicecompany.models.Client;
import com.example.servicecompany.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin("http://192.168.1.8:4200")
@RequestMapping("/api/client/")
@RestController
public class ClientController {

    @Autowired
    private IClientService clientService;


    @GetMapping()
    public List<Client> getAll() {
        //return ResponseEntity.ok(HttpStatus.OK);
        return clientService.findAllClients();
    }

    @GetMapping("page/{page}")
    public Page<Client> getAll(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page,4);
        return clientService.findAllClients(pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id){
        Client client = null;
        Map<String,Object> response = new HashMap<>();
        try{
             client = clientService.findById(id);
        }catch (DataAccessException e){
            response.put("message","Error when access into database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        if(client == null) {
            response.put("message","Client with id ".concat(id.toString().concat(" Not Exist is data base")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Client client) {
        //error when inserting into database
        Client newClient = null;
        Map<String,Object> response = new HashMap<>();


        try{
            newClient = clientService.save(client);
        }catch(DataAccessException e){
            response.put("message","Error when access into database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","the client has been created successfully");
        response.put("client",newClient);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id , @RequestBody Client client) {
        Client actualClient = clientService.findById(id);


        Map<String,Object> response = new HashMap<>();

        if(actualClient == null) {
            response.put("message","Client with id ".concat(id.toString().concat(" Not Exist is data base")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try{

            System.out.println(client);
            actualClient.setFname(client.getFname());
            actualClient.setLname(client.getLname());
            actualClient.setEmail(client.getEmail());
            actualClient.setCreateat(client.getCreateat());
            clientService.save(actualClient);
        }catch (DataAccessException e){
            response.put("message","Error when access into database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message","the client has been UPDATED successfully");
        response.put("client",actualClient);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Client actualClient = clientService.findById(id);
        Map<String,Object> response = new HashMap<>();
        if(actualClient == null) {
            response.put("message","Client with id ".concat(id.toString().concat(" Not Exist is data base")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
            try{
                clientService.delete(id);
            }catch (DataAccessException e) {
                response.put("message","Error when access into database");
                response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        response.put("message","the client has been DELETED successfully");
        response.put("client",actualClient);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }


}
