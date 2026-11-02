package ma.emsi.chibel.tp3chibel.llm;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.enterprise.context.Dependent;

import java.io.Serializable;

/**
 * Classe "métier" liée au LLM choisi (Gemini), qui s'appuie sur LangChain4j.
 * Reprise du TP 2 ; le rôle système (guide touristique) est donné par l'annotation
 * @SystemMessage de l'interface GuideTouristique, il n'y a donc plus de setter pour le rôle système.
 */
@Dependent
public class LlmClientPourGuideTouristique implements Serializable {

    /**
     * Guide touristique (service IA) dont l'implémentation est fournie par LangChain4j.
     */
    private GuideTouristique assistant;

    /**
     * Mémoire utilisée par l'assistant pour garder l'historique de la conversation.
     */
    private ChatMemory chatMemory;

    public LlmClientPourGuideTouristique() {
        // Récupère la clé secrète de l'API du LLM
        String cle = System.getenv("GEMINI_KEY");
        // Création du modèle
        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(cle)
                .modelName("gemini-3.5-flash-lite") // gemini-2.5-flash n'est plus disponible
                .build();
        // Création de l'assistant, avec une mémoire
        this.chatMemory = MessageWindowChatMemory.withMaxMessages(20);
        this.assistant = AiServices.builder(GuideTouristique.class)
                                   .chatModel(model)
                                   .chatMemory(chatMemory)
                                   .build();
    }

    /**
     * Envoie une requête au LLM et retourne sa réponse.
     *
     * @param question la question de l'utilisateur (ville ou pays).
     * @return la réponse du LLM.
     */
    public String envoyerRequete(String question) {
        return this.assistant.chat(question);
    }
}
