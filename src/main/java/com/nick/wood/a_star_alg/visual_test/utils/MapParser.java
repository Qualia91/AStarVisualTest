package com.nick.wood.a_star_alg.visual_test.utils;

import com.nick.wood.a_star_alg.map.TileType;

/**
 * Class to hold Map parser functionality.
 */
public class MapParser {

    /**
     *
     * @param inputMapData a 2D int array containing obstacle data.
     * @return
     */
    public static TileType[][] ConvertToMapArray(int[][] inputMapData) {
        int xLenght = inputMapData.length;
        int yLength = inputMapData[0].length;

        TileType[][] returnMapData = new TileType[xLenght][yLength];

        int rowIndex = 0;
        for (int[] row : inputMapData) {
            int colIndex = 0;
            for (int type : row) {

                TileType tileType = TileType.BLACK;
                if (type == 1) {
                    tileType = TileType.BLUE;
                }
                if (type == 2) {
                    tileType = TileType.RED;
                }
                if (type == 3) {
                    tileType = TileType.CYAN;
                }
                returnMapData[rowIndex][colIndex] = tileType;

                colIndex++;
            }
            rowIndex++;
        }

        return returnMapData;
    }


    public static int[] getInitialPlayerPosition(int[][] inputMapData) {

        int rowIndex = 0;
        for (int[] row : inputMapData) {
            int colIndex = 0;
            for (int item : row) {

                if (item == 2) {
                    return new int[]{rowIndex, colIndex};
                }
                colIndex++;
            }
            rowIndex++;
        }

        return new int[]{0, 0};
    }
}
