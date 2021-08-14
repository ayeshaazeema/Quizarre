package com.ayeshaazeema.quizapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.ayeshaazeema.quizapp.R
import com.ayeshaazeema.quizapp.databinding.FragmentQuizBinding
import com.ayeshaazeema.quizapp.model.Quiz

class QuizFragment : Fragment() {

    private val questions: MutableList<Quiz> = mutableListOf(
        Quiz(
            text = "Who painted the Mona Lisa?",
            answers = listOf("Leonardo da Vinci", "Vincent van Gogh", "Pablo Picasso", "Claude Monet")
        ),
        Quiz(
            text = "What year was Google images invented?",
            answers = listOf("2001", "2003", "2005", "2007")
        ),
        Quiz(
            text = "How many sides does a heptadecagon have?",
            answers = listOf("Seventeen", "Eighteen", "Nineteen", "Twenty")
        ),
        Quiz(
            text = "What did Jack Dorsey, Noah Glass, Biz Stone, and Evan Williams collectively create?",
            answers = listOf("Twitter", "Instagram", "Discord", "Tumblr")
        ),
        Quiz(
            text = "What is the capital city of Australia?",
            answers = listOf("Canberra", "Sydney", "Melbourne", "Perth")
        ),
        Quiz(
            text = "Which Disney Princess called Gus and Jaq friends?",
            answers = listOf("Cinderella", "Ariel", "Belle", "Aurora")
        ),
        Quiz(
            text = "How many permanent teeth does a dog have?",
            answers = listOf("42", "40", "44", "46")
        ),
        Quiz(
            text = "How many bones are in an adult human body?",
            answers = listOf("206", "200", "216", "260")
        ),
        Quiz(
            text = "How many islands does Indonesia have?",
            answers = listOf("17,508", "16,508", "17,608", "16,608")
        ),
        Quiz(
            text = "What is the oldest company in the world?",
            answers = listOf("Kongo Gumi", "St. Peter Stiftskulinarium", "Staffelter Hof", "Monnaie de Paris")
        ),
        Quiz(
            text = "What is the longest river in the world?",
            answers = listOf("Nile", "Amazon River", "Yangtze", "Yellow River")
        ),
        Quiz(
            text = "Which vitamin is the only one that you will not find in an egg?",
            answers = listOf("Vitamin C", "Vitamin A", "Vitamin D", "Vitamin E")
        ),
        Quiz(
            text = "What is the biggest animal in the world?",
            answers = listOf("Blue Whale", "Colossal Squid", "African Elephant", "Giraffe")
        ),
        Quiz(
            text = "How many bones does a shark have?",
            answers = listOf("None", "200", "10", "60")
        ),
        Quiz(
            text = "When was Netflix founded?",
            answers = listOf("1997", "2000", "2007", "2012")
        ),
        Quiz(
            text = "What was Disney's first movie?",
            answers = listOf("Snow White and the Seven Dwarfs", "Pinocchio", "Bambi", "Cinderella")
        ),
        Quiz(
            text = "Which language has the most words (according to dictionary entries)?",
            answers = listOf("English", "Russian", "Spanish", "Chinese")
        ),
        Quiz(
            text = "What is the largest bird of prey in the world?",
            answers = listOf("Andean Condor", "Harpy Eagle", "Bearded Vulture", "Eurasian Eagle Owl")
        ),
        Quiz(
            text = "What was the most downloaded app of 2020?",
            answers = listOf("TikTok", "Facebook", "WhatsApp", "Instagram")
        ),
        Quiz(
            text = "Which sportswear brand was known as Blue Ribbon Sports?",
            answers = listOf("Nike", "Adidas", "Puma", "Under Armour")
        )
    )

    private var questionIndex = 0
    lateinit var currentQuestion: Quiz
    lateinit var answers: MutableList<String>
    private val numberIndicatorQuestion = Math.min((questions.size + 1) / 2, 5)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val quizBinding = DataBindingUtil.inflate<FragmentQuizBinding>(
            inflater,
            R.layout.fragment_quiz,
            container,
            false
        )

        randomQuestion()

        quizBinding.quiz = this
        quizBinding.btnSubmit.setOnClickListener { view: View ->
            val checkedId = quizBinding.rgQuiz.checkedRadioButtonId
            if (-1 != checkedId) {
                var answerCorrectPosition = 0
                when (checkedId) {
                    R.id.rb_option_2 -> answerCorrectPosition = 1
                    R.id.rb_option_3 -> answerCorrectPosition = 2
                    R.id.rb_option_4 -> answerCorrectPosition = 3
                }
                if (answers[answerCorrectPosition] == currentQuestion.answers[0]) {
                    questionIndex++
                    if (questionIndex < numberIndicatorQuestion) {
                        currentQuestion = questions[questionIndex]
                        setNumberQuestion()
                        quizBinding.invalidateAll()
                    } else {
                        view.findNavController().navigate(R.id.action_quizFragment_to_wonFragment)
                    }
                } else {
                    view.findNavController().navigate(R.id.action_quizFragment_to_overFragment)
                }
            }
        }

        return quizBinding.root
    }

    private fun randomQuestion() {
        questions.shuffle()
        questionIndex = 0
        setNumberQuestion()
    }

    private fun setNumberQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answers.toMutableList()
        answers.shuffle()

        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.title_quizzare, questionIndex + 1, numberIndicatorQuestion)
    }
}