package mixview;

import Models.DjMix;
import Models.Song;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MixHelperModel {
    private String mixName;
    private DjMix currentMix;
    private List<DjMix> djMixList;
    private List<Song> songList;
}
