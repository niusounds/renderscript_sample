#pragma version(1)
#pragma rs java_package_name(com.niusounds.imageprocessing)

void root(const uchar4 *in, uchar4 *out) {
	// ARGB各値を整数から小数に変換する
	float4 px = rsUnpackColor8888(*in);

	// ピクセルに処理を適用する
	px.rgb += 0.2f;

	// 確実に0.0-1.0の範囲に収まるようにする
	px = clamp(px, 0.0f, 1.0f);

	// 小数から整数に変換する
	*out = rsPackColorTo8888(px);
}