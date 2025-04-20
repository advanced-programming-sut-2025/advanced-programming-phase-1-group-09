package models.GameWorld.Items;

import models.GameWorld.Items.Edibles.Edible;

import java.util.ArrayList;

public class Refrigerator {
    private final ArrayList<Edible> edibles;

    public Refrigerator(ArrayList<Edible> edibles) {
        this.edibles = edibles;
    }
}
