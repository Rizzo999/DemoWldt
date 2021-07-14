package docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import docker.utils.DockerConf;

import java.util.Iterator;
import java.util.List;

public class ListContainer {

    public static void main (String args[]) {

        DockerClient dockerClient = DockerConf.conf();

        //It is equivalent to --> docker ps -as
        List<Container> containers = dockerClient.listContainersCmd()
                .withShowSize(true)
                .withShowAll(true)
                .exec();


        Iterator it = containers.iterator();
        while (it.hasNext()) {

            //We can rude print "toString" method, by this way
            //System.out.println(it.next());

            //Or we can only print what we are interesting for, by using "get methods".
            //For this example, i'm interested only about the id, the name and the port binding of the container
            Container container = (Container) it.next();
            System.out.println("CONTAINER-ID: " + container.getId() + " CONTAINER-NAME: " + container.getNames()[0]
                    /*+ " PORTS: " + container.getPorts()[0]*/ + " CONTAINER-STATUS: " + container.getStatus());

        }

    }

}
