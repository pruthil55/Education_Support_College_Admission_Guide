package com.example.loginfbgooglenumber;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.material.textfield.TextInputLayout;

public class AcpcRankPredictor extends AppCompatActivity {

    TextInputLayout etPcm, etGujcet;
    Button btnCalculate;
    TextView tvRank;

    int rowGujcet = 0, columnPcm = 0;
    String gujcetStatus;
    String pcmStatus;
    int[][] rank;
    int[] pcm;
    int[] gujcet;
    int gujcetMarks;
    int pcmMarks;

    ImageView iv_close_predictor;
    LinearLayout linear_layout_predictor;

    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudienceNetworkAds.initialize(this);
        setContentView(R.layout.activity_acpc_rank_predictor);

        etPcm = (TextInputLayout) findViewById(R.id.et_pcm_marks);
        etGujcet = (TextInputLayout) findViewById(R.id.et_gujcet_marks);
        btnCalculate = (Button) findViewById(R.id.btn_predict);
        tvRank = (TextView) findViewById(R.id.tv_predicted_rank);
        iv_close_predictor = (ImageView) findViewById(R.id.iv_close_predictor);
        linear_layout_predictor = (LinearLayout) findViewById(R.id.linear_layout_predictor);


        adView = new AdView(this, "250531626269097_251872259468367", AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adContainer.addView(adView);
        adView.loadAd();


        iv_close_predictor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AcpcRankPredictor.this, LogoutActivityDrawer.class));
                finish();
            }
        });

        gujcet = new int[]{115, 112, 109, 106, 103, 100, 95, 90, 85, 80, 75, 70, 65, 60, 55, 50, 45, 40,
                35, 30};

        pcm = new int[]{285, 280, 275, 270, 265, 260, 255, 250, 245, 240, 235, 230, 225, 220, 215, 210,
                205, 200, 195, 190, 185, 180, 175, 170, 165, 160, 155, 150};

        rank = new int[][]{{30, 60, 215, 230, 240, 310, 540, 720, 970, 1420, 1710, 2225, 2630, 3350, 4020, 4795, 5575, 6780, 7965, 9470},
                {220, 225, 235, 245, 300, 350, 555, 830, 1125, 1470, 1840, 2325, 2720, 3390, 4040, 4805, 5835, 6790, 7990, 9575},
                {250, 280, 325, 340, 525, 555, 680, 945, 1280, 1505, 1955, 2450, 2930, 3600, 4205, 5000, 5910, 6830, 8140, 9800},
                {520, 530, 535, 550, 560, 655, 890, 1135, 1440, 1680, 2120, 2590, 3150, 3785, 4320, 5105, 5985, 7010, 8320, 9855},
                {595, 625, 665, 690, 775, 910, 1120, 1400, 1530, 1950, 2375, 2760, 3255, 3875, 4570, 5490, 6300, 7255, 8650, 10045},
                {920, 930, 960, 1115, 1130, 1250, 1430, 1565, 1920, 2300, 2610, 3175, 3670, 4195, 4810, 5570, 6500, 7520, 8845, 10375},
                {1320, 1350, 1370, 1410, 1460, 1490, 1670, 1945, 2275, 2580, 2980, 3420, 3885, 4540, 5125, 5960, 6820, 7845, 9360, 10625},
                {1520, 1555, 1575, 1660, 1770, 1880, 2010, 2350, 2585, 2940, 3360, 3835, 4220, 4820, 5535, 6325, 7130, 8275, 9475, 10990},
                {1940, 1960, 1970, 2050, 2080, 2250, 2425, 2615, 2950, 3245, 3765, 4180, 4690, 5145, 5945, 6775, 7500, 8725, 9850, 11320},
                {2400, 2420, 2430, 2460, 2465, 2595, 2780, 3100, 3380, 3795, 4045, 4545, 5050, 5550, 6320, 7005, 7975, 8990, 10210, 11710},
                {2750, 2770, 2795, 2920, 2970, 3020, 3220, 3540, 3845, 4055, 4470, 4965, 5520, 6000, 6785, 7415, 8365, 9460, 10600, 12040},
                {3200, 3210, 3230, 3370, 3410, 3435, 3775, 3890, 4210, 4555, 4950, 5500, 5915, 6480, 7100, 7960, 8840, 9845, 11045, 12570},
                {3805, 3815, 3825, 3855, 3865, 4000, 4185, 4380, 4710, 5020, 5510, 5905, 6335, 6960, 7600, 8445, 9435, 10275, 11470, 12940},
                {4190, 4200, 4215, 4250, 4310, 4435, 4700, 4935, 5115, 5525, 5935, 6330, 6800, 7385, 8045, 8860, 9830, 10650, 11875, 13340},
                {4715, 4720, 4745, 4800, 4825, 4980, 5120, 5505, 5610, 5975, 6350, 6810, 7270, 7970, 8750, 9455, 10235, 11235, 12435, 13870},
                {5200, 5475, 5485, 5495, 5515, 5530, 5800, 5955, 6290, 6525, 6970, 7350, 7955, 8550, 9400, 9885, 10725, 11755, 12960, 14490},
                {5870, 5905, 5925, 5950, 5965, 6025, 6310, 6510, 6795, 7115, 7495, 7985, 8465, 9210, 9820, 10550, 11360, 12310, 13465, 14900},
                {6340, 6345, 6360, 6490, 6520, 6770, 6805, 7015, 7285, 7705, 8050, 8600, 9150, 9585, 10245, 11020, 11865, 12760, 14115, 15475},
                {6980, 6990, 7000, 7060, 7120, 7240, 7400, 7695, 7980, 8310, 8800, 9310, 9605, 10175, 10805, 11625, 12400, 13400, 14580, 16190},
                {7540, 7570, 7690, 7700, 7710, 7810, 8000, 8300, 8700, 8950, 9445, 9810, 10185, 10700, 11400, 12180, 13000, 14065, 15190, 16485},
                {8235, 8255, 8290, 8315, 8395, 8455, 8775, 8960, 9420, 9530, 9870, 10300, 10755, 11375, 11990, 12660, 13670, 14530, 15730, 17100},
                {8850, 8855, 8940, 8970, 9040, 9260, 9440, 9565, 9840, 10105, 10570, 10870, 11385, 11970, 12620, 13250, 14160, 15130, 16290, 17585},
                {9480, 9485, 9570, 9580, 9645, 9835, 9900, 10125, 10525, 10675, 11200, 11605, 12000, 12610, 13220, 14030, 14770, 15900, 16815, 18295},
                {10165, 10195, 10225, 10285, 10450, 10490, 10590, 10855, 11125, 11435, 11850, 12250, 12625, 13240, 13895, 14585, 15465, 16380, 17550, 18830},
                {10860, 10865, 10980, 11010, 11095, 11175, 11350, 11595, 11770, 12155, 12590, 12920, 13370, 13980, 14575, 15235, 16260, 16970, 18225, 19555},
                {11615, 11635, 11650, 11680, 11745, 11840, 11980, 12205, 12580, 12735, 13185, 13710, 14135, 14635, 15225, 16130, 16680, 17660, 18770, 20050},
                {12325, 12350, 12365, 12375, 12455, 12600, 12685, 12980, 13230, 13535, 13960, 14390, 14850, 15455, 16160, 16645, 17495, 18315, 19410, 20750},
                {13025, 13050, 13075, 13135, 13210, 13245, 13445, 13800, 14085, 14230, 14655, 15060, 15480, 16220, 16660, 17295, 18175, 19035, 20110, 21425}};


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validatePcmMarks() | !validateGujcetMarks())
                {
                    return;
                }

                linear_layout_predictor.setVisibility(View.VISIBLE);

                gujcetMarks = Integer.parseInt(etGujcet.getEditText().getText().toString().trim());
                pcmMarks = Integer.parseInt(etPcm.getEditText().getText().toString().trim());

                InputMethodManager inputManager = (InputMethodManager) getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(etGujcet.getWindowToken(), 0);

                for (int i = 0; i < gujcet.length; i++) {
                    if (gujcetMarks > gujcet[i]) {
                        rowGujcet = i;
                        gujcetStatus = "between";
                        break;
                    }
                    if (gujcetMarks < gujcet[i] && i == gujcet.length - 1) {
                        rowGujcet = i;
                        gujcetStatus = "between";
                        break;
                    }
                    if (gujcetMarks == gujcet[i]) {
                        rowGujcet = i;
                        gujcetStatus = "equal";
                        break;
                    }
                }

                for (int j = 0; j < pcm.length; j++) {
                    if (pcmMarks > pcm[j]) {
                        columnPcm = j;
                        pcmStatus = "between";
                        break;
                    }
                    if (pcmMarks < pcm[j] && j == pcm.length - 1) {
                        columnPcm = j;
                        pcmStatus = "between";
                        break;
                    }
                    if (pcmMarks == pcm[j]) {
                        columnPcm = j;
                        pcmStatus = "equal";
                        break;
                    }
                }
                if (pcmStatus == "equal" && gujcetStatus == "equal") {
                    tvRank.setText(String.valueOf(rank[columnPcm][rowGujcet]));
                }
                if (pcmStatus == "equal" && gujcetStatus == "between") {
                    if (rowGujcet != 0 && rowGujcet != gujcet.length - 1) {
                        tvRank.setText("Between " + String.valueOf(rank[columnPcm][rowGujcet - 1]) + " to " + String.valueOf(rank[columnPcm][rowGujcet]));
                    } else if (rowGujcet == gujcet.length - 1) {
                        tvRank.setText("Above " + String.valueOf(rank[columnPcm][rowGujcet]));
                    } else {
                        tvRank.setText("Below " + String.valueOf(rank[columnPcm][rowGujcet]));
                    }
                }

                if (pcmStatus == "between" && gujcetStatus == "equal") {
                    if (columnPcm != 0 && columnPcm != pcm.length - 1) {
                        tvRank.setText("Between " + String.valueOf(rank[columnPcm - 1][rowGujcet]) + " to " + String.valueOf(rank[columnPcm][rowGujcet]));
                    }
                    if (columnPcm == pcm.length - 1) {
                        tvRank.setText("Above " + String.valueOf(rank[columnPcm][rowGujcet]));

                    } else {
                        tvRank.setText("Below " + String.valueOf(rank[columnPcm][rowGujcet]));
                    }
                }

                if (pcmStatus == "between" && gujcetStatus == "between") {
                    if (columnPcm != 0 && rowGujcet != 0 && columnPcm != pcm.length - 1 && columnPcm != pcm.length - 1) {
                        tvRank.setText("Between " + String.valueOf(rank[columnPcm - 1][rowGujcet - 1]) + " to " + String.valueOf(rank[columnPcm][rowGujcet]));
                    } else if (columnPcm == 0 && rowGujcet != 0 && rowGujcet != gujcet.length - 1) {
                        tvRank.setText("Between " + String.valueOf(rank[columnPcm][rowGujcet - 1]) + " to " + String.valueOf(rank[columnPcm][rowGujcet]));
                    } else if (rowGujcet == 0 && columnPcm != 0 && columnPcm != pcm.length - 1) {
                        tvRank.setText("Between " + String.valueOf(rank[columnPcm - 1][rowGujcet]) + " to " + String.valueOf(rank[columnPcm][rowGujcet]));
                    } else if (rowGujcet == 0 && columnPcm == 0) {
                        tvRank.setText("Below " + String.valueOf(rank[columnPcm][rowGujcet]));
                    } else if (rowGujcet == gujcet.length - 1 && columnPcm == pcm.length - 1) {
                        tvRank.setText("Above " + String.valueOf(rank[columnPcm][rowGujcet]));
                    } else if (columnPcm == 0 && rowGujcet == gujcet.length - 1) {
                        tvRank.setText("Above " + String.valueOf(rank[columnPcm][rowGujcet]));
                    } else if (columnPcm == pcm.length - 1 && rowGujcet == 0) {
                        tvRank.setText("Above " + String.valueOf(rank[columnPcm][rowGujcet]));
                    }
                }


            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AcpcRankPredictor.this, LogoutActivityDrawer.class));
        finish();
    }

    public boolean validatePcmMarks()
    {
        String pcmMarks = etPcm.getEditText().getText().toString().trim();

        if (pcmMarks.isEmpty()) {
            etPcm.setError("Field can't be empty");
            return false;
        }
        else if (Integer.parseInt(pcmMarks) > 300) {
            etPcm.setError("PCM Marks should be less than 300!");
            return false;
        }
        else {
            etPcm.setError(null);
            return true;
        }
    }

    public boolean validateGujcetMarks()
    {
        String gujcetMarks = etGujcet.getEditText().getText().toString().trim();

        if (gujcetMarks.isEmpty()) {
            etGujcet.setError("Field can't be empty");
            return false;
        }
        else if (Integer.parseInt(gujcetMarks) > 120) {
            etGujcet.setError("GUJCET Marks should be less than 120!");
            return false;
        }
        else {
            etGujcet.setError(null);
            return true;
        }
    }

}
