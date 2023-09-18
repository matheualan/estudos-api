--SELECT id_user, first_name, last_name, cpf, birth_date, create_at FROM tb_users;

--SELECT id_address, user_id, cep, logradouro, bairro, complemento, localidade, uf FROM tb_address;

--SELECT id_order, user_id, quantity, price, order_date FROM tb_orders;

--SELECT u.id_user, u.first_name, u.last_name, u.cpf, o.id_order, o.quantity, o.price, o.order_date
--FROM tb_users u
--JOIN tb_orders o ON u.id_user = o.user_id;