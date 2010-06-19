package com.beanie.example.textview;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.StreamHandler;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.widget.TextView;

public class TestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TextView tvText = (TextView) findViewById(R.id.text);

		tvText.setText(Html.fromHtml(MockData.testContent1, imgGetter, null));
	}

	private ImageGetter imgGetter = new ImageGetter() {

		public Drawable getDrawable(String source) {
			HttpGet get = new HttpGet(source);
			DefaultHttpClient client = new DefaultHttpClient();
			Drawable drawable = null;
			try {
				HttpResponse response = client.execute(get);
				InputStream stream = response.getEntity().getContent();
				FileOutputStream fileout = new FileOutputStream(new File(
						Environment.getExternalStorageDirectory()
								.getAbsolutePath()
								+ "/test.jpg"));
				int read = stream.read();
				while (read != -1) {
					fileout.write(read);
					read = stream.read();
				}
				fileout.flush();
				fileout.close();
				drawable = Drawable.createFromPath(Environment
						.getExternalStorageDirectory().getAbsolutePath()
						+ "/test.jpg");
				drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable
						.getIntrinsicHeight());

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return drawable;
		}
	};
}