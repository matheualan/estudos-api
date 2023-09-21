--SELECT id_user, first_name, last_name, cpf, birth_date, create_at FROM tb_users;

--SELECT id_address, user_id, cep, logradouro, bairro, complemento, localidade, uf FROM tb_address;

--SELECT id_order, user_id, quantity, price, order_date FROM tb_orders;

--BUSCA DADOS DE USUÁRIO E PEDIDOS
--SELECT u.id_user, u.first_name, u.last_name, u.cpf,
--o.id_order, o.quantity, o.price, o.order_date
--FROM tb_users u
--JOIN tb_orders o ON u.id_user = o.user_id;

--BUSCA DADOS DE USUÁRIO, PEDIDO E ENDEREÇO
--SELECT u.id_user, u.first_name, u.last_name, u.cpf,
--o.id_order, o.quantity, o.price, o.order_date,
--a.cep, a.logradouro, a.bairro, a.localidade, a.uf
--FROM tb_users u
--JOIN tb_orders o ON u.id_user = o.user_id
--JOIN tb_address a ON u.id_user = a.user_id;

--BUSCA DADOS DE USUÁRIO E PEDIDOS DETERMINADO POR UM CPF
--SELECT u.id_user, u.first_name, u.last_name, u.cpf,
--o.id_order, o.quantity, o.price, o.order_date
--FROM tb_users u
--JOIN tb_orders o ON u.id_user = o.user_id
--WHERE u.cpf='22312312312';

--INSERT INTO tb_orders (user_id, quantity, price, order_date) values (2, 5, 60, NOW());