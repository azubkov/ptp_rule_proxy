package azoo.com.ptp_rule_proxy;

import azoo.com.ptp_rule_proxy.args.provider.ArgumentProvider;
import azoo.com.ptp_rule_proxy.hex.HexDumpProxyPipelineFactory;
import azoo.com.ptp_rule_proxy.http.HttpProxyPipelineFactory;
import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/*run examples :
7777 192.168.201.110 8090
9028 google.com 9028
* */
public class PTPProxyMain {
    private static final Logger LOGGER = Logger.getLogger(PTPProxyMain.class);
    private ArgumentProvider argumentProvider;

    public void setArgumentProvider(ArgumentProvider argumentProvider) {
        this.argumentProvider = argumentProvider;
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/ptpmain.xml");
        PTPProxyMain mainRunner = (PTPProxyMain) ctx.getBean("mainRunner");

        // Parse command line options.
        String value = null;
        value = mainRunner.argumentProvider.getArgument("local.port");
        int localPort = Integer.parseInt(value);
        value = mainRunner.argumentProvider.getArgument("remote.host");
        String remoteHost = value;
        value = mainRunner.argumentProvider.getArgument("remote.port");
        int remotePort = Integer.parseInt(value);

        LOGGER.info("[connection uid][current time mills]");
        LOGGER.info(String.format("Proxying *:%d to %s:%d ...", localPort, remoteHost, remotePort));
        // Configure the bootstrap.
        Executor executor = Executors.newCachedThreadPool();
        ServerBootstrap sb = new ServerBootstrap(
                new NioServerSocketChannelFactory(executor, executor));
        // Set up the event pipeline factory.
        ClientSocketChannelFactory cf =
                new NioClientSocketChannelFactory(executor, executor);
//        sb.setPipelineFactory(
//                new HexDumpProxyPipelineFactory(cf, remoteHost, remotePort));
//        sb.setPipelineFactory(
//                new HttpProxyPipelineFactory(cf, remoteHost, remotePort));
        final ProtocolDetector protocolDetector = new ProtocolDetector(
                new HttpProxyPipelineFactory(cf, remoteHost, remotePort),
                new HexDumpProxyPipelineFactory(cf, remoteHost, remotePort)
        );
        sb.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("detector", protocolDetector);
                return pipeline;
            }
        });

        // Start up the server.
        sb.bind(new InetSocketAddress(localPort));
    }
}