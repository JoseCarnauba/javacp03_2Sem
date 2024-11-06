# Em Desenvolvimento

## Solicitação Checkpoint

## Sistema de Gerenciamento de Cursos e Matrículas

O seu time possui a missão de desenvolver um sistema web para gerenciar cursos e 
matrículas em uma instituição de ensino utilizando Spring MVC e Thymeleaf. O sistema 
deve incluir autenticação e autorização, além de implementar relacionamentos, 
validações, e interface de usuário.
Descrição do Sistema
O sistema será usado para gerenciar cursos e matrículas, onde administradores 
podem cadastrar cursos e alunos, e alunos autenticados podem se matricular em 
cursos.

## Requisitos do Projeto
• Autenticação e Autorização
o O sistema deve ter autenticação para acesso seguro e autorização para 
restringir acesso a funcionalidades específicas.
o Crie dois tipos de usuário:
▪ ADMIN: Pode gerenciar cursos e visualizar matrículas.
▪ ALUNO: Pode visualizar cursos disponíveis e realizar matrículas
Controle o acesso para que apenas ADMIN possa acessar as telas de 
gerenciamento de cursos e matrículas.

• Entidades e Relacionamentos
o Curso: Representa um curso disponível na instituição e deve conter 
informações como nome, descrição, data de início, carga horária etc.
o Matrícula: Representa a matrícula de um aluno em um curso e inclui 
informações como data de matrícula, status da matrícula etc.
o Aluno: Contém dados básicos dos alunos, como nome, e-mail, curso(s) 
nos quais está matriculado etc.

• Relacionamentos:
o Um Curso pode ter vários Alunos matriculados (relação muitos-paramuitos).
o Um Aluno pode se matricular em vários Cursos (relação muitos-paramuitos).
• Persistência com Spring Data JPA
o Utilize Spring Data JPA para gerenciar as operações de CRUD para as 
entidades Curso e Matrícula.
o Crie métodos de busca específicos para listar alunos por curso e cursos 
por aluno.

• Front-end com Thymeleaf
o Tela de Login: Permitir que usuários se autentiquem no sistema.
o Tela de Gerenciamento de Cursos: Permitir que o ADMIN cadastre, 
edite, visualize e exclua cursos.
o Tela de Visualização de Cursos: Permitir que alunos e administradores 
visualizem a lista de cursos disponíveis.
o Tela de Matrículas:
▪ ALUNO pode visualizar cursos e se matricular neles.
▪ ADMIN pode visualizar todas as matrículas e os alunos de cada 
curso.

▪ Validações
o Adicione validações nos campos das entidades:
o Nome do curso e descrição não podem ser vazios.
o Data de início do curso deve ser futura.
o O nome do aluno não pode ser vazio e o e-mail deve ser válido.

• Tratamento de Exceções
o Implemente tratamento de exceções para exibir mensagens amigáveis 
no front-end em caso de erros, como tentativa de duplicidade de 
matrícula ou tentativas de acesso não autorizadas.

• Configuração de Segurança com Spring Security
o Configure Spring Security para gerenciar autenticação e autorização.
o Criptografe as senhas dos usuários e configure login e logout.
o Redirecione cada tipo de usuário para a página adequada após o login 
(ex.: ADMIN é redirecionado para a tela de gerenciamento de cursos, 
enquanto ALUNO é redirecionado para a tela de visualização de cursos).

• Estrutura do Projeto
o Organize o projeto em pacotes conforme a arquitetura MVC:
o controller: Controladores para gerenciar requisições e navegação.
o service: Classes de serviço para implementar a lógica de negócios.
o repository: Interfaces de repositório para interação com o banco de 
dados.
o model: Classes das entidades.
o config: Configuração de segurança do Spring Security.

Critérios de Avaliação (Total: 100 pontos)
1. Autenticação e Autorização (20 pontos)
Implementação correta da segurança com Spring Security, controle de acesso e 
redirecionamento conforme o tipo de usuário.
2. Implementação de CRUD e Relacionamentos (30 pontos)
CRUD completo para Curso e Matrícula, incluindo relacionamento muitos-paramuitos e persistência.
3. Telas e Navegação (20 pontos)
Implementação de telas com Thymeleaf, navegação clara e adequada para cada 
tipo de usuário.
4. Validações e Exibição de Mensagens (10 pontos)
Validações de dados completas, com mensagens de erro amigáveis para 
validações e exceções.
5. Estrutura do Projeto e Organização (10 pontos)
Estrutura de pacotes organizada e adequada à arquitetura MVC.
6. Configuração e Uso de Spring Security (10 pontos)
Configuração completa de Spring Security, incluindo criptografia de senhas e 
controle de permissões.




