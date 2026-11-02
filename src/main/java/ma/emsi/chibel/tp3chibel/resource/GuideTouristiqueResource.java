package ma.emsi.chibel.tp3chibel.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Ressource REST qui donne des informations touristiques sur une ville ou un pays.
 */
@Path("/guide")
public class GuideTouristiqueResource {

    /**
     * Retourne un document JSON qui contient la liste des 2 principaux endroits à visiter
     * dans une ville ou un pays.
     *
     * @param villeOuPays nom de la ville ou du pays sur lequel on veut des informations.
     * @return document JSON avec les endroits à visiter.
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public String[] endroitsDeVisite(@PathParam("ville_ou_pays") String villeOuPays) {
        return new String[]{villeOuPays};
    }
}
