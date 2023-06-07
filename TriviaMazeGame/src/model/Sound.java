package model;
/*
 *
 * This class is the Sou class that creates the Maze Map.
 *
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 *
 */
import java.io.File;
import javax.sound.sampled.*;

// This class creates the sound.
public class Sound {

    /** Initialized the myClip. */
    Clip myClip;

    /**
     *
     * @param theSongName passed the current Sound file Name.
     */
    public Sound(final String theSongName) {
        try {
            File titleSound = new File(theSongName);
            if (titleSound.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(titleSound);
                myClip = AudioSystem.getClip();
                myClip.open(audioInputStream);
                FloatControl gainControl =
                        (FloatControl) myClip.getControl(FloatControl.Type.MASTER_GAIN);
                if (theSongName == "Title Sound.wav") {
                    gainControl.setValue(-30.0f);
                } else {
                    gainControl.setValue(-20.0f);
                }

                myClip.start();

            } else {
                System.out.println("Failed to load audio file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method play the sound.
     */
    public void play() {
        myClip.start();
    }

    /**
     * This method loops the sound.
     */
    public void loop() {
        myClip.loop(myClip.LOOP_CONTINUOUSLY);
    }

    /**
     * This method stop the sound.
     */
    public void stop() {
        myClip.stop();
    }
}