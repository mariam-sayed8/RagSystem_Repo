package com.pac_rag;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Initialize services for embedding, vector storage, and LLM
            EmbeddingService embeddingService = new EmbeddingService();
            MilvusService milvusService = new MilvusService();
            LLMService llmService = new LLMService();

            // Ask the user if they want to index the file
            System.out.print("Do you want to index the file into Milvus? (yes/no): ");
            String indexChoice = scanner.nextLine().trim().toLowerCase();

            // If yes, create and run the indexer
            if (indexChoice.equals("yes")) {
                MilvusFileIndexer indexer = new MilvusFileIndexer(
                        embeddingService.getModel(),
                        milvusService.getStore()
                );
                indexer.indexFile("HealthDataX.txt");
            }

            // Prompt user to enter a question
            System.out.print("\nAsk your question: ");
            String userQuestion = scanner.nextLine();

            // Convert question into embedding
            Embedding queryEmbedding = embeddingService.embed(userQuestion);

            // Search Milvus for the top 3 most relevant text segments
            List<EmbeddingMatch<TextSegment>> matches = milvusService.search(queryEmbedding, 3);

            // Build context from the retrieved segments
            StringBuilder contextBuilder = new StringBuilder();
            for (EmbeddingMatch<TextSegment> match : matches) {
                contextBuilder.append(match.embedded().text()).append("\n");
            }

            // Construct the final prompt for the language model
            String finalPrompt = "Answer the following question based on the context:\n\n"
                    + contextBuilder.toString() + "\n\n"
                    + "Question: " + userQuestion + "\n"
                    + "Answer:";

            // Send prompt to the LLM and get a response
            System.out.println("\nSending question to LLM...");
            String response = llmService.ask(finalPrompt);

            // Display the answer
            System.out.println(response.replaceAll("(?<=[.?!])\\s+", "\n"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
