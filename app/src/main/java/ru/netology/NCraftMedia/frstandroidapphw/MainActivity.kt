package ru.netology.NCraftMedia.frstandroidapphw

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.netology.NCraftMedia.frstandroidapphw.classPost.Post

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val post = Post (
            1,
            "Очень длинное имя пользователя, настолько длинное, что не умещается",
            "New comment",
            1602079965,//Время в секундах с unix на 10/07/2020 @ 2:12pm (UTC)
            false,
            2,
            true,
            2,
            false,
            0
        )

        commentTextTv.text = post.content
        dateTV.text = post.created.toString()
        likeTV.text = post.likedCount.toString()
        authorTV.text = post.author
        commentTV.text = post.commentCount.toString()
        shareTV.text = post.sharedCount.toString()

        //устанавливаем картинку для лайков
        if(post.likedByMe){
            likeButton.setImageResource(R.drawable.like) //Меняем картинку на то, что пост не лайкнут
        }
        else {
            likeButton.setImageResource(R.drawable.dont_like)
        }
        //Обработчик нажатия на лайк
        likeButton.setOnClickListener(){
            post.likedByMe = !post.likedByMe
            if(post.likedByMe){
                post.likedCount++
                likeTV.text = post.likedCount.toString()
                likeButton.setImageResource(R.drawable.like)
            }
            else {
                post.likedCount--
                likeTV.text = post.likedCount.toString()
                likeButton.setImageResource(R.drawable.dont_like)
            }
            //Обработчик изменения цвета текста для
            if (post.likedCount == 0) {
                likeTV.setTextColor(Color.rgb(255, 255, 255))
            }
            else {
                likeTV.setTextColor(Color.rgb(153, 153, 153))
            }

        }

        //устанавливаем картинку для комментариев
        if(post.commentByMe){
            commentButton.setImageResource(R.drawable.active_comment) //Меняем картинку на то, что пост не лайкнут
        }
        else {
            commentButton.setImageResource(R.drawable.no_active_comment)
        }

        //Устанавливаем цвет текста у кол-ва комментариев
        if (post.commentCount == 0) {
            commentTV.setTextColor(Color.rgb(255, 255, 255))
        }
        else {
            commentTV.setTextColor(Color.rgb(153, 153, 153))
        }
        //устанавливаем картинку для расширивания
        if(post.sharedByMe){
            shareButton.setImageResource(R.drawable.active_share) //Меняем картинку на то, что пост не лайкнут
        }
        else {
            shareButton.setImageResource(R.drawable.no_active_share)
        }

        //Устанавливаем цвет текста у кол-ва share
        if (post.sharedCount == 0) {
            shareTV.setTextColor(Color.rgb(255, 255, 255))
        }
        else  {
            shareTV.setTextColor(Color.rgb(153, 153, 153))
        }

        val timeJustNow:Long = System.currentTimeMillis()/1000//Получаем время на момент обновления страницы в милисекундах и делим на 1000, чтобы получить секунды
        val publishedAgo:Long = timeJustNow - post.created//Разница между созданием поста и временем на данные момент

        when(publishedAgo){
            in 1..60 ->  dateTV.text = "менее минуты назад"
            in 61..119 ->  dateTV.text = "минуту назад"
            in 120..300 -> dateTV.text = "две минуты назад"
            in 301..600 -> dateTV.text = "пять минуты назад"
            in 601..1_800 -> dateTV.text = "десять минуты назад"
            in 1_801..3_600 -> dateTV.text = "тридцать минут назад"
            in 3_601..7_200 -> dateTV.text = "час назад"
            in 7_201..18_000 -> dateTV.text = "три часа назад"
            in 18_001..86_400 -> dateTV.text = "пять часов назад"
            else -> dateTV.text = "более 1 дня назад"

        }

    }
    }

