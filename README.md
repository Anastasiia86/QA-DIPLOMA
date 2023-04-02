## Дипломный проект по профессии "Тестировщик"
---------------------------------------------
### Документация проекта
>> - [План автоматизации тестирования](https://github.com/Anastasiia86/QA-DIPLOMA/blob/main/docs/Plan.md).
>> - [Отчет о проведении тестирования](https://github.com/Anastasiia86/QA-DIPLOMA/blob/main/docs/Report.md).
>> - [Отчет о проведенной автоматизации тестирования](https://github.com/Anastasiia86/QA-DIPLOMA/blob/main/docs/Summary.md).

### Описание [задания](https://github.com/netology-code/qa-diploma).


### Запуск приложения
>> Открыть проект в IntelliJ IDEA.
>> 
>> Запустить Docker на ПК.
>> 
>> В терминале IDEA выполнить команду
`docker-compose up`
>>
>> В другой вкладке терминала выполнить команду для запуска приложения на СУБД MySQL
`java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar`
>>
## Приложение запускается на порту 8080:
 http://localhost:8080/

>> В другой вкладке теринала выполнить команду для запуска тестов
`.\gradlew test "-Ddb.url=jdbc:mysql://localhost:3306/app"`
>>
>> Для запуска приложения на СУБД PostgreSQL
`java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`
>>
>> Тесты запускаются командой в другой вкладке терминала
`.\gradlew test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`

>> После выполнения тестов для просмотра отчетов в другой вкладке терминала выполнить команды
`.\gradlew allureReport`
>>
>> затем
`.\gradlew allureServe `

## Завершение работы:

После окончания тестов по команде в термнале IDE:
`Ctrl+C`

## Остановка контейнеров:

По команде в термнале IDE:
`docker-compose down`