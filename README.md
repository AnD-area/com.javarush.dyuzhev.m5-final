## [REST API](http://localhost:8080/doc)

## Концепция:

- Spring Modulith
    - [Spring Modulith: достигли ли мы зрелости модульности](https://habr.com/ru/post/701984/)
    - [Introducing Spring Modulith](https://spring.io/blog/2022/10/21/introducing-spring-modulith)
    - [Spring Modulith - Reference documentation](https://docs.spring.io/spring-modulith/docs/current-SNAPSHOT/reference/html/)

```
  url: jdbc:postgresql://localhost:5432/jira
  username: jira
  password: JiraRush
```

- Есть 2 общие таблицы, на которых не fk
    - _Reference_ - справочник. Связь делаем по _code_ (по id нельзя, тк id привязано к окружению-конкретной базе)
    - _UserBelong_ - привязка юзеров с типом (owner, lead, ...) к объекту (таска, проект, спринт, ...). FK вручную будем
      проверять

## Аналоги

- https://java-source.net/open-source/issue-trackers

## Тестирование

- https://habr.com/ru/articles/259055/

Список выполненных задач:
1) +
2) Удалить социальные сети: vk, yandex;
3) Вынести чувствительную информацию в отдельный проперти файл;
Инфа(кроме тестовой) вынесена в системные переменные окружения, application-secrets.yaml
и в файл .env для корректной работы с nginx, .env в .gitignore;
4) Переделать тесты так, чтобы во время тестов использовалась in memory БД (H2),
а не PostgreSQL;
Сделал бины, application-test.yaml,поменял SQL(changelog-h2.sql + data-h2.sql),
данные вручную корректно заполняют H2, но mvn test (с профилем test) пока сыплет ошибки,
ещё не удалось разобраться до конца;
5) -;
6) Сделать рефакторинг метода com.javarush.jira.bugtracking.attachment.FileUtil#upload, 
чтобы он использовал современный подход для работы с файловой системой;
7) -;
8) -;
9) Написать Dockerfile для основного сервера;
10) Написать docker-compose файл для запуска контейнера сервера вместе с БД и nginx;
Должно корректно запускаться через docker compose up --build;
11) Добавить локализацию минимум на двух языках для шаблонов писем (mails)
и стартовой страницы index.html;
Сделал 3 языка для стартовой страницы, менять можно по ссылкам в GUI либо по ключам:
?lang=en
?lang=ru
?lang=it
12) -;