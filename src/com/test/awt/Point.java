/**  
 * @Title: Point.java
 * @Package com.test.awt
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test.awt;

import java.awt.Color;

/**
 * @ClassName: Point
 * @Description: TODO
 */
public class Point {

	int x, y;
	Color color;
	int tool;

	Point(int x, int y, int tool, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.tool = tool;
	}
}
