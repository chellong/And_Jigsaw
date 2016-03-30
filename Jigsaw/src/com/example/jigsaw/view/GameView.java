package com.example.jigsaw.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.example.jigsaw.R;

public class GameView extends View {
	/**
	 * 屏幕密度
	 */
	private Bitmap background;
	/**
	 * 拼图图像
	 */
	private Bitmap puzzImage;
	/**
	 * 拼图区域
	 */
	private Rect puzzRect;
	/**
	 * 平图缩略区域
	 */
	private Rect thumRect;
	/**
	 * 每个拼图快被打乱的范围
	 */
	private Rect cellRect;
	/**
	 * 平图快的宽度
	 */
	private double pw;
	/**
	 * 拼图块的高度
	 */
	private double ph;
	/**
	 * 画笔
	 */
	private Paint paint;

	/*
	 * 屏幕密度
	 */
	private float density;

	public GameView(Context context) {
		super(context);
		this.paint = new Paint();
		this.paint.setColor(Color.RED);
		this.paint.setAntiAlias(true);
		this.paint.setStyle(Paint.Style.STROKE);
		this.density = getContext().getResources().getDisplayMetrics().density;

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {

		/*
		 * 计算屏幕的大小，使高宽符合横屏的要求
		 */
		int screenW = (w > h) ? w : h;
		int screenH = (w > h) ? h : w;

		pw = (screenW - dip2px(10) - dip2px(10) - dip2px(10)) / 5.5;
		ph = (screenH - dip2px(20) - dip2px(20)) / 3.0;

		puzzRect = new Rect(dip2px(10), dip2px(20),
				dip2px(10) + (int) (4 * pw), (int) (3 * ph));

		this.thumRect = new Rect(dip2px(10) + (int) (4 * pw) + dip2px(10),
				dip2px(20), screenW - dip2px(10), (int) (dip2px(20) + ph));

		this.cellRect = new Rect(dip2px(10) + (int) (4 * pw) + dip2px(10),
				(int) (dip2px(20) + ph + dip2px(5)), (int) (screenW
						- dip2px(10) - pw), (int) (screenH - dip2px(20) - ph));
		/*
		 * 加载背景图片，按屏幕大小缩放成一张
		 */
		Bitmap bg = BitmapFactory.decodeResource(getResources(),
				R.drawable.wallpaper);

		background = Bitmap.createScaledBitmap(bg, screenW, screenH, false);
		bg.recycle();

		Bitmap pic = BitmapFactory.decodeResource(getResources(),
				R.drawable.pic02);

		bg.recycle();

		puzzImage = Bitmap.createScaledBitmap(pic, puzzRect.width(),
				puzzRect.height(), false);
		pic.recycle();

		super.onSizeChanged(screenW, screenH, oldw, oldh);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		canvas.drawBitmap(background, 0, 0, null);

		int alpha = paint.getAlpha();
		paint.setAlpha(120);

		canvas.drawBitmap(puzzImage, null, puzzRect, paint);
		/*
		 * 平图区边框
		 */
		canvas.drawRect(cellRect, paint);
		paint.setAlpha(alpha);
	
		/*
		 * 绘制水平格子线
		 */
		canvas.drawLine(puzzRect.left, (int) ph + puzzRect.top,
				puzzRect.right, (int) ph + puzzRect.top, paint);
		canvas.drawLine(puzzRect.left, (int) (ph * 2) + puzzRect.top,
				puzzRect.right, (int) (ph * 2) + puzzRect.top, paint);
		
		/*
		 * 绘制垂直格子线
		 */
		canvas.drawLine( (int) (pw * 1) + puzzRect.left,
				puzzRect.top,(int)(pw * 1) + puzzRect.left,puzzRect.bottom, paint);
		canvas.drawLine( (int) (pw * 2) + puzzRect.left,
				puzzRect.top,(int)(pw * 2) + puzzRect.left,puzzRect.bottom, paint);
		canvas.drawLine( (int) (pw * 3) + puzzRect.left,
				puzzRect.top,(int)(pw * 3) + puzzRect.left,puzzRect.bottom, paint);
		
		/*
		 * 绘制缩略图
		 */
		canvas.drawBitmap(puzzImage, null, thumRect, paint);
	}

	private int dip2px(float dip) {

		int px = (int) (dip * density + 0.5);
		return px;

	}

}
