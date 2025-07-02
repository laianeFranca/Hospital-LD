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

INSERT INTO leitos (codigo, numero, tipo) VALUES 
('L-101', 101, 'Simples'),
('L-102', 102, 'Duplo'),
('L-201', 201, 'UTI'),
('L-202', 202, 'UTI Neonatal');

