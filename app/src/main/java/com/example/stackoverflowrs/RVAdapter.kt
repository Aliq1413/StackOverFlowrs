package com.example.stackoverflowrs
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stackoverflowrs.databinding.QuestionRowBinding
class RVAdapter (private val questions: ArrayList<Question>, private val context: Context):
    RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: QuestionRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(QuestionRowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val question = questions[position]
        holder.binding.apply {
            titleTv.text = question.title
            authorTv.text = "Author: ${question.author}"
            categoriesTv.text = question.categories
            questionCard.setOnClickListener {
                Alert(context, question.title, question.details)
            }
        }
    }

    override fun getItemCount() = questions.size
}