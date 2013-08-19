package com.niusounds.imageprocessing;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Viewを取得
		final ImageView imageView = (ImageView) findViewById(R.id.imageView);
		final Button btnProcess = (Button) findViewById(R.id.btnProcess);

		// ボタン押したら画像処理を実行
		btnProcess.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 読み込み
				Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.sample);

				// ここで処理をする
				RenderScript rs = RenderScript.create(getApplicationContext());
				ScriptC_imageProcess imageProcess = new ScriptC_imageProcess(rs);
				Allocation in = Allocation.createFromBitmap(rs, image);
				Allocation out = Allocation.createTyped(rs, in.getType());
				imageProcess.forEach_root(in, out);
				out.copyTo(image);

				// 表示
				imageView.setImageBitmap(image);
			}
		});
	}
}
