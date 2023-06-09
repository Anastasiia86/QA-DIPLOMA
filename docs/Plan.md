# План автоматизации тестирования, комплексного сервиса, взаимодействующего с СУБД и API Банка
## Перечень автоматизируемых сценариев:
### Исходные данные:
- Покупка по дебетовой карте (Статус "Approved") - 4444 4444 4444 4441;
- Покупка по дебетовой карте (Статус "Declined") - 4444 4444 4444 4442.
### Примечание:
- ссылки перехода на станицу покупки тура **доступны только при запущенной SUT**.
### Позитивные сценарии:
 **Оплата картой:**
    
- **Открыть** [страницу покупки тура](https://localhost:8080/) 
-  кликнуть кнопку "Купить" (оплата картой) 
-   в появившихся полях ввести валидные данные 
-    кликнуть кнопку "Продолжить";  
      `Результат:`
        - данные успешно отправлены и могут быть просмотрены в соответствующей базе данных;
        - появляется всплывающее окно "Операция одобрена Банком".

 **Покупка в кредит:**

- **Открыть** [страницу покупки тура](https://localhost:8080/) 
- кликнуть кнопку "Купить в кредит" 
-  в появившихся полях ввести валидные данные 
-   кликнуть кнопку "Продолжить";  
      `Результат:`
       
    - данные успешно отправлены и могут быть просмотрены в соответствующей базе данных;
        
     - появляется всплывающее окно "Операция одобрена Банком".
### Негативные сценарии:
1. **Пустая форма заявки для покупки:**

**Открыть** [страницу покупки тура](https://localhost:8080/) 
 - Оставить все поля формы без заполнения
 -  кликнуть кнопку "Купить" (оплата картой) 
 -   кликнуть кнопку "Продолжить";  

  `Результат:`
  
- данные не отправлены;
- под полями "Номер карты", "Месяц", "Год", "CVC/CVV", "Владелец" появляется сообщение об ошибке `Поле обязательно для заполнения`.
1. **Поле "Номер карты":**
  
  **Открыть** [страницу покупки тура](https://localhost:8080/) 
 - кликнуть кнопку "Купить" (оплата картой) 
 -  поле "Номер карты" **оставить пустым**, остальные поля заполнить валидными данными 
  -   кликнуть кнопку "Продолжить";  
  
  `Результат:`


- данные не отправлены;
    
- под полем "Номер карты" появится сообщение `Поле обязательно для заполнения`.
 


 **Открыть** [страницу покупки тура](https://localhost:8080/) 
-  кликнуть кнопку "Купить" (оплата картой) 
  -   в поле "Номер карты" **ввести не полный номер** (4444 4444 4444 444), остальные поля заполнить валидными данными 
-    кликнуть кнопку "Продолжить"; 
     
 `Результат:`
-    данные не отправлены;
- под полем "Номер карты" появится сообщение `Поле обязательно для заполнения`.

**Открыть** [страницу покупки тура](https://localhost:8080/) 
 -  кликнуть кнопку "Купить" (оплата картой) 
 -   в поле "Номер карты" **ввести не валидный номер**, остальные поля заполнить валидными данными 
 -   кликнуть кнопку "Продолжить";  
     
 `Результат:`

  - данные успешно отправлены и могут быть просмотрены в соответствующей базе данных;
  - появится всплывающее окно об отказе в проведении операции банком.
1. **Поле "Месяц":**
 
  **Открыть** [страницу покупки тура](https://localhost:8080/) 
  - кликнуть кнопку "Купить" (оплата картой) 
  -  поле "Месяц" **оставить пустым**, остальные поля заполнить валидными данными 
-   кликнуть кнопку "Продолжить";  
     
 `Результат:`

- данные не отправлены;
 - под полем "Месяц" появится сообщение `Поле обязательно для заполнения`.


 **Открыть** [страницу покупки тура](https://localhost:8080/) 
-  кликнуть кнопку "Купить" (оплата картой) 
-   в поле "Месяц" **ввести не валидный месяц** (в пределах 01-12), остальные поля заполнить валидными данными 
-    кликнуть кнопку "Продолжить"; 
      
 `Результат:`

  - данные не отправлены;
  - под полем "Месяц" появится сообщение `Неверно указан срок действия карты`.
 
 
 
**Открыть** [страницу покупки тура](https://localhost:8080/) 
 -  кликнуть кнопку "Купить" (оплата картой) 
 -   в поле "Месяц" **ввести не валидный месяц** (нижнее граничное значение 00), остальные поля заполнить валидными данными 
-    кликнуть кнопку "Продолжить";  

`Результат:`

 - данные не отправлены;    
 - под полем "Месяц" появится сообщение `Неверно указан срок действия карты`.

**Открыть** [страницу покупки тура](https://localhost:8080/) 
-  кликнуть кнопку "Купить" (оплата картой) 
  -   в поле "Месяц" **ввести не валидный месяц** (верхнее граничное значение 13), остальные поля заполнить валидными данными 
-    кликнуть кнопку "Продолжить";  

`Результат:`
      
- данные не отправлены;
 - под полем "Месяц" появится сообщение `Неверно указан срок действия карты`.
1. **Поле "Год":**

**Открыть** [страницу покупки тура](https://localhost:8080/) 
 -  кликнуть кнопку "Купить" (оплата картой) 
  -   поле "Год" **оставить пустым**, остальные поля заполнить валидными данными 
 -    кликнуть кнопку "Продолжить";  

 `Результат:`

 - данные не отправлены;
- под полем "Год" появится сообщение `Поле обязательно для заполнения`.
 
**Открыть** [страницу покупки тура](https://localhost:8080/) 
 -  кликнуть кнопку "Купить" (оплата картой)  
-  в поле "Год" **ввести не валидный год** (текущий год - 1), остальные поля заполнить валидными данными 
-   кликнуть кнопку "Продолжить";  

`Результат:`

 - данные не отправлены;
 - под полем "Год" появится сообщение `Истёк срок действия карты`.

**Открыть** [страницу покупки тура](https://localhost:8080/) 
 -  кликнуть кнопку "Купить" (оплата картой) 
 -  в поле "Год" **ввести не валидный год** (текущий год + 6), остальные поля заполнить валидными данными 
 -   кликнуть кнопку "Продолжить";  

`Результат:`

 - данные не отправлены;
 - под полем "Год" появится сообщение `Неверно указан срок действия карты`.
1.  **Поле "Владелец":**

**Открыть** [страницу покупки тура](https://localhost:8080/) 
  -  кликнуть кнопку "Купить" (оплата картой) 
  -   поле "Владелец" **оставить пустым**, остальные поля заполнить валидными данными 
-    кликнуть кнопку "Продолжить";  

 `Результат:`

 - данные не отправлены;
 - под полем "Владелец" появится сообщение `Поле обязательно для заполнения`.
 
 **Открыть** [страницу покупки тура](https://localhost:8080/) 
 -  кликнуть кнопку "Купить" (оплата картой) 
 -   в поле "Владелец" **ввести " "**(пробел), остальные поля заполнить валидными данными 
 -    кликнуть кнопку "Продолжить";  

`Результат:`
  
- данные не отправлены;
 - под полем "Владелец" появится сообщение `Поле обязательно для заполнения`.
 
 **Открыть** [страницу покупки тура](https://localhost:8080/) 
 - кликнуть кнопку "Купить" (оплата картой) 
 -  в поле "Владелец" **ввести #&**(два любых спецсимвола), остальные поля заполнить валидными данными 
-   кликнуть кнопку "Продолжить";  

 `Результат:`

 - данные не отправлены;
 - под полем "Владелец" появится сообщение `Неверный формат имени владельца`.

**Открыть** [страницу покупки тура](https://localhost:8080/) 
- кликнуть кнопку "Купить" (оплата картой) 
-  в поле "Владелец" **ввести двузначное число**(10-99), остальные поля заполнить валидными данными 
 -   кликнуть кнопку "Продолжить";  

`Результат:`

 - данные не отправлены;
 - под полем "Владелец" появится сообщение `Неверный формат имени владельца`.
6.  **Поле "CVC/CVV":**

**Открыть** [страницу покупки тура](https://localhost:8080/) 
 -  кликнуть кнопку "Купить" (оплата картой) 
 -  поле "CVC/CVV" **оставить пустым**, остальные поля заполнить валидными данными 
-   кликнуть кнопку "Продолжить";  

`Результат:`

  - данные не отправлены;
  - под полем "CVC/CVV" появится сообщение `Поле обязательно для заполнения`.

**Открыть** [страницу покупки тура](https://localhost:8080/) 
 -  кликнуть кнопку "Купить" (оплата картой) 
  -   в поле "CVC/CVV" **ввести двузначное число**(10-99), остальные поля заполнить валидными данными 
-    кликнуть кнопку "Продолжить";  

 `Результат:`

 - данные не отправлены;
 - под полем "CVC/CVV" появится сообщение `Неверный формат`.
  

 **`Аналогичные негативные сценарии актуальны для покупки в кредит.`**
  
## Перечень используемых инструментов с обоснованием выбора:
- Java - применяемый язык программирования;
- IntelliJ IDEA - программа для написания кода;
- Gradle - система автоматической сборки проектов в IntelliJ IDEA;
- JUnit 5 - среда тестирования на Java;
- Selenium/Selenide - фреймворки для тестирования;
- MySQL connector Java, PostgreSQL и Commons DBUtils, для доступа к базе данным из кода авто-тестов;
- Lombok, для упрощения написания кода;
- Faker - инструмент для генерации пользовательских данных для формы записи;
- Github, в качестве хранилища SUT и авто-тестов;
- Allure - фреймворк для создания отчетов о тестировании;
- Allure-Selenide, для интеграции одного инструмента с другим.
## Перечень и описание возможных рисков при автоматизации:
- Своеобразная настройка SUT при запуске (заявлена поддержка двух СУБД);
- Необходимость добавления новых тестов для заказчика, при отсутствии документации и спецификации на приложение;
- Зависимость авто-тестов от текущей реализации веб-элементов, даже не значительное их изменение может привести к падению авто-тестов;
- Недоступность сайта, вкладок;
- Неоправданная стоимость автоматизации;
## Интервальная оценка с учётом рисков (в часах):
- Ориентировочное время на тестирование (с учетом оцененных рисков) = 96 часов; с учетом рисков = 106 часов
## План сдачи работ (когда будут авто-тесты, результаты их прогона и отчёт по автоматизации):
1. Завершение тестирования приложения 22.03.2023
2. Отправка отчетной документации 27.03.2023