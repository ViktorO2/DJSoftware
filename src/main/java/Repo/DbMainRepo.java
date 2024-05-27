package Repo;

import Models.DjMix;
import Models.Song;

import java.util.ArrayList;
import java.util.List;

public class DbMainRepo {
    private static List<Song> songList=new ArrayList<>();
    private static List<DjMix> djMixList=new ArrayList<>();

    static{
        songList.add(new Song(1L, "Song 1", "Rock","Artist 1",3.14,"2024-05-20"));
        songList.add(new Song(2L, "Song 2", "Jazz","Artist 2",2.23,"2024-05-21"));
        songList.add(new Song(3L, "Song 3", "Pop","Artist 3",3.44,"2024-05-21"));

        DjMix djMix = new DjMix(1L, "Mix 1", "First mix","2024-05-22", List.of(songList.get(0), songList.get(1)));
        djMixList.add(djMix);

    }


    public static List<Song> getSongList() {
        return songList;
    }

    public static List<DjMix> getDjMixList() {
        return djMixList;
    }
}
