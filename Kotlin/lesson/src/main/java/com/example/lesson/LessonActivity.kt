package com.example.lesson

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.BaseView
import com.example.lesson.entity.Lesson
import kotlinx.android.synthetic.main.activity_lesson.*

class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter> {

    private val lessonAdapter = LessonAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
        initToolbar()

        initList()

        initRefreshView()

        presenter.fetchData()
    }

    private fun initRefreshView() {
        swipe_refresh_layout.run {
            setOnRefreshListener {
                presenter.fetchData()
            }
            isRefreshing = true
        }
    }

    private fun initList() {
        list.run {
            layoutManager = LinearLayoutManager(this@LessonActivity)
            adapter = lessonAdapter
            addItemDecoration(DividerItemDecoration(this@LessonActivity, LinearLayout.VERTICAL))
        }
    }

    private fun initToolbar() {
        toolbar.run {
            inflateMenu(R.menu.menu_lesson)
            setOnMenuItemClickListener {
                presenter.showPlayback()
                false
            }
        }
    }

    fun showResult(lessons: List<Lesson>) {
        lessonAdapter.updateAndNotify(lessons)
        swipe_refresh_layout.isRefreshing = false
    }

    override val presenter: LessonPresenter by lazy {
        LessonPresenter(this)
    }
}