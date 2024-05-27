package mixview;

import Models.DjMix;
import Models.Song;
import Services.MixServices;
import Services.SongServices;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mixview.MixHelperModel;

import java.util.List;

import static org.junit.Assert.*;

public class MixViewSteps {
    private final MixServices mixServices = new MixServices();
    private final SongServices songServices = new SongServices();
    private MixHelperModel helperModel;

    public MixViewSteps(MixHelperModel helperModel) {
        this.helperModel = helperModel;
    }

    @Given("отваряне на приложението")
    public void openApp() {
    }

    @Given("потребителят стартира нова DJ сесия с име {string}")
    public void startNewDjSession(String mixName) {
        helperModel.setMixName(mixName);
        DjMix newMix = new DjMix();
        newMix.setName(mixName);
        helperModel.setCurrentMix(newMix);
    }

    @Then("системата трябва да започне записването на микса")
    public void systemStartsRecordingMix() {
        DjMix currentMix = helperModel.getCurrentMix();
        assertNotNull(currentMix);
        assertEquals(helperModel.getMixName(), currentMix.getName());
    }

    @When("потребителят спре сесията и запази сесията като {string}")
    public void saveDjSession(String mixName) {
        DjMix currentMix = helperModel.getCurrentMix();
        currentMix.setName(mixName);
        mixServices.save(currentMix);
    }

    @Then("системата трябва да съхрани сесията с името {string}")
    public void systemSavesSession(String mixName) {
        DjMix savedMix = mixServices.load().stream()
                .filter(mix -> mix.getName().equals(mixName))
                .findFirst()
                .orElse(null);
        assertNotNull(savedMix);
        assertEquals(mixName, savedMix.getName());
    }

    @When("потребителят навигира до секцията със запазени сесии")
    public void navigateToSavedSessions() {
        helperModel.setDjMixList(mixServices.load());
    }

    @Then("системата трябва да покаже списък със запазени сесии")
    public void systemShowsSavedSessions() {
        List<DjMix> djMixList = helperModel.getDjMixList();
        assertNotNull(djMixList);
        assertFalse(djMixList.isEmpty());
    }

    @When("потребителят добавя песен с име {string} към сесията {string}")
    public void addSongToSession(String songName, String mixName) {
        DjMix currentMix = helperModel.getCurrentMix();
        assertNotNull(currentMix);
        assertEquals(mixName, currentMix.getName());

        List<Song> allSongs = songServices.load();
        Song songToAdd = allSongs.stream()
                .filter(song -> song.getTitle().equalsIgnoreCase(songName))
                .findFirst()
                .orElse(null);
        assertNotNull(songToAdd);

        currentMix.getSongs().add(songToAdd);
        mixServices.save(currentMix);
        helperModel.setCurrentMix(currentMix);
    }

    @Then("системата трябва да съдържа песен с име {string} в сесията {string}")
    public void systemContainsSongInSession(String songName, String mixName) {
        DjMix currentMix = helperModel.getCurrentMix();
        assertNotNull(currentMix);
        assertEquals(mixName, currentMix.getName());
        assertTrue(currentMix.getSongs().stream()
                .anyMatch(song -> song.getTitle().equalsIgnoreCase(songName)));
    }

    @When("потребителят премахва песен с име {string} от сесията {string}")
    public void removeSongFromSession(String songName, String mixName) {
        DjMix currentMix = helperModel.getCurrentMix();
        assertNotNull(currentMix);
        assertEquals(mixName, currentMix.getName());

        List<Song> allSongs = songServices.load();
        Song songToRemove = allSongs.stream()
                .filter(song -> song.getTitle().equalsIgnoreCase(songName))
                .findFirst()
                .orElse(null);
        assertNotNull(songToRemove);

        currentMix.removeSong(songToRemove);
        mixServices.save(currentMix);
        helperModel.setCurrentMix(currentMix);
    }

    @Then("системата не трябва да съдържа песен с име {string} в сесията {string}")
    public void systemDoesNotContainSongInSession(String songName, String mixName) {
        DjMix currentMix = helperModel.getCurrentMix();
        assertNotNull(currentMix);
        assertEquals(mixName, currentMix.getName());
        assertTrue(currentMix.getSongs().stream()
                .noneMatch(song -> song.getTitle().equalsIgnoreCase(songName)));
    }
}