package net.gazeplay.games.whereisit;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class GameSizingComputer {

    private final int nbLines;
    private final int nbColumns;
    private final boolean fourThree;

    public GameSizing computeGameSizing() {
        Rectangle2D bounds = javafx.stage.Screen.getPrimary().getBounds();

        return computeGameSizing(bounds);
    }

    public GameSizing computeGameSizing(Scene scene) {
        Rectangle2D bounds = new Rectangle2D(0, 0, scene.getWidth(), scene.getHeight());

        return computeGameSizing(bounds);
    }

    public GameSizing computeGameSizing(Rectangle2D bounds) {

        double sceneWidth = bounds.getWidth();
        double sceneHeight = bounds.getHeight();

        final double width;
        final double height;
        final double shift;

        log.info("16/9 or 16/10 screen ? = " + ((sceneWidth / sceneHeight) - (16.0 / 9.0)));

        if (fourThree && ((sceneWidth / sceneHeight) - (16.0 / 9.0)) < 0.1) {
            width = 4 * sceneHeight / 3;
            height = sceneHeight;
            shift = (sceneWidth - width) / 2;
        } else {
            width = sceneWidth;
            height = sceneHeight;
            shift = 0;
        }

        return new GameSizing(width / nbColumns, height / nbLines, shift);
    }

}