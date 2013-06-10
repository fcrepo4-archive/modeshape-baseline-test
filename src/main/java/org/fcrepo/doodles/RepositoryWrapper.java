/**
 *
 */

package org.fcrepo.doodles;

import java.net.URL;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.jcr.Repository;

import org.modeshape.jcr.ModeShapeEngine;
import org.modeshape.jcr.RepositoryConfiguration;

/**
 * @author frank asseg
 *
 */
public class RepositoryWrapper {

    private Repository repo;

    private ModeShapeEngine engine;

    public Repository getRepo() throws Exception{
        return repo;
    }

    /**
     * @return
     */
    private void init() throws Exception{
        engine = new ModeShapeEngine();
        engine.start();
        URL url =
                CreateNode.class.getClassLoader().getResource("modeshape-config3.json");
        RepositoryConfiguration config = RepositoryConfiguration.read(url);
        repo =  engine.deploy(config);
    }

    private void shutdown() throws Exception {
        Future<Boolean> shutdown = engine.shutdown();
        shutdown.get(3000, TimeUnit.MILLISECONDS);
    }

}
