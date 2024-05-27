package Services;

import Models.Song;
import Repo.DbMainRepo;

import java.util.List;

public class SongServices {
    public List<Song>load(){
        return DbMainRepo.getSongList();
    }

}
