package edu.ucsb.cs56.projects.games.cs56_games_maze;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/** Serializable container for holding all necessary game state data.
    Used for both in-progress saves and end-of-game saves.
    In case of in-progress saves, saves player position and time elapsed.
    Always saves maze configuration and associated settings.

@author Evan West
@author Zak Blake
@version CS56 S13 UCSB
 */
public class MazeGameSave implements Serializable{

    private MazeGrid grid;
    private MazeSettings settings;
    private MazePlayer player;
    private long timeElapsed;
    private MazeHighScore currentMazeHighScore;
    private String storageTag;


    /** Constructor for save game object, player moved back to start, use for win state
	@param grid Object representing the maze layout grid
	@param settings Settings which the grid must be played with
     */
    public MazeGameSave(MazeGrid grid, MazeSettings settings)
    {
	this(grid,settings,new MazePlayer(grid),0);
    }

    /** Constructor for save game object, stores player position
    @param grid Object representing the maze layout grid
    @param settings Settings which the grid must be played with
    @param player Player position and state to store
    @param elapsed Time elapsed during current gameplay instance
    */
    public MazeGameSave(MazeGrid grid, MazeSettings settings, MazePlayer player, long timeElapsed)
    {
      this.grid = grid;
      this.settings = settings;
      this.player = player;
      this.currentMazeHighScore = new MazeHighScore("");
      this.timeElapsed=timeElapsed;
    }
    /** Constructor for save game object, stores player position
	@param grid Object representing the maze layout grid
	@param settings Settings which the grid must be played with
	@param player Player position and state to store
	@param elapsed Time elapsed during current gameplay instance
  @param tag to identify game to user when load is chosen
     */
    public MazeGameSave(MazeGrid grid, MazeSettings settings, MazePlayer player, long timeElapsed, String sTag)
    {
	this.grid = grid;
	this.settings = settings;
	this.player = player;
  this.currentMazeHighScore = new MazeHighScore(""); // create a high score with no name as a place holder
  this.timeElapsed=timeElapsed;
  this.storageTag= sTag;
    }

    public boolean hasHighScores()
    {
      if (this.currentMazeHighScore.getName() == "")
      {
        return false;
      }
      else
      {
        return true;
      }
    }


    /**
       @return MazeGrid object representing layout/state of maze
    */
    public MazeGrid getGrid(){
	return this.grid;
    }

    /**
       @return MazePlayer object representing the current player position for in-progress saves or starting position for finished saves.
     */
    public MazePlayer getPlayer(){
	return this.player;
    }

    /**
       @return settings for this saved game
     */
    public MazeSettings getSettings(){
	return this.settings;
    }

    /**
       @return MazeHighScore representing highest score saved in this file
     */
    public MazeHighScore getHighScore(){
	     return this.currentMazeHighScore;
    }

    /**
       @return milliseconds elapsed for in-progress saves
     */
    public long getTimeElapsed(){
	return this.timeElapsed;
    }

    /**
       Sets time elapsed in this file, set to 0 for not-in-progress saves
       @param elapsed Milliseconds elapsed during this game session
     */
    public void setTimeElapsed(long elapsed){
	this.timeElapsed=elapsed;
    }
    // compare score achieved on this maze to current score and change if needed
    public boolean checkSetNewMazeHighScore(MazeHighScore newHS){
        // check if a) there is no previous score saved or b) the new score is better
      if (this.currentMazeHighScore.getName() == "" || newHS.getTime() < this.currentMazeHighScore.getTime()){
        this.currentMazeHighScore = newHS;
        return true;
      }
      else{
        return false;
      }
    }

    /**
       Resets player position to maze start as per settings
     */
    public void resetPlayer(){
	this.player = new MazePlayer(this.grid);
    }
}
