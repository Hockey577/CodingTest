package two;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 编写一个程序，每五分钟扫描某个目录下的所有文件，
 * 根据文件名规则使用特定的方式将文件传输出去，
 * 比如xxxx.ftp，表示将xxxx.ftp发送到预先指定的FTP 服务器上，文件传输完之后将文件清除。
 * 提示：
 * 1. 传输文件不需要实现，使用模拟类替换即可
 * 2. 假设各种传输方式都支持多连接传输
 * 3. 文件数可能很多，文件还可能很大
 */

/**
 * 实现思想：
 * 1、首先扫描文件夹：递归扫描，如果是文件夹就继续往下 ---->scanfiles()
 * 2、再利用线程休眠实现每五分钟扫描
 * 3、对upFiles中的文件绝对路径进行判断，如果是ftp文件就继续执行，否则跳过
 * 3、创建ftpUpload类模拟上传，
 * 4、isUpload可以解决某个文件过大的问题，如果前一个子线程没有释放isUpload,后一个线程不能执行
 * 5、未解决timeout问题
 */
public class MultiFiles {
    //upFiles保存文件的绝对路径
    static List<String> upFiles = new ArrayList<String>();
    private static boolean isUpload = false;

    public static void main(String[] args) {
        while (true) {
            try {
                execute();
                Thread.sleep(5 * 60 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void execute() {
        if (isUpload == true) {
            return;
        }
        final String path = "d://123";
        new Thread(new Runnable() {
            public void run() {
                try {
                    isUpload = true;
                    //扫描文件夹
                    scanFiles(path);
                    for (String file : upFiles) {
                        //首先对文件进行判断，是否为ftp文件
                        if (file.endsWith(".ftp")) {
                            File oldFile = new File(file);
                            //上传ftp
                            ftpUplod.upload(oldFile);
                            //删除文件
                            oldFile.delete();
                        } else {
                            //如果是其他类型的文件，另做处理
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    isUpload = false;
                }
            }
        }).start();
    }

    private static void scanFiles(String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files && files.length > 0) {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        scanFiles(file2.getAbsolutePath());//递归
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                        upFiles.add(file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
}

