### Summary

Этот микросервис отвечает за профиль и за все операции, которые отвечают за логику и за изменение сущностей данного микросервиса

### С чего начинать

Доступы. Если ты читаешь это, значит доступ к проекту у тебя уже есть
<ol>
<li>в докере подними БД в командной строке запущенной от имени администратора надо ввести <code>docker run --name some-postgres -e POSTGRES_PASSWORD=password -d user</code>, где --name имя контейнера, -e POSTGRES_PASSWORD= пароль БД, -d логин БД</li>
<li>так же в БД нужно создать схему: profile</li>
<li>добейся успешного запуска своего микросервиса</li>
</ol>



Swagger UI: http://localhost:8089/api/profile/swagger-ui/index.html

Swagger OpenAPI .json: http://localhost:8089/api/profile/v3/api-docs

