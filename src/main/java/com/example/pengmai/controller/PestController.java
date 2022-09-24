package com.example.pengmai.controller;

import com.example.pengmai.domain.Result;
import com.example.pengmai.entity.PestControl;
import com.example.pengmai.entity.Notice;
import com.example.pengmai.entity.Plant;
import com.example.pengmai.service.PestControlService;
import com.example.pengmai.utill.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PestControl")
@Transactional
@CrossOrigin
public class PestController {
    @Autowired
    PestControlService pestControlService;
    @RequestMapping("/updatePestControl")
    private Result<PestControl> update(PestControl pestControl){
        return ResultUtil.success(pestControlService.addPestControl(pestControl));
    }
    @RequestMapping("/deletePestControl")
    private void delete(PestControl pestControl){
        pestControlService.delete(pestControl);
    }

    @RequestMapping("/findAll")
    private Result<List<PestControl>> findAll(){
        return ResultUtil.success(pestControlService.findAll());
    }
    @RequestMapping("/findPestControlById")
    private Result<List<PestControl>> findPestControl(PestControl pestControl){
        return ResultUtil.success(pestControlService.findPestControl(pestControl.getPestId()));
    }
    @RequestMapping("/findLike")
    private Result<List<PestControl>> findLike(String str){
        return ResultUtil.success(pestControlService.findLike(str));
    }
}
