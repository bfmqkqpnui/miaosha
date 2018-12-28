package com.lance.spike.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @ClassName VerifyUtil
 * @Description TODO 图片验证码生成工具
 * @Author 陆逸飞
 * @Date 2018-12-28 16:19
 * @Version v1.0
 */
public class VerifyUtil {
    // 日志
    private static Logger logger = LoggerFactory.getLogger(VerifyUtil.class);

    private static Random random = new Random();

    /**
     * 获得字体
     */
    private static Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /**
     * 获得颜色
     */
    private static Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /*
    * @Title: drowLine
    * @Description 绘制干扰线
    * @Author 陆逸飞
    * @Date 2018-12-28 17:01
    * @Param [g, width, height]
    * @Return void
    */
    private static void drowLine(Graphics g, int width, int height) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /*
    * @Title: drowString
    * @Description 绘制字符串
    * @Author 陆逸飞
    * @Date 2018-12-28 17:01
    * @Param [g, randomString]
    * @Return void
    */
    private static void drowString(Graphics g, String randomString) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        for (int i = 0; i < randomString.length() - 1; i++) {
            char rand = randomString.charAt(i);
            g.translate(random.nextInt(3), random.nextInt(3));
            g.drawString(String.valueOf(rand), 13 * i, 16);
        }
    }

    /*
    * @Title: getRandcode
    * @Description 生成随机图片
    * @Author 陆逸飞
    * @Date 2018-12-28 17:01
    * @Param [code, width, height, lineSize]
    * @Return java.awt.image.BufferedImage
    */
    public static BufferedImage getRandcode(String code, int width, int height, int lineSize) {
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics g = image.getGraphics();
        // 图片大小
        g.fillRect(0, 0, width, height);
        // 字体大小
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        // 字体颜色
        g.setColor(getRandColor(110, 133));
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g, width, height);
        }
        drowString(g, code);
        g.dispose();
        return image;
    }
}