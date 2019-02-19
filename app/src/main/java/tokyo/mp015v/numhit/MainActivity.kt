package tokyo.mp015v.numhit

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_start = findViewById(R.id.btn_start) as Button

        btn_start.setOnClickListener {
            val intent = Intent( this, Main2Activity::class.java)
            startActivity( intent )

        }
    }
}
