package tokyo.mp015v.numhit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import java.util.*

class Main2Activity : AppCompatActivity() {

    val numArray = arrayOf(0,0,0,0,0,0,0,0,0,0)
    val compNum = arrayOf(0,0,0)
    var compNumIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var str_result :String? = null

        //buttonの設定
        val btn_call = findViewById(R.id.btn_call) as Button

        //TextViewの設定
        val txt_result = findViewById(R.id.txt_result) as TextView

        //spinnerの設定
        val spn_num0 = findViewById(R.id.spn_num0) as Spinner
        val spn_num1 = findViewById(R.id.spn_num1) as Spinner
        val spn_num2 = findViewById(R.id.spn_num2) as Spinner
        val spn_num = arrayOf(0,1,2,3,4,5,6,7,8,9)

        spn_num0.adapter = ArrayAdapter<Int>( applicationContext,android.R.layout.simple_list_item_1,spn_num)
        spn_num1.adapter = ArrayAdapter<Int>( applicationContext,android.R.layout.simple_list_item_1,spn_num)
        spn_num2.adapter = ArrayAdapter<Int>( applicationContext,android.R.layout.simple_list_item_1,spn_num)

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
            val yourNum = arrayOf( spn_num0.selectedItem as Int, spn_num1.selectedItem as Int, spn_num2.selectedItem as Int )

            Log.d("debut","${compNum[0]},${compNum[1]},${compNum[2]}")
            Log.d("debug","${yourNum[0]},${yourNum[1]},${yourNum[2]}")

            //同じ数字が出てくればクリックのイベントを終わる
            if( yourNum[0] == yourNum[1] || yourNum[1] == yourNum[2] || yourNum[0] == yourNum[2] ){
                Toast.makeText( applicationContext,"同じ数字はだめです", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //eat
            for( i in 0..2 ){
                if(compNum[i]==yourNum[i]){
                    eat++
                }
            }

            //bit
            for(i in 0..2){
                for( j in 0..2 ){
                    if( compNum[i] == yourNum[j] && i != j){
                        bit++
                    }
                }
            }

            if(str_result==null){
                str_result = "${count}回目 call:${yourNum[0]}${yourNum[1]}${yourNum[2]} eat:${eat},bit:${bit}\n"
            }else{
                str_result = "${count}回目 call:${yourNum[0]}${yourNum[1]}${yourNum[2]} eat:${eat},bit:${bit}\n" + str_result
            }

            txt_result.text = str_result
            count++
        }
    }
}
