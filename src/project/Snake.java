package project;
import java.awt.*;
import java.util.*;
import java.util.List;

import edu.princeton.cs.introcs.StdDraw;

public class Snake {
	// To store the coordinates of every part of the snake
	private static List<Double> x = new ArrayList<>();
	private static List<Double> y = new ArrayList<>();
	public static void canvas(List<Double> x,List<Double> y){
		StdDraw.setCanvasSize(650,650);
		StdDraw.setXscale(0,70);
		StdDraw.setYscale(0,70);
		// This is the background picture
		StdDraw.picture(35,35,"/Users/qiuyunxiu/Desktop/project-yunxiuqiu1115/src/project/background.jpg");
		setSnake(x,y);
		// Start Scene
		StdDraw.setFont(new Font("Sans Serif",Font.BOLD,16));
		StdDraw.text(35,27.5,"**********************");
		StdDraw.text(35,30,"****Snake Game****");
		StdDraw.text(35,32.5,"**********************");
		StdDraw.text(35,35,"Press direction key to move");
		StdDraw.enableDoubleBuffering();

	}
	public static void setSnake(List<Double> x,List<Double> y){
		// Draw the snake
		StdDraw.picture(35,35,"/Users/qiuyunxiu/Desktop/project-yunxiuqiu1115/src/project/background.jpg");
		StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
		for(int i=0;i<x.size();i++){
			StdDraw.filledSquare(x.get(i),y.get(i),0.7);
		}
	}
	public static int action(List<Double> x,List<Double> y,String move,double foodX,double foodY){
		StdDraw.picture(35,35,"/Users/qiuyunxiu/Desktop/project-yunxiuqiu1115/src/project/background.jpg");
		int ret = 0;
		while(x.get(0)>0&&x.get(0)<70&&y.get(0)>0&&y.get(0)<70&&!(touchItSelf(x.get(0),y.get(0),x,y))){
			StdDraw.clear();
			// Turn down
			if(!move.equals("up")&&StdDraw.isKeyPressed(40)){
				move = "down";
			}
			// Turn up
			else if(!move.equals("down")&&StdDraw.isKeyPressed(38)){
				move = "up";
			}
			// Turn right
			else if (!move.equals("left")&&StdDraw.isKeyPressed(39)){
				move = "right";
			}
			// Turn left
			else if(!move.equals("right")&&StdDraw.isKeyPressed(37)){
				move="left";
			}
			// If the food is eaten
			if(x.get(0)==foodX&&y.get(0)==foodY){
				ret++;
				x.add(x.get(x.size()-1));
				y.add(y.get(y.size()-1));
				foodX = (int)(Math.random()*65)*1.0;
				foodY = (int)(Math.random()*65)*1.0;
				// Food cannot show up inside the snake's body
				while(touchItSelf(foodX,foodY,x,y)){
					foodX = (int)(Math.random()*65)*1.0;
					foodY = (int)(Math.random()*65)*1.0;
				}
			}
			if(move.equals("up")){
				// Move up if not touching the border
				if(y.get(0)<70){
					y.add(0,y.get(0)+1);
					x.add(0,x.get(0));
					x.remove(x.size()-1);
					y.remove(y.size()-1);
					drawGame(x,y,foodX,foodY);
				}
			}
			else if(move.equals("down")){
				// Move down if not touching the border
				if(y.get(0)>0){
					y.add(0,y.get(0)-1);
					x.add(0,x.get(0));
					x.remove(x.size()-1);
					y.remove(y.size()-1);
					drawGame(x,y,foodX,foodY);
				}
			}
			else if(move.equals("left")){
				// Move left if not touching the border
				if(x.get(0)>0){
					x.add(0,x.get(0)-1);
					y.add(0,y.get(0));
					x.remove(x.size()-1);
					y.remove(y.size()-1);
					drawGame(x,y,foodX,foodY);
				}
			}else if(move.equals("right")){
				// Move right if not touching the border
				if(x.get(0)<70){
					x.add(0,x.get(0)+1);
					y.add(0,y.get(0));
					x.remove(x.size()-1);
					y.remove(y.size()-1);
					drawGame(x,y,foodX,foodY);
				}
			}
		}
		return ret;
	}
	public static void drawGame(List<Double> x,List<Double> y,double foodX,double foodY){
		// Background Picture
		StdDraw.picture(35,35,"/Users/qiuyunxiu/Desktop/project-yunxiuqiu1115/src/project/background.jpg");
		// Draw the Snake
		for(int i=0;i<x.size();i++){
			StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
			StdDraw.filledSquare(x.get(i),y.get(i),0.5);
		}
		// Draw the food
		StdDraw.setPenColor((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
		StdDraw.filledCircle(foodX,foodY,0.5);
		// Make the animation go smoothly
		StdDraw.show(10);
		StdDraw.pause(100);

	}
	public static boolean touchItSelf(double X,double Y,List<Double>x,List<Double> y){
		// Check whether one point is inside the snake's body
		for(int i=1;i<x.size();i++){
			if(X==x.get(i)&&Y==y.get(i))return true;
		}
		return false;
	}
	// Play the game
	public static void main(String[] args){
		// Initialize the snake
		x.add(34.0);
		y.add(21.0);
		x.add(35.0);
		y.add(21.0);
		x.add(36.0);
		y.add(21.0);
		// Initialize the canvas
		canvas(x,y);
		String move = "";
		int totalEaten = 0;
		double foodX = (int)(Math.random()*65)*1.0;
		double foodY = (int)(Math.random()*65)*1.0;

		while(move.equals("")){
			if(StdDraw.isKeyPressed(38)){
				move = "up";
			}
			else if(StdDraw.isKeyPressed(40)){
				move = "down";
			}
			else if(StdDraw.isKeyPressed(37)){
				move = "left";
			}
			else if(StdDraw.isKeyPressed(39)){
				move = "right";
			}
			if(move.length()!=0){
				totalEaten =  action(x,y,move,foodX,foodY);
			}
		}
		// Draw the end scene
		StdDraw.disableDoubleBuffering();
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text(35,25,"*********************");
		StdDraw.text(35,28,"Game Over!");
		StdDraw.text(35,31,"*********************");
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.setFont(new Font("Sans Serif",Font.BOLD,16));
		StdDraw.text(35,20,"Score: " + totalEaten);
	}
}