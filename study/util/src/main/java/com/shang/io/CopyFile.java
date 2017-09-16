package com.shang.io;

import java7.nio2.FileCharsetTest;

import java.io.*;

/**
 * 功能描述:复制文件
 *
 * @author Nick
 */
public class CopyFile {


    /**
     * 功能描述:复制文件
     *
     * @param file_old_path 需要复制文件路径
     * @param file_new_path 复制后的文件路径
     * @param overWriteFile 是否覆盖原文件 true:覆盖 false:不覆盖
     * @return true：表示成功 false：表示失败
     */
    public boolean CopyFile(String file_old_path, String file_new_path, boolean overWriteFile) {
        boolean check = false;

        //1.检查文件名是否相等
        if (file_old_path.equals(file_new_path)) {
            return true;
        }

        //2.1 检查文件 文件存在 不能覆盖
        File file_new = new File(file_new_path);
        if (file_new.exists() && !overWriteFile) {
            return true;
        }


        //3.写入文件
        InputStream in = null;
        OutputStream out = null;
        int i = 0;
        try {
            in = new FileInputStream(file_old_path);
            out = new FileOutputStream(file_new_path);

            byte[] beffer = new byte[1024];

            while ((i = in.read(beffer)) != -1) {
                out.write(beffer, 0, i);
            }
            out.flush();
            check = true;

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return check;
    }

    FileCharsetTest fileCharset = new FileCharsetTest();

    /**
     * 功能描述:复制文件
     *
     * @param file_old_path 需要复制文件路径
     * @param file_new_path 复制后的文件路径
     * @param overWriteFile 是否覆盖原文件 true:覆盖 false:不覆盖
     * @return true：表示成功 false：表示失败
     */
    public boolean CopyFiles(String file_old_path, String file_new_path, boolean overWriteFile) {
        boolean check = false;


        //1.检查文件名是否相等
        if (file_old_path.equals(file_new_path)) {
            return true;
        }

        //2.1 检查文件 文件存在 不能覆盖
        File file_new = new File(file_new_path);
        if (file_new.exists() && !overWriteFile) {
            return true;
        }



        //2.2 检查文件编码类型
        try {
            String charset = fileCharset.guessFileEncoding(new File(file_old_path));
            if (charset.equals("GB2312")) {
                InputStreamReader is = new InputStreamReader(new FileInputStream(file_old_path), "GB2312");
                OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(file_new_path), "UTF-8");
                char[] c = new char[1024];
                int i = 0;
                while ((i = is.read(c, 0, 1024)) != -1) {
                    os.write(c,0,i);
                }

                os.flush();
                os.close();

                is.close();
                System.out.println( "转码完成file_old_path:" + file_old_path );
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //3.写入文件
        InputStream in = null;
        OutputStream out = null;
        int i = 0;
        try {
            in = new FileInputStream(file_old_path);
            out = new FileOutputStream(file_new_path);

            byte[] beffer = new byte[1024];

            while ((i = in.read(beffer)) != -1) {
                out.write(beffer, 0, i);
            }
            out.flush();
            check = true;

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return check;
    }

    /**
     * 功能描述:复制整个文件夹
     *
     * @param dir_old_path 需要复制的文件夹
     * @param dir_new_path 复制后的文件夹
     * @param overWrite    是否覆盖原文件 true:覆盖 false:不覆盖
     * @return true:成功 false：失败
     */
    public boolean CopyDir(String dir_old_path, String dir_new_path, boolean overWrite) {
        boolean check = false;

        //1.判断是不是文件夹
        File dir_old = new File(dir_old_path);
        if (dir_old.exists() && !dir_old.isDirectory()) {
            return check;
        }

        //2.检查复制后的文件夹 是否存在
        File dir_new = new File(dir_new_path);
        if (!dir_new.exists()) {
            dir_new.mkdir();
        }

        //3.复制文件夹下的文件
        File[] files = dir_old.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isFile()) {
                CopyFiles(dir_old_path + File.separator + file.getName(), dir_new_path + File.separator + file.getName(), overWrite);
            }
        }

        //4.复制文件夹下的文件夹
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) {
                CopyDir(dir_old_path + File.separator + file.getName(), dir_new_path + File.separator + file.getName(), overWrite);
            }
        }

        return check;
    }


    public static void main(String[] args) {
        CopyFile copy = new CopyFile();
        copy.CopyDir("F:\\workspace\\shang1", "F:\\workspace\\shang1_utf8", true);
    }
}
