package com.example.pengmai.repository;

import com.example.pengmai.entity.PestControl;
import com.example.pengmai.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PestControlRepository extends JpaRepository<PestControl,String> {
    PestControl findPestControlByPestId(String pestId);
    List<PestControl> findPestControlByPestTitleContainsAndPestContentContains(String title, String text);
}
