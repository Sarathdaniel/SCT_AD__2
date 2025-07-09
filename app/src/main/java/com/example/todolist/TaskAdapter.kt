package com.example.todolist



import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter (private val viewModel: TaskViewModel) : RecyclerView.Adapter<TaskAdapter.taskViewHolder>() {

    private var taskList: List<String> = emptyList()

    //step 1
    override fun getItemCount() = taskList.size

    //step2
    class taskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tasktextview: TextView = view.findViewById(R.id.taskText)
        val editButton: ImageButton = view.findViewById(R.id.btnEdit)
        val deleteButton: ImageButton = view.findViewById(R.id.btnDelete)
    }

//step 3


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): taskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view, parent, false)
        return taskViewHolder(view)
    }

    //step 4
    override fun onBindViewHolder(holder: taskViewHolder, position: Int) {
        val currentTask = taskList[position]
        holder.tasktextview.text = currentTask

        // Edit Task
        holder.editButton.setOnClickListener {
            val editText = EditText(holder.itemView.context)
            editText.setText(currentTask)

            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Edit Task")
                .setView(editText)
                .setPositiveButton("Update") { _, _ ->
                    val newText = editText.text.toString()
                    if (newText.isNotBlank()) {
                        viewModel.updateTask(position, newText)
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        // Delete Task
        holder.deleteButton.setOnClickListener {
            viewModel.deleteTask(position)
        }
    }

    // Helper method to update task list from LiveData
    fun updateList(newList: List<String>) {
        taskList = newList
        notifyDataSetChanged()
    }
}