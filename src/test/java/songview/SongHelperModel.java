package songview;

import Models.Song;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SongHelperModel {
    private String songName;
    private String genre;
    private String artist;
    private Song selectedSong;
    private List<Song> songList;
}
