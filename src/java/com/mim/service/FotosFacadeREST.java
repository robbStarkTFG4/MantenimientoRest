/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.service;

import com.mim.entities.Fotos;
import com.mim.entities.Orden;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 *
 * @author marcoisaac
 */
@Stateless
@Path("com.mim.entities.fotos")
public class FotosFacadeREST extends AbstractFacade<Fotos> {

    @PersistenceContext(unitName = "MantenimientoRestPU")
    private EntityManager em;

    public FotosFacadeREST() {
        super(Fotos.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Fotos entity) {
        super.create(entity);
    }

    /**
     * persist list objetcs
     *
     * @param id
     * @param list
     */
    @POST
    @Path("objetos/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void photoList(@PathParam("id") int id, List<Fotos> list) {
        TypedQuery<Orden> query = em.createQuery("SELECT c FROM Orden c WHERE c.idorden = :id", Orden.class);
        query.setParameter("id", id);
        Orden orden = query.getSingleResult();
        if (orden != null) {
            for (Fotos fotos : list) {
                fotos.setOrdenIdorden(orden);
                super.create(fotos);
            }
        }
    }

    /**
     * handles file upload
     *
     * @param multiPart
     */
    @POST
    @Path("/prime")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void saveImagen2(MultipartFormDataInput multiPart) throws IOException {

        String nombre = null;
        InputStream stream = null;

        Map<String, List<InputPart>> list = multiPart.getFormDataMap();

        List<InputPart> inputParts = list.get("file");

        for (InputPart inputPart : inputParts) {

            stream = inputPart.getBody(InputStream.class, null);

            // byte[] bytes = IOUtils.toByteArray(inputStream);
        }

        List<InputPart> nombreParts = list.get("id");
        for (InputPart inputPart : nombreParts) {
            nombre = inputPart.getBodyAsString();
            // byte[] bytes = IOUtils.toByteArray(inputStream);
        }

        if ((stream != null)) {
            //System.out.println("Procces data");
            proccessStream(stream, nombre);

        }
        //  return Response.status(Response.Status.OK).build();
    }

    private void proccessStream(InputStream stream, String nombre) {
        OutputStream os = null;
        try {
            //File fileToUpload = new File(path + name);  LOCAL
            File fileToUpload = new File(System.getenv("OPENSHIFT_DATA_DIR") + "imagenes/" + nombre); // OPENSHIFT
            os = new FileOutputStream(fileToUpload);
            byte[] b = new byte[2048];
            int length;
            while ((length = stream.read(b)) != -1) {
                os.write(b, 0, length);
            }
        } catch (IOException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @GET
    @Path("/api/{id}")
    @Produces("image/jpg")
    public Response getFile(@PathParam("id") Integer id) {
        if (id != null) {

            TypedQuery<Fotos> query = em.createQuery("SELECT c FROM Fotos c WHERE c.idfotos = :id", Fotos.class);
            query.setParameter("id", id);
            String archivo = query.getSingleResult().getArchivo();
            String[] split = archivo.split("/");
            int size = split.length;
            final String name = split[size - 1];
            System.out.println("Valor " + name);

            if (name != null) {
                System.out.println("es nulo");
                //File file = new File("C:\\Users\\NORE\\Pictures\\imagenesVolante\\cople.jpg");
                File file = new File(System.getenv("OPENSHIFT_DATA_DIR") + "imagenes/" + name); // OPENSHIFT
                Response.ResponseBuilder response = Response.ok((Object) file);
                response.header("Content-Disposition",
                        "attachment; filename=" + name);
                response.header("nombre", name);

                //factory.close();
                //em.close();
                return response.build();
            } else {
                //factory.close();
                //em.close();
                return Response.noContent().build();
            }

        } else {
            return Response.noContent().build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Fotos entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Fotos find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Fotos> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Fotos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
