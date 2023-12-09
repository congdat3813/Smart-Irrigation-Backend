package com.hcmut.smartirrigation.service;

import com.hcmut.smartirrigation.exception.ForbiddenException;
import com.hcmut.smartirrigation.exception.NotFoundException;
import com.hcmut.smartirrigation.model.dto.ResponsePage;
import com.hcmut.smartirrigation.model.dto.farm.FarmCreateDTO;
import com.hcmut.smartirrigation.model.entity.Farm;
import com.hcmut.smartirrigation.model.entity.Users;

import java.util.UUID;

public interface FarmService {
    Farm createNewOne(FarmCreateDTO farmCreate, Users user);

    ResponsePage<Farm> getPage(String keyword, int page, int size, Users users);

    Farm getDetail(UUID id) throws NotFoundException;

    void delete(UUID id, Users user) throws NotFoundException, ForbiddenException;
}
