package Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    private Long id;
    private String title;
    private String genre;
    private String artist;
    private double duration;
    private String dateAdded;

}
