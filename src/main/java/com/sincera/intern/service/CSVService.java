package com.sincera.intern.service;

import com.sincera.intern.model.Site;
import com.sincera.intern.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CSVService {

    @Autowired
    SiteRepository siteRepository;
//    public byte[] getCsv() {
//        List<Site> sites = siteRepository.findAll();
//        CsvWriter writer = new CsvWriter(new OutputStreamWriter(new ByteArrayOutputStream()));
//        writer.writeAll(users);
//        writer.close();
//        return writer.toString().getBytes();
//    }
}
