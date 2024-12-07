CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    permissao ENUM('ADMIN', 'USER') NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    data_nascimento DATE,
    altura INT,
    peso_atual INT,
    nivel_atividade ENUM('SEDENTARIO', 'LEVE', 'MODERADO', 'INTENSO', 'MUITO_INTENSO'),
    objetivo ENUM('PERDA_PESO', 'MANUTENCAO', 'GANHO_MASSA')
);
