package tokyo.mp015v.numhit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.*
import java.util.*

class Main2Activity : AppCompatActivity() {

    val numArray = arrayOf(0,0,0,0,0,0,0,0,0,0)
    var str_result :String? = null
    var compNum0 = 0
    var compNum1 = 0
    var compNum2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "一人で遊ぶ"

        //buttonの設定
        val btn_call = findViewById(R.id.main2_btn_call) as Button

        //TextViewの設定
        val txt_result = findViewById(R.id.main2_txt_result) as TextView
        val txt_history = findViewById(R.id.main2_txt_history) as TextView

        //spinnerの設定
        val spn_num0 = findViewById(R.id.main2_num0) as NumberPicker
        val spn_num1 = findViewById(R.id.main2_num1) as NumberPicker
        val spn_num2 = findViewById(R.id.main2_num2) as NumberPicker
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

            when( i ){
                0->{ compNum0 = index }
                1->{ compNum1 = index }
                2->{ compNum2 = index }
            }
        }

        //回数
        var count = 1

        //ボタンをクリックした時の処理
        btn_call.setOnClickListener {
            var eat = 0
            var bit = 0
            val yourNum0=  spn_num0.value
            val yourNum1 = spn_num1.value
            val yourNum2 = spn_num2.value

            Log.d("debug","compNum:${compNum0}${compNum1}${compNum2}")

            //同じ数字が出てくればクリックのイベントを終わる
            if( yourNum0 == yourNum1 || yourNum1 == yourNum2 || yourNum0 == yourNum2 ){
                Toast.makeText( applicationContext,"同じ数字はだめです", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //EATの計算
            if( compNum0 == yourNum0 ){
                eat++
            }
            if( compNum1 == yourNum1 ){
                eat++
            }
            if( compNum2 == yourNum2 ){
                eat++
            }

            //BITの計算
            if( yourNum0 == compNum1 || yourNum0==compNum2 ){
                bit++
            }
            if( yourNum1 == compNum0 || yourNum1 == compNum2 ){
                bit++
            }
            if( yourNum2 == compNum0 || yourNum2 == compNum1 ){
                bit++
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
            txt_result.text = "${count}回目 call:${yourNum0}${yourNum1}${yourNum2} eat:${eat},bit:${bit}"

            if( eat == 3 ){
                Toast.makeText( applicationContext,"3EAT!!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            count++
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){
            android.R.id.home->{finish()}
            else->super.onOptionsItemSelected(item)
        }

        return true
    }
}
