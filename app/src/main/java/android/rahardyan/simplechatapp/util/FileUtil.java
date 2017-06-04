package android.rahardyan.simplechatapp.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Base64;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahardyan on 16/05/17.
 */

/*
 * FileUtil: File related utilities, like safe-create, safe-delete, copying, compressing etc
 */
public final class FileUtil {
    /**
     * Represents the end-of-file (or stream).
     * @since 2.5 (made public)
     */
    public static final int EOF = -1;

    /**
     * The default buffer size ({@value}) to use for
     * {@link #copyLarge(InputStream, OutputStream)}
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
    private static final String TAG = FileUtil.class.getSimpleName();


    /**
     * create file from uri
     *
     * @param context - android context
     * @param uri - file uri
     *
     * @return file
     * @throws IOException
     */
    @NonNull
    public static File from(@NonNull final Context context, @NonNull final Uri uri) throws IOException {
        InputStream inputStream = context
                .getContentResolver().openInputStream(uri);
        String fileName = getFileName(context, uri);
        String[] splitName = splitFileName(fileName);
        File tempFile = File.createTempFile(splitName[0], splitName[1]);
        tempFile = rename(tempFile, fileName);
        tempFile.deleteOnExit();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(tempFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        copy(inputStream, out);


        return tempFile;
    }

    /**
     * create file from inputstream
     *
     * @param context - android context
     * @param inputStream -  file inputStream
     * @param fileName - file name
     *
     * @return file
     * @throws IOException
     */
    @NonNull
    public static File from(@NonNull final Context context, @NonNull final InputStream inputStream, @NonNull final String fileName) throws
            IOException {
        File file = new File(generateFilePath(context, fileName));
        file = rename(file, fileName);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        copy(inputStream, out);
        return file;
    }

    /**
     * split file name between name and extension
     *
     * @param fileName - file name
     *
     * @return array {"file name", "extension"}
     */
    @NonNull
    public static String[] splitFileName(@NonNull final String fileName) {
        String name = fileName;
        String extension = "";
        int i = fileName.lastIndexOf(".");
        if (i != -1) {
            name = fileName.substring(0, i);
            extension = fileName.substring(i);
        }

        return new String[]{name, extension};
    }

    /**
     * get extension from file name
     *
     * @param fileName - file name
     *
     * @return file extension
     */
    @NonNull
    public static String getExtension(@NonNull final String fileName) {
        return splitFileName(fileName)[1];
    }

    /**
     * get extension from file uri
     *
     * @param context - android context
     * @param uri - file uri
     *
     * @return file extension
     */
    @NonNull
    public static String getExtension (@NonNull Context context, @NonNull final Uri uri) {
        String fileName = getFileName(context, uri);
        return getExtension(fileName);
    }

    /**
     * get file name from uri
     *
     * @param context - android context
     * @param uri - file uri
     *
     * @return
     */
    @NonNull
    @WorkerThread
    public static String getFileName(@NonNull final Context context, @NonNull final Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf(File.separator);
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    /**
     * get file path
     *
     * @param context - android context
     * @param contentUri - file uri
     *
     * @return file path
     */
    @NonNull
    @WorkerThread
    public static String getRealPathFromURI(@NonNull final Context context, @NonNull final Uri contentUri) {
        Cursor cursor = context.getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            String realPath = cursor.getString(index);
            cursor.close();
            return realPath;
        }
    }

    /**
     * save file to storage
     *
     * @param context - android context
     * @param file - file that will be saved
     *
     * @return saved file
     */
    @NonNull
    public static File saveFile(@NonNull final Context context, final File file) {
        String path = generateFilePath(context, Uri.fromFile(file));
        File newFile = new File(path);
        try {
            FileInputStream in = new FileInputStream(file);
            FileOutputStream out = new FileOutputStream(newFile);
            copy(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFile;
    }

    /**
     * generate unique file path with timestamp
     *
     * @param context - android context
     * @param uri - file uri
     *
     * @return file path
     */
    @NonNull
    private static String generateFilePath(@NonNull final Context context, @NonNull final Uri uri) {
        final String FILES_PATH = context.getApplicationInfo().packageName + File.separator + "Files" + File.separator;
        File file = new File(Environment.getExternalStorageDirectory().getPath(), FILES_PATH + System.currentTimeMillis());
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath() + File.separator + getFileName(context, uri);
    }

    /**
     * generate unique file path with timestamp
     *
     * @param context - android context
     * @param fileName - file name
     *
     * @return file path
     */
    @NonNull
    public static String generateFilePath(@NonNull final Context context, @NonNull final String fileName) {
        final String FILES_PATH = context.getApplicationInfo().packageName + File.separator + "Files" + File.separator;
        File file = new File(Environment.getExternalStorageDirectory().getPath(), FILES_PATH + System.currentTimeMillis());
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath() + File.separator + fileName;
    }

    /**
     * renaming file name
     *
     * @param file - file that want to be renamed
     * @param newName - new file name
     *
     * @return file with renamed file name
     */
    @NonNull
    public static File rename(@NonNull final File file, final String newName) {
        File newFile = new File(file.getParent(), newName);
        if (!newFile.equals(file)) {
            if (newFile.exists()) {
                if (newFile.delete()) {
                    Log.d(TAG, "Delete old " + newName + " file");
                }
            }
            if (file.renameTo(newFile)) {
                Log.d(TAG, "Rename file to " + newName);
            }
        }
        return newFile;
    }

    /**
     * check file is video or not
     *
     * @param file - file the want to be check
     *
     * @return true if file type is video
     */
    public static boolean isVideo(@NonNull final File file) {
        String path = file.getPath();
        int lastDotPosition = path.lastIndexOf(".");
        String ext = path.substring(lastDotPosition + 1);
        ext = ext.replace("_", "");
        ext = ext.toLowerCase();
        String type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext);
        if (type == null) {
            return false;
        } else if (type.contains("video")) {
            return true;
        }

        return false;
    }

    /**
     * get file extension
     *
     * @param file - file that want to be check
     *
     * @return file extension
     */
    @NonNull
    public static String getExtension(@NonNull final File file) {
        String path = file.getPath();
        int lastDotPosition = path.lastIndexOf(".");
        String ext = path.substring(lastDotPosition + 1);
        ext = ext.replace("_", "");
        return ext.trim().toLowerCase();
    }

    /**
     * check is file supported or not
     *
     * @param file - file that want to be check
     * @param supportedFile - array of supported file type
     *
     * @return true, if file is supported with supportedFile type
     */
    public static boolean isSupportedFile(@NonNull final File file, @NonNull final String[] supportedFile) {
        String ext = getExtension(file);
        for (String supportedExt : supportedFile) {
            if (ext.equals(supportedExt)) {
                return true;
            }
        }

        return false;
    }

    /**
     * get mime type
     *
     * @param pathName - file path
     *
     * @return mime type
     */
    @WorkerThread
    public static String getMimeTypeOfFile(final String pathName) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, opt);
        return opt.outMimeType;
    }

