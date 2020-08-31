package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.Utils.toast
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken

class LessonPresenter(val activity: LessonActivity) {
    private var lessons: List<Lesson> = ArrayList()
    private val type = object : TypeToken<List<Lesson>>() {}.type


    fun fetchData() {
        HttpClient.get(LESSON_PATH, type, object : EntityCallback<List<Lesson>> {
            override fun onSuccess(entity: List<Lesson>) {
                this@LessonPresenter.lessons = entity
                activity.runOnUiThread { activity.showResult(entity) }
            }

            override fun onFailure(message: String?) {
                activity.runOnUiThread { toast(message) }
            }

        })
    }

    fun showPlayback() {
        activity.showResult(lessons.filter { lesson -> lesson.state == Lesson.State.PLAYBACK })

    }

    companion object {
        const val LESSON_PATH = "lessons"
    }
}