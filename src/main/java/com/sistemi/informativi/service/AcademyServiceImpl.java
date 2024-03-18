package com.sistemi.informativi.service;

import com.sistemi.informativi.Exception.CustomException;
import com.sistemi.informativi.entity.Academy;
import com.sistemi.informativi.repository.AcademyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AcademyServiceImpl implements AcademyService {

    /*
    All'interno di un applicazione Spring boot in cui
    si implementa un restful Web Service Provider è pratica
    consolidata far restituire ai metodi dello strato Service
    degli oggetti java in maniera tale che il RestController
    che invocherà i metodi del service avrà gia a disposizione
    tali oggetti che verranno successivamente convertiti
     dall'Object Mapper di spingboot in formato JSON
     */

    @Value("${no.academies}")
    private String noAcademies;
    @Value("${no.academy}")
    private String noAcademy;
    private final AcademyRepository academyRepository;

    public AcademyServiceImpl(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }


    @Override
    public Academy checkSaveOrUpdated(Academy academy) {
        Academy savedOrUpdated = null;
        try{
            savedOrUpdated=academyRepository.save(academy);
        }
        catch(IllegalArgumentException | OptimisticLockingFailureException ex){
            ex.printStackTrace();
        }

        return savedOrUpdated;
    }

    @Override
    public List<Academy> checkFindAllAcademies() throws CustomException {

        List<Academy> academies = academyRepository.findAll();
        if(academies.isEmpty()) throw new CustomException(noAcademies);

        return academies;

    }

    @Override
    public Academy checkFindAllAcademyByCode(String code) throws CustomException {
        /*
        il metodo findId viene fornito da Spring Data JPA e consente
        in generale di restituire un oggetto java che rappresenta il contenuto informativo
        relativo ad un record del database con una specifica chiave primaria passata in input al metodo

        FindById a partire dalla versione 2.7 di SpringBoot
        è un metodo funzionale; possiamo infatti invocare con l'operatore "." una funzione di nome orElseThrow alla
        quale possiamo passare in input un eccezione

        Nel caso in cui l'operazione non vada in eccezione il metodo findbyid restituisce un oggetto
        altrimenti viene catturata l'eccezione che richiamiamo nella funzione orElseThrow
         */

        return academyRepository.findById(code).orElseThrow(()-> new CustomException(noAcademy));
    }

    @Override
    public Map<String, Boolean> checkRemoveAcademy(String code) {

        Map<String,Boolean> removeMap = new HashMap<>();

        try{
            academyRepository.deleteById(code);
            removeMap.put("deletion", true);
        }
        catch(IllegalArgumentException ex){
            ex.printStackTrace();
        }
        return removeMap ;
    }
}
