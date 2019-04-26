# user_catalog
<h2> Установка и сборка </h2>  

1. откройте Command Prompt(с помощью сочетания клавиш Win + R откройте  "Run" затем введит "cmd" и нажмите enter) 
2. склонируйте репозиторий с помощью команды git clone https://github.com/tohaheleneto/user_catalog.git 
3. перейдите в созданный репозиторий 
4. введите команду mvn install (maven подгрузит необходимые зависимости и соберёт исполняемый файл) 
<h2> Запуск </h2>  

1. перейдите в папку target(cd target) 
2. запустите команду java -jar user_catalog-alppha-1.0.jar 
<h2>особенности реализации</h2>  

в связи с запретом на установку дополнительного ПО в приложение была использована встраиваемая база данных(embedded database) H2.
<h2> Реализованный функционал </h2>  

1. на главной странице отображена краткая информация о пользователях (firstName,lastName,login) 
2. при нажатии на пользователя отображается страница с полной информацией о пользователе 
3. при нажатии на плюс вверху таблицы главной страницы,открывается страница добавления пользователя. 
  На ней необходимо заполнить все поля и загрузить фото.В случае,ели login уже существует в базе данных,выведется сообщение
  Login already exist,введённые данные (за исключением ролей и фото) сохранятся и их не нужно будет заполнять заново.
4. при переходе по адресу localhost:8080/addRole открывается страница добавления ролей в "справочник"(отдельная таблица в базе данных) 