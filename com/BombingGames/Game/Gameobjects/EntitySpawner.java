package com.BombingGames.Game.Gameobjects;

import com.BombingGames.EngineCore.Controller;
import com.BombingGames.EngineCore.Map.Coordinate;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *The entitty spawner spawns an entity when a character steps on it.
 * @author Benedikt Vogler
 */
public class EntitySpawner extends Block implements IsSelfAware {
    private Coordinate coords;//this field is needed because of it is selfAware

    protected EntitySpawner(int id, Coordinate coords){
        super(id);
        if (coords == null) throw new NullPointerException("No coordinates given to EntitySpawner during creation."); 
        this.coords = coords;
        setObstacle(true);
    }
    

    @Override
    public void update(int delta) {
        int[] coordsOnTop = coords.addVectorCpy(0, 0, 1).getRel();
        
        //get every character
        ArrayList<AbstractCharacter> entitylist;
        entitylist = Controller.getMap().getAllEntitysOfType(AbstractCharacter.class);
        
        //check every character if standing on top
        int i = 0;
        while (i < entitylist.size() && !Arrays.equals( entitylist.get(i).getCoords().getRel(), coordsOnTop)){
            i++;
        }
        
        if (i < entitylist.size() && Arrays.equals(entitylist.get(i).getCoords().getRel(), coordsOnTop))
            trigger();
    }

    @Override
    public Coordinate getCoords() {
        return coords;
    }

    @Override
    public void setCoords(Coordinate coords) {
        this.coords = coords;
    }

    private void trigger() {
        Gdx.app.log("debug", "trigger des entitty spawners");
    }
    
    
    
    
    
}