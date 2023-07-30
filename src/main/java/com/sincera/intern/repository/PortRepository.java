package com.sincera.intern.repository;

import com.sincera.intern.model.Card;
import com.sincera.intern.model.Port;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PortRepository extends CrudRepository<Port, Integer> {
    @Query("SELECT a from Port a Where a.portName = :portName")
    Port getPortByPortName(@Param("portName") String portName);
    @Query("SELECT a from Port a Where a.portName = :portName")
    List<Port> getPortsByPortName(@Param("portName") String portName);
    @Query("SELECT p FROM Port p WHERE " +
            "(:portName is null or :portName = '' or p.portName like %:portName%) " +
            "AND (:portId is null or :portId = '' or p.portId = :portId) " +
            "AND (:portType is null or :portType = '' or p.portType = :portType) " +
            "AND (:ipAddress is null or :ipAddress = '' or p.ipAddress = :ipAddress) " +
            "AND (:bandwidth is null or :bandwidth = '' or p.bandwidth = :bandwidth) " +
            "AND (:trail is null or :trail = '' or p.trail = :trail) " +
            "AND (:parentCardId is null or p.parentCardId = :parentCardId) " +
            "AND (:parentCardName is null or :parentCardName = '' or p.parentCardName = :parentCardName)")
    List<Port> getPortsBy(
            @Param("portName") String portName,
            @Param("portId") Integer portId,
            @Param("portType") String portType,
            @Param("ipAddress") String ipAddress,
            @Param("bandwidth") String bandwidth,
            @Param("trail") String trail,
            @Param("parentCardId") Integer parentCardId,
            @Param("parentCardName") String parentCardName);
}
