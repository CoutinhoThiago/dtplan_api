CREATE TABLE exercicios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    tipo INT,
    observacoes VARCHAR(255) DEFAULT '',

    musculo_alvo VARCHAR(100),
    series INT DEFAULT 3,
    repeticoes_min INT DEFAULT 6,
    repeticoes_max INT DEFAULT 12,
    carga DECIMAL(10, 2),

    duracao_minutos INT DEFAULT 30,
    intensidade VARCHAR(50)
);

-- Peitoral
INSERT INTO dtplan_api.exercicios (nome, tipo, musculo_alvo, series, repeticoes_min, repeticoes_max, carga, duracao_minutos) VALUES
('Supino inclinado com halter', 1, 'Peitoral Superior', 4, 10, 12, 22.00, NULL), -- ID: 1
('Supino reto com barra', 1, 'Peitoral', 3, 10, 15, 22.50, NULL), -- ID: 2
('Crosolver polia alta', 1, 'Peitoral Inferior', 3, 10, 15, 10.20, NULL); -- ID: 3

-- Costas
INSERT INTO dtplan_api.exercicios (nome, tipo, musculo_alvo, series, repeticoes_min, repeticoes_max, carga, duracao_minutos) VALUES
('Puxador frente pegada pronada', 1, 'Costas', 4, 10, 12, 52.00, NULL), -- ID: 4
('Remada baixa', 1, 'Costas', 3, 12, 15, 91.00, NULL), -- ID: 5
('Remada curvado pronada', 1, 'Romboide', 3, 10, 15, 12.5, NULL), -- ID: 6
('Remada com halter', 1, 'Costas', 3, 10, 15, 12.00, NULL), -- ID: 7
('Encolhimento de trapézio', 1, 'Trapézio', 4, 10, 12, 20.00, NULL), -- ID: 8
('Levantamento terra', 1, 'Lombar', 4, 6, 10, 30.00, NULL); -- ID: 9

-- Ombro
INSERT INTO dtplan_api.exercicios (nome, tipo, musculo_alvo, series, repeticoes_min, repeticoes_max, carga, duracao_minutos) VALUES
('Desenvolvimento com halter', 1, 'Deltoide Anterior', 3, 10, 15, 10.00, NULL), -- ID: 10
('Elevação lateral com halter', 1, 'Deltoide Medial', 3, 10, 15, 8.00, NULL), -- ID: 11
('Elevação lateral no cabo', 1, 'Deltoide Medial', 3, 10, 15, 3.20, NULL), -- ID: 12
('Crucifixo inverso com halter', 1, 'Ombro', 3, 10, 15, 8.00, NULL); -- ID: 13

-- Tríceps
INSERT INTO dtplan_api.exercicios (nome, tipo, musculo_alvo, series, repeticoes_min, repeticoes_max, carga, duracao_minutos) VALUES
('Triceps pulley', 1, 'Tríceps', 3, 10, 12, 45.00, NULL), -- ID: 14
('Tríceps testa', 1, 'Tríceps', 3, 10, 12, 17.50, NULL), -- ID: 15
('Tríceps francês', 1, 'Tríceps', 3, 10, 15, 8.00, NULL); -- ID: 16

-- Abdômen
INSERT INTO dtplan_api.exercicios (nome, tipo, musculo_alvo, series, repeticoes_min, repeticoes_max, carga, duracao_minutos) VALUES
('Abdominal máquina', 1, 'Abdômen', 3, 10, 12, 0, NULL), -- ID: 17
('Abdominal declinado', 1, 'Abdômen', 3, 20, 20, 0, NULL); -- ID: 18

-- Perna
INSERT INTO dtplan_api.exercicios (nome, tipo, musculo_alvo, series, repeticoes_min, repeticoes_max, carga, duracao_minutos) VALUES
('Agachamento Smith', 1, 'Quadríceps', 4, 15, 8, 17.5, NULL), -- ID: 19
('Agachamento Smith', 1, 'Quadríceps', 4, 12, 10, 22.5, NULL), -- ID: 20
('Agachamento Smith', 1, 'Quadríceps', 4, 10, 8, 27.5, NULL), -- ID: 21
('Agachamento Smith', 1, 'Quadríceps', 4, 8, 8, 35.0, NULL), -- ID: 22
('Leg Press', 1, 'Quadríceps', 3, 10, 12, 200.0, NULL), -- ID: 23
('Cadeira Extensora', 1, 'Reto femoral', 3, 10, 15, 45.0, NULL), -- ID: 24
('Cadeira Abdutora', 1, 'Abdutores', 3, 10, 15, 54.0, NULL), -- ID: 25
('Cadeira Abdutora Inclinada', 1, 'Abdutores', 3, 10, 15, 54.0, 15), -- ID: 26
('Elevação Pélvica', 1, 'Glúteo', 3, 10, 15, 20.0, NULL), -- ID: 27
('Cadeira Flexora Unilateral', 1, 'Posterior de coxa', 3, 10, 15, 18.0, NULL), -- ID: 28
('Cadeira Flexora', 1, 'Posterior de coxa', 3, 10, 15, 36.0, NULL), -- ID: 29
('Panturrilha Sentado', 1, 'Panturrilha', 4, 10, 12, 15.0, NULL), -- ID: 30
('Panturrilha em Pé Máquina', 1, 'Panturrilha', 4, 15, 20, 45.0, NULL), -- ID: 31
('Stiff', 1, 'Posterior de coxa', 3, 10, 12, 35.0, NULL), -- ID: 32
('Mesa Flexora', 1, 'Posterior de coxa', 3, 10, 12, 27.0, NULL); -- ID: 33

-- Bíceps
INSERT INTO dtplan_api.exercicios (nome, tipo, musculo_alvo, series, repeticoes_min, repeticoes_max, carga, duracao_minutos) VALUES
('Rosca direta na polia', 1, 'Bíceps', 3, 10, 15, 23.00, NULL), -- ID: 34
('Rosca 45°', 1, 'Bíceps', 3, 10, 15, 7.00, NULL), -- ID: 35
('Rosca martelo', 1, 'Bíceps braquial', 3, 10, 12, 7.00, NULL); -- ID: 36

-- Antebraço
INSERT INTO dtplan_api.exercicios (nome, tipo, musculo_alvo, series, repeticoes_min, repeticoes_max, carga, duracao_minutos) VALUES
('Rosca scott', 1, 'Antebraço', 3, 10, 15, 15.00, NULL), -- ID: 37
('Mesa flexora', 1, 'Antebraço', 3, 10, 12, 27.00, NULL), -- ID: 38
('Rosca punho', 1, 'Antebraço', 3, 10, 12, 20.00, NULL), -- ID: 39
('Stiff', 1, 'Antebraço', 3, 10, 12, 35.00, NULL); -- ID: 40

-- Cardio
INSERT INTO dtplan_api.exercicios (nome, tipo, musculo_alvo, series, repeticoes_min, repeticoes_max, carga, duracao_minutos) VALUES
('Esteira', 2, NULL, NULL, NULL, NULL, NULL, 30), -- ID: 41
('Simulador de Escada', 2, NULL, NULL, NULL, NULL, NULL, 20); -- ID: 42