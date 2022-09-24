package com.example.pengmai.service;

import com.example.pengmai.auth.CheckLogin;
import com.example.pengmai.entity.PestControl;
import com.example.pengmai.entity.PestControl;
import com.example.pengmai.repository.PestControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class PestControlService {
    @Autowired
    PestControlRepository pestControlRepository;
    @CheckLogin
    public PestControl addPestControl(PestControl pestControl){
        if(pestControlRepository.findPestControlByPestId(pestControl.getPestId())==null){
            pestControl.setPestId(UUID.randomUUID().toString());
        }
        return pestControlRepository.save(pestControl);
    }
    @CheckLogin
    public void delete(PestControl pestControl) {
        pestControl = findPestControl(pestControl.getPestId());
        pestControlRepository.delete(pestControl);
    }
    public PestControl findPestControl(String id){
        return pestControlRepository.findPestControlByPestId(id);
    }

    public List<PestControl> findLike(String str){
        return pestControlRepository.findPestControlByPestTitleContainsAndPestContentContains(str,str);
    }
    public List<PestControl> findAll(){
        return pestControlRepository.findAll();
    }
}
