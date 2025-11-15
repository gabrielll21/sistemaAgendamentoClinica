# 📘 **KARISE ESTÉTICA — Sistema de Agendamentos e Gestão**

Sistema completo desenvolvido em **Java + Spring Boot**, com **Thymeleaf**, **TailwindCSS** e **MySQL**, permitindo gerenciar:

* 👩‍💼 Clientes
* ✂️ Profissionais
* 💅 Serviços
* 📅 Agendamentos
* 📆 Calendário interativo na tela inicial com FullCalendar.js
* 🔍 Barra de pesquisa
* ✔️ Validação de CPF
* ✏️ CRUD completo (Criar, Listar, Editar, Deletar)

---

# 🚀 **Tecnologias Utilizadas**

### **Back-end**

* Java 17+
* Spring Boot (Web, Thymeleaf, JPA, Validation)
* Spring Data JPA
* MySQL

### **Front-end**

* Thymeleaf
* Tailwind CSS
* FullCalendar.js
* Lucide Icons

### **Banco**

* MySQL Community Server
* JPA + Hibernate

---

# 🏗️ **Como Rodar o Projeto**

## 1️⃣ **Pré-requisitos**

Instale:

* Java 17+
* Maven
* MySQL 5.7+ ou 8+
* IntelliJ / Eclipse (recomendado)

---

## 2️⃣ **Criando o banco de dados**

No MySQL, execute:

```sql
CREATE DATABASE karise_estetica;
```

---

## 3️⃣ **Configurar o application.properties**

Arquivo: `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/karise_estetica
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

---

## 4️⃣ **Rodando a aplicação**

No terminal:

```bash
mvn spring-boot:run
```

Ou pela IDE:
**Run → SpringBootApplication**

---

# 📂 **Estrutura Geral do Projeto**

```
src/main/java/com/karise_estetica
│
├── model/
│   ├── Cliente
│   ├── Profissional
│   ├── Servico
│   ├── Agendamento
│   └── StatusAgendamento (enum)
│
├── repository/
│   ├── ClienteRepository
│   ├── ProfissionalRepository
│   ├── ServicoRepository
│   └── AgendamentoRepository
│
├── service/
│   ├── ClienteService
│   ├── ProfissionalService
│   ├── ServicoService
│   └── AgendamentoService
│
├── controller/
│   ├── ClienteController
│   ├── ProfissionalController
│   ├── ServicoController
│   └── AgendamentoController
│
└── EsteticaApplication (main)
```

---

# 🖥️ **Front-end (Thymeleaf + Tailwind)**

O layout utiliza:

* Tailwind para estilização
* Lucide Icons para ícones
* Menu lateral fixo
* Responsividade
* Padrão visual consistente

---

# 🔍 **Barra de Pesquisa**

Na tela de clientes foi adicionada uma barra de busca:

✔️ filtra pelo nome
✔️ mantém a lista abaixo
✔️ não quebra o layout

A busca é processada no controller através de:

```java
List<Cliente> listarPorNomeContainingIgnoreCase();
```

---

# ✔️ **Validação de CPF**

O CPF é validado antes de salvar o cliente.

Validação implementada manualmente:

```java
public boolean isCPFValido(String cpf) {
    // remove máscara e valida dígitos verificadores
}
```

Se o CPF for inválido → mensagem de erro na tela.

---

# ✏️ **CRUD Completo (CRUD + Editar + Deletar)**

Cada entidade possui:

* Formulário de cadastro (create)
* Listagem (read)
* Edição (update)
* Exclusão com confirmação (delete)

Exemplo de rota:

```
/clientes/listar
/clientes/editar/{id}
/clientes/deletar/{id}
```

---

# 📅 **Agendamentos**

O agendamento relaciona:

* Cliente
* Profissional
* Serviço
* Data
* Hora
* Status
* Observações

Tudo mapeado com:

```java
@ManyToOne
private Cliente cliente;
```

---

# 📆 **Calendário na Tela Inicial (FullCalendar.js)**

A tela inicial possui um calendário que carrega automaticamente os agendamentos do banco.

### 1️⃣ Endpoint que retorna JSON:

```java
@GetMapping("/api/agendamentos")
@ResponseBody
public List<Map<String, String>> obterAgendamentos()
```

Formato retornado:

```json
[
  {
    "title": "Limpeza de Pele - Maria",
    "start": "2025-02-01T14:00:00"
  }
]
```

---

### 2️⃣ index.html carrega os eventos:

```js
events: "/agendamentos/api/agendamentos"
```

---

### 3️⃣ FullCalendar renderiza

* Mês / semana / dia
* Design integrado ao sistema
* Atualiza automaticamente quando agendamentos mudam

---

# 🧪 **Testes manuais recomendados**

Antes de entregar, testar:

✔️ Criar cliente com CPF válido
✔️ Buscar cliente na barra de pesquisa
✔️ Criar profissional
✔️ Criar serviço
✔️ Criar agendamento
✔️ Ver agendamento no calendário do index
✔️ Excluir agendamento
✔️ Editar cliente/profissional/serviço
✔️ Testar navegação no menu lateral

---

# 📦 **Possíveis Melhorias Futuras**

* Login + permissões (Admin / Funcionária)
* Envio automático de WhatsApp no agendamento
* Relatórios PDF
* Dashboard financeiro
* Calendário com arrastar e soltar
* Confirmação de agendamento por SMS

---

# 🏁 **Conclusão**

Este projeto demonstra:

✔️ domínio de Spring Boot
✔️ integração front/back com API JSON
✔️ banco relacional com relacionamentos
✔️ CRUD completo
✔️ validações
✔️ interface moderna com Tailwind
✔️ uso profissional de calendário FullCalendar