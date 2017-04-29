package homedepot.com.primenumber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by varun on 27/04/17.
 */

public class SplashActivity extends Activity implements Animation.AnimationListener {

    private static String TAG = "SplashActivity";
    private ImageView mSplashImage;
    private TextView mAppName;
    private Animation mAnimZoomIn, mAnimZoomOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);
        mSplashImage = (ImageView) findViewById(R.id.splash_image);
        mAppName = (TextView) findViewById(R.id.ic_app_name);
        mAnimZoomIn = AnimationUtils.loadAnimation(this, R.anim.anim_zoomin);
        mAnimZoomOut = AnimationUtils.loadAnimation(this, R.anim.anim_zoomout);

        mSplashImage.setAnimation(mAnimZoomIn);

        mAnimZoomIn.setAnimationListener(this);
        mAnimZoomIn.start();
    }

    @Override
    public void onAnimationEnd(Animation animation) {

        if (animation == mAnimZoomIn) {
            mSplashImage.setAnimation(mAnimZoomOut);
            mAnimZoomOut.setAnimationListener(this);
            mAnimZoomOut.start();


        } else if (animation == mAnimZoomOut) {
            mAppName.setVisibility(View.VISIBLE);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
