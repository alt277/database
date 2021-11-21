package com.example.persist.repo;

import com.example.persist.entity.Device;
import com.example.persist.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository <Device,Integer> {

    Optional<Device> findById(Integer id);

    @Query("select d from Device d where d.serialNumber = :serialNumber  ")
    List<Device> findBySerialNumber(@Param("serialNumber") String serialNumber);




}
