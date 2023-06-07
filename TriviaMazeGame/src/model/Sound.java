package model;
/*
 *
 * This class is the Sou class that creates the Maze Map.
 *
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 *
 */
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.*;
import javax.swing.*;

public class Sound {


    Clip clip;
    public Sound(String songName) {
        try {
            File titleSound = new File(songName);

            if (titleSound.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(titleSound);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                FloatControl gainControl =
                        (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                if (songName == "Title Sound.wav") {
                    gainControl.setValue(-30.0f);
                } else {
                    gainControl.setValue(-20.0f);
                }

                clip.start();

            } else {
                System.out.println("Failed to load audio file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}