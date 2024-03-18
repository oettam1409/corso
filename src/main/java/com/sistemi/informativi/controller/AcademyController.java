package com.sistemi.informativi.controller;

import com.sistemi.informativi.Exception.CustomException;
import com.sistemi.informativi.entity.Academy;
import com.sistemi.informativi.service.AcademyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/api/academies")
public class AcademyController {

    private final AcademyService academyService;

    public AcademyController(AcademyService academyService) {
        this.academyService = academyService;
    }

    @GetMapping
    public List<Academy> findAllAcademies() throws CustomException {

        /* nel caso positivo il metodo del service ritorna una List di tipo academy
        che, essendo un oggetto java, viene automaticamente convertito dall'Object Mapper
        in un file jason
         */

        return academyService.checkFindAllAcademies();

    }


    /*
    l'annotation @PathVariable utilizzata per chiedere a spring restful
    di convertire la variabile di path inserita nella url del browser
    da parte del consumer in una variabile java
     */
    @GetMapping("/code/{code}")
    public Academy findAcademyByCode(@PathVariable String code) throws CustomException {
        return academyService.checkFindAllAcademyByCode(code);
    }

    @PostMapping
    public Academy saveAcademy(@Valid @RequestBody Academy academy) {
        return academyService.checkSaveOrUpdated(academy);
    }

    @PutMapping
    public Academy updateAcademy(@RequestBody Academy academy) {
        return academyService.checkSaveOrUpdated(academy);
    }

    @DeleteMapping("/code/{code}")
    public Map<String, Boolean> removeAcademy(@PathVariable String code) {

        return academyService.checkRemoveAcademy(code);

    }

}
