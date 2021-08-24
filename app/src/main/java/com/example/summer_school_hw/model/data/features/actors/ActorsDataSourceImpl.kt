package com.example.summer_school_hw.model.data.features.actors

import com.example.summer_school_hw.model.data.dto.ActorDto
import com.example.summer_school_hw.model.data.dto.GenreDto
import com.example.summer_school_hw.model.data.features.genres.GenresDataSouce

class ActorsDataSourceImpl: ActorsDataSource{
    override fun getActors()=listOf(ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lldeQ91GwIVff43JBrpdbAAeYWj.jpg", "Джейсон Стейтем"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8NvOcP35qv5UHWEdpqAvQrKnQQz.jpg","Джессика Макнэми"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dCfu2EN7FjISACcjilaJu7evwEc.jpg","Джош Лоусон"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lkW8gh20BuwzHecXqYH1eRVuWpb.jpg", "Льюис Тан"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aAfaMEEqD8syHv5bLi5B3sccrM2.jpg","Холт МакКэллани"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/Am9vM77uZd9bGODugwmWtOfzx6E.jpg","Джош Хартнетт"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/17gBs4aux2NcnMvf3DK5UKUFttn.jpg", "Тара Флинн"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/o8uE77C4wQHYHJW6En192kjxJGd.jpg","Ава Коннолли"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1ZRP9IfehCSx5OeBQQDcVPvKYD0.jpg","Мэри Мюррей"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qqvTuk4CTvS1IE47CUozhcHVahz.jpg", "Чханёль"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/jpEPPXmVC3EDMqrDQDYyXEMYlah.jpg","Чо Даль Хван"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fDO7vJVRkZOOY1GtQMJzf4N136q.jpg","Ким Юн Сун"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xAlvyeC9zLbygGMxmmyTHymwuZP.jpg", "6ix9ine"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aVfEldX1ksEMrx45yNBAf9MAIDZ.jpg", "Бенсон Джек Энтони"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qCp0psD5qzguABpRxWmMuC04kcl.jpg","Франсис Берри"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8OpoYvO1QqBYRAp1LxxUIiRdQG0.jpg","Кристиан Харисиу"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2hwXbPW2ffnXUe1Um0WXHG0cTwb.jpg", "Эмма Стоун"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xr8Ki3CIqweWWqS5q0kUYdiK6oQ.jpg","Эмма Томпсон"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4nEKEWJpaTHncCTv6zeP98V0qGI.jpg","Джоэл Фрай"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6NsMbJXRlDZuDzatN2akFdGuTvx.jpg", "Скарлетт Йоханссон"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/75PvULemW8BvheSKtPMoBBsvPLh.jpg","Флоренс Пью"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/x87wtRVVvsOG7hkfJlzNkkfXQCN.jpg","Рэйчел Вайс"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oNLhzkZXNw1RNihne9P5q57cRcd.jpg", "Джейкоб Трамбле"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/cZdGLa78UP7VzMgNbDRnoaSkZm1.jpg","Джек Дилан Грейзер"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/r3QkZtW6Iaq56ziZqvPXAQLOcTr.jpg","Эмма Берман"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rmIYKVdYT60zfsqfjNuB71f1y82.jpg", "Леброн Джеймс"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/b1EVJWdFn7a75qVYJgwO87W2TJU.jpg","Дон Чидл"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2LXuhY0h5MlxLu7X55w4oAiVDWP.jpg","Седрик Джо"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nWM4bvQk5Pq74uu3r2wLawOHMJr.jpg", "Тихон Жизневский"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yOQX4JPwX1uhMyVT1jJigAk7iUr.jpg","Любовь Аксёнова"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u1i5aLdon5fHggZVcIL1VkytqBN.jpg","Алексей Маклаков"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hIuDik6KDmHLrqZWxBVdXzUw1kq.jpg", "Александр Скарсгард"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yzfxLMcBMusKzZp9f1Z9Ags8WML.jpg","Милли Бобби Браун"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/cVZaQrUY7F5khCBYdKDlEppHnQi.jpg","Ребекка Холл"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mLq5C8100kjFad6AMLNUXX3kcPf.jpg", "Брайан Халл"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wpYWJ7Kw5qcXEOZyTuIEsfqMqXq.jpg","Дженнифер Клуска"),
        ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1ZdPd2JaiigVDB7hPxy29RAx9GY.jpg","Дерек Драймон"),
        ActorDto(
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aHYyysVwQ7D0iYLjlUgiknBQGwW.jpg",
            "Джош Лукас",
        ),
        ActorDto(
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nVhTkLBwZu4zOzYbnW9mtmHQfyg.jpg",
            "Анна де Лагуэра",
        ),
        ActorDto(
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5qoAmQpcPCjf4Pd6aTZOeINGYzk.jpg",
            "Тенорч Хуерта",
        ),
        ActorDto(
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/gXKyT1YU5RWWPaE1je3ht58eUZr.jpg",
            "Крис Пратт",
        ),
        ActorDto(
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wio1VaQDOggDfPOTJf2vxGfooxZ.jpg",
            "Ивонна Старковски",
        ),
        ActorDto(
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7kIiPojgSVNRXb5z0hiijcD5LJ6.jpg",
            "Джордж Симмонс",
        ),
        ActorDto(
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nPJXaRMvu1vh3COG16GzmdsBySQ.jpg",
            "Эмили Блант",
        ),
        ActorDto(
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/i8dOTC0w6V274ev5iAAvo4Ahhpr.jpg",
            "Киллиан Мёрфи",
        ),
        ActorDto(
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vn7hejb0IRFvSrZxpxqY9RbBxMe.jpg",
            "Милисенд Симонс",
        ),
        ActorDto(
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/eWNCcG4DqqhFKtWP56Ds8MiKPXB.jpg",
            "Джон Вашингтон",
        ),
        ActorDto(
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8A4PS5iG7GWEAVFftyqMZKl3qcr.jpg",
            "Роберт Паттинсон",
        ),
        ActorDto(
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/sJxj44aKdY0pjSIgnxBgMWLrQmw.jpg",
            "Элизабет Дебики",
        ),
        ActorDto(
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xGB0gfZ48M27gQjjL7inJIh1Pqj.jpg",
            "Джеймс Корден",
        ),
        ActorDto(
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4oQWCLK7gd6RNKF0WJipJo7TyFP.jpg",
            "Рози Бёрн",
        ),
        ActorDto(
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/uDbwncuKlqL0fAuucXSvgakJDrc.jpg",
            "Домлан Глисон"
        )
    )
}