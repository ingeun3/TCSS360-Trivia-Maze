package view;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {


    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {

        soundURL [0] = getClass().getClassLoader().getResource("starwar.wav");

    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            System.out.println("got");
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch(Exception e) {

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