package com.example.pengmai.service;

import com.example.pengmai.auth.CheckLogin;
import com.example.pengmai.entity.Notice;
import com.example.pengmai.entity.Plant;
import com.example.pengmai.entity.Shortcut;
import com.example.pengmai.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)


public class PlantService {
    @Autowired
    PlantRepository plantRepository;
    @CheckLogin
    public Plant addPlant(Plant plant){
        if(plantRepository.findPlantByPlantId(plant.getPlantId())==null){
            plant.setPlantId(UUID.randomUUID().toString());
        }
        return plantRepository.save(plant);
    }
    @CheckLogin
    public void delete(Plant plant) {
        plant = findPlant(plant.getPlantId());
        plantRepository.delete(plant);
    }
    public Plant findPlant(String id){
        return plantRepository.findPlantByPlantId(id);
    }

    public List<Plant> findLike(String str){
        return plantRepository.findPlantByPlantTitleContainingOrPlantTextContaining(str,str);
    }
    public List<Plant> findAll(){
        return plantRepository.findAll();
    }
}
