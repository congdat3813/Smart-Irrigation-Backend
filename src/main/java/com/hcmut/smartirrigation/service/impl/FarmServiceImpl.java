package com.hcmut.smartirrigation.service.impl;

import com.hcmut.smartirrigation.exception.BadRequestException;
import com.hcmut.smartirrigation.exception.ForbiddenException;
import com.hcmut.smartirrigation.exception.NotFoundException;
import com.hcmut.smartirrigation.model.dto.ResponsePage;
import com.hcmut.smartirrigation.model.dto.farm.FarmCreateDTO;
import com.hcmut.smartirrigation.model.entity.Farm;
import com.hcmut.smartirrigation.model.entity.Location;
import com.hcmut.smartirrigation.model.entity.Plant;
import com.hcmut.smartirrigation.model.entity.Users;
import com.hcmut.smartirrigation.repository.FarmRepository;
import com.hcmut.smartirrigation.repository.LocationRepository;
import com.hcmut.smartirrigation.repository.PlantRepository;
import com.hcmut.smartirrigation.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class FarmServiceImpl implements FarmService {
    @Autowired
    private FarmRepository farmRepository;
    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Override
    @Transactional
    public Farm createNewOne(FarmCreateDTO farmCreate, Users user) {
        Location location = locationRepository.findById(farmCreate.getLocation_id())
                .orElseThrow(() -> new BadRequestException("Location not found", "40003"));
        Plant plant = plantRepository.findById(farmCreate.getPlant_id())
                .orElseThrow(() ->  new BadRequestException("Plant not found", "40004"));

        Farm farm = new Farm();
        farm.setCreatedAt(new Date());
        farm.setFarmName(farmCreate.getName());
        farm.setSoilType(farmCreate.getSoilType());
        farm.setAcreage(farmCreate.getAcreage());
        farm.setUser(user);
        farm.setFarmLocation(location);
        farm.setPlant(plant);
        return farmRepository.save(farm);
    }

    @Override
    public ResponsePage<Farm> getPage(String keyword, int page, int size, Users user) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Farm> farmPage = farmRepository.getPage(keyword, user.getId(), pageable);
        return new ResponsePage<>(farmPage.getContent(), farmPage.getTotalElements(), farmPage.getNumber(), farmPage.getNumberOfElements());
    }

    @Override
    public Farm getDetail(UUID id) throws NotFoundException {
        return farmRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found farm"));
    }

    @Override
    public void delete(UUID id, Users user) throws NotFoundException, ForbiddenException {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found farm"));
        if (!farm.getUser().getId().equals(user.getId())) {
            throw new ForbiddenException("No permission for this action!");
        }
        farmRepository.delete(farm);
    }
}
