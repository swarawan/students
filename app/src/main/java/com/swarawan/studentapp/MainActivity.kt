package com.swarawan.studentapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.swarawan.studentapp.network.NetworkConfig
import com.swarawan.studentapp.network.response.StudentResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private val networkConfiguration = NetworkConfig()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonGetAllStudents.setOnClickListener { getAllStudents() }
    }

    private fun getAllStudents() {
        networkConfiguration.getServiceApi().getAll().enqueue(studentCallback)
    }

    private val studentCallback = object : Callback<StudentResponse> {
        override fun onFailure(call: Call<StudentResponse>, t: Throwable) {
            Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
        }

        override fun onResponse(call: Call<StudentResponse>, response: Response<StudentResponse>) {
            response.body()?.let { rsp ->
                val result = StringBuilder()
                rsp.data.forEach { data ->
                    result.append("${data.name} | ${data.email}")
                    result.append("\n")
                }
                textResult.text = result.toString()
            }
        }
    }
}
