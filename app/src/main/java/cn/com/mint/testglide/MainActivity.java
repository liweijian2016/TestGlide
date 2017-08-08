package cn.com.mint.testglide;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Test{

    private static final String TAG = "MainActivity";
    private TextView mTextView;
    private ImageView mImageView;
    private Intent mIntent;
    private ArrayList<String> wechats; // 所有图片的路径.
    private RequestOptions mOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        mOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        mIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.mTextView);
        mImageView = (ImageView) findViewById(R.id.mImageView);
    }

    private void initData() {
        wechats = new ArrayList<>();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mTextView:
                Glide
                        .with(this)
                        .load("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_redBlue.png")
                        .apply(mOptions)
                        .into(mImageView);

                break;
            case R.id.mImageView:
//                startActivity(mIntent);
//                File file = new File("/storage/emulated/0/tencent/MobileQQ/Scribble/Config/gif/gif_zip_tmp5/gif_orig_5pre.png");
//                recursion(Environment.getExternalStorageDirectory());
//                recursion(file);
//                test(Environment.getRootDirectory());
                // 这个可以是任何Uri. 这里为了演示，我们只创建了一个指向桌面图标的Uri
                Uri uri = resourceIdToUri(this, R.mipmap.ic_launcher_round);
//                Glide.with(this)
//                        .load(uri)
//                        .into(mImageView);
//                Glide.with(this)
//                        .asBitmap()
//                        .load(uri)
//                        .apply(mOptions)
//                        .into(mImageView);
                Glide.with(this)
                        .asBitmap()
                        .apply(mOptions)
                        .load(uri)
                        .into(mImageView);
                break;
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
