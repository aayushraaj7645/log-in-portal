package net.engineeringdigest.journalApp.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "app_cache_config")
@Getter
@Setter
@Data
@NoArgsConstructor
public class CachePojo {

    private String keys;
    private String values;








}
