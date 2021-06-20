package com.rsschool.quiz.data

object DataHolder{
    val questions = listOf(
        Question("В каком году был выпущен первый фильм «Железный человек», начиная с кинематографической вселенной Marvel?",
        listOf(
            "2005" to false,
            "2008" to true,
            "2010" to false,
            "2012" to false,
            "2000" to false
        )),
        Question("Как называется молот Тора?",
        listOf(
            "ваны" to false,
            "Mjolnir" to true,
            "сонм богов" to false,
            "ведьма" to false,
            "эскалибур" to false
        )),
        Question("В «Невероятном Халке», что говорит Тони Таддеусу Россу в конце фильма?",
        listOf(
            "Что он хочет изучать Халка" to false,
            "Что он знает о ЩИТЕ" to false,
            "Что они собирают команду" to true,
            "Что Фаддей должен ему деньги" to false,
            "Что он ничего не знает" to false
        )),
        Question("Из чего сделан щит Капитана Америки?",
        listOf(
            "Adamantium" to false,
            "Вибраниум" to true,
            "прометий" to false,
            "Карбонадий" to false,
            "Титан" to false
        )),
        Question("Флеркены - это раса чрезвычайно опасных инопланетян, которая чем-то напоминает?",
        listOf(
            "Кошки" to true,
            "Утки" to false,
            "Рептилии" to false,
            "Еноты" to false,
            "Куриц" to false
        ))
    )
}

data class Question(
    val question:String,
    val answer:List<Pair<String,Boolean>>
)