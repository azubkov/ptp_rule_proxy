package azoo.com.ptp_rule_proxy;

import azoo.com.ptp_rule_proxy.args.provider.ArgumentProvider;
import azoo.com.ptp_rule_proxy.generated.RootType;
import azoo.com.ptp_rule_proxy.handler.hex.HexDumpProxyPipelineFactory;
import azoo.com.ptp_rule_proxy.handler.http.HttpProxyPipelineFactory;
import azoo.com.ptp_rule_proxy.xml.FileLoader;
import azoo.com.ptp_rule_proxy.xml.ReplacementBuilder;
import azoo.com.ptp_rule_proxy.xml.ReplacementWrapper;
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

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PTPProxyMain {
    private static final Logger LOGGER = Logger.getLogger(PTPProxyMain.class);
    private ArgumentProvider argumentProvider;
    private ReplacementBuilder replacementBuilder;
    private FileLoader fileLoader;

    public void setFileLoader(FileLoader fileLoader) {
        this.fileLoader = fileLoader;
    }

    public void setReplacementBuilder(ReplacementBuilder replacementBuilder) {
        this.replacementBuilder = replacementBuilder;
    }

    public void setArgumentProvider(ArgumentProvider argumentProvider) {
        this.argumentProvider = argumentProvider;
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("ptpspring/ptpmain.xml");
        PTPProxyMain mainRunner = (PTPProxyMain) ctx.getBean("mainRunner");

        // Parse command line options.
        String value;
        int localPort;
        {
            value = mainRunner.argumentProvider.getArgument("local.port");
            localPort = Integer.parseInt(value);
        }
        String remoteHost;
        {
            value = mainRunner.argumentProvider.getArgument("remote.host");
            remoteHost = value;
        }
        int remotePort;
        {
            value = mainRunner.argumentProvider.getArgument("remote.port");
            remotePort = Integer.parseInt(value);
        }
        String replacementConfigFile;
        {
            value = mainRunner.argumentProvider.getArgument("replacement.config.file");
            replacementConfigFile = value;
        }
        LOGGER.error(String.format("Proxying *:%d to %s:%d ...", localPort, remoteHost, remotePort));
        mainRunner.printUsage();
        String xml= mainRunner.fileLoader.readContentSafely(replacementConfigFile);

        ReplacementWrapper replacementWrapper = mainRunner.replacementBuilder.toReplacement(xml);
        LOGGER.info("XML used:\n" + mainRunner.replacementBuilder.toXml(replacementWrapper));
        // Configure the bootstrap.
        Executor executor = Executors.newCachedThreadPool();
        ServerBootstrap sb = new ServerBootstrap(new NioServerSocketChannelFactory(executor, executor));
        // Set up the event pipeline factory.
        ClientSocketChannelFactory cf = new NioClientSocketChannelFactory(executor, executor);
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

    private void printUsage() {
        LOGGER.error("[connection uid][current time mills]");
    }
}