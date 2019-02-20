package tokyo.mp015v.numhit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView

class Main4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        //テキスト
        val txt_title = findViewById<TextView>(R.id.main4_txt_title)

        //ナンバー
        val txt_num0 = findViewById<NumberPicker>(R.id.main4_num0)
        val txt_num1 = findViewById<NumberPicker>(R.id.main4_num1)
        val txt_num2 = findViewById<NumberPicker>(R.id.main4_num2)
        txt_num0.maxValue = 9
        txt_num0.minValue = 0
        txt_num1.maxValue = 9
        txt_num1.minValue = 0
        txt_num2.maxValue = 9
        txt_num2.minValue = 0

        //ボタン
        val btn_call = findViewById<Button>(R.id.main4_btn_call)

        //テキスト
        val txt_result = findViewById<TextView>( R.id.main4_txt_result )
        val txt_history1 = findViewById<TextView>( R.id.main4_txt_history1 )
        val txt_history2 = findViewById<TextView>( R.id.main4_txt_history2 )

        //ボタンコールのクリックイベント
        btn_call.setOnClickListener {

        }

    }
}
