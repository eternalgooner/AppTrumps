package com.apptrumps.apptrumps.utils;

import com.apptrumps.apptrumps.model.Card;

import java.util.ArrayList;

/**
 * Created by David on 30-Jul-17.
 */

public class InitCardDeckUtils {
    private static ArrayList<Card> ladsPack;

    public static ArrayList getLadsPack(){
        ladsPack = new ArrayList<>(10);
        ladsPack.add(new Card(1,"Macko", 205, 10, 88, 95, 15, false, "mysterious fellow who keeps to himself"));
        ladsPack.add(new Card(2,"Brady", 203, 12, 80, 85, 25, false, "nice lad who gets things done"));
        ladsPack.add(new Card(3,"Mark", 195, 14, 98, 75, 5, false, "strange boy from the woods"));
        ladsPack.add(new Card(4,"Gar", 194, 15, 85, 65, 20, false, "odd fellow, rarely seen"));
        ladsPack.add(new Card(5,"Tomo", 193, 11, 93, 45, 35, false, "wild boy, always on the move"));
        ladsPack.add(new Card(6,"Kanu", 192, 8, 90, 51, 45, false, "drunken wannabe"));
        ladsPack.add(new Card(7,"Noel", 191, 5, 20, 52, 55, false, "king of the elves"));
        ladsPack.add(new Card(8,"Hogg", 190, 16, 21, 53, 65, true, "since the operation, he's pure swine"));
        ladsPack.add(new Card(9,"O'Hara", 196, 1, 22, 54, 85, false, "what can i say"));
        ladsPack.add(new Card(10,"Stinky", 189, 3, 23, 50, 15, false, "gas masks at the ready"));
        return ladsPack;
    }
}
