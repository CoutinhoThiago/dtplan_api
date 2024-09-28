CREATE TABLE treinos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    autor VARCHAR(255) NOT NULL,
    tipo ENUM('MUSCULACAO', 'AEROBICO'),
    descricao VARCHAR(255),
    usuario_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);


