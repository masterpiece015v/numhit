package tokyo.mp015v.numhit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import java.util.*

class Main2Activity : AppCompatActivity() {

    val numArray = arrayOf(0,0,0,0,0,0,0,0,0,0)
    val compNum = arrayOf(0,0,0)
    var str_result :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //buttonの設定
        val btn_call = findViewById(R.id.main4_btn_call) as Button

        //TextViewの設定
        val txt_result = findViewById(R.id.main4_txt_result) as TextView
        val txt_history = findViewById(R.id.txt_history) as TextView

        //spinnerの設定
        val spn_num0 = findViewById(R.id.main4_num0) as NumberPicker
        val spn_num1 = findViewById(R.id.main4_num1) as NumberPicker
        val spn_num2 = findViewById(R.id.spn_num2) as NumberPicker

        spn_num0.maxValue = 9
        spn_num0.minValue = 0
        spn_num1.maxValue = 9
        spn_num1.minValue = 0
        spn_num2.maxValue = 9
        spn_num2.minValue = 0

        //コンピュータがランダムな数字を決める
        for( i in 0..2) {
            val rnd = Random()
            var index = rnd.nextInt(10)
            while(numArray[index] != 0 ){
                index = rnd.nextInt(10)
            }

            numArray[index] = 1

            Log.d("debug",index.toString() )
            compNum[i] = index
        }

        //回数
        var count = 1

        //ボタンをクリックした時の処理
        btn_call.setOnClickListener {
            var eat = 0
            var bit = 0
            val yourNum = arrayOf( spn_num0.value, spn_num1.value, spn_num2.value )

            Log.d("debut","${compNum[0]},${compNum[1]},${compNum[2]}")
            Log.d("debug","${yourNum[0]},${yourNum[1]},${yourNum[2]}")

            //同じ数字が出てくればクリックのイベントを終わる
            if( yourNum[0] == yourNum[1] || yourNum[1] == yourNum[2] || yourNum[0] == yourNum[2] ){
                Toast.makeText( applicationContext,"同じ数字はだめです", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //EATの計算
            for( i in 0..2 ){
                if(compNum[i]==yourNum[i]){
                    eat++
                }
            }

            //BITの計算
            for(i in 0..2){
                for( j in 0..2 ){
                    if( compNum[i] == yourNum[j] && i != j){
                        bit++
                    }
                }
            }

            //履歴の表示
            if( count >= 2) {
                if (str_result == null) {
                    str_result = txt_result.text.toString()
                } else {
                    str_result = txt_result.text.toString() + "\n" + str_result
                }

                txt_history.text = str_result
            }
            //結果の表示
            txt_result.text = "${count}回目 call:${yourNum[0]}${yourNum[1]}${yourNum[2]} eat:${eat},bit:${bit}"

            count++
        }
    }
}
