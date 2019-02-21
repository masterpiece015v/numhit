package tokyo.mp015v.numhit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast

class Main4Activity : AppCompatActivity() {
    var sw = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "二人で遊ぶ"

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

        //ナンバーを取り出す
        val p1_num = intent.getIntegerArrayListExtra("p1_num")
        val p2_num = intent.getIntegerArrayListExtra("p2_num")

        //ターンカウント
        var count = 1
        //ボタンコールのクリックイベント
        btn_call.setOnClickListener {

            //同じ数字はだめ
            if( txt_num0.value == txt_num1.value || txt_num1.value == txt_num2.value || txt_num0.value == txt_num2.value ){
                Toast.makeText(applicationContext,"同じ数字はだめです", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val player = arrayListOf(txt_num0.value,txt_num1.value,txt_num2.value)
            val enemy = if(sw==1){ p2_num } else { p1_num }
            var eat = 0
            var bit = 0

            //EAT,BITの計算
            for( i in 0..2 ){
                if( player[i] ==enemy[i]){
                    eat++
                }
                Log.d("debug","${player[i]},${enemy[i]},${eat}")
            }

            for( i in 0..2 ){
                for( j in 0..2 ){
                    if(player[i] == enemy[j] && i != j ){
                        bit++
                    }
                }
            }

            //結果の出力
            val str_result = if( sw == 1 ){ "${count}回目${player[0]}${player[1]}${player[2]}:EAT=${eat} BIT=${bit} "} else {"${count}回目${player[0]}${player[1]}${player[2]}:EAT=${eat} BIT=${bit} "}
            txt_result.text = str_result
            if( sw == 1 ){
                if( txt_history1.text == null ){
                    txt_history1.text = str_result
                }else{
                    txt_history1.text = str_result + "\n" + txt_history1.text
                }

                //3EAT
                if( eat == 3){
                    Toast.makeText( applicationContext,"3EATでプレイヤー１の勝利です",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                sw = 2
                txt_title.text = "プレイヤー２の番"
            }else{
                if( txt_history2.text == null ){
                    txt_history2.text = str_result
                }else{
                    txt_history2.text = str_result + "\n" + txt_history2.text
                }

                if( eat == 3){
                    Toast.makeText( applicationContext,"3EATでプレイヤー２の勝利です",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                sw = 1
                txt_title.text = "プレイヤー１の番"
                count++
            }
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
