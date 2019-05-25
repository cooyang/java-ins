package com.coo.ins.image.service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Author: G.Yang
 * Date Time: 2019/5/25 14:40
 * Function: 根据生成的随机数,绘制图片,输出到目标位置
 * Refer: https://www.cnblogs.com/maixiaodou/p/7305729.html
 */
public class ImageService {

    private RandomService randomService;

    public ImageService() {
        randomService = new RandomService();
    }



    public BufferedImage generateImage(String code){
        return generateImage(code, 80, 32);
    }

    public BufferedImage generateImage(String code, int width, int height){
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        // 绘制背景
        g.setColor(randomService.randomColor(220, 250));
        g.fillRect(0, 0, width, height);

        // 绘制干扰线
        int interLineNum = 6;
        for (int i = 0; i < interLineNum; i++) {
            g.setColor(randomService.randomColor(40, 150));
            randomDrawLine(g, width, height, 5);
        }

        // 写验证码
        writeCodeCharacter(g, code, width, height);

        // 扭曲图片
        shearX(g, width, height);
        shearY(g, width, height);

        // 添加噪点
        addNoise(image, width, height);

        //  封笔
        g.dispose();
        return image;

    }

    private void addNoise(BufferedImage image, int width, int height) {
        float noiseRate = 0.05f;    // 噪声率
        int area = (int) (noiseRate * width * height);   //噪点数量
        Random r = new Random();
        for (int i = 0; i < area; i++) {
            int xxx = r.nextInt(width);
            int yyy = r.nextInt(height);
            int rgb = randomService.randomColor().getRGB();
            image.setRGB(xxx, yyy, rgb);
        }
    }

    private void writeCodeCharacter(Graphics g, String codeString, int width, int height) {
        int fontSize = (int) (height * 0.8);
        int fx = 0;
        char[] code = codeString.toCharArray();

        for (char c : code) {
            g.setColor(randomService.randomColor(30, 120));
            g.setFont(new Font(randomService.randomFontTye(), Font.PLAIN, fontSize));

            int fy = (int) ((Math.random() * 0.3 + 0.6) * height);//每个字符高低是否随机
            g.drawString(String.valueOf(c), fx, fy);
            fx = (int) (fx + (width / codeString.length()) * (Math.random() * 0.3 + 0.8)); //依据宽度浮动
        }

    }

    private void randomDrawLine(Graphics g, int w, int h, int offset) {
        Random random = new Random();

        int x = random.nextInt(offset);
        int y = random.nextInt(Math.abs(h - random.nextInt(offset)));
        int x1 = w - random.nextInt(offset);
        int y1 = random.nextInt(Math.abs(h - random.nextInt(offset)));
        g.drawLine(x, y, x1, y1);
    }

    private void shearX(Graphics g, int width, int height) {
        int period = 2;
        int frames = 1;
        int phase = new Random().nextInt(2);

        for (int i = 0; i < height; i++) {
            double d = (double) (period >> 1) * Math.sin((double) i / (double) period
                    + (2.2831853071795862D * (double) phase)/ (double) frames);
            g.copyArea(0, i, width, 1, (int) d, 0);

            g.setColor(randomService.randomColor());
            g.drawLine((int) d, i, 0, i);
            g.drawLine((int) d + width, i, width, i);
        }
    }

    private void shearY(Graphics g, int width, int height) {
        Random random = new Random();
        int period = random.nextInt(10);
        int frames = 10;
        int phase = new Random().nextInt(4);
        for (int i = 0; i < width; i++) {
            double d = (double) (period >> 1) * Math.sin((double) i / (double) period
                    + (2.2831853071795862D * (double) phase)/ (double) frames);
            g.copyArea(i, 0, 1, height, 0, (int) d);

            g.setColor(randomService.randomColor());
            g.drawLine(i, (int) d, i, 0);
            g.drawLine(i, (int) d + height, i, height);
        }
    }

}
