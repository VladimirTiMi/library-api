# Library API

`Library API` — это RESTful API для управления книгами в библиотеке. С его помощью можно добавлять, удалять, обновлять, а также выполнять поиск книг по различным параметрам. Проект построен на основе Spring Boot с использованием JPA для работы с базой данных.

---

## 📋 **Технологии**
### Основной стек:
- **Java 17**  
- **Spring Boot 3.4.4**
  - Spring Boot Data JPA
  - Spring Boot Web
- **PostgreSQL**  

### Инструменты:
- **Gradle**  
- **Lombok**  
- **Jakarta Validation API**

---

## 🚀 **Как запустить проект**

### **1. Убедитесь, что у вас установлены:**
- Java 17
- PostgreSQL
- Gradle

---

### **2. Склонируйте репозиторий:**
```bash
git clone <URL>
cd <project-folder>
```

---

### **3. Настройте базу данных**

1. В PostgreSQL создайте базу данных:
```sql
CREATE DATABASE library_db;
```

2. Проверьте файл `application.properties` и укажите свои данные для подключения:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/library_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

### **4. Запустите проект**
Для сборки и запуска используйте:
```bash
./gradlew bootRun
```
После успешного запуска API будет доступен по умолчанию на `http://localhost:8080`.

---

## 📖 **REST API Документация**

### 1. **Добавление книги**
**POST** `/api/books`  
Добавляет новую книгу в библиотеку.

- **Тело запроса (`JSON`):**
```json
{
  "title": "The Great Gatsby",
  "author": "F. Scott Fitzgerald",
  "genre": "Classic",
  "year": 1925
}
```
- **Ответ:**
  - **200 OK:** Добавленная книга.
  - **400 Bad Request:** Валидационная ошибка.

---

### 2. **Получение списка всех книг**
**GET** `/api/books`  
Возвращает список всех книг в библиотеке.

- **Ответ:**
  - **200 OK:** Список книг.

---

### 3. **Получение книги по ID**
**GET** `/api/books/{id}`  
Возвращает книгу с указанным `id`.

- **Ответ:**
  - **200 OK:** Найденная книга.
  - **404 Not Found:** Книга с указанным `id` не найдена.

---

### 4. **Удаление книги**
**DELETE** `/api/books/{id}`  
Удаляет книгу с указанным `id`.

- **Ответ:**
  - **200 OK:** Книга успешно удалена.
  - **404 Not Found:** Книга с указанным `id` не найдена.

---

### 5. **Поиск книг**
**GET** `/api/books/search`  
Возвращает список книг, соответствующих заданным параметрам.  
Можно использовать следующие параметры:
- `author` — поиск по имени автора;
- `genre` — поиск по жанру;
- `title` — поиск по названию.

**Примеры:**
1. **Поиск по автору:**
```bash
GET /api/books/search?author=tolkien
```
2. **Поиск по жанру:**
```bash
GET /api/books/search?genre=fantasy
```
3. **Поиск по названию:**
```bash
GET /api/books/search?title=hobbit
```

- **Ответ:**
  - **200 OK:** Список найденных книг.

---

## 🗂 **Структура проекта**

### Основные компоненты:
1. **Controller:**  
    - `BookController` — содержит эндпоинты для обработки HTTP-запросов.
    
2. **Service:**  
    - `BookService` — содержит бизнес-логику для работы с книгами.

3. **Repository:**  
    - `BookRepository` — интерфейс для работы с базой данных.

4. **Model:**  
    - `Book` — представляет сущность книги.

5. **Exception:**  
    - `BookNotFoundException` — обрабатывает исключения при отсутствии книги.

---

## ⚙️ **Основные конфигурации**

### **1. Файл `application.properties`**
Файл конфигурации для настройки базы данных и JPA.

**Пример:**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/library_db
spring.datasource.username=postgres
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---
