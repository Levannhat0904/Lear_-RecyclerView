package com.nhatle.learnrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nhatle.learnrecyclerview.utils.Utils


class StudentAdapter(
    private val listener: OnStudentClickListener
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private val _students = mutableListOf<Student>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(rootView, listener)
    }

    override fun getItemCount(): Int {
        return _students.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        return holder.bind(_students[position])
    }
    fun updateData(students: List<Student>){
        val oldSize = students.size
        _students.clear()
        _students.addAll(students)
        notifyItemRangeRemoved(0, oldSize)
        notifyItemRangeInserted(0, students.size)
    }


    class StudentViewHolder(
        itemView: View,
        private val listener: OnStudentClickListener
    ) : RecyclerView.ViewHolder(itemView){
//        ánh xạ các thành phần giao diện ở đây
        private val imageAvatar:ImageView = itemView.findViewById(R.id.image_avatar)
        private val textFullName: TextView = itemView.findViewById(R.id.text_full_name)
        private val textGpa:TextView = itemView.findViewById(R.id.text_gpa)
        private val textStudentId:TextView = itemView.findViewById(R.id.text_student_id)
        fun bind(student: Student){
            textFullName.text = student.fullName.toString()
            textGpa.text = student.gpa.toString()
            textStudentId.text = student.id.toString()
            imageAvatar.setImageResource(Utils.getAvatar(student.gender))
            itemView.setOnClickListener {
                listener.onItemClick(student)
            }
        }
    }
    interface OnStudentClickListener {
        fun onItemClick(student: Student)
    }
}