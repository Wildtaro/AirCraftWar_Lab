package edu.hitsz.factory;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * EliteEnemyFactory
 *
 * @author hitsz
 */
public class EliteEnemyFactory implements EnemyFactory {

    @Override
    public EliteEnemy createEnemy() {
        int randomSpeedX;
        do {
            randomSpeedX = (int)(Math.random()*11)-5;
        }while (randomSpeedX == 0);
        return new EliteEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.03),
                randomSpeedX,
                4,
                60);
    }
}