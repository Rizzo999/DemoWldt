package docker.utils;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;

public class DockerConf {

    public static DockerClient conf(){

        DockerClientConfig custom = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("unix:///var/run/docker.sock")
                .withDockerTlsVerify(false)
                //.withDockerCertPath("/home/user/.docker")
                .withRegistryUsername("xxxx") //set you Dockerhub username
                .withRegistryPassword("xxxx") //set your Dockerhub password
                .withRegistryEmail("rizzini.andrea99@gmail.com")
                .withRegistryUrl("https://hub.docker.com/")
                .build();

        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(custom.getDockerHost())
                .sslConfig(custom.getSSLConfig())
                .build();

        DockerClient dockerClient = DockerClientImpl.getInstance(custom, httpClient);

        dockerClient.pingCmd().exec(); //start docker client

        return dockerClient;

    }
}
