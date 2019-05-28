package com.stameni.com.quizforall.ui.login.mainActivity.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.stameni.com.quizforall.R
import com.stameni.com.quizforall.common.extensions.listen
import com.stameni.com.quizforall.data.models.quiz.SingleQuiz
import kotlinx.android.synthetic.main.quiz_item.view.*
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId


class QuizAdapter :
    PagedListAdapter<SingleQuiz, QuizAdapter.QuizViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quiz_item, parent, false)
        return QuizViewHolder(view).listen { pos, _ ->
            val quiz = getItem(pos)
            Toast.makeText(parent.context, quiz!!.quizName, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val quiz = getItem(position)

        if (quiz != null) {
            holder.quizName.text = quiz.quizName
            holder.quizType.text = quiz.quizType
            var rating = 0.0
            quiz.ratings.forEach {
                rating += it.rating.toDouble()
            }
            rating /= quiz.ratings.size
            holder.quizRating.text = rating.toString()
            holder.quizPlays.text = quiz.numberOfPlays
            val instant = Instant.parse(quiz.lastEdited)
            val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()

            holder.quizDate.text = date.toString()
        }
    }


    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var quizName = itemView.tw_quiz_name
        var quizType = itemView.tw_quiz_type
        var quizRating = itemView.tw_quiz_rating
        var quizPlays = itemView.tw_quiz_plays
        var quizDate = itemView.tw_quiz_added
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SingleQuiz>() {
            override fun areItemsTheSame(oldItem: SingleQuiz, newItem: SingleQuiz): Boolean {
                return oldItem.id === newItem.id
            }

            override fun areContentsTheSame(oldItem: SingleQuiz, newItem: SingleQuiz): Boolean {
                return oldItem == newItem
            }
        }
    }
}