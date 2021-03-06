package com.example.stackoverflowrs
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.HttpURLConnection
import java.net.URL
class MainActivity : AppCompatActivity() {
lateinit var questionsRV: RecyclerView
 override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        questionsRV= findViewById(R.id.questions_rv)
        FetchQuestions().execute()
        questionsRV.layoutManager = GridLayoutManager(this,1)
    }
    private inner class FetchQuestions: AsyncTask<Void, Void, ArrayList<Question>>(){

        var questions = arrayListOf<Question>()
        val parser = XMLParser()


        override fun doInBackground(vararg params: Void?): ArrayList<Question> {
            val url = URL("https://stackoverflow.com/feeds")
            val urlConnection = url.openConnection() as HttpURLConnection
            questions = urlConnection.inputStream?.let{ parser.parse(it) } as ArrayList<Question>
            return questions
        }

        override fun onPostExecute(result: ArrayList<Question>?) {
            super.onPostExecute(result)
            questionsRV.adapter = RVAdapter(questions, this@MainActivity)
        }
    }
}