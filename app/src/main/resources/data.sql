INSERT INTO enfermeiros (nome, cpf, turno)
VALUES
('Ana Souza', '123.456.789-00', 'Manhã'),
('Carlos Lima', '987.654.321-00', 'Tarde'),
('Juliana Silva', '456.789.123-00', 'Noite');

INSERT INTO alas (descricao, nome, andar) VALUES
('Ala Cirúrgica', 'Cirurgia Geral', 2),
('Ala Pediátrica', 'Pediatria', 3),
('Ala Clínica', 'Clínica Médica', 1),
('Ala Ortopédica', 'Ortopedia', 4);

-- CORREÇÃO: A inserção na tabela de leitos foi ajustada para corresponder à entidade Leito.java
INSERT INTO leitos (descricao, numero_leitos) VALUES
('Leito Simples 101', 1),
('Leito Duplo 102', 2),
('Leito UTI 201', 1),
('Leito UTI Neonatal 202', 1);

