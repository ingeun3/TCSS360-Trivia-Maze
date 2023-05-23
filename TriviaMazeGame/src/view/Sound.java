package view;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {


    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        playMusic();

        //soundURL [0] = getClass().getClass().getResource("starwar.wav");

    }
    public void playMusic(){
        try {

            // Initialize audio system
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/src/starwar.wav"));

            if (audioInputStream != null) {
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);

                // Play the audio in a loop
                //clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Failed to load audio file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



//    public void setFile(int i) {
//        try {
//            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
//            System.out.println("got");
//            clip = AudioSystem.getClip();
//            clip.open(ais);
//
//        }catch(Exception e) {
//
//        }
//    }

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
