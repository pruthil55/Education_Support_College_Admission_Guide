package com.example.loginfbgooglenumber;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ViewBooks extends AppCompatActivity {


    Button btn11,btn12,btn13,btn14,btn15,btn16;
    Button btn21,btn22,btn23,btn24,btn25;
    Button btn31,btn32,btn33,btn34,btn35,btn36;
    Button btn41,btn42,btn43,btn44;
    Button btn51,btn52,btn53,btn54;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);


        btn11=findViewById(R.id.btn11);
        btn12=findViewById(R.id.btn12);
        btn13=findViewById(R.id.btn13);
        btn14=findViewById(R.id.btn14);
        btn15=findViewById(R.id.btn15);
        btn16=findViewById(R.id.btn16);

        btn21=findViewById(R.id.btn21);
        btn22=findViewById(R.id.btn22);
        btn23=findViewById(R.id.btn23);
        btn24=findViewById(R.id.btn24);
        btn25=findViewById(R.id.btn25);


        btn31=findViewById(R.id.btn31);
        btn32=findViewById(R.id.btn32);
        btn33=findViewById(R.id.btn33);
        btn34=findViewById(R.id.btn34);
        btn35=findViewById(R.id.btn35);
        btn36=findViewById(R.id.btn36);

        btn41=findViewById(R.id.btn41);
        btn42=findViewById(R.id.btn42);
        btn43=findViewById(R.id.btn43);
        btn44=findViewById(R.id.btn44);


        btn51=findViewById(R.id.btn51);
        btn52=findViewById(R.id.btn52);
        btn53=findViewById(R.id.btn53);
        btn54=findViewById(R.id.btn54);

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/TEXTBOOK-ENGINEERING-MATHEMATICS-I-SEMESTER-POLYTECHNIC/dp/B073NHBW87/ref=sr_1_3?crid=3EQQ630LPHVV9&keywords=engineering+mathematics+1st+semester&qid=1569736935&s=gateway&sprefix=engineering+1st+maths%2Caps%2C309&sr=8-3"));
                startActivity(i);
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Introduction-Engineering-Mathematics-Vol-1-GBTU-Dass-ebook/dp/B00QUYKSWA/ref=sr_1_4?crid=3EQQ630LPHVV9&keywords=engineering+mathematics+1st+semester&qid=1569737062&s=gateway&sprefix=engineering+1st+maths%2Caps%2C309&sr=8-4"));
                startActivity(i);
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Textbook-Engineering-Mathematics-UPTU-Sem-I/dp/9383828633/ref=sr_1_6?crid=3EQQ630LPHVV9&keywords=engineering+mathematics+1st+semester&qid=1569736856&s=gateway&sprefix=engineering+1st+maths%2Caps%2C309&sr=8-6"));
                startActivity(i);
            }
        });

        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Engineering-Mathematics-I-V-Dubewar/dp/0190124148/ref=sr_1_15?crid=3EQQ630LPHVV9&keywords=engineering+mathematics+1st+semester&qid=1569737285&s=gateway&sprefix=engineering+1st+maths%2Caps%2C309&sr=8-15"));
                startActivity(i);
            }
        });

        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Engineering-Mathematics-Sem-Be-Vtu/dp/B07JLSF7X9/ref=sr_1_17?crid=3EQQ630LPHVV9&keywords=engineering+mathematics+1st+semester&qid=1569736856&s=gateway&sprefix=engineering+1st+maths%2Caps%2C309&sr=8-17"));
                startActivity(i);
            }
        });

        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Engineering-Mathematics-2nd-Sem-Vtu/dp/B07JMR4V8P/ref=sr_1_18?crid=3EQQ630LPHVV9&keywords=engineering+mathematics+1st+semester&qid=1569736856&s=gateway&sprefix=engineering+1st+maths%2Caps%2C309&sr=8-18"));
                startActivity(i);
            }
        });

        //c language book

        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Programming-Language-Kernighan-Dennis-Ritchie/dp/9332549443/ref=sr_1_1?crid=1A62QZS9AB8KB&keywords=c+language+books+for+beginners&qid=1569744203&s=gateway&smid=AT95IG9ONZD7S&sprefix=c+langeuge+%2Caps%2C369&sr=8-1"));
                startActivity(i);
            }
        });

        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Programming-Language-Beginners-Guide-Learn-ebook/dp/B01H0LBF9Q/ref=sr_1_2?crid=1A62QZS9AB8KB&keywords=c+language+books+for+beginners&qid=1569744304&s=gateway&sprefix=c+langeuge+%2Caps%2C369&sr=8-2"));
                startActivity(i);
            }
        });

        btn23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Let-Us-16TH-Yashavant-Kanetkar/dp/9387284492/ref=sr_1_3?crid=1A62QZS9AB8KB&keywords=c+language+books+for+beginners&qid=1569744351&s=gateway&smid=AT95IG9ONZD7S&sprefix=c+langeuge+%2Caps%2C369&sr=8-3"));
                startActivity(i);
            }
        });

        btn24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Computing-Fundamentals-C-Programming-Balagurusamy/dp/9352604164/ref=sr_1_2?crid=2F57ZEDLCE1QS&keywords=c+language+books+balagurusamy&qid=1569744576&s=gateway&sprefix=c+language+books+balaguru%2Caps%2C713&sr=8-2"));
                startActivity(i);
            }
        });

        btn25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Computer-Fundamentals-Programming-Pradip-Dey/dp/0198084560/ref=sr_1_7?crid=1A62QZS9AB8KB&keywords=c+language+books+for+beginners&qid=1569744437&s=gateway&sprefix=c+langeuge+%2Caps%2C369&sr=8-7"));
                startActivity(i);
            }
        });


        //c++

        btn31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Object-Oriented-Programming-C-Old/dp/125902993X/ref=sr_1_2?crid=6PZSDA60GHR9&keywords=c%2B%2B+balaguruswamy&qid=1569744701&s=gateway&sprefix=c%2B%2B+bala%2Caps%2C616&sr=8-2"));
                startActivity(i);
            }
        });

        btn32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Problem-Solving-Pearson-Savitch-Walter/dp/9352863089/ref=sr_1_3?keywords=c%2B%2B+language+books&qid=1569744754&s=gateway&sr=8-3"));
                startActivity(i);
            }
        });

        btn33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Programming-Language-1e-third/dp/8131705218/ref=sr_1_5?keywords=c%2B%2B+language+books&qid=1569744818&s=gateway&sr=8-5"));
                startActivity(i);
            }
        });

        btn34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Programming-Absolute-Beginners-Step-Step-ebook/dp/B01N6YVZBS/ref=sr_1_7?keywords=c%2B%2B+language+books&qid=1569744878&s=gateway&sr=8-7"));
                startActivity(i);
            }
        });

        btn35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Let-Us-Yashavant-P-Kanetkar/dp/8176561061/ref=sr_1_13?keywords=c%2B%2B+language+books&qid=1569744931&s=gateway&sr=8-13"));
                startActivity(i);
            }
        });

        btn36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Programming-Language-Beginners-Javascript-Python-ebook/dp/B00WTR45LW/ref=pd_rhf_se_s_pd_crcd_0_4/262-0107123-5172255?_encoding=UTF8&pd_rd_i=B00WTR45LW&pd_rd_r=8b1c6793-b30c-4776-9633-1b5962c5307d&pd_rd_w=tRPXr&pd_rd_wg=629qa&pf_rd_p=95c41a1b-cd60-437d-8499-216c589e59c6&pf_rd_r=3NJ7WQTVHDDJZCX9HDFA&psc=1&refRID=3NJ7WQTVHDDJZCX9HDFA"));
                startActivity(i);
            }
        });

        //electrical

        btn41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Basic-Electrical-Engineering-Mehta-Rohit-ebook/dp/B06XKML8ST/ref=sr_1_3?crid=TEDZWO5JGISW&keywords=electrical+engineering+books&qid=1569745152&s=digital-text&smid=A3SI5I1JWQ14PC&sprefix=electricsl+%2Cdigital-text%2C290&sr=1-3"));
                startActivity(i);
            }
        });

        btn42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/BASIC-ELECTRICAL-ENGINEERING-VISVESVARAYA-TECHNOLOGICAL-ebook/dp/B07FXXQ1DH/ref=pd_sbs_351_3/262-0107123-5172255?_encoding=UTF8&pd_rd_i=B07FXXQ1DH&pd_rd_r=06a20bed-2825-4678-a3b6-68cd8ac05237&pd_rd_w=3PTum&pd_rd_wg=WS9WS&pf_rd_p=b5c4df7b-dc8c-47a6-947a-01271326b67b&pf_rd_r=YPS96X9A0TG20G4HNMFJ&psc=1&refRID=YPS96X9A0TG20G4HNMFJ"));
                startActivity(i);
            }
        });

        btn43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Basics-Electrical-Engineering-Er-Mathew-ebook/dp/B07QGFKKVL/ref=pd_sbs_351_3/262-0107123-5172255?_encoding=UTF8&pd_rd_i=B07QGFKKVL&pd_rd_r=f372c338-5e6f-40af-832d-769c9000328e&pd_rd_w=tlXMs&pd_rd_wg=jkF5l&pf_rd_p=b5c4df7b-dc8c-47a6-947a-01271326b67b&pf_rd_r=P3S1JMVBW3ZK3DBE023C&psc=1&refRID=P3S1JMVBW3ZK3DBE023C"));
                startActivity(i);
            }
        });

        btn44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Basic-Electrical-Electronics-Engineering-engineering-ebook/dp/B07KV5S5XL/ref=pd_sbs_351_17?_encoding=UTF8&pd_rd_i=B07KV5S5XL&pd_rd_r=ce437cd2-b2fd-4918-ba3c-1c46c2871a2b&pd_rd_w=milqd&pd_rd_wg=yTX5q&pf_rd_p=b5c4df7b-dc8c-47a6-947a-01271326b67b&pf_rd_r=J6BF5YTE0QRZV4M9C8NW&psc=1&refRID=J6BF5YTE0QRZV4M9C8NW"));
                startActivity(i);
            }
        });



        //environment

        btn51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Environmental-Engineering-D-Srinivasan-ebook/dp/B00K7YG1F2/ref=sr_1_4?keywords=environment+engineering&qid=1569745391&s=digital-text&sr=1-4"));
                startActivity(i);
            }
        });

        btn52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Environmental-Engineering-N-S-Varandani-ebook/dp/B073TZTJSP/ref=pd_sbs_351_3/262-0107123-5172255?_encoding=UTF8&pd_rd_i=B073TZTJSP&pd_rd_r=e0a1c06f-edb7-4af1-a4c6-1f9f061c0472&pd_rd_w=AxocJ&pd_rd_wg=JBqLL&pf_rd_p=b5c4df7b-dc8c-47a6-947a-01271326b67b&pf_rd_r=MAAQR4A4PDWA6N9TBJNR&psc=1&refRID=MAAQR4A4PDWA6N9TBJNR"));
                startActivity(i);
            }
        });

        btn53.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/TEXTBOOK-ENVIRONMENTAL-ENGINEERING-Venugopala-Rao-ebook/dp/B00K7YGXUU/ref=pd_sbs_351_1/262-0107123-5172255?_encoding=UTF8&pd_rd_i=B00K7YGXUU&pd_rd_r=fc7f145d-cd22-4e18-9e9b-66eec3d34f56&pd_rd_w=MxlC2&pd_rd_wg=BWS3s&pf_rd_p=b5c4df7b-dc8c-47a6-947a-01271326b67b&pf_rd_r=WJXXBMXR4ZGQ8YZGB4X3&psc=1&refRID=WJXXBMXR4ZGQ8YZGB4X3"));
                startActivity(i);
            }
        });

        btn54.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Elements-Environmental-Engineering-K-Dugal-ebook/dp/B00QUZM9U8/ref=pd_sbs_351_3/262-0107123-5172255?_encoding=UTF8&pd_rd_i=B00QUZM9U8&pd_rd_r=c912c83a-e3a5-4ef6-b9a9-3bef0a3f49f1&pd_rd_w=0dGYe&pd_rd_wg=ZzRuS&pf_rd_p=b5c4df7b-dc8c-47a6-947a-01271326b67b&pf_rd_r=93EW5B3VNJJ0TPYRJDYX&psc=1&refRID=93EW5B3VNJJ0TPYRJDYX"));
                startActivity(i);
            }
        });

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(ViewBooks.this, LogoutActivityDrawer.class));
        finish();
    }

}
