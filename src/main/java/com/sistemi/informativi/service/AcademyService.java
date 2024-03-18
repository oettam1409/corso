package com.sistemi.informativi.service;

import com.sistemi.informativi.Exception.CustomException;
import com.sistemi.informativi.entity.Academy;

import java.util.List;
import java.util.Map;

public interface AcademyService {

    public Academy checkSaveOrUpdated(Academy academy);

    public List<Academy> checkFindAllAcademies() throws CustomException;

    public Academy checkFindAllAcademyByCode(String code) throws CustomException;

    public Map<String, Boolean> checkRemoveAcademy(String code);
}
