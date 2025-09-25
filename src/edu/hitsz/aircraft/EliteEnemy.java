package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.prop.BaseProp;
import edu.hitsz.prop.BloodProp;
import edu.hitsz.prop.BombProp;
import edu.hitsz.prop.BulletProp;

import java.util.LinkedList;
import java.util.List;

/**
 * 精英敌机
 * 不可射击
 *
 * @author hitsz
 */
public class EliteEnemy extends AbstractAircraft {
    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 1;
    /**
     * 子弹伤害
     */
    private int power = 30;
    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = 1;

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
        // 碰壁检测和立即返回
        if (locationX <= 0) {
            // 碰到左边界，立即反转方向并确保位置不超出边界
            speedX = Math.abs(speedX); // 确保速度为正（向右）
            locationX = 0; // 确保位置在边界内
        } else if (locationX >= Main.WINDOW_WIDTH) {
            // 碰到右边界，立即反转方向并确保位置不超出边界
            speedX = -Math.abs(speedX); // 确保速度为负（向左）
            locationX = Main.WINDOW_WIDTH; // 确保位置在边界内
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction*5;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }

    @Override
    public List<BaseProp> createProps() {
        List<BaseProp> props = new LinkedList<BaseProp>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction*5;
        if (Math.random() <= 1.0 / 4) {
            props.add(new BloodProp(x, y, speedX, speedY));
        } else if (Math.random() >= 1.0 / 4 && Math.random() <= 2.0 / 4 ) {
            props.add(new BombProp(x, y, speedX, speedY));
        } else if (Math.random() >= 2.0 / 4 && Math.random() <= 3.0 /4){
            props.add(new BulletProp(x, y, speedX, speedY));
        }else{
        }
        return props;
    }


}