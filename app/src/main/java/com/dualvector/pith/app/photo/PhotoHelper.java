package com.dualvector.pith.app.photo;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.dualvector.pith.BuildConfig;
import com.dualvector.pith.R;
import com.dualvector.pith.mvp.base.BaseApplication;
import com.dualvector.pith.util.FileUtil;
import com.yalantis.ucrop.UCrop;

import java.io.File;

public class PhotoHelper {

    private static final String TAG = "PhotoHelper";

    public static final int REQUEST_GALLERY = 0;
    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_CROP = 2;
    public static final int RESULT_OK = 0;

    public static volatile PhotoHelper sInstance;

    public static PhotoHelper getInstance() {
        if (sInstance == null) {
            synchronized (PhotoHelper.class) {
                if (sInstance == null) {
                    sInstance = new PhotoHelper();
                }
            }
        }
        return sInstance;
    }

    public void gallery(Activity activity) {
        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(intentToPickPic, REQUEST_GALLERY);
    }

    public File camera(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(activity.getPackageManager()) == null) {
            Log.e(TAG, "Resolve camera activity is null, start camera failed.");
            return null;
        }

        File headImageFile = new File(FileUtil.createRootPath(BaseApplication.getContext()) + "/" + System.currentTimeMillis() + ".jpg");
        FileUtil.createFile(headImageFile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    FileProvider.getUriForFile(BaseApplication.getContext(), BuildConfig.APPLICATION_ID + ".provider", headImageFile));
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(headImageFile));
        }
        activity.startActivityForResult(intent, REQUEST_CAMERA);

        return headImageFile;
    }

    public File crop(AppCompatActivity activity, Fragment fragment, String imagePath) {
        File cropImageFile = getCropImageFile(activity);
        if (cropImageFile == null) {
            return null;
        }

        Uri srcUri = getImageContentUri(activity, new File(imagePath));
        Uri desUri = Uri.fromFile(cropImageFile);
        if (srcUri == null || desUri == null) {
            Log.e(TAG, "uri is null, crop failed.");
            return null;
        }
        UCrop.of(srcUri, desUri)
                .withAspectRatio(1, 1)
                .withMaxResultSize(R.dimen.fragment_head_size, R.dimen.fragment_head_size)
                .start(activity);
        return cropImageFile;
    }

    private File getCropImageFile(Activity activity) {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return new File(activity.getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
        }
        return null;
    }

    public String handleImage(Activity activity, Intent data) {
        Uri uri = data.getData();
        String imagePath = null;
        if (Build.VERSION.SDK_INT >= 19) {
            if (DocumentsContract.isDocumentUri(activity, uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                    String id = docId.split(":")[1];
                    String selection = MediaStore.Images.Media._ID + "=" + id;
                    imagePath = getImagePath(activity, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                    Uri contentUri = ContentUris.withAppendedId(Uri.parse("" +
                            "content://downloads/public_downloads"), Long.valueOf(docId));
                    imagePath = getImagePath(activity, contentUri, null);
                }
            } else if ("content".equals(uri.getScheme())) {
                imagePath = getImagePath(activity, uri, null);
            }
        } else {
            imagePath = getImagePath(activity, uri, null);
        }
        return imagePath;
    }

    private String getImagePath(Activity activity, Uri uri, String seletion) {
        String path = null;
        Cursor cursor = activity.getContentResolver().query(uri, null, seletion, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private Uri getImageContentUri(Activity activity, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = activity.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return activity.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }
}
