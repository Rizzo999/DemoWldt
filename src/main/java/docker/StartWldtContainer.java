package docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.PortBinding;
import docker.utils.DockerConf;

public class StartWldtContainer {

    public static void main(String args[]) {

        DockerClient dockerClient = DockerConf.conf();
        CreateContainerResponse container
                = dockerClient.createContainerCmd("orch-wldt:0.1")
                .withName("wl-digital-twin")
                //.withEnv()
                .withBinds(Bind.parse("/home/andrea/Desktop/DemoWldt/docker/logback.xml:/usr/local/src/mvnapp/src/main/resources/logback.xml"))
                .withBinds(Bind.parse("/home/andrea/Desktop/DemoWldt/docker/logback.xml:/usr/local/src/mvnapp/target/classes/logback.xml"))
                .exec();

        dockerClient.startContainerCmd(container.getId()).exec();

    }

}
