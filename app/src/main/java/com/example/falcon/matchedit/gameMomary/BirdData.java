package com.example.falcon.matchedit.gameMomary;

import com.example.falcon.matchedit.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by falcon on 16/09/2017.
 */

public class BirdData {

    ArrayList<ImagReTag> BirdDataArray =new ArrayList<>();

    ImagReTag image1 = new ImagReTag(R.drawable.brid_one,"brid_one");
    ImagReTag image2 = new ImagReTag(R.drawable.bird_two,"bird_two");
    ImagReTag image3 = new ImagReTag(R.drawable.bird_three,"bird_three");
    ImagReTag image4 = new ImagReTag(R.drawable.bird_fuor,"bird_fuor");
    ImagReTag image5 = new ImagReTag(R.drawable.bird_six,"bird_six");
    ImagReTag image6 = new ImagReTag(R.drawable.bird_five,"bird_five");
    ImagReTag image7 = new ImagReTag(R.drawable.bird_seven,"bird_seven");


    ImagReTag [] arraImagRtage ={image1,image2,image3,image4,image5,image6,image7};


    int range = arraImagRtage.length;
    int myImgCount = 0;

    Random random = new Random();


    public  ArrayList<ImagReTag> getBirdDataArrayArray() {

        for( int i = 0; i < range; ++i)
        {
            myImgCount = random.nextInt(range);
            if(!BirdDataArray.contains(arraImagRtage[myImgCount])){
                BirdDataArray.add(arraImagRtage[myImgCount]);
            }
        }

        return BirdDataArray;
    }
}
