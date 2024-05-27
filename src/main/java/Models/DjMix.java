    package Models;

    import lombok.*;

    import java.util.ArrayList;
    import java.util.List;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class DjMix {
        private Long id;
        private String name;
        private String description;
        private String dateCreated;
        private List<Song>songs;

        public List<Song> getSongs() {
            if (songs == null) {
                songs = new ArrayList<>();
            }
            return songs;
        }
        public void removeSong(Song song) {
            getSongs().remove(song);
        }

    }