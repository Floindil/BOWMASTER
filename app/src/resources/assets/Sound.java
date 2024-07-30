package app.src.resources.assets;

import javax.sound.sampled.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * Example Code!
 */
public class Sound {


    /**
     * Example for playing audio
     * @param args Noine
     */
    public static void main(String[] args) {
        try {
            // Load the sound file
            InputStream inputStream = Sound.class.getResourceAsStream("sounds/bell1.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
            
            // Get a sound clip resource
            Clip clip = AudioSystem.getClip();
            
            // Open audio clip and load samples from the audio input stream
            clip.open(audioStream);
            
            // Start the clip
            clip.start();
            
            // Optional: Keep the program running till the sound clip is finished
            // This is necessary because the program might exit before the sound is played completely
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("The specified audio file is not supported.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error playing the audio file.");
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.out.println("Audio line for playing back is unavailable.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Playback interrupted.");
            e.printStackTrace();
        }
    }
}
