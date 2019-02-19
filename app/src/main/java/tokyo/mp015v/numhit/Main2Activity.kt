package tokyo.mp015v.numhit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.random.Random

class Main2Activity : AppCompatActivity() {

    val numArray = arrayOf(0,0,0,0,0,0,0,0,0,0)

    val compNum = arrayOf(0,0,0)
    var compNumIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //コンピュータがランダムな数字を決める

        for( i in 0..2) {
            val rnd = Random(9)
            val index = rnd.nextInt()

            if(numArray[index] == 0 ){
                compNum[compNumIndex] = 1
            }
            
            //Log.d("debug",rnd.toString())
        }


    }
}
