package com.bokesoft.example.config;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Plugin(name = "EsAppender", category = "Core", elementType = "appender", printObject = true)
class EsAppender extends AbstractAppender {
    private static String host;
    private static Integer port;
    private static String index;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected EsAppender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions);
    }
    // 自定义log操作，存储到es
    @Override
    public void append(LogEvent event) {

    }

    public String parseException(StackTraceElement[] stackTrace) {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        Arrays.stream(stackTrace).forEach((e) -> sb.append(e.getClassName()).append(".").append(e.getMethodName()).append("(").append(e.getFileName()).append(":").append(e.getLineNumber()).append(")").append("\n")
        );
        return sb.toString();
    }

    /**
     * 接收配置文件中的参数
     *
     * @param name
     * @param filter
     * @param layout
     * @return
     */
    @PluginFactory
    public static EsAppender createAppender(@PluginAttribute("name") String name,
                                            @PluginElement("Filter") final Filter filter,
                                            @PluginElement("Layout") Layout<? extends Serializable> layout,
                                            @PluginAttribute("ignoreExceptions") boolean ignoreExceptions,
                                            @PluginAttribute("host") String host,
                                            @PluginAttribute("port") Integer port,
                                            @PluginAttribute("index") String index
    ) {
        if (name == null) {
            LOGGER.error("No name provided for ESAppender");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        EsAppender.host = host;
        EsAppender.port = port;
        EsAppender.index = index;
        return new EsAppender(name, filter, layout, ignoreExceptions);
    }
}
