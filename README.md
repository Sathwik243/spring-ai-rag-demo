# Spring AI RAG Demo (Spring AI + Ollama + Angular)

This project demonstrates a local Retrieval-Augmented Generation (RAG) system using Spring Boot, Spring AI, Ollama, and an Angular frontend.  
It allows users to upload documents and chat with them using a locally hosted LLM.  
No external API keys are required.

---

## Features

- Upload PDF and Word documents
- Local embeddings and inference using Ollama
- RAG-based question answering
- Optimized document summarization
- Modern Angular standalone UI
- Fully local execution

---

## Architecture Overview

- Backend: Spring Boot with Spring AI
- LLM Runtime: Ollama (Gemma or Phi-3)
- Vector Store: In-memory vector store
- Frontend: Angular (standalone components)
- Communication: REST APIs

---

## Project Structure
spring-ai-rag-demo/
├── backend/
│ └── SpringAIDemo/
│ ├── src/
│ ├── pom.xml
│ └── application.yml
│
├── frontend/
│ └── rag-ui/
│ ├── src/
│ ├── package.json
│ └── angular.json
│
├── .gitignore
└── README.md

## Prerequisites

- Java 17+
- Maven 3.9+
- Node.js 18+
- Angular CLI
- Ollama installed locally

---

## Ollama Setup

Start Ollama and pull a model:
ollama pull gemma:2b

or for faster responses on CPU:
ollama pull phi3:mini

Ensure Ollama is running on:
http://localhost:11434


## Running the Backend
cd backend/SpringAIDemo
./mvnw spring-boot:run

Backend runs on: http://localhost:8080

## Running the Frontend
cd frontend/rag-ui
npm install
ng serve

Open in browser: http://localhost:4200


---

## How It Works

1. User uploads documents through the Angular UI
2. Backend extracts text and splits it into chunks
3. Chunks are embedded using Ollama
4. Embeddings are stored in an in-memory vector store
5. User queries trigger similarity search
6. Retrieved context is injected into the LLM prompt
7. The model generates an answer using only document context

---

## Sample Prompts

- Provide a concise summary of the document
- What problem does this document address?
- Explain the key benefits discussed
- Does the document mention blockchain?
- Summarize this document for a business audience

---

## Performance Notes

- RAG retrieval is limited to top-3 chunks for speed
- Summarization bypasses vector search for faster responses
- Phi-3 is recommended for CPU-only environments

---

## Limitations

- In-memory vector store (data is lost on restart)
- No authentication or user sessions
- Single-node local deployment

---

## Future Enhancements

- Persistent vector store
- Streaming responses
- Chat memory support
- Docker Compose setup
- Source citations in answers

---
