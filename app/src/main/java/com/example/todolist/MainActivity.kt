package com.example.todolist


        import android.annotation.SuppressLint
                import android.app.AlertDialog
                import android.os.Bundle
                import android.text.InputType
                import android.widget.EditText
                import android.widget.Toast
                import androidx.activity.enableEdgeToEdge
                import androidx.activity.viewModels
                import androidx.appcompat.app.AppCompatActivity

                import androidx.recyclerview.widget.LinearLayoutManager
                import androidx.recyclerview.widget.RecyclerView
                import com.google.android.material.floatingactionbutton.FloatingActionButton


        class MainActivity : AppCompatActivity() {

            private val viewModel : TaskViewModel by viewModels()
            private  lateinit var  adapter: TaskAdapter






            @SuppressLint("MissingInflatedId")
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                enableEdgeToEdge()
                setContentView(R.layout.activity_main)

                val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                val floatingActionButton = findViewById<FloatingActionButton>(R.id.button)

                adapter = TaskAdapter(viewModel)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = adapter


                viewModel.tasks.observe(this) { taskList ->
                    adapter.updateList(taskList)
                }
                floatingActionButton.setOnClickListener {
                    showAddTaskDialog()
                }
            }
            private fun showAddTaskDialog() {
                val editText = EditText(this)
                editText.hint = "Enter task"
                editText.inputType = InputType.TYPE_CLASS_TEXT

                AlertDialog.Builder(this)
                    .setTitle("New Task")
                    .setView(editText)
                    .setPositiveButton("Add") { _, _ ->
                        val task = editText.text.toString()
                        if (task.isNotBlank()) {
                            viewModel.addTask(task)
                        } else {
                            Toast.makeText(this, "Task cannot be empty", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .setNegativeButton("Cancel", null)
                    .show()


            }
        }

