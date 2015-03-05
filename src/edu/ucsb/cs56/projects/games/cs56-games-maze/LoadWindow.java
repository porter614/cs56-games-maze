package edu.ucsb.cs56.projects.games.cs56_games_maze;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.File;
import java.lang.Exception;
import java.io.*;
import java.util.Comparator;
import java.util.Collections;

/**
Class for creating a Load window and taking user input
@author Zak Blake
*/
public class LoadWindow{

  private boolean emptyFile = false;
  private ArrayList<MazeGameSave> currentlySavedGames;
  private MazeGameSaver myGameSaver;

  public LoadWindow(){
    // set up the frame
    JFrame frame = new JFrame("GameLoad");
    frame.setVisible(true);
    frame.setSize(300,500);
    Button loadButton = new Button("load");
    frame.getContentPane().add(BorderLayout.NORTH,loadButton);

    




  }


}
