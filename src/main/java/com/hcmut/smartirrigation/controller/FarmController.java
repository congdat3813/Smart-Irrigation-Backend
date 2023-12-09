package com.hcmut.smartirrigation.controller;

import com.hcmut.smartirrigation.exception.ForbiddenException;
import com.hcmut.smartirrigation.exception.NotFoundException;
import com.hcmut.smartirrigation.model.dto.ResponsePage;
import com.hcmut.smartirrigation.model.dto.farm.FarmCreateDTO;
import com.hcmut.smartirrigation.model.entity.Farm;
import com.hcmut.smartirrigation.model.entity.SessionUser;
import com.hcmut.smartirrigation.model.entity.Users;
import com.hcmut.smartirrigation.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/farm")
public class FarmController {

    @Autowired
    private FarmService farmService;
    @PostMapping()
    public ResponseEntity<Farm> createFarm(
            @RequestBody FarmCreateDTO farmCreate,
            Principal principal
    ) {
        Users user = ((SessionUser)((Authentication) principal).getPrincipal()).user();
        return ResponseEntity.status(HttpStatus.CREATED).body(farmService.createNewOne(farmCreate, user));
    }
    @GetMapping
    public ResponseEntity<ResponsePage<Farm>> getFarms(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Principal principal
    ) {
        Users user = ((SessionUser)((Authentication) principal).getPrincipal()).user();
        return ResponseEntity.ok().body(farmService.getPage(keyword, page, size, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farm> getFarms(
            @PathVariable UUID id
    ) throws NotFoundException {
        return ResponseEntity.ok().body(farmService.getDetail(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFarm(
            @PathVariable UUID id,
            Principal principal
    ) throws NotFoundException, ForbiddenException {
        Users user = ((SessionUser)((Authentication) principal).getPrincipal()).user();
        farmService.delete(id, user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Delete Successfully!");
    }
}
