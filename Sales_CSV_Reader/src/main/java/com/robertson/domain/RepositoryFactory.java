package com.robertson.domain;

import com.robertson.domain.interfaces.IRepositoy;
import com.robertson.persistance.SalesCSVReader;

// Returns a new instance of a SalesCSVReader
public class RepositoryFactory {

    public static IRepositoy csvRepository(String dataURL){
        return new SalesCSVReader(dataURL);
    }
}
