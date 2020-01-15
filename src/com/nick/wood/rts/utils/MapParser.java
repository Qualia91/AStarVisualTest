package com.nick.wood.rts.utils;

import com.nick.wood.rts.model.GoalType;
import com.nick.wood.rts.model.map.TileType;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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


    public static Vector2I getInitialPlayerPosition(String[][] inputMapData) {

        int rowIndex = 0;
        for (String[] row : inputMapData) {
            int colIndex = 0;
            for (String item : row) {

                if (item.equals("2")) {
                    return new Vector2I(rowIndex, colIndex);
                }
                colIndex++;
            }
            rowIndex++;
        }

        return new Vector2I();
    }
}
