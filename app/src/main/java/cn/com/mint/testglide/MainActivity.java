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

import java.io.File;
import java.util.ArrayList;

import static com.bumptech.glide.Glide.with;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mTextView;
    private ImageView mImageView;
    private Intent mIntent;
    private ArrayList<String> wechats; // 所有图片的路径.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
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
                        .into(mImageView);
//                        .preload(200, 200);
//                        .into(mImageView);
                break;
            case R.id.mImageView:
//                startActivity(mIntent);
//                File file = new File("/storage/emulated/0/tencent/MobileQQ/Scribble/Config/gif/gif_zip_tmp5/gif_orig_5pre.png");
//                recursion(Environment.getExternalStorageDirectory());
//                recursion(file);
//                test(Environment.getRootDirectory());
                // 这个可以是任何Uri. 这里为了演示，我们只创建了一个指向桌面图标的Uri

                Uri uri = resourceIdToUri(this, R.mipmap.ic_launcher_round);
                with(this)
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
}
