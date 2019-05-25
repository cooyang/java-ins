package com.coo.ins.image.service;

import java.awt.*;
import java.util.Random;

/**
 * Author: G.Yang
 * Date Time: 2019/5/25 14:44
 * Function: 生成随机数
 */
public class RandomService {

    public String randomCharCode(int length){
        // a-z(97-122), A-Z(65-90), 0-9(48-57)
        char[] res = new char[length];
        while (length > 0){
            int r = new Random().nextInt(123);
            if (r > 96 || (r > 64 && r < 91) || (r > 47 && r < 58)){
                res[res.length - length] = (char) r;
                length --;
            }
        }

        return String.valueOf(res);
    }

    public Color randomColor() {
        return randomColor(50, 200);
    }

    public Color randomColor(int fc, int bc) {
        int f = fc > 255 ? 255 : fc;
        int b = bc > 255 ? 255 : bc;
        Random random = new Random();
        return new Color(f + random.nextInt(b - f), f + random.nextInt(b - f),
                f + random.nextInt(b - f));
    }

    public String randomFontTye(){
        // 宋体/新宋体/黑体/楷体/隶书
        String[] fontTypes = new String[]{ "\u5b8b\u4f53", "\u65b0\u5b8b\u4f53",
                "\u9ed1\u4f53", "\u6977\u4f53", "\u96b6\u4e66" };
        return fontTypes[new Random().nextInt(fontTypes.length)];
    }


}
