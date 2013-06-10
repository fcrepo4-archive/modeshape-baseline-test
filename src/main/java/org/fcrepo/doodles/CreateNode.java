
package org.fcrepo.doodles;

import java.util.Date;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */

/**
 * @author frank asseg
 *
 */
@Path("/")
@Component
public class CreateNode {

    @Autowired
    private RepositoryWrapper repo;

    @POST
    @Path("/node")
    public Response createNode() throws Exception {
        Session sess = repo.getRepo().login();

        String nodeName = RandomStringUtils.randomAlphabetic(16);
        Node root = sess.getRootNode();
        Node node = root.addNode(nodeName);
        node.setProperty("date-ingest", new Date().getTime());
        String path = node.getPath();
        long childCount = 0;
        try{
            childCount = root.getProperty("childCount").getLong();
        }catch(RepositoryException e){
            // ignore the missing property
        }
        root.setProperty("childCount", ++childCount);
        sess.save();
        sess.logout();
        return Response.ok("created node: " + path + "\n").build();
    }

    @GET
    @Path("/node-count")
    public Response retrieveNodeCount() throws Exception {
        Session sess = repo.getRepo().login();

        Node root = sess.getRootNode();
        NodeIterator it = root.getNodes();
        long count= root.getProperty("childCount").getLong();
        sess.logout();
        return Response.ok("root node has: " + count + " children\n").build();
    }

    @GET
    @Path("/node/{name}")
    public Response retrieveNode(@PathParam("name")
    final String name) throws Exception {
        Session sess = repo.getRepo().login();

        Node root = sess.getRootNode();
        Node node = root.getNode(name);
        String path = node.getPath();
        sess.logout();
        return Response.ok("fetched node: " + path + "\n").build();
    }
}
