package login.video.videologin;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;


public class MainActivity extends AppCompatActivity implements OnPreparedListener, OnPageChangeListener, OnClickListener {

    private VideoView videoHolder;
    private ViewPager pager;
    private ImageView[] dots;
    private int count = 0;
    private int currentItem = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get content size.
        String[] content = this.getResources().getStringArray(R.array.pagerContents);
        this.count = content.length;


        setContentView(R.layout.activity_main);


        //video play fullscreen - Carregamento do arquivo de video no VideoView. Video importado no projeto
        loadStartVideo();

        //IMAGE LOGO
        Uri imageUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.genericlogo);
        ImageView logoImage = (ImageView) findViewById(R.id.logoImgView);
        logoImage.setImageURI(imageUri);

        //SLIDER PAGE
        //intancia e carrega pagaAdapter responsável pelo carregamento dos views a serem exibidos
        ModifyPagerAdapter pagerAdapter = new ModifyPagerAdapter(this,this.count);
        pager = (ViewPager) findViewById(R.id.viewpager_information);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(1);
        //litener para o evento de troca de veiw, utilizaddo para mudar o page ui
        pager.setOnPageChangeListener(this);

        //cria page ui com os circulos brancos
        //Visual pode ser alterado nos arquivos solid_dot e transparent_dot presentes na página drawable
        setUiPageViewController();

        //gera a passagem aútomatica dos pageViews na home
        autoScroll();
    }

    private void loadStartVideo(){

        videoHolder = (VideoView) findViewById(R.id.videoView);
        videoHolder.setOnPreparedListener(this);
        //seleciona o uri do video a partir da pasta raw do projeto presente na pasta res
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.videoplayback2);
        //Carrega video a partir da uri
        videoHolder.setVideoURI(videoUri);
        videoHolder.requestFocus();
        //play video
        videoHolder.start();
    }

    private void setUiPageViewController() {

        dots = new ImageView[count];

        for (int i = 0; i < count; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.transparent_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(10, 0, 10, 0);
            LinearLayout pager_indicator = (LinearLayout) findViewById(R.id.viewPagerDots);

            pager_indicator.addView(dots[i], params);
        }
        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.solid_dot));
    }

    public void autoScroll() {
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                currentItem = currentItem == count ? 1 : (currentItem + 1);
                pager.setCurrentItem(currentItem);
                autoScroll();
            }
        }.start();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < count; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.transparent_dot));
        }

        if (position == 0) {

            pager.setCurrentItem(count, false);
            dots[(count - 1)].setImageDrawable(getResources().getDrawable(R.drawable.solid_dot));
        } else if (position == (count + 1)) {
            pager.setCurrentItem(1, false);
            dots[0].setImageDrawable(getResources().getDrawable(R.drawable.solid_dot));
        } else {
            dots[(position - 1)].setImageDrawable(getResources().getDrawable(R.drawable.solid_dot));
        }


    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


    //gera looping de exeução do video
    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.setLooping(true);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v.getId() == R.id.subscribe_btn) {
            intent = new Intent(this, ConsultActivity.class);
        } else if (v.getId() == R.id.login_btn) {
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);
    }

    //responsavél pela persistência do video ao resumir o app ou navegar
    @Override
    public void onResume(){
        super.onResume();
        loadStartVideo();
    }

    @Override
    public void onPause(){
        super.onPause();
        videoHolder.pause();
    }
}
