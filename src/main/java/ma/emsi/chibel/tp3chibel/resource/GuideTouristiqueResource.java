package ma.emsi.chibel.tp3chibel.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ma.emsi.chibel.tp3chibel.llm.LlmClientPourGuideTouristique;

/**
 * Ressource REST qui donne des informations touristiques sur une ville ou un pays.
 */
@Path("/guide")
public class GuideTouristiqueResource {

    /**
     * Client du LLM qui joue le rôle de guide touristique.
     */
    @Inject
    private LlmClientPourGuideTouristique llmClient;

    /**
     * Retourne un document JSON qui contient la liste des 2 principaux endroits à visiter
     * dans une ville ou un pays, ainsi que le prix moyen d'un repas.
     * Les informations sont données par le LLM.
     *
     * @param villeOuPays nom de la ville ou du pays sur lequel on veut des informations.
     * @return document JSON avec les endroits à visiter et le prix moyen d'un repas.
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public String endroitsDeVisite(@PathParam("ville_ou_pays") String villeOuPays) {
        return llmClient.envoyerRequete(villeOuPays);
    }
}
