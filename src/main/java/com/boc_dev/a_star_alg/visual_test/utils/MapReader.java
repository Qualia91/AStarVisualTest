package com.boc_dev.a_star_alg.visual_test.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class to old the static read .bmp map functionality.
 */
public class MapReader {

    /**
     *
     * @param file The file .bmp map file to be read.
     * @return a 2 dimensional int array containing numbers that represent the map and obstacles:
     *
     * 0:   Free space.
     * 1:   Obstacle.
     * 2:   Player start position.
     * 3:   Difficult terrain.
     * 4:   Unknown colour.
     */
    public static int[][] readBMPMap(File file) throws IOException {

        int[][] array2d = null;

        BufferedImage bufferedImage = ImageIO.read(file);

        array2d = new int[bufferedImage.getWidth()][bufferedImage.getHeight()];

        for (int xPixel = 0; xPixel < bufferedImage.getWidth(); xPixel++) {
            for (int yPixel = 0; yPixel < bufferedImage.getHeight(); yPixel++) {
                int color = bufferedImage.getRGB(xPixel, yPixel);
                if (color == Color.BLACK.getRGB()) {
                    array2d[xPixel][yPixel] = 1;
                }
                else if (color == Color.RED.getRGB()){
                    array2d[xPixel][yPixel] = 2;
                }
                else if (color == Color.WHITE.getRGB()) {
                    array2d[xPixel][yPixel] = 0;
                }
                else if (color == Color.CYAN.getRGB()) {
                    array2d[xPixel][yPixel] = 3;
                } else {
                    System.out.println("UNKNOWN COLOR: " + bufferedImage.getRGB(xPixel, yPixel));

                    array2d[xPixel][yPixel] = 4;
                }


            }
        }

        return array2d;

    }

}
