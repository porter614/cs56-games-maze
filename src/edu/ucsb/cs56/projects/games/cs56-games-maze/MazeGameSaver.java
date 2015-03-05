package edu.ucsb.cs56.projects.games.cs56_games_maze;

import java.io.File;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.Exception;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.*;

/**
class to save Games serialized:
Saved Games are stored in a .ser file, when a new game is needs to be saved, the
current saved games are read from the file into an arraylist. The new game that
needs to be saved is put into the arraylist and the arraylist is written back to
the file

@author Zakary Blake
@see MazeGameSave.java
*/
public class MazeGameSaver{

  File myFile=null;
  ArrayList<MazeGameSave> savedMazeGames;

  // specify a fileName when you initialize a MazeGameSaver
  MazeGameSaver(String filename){
    myFile = new File(filename);
  }

  // pass this function an arraylist to
  public boolean writeGameListToFile(ArrayList<MazeGameSave> myGameList) throws IOException{
    FileOutputStream outstream = null;
    ObjectOutputStream oso = null;
    try{
      outstream = new FileOutputStream(myFile);
      oso = new ObjectOutputStream(outstream);
      for (MazeGameSave thisGame:myGameList){
        oso.writeObject(thisGame);
      }
    }catch(IOException e){
      e.printStackTrace();
      return false;
    }finally{
      oso.close();
      return true;
    }
  }
    // check if the file is empty to avoid trying to read from an empty file
    public boolean hasEmptyFile(){
      if (myFile==null)
      {
        System.err.println("*****NULL file");
        return false;
      }
      if (myFile.length()!=0) return false;
      return true;
    }

    // returns the currently saved Games (from the .ser file) in an arraylist
    public ArrayList<MazeGameSave> getSavedGameList() throws IOException{
      //
      MazeGameSave tempGameSave;
      FileInputStream instream = null;
      ObjectInputStream osi = null;


      try{
        System.out.println(">BEGIN");
        instream = new FileInputStream(myFile);
        System.out.println(">END");
        osi = new ObjectInputStream(instream);
        savedMazeGames = new ArrayList<MazeGameSave>();
        // read Game Saves until EOF exception
        while(true){
          try{
            // If there are no more objects to read, EOFEXCEPTION should break loop
            Object x = osi.readObject();
            tempGameSave = (MazeGameSave) x;  // cast object to correct type
            savedMazeGames.add(tempGameSave);  // add objects to the arraylist

          }catch(EOFException e){
            break; // exit while loop because file is over
          }
        }
        // at this point, we should have an arraylist of MazeGameSave objects
        // time to sort
        //MazeScoreCompare mazeScoreCompare = new MazeScoreCompare();
        //Collections.sort(savedMazeGames,mazeScoreCompare); // sort call

      }catch(IOException e){
        System.err.println("read file error");
        return null;
      }finally{
        osi.close();
        System.out.println
        ("Successfully returning GameSave Arraylist of size "+savedMazeGames.size());
        return savedMazeGames;
      }


    }




}
