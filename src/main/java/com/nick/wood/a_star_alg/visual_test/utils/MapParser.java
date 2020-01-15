package com.nick.wood.a_star_alg.visual_test.utils;

import com.nick.wood.a_star_alg.map.TileType;

public class MapParser {

    public static TileType[][] ConvertToMapArray(String[][] inputMapData) {
        int xLenght = inputMapData.length;
        int yLength = inputMapData[0].length;

        TileType[][] returnMapData = new TileType[xLenght][yLength];

        int rowIndex = 0;
        for (String[] row : inputMapData) {
            int colIndex = 0;
            for (String item : row) {

                int type = Integer.parseInt(item);
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


    public static int[] getInitialPlayerPosition(String[][] inputMapData) {

        int rowIndex = 0;
        for (String[] row : inputMapData) {
            int colIndex = 0;
            for (String item : row) {

                if (item.equals("2")) {
                    return new int[]{rowIndex, colIndex};
                }
                colIndex++;
            }
            rowIndex++;
        }

        return new int[]{0, 0};
    }
}
