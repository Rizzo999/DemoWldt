package docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import docker.utils.DockerConf;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class StopWldtContainerConf {

    public static void main (String args[]){

        String containerId = null;

        DockerClient dockerClient = DockerConf.conf();

        List<Container> containers = dockerClient.listContainersCmd()
                .withShowSize(true)
                .withShowAll(true)
                .exec();

        Iterator it = containers.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter container id that you want stop: ");
        containerId = scanner.nextLine();

        //Stop or kill the container
        dockerClient.stopContainerCmd(containerId).exec();
        dockerClient.removeContainerCmd(containerId).exec();


    }

}
