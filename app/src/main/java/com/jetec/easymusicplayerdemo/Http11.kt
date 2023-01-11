package com.jetec.easymusicplayerdemo
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

//用來抓資料
class Http11 : AppCompatActivity() {
    private val TAG: String = Http11::class.java.simpleName
    private val url: String = "https://script.google.com/a/macros/gms.ndhu.edu.tw/s/AKfycbx_oiENFtBr0SDgcME6NaFt2bQErst12nj5ztkixK4IhPLLg0h2nMMF8h8252rNEnI/exec"
    var d: String = "10"
    var face: Char = '1'
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var client = OkHttpClient.Builder().build()
        var request = Request.Builder()
            .url(url)
            .build()
        CoroutineScope(Dispatchers.IO).launch {
            var response = client.newCall(request).execute()
            //Log.d(TAG,"${face}")

            response.body?.run{
                var dd = string()
                Log.d(TAG, "${dd}")
                face = dd[2]
                MainActivity.butt = face.digitToInt()
                println(MainActivity.butt)
//                if(face == '2')
//                    println(face)
//                    MainActivity.butt = 12
//                    println(MainActivity.butt)



//
                //當讀到數值就跳轉到音樂
//                val intent = Intent(this@Http11, MainActivity::class.java)
//                startActivity(intent)
            }


        }
    }

    fun musicPlay(view: View){
        val intent = Intent(this@Http11, MainActivity::class.java)
        startActivity(intent)
    }

    companion object{
        fun start(context:Context){
            val intent = Intent(context,Http11::class.java)
            context.startActivity(intent)
        }
    }

}
