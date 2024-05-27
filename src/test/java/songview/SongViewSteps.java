package songview;

import Models.Song;
import Services.SongServices;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SongViewSteps {
private final SongHelperModel helperModel;
private final SongServices songServices=new SongServices();

public SongViewSteps(SongHelperModel helperModel) {this.helperModel = helperModel;}

    @When("отваряне на приложението")
    public void openApp() {
    }

    @Then("Визуализира се списък с музикални парчета")
    public void checkSongList(){
        List<Song> songs=songServices.load();
        assertNotNull(songs);
        assertTrue(songs.size()>0);

    }
    @When("потребителя въвежда име на музикално парче {string} в полето за търсене")
   public void enterSongName(String songName){
        helperModel.setSongName(songName);
    }

    @When("натиска бутона за търсене")
    public void clickSearchButton() {
//        List<Song> songs = songServices.load();
////        String songName = helperModel.getSongName();
////        helperModel.setSongList(songs.stream()
////                .filter(song -> song.getTitle().equalsIgnoreCase(songName))
////                .toList());
        String songName = helperModel.getSongName();
        List<Song> songs = songServices.load();
        List<Song> filteredSongs = songs.stream()
                .filter(song -> song.getTitle().equalsIgnoreCase(songName))
                .toList();
        helperModel.setSongList(filteredSongs);
    }

    @Then("Визуализира се музикално парче с име {string}")
    public void checkSongWithName(String expectedSongName) {
        List<Song> searchResults = helperModel.getSongList();
        assertNotNull(searchResults);
        assertTrue(searchResults.stream().anyMatch(song -> song.getTitle().equalsIgnoreCase(expectedSongName)));
    }

    @When("потребителя избере жанр {string}")
    public void selectGenre(String genre) {
        helperModel.setGenre(genre);
    }


    @When("натиска бутона за филтриране")
    public void clickFilterButton() {
        List<Song> songs = songServices.load();
        String genre = helperModel.getGenre();
        helperModel.setSongList(songs.stream()
                .filter(song -> song.getGenre().equalsIgnoreCase(genre))
                .toList());
    }
    @Then("Визуализира се списък с музикални парчета в жанр {string}")
    public void checkSongsByGenre(String genre) {
        List<Song> filteredSongs = helperModel.getSongList();
        assertNotNull(filteredSongs);
        assertTrue(filteredSongs.size() > 0);
        assertTrue(filteredSongs.stream().allMatch(song -> song.getGenre().equalsIgnoreCase(genre)));
    }
    @When("потребителя избере изпълнител {string}")
    public void selectArtist(String artist) {
        helperModel.setArtist(artist);
    }
    @Then("Визуализира се списък с музикални парчета на изпълнител {string}")
    public void checkSongsByArtist(String artist) {
        List<Song>filteredSongs=helperModel.getSongList();
        helperModel.setSongList(filteredSongs);
        assertNotNull(filteredSongs);
        assertTrue(filteredSongs.size() > 0);
        assertTrue(filteredSongs.stream().allMatch(song -> song.getArtist().equalsIgnoreCase(artist)));
    }

    @When("потребителя избере музикално парче с име {string}")
    public void selectSongByName(String songName) {
        Song selectedSong = songServices.load().stream()
                .filter(song -> song.getTitle().equalsIgnoreCase(songName))
                .findFirst()
                .orElse(null);
        helperModel.setSelectedSong(selectedSong);
    }

    @When("натиска бутона за детайлна информация")
    public void clickDetailsButton() {
    }

    @Then("Визуализира се детайлна информация за музикалното парче {string}")
    public void checkSongDetails(String songName) {
        Song selectedSong = helperModel.getSelectedSong();
        assertNotNull(selectedSong);
        assertEquals(songName, selectedSong.getTitle());
    }
    @When("натиска бутона за филтриране за артист")
    public void clickArtistFilterButton() {
        String artist = helperModel.getArtist();
        List<Song> songs = songServices.load();
        List<Song> filteredSongs = songs.stream()
                .filter(song -> song.getArtist().equalsIgnoreCase(artist))
                .collect(Collectors.toList());
        helperModel.setSongList(filteredSongs);
    }
}
