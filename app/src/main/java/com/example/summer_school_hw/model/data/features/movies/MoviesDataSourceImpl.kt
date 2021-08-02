package com.example.summer_school_hw.model.data.features.movies

import com.example.summer_school_hw.model.data.dto.ActorDto
import com.example.summer_school_hw.model.data.dto.GenreDto
import com.example.summer_school_hw.model.data.dto.MovieDto
import com.example.summer_school_hw.model.data.dto.features.movies.MoviesDataSource

class MoviesDataSourceImpl : MoviesDataSource {
	override fun getMovies() = listOf(
		MovieDto(
			title = "Гнев человеческий",
			description = "Эйч — загадочный и холодный на вид джентльмен, но внутри него пылает жажда справедливости. Преследуя свои мотивы, он внедряется в инкассаторскую компанию, чтобы выйти на соучастников серии многомиллионных ограблений, потрясших Лос-Анджелес. В этой запутанной игре у каждого своя роль, но под подозрением оказываются все. Виновных же обязательно постигнет гнев человеческий.",
			rateScore = 3,
			ageRestriction = 18,
			imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5JP9X5tCZ6qz7DYMabLmrQirlWh.jpg",
			posterUrl="https://www.themoviedb.org/t/p/original/xkvPZY81ZliNh33tGsrUPTUFXXA.jpg",
			genre = listOf(GenreDto("Боевик", 28)),
			actors = listOf(ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lldeQ91GwIVff43JBrpdbAAeYWj.jpg", "Джейсон Стейтем"),
			ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8NvOcP35qv5UHWEdpqAvQrKnQQz.jpg","Джессика Макнэми"),
			ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dCfu2EN7FjISACcjilaJu7evwEc.jpg","Джош Лоусон")
			)
		),
		MovieDto(
			title = "Мортал Комбат",
			description = "Боец смешанных единоборств Коул Янг не раз соглашался проиграть за деньги. Он не знает о своем наследии и почему император Внешнего мира Шан Цзун посылает могущественного криомансера Саб-Зиро на охоту за Коулом. Янг боится за безопасность своей семьи, и майор спецназа Джакс, обладатель такой же отметки в виде дракона, как и у Коула, советует ему отправиться на поиски Сони Блейд.",
			rateScore = 5,
			ageRestriction = 18,
			imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pMIixvHwsD5RZxbvgsDSNkpKy0R.jpg",
			posterUrl="https://www.themoviedb.org/t/p/original/5dCmpud0T0RaHWrMWDpYCdton7W.jpg",
			genre = listOf(GenreDto("Боевик", 28)),
			actors = listOf(ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lkW8gh20BuwzHecXqYH1eRVuWpb.jpg", "Льюис Тан"),
			ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aAfaMEEqD8syHv5bLi5B3sccrM2.jpg","Холт МакКэллани"),
			ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/Am9vM77uZd9bGODugwmWtOfzx6E.jpg","Джош Хартнетт")
		)
		),
		MovieDto(
			title = "Упс... Приплыли!",
			description = "От Великого потопа зверей спас ковчег. Но спустя полгода скитаний они готовы сбежать с него куда угодно. Нервы на пределе. Хищники готовы забыть про запреты и заглядываются на травоядных. Единственное спасение — найти райский остров. Там простор и полно еды. Но даже если он совсем близко, будут ли рады местные такому количеству гостей?",
			rateScore = 5,
			ageRestriction = 6,
			imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/546RNYy9Wi5wgboQ7EtD6i0DY5D.jpg",
			posterUrl="https://www.themoviedb.org/t/p/original/sA0hHMLDdlCuIGpNz8LiPrSdTvR.jpg",
			genre = listOf(GenreDto("Семейный", 10751),
						GenreDto("Комедия", 35),
						GenreDto("Приключения", 12),
						GenreDto("Мультик", 16),),
			actors = listOf(ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/17gBs4aux2NcnMvf3DK5UKUFttn.jpg", "Тара Флинн"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/o8uE77C4wQHYHJW6En192kjxJGd.jpg","Ава Коннолли"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1ZRP9IfehCSx5OeBQQDcVPvKYD0.jpg","Мэри Мюррей"))
			),
		MovieDto(
			title = "The Box",
			description = "Уличный музыкант знакомится с музыкальным продюсером, и они вдвоём отправляются в путешествие, которое перевернёт их жизни.",
			rateScore = 4,
			ageRestriction = 12,
			imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fq3DSw74fAodrbLiSv0BW1Ya4Ae.jpg",
			posterUrl="https://www.themoviedb.org/t/p/original/wRAaTBveXfFvCrEvcL3Ep9hMq83.jpg",
			genre = listOf(GenreDto("Музыка", 10402)),
			actors = listOf(ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qqvTuk4CTvS1IE47CUozhcHVahz.jpg", "Чханёль"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/jpEPPXmVC3EDMqrDQDYyXEMYlah.jpg","Чо Даль Хван"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fDO7vJVRkZOOY1GtQMJzf4N136q.jpg","Ким Юн Сун"))
		),
		MovieDto(
			title = "Сага о Дэнни Эрнандесе",
			description = "Tekashi69 или Сикснайн — знаменитый бруклинский рэпер с радужными волосами — прогремел синглом «Gummo», коллабом с Ники Минаж, а также многочисленными преступлениями. В документальном расследовании о жизни и творчестве рэпера разворачивается настоящая гангстерская история, в которой количество обвинений растет пропорционально интернет-популярности.",
			rateScore = 2,
			ageRestriction = 18,
			imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5xXGQLVtTAExHY92DHD9ewGmKxf.jpg",
			posterUrl="https://www.themoviedb.org/t/p/original/5xXGQLVtTAExHY92DHD9ewGmKxf.jpg",
			genre = listOf(GenreDto("Документальное", 99),),
			actors = listOf(ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xAlvyeC9zLbygGMxmmyTHymwuZP.jpg", "6ix9ine"))
			),
		MovieDto(
			title = "Пчелка Майя",
			description = "Летний сбор пыльцы позади, и пчёлы пребывают в возбуждённом ожидании, ведь из столицы прибыл гонец, чтобы пригласить улей на соревнования за Кубок Мёда. Неутомимая Пчёлка Майя очень хочет принять участие в состязаниях, и тут выясняется, что улей может поучаствовать в Играх только собственным мёдом: половина всего летнего урожая родного улья пойдет на то, чтобы прокормить атлетов из других сборных.",
			rateScore = 4,
			ageRestriction = 0,
			imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xltjMeLlxywym14NEizl0metO10.jpg",
			posterUrl="https://www.themoviedb.org/t/p/original/tMS2qcbhbkFpcwLnbUE9o9IK4HH.jpg",
			genre = listOf(GenreDto("Приключения", 12),
				GenreDto("Мультик", 16),),
			actors = listOf(ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aVfEldX1ksEMrx45yNBAf9MAIDZ.jpg", "Бенсон Джек Энтони"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qCp0psD5qzguABpRxWmMuC04kcl.jpg","Франсис Берри"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8OpoYvO1QqBYRAp1LxxUIiRdQG0.jpg","Кристиан Харисиу"))
		),
		MovieDto(
			title = "Круэлла",
			description = "Невероятно одаренная мошенница по имени Эстелла решает сделать себе имя в мире моды.",
			rateScore = 4,
			ageRestriction = 12,
			imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hUfyYGP9Xf6cHF9y44JXJV3NxZM.jpg",
			posterUrl="https://www.themoviedb.org/t/p/original/wK9Kd0vyuqgt41AF8CMzMBAw9KJ.jpg",
			genre = listOf(GenreDto("Комедия", 35),
				GenreDto("Криминал", 80)),
			actors = listOf(ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2hwXbPW2ffnXUe1Um0WXHG0cTwb.jpg", "Эмма Стоун"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xr8Ki3CIqweWWqS5q0kUYdiK6oQ.jpg","Эмма Томпсон"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4nEKEWJpaTHncCTv6zeP98V0qGI.jpg","Джоэл Фрай"))
		),
		MovieDto(
			title = "Чёрная вдова",
			description = "Наташе Романофф предстоит лицом к лицу встретиться со своим прошлым. Чёрной Вдове придется вспомнить о том, что было в её жизни задолго до присоединения к команде Мстителей, и узнать об опасном заговоре, в который оказываются втянуты её старые знакомые - Елена, Алексей, также известный как Красный Страж, и Мелина.",
			rateScore = 3,
			ageRestriction = 16,
			imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mbtN6V6y5kdawvAkzqN4ohi576a.jpg",
			posterUrl="https://www.themoviedb.org/t/p/original/dShZ6Y3i1l6S3arJuk3P45eX6T.jpg",
			genre = listOf(GenreDto("Боевик", 28)),
			actors = listOf(ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6NsMbJXRlDZuDzatN2akFdGuTvx.jpg", "Скарлетт Йоханссон"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/75PvULemW8BvheSKtPMoBBsvPLh.jpg","Флоренс Пью"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/x87wtRVVvsOG7hkfJlzNkkfXQCN.jpg","Рэйчел Вайс"))
		),
	)
	override fun downloadMovies() = listOf(
		MovieDto(
		title = "Лука",
		description = "В прекрасном приморском городке на Итальянской Ривьере мальчик Лука проводит незабываемое лето, наполненное мороженым, пастой и бесконечными поездками на скутере. Вместе с ним эти приключения переживает его новый лучший друг, который на самом деле – морское чудовище из другого мира.",
		rateScore = 4,
		ageRestriction = 6,
		imageUrl = "https://www.themoviedb.org/t/p/original/8tABCBpzu3mZbzMB3sRzMEHEvJi.jpg",
		posterUrl="https://www.themoviedb.org/t/p/original/tiVmLZ6bTPH0bWTs13amLZhKL4o.jpg",
		genre = listOf(GenreDto("Мультик", 16),
			GenreDto("Приключения", 12),
			GenreDto("Семейный", 10751)),
		actors = listOf(ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oNLhzkZXNw1RNihne9P5q57cRcd.jpg", "Джейкоб Трамбле"),
			ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/cZdGLa78UP7VzMgNbDRnoaSkZm1.jpg","Джек Дилан Грейзер"),
			ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/r3QkZtW6Iaq56ziZqvPXAQLOcTr.jpg","Эмма Берман"))
	),
		MovieDto(
			title = "Космический джем: Новое поколение",
			description = "Чтобы спасти сына, знаменитый чемпион НБА отправляется в сказочный мир, где в команде мультяшек вынужден сражаться на баскетбольной площадке с цифровыми копиями знаменитых игроков.",
			rateScore = 4,
			ageRestriction = 6,
			imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5bFK5d3mVTAvBCXi5NPWH0tYjKl.jpg",
			posterUrl="https://www.themoviedb.org/t/p/original/1MMMF4KlwNFCSR1KzQQuxapUMAa.jpg",
			genre = listOf(GenreDto("Мультик", 16),
				GenreDto("Приключения", 12),
				GenreDto("Семейный", 10751),
				GenreDto("Комедия", 35),),
			actors = listOf(ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rmIYKVdYT60zfsqfjNuB71f1y82.jpg", "Леброн Джеймс"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/b1EVJWdFn7a75qVYJgwO87W2TJU.jpg","Дон Чидл"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2LXuhY0h5MlxLu7X55w4oAiVDWP.jpg","Седрик Джо"))
		),
		MovieDto(
			title = "Майор Гром: Чумной Доктор",
			description = "Майор полиции Игорь Гром известен всему Санкт-Петербургу своим пробивным характером и непримиримой позицией по отношению к преступникам всех мастей. Неимоверная сила, аналитический склад ума и неподкупность – всё это делает майора Грома идеальным полицейским, не знающим преград. Но всё резко меняется с появлением человека в маске Чумного Доктора.",
			rateScore = 3,
			ageRestriction = 12,
			imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wnesEmcq7xdw1Rm1Bn6XEFTkenR.jpg",
			posterUrl="https://www.themoviedb.org/t/p/original/4e3hHuXbg1Gh9xuz045bRoImArR.jpg",
			genre = listOf(GenreDto("Приключения", 12)),
			actors = listOf(ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nWM4bvQk5Pq74uu3r2wLawOHMJr.jpg", "Тихон Жизневский"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yOQX4JPwX1uhMyVT1jJigAk7iUr.jpg","Любовь Аксёнова"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u1i5aLdon5fHggZVcIL1VkytqBN.jpg","Алексей Маклаков"))
		),
		MovieDto(
			title = "Годзилла против Конга",
			description = "Человечество стало виной тому, что Годзилла и Конг вынуждены сойтись в неравной схватке. Организация «Монарх», отслеживающая жизнь монстров на земле, отправляет экспедицию в неизведанные земли острова Черепа, где надеется раскрыть тайну происхождения титанов.",
			rateScore = 5,
			ageRestriction = 12,
			imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
			posterUrl="https://www.themoviedb.org/t/p/original/rvaOf23XpF3Bbkxis6aTRPwUdJ2.jpg",
			genre = listOf(GenreDto("Боевик", 28),
				GenreDto("Фантастика", 14),
				GenreDto("Приключения", 12)),
			actors = listOf(ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hIuDik6KDmHLrqZWxBVdXzUw1kq.jpg", "Александр Скарсгард"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yzfxLMcBMusKzZp9f1Z9Ags8WML.jpg","Милли Бобби Браун"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/cVZaQrUY7F5khCBYdKDlEppHnQi.jpg","Ребекка Холл"))
		),
		MovieDto(
			title = "Монстрические питомцы",
			description = "В новом фильме любимый монструозный щенок Дракулы страдает от нехватки внимания со стороны хозяина, слишком занятого делами отеля, а ведь в Пёсике столько энергии и он бесконечно хочет играть в мяч! Драк решает найти своему питомцу компаньона, и после серии неудачных попыток Пёсик наконец-то выбирает себе друга, который грозит Дракуле еще большей суетой.",
			rateScore = 4,
			ageRestriction = 6,
			imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dkokENeY5Ka30BFgWAqk14mbnGs.jpg",
			posterUrl="https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dkokENeY5Ka30BFgWAqk14mbnGs.jpg",
			genre = listOf(GenreDto("Фантастика", 14),
				GenreDto("Фантастика", 14),
				GenreDto("Мультик", 16),
				GenreDto("Фантастика", 14)),
			actors = listOf(ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mLq5C8100kjFad6AMLNUXX3kcPf.jpg", "Брайан Халл"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wpYWJ7Kw5qcXEOZyTuIEsfqMqXq.jpg","Дженнифер Клуска"),
				ActorDto("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1ZdPd2JaiigVDB7hPxy29RAx9GY.jpg","Дерек Драймон"))
		),
		MovieDto(
			title = "Судная ночь навсегда",
			description = "Этим летом все правила будут нарушены. Группа мародеров решает, что ежегодная Судная ночь не должна заканчиваться с наступлением утра," +
					" а может продолжаться бесконечно. Никто больше не будет в безопасности.",
			rateScore = 4,
			ageRestriction = 18,
			imageUrl = "https://www.themoviedb.org/t/p/original/zEKa7Gfrr94V76w1A83khcML4Df.jpg",
			posterUrl = "https://www.themoviedb.org/t/p/original/kGUcCqsENSDI1oU3wU3bVVVf8v7.jpg",
			genre = listOf(GenreDto("Боевик", 28),
				GenreDto("Триллер", 53),),
			actors = listOf(
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
				)
			)
		),
		MovieDto(
			title = "Война будущего",
			description = "В будущем идёт разрушительный конфликт с инопланетной расой. В попытке переломить ход войны учёные начинают призывать в свою армию солдат из прошлого.",
			rateScore = 5,
			ageRestriction = 12,
			imageUrl = "https://www.themoviedb.org/t/p/original/jdzuxuA05lW4DzedZqa43SYhaZ.jpg",
			posterUrl = "https://www.themoviedb.org/t/p/original/ceiwpwT6bxuAKtK6suPUDbuWEHK.jpg",
			genre = listOf(GenreDto("Фантастика", 14),
				GenreDto("Боевик", 28)),
			actors = listOf(
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
				)
			)
		),
		MovieDto(
			title = "Тихое место 2",
			description = "474 дня прошло после нападения на Землю охотящихся на звук существ, семья Эбботт продолжает бороться за жизнь в полной тишине." +
					" Столкнувшись со смертельной угрозой в собственном доме, они вынуждены отправиться во внешний мир, где находят убежище и старого знакомого семьи.",
			rateScore = 3,
			ageRestriction = 16,
			imageUrl = "https://www.themoviedb.org/t/p/original/sdOTD3C9h4Etl6rYrtYLAJOuUWN.jpg",
			posterUrl = "https://www.themoviedb.org/t/p/original/uX4SrYuSaJAYsnFDZExyzjp4pZo.jpg",
			genre = listOf(GenreDto("Триллер", 53),),
			actors = listOf(
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
				)
			)
		),
		MovieDto(
			title = "Довод",
			description = "После теракта в киевском оперном театре агент ЦРУ объединяется с британской разведкой, " +
					"чтобы противостоять русскому олигарху, который сколотил состояние на торговле оружием. ",
			rateScore = 4,
			ageRestriction = 16,
			imageUrl = "https://www.themoviedb.org/t/p/original/m96dj44zZJ8TxpaMZDJv63TldZh.jpg",
			posterUrl = "https://www.themoviedb.org/t/p/original/epoids15egPuq933RvT0Y34L478.jpg",
			genre = listOf(GenreDto("Фантастика", 14),
				GenreDto("Боевик", 28),
				GenreDto("Триллер", 53),),
			actors = listOf(
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
				)
			)
		),
		MovieDto(
			title = "Кролик Питер 2",
			description = "Продолжение истории маленького и непоседливого кролика по имени Питер." +
					" Беатрис, Томас и крольчата, наконец, находят общий язык и начинают спокойную и размеренную жизнь за городом.",
			rateScore = 4,
			ageRestriction = 0,
			imageUrl = "https://www.themoviedb.org/t/p/original/gGQEXTCU5IawU6929RGBHXRWXjZ.jpg",
			posterUrl = "https://www.themoviedb.org/t/p/original/tIJQQPCDUzTzBh1ltiIJeKotYAR.jpg",
			genre = listOf(GenreDto("Семейный", 10751),
				GenreDto("Приключения", 12),),
			actors = listOf(
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
		),
	)
}