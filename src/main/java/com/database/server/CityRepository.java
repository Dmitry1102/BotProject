package com.database.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends JpaRepository<City,Long>{

    @Query("SELECT t.cityinfo FROM City t where t.cityname = :name")
    String findCityInfo(@Param("name") String name);

}
