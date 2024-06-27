--INSERT INTO tb_clients (name, cpf, created_at) VALUES ('aaa', '111', CURRENT_TIMESTAMP);
--INSERT INTO tb_clients (name, cpf) VALUES ('bbb', '222');
--INSERT INTO tb_clients (name, cpf) VALUES ('ccc', '333');
--INSERT INTO tb_clients (name, cpf) VALUES ('ddd', '444');
--INSERT INTO tb_clients (name, cpf) VALUES ('eee', '555');

--% significa uma sequencia de caracteres sendo letras ou numeros
--SELECT id_client, name, cpf FROM tb_clients WHERE name LIKE 'M%';
--SELECT name FROM tb_clients WHERE name LIKE '%o';
--SELECT name FROM tb_clients WHERE name LIKE '%a%';