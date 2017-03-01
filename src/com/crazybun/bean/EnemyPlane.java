package com.crazybun.bean;

import com.crazybun.utils.Setting;

/**
 * 敌方飞机类
 * 
 * @author CrazyBun
 */
public class EnemyPlane extends FlyItems implements Enemy {
	/**
	 * 敌方飞机类型，0：奖励飞机， 1 小飞机，2中型飞机，3Boss飞机
	 */
	private int planeType;
	/**
	 * 敌方飞机的分数
	 */
	private int score;
	/**
	 * 敌方飞水平机移动方向，是否返回
	 */
	private boolean backX;
	/**
	 * 敌方飞机垂直移动方向，是否返回
	 */
	private boolean backY;

	/**
	 * 构造敌方飞机
	 * 
	 * @param type
	 *            飞机的类型：0：奖励飞机， 1 小飞机，2中型飞机，3Boss飞机
	 */
	public EnemyPlane(int type, int life) {
		this.planeType = type;
		switch (planeType) {
		case 0:
			image = Setting.awardPlane;
			speed = Setting.SPEED_AWARDPLANE;
			score = Enemy.ENEMY_AWARD_SCORE;
			break;
		case 1:
			image = Setting.smallPlane;
			speed = Setting.SPEED_ENEMYPLANE;
			score = Enemy.ENEMY_SMALL_SCORE;
			break;
		case 2:
			image = Setting.midPlane;
			speed = Setting.SPEED_ENEMYPLANE;
			score = Enemy.ENEMY_NORMAL_SCORE;
			break;
		case 3:
			image = Setting.bossPlane;
			speed = Setting.SPEED_BOSSPLANE;
			score = Enemy.ENEMY_BOSS_SCORE;
			break;
		default:
			break;
		}
		width = image.getWidth(null);
		height = image.getHeight(null);
		x = Setting.RND.nextInt(Setting.FRAME_WIDTH - width);
		y = -height;
		this.backX = Math.random() < 0.5f ? true : false;
		this.backY = false;
		this.life = life;
	}

	/**
	 * 敌方飞机的移动方式
	 */
	@Override
	public void move() {
		if (planeType == 3) {//Boss飞机移动方式
			x = backX ? x - speed : x + speed;
			y = backY ? y - speed : y + speed;
		} else if (planeType == 2) {//中等飞机移动方式
			x = backX ? x - speed : x + speed;
			y += speed;
		} else {//其他飞机移动方式
			y += speed;
		}
	}

	/**
	 * 重写越界检测
	 */
	@Override
	public boolean outOfBound() {
		backX = x < 0 ? false : (x > Setting.FRAME_WIDTH - width ? true : backX);
		return super.outOfBound();
	}

	/**
	 * 敌机发射子弹
	 * 
	 * @return Bullet
	 */
	public Bullet shootBullet() {
		if (planeType == 2) {//中等飞机发射子弹方式
			return new Bullet(x + width / 2, this.y + height / 2, false);
		}
		if (planeType == 3) {//Boss飞机发射子弹方式
//			return new Bullet(x + width / 2, this.y + height / 2, false);
		}
		return null;
	}

	/**
	 * 获取分数
	 * 
	 * @return int
	 */
	public int getScore() {
		return score;
	}

	/**
	 * 获取敌机类型
	 * 
	 * @return int
	 */
	public int getPlaneType() {
		return planeType;
	}
}
