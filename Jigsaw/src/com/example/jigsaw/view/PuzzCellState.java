package com.example.jigsaw.view;

public class PuzzCellState {

	/**
	 * 拼图块对应的小图编号
	 */
	public int imgId;
	/**
	 * 拼图块在屏幕上显示位置x坐标
	 */
	public int posx;		
	/**
	 * 拼图块在屏幕上显示位置y坐标
	 */
	public int posy;		
	/**
	 * 拼图块堆叠显示的上下次序
	 */
	public int zOrder;		
	/**
	 * 拼图块是否被固定
	 */
	public boolean fixed; 
}
