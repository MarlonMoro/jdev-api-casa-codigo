insert into Category (id, description) values(1, 'GERAL'), (2, 'LANCAMENTOS');

insert into autor values (1, '2020-10-10 03:00:00', 'descricao', 'marlon@gmail.com', 'marlon');

insert into Livro (id, titulo, sumario, preco, numero_paginas, isbn, data_publicacao, categoria_id, autor_id) values (1, 'Novo livro', 'sumario do livro', 120.00, 120, 'isbn do livro', '2020-10-10', 1, 1);

insert into pais (id, nome) values (1, 'Japao');

insert into pais (id, nome) values (2, 'Brasil');

insert into estado (id, nome, pais_id) values (1, 'Minas Gerais', 2), (2, 'Goias', 2);

insert into cupom (id, codigo, percentual_desconto, validade) values (1, 'BLACKFRIDAY', 10.0, '2020-11-11');




