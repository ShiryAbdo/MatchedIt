package com.example.falcon.matchedit.gameMomary;

import com.example.falcon.matchedit.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by falcon on 20/09/2017.
 */

public class fishData {


    ArrayList<ImagReTag> numberArray =new ArrayList<>();

    ImagReTag image1 = new ImagReTag(R.drawable.fish_one,"Butterfly");
    ImagReTag image2 = new ImagReTag(R.drawable.batr_two,"Butterfly");
    ImagReTag image3 = new ImagReTag(R.drawable.batter_foure,"Butterfly");
    ImagReTag image4 = new ImagReTag(R.drawable.bater_five,"Butterflye");
    ImagReTag image5 = new ImagReTag(R.drawable.bater_six,"Butterfly");


    ImagReTag [] arraImagRtage ={image1,image2,image3,image4,image5};


    int range = arraImagRtage.length;
    int myImgCount = 0;

    Random random = new Random();


    public  ArrayList<ImagReTag> getbutterflyArray() {

        for( int i = 0; i < range; ++i)
        {
            myImgCount = random.nextInt(range);
            if(!numberArray.contains(arraImagRtage[myImgCount])){
                numberArray.add(arraImagRtage[myImgCount]);
            }
        }

        return numberArray;
    }
}
