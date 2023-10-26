package com.sincera.intern.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.sincera.intern.dto.SiteDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataProcessingService {
    public Map<String, List<SiteDto>> processJsonData(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<SiteDto> siteDtos = objectMapper.readValue(json, new TypeReference<List<SiteDto>>() {});

        // Create a map to store the data grouped by cluster ID
        Map<String, List<SiteDto>> groupedData = new HashMap<>();

        for (SiteDto siteDto : siteDtos) {
            String clusterId = siteDto.getCluster_id();

            // Check if the cluster ID is already in the map
            if (groupedData.containsKey(clusterId)) {
                // If it is, add the current siteDto to the list for that cluster
                groupedData.get(clusterId).add(siteDto);
            } else {
                // If it's not, create a new list for that cluster and add the siteDto
                List<SiteDto> newCluster = new ArrayList<>();
                newCluster.add(siteDto);
                groupedData.put(clusterId, newCluster);
            }
        }

        return groupedData;
    }
}


