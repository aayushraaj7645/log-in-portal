package net.engineeringdigest.journalApp.cacheApp;

import jakarta.annotation.PostConstruct;
import net.engineeringdigest.journalApp.entity.CachePojo;
import net.engineeringdigest.journalApp.repository.configCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CacheApp {

    public Map<String ,String> appCache = new HashMap<>();


    @Autowired
    private configCacheRepository configCacheRepository;

    @PostConstruct
    public void init() {
        List<CachePojo> all = configCacheRepository.findAll();
        for(CachePojo cachePojo : all){
             appCache.put(cachePojo.getKeys(), cachePojo.getValues());
        }
    }




}
