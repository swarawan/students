package com.swarawan.studentapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.swarawan.studentapp.network.NetworkConfig
import com.swarawan.studentapp.network.request.StudentRequest
import com.swarawan.studentapp.network.response.StudentData
import com.swarawan.studentapp.network.response.StudentListResponse
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
        buttonInsertStudent.setOnClickListener { insertStudent() }
    }

    private fun getAllStudents() {
        networkConfiguration.getServiceApi().getAll().enqueue(studentCallback)
    }

    private val studentCallback = object : Callback<StudentListResponse> {
        override fun onFailure(call: Call<StudentListResponse>, t: Throwable) {
            Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
        }

        override fun onResponse(call: Call<StudentListResponse>, listResponse: Response<StudentListResponse>) {
            listResponse.body()?.let { rsp ->
                val result = StringBuilder()
                rsp.data.sortedBy { it.id }
                        .forEach { data ->
                            result.append("${data.name} | ${data.email}")
                            result.append("\n")
                        }
                textResult.text = result.toString()
            }
        }
    }

    private fun insertStudent() {
        val name = inputName.text.toString()
        val email = inputEmail.text.toString()
        val request = StudentRequest(name, email)

        networkConfiguration.getServiceApi()
                .insert(request)
                .enqueue(object : Callback<StudentResponse> {
                    override fun onFailure(call: Call<StudentResponse>, t: Throwable) {
                        Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<StudentResponse>, response: Response<StudentResponse>) {
                        Toast.makeText(this@MainActivity, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                        getAllStudents()
                    }
                })
    }
}
