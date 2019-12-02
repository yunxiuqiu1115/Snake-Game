package project;
import java.util.*;
import edu.princeton.cs.introcs.StdDraw;

public class Snake {
	// To store the coordinates of every part of the snake
	private static List<Double> x = new ArrayList<>();
	private static List<Double> y = new ArrayList<>();
	public static void canvas(List<Double> x,List<Double> y){
		StdDraw.setCanvasSize(650,650);
		StdDraw.setXscale(0,70);
		StdDraw.setYscale(0,70);
		StdDraw.picture(35,35,"/Users/qiuyunxiu/Desktop/project-yunxiuqiu1115/src/project/background.jpg");
		setSnake(x,y);
		StdDraw.text(35,27.5,"**********************");
		StdDraw.text(35,30,"*****Snake Game*****");
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
	public static int action(List<Double> x,List<Double> y,String move,double[] food){
		StdDraw.picture(35,35,"/Users/qiuyunxiu/Desktop/project-yunxiuqiu1115/src/project/background.jpg");
		int ret = 0;
		while(x.get(0)>0&&x.get(0)<70&&y.get(0)>0&&y.get(0)<70&&!(touchItSelf(x.get(0),y.get(0),x,y))){
			StdDraw.clear();
			if(!move.equals("up")&&StdDraw.isKeyPressed(40)){
				move = "down";
			}
			else if(!move.equals("down")&&StdDraw.isKeyPressed(38)){
				move = "up";
			}
			else if (!move.equals("left")&&StdDraw.isKeyPressed(39)){
				move = "right";
			}
			else if(!move.equals("right")&&StdDraw.isKeyPressed(37)){
				move="left";
			}

			if(foodIsEaten(x.get(0),y.get(0),food[0],food[1])){
				ret++;
				x.add(x.get(x.size()-1));
				y.add(y.get(y.size()-1));
				food = setNewFood();
				while(touchItSelf(food[0],food[1],x,y)){
					food = setNewFood();
				}
			}
			if(move.equals("up")){
				if(y.get(0)<70){
					y.add(0,y.get(0)+1);
					x.add(0,x.get(0));
					x.remove(x.size()-1);
					y.remove(y.size()-1);
					drawGame(x,y,food);
				}
			}
			else if(move.equals("down")){
				if(y.get(0)>0){
					y.add(0,y.get(0)-1);
					x.add(0,x.get(0));
					x.remove(x.size()-1);
					y.remove(y.size()-1);
					drawGame(x,y,food);
				}
			}
			else if(move.equals("left")){
				if(x.get(0)>0){
					x.add(0,x.get(0)-1);
					y.add(0,y.get(0));
					x.remove(x.size()-1);
					y.remove(y.size()-1);
					drawGame(x,y,food);
				}
			}else if(move.equals("right")){
				if(x.get(0)<70){
					x.add(0,x.get(0)+1);
					y.add(0,y.get(0));
					x.remove(x.size()-1);
					y.remove(y.size()-1);
					drawGame(x,y,food);
				}
			}


		}
		return ret;
	}
	public static void drawGame(List<Double> x,List<Double> y,double[] food){
		StdDraw.picture(35,35,"/Users/qiuyunxiu/Desktop/project-yunxiuqiu1115/src/project/background.jpg");
		for(int i=0;i<x.size();i++){
			StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
			StdDraw.filledSquare(x.get(i),y.get(i),0.5);
		}
		StdDraw.setPenColor((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
		StdDraw.filledCircle(food[0],food[1],0.5);
		StdDraw.show(10);
		StdDraw.pause(100);

	}
	public static boolean touchItSelf(double X,double Y,List<Double>x,List<Double> y){
		for(int i=1;i<x.size();i++){
			if(X==x.get(i)&&Y==y.get(i))return true;
		}
		return false;
	}
	public static double[] setNewFood(){
		double[] food = new double[2];
		food[0] = (int)(Math.random()*65)*1.0;
		food[1] = (int)(Math.random()*65)*1.0;
		return food;
	}
	public static boolean foodIsEaten(double X,double Y,double foodX,double foodY){
		if(X==foodX&&Y==foodY)return true;

		return false;
	}

	public static void main(String[] args){
		x.add(34.0);
		y.add(21.0);
		x.add(35.0);
		y.add(21.0);
		x.add(36.0);
		y.add(21.0);

		canvas(x,y);
		String move = "";
		int totalEaten = 0;
		double[] food = setNewFood();

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
				totalEaten =  action(x,y,move,food);
			}
		}

		StdDraw.disableDoubleBuffering();
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text(35,25,"*********************");
		StdDraw.text(35,28,"Game Over!");
		StdDraw.text(35,32,"*********************");
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.text(35,20,"Score: " + totalEaten);
	}
}