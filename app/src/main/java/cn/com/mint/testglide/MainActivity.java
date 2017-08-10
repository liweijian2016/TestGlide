package cn.com.mint.testglide;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.ArrayList;

import static com.bumptech.glide.Glide.with;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Test {

    private static final String TAG = "MainActivity";
    private TextView mTextView;
    private TextView mTestLambda;
    private ImageView mImageView;
    private Intent mIntent;
    private ArrayList<String> wechats; // 所有图片的路径.
    private RequestOptions mOptions;
    private RequestOptions mOptions1;
    private RequestOptions mOptions2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化控件.
        initView();
        // 初始化数据.
        initData();
        // 初始化监听.
        initListener();
        mOptions = new RequestOptions()
                .centerCrop()
                .dontAnimate()
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.DATA);
        mOptions1 = new RequestOptions()
                .override(300, 300)
//                .getOverrideWidth()
//                .fitCenter()
//                .circleCrop()
                .optionalCircleCrop()
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        mOptions2 = new RequestOptions()
                .centerInside()
                .override(500, 500)
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        mIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }

    private void initListener() {
        mTestLambda.setOnClickListener(view ->
                        with(this)
                                .load(ImageConfig.URL_WEBP) // URL_WEBP
                                .thumbnail(with(this).load(ImageConfig.URL_JPG)) // RequestBuilder.
                                .apply(mOptions1)
//                        .apply(mOptions)
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        Log.e("onLoadFailed", "onLoadFailed --->" + e);
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                        // onResourceReady --->com.bumptech.glide.load.resource.gif.GifDrawable@bc5bb72
                                        Log.e("onResourceReady", "onResourceReady --->" + resource);
                                        return false;
                                    }
                                })
                                .into(mImageView)
        );
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.mTextView);
        mImageView = (ImageView) findViewById(R.id.mImageView);
        mTestLambda = (TextView) findViewById(R.id.testLambda);
    }

    private void initData() {
        wechats = new ArrayList<>();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mTextView:
                with(this)
                        .load(ImageConfig.URL_GIF) // URL_WEBP
                        .thumbnail(with(this).load(ImageConfig.URL_JPG)) // RequestBuilder.
                        .apply(mOptions)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                Log.e("onLoadFailed", "onLoadFailed --->" + e);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                // onResourceReady --->com.bumptech.glide.load.resource.gif.GifDrawable@bc5bb72
                                Log.e("onResourceReady", "onResourceReady --->" + resource);
                                return false;
                            }
                        })
                        .into(mImageView);
                break;
            case R.id.mImageView:
//                startActivity(mIntent);
//                File file = new File("/storage/emulated/0/tencent/MobileQQ/Scribble/Config/gif/gif_zip_tmp5/gif_orig_5pre.png");
//                recursion(Environment.getExternalStorageDirectory());
//                recursion(file);
//                test(Environment.getRootDirectory());
                // 这个可以是任何Uri. 这里为了演示，我们只创建了一个指向桌面图标的Uri
                Glide.with(this).asFile();
                Glide.with(this)
                        .asGif()
                        .apply(mOptions2)
                        .transition(new DrawableTransitionOptions().crossFade(2000))
                        .load(ImageConfig.URL_GIF)
                        .into(mImageView);
                break;
                /*
                style 1: 加载uri.
                Uri uri = resourceIdToUri(this, R.mipmap.ic_launcher_round);
                Glide.with(this)
                        .asBitmap()
                        .apply(mOptions)
                        .load(uri)
                        .into(mImageView);
                */
        }
    }

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
        // android.resource://<packageName>/R.mipmap.ic_launcher_round
    }

    /**
     * 真机可以,模拟器不行.
     */
    public static void recursion(File file) {
        if (file.isFile()) {
            Log.i(TAG, "file.getAbsolutePath()===>" + file.getAbsolutePath());
            return;
        }
        if (file.isDirectory()) {
            Log.i(TAG, "file.getAbsolutePath()===>" + file.getAbsolutePath());
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                return;
            }
            for (File f : childFile) {
                recursion(f);
            }
        }
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
