package net.engineeringdigest.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Getter
@Setter
@Data
@NoArgsConstructor
public class entity {
    @Id
    @NonNull
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String title ;
    @NonNull
    private String content ;

    //private LocalDateTime date;


}
