package com.example.falcon.matchedit;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.OnBoomListener;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.nightonke.boommenu.Util;

import static com.example.falcon.matchedit.BuilderManager.getImageResource;

public class CatogeryMenuActivity extends AppCompatActivity {
    Button animal_catogery ,birds_catogery ;
    String catogery;
    String cATOGERYtWO,NUMBERcatogery;
    BoomMenuButton bmb;
    Handler mHandler;
    Button clicke;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        mHandler = new Handler();
        clicke=(Button)findViewById(R.id.ide);
        clicke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                catogery="animal_catogery";
                Intent  intent =new Intent(CatogeryMenuActivity.this,MainActivity.class);
                intent.putExtra("catogery",catogery);
                startActivity(intent);
                finish();
            }
        });
        initializeBmb3();







    }

    private void initializeBmb3() {
        bmb = (BoomMenuButton) findViewById(R.id.bmb3);
        bmb.setButtonEnum(ButtonEnum.TextOutsideCircle);
//
         bmb.setHighlightedColor(Color.BLUE);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.Share);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.Custom);
        bmb.setCacheOptimization(true);

        for (int i = 0; i < 9; i++) {
            bmb.addBuilder(new TextOutsideCircleButton.Builder()
                    .normalImageRes(BuilderManager.imageResources[i])
                    .normalTextRes(BuilderManager.textResorce[i])
                    .pieceColor(Color.WHITE));



        }

            float w = Util.dp2px(80);
            float h = Util.dp2px(96);
            float h_0_5 = h / 2;
            float h_1_5 = h * 1.5f;

            float hm = bmb.getButtonHorizontalMargin();
            float vm = bmb.getButtonVerticalMargin();
            float vm_0_5 = vm / 2;
            float vm_1_5 = vm * 1.5f;

            bmb.getCustomButtonPlacePositions().add(new PointF(-w - hm, -h_1_5 - vm_1_5));
            bmb.getCustomButtonPlacePositions().add(new PointF(0, -h_1_5 - vm_1_5));
            bmb.getCustomButtonPlacePositions().add(new PointF(+w + hm, -h_1_5 - vm_1_5));
            bmb.getCustomButtonPlacePositions().add(new PointF(-w - hm, -h_0_5 - vm_0_5));
            bmb.getCustomButtonPlacePositions().add(new PointF(0, -h_0_5 - vm_0_5));
            bmb.getCustomButtonPlacePositions().add(new PointF(+w + hm, -h_0_5 - vm_0_5));
            bmb.getCustomButtonPlacePositions().add(new PointF(-w - hm, +h_0_5 + vm_0_5));
            bmb.getCustomButtonPlacePositions().add(new PointF(0, +h_0_5 + vm_0_5));
            bmb.getCustomButtonPlacePositions().add(new PointF(+w + hm, +h_0_5 + vm_0_5));
//            bmb.getCustomButtonPlacePositions().add(new PointF(-w - hm, +h_1_5 + vm_1_5));
//            bmb.getCustomButtonPlacePositions().add(new PointF(0, +h_1_5 + vm_1_5));
//            bmb.getCustomButtonPlacePositions().add(new PointF(+w + hm, +h_1_5 + vm_1_5));


        bmb.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                Intent intent;


            switch (index) {
                    case 0:


                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {


                                catogery="animal_catogery";
                                Intent  intent =new Intent(CatogeryMenuActivity.this,MainActivity.class);
                                intent.putExtra("catogery",catogery);
                                startActivity(intent);
                                finish();
                            }
                        }, 300);
//                        catogery="animal_catogery";
//                        intent =new Intent(CatogeryMenuActivity.this,MainActivity.class);
//                        intent.putExtra("catogery",catogery);
//                        startActivity(intent);
//                        finish();

                        break;
                    case 1:
                        cATOGERYtWO="birds_catogery";
                        intent =new Intent(CatogeryMenuActivity.this,MainActivity.class);
                        intent.putExtra("catogery",cATOGERYtWO);
                        startActivity(intent);
                        finish();
                        break;
                    case 2:
                        catogery="flawer_catogery";
                        intent =new Intent(CatogeryMenuActivity.this,MainActivity.class);
                        intent.putExtra("catogery",catogery);
                        startActivity(intent);
                        finish();
                        break;
                    case 3:

                        catogery="Butterfly_catogery";
                        intent =new Intent(CatogeryMenuActivity.this,MainActivity.class);
                        intent.putExtra("catogery",catogery);
                        startActivity(intent);
                        finish();
                        break;

                    case 4:

                        catogery="animal_catogery";
                        intent =new Intent(CatogeryMenuActivity.this,MainActivity.class);
                        intent.putExtra("catogery",catogery);
                        startActivity(intent);
                        finish();
                        break;

                    case 5:
                        catogery="animal_catogery";
                        intent =new Intent(CatogeryMenuActivity.this,MainActivity.class);
                        intent.putExtra("catogery",catogery);
                        startActivity(intent);
                        finish();
                        break;

                    case 6:
                        String NEWcTOGerY="numbers_catogery";
                        intent =new Intent(CatogeryMenuActivity.this,MainActivity.class);
                        intent.putExtra("catogery","numbers_catogery");
                        startActivity(intent);
                        finish();
                        break;

                    case 7:
                        catogery="animal_catogery";
                        intent =new Intent(CatogeryMenuActivity.this,MainActivity.class);
                        intent.putExtra("catogery",catogery);
                        startActivity(intent);
                        finish();
                        break;

                    case 8:  System.out.println("September"); break;
                    case 9: System.out.println("October"); break;
                    case 10: System.out.println("November"); break;
                    case 11: System.out.println("December"); break;
                    default: System.out.println("Invalid month.");break;
                }


//





            }

            @Override
            public void onBackgroundClick() {

            }

            @Override
            public void onBoomWillHide() {

            }

            @Override
            public void onBoomDidHide() {

            }

            @Override
            public void onBoomWillShow() {

            }

            @Override
            public void onBoomDidShow() {

            }
        });


    }

 }
