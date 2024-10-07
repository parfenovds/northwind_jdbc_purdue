-- Создание ролей
CREATE ROLE SalesRole;
CREATE ROLE HRRole;
CREATE ROLE CEORole;

-- Назначение прав для ролей
-- SalesRole: доступ только к таблицам Orders и Customers
GRANT SELECT ON Orders TO SalesRole;
GRANT SELECT ON Customers TO SalesRole;

-- HRRole: доступ только к таблице Employees
GRANT SELECT ON Employees TO HRRole;

-- CEORole: доступ ко всем таблицам (Orders, Customers, Employees)
GRANT SELECT ON Orders TO CEORole;
GRANT SELECT ON Customers TO CEORole;
GRANT SELECT ON Employees TO CEORole;

-- Создание пользователей
CREATE LOGIN User_CEO WITH PASSWORD = 'PasswordCEO!';
CREATE LOGIN User_HR WITH PASSWORD = 'PasswordHR!';
CREATE LOGIN User_Sales WITH PASSWORD = 'PasswordSales!';

-- Добавление пользователей в базу данных
USE Northwind;
CREATE USER User_CEO FOR LOGIN User_CEO;
CREATE USER User_HR FOR LOGIN User_HR;
CREATE USER User_Sales FOR LOGIN User_Sales;

-- Присваиваем роли пользователям
EXEC sp_addrolemember 'CEORole', 'User_CEO';
EXEC sp_addrolemember 'HRRole', 'User_HR';
EXEC sp_addrolemember 'SalesRole', 'User_Sales';