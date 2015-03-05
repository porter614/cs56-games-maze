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
public class LoadWindow {

  private boolean emptyFile = false;
  private ArrayList<MazeGameSave> currentlySavedGames;
  private MazeGameSaver myGameSaver;
  private int NumberOfSavedGames;
  private MazeGui operatableGUI;

  public LoadWindow(MazeGui gameGui) throws IOException{

    operatableGUI = gameGui; // get a ref to the curent GUI
    // load the saved games into ArrayList
    try{
      myGameSaver = new MazeGameSaver("StoredGameSaves.ser");
      currentlySavedGames = myGameSaver.getSavedGameList();
    }catch(IOException e){
      e.printStackTrace();
    }
    // extract number of saved games
    NumberOfSavedGames= currentlySavedGames.size();
    System.out.println("Number of Games Loaded = "+NumberOfSavedGames);

    // set up the frame
    JFrame frame = new JFrame("GameLoad");
    frame.setVisible(true);
    frame.setSize(175,500);
    frame.setResizable(false);

    // Diplay the Saved Games for User to choose from on a panel

    JPanel mainPanel = new JPanel(); // panel to store all the buttons

    JButton[] checkBoxList = new JButton[NumberOfSavedGames];
    for(int i=0; i<NumberOfSavedGames; i++)
    {
      String currentTag = currentlySavedGames.get(i).getSaveTag();
      checkBoxList[i] = new JButton(currentTag);
      checkBoxList[i].addActionListener(listener);
      mainPanel.add(checkBoxList[i]);
    }
    frame.getContentPane().add(BorderLayout.CENTER,mainPanel);

  }


  // listener to use button input to
  ActionListener listener = new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e){
      if (e.getSource() instanceof JButton){
        String saveTagName = e.getActionCommand();
        MazeGameSave specifiedGame = myGameSaver.SearchForSavedGame(saveTagName);
        if (specifiedGame != null) System.out.println(">> GameFound <<");
        operatableGUI.newMaze(specifiedGame);
      }
    }
  }; // end of listener



}
