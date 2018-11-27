package com.javaproject.examplecollect.imageexample;

import org.junit.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageIOTest {

    /**
     * 读取网络资源，写回指定格式的图片到本地
     * @throws IOException
     */
    @Test
    public void imageIORead() throws IOException {
        URL url = new URL("http://www.mkyong.com/image/rss.png");
        BufferedImage read = ImageIO.read(url);
        ImageIO.write(read,"png",new File("d://aa.png"));
        //jpg默认大小会小一些
        ImageIO.write(read,"jpg",new File("d://aa.jpg"));
        ImageIO.write(read,"gif",new File("d://aa.gif"));
    }

    /**
     * 窗口显示一下图片
     * @param args
     */
    public static void main(String[] args) {
        Image image = null;
        try {
            URL url = new URL("http://img04.tooopen.com/images/20130805/tooopen_10473021.jpg");
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);

        System.out.println("ok");
    }

}
