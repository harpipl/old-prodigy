package pl.harpi.prodigy.runtime;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import pl.harpi.prodigy.runtime.service.StructureService;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class CacheInitialisation {
    private final CacheManager cacheManager;

    private final StructureService structureService;

    public CacheInitialisation(CacheManager cacheManager, StructureService structureService) {
        this.cacheManager = cacheManager;
        this.structureService = structureService;
    }

    @PostConstruct
    private void init() {
        Map<String, Object> structure = structureService.getStructure(1L);
        cacheManager.getCache("cacheProducts").put(1L, structure);
    }
}
