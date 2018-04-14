package model;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;

import java.util.concurrent.ThreadLocalRandom;

public class DicePairImpl implements DicePair{

    private int numFaces;
    private int dice1;
    private int dice2;

    public DicePairImpl(int dice1, int dice2, int numFaces) {
        // TODO add try catch to prevent dice values outside of numFaces range
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.numFaces = numFaces;
    }

    @Override
    public int getDice1() {
        return dice1;
    }

    @Override
    public int getDice2() {
        return dice2;
    }

    @Override
    public int getNumFaces() {
        return numFaces;
    }

    public String toString() {
        return String.format("Dice 1: %d, Dice 2: %d .. Total: %d", dice1,
                dice2, dice1+dice2);
    }
}
