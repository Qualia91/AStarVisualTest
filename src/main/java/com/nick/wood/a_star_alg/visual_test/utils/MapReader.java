package com.nick.wood.a_star_alg.visual_test.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapReader {
    public static ArrayList<String[]> readMap(String fileName) {
        ArrayList<String[]> returnArray = new ArrayList<>();
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(fileName)) ;
            String line;

            while ((line = br.readLine()) != null) {

                String[] lineArray = line.split(",");
                returnArray.add(lineArray);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return returnArray;
    }

    public static String[][] readBMPMap(File file) {

        String[][] array2d = null;

        try {
            BufferedImage bufferedImage = ImageIO.read(file);

            array2d = new String[bufferedImage.getWidth()][bufferedImage.getHeight()];

            for (int xPixel = 0; xPixel < bufferedImage.getWidth(); xPixel++) {
                for (int yPixel = 0; yPixel < bufferedImage.getHeight(); yPixel++) {
                    int color = bufferedImage.getRGB(xPixel, yPixel);
                    if (color == Color.BLACK.getRGB()) {
                        array2d[xPixel][yPixel] = "1";
                    }
                    else if (color == Color.RED.getRGB()){
                        array2d[xPixel][yPixel] = "2";
                    }
                    else if (color == Color.WHITE.getRGB()) {
                        array2d[xPixel][yPixel] = "0";
                    }
                    else if (color == Color.CYAN.getRGB()) {
                        array2d[xPixel][yPixel] = "3";
                    } else {
                        System.out.println("UNKNOWN COLOR: " + bufferedImage.getRGB(xPixel, yPixel));

                        array2d[xPixel][yPixel] = "3";
                    }


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return array2d;

    }
}
