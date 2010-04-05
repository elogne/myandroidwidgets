package com.beanie.example.views;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.beanie.example.R;

public class MyProgressBar extends LinearLayout implements Runnable {

	private Context context;
	private ArrayList<ImageView> imageHolders;
	private ArrayList<String> images;
	private Thread animationThread;
	private boolean stopped = true;

	public MyProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		prepareLayout();
	}

	public MyProgressBar(Context context) {
		super(context);
		this.context = context;
		prepareLayout();
	}

	public void dismiss() {
		stopped = true;
		setVisibility(View.GONE);
	}

	private void prepareLayout() {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.myprogressbar, null);
		addView(view);

		imageHolders = new ArrayList<ImageView>();
		imageHolders.add((ImageView) view.findViewById(R.id.imgOne));
		imageHolders.add((ImageView) view.findViewById(R.id.imgTwo));
		imageHolders.add((ImageView) view.findViewById(R.id.imgThree));

		images = new ArrayList<String>();

		images.add("progress_1");
		images.add("progress_2");
		images.add("progress_3");
		images.add("progress_4");
		images.add("progress_5");
		images.add("progress_6");
		images.add("progress_7");
		images.add("progress_8");
		images.add("progress_9");
	}

	public void startAnimation() {
		setVisibility(View.VISIBLE);
		animationThread = new Thread(this, "Progress");
		animationThread.start();
	}

	@Override
	public void run() {
		while (stopped) {
			try {
				Thread.sleep(300);
				handler.sendEmptyMessage(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			int currentImage = 0;
			int nextImage = 0;
			for (ImageView imageView : imageHolders) {
				currentImage = Integer.parseInt(imageView.getTag().toString());
				if (currentImage < 9) {
					nextImage = currentImage + 1;
				} else {
					nextImage = 1;
				}
				imageView.setTag("" + nextImage);
				imageView.setImageResource(getResources().getIdentifier(
						images.get(nextImage - 1), "drawable",
						"com.beanie.example"));
			}
			super.handleMessage(msg);
		}

	};

}
