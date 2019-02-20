package tokyo.mp015v.numhit

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_start1 = findViewById(R.id.btn_start1) as Button
        val btn_start2 = findViewById(R.id.btn_start2) as Button
        //１人用
        btn_start1.setOnClickListener {
            val intent = Intent( this, Main2Activity::class.java)
            startActivity( intent )
        }

        //２人用
        btn_start2.setOnClickListener {
            val intent = Intent( this,Main3Activity::class.java)
            startActivity( intent )
        }
    }
}
