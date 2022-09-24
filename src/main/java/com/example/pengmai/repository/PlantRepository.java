package com.example.pengmai.repository;

import com.example.pengmai.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant,String> {
    Plant findPlantByPlantId(String plantId);
    List<Plant> findPlantByPlantTitleContainingOrPlantTextContaining(String title,String text);
}
