package tokyo.mp015v.numhit

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import java.lang.NumberFormatException

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "数字を決める"

        //UI
        val txt_title2 = findViewById(R.id.txt_title2) as TextView
        val btn_decision = findViewById(R.id.btn_decision) as Button
        val num0 = findViewById(R.id.num0) as NumberPicker
        val num1 = findViewById(R.id.num1) as NumberPicker
        val num2 = findViewById(R.id.num2) as NumberPicker

        //NumberPickerの設定
        num0.maxValue = 9
        num0.minValue = 0
        num1.maxValue = 9
        num1.minValue = 0
        num2.maxValue = 9
        num2.minValue = 0

        //プレイヤー切り替え
        var sw = 0
        val p1_num = arrayListOf(0,0,0)
        val p2_num = arrayListOf(0,0,0)

        //数字を決定する
        btn_decision.setOnClickListener {
            when( sw ){
                0->{
                    //同じ数字がないかチェック
                    if( num0.value == num1.value || num1.value == num2.value || num0.value == num2.value ){
                        Toast.makeText(applicationContext,"同じ数字は設定できません",Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    //プレイヤー１
                    p1_num[0] = num0.value
                    p1_num[1] = num1.value
                    p1_num[2] = num2.value

                    sw=1
                    txt_title2.text = "プレイヤー２の数字３桁を決定してください。"
                    btn_decision.text = "プレイヤー２の数字決定"
                    num0.value = 0
                    num1.value = 0
                    num2.value = 0
                }
                1->{
                    //同じ数字がないかチェック
                    if( num0.value == num1.value || num1.value == num2.value || num0.value == num2.value ){
                        Toast.makeText(applicationContext,"同じ数字は設定できません",Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    //プレイヤー２
                    p2_num[0] = num0.value
                    p2_num[1] = num1.value
                    p2_num[2] = num2.value
                    sw=2
                    txt_title2.text = "それぞれのプレイヤーの数字が決定しました。"
                    btn_decision.text = "ゲームスタート"
                    num0.value = 0
                    num1.value = 0
                    num2.value = 0
                }
                2->{
                    //ゲームスタート
                    val intent = Intent(this,Main4Activity::class.java)
                    intent.putExtra("p1_num",p1_num)
                    intent.putExtra("p2_num",p2_num)
                    startActivity( intent )
                }
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
