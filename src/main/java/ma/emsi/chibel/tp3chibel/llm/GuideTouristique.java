package ma.emsi.chibel.tp3chibel.llm;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * Service IA : guide touristique. L'implémentation est fournie par LangChain4j (AiServices).
 */
public interface GuideTouristique {

    /**
     * Envoie le nom d'une ville ou d'un pays au LLM et retourne sa réponse.
     *
     * @param villeOuPays nom de la ville ou du pays.
     * @return informations touristiques au format JSON.
     */
    @SystemMessage("""
            Tu es un guide touristique. Pour la ville ou le pays donné par l'utilisateur,
            indique les 2 principaux endroits à visiter, ainsi que le prix moyen d'un repas
            dans la devise du pays.
            Réponds au format JSON, avec exactement ce format :
            {
              "ville_ou_pays": "nom de la ville ou du pays",
              "endroits_a_visiter": ["endroit 1", "endroit 2"],
              "prix_moyen_repas": "<prix> <devise du pays>"
            }
            """)
    String chat(String villeOuPays);

    /**
     * Envoie le nom d'une ville ou d'un pays au LLM et retourne sa réponse,
     * avec le nombre d'endroits à visiter choisi par l'utilisateur.
     *
     * @param villeOuPays nom de la ville ou du pays.
     * @param nb nombre d'endroits à visiter.
     * @return informations touristiques au format JSON.
     */
    @SystemMessage("""
            Tu es un guide touristique. Pour la ville ou le pays donné par l'utilisateur,
            indique les {{nb}} principaux endroits à visiter, ainsi que le prix moyen d'un repas
            dans la devise du pays.
            Réponds au format JSON, avec exactement ce format :
            {
              "ville_ou_pays": "nom de la ville ou du pays",
              "endroits_a_visiter": ["endroit 1", "endroit 2", ...],
              "prix_moyen_repas": "<prix> <devise du pays>"
            }
            """)
    String chatAvecNbEndroits(@UserMessage String villeOuPays, @V("nb") int nb);
}
