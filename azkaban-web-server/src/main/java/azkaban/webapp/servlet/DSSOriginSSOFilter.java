package azkaban.webapp.servlet;

import azkaban.webapp.AzkabanWebServer;
import com.webank.wedatasphere.dss.standard.app.sso.origin.plugin.OriginSSOPluginFilter;
import com.webank.wedatasphere.dss.standard.app.sso.plugin.filter.UserInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterConfig;

import static azkaban.ServiceProvider.SERVICE_PROVIDER;

public class DSSOriginSSOFilter extends OriginSSOPluginFilter {

    private static final Logger logger = LoggerFactory.getLogger(DSSOriginSSOFilter.class.getName());

    private AzkabanWebServer application;

    @Override
    public void init(FilterConfig filterConfig) {
        this.application = SERVICE_PROVIDER.getInstance(AzkabanWebServer.class);
        super.init(filterConfig);
        logger.info("The DSSOriginSSOFilter Init");
    }

    @Override
    public UserInterceptor getUserInterceptor(FilterConfig filterConfig) {
        return new WTSSHttpRequestUserInterceptor(this.application);
    }
}
