package tokyo.mp015v.numhit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import java.util.*

class Main4Activity : AppCompatActivity() {

    var str_result1 : String? = null
    var str_result2 : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //buttonの設定
        val btn_call = findViewById(R.id.main4_btn_call) as Button

        //TextViewの設定
        val txt_result = findViewById(R.id.main4_txt_result) as TextView
        val txt_history1 = findViewById(R.id.main4_txt_history1) as TextView
        val txt_history2 = findViewById(R.id.main4_txt_history2) as TextView

        //spinnerの設定
        val spn_num0 = findViewById(R.id.main4_num0) as NumberPicker
        val spn_num1 = findViewById(R.id.main4_num1) as NumberPicker
        val spn_num2 = findViewById(R.id.main4_num2) as NumberPicker

        spn_num0.maxValue = 9
        spn_num0.minValue = 0
        spn_num1.maxValue = 9
        spn_num1.minValue = 0
        spn_num2.maxValue = 9
        spn_num2.minValue = 0

        //プレイヤーの選択した数
        val p1_num = intent.getIntArrayExtra("p1_num")
        val p2_num = intent.getIntArrayExtra("p2_num")

        //回数
        var count = 1

        //ボタンをクリックした時の処理
        btn_call.setOnClickListener {
            var eat = 0
            var bit = 0
            val selectNum = arrayOf( spn_num0.value, spn_num1.value, spn_num2.value )

            //同じ数字が出てくればクリックのイベントを終わる
            if( selectNum[0] == selectNum[1] || selectNum[1] == selectNum[2] || selectNum[0] == selectNum[2] ){
                Toast.makeText( applicationContext,"同じ数字はだめです", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val enemy = if( count % 2 == 1 ){ p2_num } else { p1_num }

            //EATの計算
            for( i in 0..2 ){
                if(enemy[i]==selectNum[i]){
                    eat++
                }
            }

            //BITの計算
            for(i in 0..2){
                for( j in 0..2 ){
                    if( enemy[i] == selectNum[j] && i != j){
                        bit++
                    }
                }
            }

            //履歴の表示

            var str_result : String? = if( count % 2 == 1 ){ str_result1 }else{ str_result2 }

            if( count >= 2) {
                if (str_result == null) {
                    str_result = txt_result.text.toString()
                } else {
                    str_result = txt_result.text.toString() + "\n" + str_result
                }

                if( count % 2 == 1) {
                    str_result1 = str_result
                    txt_history1.text = str_result
                }else{
                    str_result2 = str_result
                    txt_history2.text = str_result
                }
            }

            //結果の表示
            txt_result.text = "${count/2+1}回目 call:${selectNum[0]}${selectNum[1]}${selectNum[2]} eat:${eat},bit:${bit}"
            
            count++
        }
    }
}
