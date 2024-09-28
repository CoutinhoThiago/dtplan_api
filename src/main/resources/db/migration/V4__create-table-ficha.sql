-- Criação da tabela de ficha
CREATE TABLE fichas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    treino_id BIGINT,
    FOREIGN KEY (treino_id) REFERENCES treinos(id)
);

-- Criação da tabela de ficha_exercicios
CREATE TABLE fichas_exercicios (
    fichas_id BIGINT,
    exercicios_id BIGINT,
    FOREIGN KEY (fichas_id) REFERENCES fichas(id),
    FOREIGN KEY (exercicios_id) REFERENCES exercicios(id),
    PRIMARY KEY (fichas_id, exercicios_id)
);