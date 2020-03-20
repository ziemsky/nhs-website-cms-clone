package uk.nhs.digital.common.components;

import static org.apache.commons.collections.IteratorUtils.toList;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.builder.HstQueryBuilder;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.nhs.digital.website.beans.CyberAlert;
import uk.nhs.digital.website.beans.DscHome;

import java.util.Calendar;

public class DscHomeComponent extends BaseHstComponent {

    private static Logger log = LoggerFactory.getLogger(DscHomeComponent.class);

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        final HstRequestContext ctx = request.getRequestContext();
        DscHome dscHome = ctx.getContentBean(DscHome.class);
        if (dscHome != null) {
            request.setAttribute("document", dscHome);
        }

        try {
            HippoBean scope = ctx.getSiteContentBaseBean();

            HstQuery hstAlertsQuery = HstQueryBuilder.create(scope)
                .ofTypes(CyberAlert.class).orderByDescending(Calendar.getInstance().toString())
                .limit(getAlertPageSize(request))
                .build();

            HstQueryResult alertsResult = hstAlertsQuery.execute();
            request.setAttribute("cyberAlertsList", toList(alertsResult.getHippoBeans()));

        } catch (QueryException queryException) {
            log.warn("QueryException ", queryException);
        }
    }

    protected int getAlertPageSize(final HstRequest request) {
        //getting the componentPageSize parameter value if defined in the component
        String compononentPageSize = StringUtils.defaultIfEmpty(getComponentLocalParameter("alertPageSize"), "");
        //if the componentPageSize hasn't been defined, then use the component param info
        return NumberUtils.isCreatable(compononentPageSize)
            ? Integer.parseInt(compononentPageSize) : 3;
    }

}
