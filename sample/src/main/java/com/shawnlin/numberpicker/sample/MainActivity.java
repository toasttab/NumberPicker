package com.shawnlin.numberpicker.sample;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.widget.SeekBar;

import com.larswerkman.lobsterpicker.OnColorListener;
import com.larswerkman.lobsterpicker.sliders.LobsterShadeSlider;
import com.shawnlin.numberpicker.NumberPicker;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "NumberPicker";
    private static int MAX_VALUE_SEEK_BAR = 30;
    private static int MIN_VALUE_SEEK_BAR = 16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final NumberPicker numberPicker = findViewById(R.id.number_picker);
        LobsterShadeSlider textColorSlider = findViewById(R.id.slider_text_color);
        SeekBar textSizeSeekBar = findViewById(R.id.seek_bar_text_size);
        LobsterShadeSlider selectedTextColorSlider = findViewById(R.id.slider_selected_text_color);
        SeekBar selectedTextSizeSeekBar = findViewById(R.id.seek_bar_selected_text_size);
        LobsterShadeSlider dividerColorSlider = findViewById(R.id.slider_divider_color);

        textSizeSeekBar.setMax(MAX_VALUE_SEEK_BAR);
        textSizeSeekBar.setProgress(MIN_VALUE_SEEK_BAR);
        selectedTextSizeSeekBar.setMax(MAX_VALUE_SEEK_BAR);
        selectedTextSizeSeekBar.setProgress(20);

        numberPicker.setFormatter(R.string.number_picker_formatter);
        numberPicker.setTypeface(R.string.roboto_light, Typeface.NORMAL);

        numberPicker.setMaxValue(59);
        numberPicker.setMinValue(0);
        numberPicker.setValue(3);

        textSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numberPicker.setTextSize(spToPx((float) progress + MIN_VALUE_SEEK_BAR));
                numberPicker.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        textColorSlider.addOnColorListener(new OnColorListener() {
            @Override
            public void onColorChanged(@ColorInt int color) {
                numberPicker.setTextColor(color);
                numberPicker.invalidate();
            }

            @Override
            public void onColorSelected(@ColorInt int color) {}
        });

        selectedTextSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numberPicker.setSelectedTextSize(spToPx((float) progress + MIN_VALUE_SEEK_BAR));
                numberPicker.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        selectedTextColorSlider.addOnColorListener(new OnColorListener() {
            @Override
            public void onColorChanged(@ColorInt int color) {
                numberPicker.setSelectedTextColor(color);
                numberPicker.invalidate();
            }

            @Override
            public void onColorSelected(@ColorInt int color) {}
        });

        dividerColorSlider.addOnColorListener(new OnColorListener() {
            @Override
            public void onColorChanged(@ColorInt int color) {
                numberPicker.setDividerColor(color);
                numberPicker.invalidate();
            }

            @Override
            public void onColorSelected(@ColorInt int color) {}
        });

    }

    private float spToPx(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }

}
