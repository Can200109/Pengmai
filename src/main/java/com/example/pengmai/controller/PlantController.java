package com.example.pengmai.controller;

import com.example.pengmai.domain.Result;
import com.example.pengmai.entity.Plant;
import com.example.pengmai.service.PlantService;
import com.example.pengmai.utill.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Plant")
@Transactional
@CrossOrigin
public class PlantController {
    @Autowired
    PlantService plantService;
    //添加或修改
    @RequestMapping("/updatePlant")
    private Result<Plant> addPlant(Plant plant){
        return ResultUtil.success(plantService.addPlant(plant));
    }
    //删除
    @RequestMapping("/deletePlant")
    private void deletePlant(Plant plant){
        plantService.delete(plant);
    }
    //查找所有
    @RequestMapping("/findAll")
    private Result<List<Plant>> findAll(){return ResultUtil.success(plantService.findAll());}
    @RequestMapping("/findLike")
    private Result<List<Plant>> findLike(String str){
        return ResultUtil.success(plantService.findLike(str));
    }

}
