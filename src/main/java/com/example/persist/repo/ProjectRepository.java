package com.example.persist.repo;

import com.example.persist.entity.Device;
import com.example.persist.entity.Event;
import com.example.persist.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository <Project,Integer>{


    @Query("  SELECT p  FROM Device as d " +
            " join Project as p on d.id=p.id " +
            "where p.name =:name")
    List<Device> findDevicesByProject(@Param("name") String name);
}
