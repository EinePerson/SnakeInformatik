package de.igelstudios.game.map;

import java.util.Random;

public enum Food{
    GAP(100,1,0,1,"items.gap");
    public static final String TEXTURE_FILE = "test2.png";
    private static final int max = maxSize();
    private final int food,chance,u,v;
    private final String text;

    private static int maxSize(){
        int i = 0;
        for (Food value : values()) {
            i += value.chance;
        }
        return i;
    }

    Food(int food,int chance,int u,int v,String text){
        this.food = food;
        this.chance = chance;
        this.u = u;
        this.v = v;
        this.text = text;
    }
    
    public static Food getRandom(){
        int i = (int) (Math.random() * max);
        int j = 0;
        for (Food value : values()) {
            j += value.chance;
            if(i <= j)return value;
        }
        return null;
    }

    public int getFood() {
        return food;
    }

    public int getV() {
        return v;
    }

    public int getU() {
        return u;
    }

    public int getChance() {
        return chance;
    }
}
