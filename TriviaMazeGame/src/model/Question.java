package model;

import org.sqlite.SQLiteDataSource;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Question {


    private ImageIcon myImage;

    private String myQuestion;



    private ArrayList<Answer> myAnswers;

    public Question(String theQuestion) {
        myQuestion = theQuestion;
        myAnswers = new ArrayList<Answer>();
        myImage = null;
    }

    public Question(String theQuestion, String theImage) {
        myQuestion = theQuestion;
        myAnswers = new ArrayList<Answer>();
        myImage = new ImageIcon(theImage);
    }

    public void formAnswers(String theAnswer, boolean theCorrectness) {
        Answer answer = new Answer(theAnswer, theCorrectness);
        addAnswer(answer);
    }

    private void addAnswer(Answer theAnswer) {
        myAnswers.add(theAnswer);
    }

    public String getQuestion() {
        return myQuestion;
    }

    public ImageIcon getImage() {
        return myImage;
    }

    public boolean hasImage() {
        boolean flag = true;
        if (myImage == null) {
            flag = false;
        }
        return flag;
    }

//    @Override
//    public String toString() {
//
//    }
    public ArrayList<Answer> getAnswers() {
        return myAnswers;
        //should clone?
    }
}
