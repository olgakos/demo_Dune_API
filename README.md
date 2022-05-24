Пример с API тестами на проект demo Dume

# Проект по автоматизации тестирования API (demo)
:earth_americas: https://github.com/ywalia01/dune-api
<br>Данный публичный API предоставляет следующий функционал: поиск цитат, запрос информации о книгах и фильмах вселенной "Дюны" :) 
<br>Сборка запущена с помощью инструмента удаленного запуска Jenkins
<br>Получены результаты теста в формате Allure отчета
<br>Добавлены артефакты тестирования (скриншот и видео)

## Содержание:
- [Технологии и инструменты](#watermelon-технологии-и-инструменты)
- [Реализованные проверки](#watermelon-Реализованные-проверки)
- [Запуск из терминала](#watermelon-Запуск-тестов-из-терминала)
- [Запуск тестов в Jenkins](#watermelon-Запуск-тестов-в-Jenkins)
- [Отчет о прохождении тестов в Allure report](#bellhop_bell-Отчет-о-прохождении-тестов-в-Allure-report)
- [Тестовая документация в Allure TestOps](#watermelon-Тестовая-документация-в-Allure-TestOps)
- [Видео прохождения тестов](#watermelon-Видео-прохождения-тестов)
- [Отчет в Telegram](#bellhop_bell-Отчет-в-Telegram)

## :watermelon: Технологии и инструменты
<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Idea.svg" width="50" height="50"  alt="IDEA" title="IJ IDEA"></a>  
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<!-- <a href="https://selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50" alt="Selenide"/></a> -->
<a href="https://rest-assured.io/"><img src="images/logo/RestAssured.svg" width="50" height="50"  alt="Rest-Assured" title="Rest-Assured"></a>
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a>  
<a href="https://qameta.io/"><img src="images/logo/Allure_TO.svg" width="50" height="50"  alt="AllureTestOps" title="AllureTestOps"></a>
<a href="https://habr.com/ru/post/438870/"><img src="images/logo/Lombok.svg" width="50" height="50"  alt="Lombok" title="Lombok"></a>  
<a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>
<a href="https://web.telegram.org/"><img width="50" height="50"  alt="Telegram" src="images/logo/Telegram.svg"></a>
</p>

Перечень технологий и инструментов, использованных при реализации этого проекта:
- Автотесты написаны на языке `Java`
- Для сборки проекта используется `Gradle`
- Проект `Lombok`- плагин компилятора, который добавляет в Java новые «ключевые слова» и превращает аннотации в Java-код.
- Java-библиотека для автоматизации тестирования REST-API: `REST Assured`

## :watermelon: Реализованные проверки
Примеры API тестов:
- [✓] Проверить статус код 200
- [✓] Проверяем, что список цитат не пуст
- [✓] Проверить, что цитата с id=14 содержит ожидаемый текст
- [✓] Запросить случайную книгу. У книги есть название и автор.
- [✓] Проверка книги подядковый №2. Проверить точное название книги.
- [✓] Проверка списка. Размер списка фильмов: 2 пункта
- [✓] Проверить 2 параметра сразу. Проверяем имена 2 режиссеров.

## :watermelon: Запуск тестов из терминала
Шаги:
1. Открыть терминал
2. Запустить на выполнение команду:
###### Локальный запуск:
```
gradle clean test
```
###### Удаленный запуск:
```

```

## :watermelon: Запуск тестов в Jenkins
<a href="https://jenkins.autotests.cloud/job/c011-Dune-API-OlgaKos-Tests/">Сборка в Jenkins</a>



Шаги:

## :bellhop_bell: Отчет о прохождении тестов в Allure report
После того как тесты завершились, можно получить визуальный Allure отчет.

###### Главная страница Allure-отчета содержит следующие информационные блоки:

###### Главный экран отчета (Owerwiev)
<p align="center">
<img title="Allure Graphics" src="images/screens/ScreenshotAllure1.jpg" alt="Allure Graphics">
</p>

###### Страница с проведенными тестами (Suites)
<p align="center">
<img title="Allure Graphics" src="images/screens/ScreenshotAllure2.jpg" alt="Allure Graphics">
</p>

## :watermelon: Тестовая документация в Allure TestOps

<p align="center">
  <img title="Allure TestOps" src="images/screens/ScreenshotAllureTestOps1" alt="TestOps">
</p>

Обзор тестовых запусков:
<p align="center">
  <img title="Allure TestOps" src="images/screens/ScreenshotAllureTestOps2" alt="TestOps">
</p>

## :watermelon: Видео прохождения тестов
К каждому тесту (в отчете) прилагается автоматически сгенерирвонное Selenoid видео. Пример:
<p align="center">
  <img title="Selenoid Video" src="images/screens/VideoExample.gif" alt="Video">
</p>

## :bellhop_bell: Отчет в Telegram
После завершения сборки специальный Telegram-бот отправляет сообщение с отчетом о прогоне тестов.
Чтобы видеть сообщения от бота, вступите (временно) в телеграм-группу `OlgaKos Bot_Group`

<p align="center">
<img title="Telegram Bot" src="images/screens/ScreenshotTelegram.jpg" alt="Telegram Bot">
</p>

----------
ЧЕРНОВИК