package com.database.server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> listAll() {
        return cityRepository.findAll();
    }

    public void save(City configs) {
        cityRepository.save(configs);
    }

    public City get(long id) {
        return cityRepository.findById(id).get();
    }

    public void delete(long id) {
        cityRepository.deleteById(id);
    }


    public String getCityByName(String name){
        return cityRepository.findCityInfo(name);
    }
}
