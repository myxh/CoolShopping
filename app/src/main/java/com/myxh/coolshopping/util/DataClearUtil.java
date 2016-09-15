package com.myxh.coolshopping.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by asus on 2016/9/15.
 */
public class DataClearUtil {
    /**
     * 清除内部缓存
     * @param context
     */
    public static void cleanInternalCache(Context context) {
        deleteFileByDirectory(context.getCacheDir());
    }

    /**
     * 清除外部缓存
     * @param context
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteFileByDirectory(context.getExternalCacheDir());
        }
    }

    /**
     * 清除数据库缓存
     * @param context
     */
    public static void cleanDatabase(Context context) {
        deleteFileByDirectory(new File("/data/data/"+context.getPackageName()+"/databases"));
    }

    /**
     * 清除SharedPreference缓存
     * @param context
     */
    public static void cleanSharedPreference(Context context) {
        deleteFileByDirectory(new File("/data/data/"+context.getPackageName()+"/shared_prefs"));
    }

    /**
     * 清除自定义路径下缓存
     * @param filepath
     */
    public static void cleanCustomCache(String filepath) {
        deleteFileByDirectory(new File(filepath));
    }

    /**
     * 清除files文件夹下缓存
     * @param context
     */
    public static void cleanFiles(Context context) {
        deleteFileByDirectory(context.getFilesDir());
    }

    /**
     * 删除指定名称数据库
     * @param context
     * @param databaseName
     */
    public static void cleanDatabaseByName(Context context, String databaseName) {
        context.deleteDatabase(databaseName);
    }

    /**
     * 清除App内所有数据
     * @param context
     * @param filePaths
     */
    public static void cleanApplicationData(Context context, String... filePaths) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabase(context);
        cleanSharedPreference(context);
        cleanFiles(context);

        if (filePaths != null) {
            for (String filePath : filePaths) {
                cleanCustomCache(filePath);
            }
        }
    }

    /**
     * 删除指定目录下所有文件,不处理文件夹
     * @param cacheDir
     */
    private static void deleteFileByDirectory(File cacheDir) {
        /*if (cacheDir != null && cacheDir.exists() && cacheDir.isDirectory()) {
            for (File file : cacheDir.listFiles()) {
                file.delete();
            }
        }*/
        if (cacheDir == null || !cacheDir.exists()) {
            return;
        }
        if (cacheDir.isDirectory()) {
            File[] files = cacheDir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFileByDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        cacheDir.delete();
    }

    /**
     * 获取Cache总大小
     * @param context
     * @return
     */
    public static String getTotalCacheSize(Context context) {
        long folderSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            folderSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(folderSize);
    }

    /**
     * 清除所有缓存
     */
    public static void cleanAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    /**
     * 删除指定目录下所有文件及文件夹
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir) {
        if (dir == null && !dir.exists()) {
            return false;
        }
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                boolean isSuccess = deleteDir(file);
                if (!isSuccess) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * 获取文件夹大小
     * @param file
     * @return
     */
    public static long getFolderSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size += getFolderSize(fileList[i]);
                } else {
                    size += fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 获取格式化后文件大小
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size/1024;
        if (kiloByte < 1) {
            return size+"Byte";
        }
        double megaByte = kiloByte/1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()+"KB";
        }
        double gigaByte = megaByte/1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()+"MB";
        }
        double teraByte = gigaByte/1024;
        if (teraByte < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()+"GB";
        }
        BigDecimal result4 = new BigDecimal(Double.toString(teraByte));
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()+"TB";
    }
}