    /**
     * check is file size permitted
     *
     * @param file - file that will be check
     * @param minSizeInByte - min file size
     * @param maxSizeInByte - max file size
     *
     * @return return true if file size is more than minSizeInByte and below maxSizeInByte
     */
    public static boolean isFileSizePermitted(@NonNull final File file, final long minSizeInByte, final long maxSizeInByte) {
        long fileSize = file.length();
        return (fileSize > minSizeInByte && fileSize < maxSizeInByte);
    }

    /**
     * Copies bytes from an InputStream to an OutputStream
     *
     * @param input the <code>InputStream</code> to read from
     * @param output the <code>OutputStream</code> to write to
     *
     * @return the number of bytes copied, or -1 if count > Integer.MAX_VALUE
     * @throws NullPointerException if the input or output is null
     * @throws IOException
     */
    private static int copy(final InputStream input, final OutputStream output) throws IOException {
        final long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    /**
     * Copies bytes from a large (over 2GB) <code>InputStream</code> to an
     * <code>OutputStream</code>.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream</code>.
     * <p>
     * The buffer size is given by {@link #DEFAULT_BUFFER_SIZE}.
     *
     * @param input the <code>InputStream</code> to read from
     * @param output the <code>OutputStream</code> to write to
     *
     * @return the number of bytes copied
     * @throws NullPointerException if the input or output is null
     * @throws IOException          if an I/O error occurs
     */
    private static long copyLarge(final InputStream input, final OutputStream output)
            throws IOException {
        return copy(input, output, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Copies bytes from an <code>InputStream</code> to an <code>OutputStream</code> using an internal buffer of the
     * given size.
     * <p>
     * This method buffers the input internally, so there is no need to use a <code>BufferedInputStream</code>.
     * <p>
     *
     * @param input the <code>InputStream</code> to read from
     * @param output the <code>OutputStream</code> to write to
     * @param bufferSize the bufferSize used to copy from the input to the output
     *
     * @return the number of bytes copied
     * @throws NullPointerException if the input or output is null
     * @throws IOException          if an I/O error occurs
     */
    private static long copy(final InputStream input, final OutputStream output, final int bufferSize)
            throws IOException {
        return copyLarge(input, output, new byte[bufferSize]);
    }

    /**
     * Copies bytes from a large (over 2GB) <code>InputStream</code> to an
     * <code>OutputStream</code>.
     * <p>
     * This method uses the provided buffer, so there is no need to use a
     * <code>BufferedInputStream</code>.
     * <p>
     *
     * @param input the <code>InputStream</code> to read from
     * @param output the <code>OutputStream</code> to write to
     * @param buffer the buffer to use for the copy
     *
     * @return the number of bytes copied
     * @throws NullPointerException if the input or output is null
     * @throws IOException          if an I/O error occurs
     */
    @WorkerThread
    private static long copyLarge(final InputStream input, final OutputStream output, final byte[] buffer)
            throws IOException {
        long count = 0;
        int n;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    /**
     * convert string image list to base64
     *
     * @param imageList - list string image
     *
     * @return list base64
     */
    @NonNull
    public static List<String> convertBase64(@NonNull List<String> imageList) {
        List<String> returnList = new ArrayList<>();
        for (int i = 0; i < imageList.size(); i++) {
            Bitmap bm = BitmapFactory.decodeFile(imageList.get(i));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] bytes = baos.toByteArray();
            String encodedImage = "data:image/png;base64," + Base64.encodeToString(bytes, Base64.DEFAULT);
            returnList.add(encodedImage);
        }
        return returnList;
    }

}
