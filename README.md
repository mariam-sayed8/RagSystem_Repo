# 📦 PAC-RAG: Personalized AI Chat with RAG & Milvus

This project implements a simple **Retrieval-Augmented Generation (RAG)** system using:

* 🧠 **LangChain4j** (Java SDK)
* 🔍 **Ollama** for local embedding generation and chat completion
* 📦 **Milvus** as a vector database

The system allows you to:

* Index and embed text files into Milvus
* Store and retrieve relevant information based on user questions
* Use a local LLM (e.g., LLaMA2) to generate intelligent responses based on retrieved context

---

## 📁 Project Structure

```
pac_rag/
├── MainApp.java            # Main class to run the RAG system
├── EmbeddingService.java   # Handles embedding generation (via Ollama)
├── MilvusService.java      # Manages connection to Milvus and searching
├── MilvusFileIndexer.java  # Reads file and indexes its content into Milvus
├── LLMService.java         # Sends prompts to LLM and retrieves answers
├── HealthDataX.txt         # (Sample file to be indexed)
```

---

## 🛠 Requirements

* Java 17+
* Docker (for Milvus & Ollama)
* LangChain4j dependencies (added via Maven or Gradle)
* Internet (only for downloading models once, if using Ollama)

---

## 🧪 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/pac-rag.git
cd pac-rag
```

### 2. Start Milvus and Ollama Locally

**Milvus:**

```bash
docker compose -f milvus-standalone-docker-compose.yml up -d
```

**Ollama:**

```bash
ollama run all-minilm
ollama run llama2
```

> Make sure both are accessible at:
>
> * Milvus: `http://localhost:19530`
> * Ollama: `http://localhost:11434`

### 3. Run the Application

```bash
# From your IDE or command line
# Run MainApp.java
```

You will be prompted:

* Whether to index the file `HealthDataX.txt`
* To enter a question
* The system will search Milvus and respond using the LLM

---

## 🧠 Example Interaction

```
Do you want to index the file into Milvus? (yes/no): yes
✔ File indexed successfully into Milvus.

Ask your question: What are the symptoms of heart disease?

Sending question to LLM...
Answer:
- Chest pain
- Shortness of breath
- Fatigue
```

---

## ⚙ Configuration

You can customize:

* File to index: Change `HealthDataX.txt` in `MainApp.java`
* Model names: Modify in `LLMService.java` and `EmbeddingService.java`
* Embedding dimension: Adjust in `MilvusService.java` (`dimension(384)`)

---

## 🧾 Tech Stack

| Component | Tool / Model          |
| --------- | --------------------- |
| Vector DB | Milvus                |
| Embedding | all-minilm via Ollama |
| LLM       | LLaMA2 via Ollama     |
| Framework | LangChain4j           |
| Language  | Java                  |

---

## 🧹 TODO / Enhancements

* [ ] Add a REST API interface
* [ ] Support for multiple documents
* [ ] Web interface with Spring Boot
* [ ] Dynamic model switching

---

## 🤝 author 

Mariam Mohamed Sayed
GitHub: [https://github.com/mariam-sayed8]

---

## 💡 Inspiration

This project is inspired by the idea of **context-aware local chatbots** that utilize personal documents or datasets to answer user queries more accurately.

---
